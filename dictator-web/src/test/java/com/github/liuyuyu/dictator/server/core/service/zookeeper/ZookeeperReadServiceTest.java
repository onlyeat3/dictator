package com.github.liuyuyu.dictator.server.core.service.zookeeper;

import com.github.liuyuyu.dictator.server.core.service.AbstractConfigReadServiceTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/*
 * @author liuyuyu
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ZookeeperReadServiceTest extends AbstractConfigReadServiceTest {

    @Autowired
    private ZookeeperConfigService zookeeperConfigService;

    @Override
    public void setConfigReadService() {
        super.configReadService = this.zookeeperConfigService;
    }

    @Test
    public void findAll() {
        super.findAll();
    }
}