package com.github.liuyuyu.dictator.server.mapper;

import com.github.liuyuyu.dictator.server.basic.mybatis.SimpleMapper;
import com.github.liuyuyu.dictator.server.model.entity.DictatorRole;
import com.github.liuyuyu.dictator.server.web.model.dto.DictatorRoleDto;
import com.github.liuyuyu.dictator.server.web.model.dto.DictatorUserRoleDto;
import com.github.liuyuyu.dictator.server.web.model.param.RoleQueryParam;
import com.github.pagehelper.Page;
import lombok.NonNull;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DictatorRoleMapper extends SimpleMapper<DictatorRole> {
    List<DictatorRole> findByUserId(@Param("userId") Long userId);

    Page<DictatorRoleDto> find(RoleQueryParam roleQueryParam);

    List<DictatorUserRoleDto> findByUserIdList(@NonNull @Param("userIdList") List<Long> userIdList);
}