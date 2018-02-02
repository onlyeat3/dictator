package com.github.liuyuyu.dictator.server.service.database;

import com.github.liuyuyu.dictator.common.exception.IgnoredFunctionException;
import com.github.liuyuyu.dictator.common.model.dto.DictatorValueResponse;
import com.github.liuyuyu.dictator.server.mapper.DictatorConfigMapper;
import com.github.liuyuyu.dictator.server.model.entity.DictatorConfig;
import com.github.liuyuyu.dictator.server.service.ConfigService;
import com.github.liuyuyu.dictator.server.service.param.CommonParam;
import com.github.liuyuyu.dictator.server.service.param.ConfigGetParam;
import com.github.liuyuyu.dictator.server.service.param.ConfigSetParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author liuyuyu
 */
@Service
public class DataBaseConfigService implements ConfigService{
    @Autowired private DictatorConfigMapper configMapper;

    @Override
    public DictatorValueResponse find(ConfigGetParam configGetParam) {
        DictatorConfig dictatorConfigEntity = this.configMapper.findByGetParam(configGetParam);
        DictatorValueResponse dictatorValueResponse = DictatorValueResponse.of(configGetParam.getDefaultValue());
        if(dictatorConfigEntity != null){
            dictatorValueResponse.setValue(dictatorConfigEntity.getConfigValue());
            dictatorValueResponse.setVersion(String.valueOf(dictatorConfigEntity.getVersion()));
        }
        return dictatorValueResponse;
    }

    @Override
    public void save(ConfigSetParam configSetParam) {
        throw IgnoredFunctionException.of("DataBaseConfigService#save(ConfigSetParam)");
    }

    @Override
    public void saveOrModify(ConfigSetParam configSetParam) {
        throw IgnoredFunctionException.of("DataBaseConfigService#saveOrModify(ConfigSetParam)");
    }

    @Override
    public boolean exists(CommonParam commonParam) {
        return this.configMapper.countByParam(commonParam) > 0;
    }

    @Override
    public void saveIfNotExists(ConfigSetParam configSetParam) {
        throw IgnoredFunctionException.of("DataBaseConfigService#saveIfNotExists(ConfigSetParam)");
    }
}
