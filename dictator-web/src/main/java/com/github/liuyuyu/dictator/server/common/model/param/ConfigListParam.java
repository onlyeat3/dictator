package com.github.liuyuyu.dictator.server.common.model.param;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/*
 * @author liuyuyu
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
public class ConfigListParam extends PageParam {
    public static ConfigListParam of() {
        return new ConfigListParam();
    }
}
