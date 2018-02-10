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
    public static final String NAME = "dictatorPropertySource";

    @Getter
    private Map<String,String> configCache = new HashMap<>();
    private ReentrantLock lock = new ReentrantLock();

    public DictatorPropertySource(){
        super(NAME);
    }

    public DictatorPropertySource(String name) {
        super(name);
    }

    @Override
    public String getProperty(String name) {
        this.lock.lock();
        try {
            String cachedValue = this.configCache.get(name);
            if(cachedValue != null){
                return cachedValue;
            }else{
                return null;
            }
        }finally {
            this.lock.unlock();
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

    @Override
    public String getName() {
        return NAME;
    }
}
