package com.github.liuyuyu.dictator.server.web.model.type;

import com.github.liuyuyu.dictator.server.web.exception.InvalidEnumException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * @author liuyuyu
 */
@Getter
@AllArgsConstructor
public enum ResourceTypeEnum {
    MENU(1,"菜单"),
    BUTTON(2,"按钮");

    private int value;
    private String name;

    public static ResourceTypeEnum valueOf(int value) {
        return Arrays.stream(ResourceTypeEnum.values())
                .filter(e -> e.getValue() == value)
                .findFirst()
                .orElseThrow(() -> new InvalidEnumException(String.valueOf(value)));
    }
}
