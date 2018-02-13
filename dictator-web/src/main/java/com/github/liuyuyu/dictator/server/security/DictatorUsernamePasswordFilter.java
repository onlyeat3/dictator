package com.github.liuyuyu.dictator.server.security;

import com.github.liuyuyu.dictator.common.utils.JsonUtils;
import com.github.liuyuyu.dictator.server.common.model.request.LoginRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author liuyuyu
 */
@Slf4j
public class DictatorUsernamePasswordFilter extends UsernamePasswordAuthenticationFilter {

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            String requestBody = org.apache.commons.io.IOUtils.toString(request.getReader());
            LoginRequest loginRequest = JsonUtils.toObject(requestBody, LoginRequest.class);
            if (loginRequest != null) {
                String username = loginRequest.getUsername().trim();
                UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, loginRequest.getPassword());
                this.setDetails(request, token);
                return this.getAuthenticationManager().authenticate(token);
            }
        } catch (IOException e) {
            throw new InternalAuthenticationServiceException("invalid request body", e);
        }
        return null;
    }
}
