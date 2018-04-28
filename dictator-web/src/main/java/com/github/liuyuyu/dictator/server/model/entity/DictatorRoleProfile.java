package com.github.liuyuyu.dictator.server.model.entity;

import java.util.Date;
import javax.persistence.*;
import lombok.*;

@Data
@Table(name = "dictator_role_profile")
public class DictatorRoleProfile {
    /**
     * 角色ID
     */
    @Column(name = "role_id")
    private Long roleId;

    /**
     * 环境ID
     */
    @Column(name = "profile_id")
    private Long profileId;

    /**
     * 创建时间
     */
    @Column(name = "created_time")
    private Date createdTime;

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