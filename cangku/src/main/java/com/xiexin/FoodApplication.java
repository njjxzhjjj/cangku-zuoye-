package com.xiexin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.xiexin.dao")
public class FoodApplication {

    public static void main(String[] args) {
        System.out.println("springboot 项目 启动啦......");
        SpringApplication.run(FoodApplication.class, args);
    }

}
