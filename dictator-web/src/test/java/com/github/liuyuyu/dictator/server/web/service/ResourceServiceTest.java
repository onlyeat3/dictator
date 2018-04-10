package com.github.liuyuyu.dictator.server.web.service;

import com.github.liuyuyu.dictator.server.AbstractSpringBootTest;
import com.github.liuyuyu.dictator.server.constant.TestConstant;
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

import java.util.Optional;
import java.util.stream.IntStream;

import static org.junit.Assert.assertFalse;

/**
 * @author liuyuyu
 */
@Slf4j
public class ResourceServiceTest extends AbstractSpringBootTest {
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
        param.setOperatorId(TestConstant.defaultUserId);
        param.setOperatorIp(TestConstant.IP);
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
        param.setOperatorId(TestConstant.defaultUserId);
        param.setOperatorIp(TestConstant.IP);
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
    public void test4findPage() {
        IntStream.range(0,99)
                .boxed()
                .map(i->{
                    ResourceSaveOrUpdateParam param = new ResourceSaveOrUpdateParam();
                    param.setResourceName("资源"+i);
                    param.setResourceType(ResourceTypeEnum.MENU.getValue());
                    param.setParentId(0L);
                    param.setTargetUri("/"+i);
                    param.setOperatorId((long) i);
                    param.setOperatorIp(TestConstant.IP);
                    return param;
                })
                .forEach(r-> this.resourceService.saveOrUpdate(r));
        ResourceQueryParam param = ResourceQueryParam.of();
        PageInfo<DictatorResourceDto> page = this.resourceService.findPage(param);
        assertFalse(page.getList().isEmpty());
    }

}