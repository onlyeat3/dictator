package com.github.liuyuyu.dictator.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

/*
 * @author liuyuyu
 */
@Getter
@AllArgsConstructor
public enum ErrorCodeEnum {
    INVALID_PARAMETER("INVALID_PARAMETER", "无效参数"),
    SERVER_ERROR("SERVER_ERROR", "服务端错误");
    private String code;
    private String defaultMessage;
}
