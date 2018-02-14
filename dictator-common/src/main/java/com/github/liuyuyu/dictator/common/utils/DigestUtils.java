package com.github.liuyuyu.dictator.common.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author liuyuyu
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DigestUtils {

    public static String encrypt(@NonNull String plain){
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(plain.getBytes());
            byte[] digest = messageDigest.digest();
            return new BigInteger(1,digest).toString(16);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("Could not find MessageDigest with algorithm MD5", e);
        }
    }
}
