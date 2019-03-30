package com.github.liuyuyu.dictator.server;

import io.vertx.core.Vertx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(VertxProperties.class)
public class DictatorServerApplication implements CommandLineRunner {
    @Autowired private DictatorServerVerticle serverVerticle;

    public static void main(String[] args) {
        SpringApplication.run(DictatorServerApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        this.serverVerticle.start();
    }
}
