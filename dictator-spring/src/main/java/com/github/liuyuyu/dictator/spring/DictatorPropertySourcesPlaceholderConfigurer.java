package com.github.liuyuyu.dictator.spring;

import com.github.liuyuyu.dictator.client.DictatorClient;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import java.util.Properties;

/*
 * @author liuyuyu
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class DictatorPropertySourcesPlaceholderConfigurer extends PropertyPlaceholderConfigurer {
    private DictatorClient dictatorClient;

    @Override
    protected String resolvePlaceholder(@NonNull String placeholder, Properties props, int systemPropertiesMode) {
        return this.dictatorClient.get(placeholder);
    }
}
