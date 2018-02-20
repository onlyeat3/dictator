package com.github.liuyuyu.dictator.server.config;

import com.github.liuyuyu.dictator.server.common.interceptor.LoginInterceptor;
import com.github.liuyuyu.dictator.server.common.mvc.CurrentUserArgumentResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.Arrays;
import java.util.List;

/**
 * @author liuyuyu
 */
@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {
    @Autowired private CurrentUserArgumentResolver currentUserArgumentResolver;
    @Autowired private Environment environment;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        boolean isDev = Arrays.asList(environment.getActiveProfiles()).contains("dev");
        if(isDev){
            registry.addMapping("/**")
                    .allowedOrigins("http://localhost:9527");
        }
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(this.currentUserArgumentResolver);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(LoginInterceptor.of())
                .excludePathPatterns("/user/login")
                .excludePathPatterns("/dictator/config/**");
    }
}
