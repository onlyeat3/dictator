package com.github.liuyuyu.dictator.server.web.service;

import com.github.liuyuyu.dictator.common.utils.BeanConverter;
import com.github.liuyuyu.dictator.server.AbstractSpringBootTest;
import com.github.liuyuyu.dictator.server.web.model.dto.DictatorAppDto;
import com.github.liuyuyu.dictator.server.web.model.param.AppSaveOrUpdateParam;
import com.github.liuyuyu.dictator.server.web.model.param.DeleteParam;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertFalse;

/**
 * @author liuyuyu
 */
public class DictatorAppServiceTest extends AbstractSpringBootTest {
    @Autowired private DictatorAppService appService;

    @Test
    public void test1_saveOrUpdate() {
        AppSaveOrUpdateParam appSaveOrUpdateParam = new AppSaveOrUpdateParam();
        appSaveOrUpdateParam.setAppId("dictator-demo");
        appSaveOrUpdateParam.setAppName("dictator");
        appSaveOrUpdateParam.setOwnerEmail("liuyuyu2333@gmail.com");
        appSaveOrUpdateParam.setOperatorId(0L);
        appSaveOrUpdateParam.setOperatorIp("-");
        this.appService.saveOrUpdate(appSaveOrUpdateParam);
    }

    @Test
    public void test2_findAll() {
        List<DictatorAppDto> all = this.appService.findAll();
        assertFalse(all.isEmpty());
    }

    @Test
    public void test3_delete() {
        DictatorAppDto dictatorAppDto = this.appService.findAll().stream()
                .findFirst()
                .orElse(null);
        this.appService.delete(BeanConverter.from(dictatorAppDto).to(DeleteParam.class));
    }
}