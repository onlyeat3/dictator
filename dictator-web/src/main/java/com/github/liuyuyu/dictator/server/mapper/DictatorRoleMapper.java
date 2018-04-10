package com.github.liuyuyu.dictator.server.mapper;

import com.github.liuyuyu.dictator.server.model.entity.DictatorRole;
import com.github.liuyuyu.dictator.server.web.model.dto.DictatorRoleDto;
import com.github.liuyuyu.dictator.server.web.model.param.RoleQueryParam;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DictatorRoleMapper extends SimpleMapper<DictatorRole> {
    List<DictatorRole> findByUserId(@Param("userId") Long userId);

    Page<DictatorRoleDto> find(RoleQueryParam roleQueryParam);
}