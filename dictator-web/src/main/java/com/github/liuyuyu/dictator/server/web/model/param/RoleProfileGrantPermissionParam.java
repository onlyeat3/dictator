package com.github.liuyuyu.dictator.server.web.model.param;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class RoleProfileGrantPermissionParam {
    /**
     * 角色ID
     */
    @NotNull private Long roleId;
    /**
     * 环境ID
     */
    @NotNull @NotEmpty
    private List<Long> profileIdList;
    /**
     * 操作者ID
     */
    private Long operatorId;

    /**
     * 操作者IP
     */
    private String operatorIp;
}
