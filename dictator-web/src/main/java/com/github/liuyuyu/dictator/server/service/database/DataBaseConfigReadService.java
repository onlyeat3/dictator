package com.github.liuyuyu.dictator.server.service.database;

import com.github.liuyuyu.dictator.common.model.dto.DictatorValueResponse;
import com.github.liuyuyu.dictator.server.mapper.DictatorConfigHistoryMapper;
import com.github.liuyuyu.dictator.server.mapper.DictatorConfigMapper;
import com.github.liuyuyu.dictator.server.model.entity.DictatorConfig;
import com.github.liuyuyu.dictator.server.model.entity.DictatorConfigHistory;
import com.github.liuyuyu.dictator.server.service.ConfigReadService;
import com.github.liuyuyu.dictator.server.service.param.CommonParam;
import com.github.liuyuyu.dictator.server.service.param.ConfigGetParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author liuyuyu
 */
@Component
@Order
public class DataBaseConfigReadService implements ConfigReadService {
    @Autowired private DictatorConfigMapper configMapper;
    @Autowired private DictatorConfigHistoryMapper configHistoryMapper;

    @Override
    public DictatorValueResponse find(ConfigGetParam configGetParam) {
        DictatorConfig dictatorConfigEntity = this.configMapper.findByGetParam(configGetParam);
        DictatorValueResponse dictatorValueResponse = DictatorValueResponse.of();
        if(dictatorConfigEntity != null){
            dictatorValueResponse.setValue(dictatorConfigEntity.getConfigValue());
            dictatorValueResponse.setVersion(String.valueOf(dictatorConfigEntity.getVersion()));
        }
        return dictatorValueResponse;
    }

    @Override
    public boolean exists(CommonParam commonParam) {
        return this.configMapper.countByParam(commonParam) > 0;
    }

    public List<DictatorConfig> findAllValid() {
        return this.configMapper.selectAll();
    }

    public List<DictatorConfigHistory> findLastHourInvalid() {
        return this.configHistoryMapper.findLastHour();
    }
}
