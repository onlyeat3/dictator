package com.github.liuyuyu.dictator.server.common.service;

import com.github.liuyuyu.dictator.common.utils.BeanConverter;
import com.github.liuyuyu.dictator.server.common.annotation.TransactionalAutoRollback;
import com.github.liuyuyu.dictator.server.common.exception.ServiceException;
import com.github.liuyuyu.dictator.server.common.exception.enums.ConfigErrorMessageEnum;
import com.github.liuyuyu.dictator.server.mapper.DictatorConfigHistoryMapper;
import com.github.liuyuyu.dictator.server.mapper.DictatorConfigMapper;
import com.github.liuyuyu.dictator.server.model.entity.DictatorConfig;
import com.github.liuyuyu.dictator.server.model.entity.DictatorConfigHistory;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
 * @author liuyuyu
 */
@Service
public class ConfigService {
    @Autowired private DictatorConfigMapper configMapper;
    @Autowired private DictatorConfigHistoryMapper configHistoryMapper;

    /**
     * 移动到历史表
     */
    @TransactionalAutoRollback
    public void delete(@NonNull Long configId) {
        DictatorConfig dictatorConfig = this.configMapper.findById(configId)
                .orElseThrow(() -> ServiceException.from(ConfigErrorMessageEnum.CONFIG_NOT_EXISTS));
        DictatorConfigHistory dictatorConfigHistory = BeanConverter.from(dictatorConfig)
                .to(DictatorConfigHistory.class);
        dictatorConfigHistory.setConfigId(configId);
        dictatorConfigHistory.setId(null);

        this.configMapper.deleteByPrimaryKey(configId);
        this.configHistoryMapper.insertSelective(dictatorConfigHistory);
    }
}
