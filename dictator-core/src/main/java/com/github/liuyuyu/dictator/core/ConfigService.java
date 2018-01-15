package com.github.liuyuyu.dictator.core;

import com.github.liuyuyu.dictator.core.param.CommonParam;
import com.github.liuyuyu.dictator.core.param.ConfigGetParam;
import com.github.liuyuyu.dictator.core.param.ConfigSetParam;

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
