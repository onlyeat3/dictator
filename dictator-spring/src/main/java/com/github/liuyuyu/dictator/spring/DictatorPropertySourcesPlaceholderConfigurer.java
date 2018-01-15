package com.github.liuyuyu.dictator.spring;

import com.github.liuyuyu.dictator.core.ConfigService;
import com.github.liuyuyu.dictator.core.ZkProperties;
import com.github.liuyuyu.dictator.core.param.ConfigGetParam;
import lombok.Data;
import lombok.NonNull;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import java.util.Properties;

/*
 * @author liuyuyu
 */
@Data
public class DictatorPropertySourcesPlaceholderConfigurer extends PropertyPlaceholderConfigurer {
    private ConfigService configService;
    private ZkProperties zkProperties;

    @Override
    protected String resolvePlaceholder(@NonNull String placeholder, Properties props, int systemPropertiesMode) {
        boolean isDictatorConfigProperties = ZkProperties.getPropertiesNames().contains(placeholder);
        if(isDictatorConfigProperties){
            return super.resolvePlaceholder(placeholder,props,systemPropertiesMode);
        }
        ConfigGetParam configGetParam = new ConfigGetParam();
        configGetParam.setKey(placeholder);
        configGetParam.setPath(this.zkProperties.getBasePath());
        return this.configService.find(configGetParam);
    }

    @Override
    protected String resolvePlaceholder(String placeholder, Properties props) {
        return this.resolvePlaceholder(placeholder,props,0);
    }
}
