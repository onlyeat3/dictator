package com.github.liuyuyu.dictator.server.model.entity;

import javax.persistence.*;
import lombok.*;

@Data
@Table(name = "dictator_role_resource")
public class DictatorRoleResource {
    /**
     * 角色ID
     */
    @Column(name = "role_id")
    private Long roleId;

    /**
     * 资源ID
     */
    @Column(name = "resource_id")
    private Long resourceId;
}