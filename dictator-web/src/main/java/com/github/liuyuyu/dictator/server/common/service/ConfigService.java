package com.github.liuyuyu.dictator.server.common.service;

import com.github.liuyuyu.dictator.server.mapper.DictatorConfigMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
 * @author liuyuyu
 */
@Service
public class ConfigService {
    @Autowired private DictatorConfigMapper configMapper;
}
