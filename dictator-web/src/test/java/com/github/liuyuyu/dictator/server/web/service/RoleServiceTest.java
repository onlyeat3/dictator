package com.github.liuyuyu.dictator.server.web.service;

import com.github.liuyuyu.dictator.server.AbstractSpringBootTest;
import com.github.liuyuyu.dictator.server.constant.TestConstant;
import com.github.liuyuyu.dictator.server.mapper.DictatorResourceMapper;
import com.github.liuyuyu.dictator.server.model.entity.DictatorResource;
import com.github.liuyuyu.dictator.server.web.model.param.RolePermissionUpdateParam;
import com.github.liuyuyu.dictator.server.web.model.param.RoleSaveParam;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author liuyuyu
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RoleServiceTest extends AbstractSpringBootTest {
    @Autowired private DictatorResourceMapper resourceMapper;
    @Autowired private RoleService roleService;
    public static Long roleId;

    @Test
    public void test1saveOrUpdate() {
        RoleSaveParam roleSaveParam = RoleSaveParam.of();
        roleSaveParam.setRoleName("XXX");
        roleSaveParam.setOperatorId(TestConstant.defaultUserId);
        roleSaveParam.setOperatorIp(TestConstant.IP);
        roleId = this.roleService.saveOrUpdate(roleSaveParam);
    }

    @Test
    public void test2updatePermission() {
        List<Long> resourceIdList = this.resourceMapper.findAll().stream()
                .map(DictatorResource::getId)
                .collect(Collectors.toList());
        RolePermissionUpdateParam param = RolePermissionUpdateParam.of();
        param.setRoleId(roleId);
        param.setResourceIdList(resourceIdList);
        this.roleService.updatePermission(param);
    }

    @Test
    public void test3delete() {
    }

    @Test
    public void test4findDetail() {
    }

    @Test
    public void find5Page() {
    }
}