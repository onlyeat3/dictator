package com.github.liuyuyu.dictator.springboot;

import com.github.liuyuyu.dictator.core.ConfigService;
import com.github.liuyuyu.dictator.core.ZkProperties;
import com.github.liuyuyu.dictator.core.ZookeeperConfigService;
import com.github.liuyuyu.dictator.spring.DictatorPropertySourcesPlaceholderConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.Properties;

/*
 * @author liuyuyu
 */
@Configuration
public class DictatorAutoConfiguration {
    public static String SPRING_APPLICATION_PROPERTIES_FILE_PATH = "application.properties";

    @Bean
    public ZkProperties zkProperties() throws IOException {
        Properties properties = new Properties();
        properties.load(ClassLoader.getSystemClassLoader().getResourceAsStream(SPRING_APPLICATION_PROPERTIES_FILE_PATH));
        String zkAddress = properties.getProperty("dictator.zkAddress");
        String basePath = properties.getProperty("dictator.basePath");

        ZkProperties zkProperties = new ZkProperties();
        zkProperties.setZkAddress(zkAddress);
        zkProperties.setBasePath(basePath);
        return zkProperties;
    }

    @Bean
    public static PropertyPlaceholderConfigurer properties(@Autowired ConfigService configService, @Autowired ZkProperties zkProperties) {
        DictatorPropertySourcesPlaceholderConfigurer dictatorPropertySourcesPlaceholderConfigurer = new DictatorPropertySourcesPlaceholderConfigurer();
        dictatorPropertySourcesPlaceholderConfigurer.setConfigService(configService);
        dictatorPropertySourcesPlaceholderConfigurer.setZkProperties(zkProperties);
        return dictatorPropertySourcesPlaceholderConfigurer;
    }

    @Bean(initMethod = "init")
    public ZookeeperConfigService configService(@Autowired ZkProperties zkProperties) {
        return new ZookeeperConfigService(zkProperties);
    }
}
