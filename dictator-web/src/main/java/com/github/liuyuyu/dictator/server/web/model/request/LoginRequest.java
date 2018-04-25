package com.github.liuyuyu.dictator.server.web.model.request;

import com.github.liuyuyu.dictator.common.Convertible;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author liuyuyu
 */
@Data
public class LoginRequest implements Convertible {
    /**
     * 用户名
     */
    @NotBlank private String username;
    /**
     * 密码
     */
    private String password;
}
