package com.github.liuyuyu.dictator.server.common.exception;

import com.github.liuyuyu.dictator.server.common.exception.enums.AbstractErrorMessageEnum;
import lombok.Getter;

/**
 * @author liuyuyu
 */
public class ServiceException extends RuntimeException {
    @Getter
    private String code;

    private ServiceException(String code, String message){
        super(message);
        this.code = code;
    }

    public static ServiceException from(AbstractErrorMessageEnum errorMessageEnum){
        return new ServiceException(errorMessageEnum.getName(),errorMessageEnum.getErrorMessage());
    }
}
