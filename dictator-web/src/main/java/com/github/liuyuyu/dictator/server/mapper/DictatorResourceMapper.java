package com.github.liuyuyu.dictator.server.mapper;

import com.github.liuyuyu.dictator.server.model.entity.DictatorResource;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DictatorResourceMapper extends SimpleMapper<DictatorResource> {
    List<DictatorResource> findByRoleIdList(@Param("roleIdList") List<Long> roleIdList);
}