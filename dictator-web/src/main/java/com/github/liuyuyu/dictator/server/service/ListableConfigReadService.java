package com.github.liuyuyu.dictator.server.service;

import com.github.liuyuyu.dictator.common.model.dto.DictatorValueResponse;
import com.github.liuyuyu.dictator.server.service.param.CommonParam;
import com.github.liuyuyu.dictator.server.service.param.ConfigGetParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liuyuyu
 */
@Component
public class ListableConfigReadService implements ConfigReadService {
    @Autowired private List<ConfigReadService> configReadServiceList = new ArrayList<>();

    @Override
    public DictatorValueResponse find(ConfigGetParam configGetParam) {
        for (ConfigReadService configReadService : this.configReadServiceList) {
            DictatorValueResponse dictatorValueResponse = configReadService.find(configGetParam);
            if (dictatorValueResponse != null && dictatorValueResponse.getValue() != null) {
                return dictatorValueResponse;
            }
        }
        return null;
    }

    @Override
    public boolean exists(CommonParam commonParam) {
        for (ConfigReadService configReadService : this.configReadServiceList) {
            boolean isExists = configReadService.exists(commonParam);
            if (isExists) {
                return true;
            }
        }
        return false;
    }
}
