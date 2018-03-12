package com.github.liuyuyu.dictator.server.common.controller;

import com.github.liuyuyu.dictator.common.model.response.DataWrapper;
import com.github.liuyuyu.dictator.server.common.model.dto.DictatorUserDto;
import com.github.liuyuyu.dictator.server.common.model.param.ConfigProfileParam;
import com.github.liuyuyu.dictator.server.common.model.request.ConfigProfileRequest;
import com.github.liuyuyu.dictator.server.common.model.request.IdRequest;
import com.github.liuyuyu.dictator.server.common.mvc.CurrentUser;
import com.github.liuyuyu.dictator.server.common.service.ProfileService;
import com.github.liuyuyu.dictator.server.model.entity.DictatorUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/*
 * @author liuyuyu
 */
@RestController
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public DataWrapper list() {
        return DataWrapper.from(this.profileService.findAll());
    }

    @RequestMapping(value = "/saveOrUpdate",method = RequestMethod.POST)
    public DataWrapper saveOrUpdate(@RequestBody @Valid ConfigProfileRequest configProfileRequest, @CurrentUser DictatorUserDto currentUser){
        ConfigProfileParam configProfileParam = new ConfigProfileParam();
        configProfileParam.from(configProfileRequest);
        configProfileParam.setOperatorId(currentUser.getId());
        configProfileParam.setOperatorIp(currentUser.getLoginIp());
        this.profileService.saveOrUpdate(configProfileParam);
        return DataWrapper.of();
    }
}
