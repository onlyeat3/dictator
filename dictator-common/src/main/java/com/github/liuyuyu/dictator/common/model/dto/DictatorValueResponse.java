package com.github.liuyuyu.dictator.common.model.dto;

import lombok.Data;

/*
 * @author liuyuyu
 */
@Data
public class DictatorValueResponse {
    /**
     * 配置的具体值
     */
    private String value;
    /**
     * 配置的版本
     */
    private String version;

    public static DictatorValueResponse of() {
        return new DictatorValueResponse();
    }
}
