package com.github.liuyuyu.dictator.server.common.model.request;

import com.github.liuyuyu.dictator.common.Convertible;
import com.github.liuyuyu.dictator.server.common.model.param.LoginParam;
import lombok.Data;

/**
 * @author liuyuyu
 */
@Data
public class LoginRequest implements Convertible<LoginParam> {
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;

}
