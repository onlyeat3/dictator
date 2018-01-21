package com.github.liuyuyu.dictator.server.service;

import com.github.liuyuyu.dictator.server.service.dto.ReturnValueDto;
import com.github.liuyuyu.dictator.server.service.param.CommonParam;
import com.github.liuyuyu.dictator.server.service.param.ConfigGetParam;
import com.github.liuyuyu.dictator.server.service.param.ConfigSetParam;

/*
 * @author liuyuyu
 */
public interface ConfigService {
    ReturnValueDto find(ConfigGetParam configGetParam);

    void save(ConfigSetParam configSetParam);

    void saveOrModify(ConfigSetParam configSetParam);

    boolean exists(CommonParam commonParam);

    void saveIfNotExists(ConfigSetParam configSetParam);
}
