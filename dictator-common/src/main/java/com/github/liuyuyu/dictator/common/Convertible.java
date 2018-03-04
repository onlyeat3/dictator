package com.github.liuyuyu.dictator.common;

import com.github.liuyuyu.dictator.common.utils.BeanConverter;

/*
 * @author liuyuyu
 * @param A 自己
 * @param T 可转换的其他类型
 */
public interface Convertible<A, T> {

    default T to(Class<T> clazz) {
        return BeanConverter.from(this)
                .to(clazz);
    }

    @SuppressWarnings("unchecked")
    default A from(T t) {
        Convertible convertible = BeanConverter.from(t)
                .to(this.getClass());
        return (A) convertible;
    }
}
