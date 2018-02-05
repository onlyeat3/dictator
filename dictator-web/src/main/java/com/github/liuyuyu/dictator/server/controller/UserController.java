package com.github.liuyuyu.dictator.server.controller;

import com.github.liuyuyu.dictator.common.model.response.DataWrapper;
import com.github.liuyuyu.dictator.server.model.request.LoginRequest;
import com.github.liuyuyu.dictator.server.model.response.UserInfoResponse;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.UUID;

/**
 * @author liuyuyu
 */
@RequestMapping("/user")
@RestController
public class UserController {

    @RequestMapping("/login")
    public DataWrapper login(@RequestBody @Valid LoginRequest loginRequest){
        String token = UUID.randomUUID().toString().replaceAll("-", "");
        return DataWrapper.from(token);
    }

    @RequestMapping("/info")
    public DataWrapper showInfo(){
        UserInfoResponse userInfoResponse = UserInfoResponse.of();
        userInfoResponse.setName("admin");
        userInfoResponse.setRoles(Arrays.asList("admin","admini","adminii"));
        return DataWrapper.from(userInfoResponse);
    }
}
