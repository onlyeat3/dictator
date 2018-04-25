package com.github.liuyuyu.dictator.server.web.model.dto;

import lombok.Data;

@Data
public class DictatorUserRoleDto {
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 角色ID
     */
    private Long roleId;
}
