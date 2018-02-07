package com.github.liuyuyu.dictator.spring;

import com.github.liuyuyu.dictator.client.DictatorClient;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.core.env.Environment;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.locks.ReentrantLock;

/*
 * @author liuyuyu
 */
@Data
@Slf4j
@EqualsAndHashCode(callSuper = true)
public class DictatorPropertySourcesPlaceholderConfigurer extends PropertyPlaceholderConfigurer implements InitializingBean {
    @Autowired
    private Environment environment;

    @Getter
    private Map<String,String> configCache = new HashMap<>();
    private DictatorClient dictatorClient;
    private ReentrantLock lock = new ReentrantLock();

    @Override
    protected String resolvePlaceholder(@NonNull String placeholder, Properties props, int systemPropertiesMode) {
        String cachedValue = this.configCache.get(placeholder);
        if(cachedValue != null){
            return cachedValue;
        }else{
            return this.dictatorClient.get(placeholder);
        }
    }

    @Override
    public void afterPropertiesSet() {
        this.refreshCache();
        log.info("start.");
    }

    public void refreshCache(){
        this.lock.lock();
        try {
            Map<String, String> currentConfigMap = this.getDictatorClient().reload();
            this.configCache.clear();
            this.configCache.putAll(currentConfigMap);
        }finally {
            this.lock.unlock();
        }
    }
}
