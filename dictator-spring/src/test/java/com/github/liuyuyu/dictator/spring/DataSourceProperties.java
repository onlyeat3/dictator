package com.github.liuyuyu.dictator.spring;

import com.github.liuyuyu.dictator.spring.annotation.AutoRefreshValue;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/*
 * @author liuyuyu
 */
@Data
@Component
@AutoRefreshValue
public class DataSourceProperties {
    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;
}
