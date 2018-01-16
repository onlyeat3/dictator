package com.github.liuyuyu.dictator.common.exception;

import lombok.*;

/*
 * 缺少配置属性
 * @author liuyuyu
 */
@Getter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PropertyMissException extends RuntimeException {
    /**
     * 缺少的配置属性名
     */
    @Setter(AccessLevel.PRIVATE)
    private String propertyName;

    public static PropertyMissException of(@NonNull String propertyName) {
        PropertyMissException propertyMissException = new PropertyMissException();
        propertyMissException.setPropertyName(propertyName);
        return propertyMissException;
    }

    @Override
    public String getMessage() {
        return String.format("Miss %s", this.propertyName);
    }
}
