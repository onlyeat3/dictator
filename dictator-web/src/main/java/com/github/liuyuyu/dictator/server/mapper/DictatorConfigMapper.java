package com.github.liuyuyu.dictator.server.mapper;

import com.github.liuyuyu.dictator.server.common.model.dto.DictatorConfigDto;
import com.github.liuyuyu.dictator.server.common.model.param.ConfigListParam;
import com.github.liuyuyu.dictator.server.core.service.param.CommonParam;
import com.github.liuyuyu.dictator.server.core.service.param.ConfigGetParam;
import com.github.liuyuyu.dictator.server.mapper.weekend.DictatorConfigWeekend;
import com.github.liuyuyu.dictator.server.model.entity.DictatorConfig;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import lombok.NonNull;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DictatorConfigMapper extends SimpleMapper<DictatorConfig> {
    default DictatorConfig findByGetParam(@NonNull ConfigGetParam configGetParam,@NonNull Long profileId) {
        return this.selectOneByExample(DictatorConfigWeekend.from(configGetParam,profileId));
    }

    default int countByParam(@NonNull CommonParam commonParam,@NonNull Long profileId) {
        return this.selectCountByExample(DictatorConfigWeekend.from(commonParam,profileId));
    }

    Page<DictatorConfigDto> findPageValid(@NonNull ConfigListParam configListParam);

    default List<DictatorConfig> findAllByGetParam(@NonNull ConfigGetParam configGetParam, @NonNull Long profileId) {
        return this.selectByExample(DictatorConfigWeekend.from(configGetParam,profileId));
    }
}