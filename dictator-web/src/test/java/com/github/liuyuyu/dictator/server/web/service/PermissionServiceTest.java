package com.github.liuyuyu.dictator.server.web.service;

import com.github.liuyuyu.dictator.server.AbstractSpringBootTest;
import com.github.liuyuyu.dictator.server.web.model.param.PermissionCheckParam;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class PermissionServiceTest extends AbstractSpringBootTest {

    @Autowired private PermissionService permissionService;

    @Test
    public void hasPermission() {
        PermissionCheckParam param = new PermissionCheckParam();
        param.getRoleIdList().add(2L);
        param.setConfigId(35L);
        Assert.assertTrue(this.permissionService.hasPermission(param));
    }
}