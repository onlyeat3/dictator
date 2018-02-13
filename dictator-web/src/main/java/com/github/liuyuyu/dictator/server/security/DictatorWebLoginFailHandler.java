package com.github.liuyuyu.dictator.server.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author liuyuyu
 */
public class DictatorWebLoginFailHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        request.setAttribute(WebAttributes.AUTHENTICATION_EXCEPTION,e);
        request.getRequestDispatcher("/user/loginFail").forward(request,response);
    }
}
