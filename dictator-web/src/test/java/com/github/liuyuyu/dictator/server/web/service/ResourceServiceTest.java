package com.github.liuyuyu.dictator.server.web.service;

import com.github.liuyuyu.dictator.server.mapper.DictatorResourceMapper;
import com.github.liuyuyu.dictator.server.model.entity.DictatorResource;
import com.github.liuyuyu.dictator.server.web.model.dto.DictatorResourceDto;
import com.github.liuyuyu.dictator.server.web.model.param.ResourceQueryParam;
import com.github.liuyuyu.dictator.server.web.model.param.ResourceSaveOrUpdateParam;
import com.github.liuyuyu.dictator.server.web.model.type.ResourceTypeEnum;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import static org.junit.Assert.assertFalse;

/**
 * @author liuyuyu
 */
@Slf4j
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest
public class ResourceServiceTest {
    @Autowired private ResourceService resourceService;
    @Autowired private DictatorResourceMapper resourceMapper;
    private static Long resourceId;


    @Test
    public void test1save() {
        ResourceSaveOrUpdateParam param = new ResourceSaveOrUpdateParam();
        param.setResourceName("登录");
        param.setResourceType(ResourceTypeEnum.MENU.getValue());
        param.setParentId(0L);
        param.setTargetUri("/login");
        param.setOperatorId(1L);
        param.setOperatorIp("0.0.0.0");
        resourceId = this.resourceService.saveOrUpdate(param);
    }

    @Test
    public void test2update() {
        ResourceSaveOrUpdateParam param = new ResourceSaveOrUpdateParam();
        param.setId(resourceId);
        param.setResourceName("录登");
        param.setResourceType(ResourceTypeEnum.BUTTON.getValue());
        param.setParentId(-1L);
        param.setTargetUri("/signin");
        param.setOperatorId(-1L);
        param.setOperatorIp("6.6.6.6");
        this.resourceService.saveOrUpdate(param);
    }

    @Test
    public void test3delete() {
        Optional<DictatorResource> resourceEntityOptBefore = this.resourceMapper.findById(resourceId);
        DictatorResource resourceEntityBefore = resourceEntityOptBefore.orElseThrow(() -> new RuntimeException("null"));
        log.info("before:{}",resourceEntityBefore);
        this.resourceService.delete(resourceId);
        Optional<DictatorResource> resourceEntityOptAfter = this.resourceMapper.findById(resourceId);
        assertFalse(resourceEntityOptAfter.isPresent());
    }

    @Test
    public void test4FindByParentIdList() {
        IntStream.range(0,99)
                .boxed()
                .map(i->{
                    ResourceSaveOrUpdateParam param = new ResourceSaveOrUpdateParam();
                    param.setResourceName("资源"+i);
                    param.setResourceType(ResourceTypeEnum.MENU.getValue());
                    param.setParentId(0L);
                    param.setTargetUri("/"+i);
                    param.setOperatorId((long) i);
                    param.setOperatorIp("0.0.0."+i);
                    return param;
                })
                .forEach(r-> this.resourceService.saveOrUpdate(r));
        List<DictatorResourceDto> resourceDtoList = this.resourceService.findByParentId(Collections.singletonList(0L));
        assertFalse(resourceDtoList.isEmpty());
    }

}