package com.github.liuyuyu.dictator.server.mapper;

import com.github.liuyuyu.dictator.server.mapper.weekend.DictatorConfigWeekend;
import com.github.liuyuyu.dictator.server.model.entity.DictatorConfig;
import com.github.liuyuyu.dictator.server.service.param.CommonParam;
import com.github.liuyuyu.dictator.server.service.param.ConfigGetParam;
import lombok.NonNull;
import tk.mybatis.mapper.common.Mapper;

@org.apache.ibatis.annotations.Mapper
public interface DictatorConfigMapper extends Mapper<DictatorConfig> {
    default DictatorConfig findByGetParam(@NonNull ConfigGetParam configGetParam) {
        return this.selectOneByExample(DictatorConfigWeekend.from(configGetParam));
    }

    default int countByParam(@NonNull CommonParam commonParam) {
        return this.selectCountByExample(DictatorConfigWeekend.from(commonParam));
    }
}