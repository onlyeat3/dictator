package com.github.liuyuyu.dictator.server.common.model.response;

import lombok.Data;

/**
 * @author liuyuyu
 */
@Data(staticConstructor = "of")
public class MenuMetaResponse {
    /**
     * 标题
     */
    private String title;
    /**
     * 图标
     */
    private String icon;
}
