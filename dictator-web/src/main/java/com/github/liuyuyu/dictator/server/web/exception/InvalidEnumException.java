package com.github.liuyuyu.dictator.server.web.exception;

public class InvalidEnumException extends RuntimeException {
    public InvalidEnumException() {
    }

    public InvalidEnumException(String message) {
        super(message);
    }

    public InvalidEnumException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidEnumException(Throwable cause) {
        super(cause);
    }
}
