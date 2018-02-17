package com.github.liuyuyu.dictator.server.common.controller;

import com.github.liuyuyu.dictator.common.model.response.DataWrapper;
import com.github.liuyuyu.dictator.server.common.model.dto.DictatorConfigDto;
import com.github.liuyuyu.dictator.server.common.model.param.ConfigListParam;
import com.github.liuyuyu.dictator.server.core.service.database.DataBaseConfigReadService;
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

    @RequestMapping("/list")
    public DataWrapper list(@RequestBody @Valid ConfigListParam configListParam) {
        PageInfo<DictatorConfigDto> dictatorConfigDtoPageInfo = this.configReadService.findPageValid(configListParam);
        return DataWrapper.from(dictatorConfigDtoPageInfo);
    }
}
