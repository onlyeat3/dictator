package com.github.liuyuyu.dictator.springboot.config;

import com.github.liuyuyu.dictator.spring.DictatorAutoRefresher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

/*
 * @author liuyuyu
 */
@Configuration
public class BeanConfig {

    @Bean
    public ThreadPoolTaskScheduler taskScheduler(){
        return new ThreadPoolTaskScheduler();
    }
}
