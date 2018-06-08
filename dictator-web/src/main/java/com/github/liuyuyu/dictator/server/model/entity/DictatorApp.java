package com.github.liuyuyu.dictator.server.model.entity;

import java.util.Date;
import javax.persistence.*;
import lombok.*;

@Data
@Table(name = "dictator_app")
public class DictatorApp {
    /**
     * 自增主键
     */
    private Long id;

    /**
     * app名称
     */
    @Column(name = "app_name")
    private String appName;

    /**
     * APP ID
     */
    @Column(name = "app_id")
    private String appId;

    /**
     * 所有者邮箱
     */
    @Column(name = "owner_email")
    private String ownerEmail;

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