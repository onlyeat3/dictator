package com.github.liuyuyu.dictator.server;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("server")
public class VertxProperties {
    private Integer port;
}
