package com.github.liuyuyu.dictator.server.web.model.param;

import com.github.liuyuyu.dictator.common.Convertible;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class ConfigSaveUpdateParam implements Convertible, OperatorParam {
    private Long id;

    /**
     * 应用（服务）ID
     */
    @NotBlank
    private String appCode;

    /**
     * 配置名
     */
    @NotBlank
    private String configName;

    /**
     * 配置值
     */
    @NotBlank
    private String configValue;

    /**
     * 备注
     */
    private String remark;

    /**
     * 操作者ID
     */
    private Long operatorId;

    /**
     * 操作者IP
     */
    private String operatorIp;

    /**
     * profile_id
     */
    @NotNull
    private Long profileId;

    /**
     * 分组ID
     */
    private Long groupId;
    /**
     * 角色ID
     */
    private List<Long> roleIdList;
}