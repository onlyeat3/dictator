package com.github.liuyuyu.dictator.server.mapper;

import com.github.liuyuyu.dictator.server.basic.mybatis.SimpleMapper;
import com.github.liuyuyu.dictator.server.model.entity.DictatorUserRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface DictatorUserRoleMapper extends SimpleMapper<DictatorUserRole> {
    void deleteByRoleId(@Param("roleId") Long roleId);

    void deleteByUserId(@Param("userId") Long userId);
}