package com.github.liuyuyu.dictator.server.common.interceptor;

import com.github.liuyuyu.dictator.server.common.constant.UserConstants;
import com.github.liuyuyu.dictator.server.common.exception.enums.UserErrorMessageEnum;
import com.github.liuyuyu.dictator.server.common.model.dto.DictatorUserDto;
import com.github.liuyuyu.dictator.server.common.security.TokenManger;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * @author liuyuyu
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {
    public static LoginInterceptor of() {
        return new LoginInterceptor();
    }

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        //OPTIONS返回true
        if(httpServletRequest.getMethod().equalsIgnoreCase(HttpMethod.OPTIONS.name())){
            return true;
        }
        String token = httpServletRequest.getHeader(UserConstants.TOKEN_NAME);
        if(token == null){
            token = httpServletRequest.getParameter(UserConstants.TOKEN_NAME);
        }
        if(token == null){
            throw UserErrorMessageEnum.MISS_TOKEN.getServiceException();
        }
        DictatorUserDto dictatorUserDto = TokenManger.get(token);
        if(dictatorUserDto == null){
            throw UserErrorMessageEnum.INVALID_TOKEN.getServiceException();
        }else{
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
