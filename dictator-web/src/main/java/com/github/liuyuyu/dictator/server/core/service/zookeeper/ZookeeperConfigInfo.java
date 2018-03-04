package com.github.liuyuyu.dictator.server.core.service.zookeeper;

import lombok.Data;

/*
 * zk内缓存的值
 * @author liuyuyu
 */
@Data
public class ZookeeperConfigInfo {
    /**
     * 配置值
     */
    private String value;
    /**
     * 上次更新时间
     */
    private Long lastUpdatedTime;
}
