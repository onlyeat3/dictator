package com.github.liuyuyu.dictator.server.common.service;

import com.github.liuyuyu.dictator.common.utils.BeanConverter;
import com.github.liuyuyu.dictator.server.common.model.dto.ConfigGroupDto;
import com.github.liuyuyu.dictator.server.mapper.DictatorConfigGroupMapper;
import com.github.liuyuyu.dictator.server.model.entity.DictatorConfigGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*
 * @author liuyuyu
 */
@Service
public class GroupService {

    @Autowired private DictatorConfigGroupMapper groupMapper;

    public List<ConfigGroupDto> findAll(){
        List<DictatorConfigGroup> dictatorConfigGroupList = this.groupMapper.findAll();
        return BeanConverter.from(dictatorConfigGroupList)
                .toList(ConfigGroupDto.class);
    }
}
