package com.github.liuyuyu.dictator.common.utils;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
            T t = targetClass.getConstructor().newInstance();
            BeanUtils.copyProperties(t, this.source);
            return t;
        } catch (IllegalAccessException | InvocationTargetException | InstantiationException | NoSuchMethodException e) {
            log.error("new instance fail", e);
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public <T> List<T> toList(@NonNull Class<T> targetClass) {
        return (List<T>) ((List) (this.source)).stream()
                .map(e -> {
                    try {
                        T t = targetClass.newInstance();
                        BeanUtils.copyProperties(t, e);
                        return t;
                    } catch (IllegalAccessException | InvocationTargetException | InstantiationException ex) {
                        log.error("new instance fail", ex);
                    }
                    return null;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}
