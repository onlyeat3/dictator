package com.github.liuyuyu.dictator.spring;

import com.github.liuyuyu.dictator.client.DictatorClient;
import com.github.liuyuyu.dictator.client.DictatorClientProperties;
import com.github.liuyuyu.dictator.spring.loader.DictatorBootstrapPropertiesLoader;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.Assert;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author liuyuyu
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DictatorPropertyManager {
    private static DictatorClient DICTATOR_CLIENT;
    private static final Map<String, String> CONFIG_CACHE = new HashMap<>();
    private static final ReentrantLock LOCK = new ReentrantLock();

    public static String getProperty(@NonNull String name) {
        LOCK.lock();
        try {
            String cachedValue = CONFIG_CACHE.get(name);
            if (cachedValue != null) {
                return cachedValue;
            } else {
                return null;
            }
        } finally {
            LOCK.unlock();
        }
    }

    public static void refreshCache() {
        Assert.notNull(DICTATOR_CLIENT,"DICTATOR_CLIENT not found.");
        LOCK.lock();
        try {
            Map<String, String> currentConfigMap = DICTATOR_CLIENT.reload();
            CONFIG_CACHE.clear();
            CONFIG_CACHE.putAll(currentConfigMap);
        } finally {
            LOCK.unlock();
        }
    }

    public static void init(@NonNull ApplicationContext applicationContext) {
        Resource resource = applicationContext.getResource(ResourceLoader.CLASSPATH_URL_PREFIX + "dictator.properties");
        try {
            DictatorClientProperties dictatorClientProperties = DictatorBootstrapPropertiesLoader.from(resource);
            DICTATOR_CLIENT = DictatorClient.of(dictatorClientProperties);
            DictatorPropertyManager.refreshCache();
        } catch (IOException e) {
            log.error("dictator init load fail",e);
        }
    }
}
