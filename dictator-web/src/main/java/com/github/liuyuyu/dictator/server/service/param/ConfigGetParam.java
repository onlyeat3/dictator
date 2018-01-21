package com.github.liuyuyu.dictator.server.service.param;

import com.github.liuyuyu.dictator.common.model.request.PropertyGetRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/*
 * @author liuyuyu
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ConfigGetParam extends CommonParam {
    /**
     * 配置值
     */
    private String defaultValue;

    public static ConfigGetParam from(CommonParam commonParam) {
        ConfigGetParam configGetParam = new ConfigGetParam();
        configGetParam.setKey(commonParam.getKey());
        configGetParam.setAppId(commonParam.getAppId());
        return configGetParam;
    }

    public static ConfigGetParam from(PropertyGetRequest propertyGetRequest) {
        ConfigGetParam configGetParam = new ConfigGetParam();
        configGetParam.setAppId(propertyGetRequest.getAppId());
        configGetParam.setDeploymentId(propertyGetRequest.getDeploymentId());
        configGetParam.setKey(propertyGetRequest.getPropertyName());
        return configGetParam;
    }
}
