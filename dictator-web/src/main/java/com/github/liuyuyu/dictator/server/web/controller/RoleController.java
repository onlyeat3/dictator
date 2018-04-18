package com.github.liuyuyu.dictator.server.web.controller;

import com.github.liuyuyu.dictator.common.model.response.DataWrapper;
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
@RequestMapping("/role")
@RestController
public class RoleController {
    @Autowired private RoleService roleService;

    @RequestMapping("/list")
    public DataWrapper showList(@RequestBody @Valid RoleQueryParam roleQueryParam){
        PageInfo<DictatorRoleDto> pageInfo = this.roleService.findPage(roleQueryParam);
        return DataWrapper.from(pageInfo);
    }

    @RequestMapping("/detail")
    public DataWrapper showDetail(@RequestBody @Valid IdRequest idRequest){
        return DataWrapper.from(this.roleService.findDetail(idRequest.getId()));
    }

    @RequestMapping("/saveOrUpdate")
    public DataWrapper saveOrUpdate(@RequestBody @Valid RoleSaveOrUpdateParam roleSaveOrUpdateParam, @CurrentUser DictatorUserDto currentUser){
        roleSaveOrUpdateParam.setOperatorId(currentUser.getId());
        roleSaveOrUpdateParam.setOperatorIp(currentUser.getLoginIp());
        this.roleService.saveOrUpdate(roleSaveOrUpdateParam);
        return DataWrapper.of();
    }

    @RequestMapping("/grantPermission")
    public DataWrapper grantPermission(@RequestBody @Valid RolePermissionUpdateParam roleSaveOrUpdateParam){
        this.roleService.updatePermission(roleSaveOrUpdateParam);
        return DataWrapper.of();
    }

    @RequestMapping("/delete")
    public DataWrapper delete(@RequestBody @Valid IdRequest idRequest){
        this.roleService.delete(idRequest.getId());
        return DataWrapper.of();
    }
}
