package com.github.liuyuyu.dictator.server.common.model.param;

import com.github.liuyuyu.dictator.common.Convertible;
import com.github.liuyuyu.dictator.common.Resolvable;
import com.github.liuyuyu.dictator.server.common.model.request.ConfigProfileRequest;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

@Data
public class ConfigProfileParam implements Resolvable,Convertible {
    /**
     * 自增主键
     */
    private Long id;

    /**
     * 环境名
     */
    @NotBlank private String profileName;

    /**
     * 环境代码
     */
    @NotBlank private String profileCode;

    /**
     * 环境描述
     */
    @NotBlank private String profileDesc;

    /**
     * 操作者ID
     */
    private Long operatorId;

    /**
     * 操作者IP
     */
    private String operatorIp;
    /**
     * 是否启用
     */
    private Boolean enable;
}
