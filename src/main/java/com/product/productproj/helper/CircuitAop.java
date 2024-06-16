package com.product.productproj.helper;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.concurrent.*;

@Component
@Aspect
public class CircuitAop {

    private static ConcurrentHashMap<String, Semaphore> LIMITER = new ConcurrentHashMap<>();

    @Pointcut("@annotation(com.product.productproj.helper.CircuitConfig)")
    public void point() {

    }

    @Around("point()")
    public Object limit(ProceedingJoinPoint proceedingJoinPoint) {
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
        //超时处理
        CircuitConfig limit = methodSignature.getMethod().getDeclaredAnnotation(CircuitConfig.class);
        if (limit.timeout() > 0) {
            ExecutorService es = Executors.newFixedThreadPool(2);
            Future future = es.submit(() -> {
                try {
                    return proceedingJoinPoint.proceed();
                } catch (Throwable throwable) {
                    return null;
                }
            });
            final Object obj;
            try {
                obj = future.get(limit.timeout(), TimeUnit.MILLISECONDS);
                return obj;
            } catch (Exception e) {
                future.cancel(true);
                throw new RuntimeException("处理超时");
            }

        } else if (limit.failCount() > 0) {
            // 允许同时访问的线程（进程内的锁）
            String cacheKey = proceedingJoinPoint.getTarget().getClass().getName() + "::" + methodSignature.getName()
                    + "::" + Arrays.toString(methodSignature.getParameterNames());
            LIMITER.putIfAbsent(cacheKey, new Semaphore(limit.failCount()));
            System.out.println(cacheKey);

            Semaphore semaphore = LIMITER.get(cacheKey);
            try {
                semaphore.acquire();
                proceedingJoinPoint.proceed();
            } catch (Throwable throwable) {
                throw new RuntimeException("请求异常");
            } finally {
                // 释放
                if (null != semaphore) {
                    semaphore.release();
                }
            }

        }


        try {
            return proceedingJoinPoint.proceed();
        } catch (Throwable throwable) {
            return null;
        }

    }

}
