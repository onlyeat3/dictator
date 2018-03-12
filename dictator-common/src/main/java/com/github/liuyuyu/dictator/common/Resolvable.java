package com.github.liuyuyu.dictator.common;

import com.github.liuyuyu.dictator.common.utils.BeanConverter;

public interface Resolvable {
    default void from(Object source){
        BeanConverter.from(source).assign(this);
    }
}
