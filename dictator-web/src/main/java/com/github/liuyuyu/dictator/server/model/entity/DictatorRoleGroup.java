package com.github.liuyuyu.dictator.server.model.entity;

import javax.persistence.*;
import lombok.*;

@Data
@Table(name = "dictator_role_group")
public class DictatorRoleGroup {
    /**
     * 角色ID
     */
    @Column(name = "role_id")
    private Long roleId;

    /**
     * 分组ID
     */
    @Column(name = "group_id")
    private Long groupId;
}