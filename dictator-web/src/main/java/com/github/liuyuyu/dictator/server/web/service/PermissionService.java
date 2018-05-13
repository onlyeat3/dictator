package com.github.liuyuyu.dictator.server.web.service;

import com.github.liuyuyu.dictator.server.mapper.DictatorConfigMapper;
import com.github.liuyuyu.dictator.server.mapper.DictatorRoleGroupMapper;
import com.github.liuyuyu.dictator.server.mapper.DictatorRoleProfileMapper;
import com.github.liuyuyu.dictator.server.web.exception.enums.ConfigErrorMessageEnum;
import com.github.liuyuyu.dictator.server.web.model.param.PermissionCheckParam;
import com.github.liuyuyu.dictator.server.web.model.param.ProfilePermissionCheckParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

@Service
public class PermissionService {
    @Autowired private DictatorRoleProfileMapper roleProfileMapper;
    @Autowired private DictatorRoleGroupMapper roleGroupMapper;
    @Autowired private DictatorConfigMapper configMapper;

    public boolean hasPermission(@Valid PermissionCheckParam permissionCheckParam){
        //环境权限
        boolean hasProfilePermission = this.roleProfileMapper.countByRoleIdAndAndConfigId(permissionCheckParam.getRoleIdList(), permissionCheckParam.getConfigId()) > 0;
        //TODO 分组权限
        //所有权限都拥有才能返回
        if(hasProfilePermission){
            return true;
        }else{
            return false;
        }
    }

    public void checkPermission(PermissionCheckParam permissionCheckParam){
        if (!this.hasPermission(permissionCheckParam)) {
            throw ConfigErrorMessageEnum.NO_CONFIG_PERMISSION.serviceException();
        }
    }

    public void checkWritePermission(ProfilePermissionCheckParam profilePermissionCheckParam) {
        boolean hasProfilePermission = this.roleProfileMapper.countByRoleIdAndAndProfileId(profilePermissionCheckParam.getRoleIdList(), profilePermissionCheckParam.getProfileId()) > 0;
        if(!hasProfilePermission){
            throw ConfigErrorMessageEnum.NO_PROFILE_PERMISSION.serviceException();
        }
    }
}
