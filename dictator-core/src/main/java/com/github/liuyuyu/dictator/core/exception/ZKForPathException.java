package com.github.liuyuyu.dictator.core.exception;

import org.apache.commons.lang3.exception.ExceptionUtils;

/*
 * @author liuyuyu
 */
public class ZKForPathException extends RuntimeException {

    public ZKForPathException(Throwable cause) {
        super(cause);
    }

    public static ZKForPathException of(Throwable e) {
        return new ZKForPathException(ExceptionUtils.getRootCause(e));
    }

}
