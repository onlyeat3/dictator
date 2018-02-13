package com.github.liuyuyu.dictator.server.core.service.zookeeper;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.apache.curator.RetryPolicy;
import org.apache.curator.retry.RetryOneTime;
import org.springframework.boot.context.properties.ConfigurationProperties;

/*
 * @author liuyuyu
 */
@Data
@ConfigurationProperties(prefix = "dictator.zk")
public class ZkProperties {
    /**
     * 是否启用
     */
    private Boolean enable;
    /**
     * zookeeper地址
     */
    private String address = StringUtils.EMPTY;
    /**
     * 分配的根目录
     */
    private String basePath = StringUtils.EMPTY;
    /**
     * zk session 超时时间(ms)
     */
    private Integer sessionTimeoutMs = Integer.getInteger("curator-default-session-timeout", 60000);
    /**
     * 连接zk超时时间(ms)
     */
    private Integer connectionTimeoutMs = Integer.getInteger("curator-default-connection-timeout", 15000);
    /**
     * 重试策略(curator的start方法不能用重试策略，生效的只有新来的请求，为了保障速度，只重试一次)
     */
    private RetryPolicy retryPolicy = new RetryOneTime(1000);
}
