package com.github.liuyuyu.dictator.common.exception;

import lombok.NonNull;

/**
 * @author liuyuyu
 */
public class NetworkGetFailException extends RuntimeException {
    public NetworkGetFailException(Throwable cause) {
        super(cause);
    }

    public NetworkGetFailException(String message) {
        super(message);
    }

    public static NetworkGetFailException from(Exception e){
        return new NetworkGetFailException(e);
    }

    public static NetworkGetFailException from(@NonNull String msg){
        return new NetworkGetFailException(msg);
    }
}
