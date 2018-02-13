package com.github.liuyuyu.dictator.server.common.model.request;

import lombok.Data;

/**
 * @author liuyuyu
 */
@Data
public class LoginRequest {
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
}
