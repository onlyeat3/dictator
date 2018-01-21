package com.github.liuyuyu.dictator.server.param;

import com.github.liuyuyu.dictator.common.Convertible;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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
    private String value;

    @Override
    public CommonParam to(Class<CommonParam> clazz) {
        CommonParam commonParam = new CommonParam();
        commonParam.setKey(this.getKey());
        commonParam.setAppId(this.getAppId());
        return commonParam;
    }
}
