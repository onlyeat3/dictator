package com.github.liuyuyu.dictator.server.core.task;

import com.github.liuyuyu.dictator.common.utils.BeanConverter;
import com.github.liuyuyu.dictator.server.core.service.ListableConfigWriteService;
import com.github.liuyuyu.dictator.server.core.service.database.DataBaseConfigReadService;
import com.github.liuyuyu.dictator.server.core.service.param.CommonParam;
import com.github.liuyuyu.dictator.server.core.service.param.ConfigSetParam;
import com.github.liuyuyu.dictator.server.model.entity.DictatorConfig;
import com.github.liuyuyu.dictator.server.model.entity.DictatorConfigHistory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

/*
 * @author liuyuyu
 */
@Slf4j
@Component
public class ConfigReloadTask {
    @Autowired
    private DataBaseConfigReadService dataBaseConfigReadService;
    @Autowired
    private ListableConfigWriteService listableConfigWriteService;

    @Scheduled(fixedRateString = "${dictator.server.refresh.rate:5000}")
    public void refresh() {
        //删除最近一小时被清掉的配置
        List<DictatorConfigHistory> lastHourInvalidConfigList = this.dataBaseConfigReadService.findLastHourInvalid();
        lastHourInvalidConfigList.stream()
                .map(ch -> {
                    CommonParam commonParam = BeanConverter.from(ch).to(CommonParam.class);
                    commonParam.setKey(ch.getConfigName());
                    return commonParam;
                })
                .forEach(c -> this.listableConfigWriteService.delete(c));

        //刷新新的
        List<DictatorConfig> dictatorConfigList = this.dataBaseConfigReadService.findAllValid();
        dictatorConfigList.stream()
                .map(d -> {
                    ConfigSetParam configSetParam = BeanConverter.from(d)
                            .to(ConfigSetParam.class);
                    configSetParam.setKey(d.getConfigName());
                    configSetParam.setValue(d.getConfigValue());
                    configSetParam.setLastUpdatedTime(d.getUpdatedTime().toInstant().toEpochMilli());
                    return configSetParam;
                })
                .forEach(c -> this.listableConfigWriteService.saveOrModify(c));
    }

    @PostConstruct
    public void init() {
        log.info("ConfigReloadTask start.");
    }
}
