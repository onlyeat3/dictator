package com.github.liuyuyu.dictator.server.web.model.param;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * @author liuyuyu
 */
@Data
public class AppSaveOrUpdateParam extends AbstractOperatorParam {
    /**
     * 自增主键
     */
    private Long id;

    /**
     * app名称
     */
    @NotBlank private String appName;
    /**
     * App代码
     */
    @NotBlank private String appCode;

    /**
     * 所有者邮箱
     */
    @NotBlank private String ownerEmail;
    /**
     * 是否启用
     */
    private Boolean enable;
}
