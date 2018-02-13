package com.github.liuyuyu.dictator.server.core.service;

import com.github.liuyuyu.dictator.common.model.dto.DictatorValueResponse;
import com.github.liuyuyu.dictator.server.utils.ParamBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * @author liuyuyu
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ListableConfigReadServiceTest {
    @Autowired
    private ListableConfigReadService listableConfigReadService;

    @Test
    public void find() {
        DictatorValueResponse dictatorValueResponse = this.listableConfigReadService.find(ParamBuilder.buildCommonParam());
        assertNotNull(dictatorValueResponse);
        assertNotNull(dictatorValueResponse.getValue());
    }

    @Test
    public void exists() {
        boolean isExists = this.listableConfigReadService.exists(ParamBuilder.buildCommonParam());
        assertTrue(isExists);
    }
}