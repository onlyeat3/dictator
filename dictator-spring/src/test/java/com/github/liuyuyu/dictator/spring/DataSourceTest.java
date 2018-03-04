package com.github.liuyuyu.dictator.spring;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author liuyuyu
 */
@ContextConfiguration("classpath:/spring.xml")
public class DataSourceTest extends AbstractJUnit4SpringContextTests {
    @Autowired
    private DataSource dataSource;

    @Test
    public void testDataSource() throws SQLException {
        Connection connection = this.dataSource.getConnection();
        Assert.assertNotNull(connection);
    }
}
