package com.github.liuyuyu.dictator.common;

/*
 * @author liuyuyu
 */
public interface Convertible<T> {

    T to(Class<T> clazz);
}
