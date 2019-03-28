package com.github.liuyuyu.dictator.springboot;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "dictator")
public class DictatorSpringBootProperties{
    /**
     * 配置中心服务端地址
     */
    private String serverUrl;
    /**
     * 应用ID
     */
    private String appCode;
    /**
     * profile code
     */
    private String profile = StringUtils.EMPTY;
}
