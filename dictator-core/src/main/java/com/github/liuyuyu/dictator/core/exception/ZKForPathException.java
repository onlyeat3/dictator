package com.github.liuyuyu.dictator.core.exception;

/*
 * @author liuyuyu
 */
public class ZKForPathException extends RuntimeException {

    public ZKForPathException(Throwable cause) {
        super(cause);
    }

    public static ZKForPathException of(Throwable e) {
        return new ZKForPathException(e);
    }
}
