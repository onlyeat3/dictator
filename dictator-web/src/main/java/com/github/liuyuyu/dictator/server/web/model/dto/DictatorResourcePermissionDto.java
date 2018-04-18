package com.github.liuyuyu.dictator.server.web.model.dto;

import lombok.Data;

/**
 * 角色的资源权限
 */
@Data
public class DictatorResourcePermissionDto {
    /**
     * 角色ID
     */
    private Long roleId;
    /**
     * 所有权限,逗号分隔
     */
    private String permissions;
}
