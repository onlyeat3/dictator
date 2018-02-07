package com.github.liuyuyu.dictator.spring;

import com.github.liuyuyu.dictator.client.DictatorClient;
import lombok.Getter;
import lombok.Setter;
import org.springframework.core.env.PropertySource;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author liuyuyu
 */
public class DictatorPropertySource extends PropertySource<String> {
    @Setter
    private DictatorClient dictatorClient;

    @Getter
    private Map<String,String> configCache = new HashMap<>();
    private ReentrantLock lock = new ReentrantLock();

    public DictatorPropertySource(){
        super("dictatorConfigPropertySource");
        this.refreshCache();
    }

    public DictatorPropertySource(String name) {
        super(name);
    }

    @Override
    public String getProperty(String name) {
        String cachedValue = this.configCache.get(name);
        if(cachedValue != null){
            return cachedValue;
        }else{
            return this.dictatorClient.get(name);
        }
    }

    public void refreshCache(){
        this.lock.lock();
        try {
            Map<String, String> currentConfigMap = this.dictatorClient.reload();
            this.configCache.clear();
            this.configCache.putAll(currentConfigMap);
        }finally {
            this.lock.unlock();
        }
    }
}
