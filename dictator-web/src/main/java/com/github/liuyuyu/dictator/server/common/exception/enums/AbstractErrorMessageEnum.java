package com.github.liuyuyu.dictator.server.common.exception.enums;

/**
 * @author liuyuyu
 */
public interface AbstractErrorMessageEnum {
    String getErrorMessage();
    default String getName(){
        if(this instanceof Enum){
            Enum thisEnum = (Enum) this;
            return thisEnum.name();
        }else {
            throw new IllegalArgumentException("illegal argument " + this);
        }

    }
}
