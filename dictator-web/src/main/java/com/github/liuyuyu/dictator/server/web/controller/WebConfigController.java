package com.github.liuyuyu.dictator.server.web.controller;

import com.github.liuyuyu.dictator.common.model.response.DataWrapper;
import com.github.liuyuyu.dictator.server.core.service.database.DataBaseConfigReadService;
import com.github.liuyuyu.dictator.server.web.model.dto.DictatorConfigDto;
import com.github.liuyuyu.dictator.server.web.model.dto.DictatorUserDto;
import com.github.liuyuyu.dictator.server.web.model.param.ConfigBatchImportParam;
import com.github.liuyuyu.dictator.server.web.model.param.ConfigListParam;
import com.github.liuyuyu.dictator.server.web.model.param.ConfigSaveUpdateParam;
import com.github.liuyuyu.dictator.server.web.model.request.IdRequest;
import com.github.liuyuyu.dictator.server.web.mvc.CurrentUser;
import com.github.liuyuyu.dictator.server.web.service.ConfigService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/*
 * @author liuyuyu
 */
@RestController
@RequestMapping("/config")
public class WebConfigController {

    @Autowired
    private DataBaseConfigReadService configReadService;
    @Autowired
    private ConfigService configService;

    @RequestMapping("/list")
    public DataWrapper list(@RequestBody @Valid ConfigListParam configListParam) {
        PageInfo<DictatorConfigDto> dictatorConfigDtoPageInfo = this.configReadService.findPageValid(configListParam);
        return DataWrapper.from(dictatorConfigDtoPageInfo);
    }

    @RequestMapping("/saveOrUpdate")
    public DataWrapper saveOrUpdate(@RequestBody @Valid ConfigSaveUpdateParam configSaveUpdateParam, @CurrentUser DictatorUserDto currentUser) {
        configSaveUpdateParam.join(currentUser);
        this.configService.saveOrUpdate(configSaveUpdateParam);
        return DataWrapper.of();
    }

    @RequestMapping("/batchAdd")
    public DataWrapper batchAdd(@RequestBody @Valid ConfigBatchImportParam configBatchImportParam, @CurrentUser DictatorUserDto currentUser) {
        configBatchImportParam.join(currentUser);
        this.configService.batchAdd(configBatchImportParam);
        return DataWrapper.of();
    }

    @RequestMapping("/delete")
    public DataWrapper delete(@RequestBody @Valid IdRequest idRequest) {
        this.configService.delete(idRequest.getId());
        return DataWrapper.of();
    }
}
