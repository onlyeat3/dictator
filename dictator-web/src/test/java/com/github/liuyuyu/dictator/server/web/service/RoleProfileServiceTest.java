package com.github.liuyuyu.dictator.server.web.service;

import com.github.liuyuyu.dictator.server.AbstractSpringBootTest;
import com.github.liuyuyu.dictator.server.web.model.param.RoleProfileGrantPermissionParam;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

public class RoleProfileServiceTest extends AbstractSpringBootTest {
    @Autowired private RoleProfileService roleProfileService;

    @Test
    public void grantPermission() {
        RoleProfileGrantPermissionParam param = new RoleProfileGrantPermissionParam();
        param.setRoleId(1L);
        param.setOperatorId(1L);
        param.setOperatorIp("");
        List<Long> profileIdList = Lists.newArrayList(1L,2L,3L,4L);
        param.setProfileIdList(profileIdList);
        this.roleProfileService.grantPermission(param);
    }
}