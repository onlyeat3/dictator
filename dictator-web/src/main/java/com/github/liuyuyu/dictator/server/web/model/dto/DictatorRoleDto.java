package com.github.liuyuyu.dictator.server.web.model.dto;

import lombok.Data;

import java.util.Date;

@Data
public class DictatorRoleDto {
    /**
     * 自增主键
     */
    private Long id;

    /**
     * 角色名
     */
    private String roleName;
    /**
     * 已有权限资源名
     */
    private String permissions;

    /**
     * 创建时间
     */
    private Date createdAt;

    /**
     * 最后更新时间
     */
    private Date updatedAt;
}