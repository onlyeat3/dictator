package com.github.liuyuyu.dictator.server.utils;

import com.github.liuyuyu.dictator.server.core.service.param.ConfigGetParam;

/**
 * @author liuyuyu
 */
public class ParamBuilder {
    public static ConfigGetParam buildCommonParam() {
        ConfigGetParam param = new ConfigGetParam();
        param.setAppId("app");
        param.setDeploymentId("db");
        param.setProfile("dev");
        param.setKey("spring.datasource.username");
        return param;
    }
}
