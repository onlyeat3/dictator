package com.github.liuyuyu.dictator.server.utils;


import com.github.liuyuyu.dictator.service.param.ConfigGetParam;

/**
 * @author liuyuyu
 */
public class ParamBuilder {
    public static ConfigGetParam buildCommonParam() {
        ConfigGetParam param = new ConfigGetParam();
        param.setAppCode("app");
        param.setProfile("dev");
        param.setKey("spring.datasource.username");
        return param;
    }
}
