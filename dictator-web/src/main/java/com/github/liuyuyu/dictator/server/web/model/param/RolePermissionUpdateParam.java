package com.github.liuyuyu.dictator.server.web.model.param;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * @author liuyuyu
 */
@Data
public class RolePermissionUpdateParam {
    public static RolePermissionUpdateParam of() {
        return new RolePermissionUpdateParam();
    }

    /**
     * 资源ID
     */
    private List<Long> resourceIdList = new ArrayList<>();
    /**
     * 角色ID
     */
    @NotNull private Long roleId;
}
