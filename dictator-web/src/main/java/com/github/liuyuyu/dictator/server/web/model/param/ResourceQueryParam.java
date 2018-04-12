package com.github.liuyuyu.dictator.server.web.model.param;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * @author liuyuyu
 */
@Data
public class ResourceQueryParam{
    public static ResourceQueryParam of() {
        return new ResourceQueryParam();
    }

    /**
     * 父节点ID
     */
    @NotNull private Long parentId;
}
