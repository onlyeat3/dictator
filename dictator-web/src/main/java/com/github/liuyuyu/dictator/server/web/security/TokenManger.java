package com.github.liuyuyu.dictator.server.web.security;

import com.github.liuyuyu.dictator.server.web.model.dto.DictatorUserDto;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.apache.commons.lang3.StringUtils;

import java.util.concurrent.TimeUnit;

/**
 * @author liuyuyu
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TokenManger {
    private static final Cache<String, DictatorUserDto> DICTATOR_USER_DTO_MAP = CacheBuilder.newBuilder()
            .initialCapacity(10)
            .concurrencyLevel(20)
            .expireAfterAccess(30, TimeUnit.MINUTES)
            .build();

    public static DictatorUserDto get(@NonNull String token) {
        return DICTATOR_USER_DTO_MAP.getIfPresent(token);
    }

    public static void remove(@NonNull DictatorUserDto dictatorUserDto) {
        DICTATOR_USER_DTO_MAP.invalidate(dictatorUserDto);
    }

    public static void put(@NonNull DictatorUserDto dictatorUserDto) {
        String token = dictatorUserDto.getToken();
        if (StringUtils.isEmpty(token)) {
            throw new IllegalArgumentException("invalid token " + token);
        }
        DICTATOR_USER_DTO_MAP.put(token, dictatorUserDto);
    }
}
