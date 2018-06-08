package com.github.liuyuyu.dictator.server.mapper;

import com.github.liuyuyu.dictator.server.basic.mybatis.SimpleMapper;
import com.github.liuyuyu.dictator.server.model.entity.DictatorRoleResource;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface DictatorRoleResourceMapper extends SimpleMapper<DictatorRoleResource> {
    int deleteByResourceId(@Param("resourceId") Long resourceId);

    void deleteByRoleId(@Param("roleId") Long roleId);
}