package com.github.liuyuyu.dictator.spring;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import java.util.concurrent.TimeUnit;

/*
 * @author liuyuyu
 */
@ContextConfiguration("classpath:/spring.xml")
public class DataSourcePropertiesTest extends AbstractJUnit4SpringContextTests {
    @Autowired
    private DataSourceProperties dataSourceProperties;

    @Test
    public void testInject() throws InterruptedException {
        System.out.println(this.dataSourceProperties);
        TimeUnit.SECONDS.sleep(10);
        System.out.println(this.dataSourceProperties);
        Assert.assertNotNull(this.dataSourceProperties.getUrl());
    }
}
