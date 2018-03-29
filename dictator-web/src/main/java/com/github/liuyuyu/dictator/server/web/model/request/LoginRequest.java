package com.github.liuyuyu.dictator.server.web.model.request;

import com.github.liuyuyu.dictator.common.Convertible;
import lombok.Data;

/**
 * @author liuyuyu
 */
@Data
public class LoginRequest implements Convertible {
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
}
