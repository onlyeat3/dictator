package com.github.liuyuyu.dictator.server.web.model.param;

import lombok.Data;

@Data
public class UpdatePasswordParam {
    /**
     * 旧密码
     */
    private String oldPassword;
    /**
     * 新密码
     */
    private String newPassword;
}
