package com.github.liuyuyu.dictator.server.model.entity;

import java.util.Date;
import javax.persistence.*;
import lombok.*;

@Data
@Table(name = "dictator_config")
public class DictatorConfig {
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