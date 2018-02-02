package com.github.liuyuyu.dictator.server.config;

import com.github.liuyuyu.dictator.server.service.zookeeper.ZookeeperConfigService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * @author liuyuyu
 */
@Slf4j
@Configuration
public class BeanConfig {

    @Bean("zkConfigService")
    @Conditional(ZkCondition.class)
    public ZookeeperConfigService zkConfigService(){
        ZookeeperConfigService zookeeperConfigService = new ZookeeperConfigService();
        log.info("Bean zkConfigService loaded.");
        return zookeeperConfigService;
    }
}
