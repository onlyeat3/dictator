package com.github.liuyuyu.dictator.test.embed.mysql;

import lombok.Data;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
public class MySQLProperties extends DataSourceProperties {
    public static final String DEFAULT_TEST_DB = "test";
}
