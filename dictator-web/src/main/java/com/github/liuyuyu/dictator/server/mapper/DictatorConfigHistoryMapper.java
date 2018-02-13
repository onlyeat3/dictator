package com.github.liuyuyu.dictator.server.mapper;

import com.github.liuyuyu.dictator.server.model.entity.DictatorConfigHistory;
import org.apache.ibatis.annotations.Mapper;
import tk.mybatis.mapper.weekend.Weekend;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface DictatorConfigHistoryMapper extends SimpleMapper<DictatorConfigHistory> {
    default List<DictatorConfigHistory> findLastHour() {
        Weekend<DictatorConfigHistory> weekend = Weekend.of(DictatorConfigHistory.class);
        weekend.weekendCriteria()
                .andGreaterThanOrEqualTo(DictatorConfigHistory::getHistoryCreatedTime, LocalDateTime.now().minusHours(1));
        return this.selectByExample(weekend);
    }
}