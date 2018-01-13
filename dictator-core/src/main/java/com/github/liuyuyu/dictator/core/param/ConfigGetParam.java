package com.github.liuyuyu.dictator.core.param;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/*
 * @author liuyuyu
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ConfigGetParam extends CommonParam{
    /**
     * 配置值
     */
    @NonNull private String defaultValue;

    public static ConfigGetParam from(CommonParam commonParam){
        ConfigGetParam configGetParam = new ConfigGetParam();
        configGetParam.setKey(commonParam.getKey());
        configGetParam.setPath(commonParam.getPath());
        return configGetParam;
    }
}
