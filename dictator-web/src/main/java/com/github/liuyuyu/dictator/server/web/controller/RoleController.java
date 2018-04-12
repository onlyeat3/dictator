package com.github.liuyuyu.dictator.server.web.controller;

import com.github.liuyuyu.dictator.common.model.response.DataWrapper;
import com.github.liuyuyu.dictator.server.web.model.dto.DictatorRoleDto;
import com.github.liuyuyu.dictator.server.web.model.param.RoleQueryParam;
import com.github.liuyuyu.dictator.server.web.service.RoleService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author liuyuyu
 */
@RequestMapping("/role")
@RestController
public class RoleController {
    @Autowired private RoleService roleService;

    @RequestMapping("/list")
    public DataWrapper showList(@RequestBody @Valid RoleQueryParam roleQueryParam){
        PageInfo<DictatorRoleDto> pageInfo = this.roleService.findPage(roleQueryParam);
        return DataWrapper.from(pageInfo);
    }
}
