package com.github.liuyuyu.dictator.server.web.service;

import com.github.liuyuyu.dictator.common.exception.ExceptionWrapper;
import com.github.liuyuyu.dictator.common.utils.BeanConverter;
import com.github.liuyuyu.dictator.server.web.annotation.TransactionalAutoRollback;
import com.github.liuyuyu.dictator.server.web.exception.ServiceException;
import com.github.liuyuyu.dictator.server.web.exception.enums.ConfigErrorMessageEnum;
import com.github.liuyuyu.dictator.server.web.model.param.ConfigBatchImportParam;
import com.github.liuyuyu.dictator.server.web.model.param.ConfigSaveUpdateParam;
import com.github.liuyuyu.dictator.server.mapper.DictatorConfigHistoryMapper;
import com.github.liuyuyu.dictator.server.mapper.DictatorConfigMapper;
import com.github.liuyuyu.dictator.server.model.entity.DictatorConfig;
import com.github.liuyuyu.dictator.server.model.entity.DictatorConfigHistory;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Properties;
import java.util.stream.Collectors;

/*
 * @author liuyuyu
 */
@Service
public class ConfigService {
    @Autowired
    private DictatorConfigMapper configMapper;
    @Autowired
    private DictatorConfigHistoryMapper configHistoryMapper;

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

    @TransactionalAutoRollback
    public void saveOrUpdate(ConfigSaveUpdateParam configSaveUpdateParam) {
        DictatorConfig dictatorConfig = configSaveUpdateParam.to(DictatorConfig.class);
        //版本上升
        Long version = 0L;
        //没有ID,insert
        if (configSaveUpdateParam.getId() == null || configSaveUpdateParam.getId() == -1L) {
            dictatorConfig.setVersion(version);
            dictatorConfig.setId(null);
            this.configMapper.insertSelective(dictatorConfig);
        } else {
            if(configSaveUpdateParam.getId() != null){
                Optional<DictatorConfigHistory> lastByConfig = this.configHistoryMapper.findLastByConfigId(configSaveUpdateParam.getId());
                if (lastByConfig.isPresent()) {
                    version = lastByConfig.get().getVersion() + 1L;
                }
            }
            dictatorConfig.setVersion(version);

            //先保存历史记录再更新
            Optional<DictatorConfig> optionalDictatorConfig = this.configMapper.findById(configSaveUpdateParam.getId());
            if (optionalDictatorConfig.isPresent()) {
                DictatorConfig oldDictatorConfig = optionalDictatorConfig.get();
                DictatorConfigHistory configHistory = BeanConverter.from(oldDictatorConfig)
                        .to(DictatorConfigHistory.class);
                configHistory.setConfigId(oldDictatorConfig.getId());
                configHistory.setId(null);
                dictatorConfig.setVersion(dictatorConfig.getVersion() + 1);
                this.configMapper.updateByPrimaryKeySelective(dictatorConfig);

                //保存
                this.configHistoryMapper.insertSelective(configHistory);
            }
        }
    }

    @TransactionalAutoRollback
    public void deleteByProfileId(@NonNull Long profileId) {
        List<DictatorConfig> configList = this.configMapper.findByProfileId(profileId);
        BeanConverter.from(configList)
                .toList(DictatorConfigHistory.class)
                .forEach(e->{
                    e.setConfigId(e.getId());
                    e.setId(null);
                    this.configHistoryMapper.insertSelective(e);
                });
        if(configList.isEmpty()){
            return;
        }
        List<Long> configIdList = configList.stream()
                .map(DictatorConfig::getId)
                .collect(Collectors.toList());
        this.configMapper.deleteByIdList(configIdList);
    }

    @TransactionalAutoRollback
    public void batchAdd(ConfigBatchImportParam configBatchImportParam) {
        try {
            Properties properties = new Properties();
            properties.load(new StringReader(configBatchImportParam.getContent()));
            properties.stringPropertyNames().stream()
                    .filter(Objects::nonNull)
                    .filter(key-> properties.getProperty(key) != null)
                    .map(key->{
                        ConfigSaveUpdateParam configSaveUpdateParam = BeanConverter.from(configBatchImportParam)
                                .to(ConfigSaveUpdateParam.class);
                        configSaveUpdateParam.setConfigName(key);
                        configSaveUpdateParam.setConfigValue(properties.getProperty(key));
                        return configSaveUpdateParam;
                    })
                    .forEach(this::saveOrUpdate);
        } catch (IOException e) {
            ExceptionWrapper.of(e);
        }
    }
}
