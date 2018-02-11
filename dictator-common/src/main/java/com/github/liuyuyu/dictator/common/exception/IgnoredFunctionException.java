package com.github.liuyuyu.dictator.common.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

/**
 * 未实现的功能
 *
 * @author liuyuyu
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class IgnoredFunctionException extends RuntimeException {
    /**
     * 功能名
     */
    private String functionName;

    public static IgnoredFunctionException of(@NonNull String functionName) {
        IgnoredFunctionException ignoredFunctionException = new IgnoredFunctionException();
        ignoredFunctionException.setFunctionName(functionName);
        return ignoredFunctionException;
    }
}
