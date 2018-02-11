package com.github.liuyuyu.dictator.server.utils;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

/**
 * @author liuyuyu
 */
@Slf4j
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BeanConverter {
    private Object source;

    public static BeanConverter from(@NonNull Object source) {
        BeanConverter beanConverter = new BeanConverter();
        beanConverter.setSource(source);
        return beanConverter;
    }

    public <T> T to(@NonNull Class<T> targetClass) {
        try {
            T t = targetClass.newInstance();
            BeanUtils.copyProperties(this.source, t);
            return t;
        } catch (InstantiationException | IllegalAccessException e) {
            log.error("new instance fail", e);
        }
        return null;
    }
}
