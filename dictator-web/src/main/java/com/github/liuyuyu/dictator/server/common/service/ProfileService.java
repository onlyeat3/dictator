package com.github.liuyuyu.dictator.server.common.service;

import com.github.liuyuyu.dictator.common.utils.BeanConverter;
import com.github.liuyuyu.dictator.server.common.model.dto.ConfigProfileDto;
import com.github.liuyuyu.dictator.server.mapper.DictatorConfigProfileMapper;
import com.github.liuyuyu.dictator.server.model.entity.DictatorConfigProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*
 * @author liuyuyu
 */
@Service
public class ProfileService {
    @Autowired private DictatorConfigProfileMapper profileMapper;

    public List<ConfigProfileDto> findAll(){
        List<DictatorConfigProfile> configProfileList = this.profileMapper.selectAll();
        return BeanConverter.from(configProfileList)
                .toList(ConfigProfileDto.class);
    }
}
