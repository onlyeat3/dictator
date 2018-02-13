package com.github.liuyuyu.dictator.server.config;

import com.github.liuyuyu.dictator.server.security.DictatorUserService;
import com.github.liuyuyu.dictator.server.security.DictatorUsernamePasswordFilter;
import com.github.liuyuyu.dictator.server.security.DictatorWebAccessDecisionManager;
import com.github.liuyuyu.dictator.server.security.DictatorWebLoginFailHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.ArrayList;
import java.util.List;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private DictatorWebAccessDecisionManager dictatorWebAccessDecisionManager;
    @Autowired private DictatorWebLoginFailHandler dictatorWebLoginFailHandler;

    @Bean
    public UserDetailsService customUserService() { //注册UserDetailsService 的bean
        return new DictatorUserService();
    }

    @Bean
    public DictatorWebLoginFailHandler dictatorWebLoginFailHandler(){
        return new DictatorWebLoginFailHandler();
    }

    @Bean
    public DictatorUsernamePasswordFilter dictatorUsernamePasswordFilter() throws Exception {
        DictatorUsernamePasswordFilter dictatorUsernamePasswordFilter = new DictatorUsernamePasswordFilter();
        dictatorUsernamePasswordFilter.setAuthenticationFailureHandler(this.dictatorWebLoginFailHandler);
        dictatorUsernamePasswordFilter.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/user/login"));
        dictatorUsernamePasswordFilter.setAuthenticationManager(authenticationManagerBean());
        return dictatorUsernamePasswordFilter;
    }

    @Bean
    public DictatorWebAccessDecisionManager dictatorWebAccessDecisionManager() {
        DictatorWebAccessDecisionManager dictatorWebAccessDecisionManager = new DictatorWebAccessDecisionManager();
        List<String> excludeList = new ArrayList<>();
        excludeList.add("/user/login");
        excludeList.add("/user/loginFail");
        excludeList.add("/");
        dictatorWebAccessDecisionManager.setExcludeUrlPatternList(excludeList);
        return dictatorWebAccessDecisionManager;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserService())
                .passwordEncoder(this.passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().and()
                .csrf().disable()
                .addFilterBefore(this.dictatorUsernamePasswordFilter(),UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .accessDecisionManager(this.dictatorWebAccessDecisionManager)
                .anyRequest().authenticated() //任何请求,登录后可以访问
                .and()
                .formLogin()
                .failureHandler(this.dictatorWebLoginFailHandler)
                .loginPage("/user/login")
                .permitAll() //登录页面用户任意访问
                .and()
                .logout().permitAll() //注销行为任意访问
        ;
    }
}