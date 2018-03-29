package com.github.liuyuyu.dictator.server.mapper;

import com.github.liuyuyu.dictator.server.model.entity.DictatorResource;
import com.github.liuyuyu.dictator.server.web.model.dto.DictatorResourceDto;
import com.github.liuyuyu.dictator.server.web.model.param.ResourceQueryParam;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.weekend.Weekend;

import java.util.List;

@Mapper
public interface DictatorResourceMapper extends SimpleMapper<DictatorResource> {
    List<DictatorResource> findByRoleIdList(@Param("roleIdList") List<Long> roleIdList);

    default PageInfo<DictatorResourceDto> findByParam(ResourceQueryParam resourceQueryParam){
        resourceQueryParam.startPage();
        Weekend<DictatorResource> weekend = Weekend.of(DictatorResource.class);
        return this.findPage(weekend, DictatorResourceDto.class);
    }
}