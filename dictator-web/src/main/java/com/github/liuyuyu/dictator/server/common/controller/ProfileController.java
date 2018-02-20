package com.github.liuyuyu.dictator.server.common.controller;

import com.github.liuyuyu.dictator.common.model.response.DataWrapper;
import com.github.liuyuyu.dictator.server.common.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/*
 * @author liuyuyu
 */
@RestController
@RequestMapping("/profile")
public class ProfileController {

    @Autowired private ProfileService profileService;

    @RequestMapping(value = "/list",method = RequestMethod.POST)
    public DataWrapper list(){
        return DataWrapper.from(this.profileService.findAll());
    }
}
