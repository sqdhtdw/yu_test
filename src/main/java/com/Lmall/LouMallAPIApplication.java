package com.Lmall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 商城
 */
@MapperScan("com.Lmall.dao")
@SpringBootApplication
public class LouMallAPIApplication {

    public static void main(String[] args) {
        SpringApplication.run(LouMallAPIApplication.class, args);
    }

}
