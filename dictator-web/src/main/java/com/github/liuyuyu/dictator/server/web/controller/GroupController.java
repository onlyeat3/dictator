package com.github.liuyuyu.dictator.server.web.controller;

import com.github.liuyuyu.dictator.common.model.response.DataWrapper;
import com.github.liuyuyu.dictator.server.utils.ResourceName;
import com.github.liuyuyu.dictator.server.web.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/*
 * @author liuyuyu
 */
@ResourceName(value = "分组",uri = "group")
@RestController
@RequestMapping("/group")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @ResourceName("客户端分组列表")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public DataWrapper list() {
        return DataWrapper.from(this.groupService.findAll());
    }
}
