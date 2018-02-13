package com.github.liuyuyu.dictator.server.mapper;

import com.github.liuyuyu.dictator.server.model.entity.DictatorRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DictatorRoleMapper extends SimpleMapper<DictatorRole> {
    List<DictatorRole> findByUserId(@Param("userId") Long userId);
}