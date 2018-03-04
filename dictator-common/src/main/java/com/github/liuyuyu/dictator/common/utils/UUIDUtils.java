package com.github.liuyuyu.dictator.common.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * @author liuyuyu
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UUIDUtils {
    public static String next() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
