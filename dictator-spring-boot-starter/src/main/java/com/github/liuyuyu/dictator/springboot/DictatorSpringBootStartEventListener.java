package com.github.liuyuyu.dictator.springboot;

import com.github.liuyuyu.dictator.client.DictatorClient;
import com.github.liuyuyu.dictator.client.DictatorClientProperties;
import com.github.liuyuyu.dictator.spring.DictatorConfigEnvironment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;

/**
 * @author liuyuyu
 */
@Slf4j
public class DictatorSpringBootStartEventListener implements SpringApplicationRunListener {

    public DictatorSpringBootStartEventListener() {
    }

    public DictatorSpringBootStartEventListener(SpringApplication springApplication, String... args){

    }

    @Override
    public void starting() {
    }

    @Override
    public void environmentPrepared(ConfigurableEnvironment configurableEnvironment) {
    }

    @Override
    public void contextPrepared(ConfigurableApplicationContext applicationContext) {
        Resource resource = applicationContext.getResource(ResourceLoader.CLASSPATH_URL_PREFIX + "dictator.properties");
        try {
            DictatorClientProperties dictatorClientProperties = DictatorBootstrapPropertiesLoader.from(resource);
            DictatorClient dictatorClient = DictatorClient.of(dictatorClientProperties);

            ConfigurableEnvironment environment = applicationContext.getEnvironment();
            if (environment != null) {
                DictatorConfigEnvironment dictatorConfigEnvironment = DictatorConfigEnvironment.from(dictatorClient);
                environment.merge(dictatorConfigEnvironment);
            }
            log.info("dictator loaded.");
        } catch (IOException e) {
            log.error("dictator load fail",e);
        }
    }

    @Override
    public void contextLoaded(ConfigurableApplicationContext configurableApplicationContext) {

    }

    @Override
    public void finished(ConfigurableApplicationContext configurableApplicationContext, Throwable throwable) {

    }
}
