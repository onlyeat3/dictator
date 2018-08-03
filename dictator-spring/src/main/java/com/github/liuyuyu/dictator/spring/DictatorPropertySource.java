package com.github.liuyuyu.dictator.spring;

import org.springframework.core.env.MapPropertySource;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * @author liuyuyu
 */
public class DictatorPropertySource extends MapPropertySource {
    public static final String NAME = "dictatorPropertySource";

    public DictatorPropertySource() {
        this(NAME, DictatorPropertyManager.CONFIG_CACHE);
    }

    public DictatorPropertySource(String name, Map<String, Object> source) {
        super(name, source);
    }

    @Override
    public boolean containsProperty(String name) {
        return DictatorPropertyManager.containsName(name);
    }

    @Override
    public String[] getPropertyNames() {
        return StringUtils.toStringArray(DictatorPropertyManager.CONFIG_CACHE.keySet());
    }

    @Override
    public String getProperty(String name) {
        return DictatorPropertyManager.getOrRemoteGet(name);
    }

    @Override
    public String getName() {
        return NAME;
    }
}
