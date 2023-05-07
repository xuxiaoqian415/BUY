package com.zust.buy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@MapperScan("com.zust.buy.wxpreorder.mapper")
public class WxPreOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(WxPreOrderApplication.class, args);
    }

}
