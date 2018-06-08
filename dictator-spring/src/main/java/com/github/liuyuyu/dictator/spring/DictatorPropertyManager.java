package com.github.liuyuyu.dictator.spring;

import com.github.liuyuyu.dictator.client.DictatorClient;
import com.github.liuyuyu.dictator.client.DictatorClientProperties;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

/**
 * @author liuyuyu
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DictatorPropertyManager {
    public static final Map<String, Object> CONFIG_CACHE = new HashMap<>();
    private static final ReentrantLock LOCK = new ReentrantLock();
    private static DictatorClient DICTATOR_CLIENT;
    private static Long LAST_UPDATED_TIME;

    static String getProperty(@NonNull String name) {
        LOCK.lock();
        try {
            Object cachedValue = CONFIG_CACHE.get(name);
            if (cachedValue != null) {
                return String.valueOf(cachedValue);
            } else {
                return null;
            }
        } finally {
            LOCK.unlock();
        }
    }

    public static void refreshCache() {
        Assert.notNull(DICTATOR_CLIENT, "DICTATOR_CLIENT not found.");
        LOCK.lock();
        try {
            Map<String, String> currentConfigMap = DICTATOR_CLIENT.loadAll(LAST_UPDATED_TIME);
            if (currentConfigMap.isEmpty()) {
                return;
            }
            LAST_UPDATED_TIME = System.currentTimeMillis();
            CONFIG_CACHE.putAll(currentConfigMap);
        } finally {
            LOCK.unlock();
        }
    }

    /**
     * 能够获取原来配置，可以用的初始化方式
     */
    public static void init(@NonNull Environment environment) {
        //取Spring的profile作为环境区分的标识
        List<String> activeProfileList = Arrays.stream(environment.getActiveProfiles())
                .collect(Collectors.toList());
        String mainProfile = activeProfileList.stream()
                .findFirst()
                .orElse(environment.getDefaultProfiles()[0]);
        DictatorClientProperties dictatorClientProperties = DictatorClientProperties.of();
        dictatorClientProperties.setProfile(mainProfile);
        dictatorClientProperties.setServerUrl(environment.getProperty("dictator.serverUrl"));
        dictatorClientProperties.setAppCode(environment.getProperty("dictator.appId"));
        dictatorClientProperties.setProfile(mainProfile);
        // 打印参数
        dictatorClientProperties.printCurrent();
        //验证参数
        dictatorClientProperties.verify();
        DICTATOR_CLIENT = DictatorClient.of(dictatorClientProperties);
        DictatorPropertyManager.refreshCache();
    }

    public static boolean containsName(@NonNull String name) {
        return CONFIG_CACHE.containsKey(name);
    }

    /**
     * 加载时不能获取到系统配置，使用的初始化方式
     */
    public static void init(@NonNull DictatorClientProperties dictatorClientProperties) {
        // 打印参数
        dictatorClientProperties.printCurrent();
        //验证参数
        dictatorClientProperties.verify();
        DICTATOR_CLIENT = DictatorClient.of(dictatorClientProperties);
        DictatorPropertyManager.refreshCache();
    }
}
