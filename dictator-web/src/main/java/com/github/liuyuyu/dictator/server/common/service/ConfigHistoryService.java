package com.github.liuyuyu.dictator.server.common.service;

import com.github.liuyuyu.dictator.common.utils.BeanConverter;
import com.github.liuyuyu.dictator.server.common.annotation.TransactionalAutoRollback;
import com.github.liuyuyu.dictator.server.common.exception.ServiceException;
import com.github.liuyuyu.dictator.server.common.exception.enums.ConfigErrorMessageEnum;
import com.github.liuyuyu.dictator.server.common.model.dto.DictatorConfigHistoryDto;
import com.github.liuyuyu.dictator.server.common.model.param.ConfigListParam;
import com.github.liuyuyu.dictator.server.common.model.param.HistoryConfigUpdateParam;
import com.github.liuyuyu.dictator.server.mapper.DictatorConfigHistoryMapper;
import com.github.liuyuyu.dictator.server.mapper.DictatorConfigMapper;
import com.github.liuyuyu.dictator.server.model.entity.DictatorConfig;
import com.github.liuyuyu.dictator.server.model.entity.DictatorConfigHistory;
import com.github.pagehelper.PageInfo;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*
 * @author liuyuyu
 */
@Service
public class ConfigHistoryService {
    @Autowired
    private DictatorConfigHistoryMapper configHistoryMapper;
    @Autowired private DictatorConfigMapper dictatorConfigMapper;

    public void save(HistoryConfigUpdateParam historyConfigUpdateParam) {
        DictatorConfigHistory configHistory = historyConfigUpdateParam.to(DictatorConfigHistory.class);
        this.configHistoryMapper.insertSelective(configHistory);
    }

    public PageInfo<DictatorConfigHistoryDto> findPageValid(ConfigListParam configListParam) {
        configListParam.startPage();
        return this.configHistoryMapper.findPageValid(configListParam);
    }

    public List<DictatorConfigHistoryDto> findAllByConfigId(@NonNull Long configId) {
        List<DictatorConfigHistory> historyList = this.configHistoryMapper.findByConfigId(configId);
        return BeanConverter.from(historyList)
                .toList(DictatorConfigHistoryDto.class);
    }

    @TransactionalAutoRollback
    public void recovery(@NonNull Long id) {
        DictatorConfigHistory dictatorConfigHistory = this.configHistoryMapper.findById(id)
                .orElseThrow(ConfigErrorMessageEnum.CONFIG_HISTORY_NOT_EXISTS::getServiceException);
        DictatorConfig dictatorConfig = BeanConverter.from(dictatorConfigHistory)
                .to(DictatorConfig.class);
        dictatorConfig.setId(dictatorConfigHistory.getConfigId());

        this.configHistoryMapper.deleteByPrimaryKey(id);
        this.dictatorConfigMapper.insertSelective(dictatorConfig);
    }
}
