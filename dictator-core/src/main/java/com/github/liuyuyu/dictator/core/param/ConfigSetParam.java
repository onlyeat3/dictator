package com.github.liuyuyu.dictator.core.param;

import com.github.liuyuyu.dictator.common.Convertible;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/*
 * @author liuyuyu
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class ConfigSetParam extends CommonParam implements Convertible<CommonParam> {
    /**
     * 配置值
     */
    @NonNull private String value;

    @Override
    public CommonParam to(Class<CommonParam> clazz) {
        CommonParam commonParam = new CommonParam();
        commonParam.setKey(this.getKey());
        commonParam.setPath(this.getPath());
        return commonParam;
    }
}
