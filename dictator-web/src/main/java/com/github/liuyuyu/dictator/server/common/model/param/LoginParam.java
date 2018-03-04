package com.github.liuyuyu.dictator.server.common.model.param;

import lombok.Data;

/**
 * @author liuyuyu
 */
@Data
public class LoginParam {
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;

    public static LoginParam of() {
        return new LoginParam();
    }
}
