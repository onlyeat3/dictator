package com.github.liuyuyu.dictator.server.web.interceptor;

import com.github.liuyuyu.dictator.server.web.constant.UserConstants;
import com.github.liuyuyu.dictator.server.web.exception.enums.UserErrorMessageEnum;
import com.github.liuyuyu.dictator.server.web.model.dto.DictatorUserDto;
import com.github.liuyuyu.dictator.server.web.security.TokenManger;
import com.github.liuyuyu.dictator.server.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/*
 * @author liuyuyu
 */
@Component
public class PermissionCheckInterceptor implements HandlerInterceptor {
    @Autowired private UserService userService;
    public static PermissionCheckInterceptor of() {
        return new PermissionCheckInterceptor();
    }

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        //OPTIONS返回true
        if (httpServletRequest.getMethod().equalsIgnoreCase(HttpMethod.OPTIONS.name())) {
            return true;
        }
        String token = httpServletRequest.getHeader(UserConstants.TOKEN_NAME);
        if (token == null) {
            token = httpServletRequest.getParameter(UserConstants.TOKEN_NAME);
        }
        if (token == null) {
            throw UserErrorMessageEnum.MISS_TOKEN.getServiceException();
        }
        //是否登录
        DictatorUserDto dictatorUserDto = TokenManger.get(token);
        if (dictatorUserDto == null) {
            throw UserErrorMessageEnum.INVALID_TOKEN.getServiceException();
        }
        //GM账号
        if(Objects.equals(dictatorUserDto.getId(), UserConstants.GM_USER_ID)){
            return true;
        }else{
            DictatorUserDto userInfo = this.userService.findUserInfo(dictatorUserDto.getId());
            if(userInfo == null){
                throw UserErrorMessageEnum.UNKNOWN_USER.getServiceException();
            }
            long matchsCount = userInfo.getResourceList().stream()
                    .filter(r -> r.getTargetUri().contains(httpServletRequest.getRequestURI()))
                    .count();
            if(matchsCount < 1){
                throw UserErrorMessageEnum.FORBIDDEN.getServiceException();
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
