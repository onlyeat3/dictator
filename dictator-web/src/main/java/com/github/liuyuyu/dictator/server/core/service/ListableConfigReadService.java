package com.github.liuyuyu.dictator.server.core.service;

import com.github.liuyuyu.dictator.common.model.dto.DictatorValueResponse;
import com.github.liuyuyu.dictator.server.core.service.param.CommonParam;
import com.github.liuyuyu.dictator.server.core.service.param.ConfigGetParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author liuyuyu
 */
@Component
public class ListableConfigReadService {
    @Autowired
    private List<ConfigReadService> configReadServiceList = new ArrayList<>();

    public DictatorValueResponse find(ConfigGetParam configGetParam) {
        for (ConfigReadService configReadService : this.configReadServiceList) {
            DictatorValueResponse dictatorValueResponse = configReadService.find(configGetParam);
            if (dictatorValueResponse != null && dictatorValueResponse.getValue() != null) {
                return dictatorValueResponse;
            }
        }
        return null;
    }

    public boolean exists(CommonParam commonParam) {
        for (ConfigReadService configReadService : this.configReadServiceList) {
            boolean isExists = configReadService.exists(commonParam);
            if (isExists) {
                return true;
            }
        }
        return false;
    }

    public Map<String, String> findAll(CommonParam commonParam) {
        for (ConfigReadService configReadService : this.configReadServiceList) {
            Map<String, String> configMap = configReadService.findAll(commonParam);
            if (configMap != null && !configMap.isEmpty()) {
                return configMap;
            }
        }
        return null;
    }
}
