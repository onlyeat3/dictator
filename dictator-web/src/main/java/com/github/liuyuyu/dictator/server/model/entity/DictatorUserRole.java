package com.github.liuyuyu.dictator.server.model.entity;

import javax.persistence.*;
import lombok.*;

@Data
@Table(name = "dictator_user_role")
public class DictatorUserRole {
    /**
     * 用户ID
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 角色ID
     */
    @Column(name = "role_id")
    private Long roleId;
}