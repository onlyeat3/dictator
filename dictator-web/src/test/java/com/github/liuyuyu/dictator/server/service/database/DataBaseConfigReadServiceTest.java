package com.github.liuyuyu.dictator.server.service.database;

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
public class DataBaseConfigReadServiceTest {
    @Autowired
    private DataBaseConfigReadService dataBaseConfigService;

    @Test
    public void find() {
        DictatorValueResponse dictatorValueResponse = this.dataBaseConfigService.find(ParamBuilder.buildCommonParam());
        assertNotNull(dictatorValueResponse);
        assertNotNull(dictatorValueResponse.getValue());
    }

    @Test
    public void exists() {
        boolean isExists = this.dataBaseConfigService.exists(ParamBuilder.buildCommonParam());
        assertTrue(isExists);
    }

}