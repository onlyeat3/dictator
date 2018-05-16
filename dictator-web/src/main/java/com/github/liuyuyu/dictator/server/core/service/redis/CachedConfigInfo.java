package com.github.liuyuyu.dictator.server.core.service.redis;

import lombok.Data;

/*
 * zk内缓存的值
 * @author liuyuyu
 */
@Data
public class CachedConfigInfo {
    /**
     * 配置值
     */
    private String value;
    /**
     * 上次更新时间
     */
    private Long lastUpdatedTime;
}
