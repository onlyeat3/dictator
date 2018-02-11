package com.github.liuyuyu.dictator.client;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/*
 * @author liuyuyu
 */
public class DictatorClientTest {

    private DictatorClient dictatorClient;

    @Before
    public void setUp() {
        DictatorClientProperties properties = new DictatorClientProperties();
        properties.setAppId("app");
        properties.setDeploymentId("dev/db");
        properties.setServerUrl("http://localhost:8080");
        this.dictatorClient = DictatorClient.of(properties);
    }

    @Test
    public void testGet() {
        String jdbcUrl = this.dictatorClient.get("spring.datasource.url");
        assertNotNull(jdbcUrl);
    }

}