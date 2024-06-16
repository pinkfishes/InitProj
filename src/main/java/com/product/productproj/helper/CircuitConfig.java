package com.product.productproj.helper;

import java.lang.annotation.*;

@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CircuitConfig {

    int failCount() default -1;
    long timeout() default -1;
//    String fallbackMethod();
}
