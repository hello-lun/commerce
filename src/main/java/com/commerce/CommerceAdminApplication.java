package com.commerce;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.commerce.mapper")
public class CommerceAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(CommerceAdminApplication.class, args);
    }

}
