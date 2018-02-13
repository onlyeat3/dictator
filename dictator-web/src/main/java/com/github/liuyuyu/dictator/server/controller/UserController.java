package com.github.liuyuyu.dictator.server.controller;

import com.github.liuyuyu.dictator.common.model.response.DataWrapper;
import com.github.liuyuyu.dictator.server.common.model.request.LoginRequest;
import com.github.liuyuyu.dictator.server.common.model.response.UserInfoResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.WebAttributes;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.UUID;

/**
 * @author liuyuyu
 */
@RequestMapping("/user")
@RestController
public class UserController {

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public DataWrapper login(@RequestBody @Valid LoginRequest loginRequest) {
        String token = UUID.randomUUID().toString().replaceAll("-", "");
        return DataWrapper.from(token);
    }

    @RequestMapping("/loginFail")
    public DataWrapper loginFail(HttpServletRequest request) {
        Throwable e = (Throwable) request.getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
        DataWrapper dataWrapper = DataWrapper.of();
        String msg = "未知原因导致登录失败";
        String code = "UNKNOWN_FAIL";
        if(e != null){
            if(e instanceof UsernameNotFoundException || e instanceof BadCredentialsException){
                code = "INCORRECT_USERNAME_OR_PASSWORD";
                msg = "用户名或密码错误";
            }
        }
        dataWrapper.setMsg(msg);
        dataWrapper.setCode(code);
        dataWrapper.setSuccess(Boolean.FALSE);
        return dataWrapper;
    }

    @RequestMapping("/info")
    public DataWrapper showInfo() {
        UserInfoResponse userInfoResponse = UserInfoResponse.of();
        userInfoResponse.setName("admin");
        userInfoResponse.setRoles(Arrays.asList("admin", "admini", "adminii"));
        return DataWrapper.from(userInfoResponse);
    }
}
