package com.github.liuyuyu.dictator.server.mapper;

import com.github.liuyuyu.dictator.server.model.entity.DictatorUserRole;
import org.apache.ibatis.annotations.Param;

public interface DictatorUserRoleMapper extends SimpleMapper<DictatorUserRole> {
    void deleteByRoleId(@Param("roleId") Long roleId);
}