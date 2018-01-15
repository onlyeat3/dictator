package com.github.liuyuyu.dictator.core;

import com.github.liuyuyu.dictator.common.exception.PropertyMissException;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.apache.curator.RetryPolicy;
import org.apache.curator.retry.RetryForever;
import org.reflections.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

/*
 * @author liuyuyu
 */
@Data
public class ZkProperties {
    /**
     * zookeeper地址
     */
    private String zkAddress = StringUtils.EMPTY;
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
     * 重试策略(默认一秒重试一次，连上为止)
     */
    private RetryPolicy retryPolicy = new RetryForever(1000);

    public String getZkAddress() {
        if(StringUtils.isNotBlank(this.zkAddress)){
            return this.zkAddress;
        }else{
            throw PropertyMissException.of("zkAddress");
        }
    }
    public static List<String> getPropertiesNames(){
        return ReflectionUtils.getAllFields(ZkProperties.class).stream()
                .map(Field::getName)
                .collect(Collectors.toList());
    }
}
