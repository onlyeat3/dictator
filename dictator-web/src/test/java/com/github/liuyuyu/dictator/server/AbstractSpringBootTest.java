package com.github.liuyuyu.dictator.server;

import org.junit.FixMethodOrder;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author liuyuyu
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest
public class AbstractSpringBootTest {

    public String getAppId(){
        return "dictator-test";
    }

    protected String getProfile() {
        return "test";
    }

    protected Long getLastUpdatedTime() {
        return System.currentTimeMillis();
    }
}
