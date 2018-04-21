package com.github.liuyuyu.dictator.server.security;


import com.github.liuyuyu.dictator.server.AbstractSpringBootTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordEncoderTest extends AbstractSpringBootTest {

    @Autowired private PasswordEncoder passwordEncoder;

    @Test
    public void gen(){
        String encode = this.passwordEncoder.encode("123456");
        System.out.println(encode);
    }
}
