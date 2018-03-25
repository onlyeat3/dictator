package com.github.liuyuyu.dictator.server.mapper.weekend;

import com.github.liuyuyu.dictator.server.core.service.param.CommonParam;
import com.github.liuyuyu.dictator.server.model.entity.DictatorConfig;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import tk.mybatis.mapper.weekend.Weekend;
import tk.mybatis.mapper.weekend.WeekendCriteria;

import java.util.Date;

/**
 * @author liuyuyu
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DictatorConfigWeekend {

    public static Weekend<DictatorConfig> from(@NonNull CommonParam commonParam, @NonNull Long profileId, Date lastUpdatedTime) {
        Weekend<DictatorConfig> weekend = Weekend.of(DictatorConfig.class);
        WeekendCriteria<DictatorConfig, Object> dictatorConfigObjectWeekendCriteria = weekend.weekendCriteria();
        dictatorConfigObjectWeekendCriteria
                .andEqualTo(DictatorConfig::getAppId, commonParam.getAppId())
                .andEqualTo(DictatorConfig::getProfileId, profileId)
                .andEqualTo(DictatorConfig::getConfigName, commonParam.getKey());
        if (lastUpdatedTime != null) {
            dictatorConfigObjectWeekendCriteria.andGreaterThanOrEqualTo(DictatorConfig::getUpdatedTime, lastUpdatedTime);
        }
        return weekend;
    }

    public static Weekend<DictatorConfig> of() {
        return Weekend.of(DictatorConfig.class);
    }
}
