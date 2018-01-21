package com.github.liuyuyu.dictator.client;

import lombok.Data;
import lombok.NonNull;

/*
 * @author liuyuyu
 */
@Data
public class DictatorClientProperties {

    public static DictatorClientProperties of(){
        return new DictatorClientProperties();
    }

    public static DictatorClientProperties of(@NonNull String appId, @NonNull String deploymentId, @NonNull String serverUrl){
        DictatorClientProperties dictatorClientProperties = new DictatorClientProperties();
        dictatorClientProperties.setAppId(appId);
        dictatorClientProperties.setDeploymentId(deploymentId);
        dictatorClientProperties.setServerUrl(serverUrl);
        return dictatorClientProperties;
    }

    /**
     * 应用ID
     */
    private String appId;
    /**
     * 部署的节点ID
     */
    private String deploymentId;
    /**
     * 配置中心服务端地址
     */
    private String serverUrl;
}
