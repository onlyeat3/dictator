package com.github.liuyuyu.dictator.common;

import com.github.liuyuyu.dictator.common.utils.BeanConverter;

/*
 * @author liuyuyu
 * @param T 可转换的其他类型
 */
public interface Convertible {

    default <T> T to(Class<T> clazz) {
        return BeanConverter.from(this)
                .to(clazz);
    }
}
