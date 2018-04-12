package com.github.liuyuyu.dictator.server.web.model.param;

import com.github.liuyuyu.dictator.common.Convertible;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author liuyuyu
 */
@Data
public class ResourceSaveOrUpdateParam implements Convertible {
    /**
     * 自增主键
     */
    private Long id;

    /**
     * 资源名
     */
    @NotNull private String resourceName;

    /**
     * 资源类型：菜单、按钮
     */
    @NotNull private Integer resourceType;

    /**
     * 父节点ID
     */
    @NotNull private Long parentId;

    /**
     * 目标URI
     */
    @NotNull private String targetUri;

    /**
     * 后台操作人ID
     */
    private Long operatorId;

    /**
     * 操作者IP
     */
    private String operatorIp;
}
