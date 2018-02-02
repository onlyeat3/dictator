package com.github.liuyuyu.dictator.client;

import com.github.liuyuyu.dictator.common.BaseProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

/*
 * @author liuyuyu
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class DictatorClientProperties extends BaseProperties {

    public static DictatorClientProperties of(){
        return new DictatorClientProperties();
    }

    public static DictatorClientProperties of(@NonNull String appId, @NonNull String deploymentId,@NonNull String profile, @NonNull String serverUrl){
        DictatorClientProperties dictatorClientProperties = new DictatorClientProperties();
        dictatorClientProperties.setAppId(appId);
        dictatorClientProperties.setDeploymentId(deploymentId);
        dictatorClientProperties.setProfile(profile);
        dictatorClientProperties.setServerUrl(serverUrl);
        return dictatorClientProperties;
    }

    /**
     * 配置中心服务端地址
     */
    private String serverUrl;
}
