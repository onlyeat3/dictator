package com.github.liuyuyu.dictator.server.mapper;

import com.github.liuyuyu.dictator.server.model.entity.DictatorUser;
import lombok.NonNull;
import org.apache.ibatis.annotations.Mapper;
import tk.mybatis.mapper.weekend.Weekend;

import java.util.Optional;

@Mapper
public interface DictatorUserMapper extends SimpleMapper<DictatorUser> {
    default Optional<DictatorUser> findByUsername(@NonNull String username) {
        Weekend<DictatorUser> weekend = Weekend.of(DictatorUser.class);
        weekend.weekendCriteria()
                .andEqualTo(DictatorUser::getUserName, username);
        return this.findOne(weekend);
    }
}