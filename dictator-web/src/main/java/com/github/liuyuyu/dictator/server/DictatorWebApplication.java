package com.github.liuyuyu.dictator.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import tk.mybatis.spring.annotation.MapperScan;

@EnableScheduling
@SpringBootApplication
@MapperScan("com.github.liuyuyu.dictator.**.mapper")
public class DictatorWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(DictatorWebApplication.class, args);
    }
}
