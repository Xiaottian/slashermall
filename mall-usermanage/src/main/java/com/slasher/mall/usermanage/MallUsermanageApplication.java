package com.slasher.mall.usermanage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.slasher.mall.usermanage.mapper")
public class MallUsermanageApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallUsermanageApplication.class, args);
    }

}

