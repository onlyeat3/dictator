package com.github.liuyuyu.dictator.server.web.model.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    /**
     * 权限列表
     */
    private List<DictatorResourceDto> permissionList = new ArrayList<>();
}