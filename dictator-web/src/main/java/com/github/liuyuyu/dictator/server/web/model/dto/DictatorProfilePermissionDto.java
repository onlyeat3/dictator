package com.github.liuyuyu.dictator.server.web.model.dto;

import lombok.Data;

/**
 * 角色的环境权限
 */
@Data
public class DictatorProfilePermissionDto {
    /**
     * 角色ID
     */
    private Long roleId;
    /**
     * 所有权限,逗号分隔
     */
    private String profilePermissions;
    /**
     * 环境ID,都好分隔
     */
    private String profileIds;
}
