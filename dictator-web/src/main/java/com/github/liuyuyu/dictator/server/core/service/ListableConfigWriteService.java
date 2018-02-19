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
public class ListableConfigWriteService {
    @Autowired(required = false)
    private ConfigWriteService configWriteService;

    public void save(ConfigSetParam configSetParam) {
        if(this.configWriteService == null){
            return;
        }
        this.configWriteService.save(configSetParam);
    }

    public void saveOrModify(ConfigSetParam configSetParam) {
        if(this.configWriteService == null){
            return;
        }
        this.configWriteService.saveOrModify(configSetParam);
    }

    public void saveIfNotExists(ConfigSetParam configSetParam) {
        if(this.configWriteService == null){
            return;
        }
        this.configWriteService.saveIfNotExists(configSetParam);
    }

    public boolean delete(CommonParam commonParam) {
        if(this.configWriteService == null){
            return false;
        }
        return this.configWriteService.delete(commonParam);
    }
}
