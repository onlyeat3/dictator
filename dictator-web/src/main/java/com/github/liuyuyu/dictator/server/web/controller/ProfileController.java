package com.github.liuyuyu.dictator.server.web.controller;

import com.github.liuyuyu.dictator.common.model.response.DataWrapper;
import com.github.liuyuyu.dictator.server.utils.ResourceName;
import com.github.liuyuyu.dictator.server.web.model.dto.DictatorUserDto;
import com.github.liuyuyu.dictator.server.web.model.param.ConfigProfileDeleteParam;
import com.github.liuyuyu.dictator.server.web.model.param.ConfigProfileParam;
import com.github.liuyuyu.dictator.server.web.model.request.ConfigProfileDeleteRequest;
import com.github.liuyuyu.dictator.server.web.model.request.ConfigProfileRequest;
import com.github.liuyuyu.dictator.server.web.mvc.CurrentUser;
import com.github.liuyuyu.dictator.server.web.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/*
 * @author liuyuyu
 */
@ResourceName(value = "环境",uri = "profile")
@RestController
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @ResourceName("环境列表")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public DataWrapper list() {
        return DataWrapper.from(this.profileService.findAll());
    }

    @ResourceName("环境增加/编辑")
    @RequestMapping(value = "/saveOrUpdate",method = RequestMethod.POST)
    public DataWrapper saveOrUpdate(@RequestBody @Valid ConfigProfileRequest configProfileRequest, @CurrentUser DictatorUserDto currentUser){
        ConfigProfileParam configProfileParam = new ConfigProfileParam();
        configProfileParam.from(configProfileRequest);
        configProfileParam.setOperatorId(currentUser.getId());
        configProfileParam.setOperatorIp(currentUser.getLoginIp());
        this.profileService.saveOrUpdate(configProfileParam);
        return DataWrapper.of();
    }

    /**
     * 删除环境
     */
    @ResourceName("删除环境")
    @RequestMapping("/delete")
    public DataWrapper delete(@RequestBody @Valid ConfigProfileDeleteRequest profileEnableUpdateRequest, @CurrentUser DictatorUserDto currentUser){
        ConfigProfileDeleteParam configProfileDeleteParam = profileEnableUpdateRequest.to(ConfigProfileDeleteParam.class);
        configProfileDeleteParam.join(currentUser);
        configProfileDeleteParam.setCurrentRoleIdList(currentUser.getRoleIdList());
        this.profileService.delete(configProfileDeleteParam);
        return DataWrapper.of();
    }
}
