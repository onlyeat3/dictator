package com.github.liuyuyu.dictator.server.web.model.param;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Data
public class UpdatePasswordParam {
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 旧密码
     */
    @NotBlank private String oldPassword;
    /**
     * 新密码
     */
    @NotBlank @Size(min = 6,max = 20)
    private String newPassword;
    /**
     * 确认密码
     */
    @NotBlank private String confirmPassword;
}
