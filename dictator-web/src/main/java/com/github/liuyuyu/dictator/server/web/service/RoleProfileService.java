package com.github.liuyuyu.dictator.server.web.service;

import com.github.liuyuyu.dictator.server.mapper.DictatorConfigProfileMapper;
import com.github.liuyuyu.dictator.server.mapper.DictatorRoleProfileMapper;
import com.github.liuyuyu.dictator.server.model.entity.DictatorConfigProfile;
import com.github.liuyuyu.dictator.server.model.entity.DictatorRoleProfile;
import com.github.liuyuyu.dictator.server.web.model.param.RoleProfileGrantPermissionParam;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleProfileService {
    @Autowired
    private DictatorRoleProfileMapper roleProfileMapper;
    @Autowired
    private DictatorConfigProfileMapper profileMapper;

    public void grantPermission(RoleProfileGrantPermissionParam roleProfileGrantPermissionParam) {
        final List<DictatorConfigProfile> profileList = this.profileMapper.findAll();
        final List<Long> allProfileIdList = profileList.stream()
                .map(DictatorConfigProfile::getId)
                .collect(Collectors.toList());
        final List<Long> validProfileIdList = roleProfileGrantPermissionParam.getProfileIdList().stream()
                .filter(allProfileIdList::contains)
                .collect(Collectors.toList());
        this.roleProfileMapper.deleteByRoleId(roleProfileGrantPermissionParam.getRoleId());
        validProfileIdList.stream()
                .map(p -> {
                    DictatorRoleProfile roleProfileEntity = new DictatorRoleProfile();
                    roleProfileEntity.setRoleId(roleProfileGrantPermissionParam.getRoleId());
                    roleProfileEntity.setProfileId(p);
                    roleProfileEntity.setOperatorId(roleProfileGrantPermissionParam.getOperatorId());
                    roleProfileEntity.setOperatorIp(roleProfileGrantPermissionParam.getOperatorIp());
                    return roleProfileEntity;
                })
                .forEach(e -> this.roleProfileMapper.insertSelective(e));
    }
}
