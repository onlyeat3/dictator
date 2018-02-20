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
        return this.configHistoryMapper.findPageValid(configListParam).toPageInfo();
    }

    public List<DictatorConfigHistoryDto> findAllByConfigId(@NonNull Long configId) {
        List<DictatorConfigHistory> historyList = this.configHistoryMapper.findByConfigId(configId);
        return BeanConverter.from(historyList)
                .toList(DictatorConfigHistoryDto.class);
    }

    @TransactionalAutoRollback
    public void recovery(@NonNull Long id) {
        DictatorConfigHistory oldConfigHistory = this.configHistoryMapper.findById(id)
                .orElseThrow(ConfigErrorMessageEnum.CONFIG_HISTORY_NOT_EXISTS::getServiceException);
        DictatorConfig currentConfig = this.dictatorConfigMapper.findById(oldConfigHistory.getConfigId())
                .orElseThrow(ConfigErrorMessageEnum.CONFIG_NOT_EXISTS::getServiceException);
        currentConfig.setId(oldConfigHistory.getConfigId());
        if(currentConfig.getVersion().equals(oldConfigHistory.getVersion())){
            return;
        }
        //当前有效配置和历史配置交换
        this.dictatorConfigMapper.deleteByPrimaryKey(oldConfigHistory.getConfigId());
        this.configHistoryMapper.deleteByPrimaryKey(id);

        DictatorConfigHistory newConfigHistory = BeanConverter.from(currentConfig).to(DictatorConfigHistory.class);
        newConfigHistory.setConfigId(currentConfig.getId());
        this.configHistoryMapper.insertSelective(newConfigHistory);
        DictatorConfig newConfig = BeanConverter.from(oldConfigHistory).to(DictatorConfig.class);
        newConfig.setId(oldConfigHistory.getConfigId());
        this.dictatorConfigMapper.insertSelective(newConfig);
    }
}
