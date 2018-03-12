package com.github.liuyuyu.dictator.server.model.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name = "dictator_config_profile")
public class DictatorConfigProfile {
    /**
     * 自增主键
     */
    @Id
    private Long id;

    /**
     * 环境名
     */
    @Column(name = "profile_name")
    private String profileName;

    /**
     * 环境代码
     */
    @Column(name = "profile_code")
    private String profileCode;

    /**
     * 环境描述
     */
    @Column(name = "profile_desc")
    private String profileDesc;

    /**
     * 是否启用
     */
    @Column(name = "enable")
    private Boolean enable;

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