package com.github.liuyuyu.dictator.server.core.service;

import com.github.liuyuyu.dictator.common.model.dto.DictatorValueResponse;
import com.github.liuyuyu.dictator.common.model.request.BatchGetRequest;
import com.github.liuyuyu.dictator.common.utils.BeanConverter;
import com.github.liuyuyu.dictator.service.param.CommonParam;
import com.github.liuyuyu.dictator.service.param.ConfigGetParam;
import com.github.liuyuyu.dictator.service.redis.RedisConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author liuyuyu
 */
@Component
public class ListableConfigReadService {
    @Autowired
    private RedisConfigService configReadService;

    public DictatorValueResponse find(ConfigGetParam configGetParam) {
        DictatorValueResponse dictatorValueResponse = configReadService.find(configGetParam);
        if (dictatorValueResponse != null && dictatorValueResponse.getValue() != null) {
            return dictatorValueResponse;
        }
        return null;
    }

    public boolean exists(CommonParam commonParam) {
        return configReadService.exists(commonParam);
    }

}
