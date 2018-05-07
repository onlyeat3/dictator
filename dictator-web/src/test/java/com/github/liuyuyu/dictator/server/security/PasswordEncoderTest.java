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

    @Test
    public void testMatches(){
        String encodedPassword = "$2a$10$7hIfb/Rf5LG0uAY3cgPCS.q12bUrUqOnv5q80SdNi2sYGCGa/Gmc6";
        final boolean matches = this.passwordEncoder.matches("123456", encodedPassword);
        System.out.println(matches);
    }
}
