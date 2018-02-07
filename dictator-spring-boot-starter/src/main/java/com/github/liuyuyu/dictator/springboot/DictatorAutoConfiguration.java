package com.github.liuyuyu.dictator.springboot;

import com.github.liuyuyu.dictator.client.DictatorClient;
import com.github.liuyuyu.dictator.client.DictatorClientProperties;
import com.github.liuyuyu.dictator.spring.DictatorConfigEnvironment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;

/*
 * @author liuyuyu
 */
@Slf4j
@Configuration
//@ConditionalOnBean({ConfigurationPropertiesBindingPostProcessor.class})
public class DictatorAutoConfiguration implements ApplicationContextAware {
    private ApplicationContext applicationContext;

    @Bean
    public static DictatorClient dictatorClient(ApplicationContext applicationContext) throws IOException {
        Resource resource = applicationContext.getResource(ResourceLoader.CLASSPATH_URL_PREFIX + "dictator.properties");
        DictatorClientProperties dictatorClientProperties = DictatorBootstrapPropertiesLoader.from(resource);
        return DictatorClient.of(dictatorClientProperties);
    }

    @Bean
    public static Environment environment(@Autowired ApplicationContext applicationContext, @Autowired DictatorClient dictatorClient) {
        Environment environment = applicationContext.getEnvironment();
        if (environment instanceof ConfigurableEnvironment) {
            DictatorConfigEnvironment dictatorConfigEnvironment = DictatorConfigEnvironment.from(dictatorClient);
            ((ConfigurableEnvironment) environment).merge(dictatorConfigEnvironment);
        }
        return environment;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
