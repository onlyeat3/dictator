package com.github.liuyuyu.dictator.server.web.model.param;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

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
     * APP ID
     */
    @NotBlank private String appId;

    /**
     * 所有者邮箱
     */
    @NotBlank private String ownerEmail;
}
