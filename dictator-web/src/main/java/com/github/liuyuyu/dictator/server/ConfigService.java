package com.github.liuyuyu.dictator.server;

import com.github.liuyuyu.dictator.server.param.CommonParam;
import com.github.liuyuyu.dictator.server.param.ConfigGetParam;
import com.github.liuyuyu.dictator.server.param.ConfigSetParam;

/*
 * @author liuyuyu
 */
public interface ConfigService {
    String find(ConfigGetParam configGetParam);

    void save(ConfigSetParam configSetParam);

    void saveOrModify(ConfigSetParam configSetParam);

    boolean exists(CommonParam commonParam);

    void saveIfNotExists(ConfigSetParam configSetParam);
}
