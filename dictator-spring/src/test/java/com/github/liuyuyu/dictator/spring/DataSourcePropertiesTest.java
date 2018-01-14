package com.github.liuyuyu.dictator.spring;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

/*
 * @author liuyuyu
 */
@ContextConfiguration("classpath:/spring.xml")
public class DataSourcePropertiesTest extends AbstractJUnit4SpringContextTests {
    @Autowired
    private DataSourceProperties dataSourceProperties;

    @Test
    public void testInject() {
        System.out.println(this.dataSourceProperties);
        Assert.assertNotNull(this.dataSourceProperties.getUrl());
    }
}
