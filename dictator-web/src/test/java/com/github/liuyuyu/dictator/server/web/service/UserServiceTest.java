package com.github.liuyuyu.dictator.server.web.service;

import com.github.liuyuyu.dictator.server.AbstractSpringBootTest;
import com.github.liuyuyu.dictator.server.web.model.param.UpdatePasswordParam;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class UserServiceTest extends AbstractSpringBootTest {
    @Autowired private UserService userService;

    @Test
    public void updatePassword() {
        String newPassword = "999999";
        UpdatePasswordParam param = new UpdatePasswordParam();
        param.setUserId(1L);
        param.setOldPassword("123456");
        param.setNewPassword(newPassword);
        param.setConfirmPassword(newPassword);
        this.userService.updatePassword(param);
    }
}