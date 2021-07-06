package com.offon.springdemo001;

import com.offon.springdemo001.pojo.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringDemo001Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringDemo001Application.class, args);
    }

    // 实体类和配置文件的映射
    @Bean
    @ConfigurationProperties(prefix = "user")
    public User user() {
        return new User();
    }
}
