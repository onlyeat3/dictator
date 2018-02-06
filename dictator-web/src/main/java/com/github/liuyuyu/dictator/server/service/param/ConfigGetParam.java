package com.github.liuyuyu.dictator.server.service.param;

import com.github.liuyuyu.dictator.common.BaseProperties;
import com.github.liuyuyu.dictator.common.model.request.PropertyGetRequest;
import lombok.*;

/*
 * @author liuyuyu
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
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
        configGetParam.setProfile(propertyGetRequest.getProfile());
        configGetParam.setKey(propertyGetRequest.getPropertyName());
        return configGetParam;
    }

    public static ConfigGetParam from(@NonNull BaseProperties source){
        ConfigGetParam target = new ConfigGetParam();
        target.setAppId(source.getAppId());
        target.setDeploymentId(source.getDeploymentId());
        target.setProfile(source.getProfile());
        target.setVersion(source.getVersion());
        return target;
    }
}
