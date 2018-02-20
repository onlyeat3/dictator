package com.github.liuyuyu.dictator.server.mapper;

import com.github.liuyuyu.dictator.server.common.model.dto.DictatorConfigHistoryDto;
import com.github.liuyuyu.dictator.server.common.model.param.ConfigListParam;
import com.github.liuyuyu.dictator.server.model.entity.DictatorConfigHistory;
import com.github.pagehelper.PageInfo;
import lombok.NonNull;
import org.apache.ibatis.annotations.Mapper;
import tk.mybatis.mapper.weekend.Weekend;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Mapper
public interface DictatorConfigHistoryMapper extends SimpleMapper<DictatorConfigHistory> {
    default List<DictatorConfigHistory> findLastHour() {
        Weekend<DictatorConfigHistory> weekend = Weekend.of(DictatorConfigHistory.class);
        weekend.weekendCriteria()
                .andGreaterThanOrEqualTo(DictatorConfigHistory::getHistoryCreatedTime, LocalDateTime.now().minusHours(1));
        return this.selectByExample(weekend);
    }

    default PageInfo<DictatorConfigHistoryDto> findPageValid(ConfigListParam configListParam){
        configListParam.startPage();
        Weekend<DictatorConfigHistory> weekend = Weekend.of(DictatorConfigHistory.class);
        return this.findPage(weekend, DictatorConfigHistoryDto.class);
    }

    default Optional<DictatorConfigHistory> findLastByConfigId(@NonNull Long configId){
        Weekend<DictatorConfigHistory> weekend = Weekend.of(DictatorConfigHistory.class);
        weekend.weekendCriteria()
                .andEqualTo(DictatorConfigHistory::getConfigId,configId);
        weekend.orderBy("version").desc();
        weekend.orderBy("updatedTime").desc();
        weekend.orderBy("createdTime").desc();
        return this.findFirst(weekend);
    }

    default List<DictatorConfigHistory> findByConfigId(@NonNull Long configId){
        Weekend<DictatorConfigHistory> weekend = Weekend.of(DictatorConfigHistory.class);
        weekend.weekendCriteria()
                .andEqualTo(DictatorConfigHistory::getConfigId,configId);
        return this.findAll(weekend);
    }
}