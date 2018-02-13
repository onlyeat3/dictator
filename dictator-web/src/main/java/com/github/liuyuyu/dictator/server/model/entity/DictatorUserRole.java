package com.github.liuyuyu.dictator.server.model.entity;

import javax.persistence.*;
import lombok.*;

@Data
@Table(name = "dictator_user_role")
public class DictatorUserRole {
    /**
     * 管理员ID
     */
    @Column(name = "admin_id")
    private Long adminId;

    /**
     * 角色ID
     */
    @Column(name = "role_id")
    private Long roleId;
}