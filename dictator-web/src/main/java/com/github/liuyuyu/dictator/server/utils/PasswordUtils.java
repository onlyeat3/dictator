package com.github.liuyuyu.dictator.server.utils;

import com.github.liuyuyu.dictator.common.utils.DigestUtils;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/**
 * @author liuyuyu
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PasswordUtils {

    public static String encrypt(@NonNull String plainPassword) {
        return DigestUtils.encrypt(DigestUtils.encrypt(plainPassword + "dictator"));
    }

    public static void main(String[] args) {
        System.out.println(PasswordUtils.encrypt("123456"));
    }
}
