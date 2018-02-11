package com.github.liuyuyu.dictator.server.model.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name = "dictator_config_environment")
public class DictatorConfigEnvironment {
    /**
     * 自增主键
     */
    private Long id;

    /**
     * 环境名
     */
    @Column(name = "env_name")
    private String envName;

    /**
     * 环境代码
     */
    @Column(name = "env_code")
    private String envCode;

    /**
     * 环境描述
     */
    @Column(name = "env_desc")
    private String envDesc;

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