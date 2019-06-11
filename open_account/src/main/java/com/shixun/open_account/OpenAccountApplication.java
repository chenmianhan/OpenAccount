package com.shixun.open_account;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.shixun.open_account.dao")
public class OpenAccountApplication {

    public static void main(String[] args) {
        SpringApplication.run(OpenAccountApplication.class, args);
    }

}
