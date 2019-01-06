package com.slasher.mall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = "com.slasher.mall")
public class MallManageServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallManageServiceApplication.class, args);
    }

}

