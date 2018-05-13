package com.github.liuyuyu.dictator.server.web.exception.enums;

import com.github.liuyuyu.dictator.server.web.exception.ServiceException;

/**
 * @author liuyuyu
 */
public interface AbstractErrorMessageEnum {
    String getErrorMessage();

    default String getName() {
        if (this instanceof Enum) {
            Enum thisEnum = (Enum) this;
            return thisEnum.name();
        } else {
            throw new IllegalArgumentException("illegal argument " + this);
        }
    }

    default ServiceException serviceException() {
        return ServiceException.from(this);
    }
}
