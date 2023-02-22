package com.yiran.nuomi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.yiran.nuomi.dao")
public class NuomiApplication {

    public static void main(String[] args) {

        // 调用 init 来做初始化，数据库初始化，回收站初始化

        SpringApplication.run(NuomiApplication.class, args);
    }

}
