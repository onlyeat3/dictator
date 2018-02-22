package com.github.liuyuyu.dictator.server.mapper;

import com.github.liuyuyu.dictator.server.model.entity.DictatorConfigGroup;
import com.google.common.collect.Maps;
import lombok.NonNull;
import org.apache.ibatis.annotations.Mapper;
import tk.mybatis.mapper.weekend.Weekend;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Mapper
public interface DictatorConfigGroupMapper extends SimpleMapper<DictatorConfigGroup> {
    default Map<Long,String> findConfigNameMapByGroupIdList(@NonNull List<Long> configIdList){
        if(configIdList.isEmpty()){
            return Maps.newHashMap();
        }
        Weekend<DictatorConfigGroup> weekend = Weekend.of(DictatorConfigGroup.class);
        weekend.weekendCriteria()
                .andIn(DictatorConfigGroup::getId,configIdList);
        return this.findAll(weekend).stream()
                .collect(Collectors.toMap(DictatorConfigGroup::getId, DictatorConfigGroup::getGroupName));
    }
}