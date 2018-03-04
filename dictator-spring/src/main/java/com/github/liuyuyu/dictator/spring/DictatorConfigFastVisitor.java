package com.github.liuyuyu.dictator.spring;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/*
 * @author liuyuyu
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DictatorConfigFastVisitor {
    public static String get(@NonNull String name) {
        return DictatorPropertyManager.getProperty(name);
    }
}
