package com.github.liuyuyu.dictator.service;


import com.github.liuyuyu.dictator.service.param.CommonParam;
import com.github.liuyuyu.dictator.service.param.ConfigSetParam;

/*
 * @author liuyuyu
 */
public interface ConfigWriteService {

    void save(ConfigSetParam configSetParam);

    void saveOrModify(ConfigSetParam configSetParam);

    void saveIfNotExists(ConfigSetParam configSetParam);

    boolean delete(CommonParam commonParam);
}
