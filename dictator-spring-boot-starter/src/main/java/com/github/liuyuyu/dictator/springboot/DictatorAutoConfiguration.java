package com.github.liuyuyu.dictator.springboot;

import com.github.liuyuyu.dictator.client.DictatorClient;
import com.github.liuyuyu.dictator.client.DictatorClientProperties;
import com.github.liuyuyu.dictator.spring.DictatorPropertySourcesPlaceholderConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;

/*
 * @author liuyuyu
 */
@Configuration
public class DictatorAutoConfiguration {

    @Bean
    public DictatorClient dictatorClient(@Autowired ApplicationContext applicationContext) throws IOException {
        Resource resource = applicationContext.getResource(ResourceLoader.CLASSPATH_URL_PREFIX + "dictator.properties");
        DictatorClientProperties dictatorClientProperties = DictatorBootstrapPropertiesLoader.from(resource);
        return DictatorClient.of(dictatorClientProperties);
    }

    @Bean
    public static PropertyPlaceholderConfigurer propertyPlaceholderConfigurer(@Autowired DictatorClient dictatorClient) {
        DictatorPropertySourcesPlaceholderConfigurer dictatorPropertySourcesPlaceholderConfigurer = new DictatorPropertySourcesPlaceholderConfigurer();
        dictatorPropertySourcesPlaceholderConfigurer.setDictatorClient(dictatorClient);
        return dictatorPropertySourcesPlaceholderConfigurer;
    }

}
