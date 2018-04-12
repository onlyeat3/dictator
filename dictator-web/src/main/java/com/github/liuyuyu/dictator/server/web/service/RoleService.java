package com.github.liuyuyu.dictator.server.web.service;

import com.github.liuyuyu.dictator.server.mapper.DictatorRoleMapper;
import com.github.liuyuyu.dictator.server.mapper.DictatorRoleResourceMapper;
import com.github.liuyuyu.dictator.server.mapper.DictatorUserRoleMapper;
import com.github.liuyuyu.dictator.server.model.entity.DictatorRole;
import com.github.liuyuyu.dictator.server.model.entity.DictatorRoleResource;
import com.github.liuyuyu.dictator.server.web.annotation.TransactionalAutoRollback;
import com.github.liuyuyu.dictator.server.web.model.dto.DictatorRoleDto;
import com.github.liuyuyu.dictator.server.web.model.param.RolePermissionUpdateParam;
import com.github.liuyuyu.dictator.server.web.model.param.RoleQueryParam;
import com.github.liuyuyu.dictator.server.web.model.param.RoleSaveParam;
import com.github.pagehelper.PageInfo;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author liuyuyu
 */
@Service
public class RoleService {
    @Autowired private DictatorRoleMapper roleMapper;
    @Autowired private DictatorRoleResourceMapper roleResourceMapper;
    @Autowired private DictatorUserRoleMapper userRoleMapper;


    @TransactionalAutoRollback
    public Long saveOrUpdate(@NonNull RoleSaveParam roleSaveParam){
        DictatorRole dictatorRoleEntity = roleSaveParam.to(DictatorRole.class);
        if(dictatorRoleEntity.getId() != null){
            this.roleMapper.updateByPrimaryKeySelective(dictatorRoleEntity);
        }else{
            this.roleMapper.insertSelective(dictatorRoleEntity);
        }
        return dictatorRoleEntity.getId();
    }

    @TransactionalAutoRollback
    public void updatePermission(RolePermissionUpdateParam rolePermissionUpdateParam){
        if(rolePermissionUpdateParam.getResourceIdList().isEmpty()){
            return;
        }
        //清空
        this.roleResourceMapper.deleteByRoleId(rolePermissionUpdateParam.getRoleId());
        //insert
        rolePermissionUpdateParam.getResourceIdList().stream()
                .parallel()
                .map(resourceId->{
                    DictatorRoleResource rr = new DictatorRoleResource();
                    rr.setRoleId(rolePermissionUpdateParam.getRoleId());
                    rr.setResourceId(resourceId);
                    return rr;
                })
                .forEach(e-> this.roleResourceMapper.insertSelective(e));
    }

    @TransactionalAutoRollback
    public void delete(@NonNull Long roleId){
        //删除角色
        this.roleMapper.deleteByPrimaryKey(roleId);
        //删除角色和用户的关联
        this.userRoleMapper.deleteByRoleId(roleId);
        //删除角色和资源的关联
        this.roleResourceMapper.deleteByRoleId(roleId);
    }

    public void findDetail(@NonNull Long roleId){
        //角色基本信息
        //角色拥有的权限
    }

    public PageInfo<DictatorRoleDto> findPage(@NonNull RoleQueryParam roleQueryParam) {
        return this.roleMapper.find(roleQueryParam)
                .toPageInfo();
    }
}
