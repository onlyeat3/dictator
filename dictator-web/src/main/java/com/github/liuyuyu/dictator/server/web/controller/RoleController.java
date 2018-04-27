package com.github.liuyuyu.dictator.server.web.controller;

import com.github.liuyuyu.dictator.common.model.response.DataWrapper;
import com.github.liuyuyu.dictator.server.utils.ResourceName;
import com.github.liuyuyu.dictator.server.web.model.dto.DictatorRoleDto;
import com.github.liuyuyu.dictator.server.web.model.dto.DictatorUserDto;
import com.github.liuyuyu.dictator.server.web.model.param.RolePermissionUpdateParam;
import com.github.liuyuyu.dictator.server.web.model.param.RoleQueryParam;
import com.github.liuyuyu.dictator.server.web.model.param.RoleSaveOrUpdateParam;
import com.github.liuyuyu.dictator.server.web.model.request.IdRequest;
import com.github.liuyuyu.dictator.server.web.mvc.CurrentUser;
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
@ResourceName(value = "角色",uri = "role")
@RequestMapping("/role")
@RestController
public class RoleController {
    @Autowired private RoleService roleService;

    @ResourceName("角色列表")
    @RequestMapping("/list")
    public DataWrapper showList(@RequestBody @Valid RoleQueryParam roleQueryParam){
        PageInfo<DictatorRoleDto> pageInfo = this.roleService.findPage(roleQueryParam);
        return DataWrapper.from(pageInfo);
    }

    @ResourceName("角色详情")
    @RequestMapping("/detail")
    public DataWrapper showDetail(@RequestBody @Valid IdRequest idRequest){
        return DataWrapper.from(this.roleService.findDetail(idRequest.getId()));
    }

    @ResourceName("角色增加/编辑")
    @RequestMapping("/saveOrUpdate")
    public DataWrapper saveOrUpdate(@RequestBody @Valid RoleSaveOrUpdateParam roleSaveOrUpdateParam, @CurrentUser DictatorUserDto currentUser){
        roleSaveOrUpdateParam.setOperatorId(currentUser.getId());
        roleSaveOrUpdateParam.setOperatorIp(currentUser.getLoginIp());
        this.roleService.saveOrUpdate(roleSaveOrUpdateParam);
        return DataWrapper.of();
    }

    @ResourceName("授权给角色")
    @RequestMapping("/grantPermission")
    public DataWrapper grantPermission(@RequestBody @Valid RolePermissionUpdateParam roleSaveOrUpdateParam){
        this.roleService.updatePermission(roleSaveOrUpdateParam);
        return DataWrapper.of();
    }

    @ResourceName("删除角色")
    @RequestMapping("/delete")
    public DataWrapper delete(@RequestBody @Valid IdRequest idRequest){
        this.roleService.delete(idRequest.getId());
        return DataWrapper.of();
    }
}
