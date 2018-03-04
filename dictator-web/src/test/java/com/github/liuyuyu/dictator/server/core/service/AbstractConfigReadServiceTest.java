package com.github.liuyuyu.dictator.server.core.service;

import com.github.liuyuyu.dictator.server.core.service.ConfigReadService;
import com.github.liuyuyu.dictator.server.core.service.param.CommonParam;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

import static org.junit.Assert.*;

/*
 * @author liuyuyu
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public abstract class AbstractConfigReadServiceTest {

    protected ConfigReadService configReadService;

    @Before
    public abstract void setConfigReadService();

    public void findAll() {
        CommonParam commonParam = new CommonParam();
        commonParam.setAppId("dictator-demo");
        commonParam.setProfile("dev");
        commonParam.setDeploymentId("db");
        Map<String, String> configMap = this.configReadService.findAll(commonParam);
        assertFalse(configMap.isEmpty());
        commonParam.setLastUpdatedTime(System.currentTimeMillis());
        Map<String, String> nextConfigMap = this.configReadService.findAll(commonParam);
        assertTrue(nextConfigMap.isEmpty());
    }
}