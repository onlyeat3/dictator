package com.github.liuyuyu.dictator.server.web.controller;

import com.github.liuyuyu.dictator.common.model.response.DataWrapper;
import com.github.liuyuyu.dictator.server.web.model.dto.DictatorUserDto;
import com.github.liuyuyu.dictator.server.web.model.param.AppSaveOrUpdateParam;
import com.github.liuyuyu.dictator.server.web.model.param.DeleteParam;
import com.github.liuyuyu.dictator.server.web.mvc.CurrentUser;
import com.github.liuyuyu.dictator.server.web.service.DictatorAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author liuyuyu
 */
@RestController
@RequestMapping("/app")
public class DictatorAppController {
    @Autowired private DictatorAppService appService;

    @RequestMapping("/listAll")
    public DataWrapper findAll(){
        return DataWrapper.from(this.appService.findAll());
    }

    @RequestMapping("/saveOrUpdate")
    public DataWrapper saveOrUpdate(@Valid @RequestBody AppSaveOrUpdateParam param, @CurrentUser DictatorUserDto currentUser){
        param.join(currentUser);
        this.appService.saveOrUpdate(param);
        return DataWrapper.of();
    }

    @RequestMapping("/delete")
    public DataWrapper delete(@Valid @RequestBody DeleteParam param, @CurrentUser DictatorUserDto currentUser){
        param.join(currentUser);
        this.appService.delete(param);
        return DataWrapper.of();
    }
}
