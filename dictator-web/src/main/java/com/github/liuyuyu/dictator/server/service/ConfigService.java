package com.github.liuyuyu.dictator.server.service;

import com.github.liuyuyu.dictator.common.model.dto.DictatorValueResponse;
import com.github.liuyuyu.dictator.server.service.param.CommonParam;
import com.github.liuyuyu.dictator.server.service.param.ConfigGetParam;
import com.github.liuyuyu.dictator.server.service.param.ConfigSetParam;

/*
 * @author liuyuyu
 */
public interface ConfigService {
    DictatorValueResponse find(ConfigGetParam configGetParam);

    void save(ConfigSetParam configSetParam);

    void saveOrModify(ConfigSetParam configSetParam);

    boolean exists(CommonParam commonParam);

    void saveIfNotExists(ConfigSetParam configSetParam);
}
