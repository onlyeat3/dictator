package com.github.liuyuyu.dictator.server.service.dto;

import lombok.Data;

/*
 * @author liuyuyu
 */
@Data
public class ReturnValueDto {
    /**
     * 配置的具体值
     */
    private String value;
    /**
     * 配置的版本
     */
    private String version;

    public static ReturnValueDto of() {
        return new ReturnValueDto();
    }
}
