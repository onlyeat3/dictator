package com.github.liuyuyu.dictator.server.web.model.param;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author liuyuyu
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ResourceQueryParam extends PageParam {
    public static ResourceQueryParam of() {
        return new ResourceQueryParam();
    }
}
