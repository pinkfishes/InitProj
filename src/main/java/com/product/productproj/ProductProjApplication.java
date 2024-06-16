package com.product.productproj;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;


@SpringBootApplication
@MapperScan("com.product.productproj.mapper")
@EnableAspectJAutoProxy
public class ProductProjApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductProjApplication.class, args);
    }

}
