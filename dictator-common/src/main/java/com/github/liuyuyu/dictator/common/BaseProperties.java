package com.github.liuyuyu.dictator.common;

import com.sun.istack.internal.NotNull;
import lombok.Data;
import lombok.NonNull;
import org.hibernate.validator.constraints.NotBlank;

/*
 * @author liuyuyu
 */
@Data
public class BaseProperties {
    /**
     * 应用ID
     */
    @NotBlank private String appId;
    /**
     * 部署的节点ID
     */
    @NotBlank private String deploymentId;

    public static BaseProperties from(@NonNull BaseProperties source){
        BaseProperties target = new BaseProperties();
        target.setAppId(source.getAppId());
        target.setDeploymentId(source.getDeploymentId());
        return target;
    }
}
