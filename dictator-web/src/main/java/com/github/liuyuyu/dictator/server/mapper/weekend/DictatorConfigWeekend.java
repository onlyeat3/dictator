package com.github.liuyuyu.dictator.server.mapper.weekend;

import com.github.liuyuyu.dictator.server.core.service.param.CommonParam;
import com.github.liuyuyu.dictator.server.model.entity.DictatorConfig;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import tk.mybatis.mapper.weekend.Weekend;

/**
 * @author liuyuyu
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DictatorConfigWeekend {

    public static Weekend<DictatorConfig> from(@NonNull CommonParam commonParam) {
        Weekend<DictatorConfig> weekend = Weekend.of(DictatorConfig.class);
        weekend.weekendCriteria()
                .andEqualTo(DictatorConfig::getAppId, commonParam.getAppId())
                .andEqualTo(DictatorConfig::getDeploymentId, commonParam.getDeploymentId())
                .andEqualTo(DictatorConfig::getProfile, commonParam.getProfile())
                .andEqualTo(DictatorConfig::getConfigName, commonParam.getKey());
        return weekend;
    }

    public static Weekend<DictatorConfig> of() {
        return Weekend.of(DictatorConfig.class);
    }
}
