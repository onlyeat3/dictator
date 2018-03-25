package com.github.liuyuyu.dictator.spring.loader;

import com.github.liuyuyu.dictator.client.DictatorClientProperties;
import lombok.NonNull;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.Properties;

/*
 * @author liuyuyu
 */
public class DictatorBootstrapPropertiesLoader {
    public static DictatorClientProperties from(@NonNull Resource resource) throws IOException {
        Properties properties = new Properties();
        properties.load(resource.getInputStream());
        DictatorClientProperties dictatorClientProperties = new DictatorClientProperties();
        String appId = properties.getProperty("appId");
        String serverUrl = properties.getProperty("serverUrl");
        String profile = properties.getProperty("profile");

        dictatorClientProperties.setAppId(appId);
        dictatorClientProperties.setServerUrl(serverUrl);
        dictatorClientProperties.setProfile(profile);

        return dictatorClientProperties;
    }

}
