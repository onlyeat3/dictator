package com.github.liuyuyu.dictator.server.mapper;

import com.github.liuyuyu.dictator.server.model.entity.DictatorRoleProfile;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface DictatorRoleProfileMapper extends SimpleMapper<DictatorRoleProfile> {
    int deleteByRoleId(@Param("roleId") Long roleId);
}