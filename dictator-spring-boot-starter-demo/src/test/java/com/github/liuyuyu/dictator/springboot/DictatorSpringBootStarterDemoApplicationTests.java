package com.github.liuyuyu.dictator.springboot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DictatorSpringBootStarterDemoApplicationTests {

    @Autowired
    private DataSourceProperties dataSourceProperties;
    @Autowired private DefaultListableBeanFactory applicationContext;

    @Test
    public void testInject() {
        System.out.println(this.dataSourceProperties);
//		Assert.assertNotNull(this.dataSourceProperties.getUrl());
    }

}
