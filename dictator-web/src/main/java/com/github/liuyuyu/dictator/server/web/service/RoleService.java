package com.github.liuyuyu.dictator.server.web.service;

import com.github.liuyuyu.dictator.common.utils.BeanConverter;
import com.github.liuyuyu.dictator.server.mapper.DictatorResourceMapper;
import com.github.liuyuyu.dictator.server.mapper.DictatorRoleMapper;
import com.github.liuyuyu.dictator.server.mapper.DictatorRoleResourceMapper;
import com.github.liuyuyu.dictator.server.mapper.DictatorUserRoleMapper;
import com.github.liuyuyu.dictator.server.model.entity.DictatorResource;
import com.github.liuyuyu.dictator.server.model.entity.DictatorRole;
import com.github.liuyuyu.dictator.server.model.entity.DictatorRoleResource;
import com.github.liuyuyu.dictator.server.web.annotation.TransactionalAutoRollback;
import com.github.liuyuyu.dictator.server.web.model.dto.DictatorResourceDto;
import com.github.liuyuyu.dictator.server.web.model.dto.DictatorResourcePermissionDto;
import com.github.liuyuyu.dictator.server.web.model.dto.DictatorRoleDto;
import com.github.liuyuyu.dictator.server.web.model.param.RolePermissionUpdateParam;
import com.github.liuyuyu.dictator.server.web.model.param.RoleQueryParam;
import com.github.liuyuyu.dictator.server.web.model.param.RoleSaveOrUpdateParam;
import com.github.pagehelper.PageInfo;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author liuyuyu
 */
@Service
public class RoleService {
    @Autowired
    private DictatorRoleMapper roleMapper;
    @Autowired
    private DictatorRoleResourceMapper roleResourceMapper;
    @Autowired
    private DictatorUserRoleMapper userRoleMapper;
    @Autowired
    private DictatorResourceMapper resourceMapper;


    @TransactionalAutoRollback
    public Long saveOrUpdate(@NonNull RoleSaveOrUpdateParam roleSaveOrUpdateParam) {
        DictatorRole dictatorRoleEntity = roleSaveOrUpdateParam.to(DictatorRole.class);
        if (dictatorRoleEntity.getId() != null) {
            this.roleMapper.updateByPrimaryKeySelective(dictatorRoleEntity);
        } else {
            this.roleMapper.insertSelective(dictatorRoleEntity);
        }
        return dictatorRoleEntity.getId();
    }

    @TransactionalAutoRollback
    public void updatePermission(RolePermissionUpdateParam rolePermissionUpdateParam) {
        if (rolePermissionUpdateParam.getResourceIdList().isEmpty()) {
            return;
        }
        //清空
        this.roleResourceMapper.deleteByRoleId(rolePermissionUpdateParam.getRoleId());
        //insert
        rolePermissionUpdateParam.getResourceIdList().stream()
                .map(resourceId -> {
                    DictatorRoleResource rr = new DictatorRoleResource();
                    rr.setRoleId(rolePermissionUpdateParam.getRoleId());
                    rr.setResourceId(resourceId);
                    return rr;
                })
                .forEach(e -> this.roleResourceMapper.insertSelective(e));
    }

    @TransactionalAutoRollback
    public void delete(@NonNull Long roleId) {
        //删除角色
        this.roleMapper.deleteByPrimaryKey(roleId);
        //删除角色和用户的关联
        this.userRoleMapper.deleteByRoleId(roleId);
        //删除角色和资源的关联
        this.roleResourceMapper.deleteByRoleId(roleId);
    }

    public DictatorRoleDto findDetail(@NonNull Long roleId) {
        //角色基本信息
        Optional<DictatorRole> roleOpt = this.roleMapper.findById(roleId);
        if (!roleOpt.isPresent()) {
            return null;
        }
        DictatorRoleDto roleDto = BeanConverter.from(roleOpt.get())
                .to(DictatorRoleDto.class);

        roleDto.setPermissionList(this.findPermission(roleDto.getId(), 0L));
        List<Long> checkedIdList = this.resourceMapper.findByRoleIdList(Collections.singletonList(roleId)).stream()
                .map(DictatorResource::getId)
                .collect(Collectors.toList());
        roleDto.setCheckedPermissionIdList(checkedIdList);
        return roleDto;
    }

    private List<DictatorResourceDto> findPermission(@NonNull Long roleId, @NonNull Long parentId) {
        List<DictatorResourceDto> allPermission = BeanConverter.from(this.resourceMapper.findByParentId(parentId))
                .toList(DictatorResourceDto.class);
        //角色拥有的权限
        List<DictatorResourceDto> dictatorResourceDtoList = BeanConverter.from(this.resourceMapper.findByRoleIdAndParentId(roleId, parentId))
                .toList(DictatorResourceDto.class);
        //选中
        allPermission.forEach(p -> {
            dictatorResourceDtoList.forEach(res -> p.setChecked(p.getId().equals(res.getId())));
        });
        //下级节点,不一次性取出来是因为懒，不想写
        allPermission.forEach(p -> p.setChildren(this.findPermission(roleId, p.getId())));
        return allPermission;
    }

    public PageInfo<DictatorRoleDto> findPage(@NonNull RoleQueryParam roleQueryParam) {
        PageInfo<DictatorRoleDto> dictatorRoleDtoPageInfo = this.roleMapper.find(roleQueryParam)
                .toPageInfo();
        if (dictatorRoleDtoPageInfo.getList().isEmpty()) {
            return dictatorRoleDtoPageInfo;
        }
        List<Long> roleIdList = dictatorRoleDtoPageInfo.getList().stream()
                .map(DictatorRoleDto::getId)
                .collect(Collectors.toList());

        List<DictatorResourcePermissionDto> resourcePermissionDtoList = this.resourceMapper.findPermissionByRoleIdList(roleIdList);
        dictatorRoleDtoPageInfo.getList()
                .forEach(role -> resourcePermissionDtoList.stream()
                        .filter(rp -> role.getId().equals(rp.getRoleId()))
                        .findFirst()
                        .ifPresent(dictatorResourcePermissionDto -> role.setPermissions(dictatorResourcePermissionDto.getPermissions())));
        return dictatorRoleDtoPageInfo;
    }
}
