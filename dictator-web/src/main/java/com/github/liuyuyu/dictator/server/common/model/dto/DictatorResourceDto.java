package com.github.liuyuyu.dictator.server.common.model.dto;

import com.github.liuyuyu.dictator.common.Convertible;
import com.github.liuyuyu.dictator.server.model.entity.DictatorResource;
import lombok.Data;

/**
 * @author liuyuyu
 */
@Data
public class DictatorResourceDto implements Convertible<DictatorResourceDto,DictatorResource> {
    /**
     * 自增主键
     */
    private Long id;

    /**
     * 资源名
     */
    private String resourceName;

    /**
     * 资源类型：菜单、按钮
     */
    private Integer resourceType;

    /**
     * 父节点ID
     */
    private Long parentId;

    /**
     * 所有上级节点
     */
    private String parentIds;

    /**
     * 目标URI
     */
    private String targetUri;
}
