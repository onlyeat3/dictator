package com.github.liuyuyu.dictator.server.web.model.type;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

import java.util.Arrays;
import java.util.Optional;

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

    public static Optional<ResourceTypeEnum> valueOf(@NonNull Integer value) {
        return Arrays.stream(ResourceTypeEnum.values())
                .filter(e -> e.value == value)
                .findFirst();
    }
}
