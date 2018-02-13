package com.github.liuyuyu.dictator.server.core.service;

import com.github.liuyuyu.dictator.server.core.service.param.CommonParam;
import com.github.liuyuyu.dictator.server.core.service.param.ConfigSetParam;

/*
 * @author liuyuyu
 */
public interface ConfigWriteService {

    void save(ConfigSetParam configSetParam);

    void saveOrModify(ConfigSetParam configSetParam);

    void saveIfNotExists(ConfigSetParam configSetParam);

    boolean delete(CommonParam commonParam);
}
