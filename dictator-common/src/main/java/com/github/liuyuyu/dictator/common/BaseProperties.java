package com.github.liuyuyu.dictator.common;

import lombok.Data;
import lombok.NonNull;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.NotBlank;

/*
 * @author liuyuyu
 */
@Data
public class BaseProperties {
    /**
     * 应用ID
     */
    @NotBlank
    private String appId;
    /**
     * 部署的节点ID
     */
    @NotBlank
    private String deploymentId;
    /**
     * profile code
     */
    private String profile = StringUtils.EMPTY;

    /**
     * 配置的版本
     */
    private Long lastUpdatedTime;

    public static BaseProperties from(@NonNull BaseProperties source) {
        BaseProperties target = new BaseProperties();
        target.setAppId(source.getAppId());
        target.setDeploymentId(source.getDeploymentId());
        target.setProfile(source.getProfile());
        target.setLastUpdatedTime(source.getLastUpdatedTime());
        return target;
    }
}
