package com.github.liuyuyu.dictator.spring;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Properties;

/*
 * @author liuyuyu
 */
@Data
@Slf4j
@EqualsAndHashCode(callSuper = true)
public class DictatorPropertySourcesPlaceholderConfigurer extends PropertyPlaceholderConfigurer implements InitializingBean, ApplicationContextAware {
    private ApplicationContext applicationContext;

    @Override
    protected String resolvePlaceholder(@NonNull String placeholder, Properties props, int systemPropertiesMode) {
        return DictatorPropertyManager.getProperty(placeholder);
    }

    @Override
    public void afterPropertiesSet() {
        DictatorPropertyManager.init(this.applicationContext);
        log.info("loaded.");
    }
}
