package com.github.liuyuyu.dictator.server.task;

import com.github.liuyuyu.dictator.server.model.entity.DictatorConfig;
import com.github.liuyuyu.dictator.server.model.entity.DictatorConfigHistory;
import com.github.liuyuyu.dictator.server.service.ListableConfigWriteService;
import com.github.liuyuyu.dictator.server.service.database.DataBaseConfigReadService;
import com.github.liuyuyu.dictator.server.service.param.CommonParam;
import com.github.liuyuyu.dictator.server.service.param.ConfigSetParam;
import com.github.liuyuyu.dictator.server.utils.BeanConverter;
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
    @Autowired private DataBaseConfigReadService dataBaseConfigReadService;
    @Autowired private ListableConfigWriteService listableConfigWriteService;

    @Scheduled(fixedRateString = "${dictator.server.refresh.rate:5000}")
    public void refresh(){
        //删除最近一小时被清掉的配置
        List<DictatorConfigHistory> lastHourInvalidConfigList = this.dataBaseConfigReadService.findLastHourInvalid();
        lastHourInvalidConfigList.stream()
                .map(ch-> {
                    CommonParam commonParam = BeanConverter.from(ch).to(CommonParam.class);
                    commonParam.setKey(ch.getConfigName());
                    return commonParam;
                })
                .forEach(c-> this.listableConfigWriteService.delete(c));

        //刷新新的
        List<DictatorConfig> dictatorConfigList = this.dataBaseConfigReadService.findAllValid();
        dictatorConfigList.stream()
                .map(d->{
                    ConfigSetParam configSetParam = BeanConverter.from(d)
                            .to(ConfigSetParam.class);
                    configSetParam.setKey(d.getConfigName());
                    configSetParam.setValue(d.getConfigValue());
                    return configSetParam;
                })
                .forEach(c-> this.listableConfigWriteService.saveOrModify(c));
    }

    @PostConstruct
    public void init(){
        log.info("ConfigReloadTask start.");
    }
}
