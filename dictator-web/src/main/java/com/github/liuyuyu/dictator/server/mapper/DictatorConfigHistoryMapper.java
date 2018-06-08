package com.github.liuyuyu.dictator.server.mapper;

import com.github.liuyuyu.dictator.server.basic.mybatis.SimpleMapper;
import com.github.liuyuyu.dictator.server.model.entity.DictatorConfigHistory;
import com.github.liuyuyu.dictator.server.web.model.dto.DictatorConfigHistoryDto;
import com.github.liuyuyu.dictator.server.web.model.param.ConfigListParam;
import com.github.pagehelper.Page;
import lombok.NonNull;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
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

    Page<DictatorConfigHistoryDto> findPageValid(@Param("configListParam") ConfigListParam configListParam, @Param("roleIdList") List<Long> roleIdList, @Param("profileId") Long profileId);

    default Optional<DictatorConfigHistory> findLastByConfigId(@NonNull Long configId) {
        Weekend<DictatorConfigHistory> weekend = Weekend.of(DictatorConfigHistory.class);
        weekend.weekendCriteria()
                .andEqualTo(DictatorConfigHistory::getConfigId, configId);
        weekend.orderBy("version").desc();
        weekend.orderBy("updatedTime").desc();
        weekend.orderBy("createdTime").desc();
        return this.findFirst(weekend);
    }

    default List<DictatorConfigHistory> findByConfigId(@NonNull Long configId) {
        Weekend<DictatorConfigHistory> weekend = Weekend.of(DictatorConfigHistory.class);
        weekend.weekendCriteria()
                .andEqualTo(DictatorConfigHistory::getConfigId, configId);
        return this.findAll(weekend);
    }

    default int countByProfileId(@NonNull Long profileId) {
        Weekend<DictatorConfigHistory> weekend = Weekend.of(DictatorConfigHistory.class);
        weekend.weekendCriteria()
                .andEqualTo(DictatorConfigHistory::getProfileId, profileId);
        return this.selectCountByExample(weekend);
    }
}