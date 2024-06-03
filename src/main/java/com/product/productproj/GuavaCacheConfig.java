package com.product.productproj;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

public class GuavaCacheConfig {

    private Cache<String,Object> commonCache=null;

    @PostConstruct
    public void init() {
        commonCache= CacheBuilder.newBuilder().initialCapacity(10)
                .maximumSize(100).expireAfterWrite(60, TimeUnit.SECONDS).build();
    }

    @Bean
    public Cache<String,Object> getCommonCache() {
        return commonCache;
    }
}
