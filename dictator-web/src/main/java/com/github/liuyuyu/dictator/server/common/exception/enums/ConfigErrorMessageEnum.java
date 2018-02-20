package com.github.liuyuyu.dictator.server.common.exception.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author liuyuyu
 */
@Getter
@AllArgsConstructor
public enum ConfigErrorMessageEnum implements AbstractErrorMessageEnum {
    PROFILE_NOT_EXISTS("profile不存在"),
    CONFIG_NOT_EXISTS("配置不存在或已被删除"),
    CONFIG_HISTORY_NOT_EXISTS("配置历史不存在");
    /**
     * 默认信息
     */
    private String errorMessage;
}
