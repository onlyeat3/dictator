package com.github.liuyuyu.dictator.server.mapper;

import com.github.liuyuyu.dictator.server.basic.mybatis.SimpleMapper;
import com.github.liuyuyu.dictator.server.model.entity.DictatorApp;
import tk.mybatis.mapper.weekend.Weekend;

import java.util.List;

public interface DictatorAppMapper extends SimpleMapper<DictatorApp> {
    default List<DictatorApp> findAllEnabled() {
        Weekend<DictatorApp> weekend = Weekend.of(DictatorApp.class);
        weekend.weekendCriteria()
                .andEqualTo(DictatorApp::getEnable,Boolean.TRUE);
        return this.findAll(weekend);
    }
}