package com.github.liuyuyu.dictator.server.common.service;

import com.github.liuyuyu.dictator.common.utils.BeanConverter;
import com.github.liuyuyu.dictator.server.common.annotation.TransactionalAutoRollback;
import com.github.liuyuyu.dictator.server.common.exception.ServiceException;
import com.github.liuyuyu.dictator.server.common.exception.enums.ConfigErrorMessageEnum;
import com.github.liuyuyu.dictator.server.common.model.param.ConfigSaveUpdateParam;
import com.github.liuyuyu.dictator.server.mapper.DictatorConfigHistoryMapper;
import com.github.liuyuyu.dictator.server.mapper.DictatorConfigMapper;
import com.github.liuyuyu.dictator.server.model.entity.DictatorConfig;
import com.github.liuyuyu.dictator.server.model.entity.DictatorConfigHistory;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    @TransactionalAutoRollback
    public void saveOrUpdate(ConfigSaveUpdateParam configSaveUpdateParam) {
        DictatorConfig dictatorConfig = configSaveUpdateParam.to(DictatorConfig.class);
        //版本上升
        Optional<DictatorConfigHistory> lastByConfig = this.configHistoryMapper.findLastByConfigId(configSaveUpdateParam.getId());
        Long version = 0L;
        if(lastByConfig.isPresent()){
            version = lastByConfig.get().getVersion() + 1L;
        }
        dictatorConfig.setVersion(version);
        //没有ID,insert
        if(configSaveUpdateParam.getId() == null || configSaveUpdateParam.getId() == -1L){
            dictatorConfig.setId(null);
            this.configMapper.insertSelective(dictatorConfig);
        }else{
            //先保存历史记录再更新
            Optional<DictatorConfig> optionalDictatorConfig = this.configMapper.findById(configSaveUpdateParam.getId());
            if(optionalDictatorConfig.isPresent()){
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
}
