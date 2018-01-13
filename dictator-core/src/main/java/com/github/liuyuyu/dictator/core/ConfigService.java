package com.github.liuyuyu.dictator.core;

import com.github.liuyuyu.dictator.core.param.CommonParam;
import com.github.liuyuyu.dictator.core.param.ConfigGetParam;
import com.github.liuyuyu.dictator.core.param.ConfigSetParam;

/*
 * @author liuyuyu
 */
public interface ConfigService {
    String get(ConfigGetParam configGetParam);

    void set(ConfigSetParam configSetParam);

    void setOrModify(ConfigSetParam configSetParam);

    boolean exists(CommonParam commonParam);

    void setIfNotExists(ConfigSetParam configSetParam);
}
