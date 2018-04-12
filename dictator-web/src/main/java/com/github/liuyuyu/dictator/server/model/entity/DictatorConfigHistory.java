package com.github.liuyuyu.dictator.server.model.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name = "dictator_config_history")
public class DictatorConfigHistory {
    /**
     * 自增主键
     */
    @Id
    private Long id;

    /**
     * 应用（服务）ID
     */
    @Column(name = "app_id")
    private String appId;
    /**
     * 配置表ID
     */
    @Column(name = "config_id")
    private Long configId;

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

    /**
     * profile_id
     */
    @Column(name = "profile_id")
    private Long profileId;

    /**
     * 分组ID
     */
    @Column(name = "group_id")
    private Long groupId;
}