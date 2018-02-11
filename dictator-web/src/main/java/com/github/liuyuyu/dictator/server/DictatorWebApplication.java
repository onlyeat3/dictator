package com.github.liuyuyu.dictator.server;

import com.github.liuyuyu.dictator.server.service.zookeeper.ZkProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableConfigurationProperties(ZkProperties.class)
@SpringBootApplication
public class DictatorWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(DictatorWebApplication.class, args);
    }
}
