package com.github.liuyuyu.dictator.spring;

import com.github.liuyuyu.dictator.core.ConfigService;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import java.util.Properties;

/*
 * @author liuyuyu
 */
public class DictatorPropertySourcesPlaceholderConfigurer extends PropertyPlaceholderConfigurer {
    private ConfigService configService;

    @Override
    protected String resolvePlaceholder(String placeholder, Properties props, int systemPropertiesMode) {
        return super.resolvePlaceholder(placeholder, props, systemPropertiesMode);
    }

    @Override
    protected String resolvePlaceholder(String placeholder, Properties props) {
        return super.resolvePlaceholder(placeholder, props);
    }
}
