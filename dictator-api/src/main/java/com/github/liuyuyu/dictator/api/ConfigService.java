package com.github.liuyuyu.dictator.api;

import com.github.liuyuyu.dictator.api.param.CommonParam;
import com.github.liuyuyu.dictator.api.param.ConfigGetParam;
import com.github.liuyuyu.dictator.api.param.ConfigSetParam;

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
