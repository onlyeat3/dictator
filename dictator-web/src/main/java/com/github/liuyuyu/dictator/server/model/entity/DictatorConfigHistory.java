package com.github.liuyuyu.dictator.server.model.entity;

import java.util.Date;
import javax.persistence.*;
import lombok.*;

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
    private Long appId;

    /**
     * APP 代码
     */
    @Column(name = "app_code")
    private String appCode;

    /**
     * 配置表ID
     */
    @Column(name = "config_id")
    private Long configId;

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
     * 备注
     */
    private String remark;

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