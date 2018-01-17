package com.github.liuyuyu.dictator.spring;

import com.github.liuyuyu.dictator.core.ConfigService;
import com.github.liuyuyu.dictator.core.ZkProperties;
import com.github.liuyuyu.dictator.api.param.ConfigGetParam;
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
    private ConfigService configService;

    private ZkProperties zkProperties;

    @Override
    protected String resolvePlaceholder(@NonNull String placeholder, Properties props, int systemPropertiesMode) {
        String localPropertyValue = super.resolvePlaceholder(placeholder, props, systemPropertiesMode);
        if (StringUtils.isNotEmpty(localPropertyValue)) {
            return localPropertyValue;
        }
        ConfigGetParam configGetParam = new ConfigGetParam();
        configGetParam.setKey(placeholder);
        configGetParam.setPath(this.zkProperties.getBasePath());
        return this.configService.find(configGetParam);
    }
}
