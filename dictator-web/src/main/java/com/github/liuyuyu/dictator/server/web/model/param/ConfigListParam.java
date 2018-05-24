package com.github.liuyuyu.dictator.server.web.model.param;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

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
     * 环境ID(搜索)
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
    /**
     * 角色ID
     */
    private List<Long> roleIdList;
    /**
     * 是否GM账号
     */
    private Boolean isGM;

    public static ConfigListParam of() {
        return new ConfigListParam();
    }
}
