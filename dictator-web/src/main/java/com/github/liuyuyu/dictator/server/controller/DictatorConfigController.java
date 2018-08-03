package com.github.liuyuyu.dictator.server.controller;

import com.github.liuyuyu.dictator.common.model.request.BatchGetRequest;
import com.github.liuyuyu.dictator.common.model.request.PropertyGetRequest;
import com.github.liuyuyu.dictator.common.model.response.DataWrapper;
import com.github.liuyuyu.dictator.server.core.service.ListableConfigReadService;
import com.github.liuyuyu.dictator.server.core.service.param.CommonParam;
import com.github.liuyuyu.dictator.server.core.service.param.ConfigGetParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/*
 * @author liuyuyu
 */
@Slf4j
@RestController
@RequestMapping("/dictator/config")
public class DictatorConfigController {

    @Autowired
    private ListableConfigReadService listableConfigReadService;

    @RequestMapping(value = "/get", method = RequestMethod.POST)
    public DataWrapper get(@Valid @RequestBody PropertyGetRequest propertyGetRequest) {
        ConfigGetParam configGetParam = ConfigGetParam.from(propertyGetRequest);
        DataWrapper dataWrapper = DataWrapper.from(this.listableConfigReadService.find(configGetParam));
        log.info("request:{},response:{}", propertyGetRequest, dataWrapper);
        return dataWrapper;
    }

    @RequestMapping(value = "/batch/get", method = RequestMethod.POST)
    public DataWrapper batchGet(@Valid @RequestBody BatchGetRequest batchGetRequest) {
        DataWrapper dataWrapper = DataWrapper.from(this.listableConfigReadService.findAll(batchGetRequest));
        log.info("request:{},response:{}", batchGetRequest, dataWrapper);
        return dataWrapper;
    }
}
