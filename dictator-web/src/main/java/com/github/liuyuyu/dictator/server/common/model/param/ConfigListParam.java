package com.github.liuyuyu.dictator.server.common.model.param;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/*
 * @author liuyuyu
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
public class ConfigListParam extends PageParam {
    /**
     * 应用ID
     */
    private String appId;
    /**
     * 部署ID
     */
    private String deploymentId;
    /**
     * 环境ID
     */
    private Long profileId;
    /**
     * 分组ID
     */
    private Long groupId;
    /**
     * 配置名
     */
    private String configName;

    public static ConfigListParam of() {
        return new ConfigListParam();
    }
}
