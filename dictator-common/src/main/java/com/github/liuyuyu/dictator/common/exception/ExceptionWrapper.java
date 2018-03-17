package com.github.liuyuyu.dictator.common.exception;

public class ExceptionWrapper extends RuntimeException {
    private final Exception e;

    private ExceptionWrapper(Exception e) {
        this.e = e;
    }

    public static ExceptionWrapper of(Exception e){
        return new ExceptionWrapper(e);
    }

    @Override
    public String getMessage() {
        return e.getMessage();
    }

    @Override
    public String getLocalizedMessage() {
        return e.getLocalizedMessage();
    }

    @Override
    public Throwable getCause() {
        return e.getCause();
    }

    @Override
    public Throwable initCause(Throwable cause) {
        return e.initCause(cause);
    }

    @Override
    public String toString() {
        return e.toString();
    }

    @Override
    public void printStackTrace() {
        e.printStackTrace();
    }
}
