package com.github.liuyuyu.dictator.server.model.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name = "dictator_config_history")
public class DictatorConfigHistory {
    /**
     * 自增主键
     */
    private Long id;

    /**
     * 应用（服务）ID
     */
    @Column(name = "app_id")
    private String appId;

    /**
     * 部署的ID（例如：机器名+环境名）
     */
    @Column(name = "deployment_id")
    private String deploymentId;

    /**
     * 环境
     */
    private String profile;

    /**
     * 配置名
     */
    @Column(name = "config_name")
    private String configName;

    /**
     * 配置值
     */
    @Column(name = "config_value")
    private String configValue;

    /**
     * 配置版本
     */
    private Long version;

    /**
     * 创建时间
     */
    @Column(name = "created_time")
    private Date createdTime;

    /**
     * 修改时间
     */
    @Column(name = "updated_time")
    private Date updatedTime;

    /**
     * 记录创建时间
     */
    @Column(name = "history_created_time")
    private Date historyCreatedTime;

    /**
     * 操作者ID
     */
    @Column(name = "operator_id")
    private Long operatorId;

    /**
     * 操作者IP
     */
    @Column(name = "operator_ip")
    private String operatorIp;
}