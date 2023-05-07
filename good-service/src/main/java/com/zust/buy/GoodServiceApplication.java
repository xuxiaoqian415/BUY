package com.zust.buy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.zust.buy.good.mapper")
public class GoodServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(GoodServiceApplication.class, args);
    }

}
