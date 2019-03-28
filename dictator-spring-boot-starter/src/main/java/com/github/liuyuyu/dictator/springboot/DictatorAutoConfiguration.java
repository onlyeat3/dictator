package com.github.liuyuyu.dictator.springboot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/*
 * @author liuyuyu
 */
@Slf4j
@Configuration
@EnableScheduling
@EnableConfigurationProperties(DictatorSpringBootProperties.class)
public class DictatorAutoConfiguration {
}
