package com.github.liuyuyu.dictator.server.web.model.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

/*
 * @author liuyuyu
 */
@Data
public class ConfigIdRequest {
    /**
     * 配置ID
     */
    @NotNull
    private Long configId;

    public static ConfigIdRequest of() {
        return new ConfigIdRequest();
    }
}
