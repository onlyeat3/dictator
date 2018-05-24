package com.github.liuyuyu.dictator.server.web.controller;

import com.github.liuyuyu.dictator.common.model.response.DataWrapper;
import com.github.liuyuyu.dictator.server.core.service.database.DataBaseConfigReadService;
import com.github.liuyuyu.dictator.server.utils.ResourceName;
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
@ResourceName(value = "当前配置",uri = "/current")
@RestController
@RequestMapping("/config")
public class WebConfigController {

    @Autowired
    private DataBaseConfigReadService configReadService;
    @Autowired
    private ConfigService configService;

    @ResourceName("配置列表")
    @RequestMapping("/list")
    public DataWrapper list(@RequestBody @Valid ConfigListParam configListParam,@CurrentUser DictatorUserDto currentUser) {
        configListParam.setRoleIdList(currentUser.getRoleIdList());
        configListParam.setIsGM(currentUser.isGM());
        PageInfo<DictatorConfigDto> dictatorConfigDtoPageInfo = this.configReadService.findPageValid(configListParam);
        return DataWrapper.from(dictatorConfigDtoPageInfo);
    }

    @ResourceName("配置增加/编辑")
    @RequestMapping("/saveOrUpdate")
    public DataWrapper saveOrUpdate(@RequestBody @Valid ConfigSaveUpdateParam configSaveUpdateParam, @CurrentUser DictatorUserDto currentUser) {
        configSaveUpdateParam.join(currentUser);
        this.configService.saveOrUpdate(configSaveUpdateParam);
        return DataWrapper.of();
    }

    @ResourceName("批量导入配置")
    @RequestMapping("/batchAdd")
    public DataWrapper batchAdd(@RequestBody @Valid ConfigBatchImportParam configBatchImportParam, @CurrentUser DictatorUserDto currentUser) {
        configBatchImportParam.join(currentUser);
        this.configService.batchAdd(configBatchImportParam);
        return DataWrapper.of();
    }

    @ResourceName("删除配置")
    @RequestMapping("/delete")
    public DataWrapper delete(@RequestBody @Valid IdRequest idRequest,@CurrentUser DictatorUserDto currentUser) {
        this.configService.delete(idRequest.getId(),currentUser.getRoleIdList());
        return DataWrapper.of();
    }
}
