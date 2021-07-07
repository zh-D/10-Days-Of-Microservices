package com.offcn.spring002;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.offcn.spring002.dao")
public class Spring002Application {

    public static void main(String[] args) {
        SpringApplication.run(Spring002Application.class, args);
    }

}
