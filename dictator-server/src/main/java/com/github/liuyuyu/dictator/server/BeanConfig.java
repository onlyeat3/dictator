package com.github.liuyuyu.dictator.server;

import com.github.liuyuyu.dictator.service.redis.RedisConfigService;
import io.vertx.core.Vertx;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
public class BeanConfig {

    @Bean
    public ExecutorService executors(){
        return Executors.newWorkStealingPool();
    }
    @Bean
    public RedisConfigService redisConfigService(){
        return new RedisConfigService();
    }

    @Bean
    public Vertx vertx(){
        return Vertx.vertx();
    }
}
