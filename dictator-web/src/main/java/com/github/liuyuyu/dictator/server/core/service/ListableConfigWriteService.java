package com.github.liuyuyu.dictator.server.core.service;

import com.github.liuyuyu.dictator.server.core.service.param.CommonParam;
import com.github.liuyuyu.dictator.server.core.service.param.ConfigSetParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author liuyuyu
 */
@Slf4j
@Component
public class ListableConfigWriteService implements ConfigWriteService {
    @Autowired
    private ConfigWriteService configWriteService;

    @Override
    public void save(ConfigSetParam configSetParam) {
        this.configWriteService.save(configSetParam);
    }

    @Override
    public void saveOrModify(ConfigSetParam configSetParam) {
        this.configWriteService.saveOrModify(configSetParam);
    }

    @Override
    public void saveIfNotExists(ConfigSetParam configSetParam) {
        this.configWriteService.saveIfNotExists(configSetParam);
    }

    @Override
    public boolean delete(CommonParam commonParam) {
        return this.configWriteService.delete(commonParam);
    }
}
