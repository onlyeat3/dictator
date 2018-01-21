package com.github.liuyuyu.dictator.client.http;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import okhttp3.MediaType;

/*
 * @author liuyuyu
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MediaTypeConstants {
    public static final MediaType APPLICATION_JSON_UTF8 = MediaType.parse("application/json;charset=UTF-8");
}
