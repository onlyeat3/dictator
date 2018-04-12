package com.github.liuyuyu.dictator.server.core.service.database;

import com.github.liuyuyu.dictator.common.model.dto.DictatorValueResponse;
import com.github.liuyuyu.dictator.server.core.service.AbstractConfigReadServiceTest;
import com.github.liuyuyu.dictator.server.core.service.param.CommonParam;
import com.github.liuyuyu.dictator.server.utils.ParamBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * @author liuyuyu
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DataBaseConfigReadServiceTest extends AbstractConfigReadServiceTest {
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

    @Test
    public void findAll() {
        super.findAll();
    }

    @Override
    public void setConfigReadService() {
        super.configReadService = this.dataBaseConfigService;
    }

    @Test
    public void findAllValid() {
    }

    @Test
    public void findLastHourInvalid() {
    }

    @Test
    public void findPageValid() {
        CommonParam commParam = new CommonParam();
        commParam.setAppId("app");
        commParam.setProfile("dev");
        Map<String, String> all = this.dataBaseConfigService.findAll(commParam);
        all.keySet()
                .forEach(k->{
                    String v = all.get(k);
                    System.out.println(k+":"+v);
                });

    }
}