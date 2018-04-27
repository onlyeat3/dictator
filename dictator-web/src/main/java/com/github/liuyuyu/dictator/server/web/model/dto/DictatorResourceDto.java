package com.github.liuyuyu.dictator.server.web.model.dto;

import com.github.liuyuyu.dictator.common.Convertible;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liuyuyu
 */
@Data
public class DictatorResourceDto implements Convertible {
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
     * 资源类型名
     */
    private String resourceTypeName;

    /**
     * 父节点ID
     */
    private Long parentId;

    /**
     * 所有上级节点
     */
    private String parentIds;
    /**
     * 所有上级节点ID
     */
    private List<Long> parentIdList = new ArrayList<>();

    /**
     * 目标URI
     */
    private String targetUri;
    /**
     * 是否选中
     */
    private Boolean checked;
    /**
     * 子节点
     */
    private List<DictatorResourceDto> children = new ArrayList<>();
}
