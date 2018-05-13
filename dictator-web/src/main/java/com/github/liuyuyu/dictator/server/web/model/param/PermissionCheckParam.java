package com.github.liuyuyu.dictator.server.web.model.param;

import lombok.Data;

import java.util.List;

@Data
public class PermissionCheckParam {
    /**
     * 角色ID
     */
    private List<Long> roleIdList;
    /**
     * 配置ID
     */
    private Long configId;
}
