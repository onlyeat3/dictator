package com.github.liuyuyu.dictator.spring;

import org.springframework.core.env.PropertySource;

/**
 * @author liuyuyu
 */
public class DictatorPropertySource extends PropertySource<String> {
    public static final String NAME = "dictatorPropertySource";

    public DictatorPropertySource() {
        super(NAME);
    }

    public DictatorPropertySource(String name) {
        super(name);
    }

    @Override
    public String getProperty(String name) {
        return DictatorPropertyManager.getProperty(name);
    }

    @Override
    public String getName() {
        return NAME;
    }
}
