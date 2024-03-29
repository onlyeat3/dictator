package com.github.liuyuyu.dictator.server.mapper;

import com.github.liuyuyu.dictator.server.basic.mybatis.SimpleMapper;
import com.github.liuyuyu.dictator.server.mapper.weekend.DictatorConfigWeekend;
import com.github.liuyuyu.dictator.server.model.entity.DictatorConfig;
import com.github.liuyuyu.dictator.server.web.model.dto.DictatorConfigDto;
import com.github.liuyuyu.dictator.server.web.model.param.ConfigListParam;
import com.github.liuyuyu.dictator.service.param.CommonParam;
import com.github.liuyuyu.dictator.service.param.ConfigGetParam;
import com.github.pagehelper.Page;
import lombok.NonNull;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;import tk.mybatis.mapper.weekend.Weekend;

import java.util.Date;
import java.util.List;

@Mapper
public interface DictatorConfigMapper extends SimpleMapper<DictatorConfig> {
    default DictatorConfig findByGetParam(@NonNull ConfigGetParam configGetParam, @NonNull Long profileId) {
        return this.selectOneByExample(DictatorConfigWeekend.from(configGetParam, profileId, null));
    }

    default int countByParam(@NonNull CommonParam commonParam, @NonNull Long profileId) {
        return this.selectCountByExample(DictatorConfigWeekend.from(commonParam, profileId, null));
    }

    Page<DictatorConfigDto> findPageValid(@Param("configListParam") ConfigListParam configListParam, @Param("profileId")Long profileId, @Param("roleIdList") List<Long> roleIdList);

    default List<DictatorConfig> findAllByGetParam(@NonNull ConfigGetParam configGetParam, @NonNull Long profileId, Date lastUpdatedTime) {
        return this.selectByExample(DictatorConfigWeekend.from(configGetParam, profileId, lastUpdatedTime));
    }

    default List<DictatorConfig> findByProfileId(@NonNull Long profileId){
        Weekend<DictatorConfig> weekend = DictatorConfigWeekend.of();
        weekend.weekendCriteria()
                .andEqualTo(DictatorConfig::getProfileId,profileId);
        return this.findAll(weekend);
    }

    default void deleteByIdList(List<Long> idList){
            Weekend<DictatorConfig> weekend = Weekend.of(DictatorConfig.class);
            weekend.weekendCriteria()
                    .andIn(DictatorConfig::getId,idList);
            this.deleteByExample(weekend);
    }
}