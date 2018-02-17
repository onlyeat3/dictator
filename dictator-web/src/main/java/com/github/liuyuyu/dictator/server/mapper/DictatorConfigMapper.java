package com.github.liuyuyu.dictator.server.mapper;

import com.github.liuyuyu.dictator.server.common.model.param.ConfigListParam;
import com.github.liuyuyu.dictator.server.core.service.param.CommonParam;
import com.github.liuyuyu.dictator.server.core.service.param.ConfigGetParam;
import com.github.liuyuyu.dictator.server.mapper.weekend.DictatorConfigWeekend;
import com.github.liuyuyu.dictator.server.model.entity.DictatorConfig;
import com.github.pagehelper.PageInfo;
import lombok.NonNull;
import org.apache.ibatis.annotations.Mapper;
import tk.mybatis.mapper.weekend.Weekend;

import java.util.List;

@Mapper
public interface DictatorConfigMapper extends SimpleMapper<DictatorConfig> {
    default DictatorConfig findByGetParam(@NonNull ConfigGetParam configGetParam) {
        return this.selectOneByExample(DictatorConfigWeekend.from(configGetParam));
    }

    default int countByParam(@NonNull CommonParam commonParam) {
        return this.selectCountByExample(DictatorConfigWeekend.from(commonParam));
    }

    default <C> PageInfo<C> findPageValid(@NonNull ConfigListParam configListParam,Class<C> targetClass){
        configListParam.startPage();
        Weekend<DictatorConfig> weekend = DictatorConfigWeekend.of();
        return this.findPage(weekend,targetClass);
    }
}