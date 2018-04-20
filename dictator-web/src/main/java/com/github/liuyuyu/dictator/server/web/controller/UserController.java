package com.github.liuyuyu.dictator.server.web.controller;

import com.github.liuyuyu.dictator.common.NamedValue;
import com.github.liuyuyu.dictator.common.model.response.DataWrapper;
import com.github.liuyuyu.dictator.server.web.constant.UserConstants;
import com.github.liuyuyu.dictator.server.web.model.dto.DictatorUserDto;
import com.github.liuyuyu.dictator.server.web.model.param.DictatorUserSaveOrUpdateParam;
import com.github.liuyuyu.dictator.server.web.model.param.LoginParam;
import com.github.liuyuyu.dictator.server.web.model.request.LoginRequest;
import com.github.liuyuyu.dictator.server.web.model.response.UserInfoResponse;
import com.github.liuyuyu.dictator.server.web.mvc.CurrentUser;
import com.github.liuyuyu.dictator.server.web.security.TokenManger;
import com.github.liuyuyu.dictator.server.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * @author liuyuyu
 */
@RequestMapping("/user")
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public DataWrapper login(@RequestBody @Valid LoginRequest loginRequest, HttpSession session) {
        DictatorUserDto dictatorUserDto = this.userService.login(loginRequest.to(LoginParam.class));
        session.setAttribute(UserConstants.CURRENT_USER, dictatorUserDto);
        TokenManger.put(dictatorUserDto);
        return DataWrapper.from(NamedValue.of("token", dictatorUserDto.getToken()));
    }

    @RequestMapping("/info")
    public DataWrapper showInfo(@CurrentUser DictatorUserDto userDto) {
        DictatorUserDto userInfo = this.userService.findUserInfo(userDto.getId());
        UserInfoResponse userInfoResponse = UserInfoResponse.of();
        userInfoResponse.from(userInfo);
        return DataWrapper.from(userInfoResponse);
    }

    @RequestMapping("/saveOrUpdate")
    public DataWrapper saveOrUpdate(@RequestBody @Valid DictatorUserSaveOrUpdateParam userSaveOrUpdateParam){
        DictatorUserDto userDto = userSaveOrUpdateParam.to(DictatorUserDto.class);
        this.userService.saveOrUpdate(userDto);
        return DataWrapper.of();
    }

    @RequestMapping("/listAll")
    public DataWrapper listAll(){
        return DataWrapper.from(this.userService.findAll());
    }
}
