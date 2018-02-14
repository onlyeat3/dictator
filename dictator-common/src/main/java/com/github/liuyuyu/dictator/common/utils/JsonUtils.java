package com.github.liuyuyu.dictator.common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.github.liuyuyu.dictator.common.jackson.NamedValueSerializer;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*
 * @author liuyuyu
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JsonUtils {
    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    static {
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(new NamedValueSerializer());
        OBJECT_MAPPER.registerModule(simpleModule);
    }

    public static String toJson(Object object) {
        if (object == null) {
            return StringUtils.EMPTY;
        }
        try {
            return OBJECT_MAPPER.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            return StringUtils.EMPTY;
        }
    }

    public static <T> T toObject(@NonNull String jsonString, Class<T> targetClass) {
        try {
            return OBJECT_MAPPER.readValue(jsonString, targetClass);
        } catch (IOException e) {
            return null;
        }
    }

    public static <T> List<T> toList(@NonNull String jsonString, Class<?> targetClass) {
        JavaType listJavaType = OBJECT_MAPPER.getTypeFactory().constructParametricType(ArrayList.class, targetClass);
        try {
            return OBJECT_MAPPER.readValue(jsonString, listJavaType);
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }
}
