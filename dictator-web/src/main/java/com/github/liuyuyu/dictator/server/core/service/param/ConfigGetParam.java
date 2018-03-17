package com.github.liuyuyu.dictator.server.core.service.param;

import com.github.liuyuyu.dictator.common.BaseProperties;
import com.github.liuyuyu.dictator.common.model.request.PropertyGetRequest;
import com.github.liuyuyu.dictator.common.utils.BeanConverter;
import lombok.*;

/*
 * @author liuyuyu
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ConfigGetParam extends CommonParam {
    /**
     * 配置值
     */
    private String defaultValue;

    public static ConfigGetParam from(CommonParam commonParam) {
        return BeanConverter.from(commonParam)
                .to(ConfigGetParam.class);
    }

    public static ConfigGetParam from(PropertyGetRequest propertyGetRequest) {
        return BeanConverter.from(propertyGetRequest)
                .to(ConfigGetParam.class);
    }

    public static ConfigGetParam from(@NonNull BaseProperties source) {
        return BeanConverter.from(source)
                .to(ConfigGetParam.class);
    }
}
