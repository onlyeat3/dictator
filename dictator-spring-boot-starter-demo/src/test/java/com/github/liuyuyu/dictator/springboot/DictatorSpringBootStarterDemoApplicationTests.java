package com.github.liuyuyu.dictator.springboot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DictatorSpringBootStarterDemoApplicationTests {

    @Autowired
    private DataSourceProperties dataSourceProperties;

    @Test
    public void testInject() throws InterruptedException {
        System.out.println(this.dataSourceProperties);
        TimeUnit.SECONDS.sleep(5);
        System.out.println(this.dataSourceProperties);
//		Assert.assertNotNull(this.dataSourceProperties.getUrl());
    }

}
