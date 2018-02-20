package com.github.liuyuyu.dictator.server.common.mvc;

import com.github.liuyuyu.dictator.server.common.constant.UserConstants;
import com.github.liuyuyu.dictator.server.common.exception.ServiceException;
import com.github.liuyuyu.dictator.server.common.exception.enums.UserErrorMessageEnum;
import com.github.liuyuyu.dictator.server.common.model.dto.DictatorUserDto;
import com.github.liuyuyu.dictator.server.common.security.TokenManger;
import com.github.liuyuyu.dictator.server.utils.ServletUtils;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

/**
 * @author liuyuyu
 */
@Component
public class CurrentUserArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.hasParameterAnnotation(CurrentUser.class);
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        CurrentUser currentUserAnnotation = methodParameter.getParameterAnnotation(CurrentUser.class);
        HttpServletRequest request = nativeWebRequest.getNativeRequest(HttpServletRequest.class);
        String token = request.getHeader(UserConstants.TOKEN_NAME);
        if(token == null){
            token = request.getParameter(UserConstants.TOKEN_NAME);
        }

        if(token == null){
            throw ServiceException.from(UserErrorMessageEnum.TOKEN_PARAM_NOT_FOUND);
        }
        DictatorUserDto dictatorUserDto = TokenManger.get(token);
        if(dictatorUserDto != null){
            if(dictatorUserDto.getLoginIp() == null){
                String ip = ServletUtils.getRemoteAddress(request);
                dictatorUserDto.setLoginIp(ip);
            }
            return dictatorUserDto;
        }else{
            if(currentUserAnnotation.required()){
                throw ServiceException.from(UserErrorMessageEnum.INVALID_TOKEN);
            }else{
                return null;
            }
        }
    }
}
