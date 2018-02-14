package com.github.liuyuyu.dictator.common;

import com.github.liuyuyu.dictator.common.utils.BeanConverter;

/*
 * @author liuyuyu
 */
public interface Convertible<T> {

    default T to(Class<T> clazz){
        return BeanConverter.from(this)
                .to(clazz);
    }

    @SuppressWarnings("unchecked")
    default <R> R from(T t){
        Convertible convertible = BeanConverter.from(t)
                .to(this.getClass());
        return (R) convertible;
    }
}
