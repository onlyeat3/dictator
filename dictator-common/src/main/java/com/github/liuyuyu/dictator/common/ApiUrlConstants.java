package com.github.liuyuyu.dictator.common;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/*
 * @author liuyuyu
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ApiUrlConstants {
    private static final String CONTROLLER_URI = "/dictator/config";
    public static final String CONFIG_GET_URI = CONTROLLER_URI + "/" + "get";
    public static final String CONFIG_BATCH_GET_URI = CONTROLLER_URI + "/" + "batch/get";
}
