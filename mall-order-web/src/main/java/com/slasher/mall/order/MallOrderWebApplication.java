package com.slasher.mall.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.slasher.mall")
public class MallOrderWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallOrderWebApplication.class, args);
    }

}

