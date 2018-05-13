package com.github.liuyuyu.dictator.server.web.model.param;

import lombok.Data;

import java.util.List;

@Data
public class ProfilePermissionCheckParam {
    /**
     * 角色ID
     */
    private List<Long> roleIdList;
    /**
     * 环境ID
     */
    private Long profileId;
}
