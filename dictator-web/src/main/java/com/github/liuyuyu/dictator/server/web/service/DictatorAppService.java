package com.github.liuyuyu.dictator.server.web.service;

import com.github.liuyuyu.dictator.common.utils.BeanConverter;
import com.github.liuyuyu.dictator.server.mapper.DictatorAppMapper;
import com.github.liuyuyu.dictator.server.model.entity.DictatorApp;
import com.github.liuyuyu.dictator.server.web.model.dto.DictatorAppDto;
import com.github.liuyuyu.dictator.server.web.model.param.AppSaveOrUpdateParam;
import com.github.liuyuyu.dictator.server.web.model.param.DeleteParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author liuyuyu
 */
@Slf4j
@Service
public class DictatorAppService {
    @Autowired private DictatorAppMapper appMapper;

    public void saveOrUpdate(AppSaveOrUpdateParam appSaveOrUpdateParam){
        DictatorApp dictatorAppEntity = BeanConverter.from(appSaveOrUpdateParam).to(DictatorApp.class);
        if (dictatorAppEntity.getId() != null) {
            this.appMapper.updateByPrimaryKeySelective(dictatorAppEntity);
        }else{
            this.appMapper.insertSelective(dictatorAppEntity);
        }
    }

    public List<DictatorAppDto> findAll(){
        return BeanConverter.from(this.appMapper.findAll())
                .toList(DictatorAppDto.class);
    }


    public void delete(DeleteParam deleteParam){
        if (deleteParam.getId() == null) {
            log.warn("No delete.{}",deleteParam);
            return;
        }
        DictatorApp dictatorAppForUpdate = BeanConverter.from(deleteParam).to(DictatorApp.class);
        dictatorAppForUpdate.setEnable(Boolean.FALSE);
        this.appMapper.updateByPrimaryKeySelective(dictatorAppForUpdate);
    }
}
