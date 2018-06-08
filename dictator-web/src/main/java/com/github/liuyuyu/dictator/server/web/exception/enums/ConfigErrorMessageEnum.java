package com.github.liuyuyu.dictator.server.web.exception.enums;

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
    NO_CONFIG_PERMISSION("无权访问配置"),
    NO_PROFILE_PERMISSION("无权访问环境"),
    APP_NOT_EXISTS("不存在的APP"),
    CONFIG_HISTORY_NOT_EXISTS("配置历史不存在");
    /**
     * 默认信息
     */
    private String errorMessage;
}
