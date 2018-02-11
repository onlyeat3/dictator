package com.github.liuyuyu.dictator.server.mapper;

import com.github.liuyuyu.dictator.server.model.entity.DictatorConfigHistory;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.weekend.Weekend;

import java.time.LocalDateTime;
import java.util.List;

@org.apache.ibatis.annotations.Mapper
public interface DictatorConfigHistoryMapper extends Mapper<DictatorConfigHistory> {

    default List<DictatorConfigHistory> findLastHour() {
        Weekend<DictatorConfigHistory> weekend = Weekend.of(DictatorConfigHistory.class);
        weekend.weekendCriteria()
                .andGreaterThanOrEqualTo(DictatorConfigHistory::getHistoryCreatedTime, LocalDateTime.now().minusHours(1));
        return this.selectByExample(weekend);
    }
}