package com.github.liuyuyu.dictator.server.zookeeper;

import org.apache.commons.lang3.exception.ExceptionUtils;

/*
 * @author liuyuyu
 */
public class ZKForPathException extends RuntimeException {

    public ZKForPathException(Throwable cause) {
        super(cause);
    }

    public static ZKForPathException of(Throwable e) {
        Throwable rootCause = ExceptionUtils.getRootCause(e);
        if(rootCause != null){
            e = rootCause;
        }
        return new ZKForPathException(e);
    }

}
