package com.github.liuyuyu.dictator.server.common.model.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

/*
 * @author liuyuyu
 */
@Data
public class ConfigIdRequest {
    public static ConfigIdRequest of() {
        return new ConfigIdRequest();
    }

    /**
     * 配置ID
     */
    @NotNull private Long configId;
}
