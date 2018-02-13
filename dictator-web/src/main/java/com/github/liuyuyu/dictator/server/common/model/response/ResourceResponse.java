package com.github.liuyuyu.dictator.server.common.model.response;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liuyuyu
 */
@Data(staticConstructor = "of")
public class ResourceResponse {
    /**
     * 主键
     */
    private Long id;
    /**
     * 菜单名
     */
    private String name;
    /**
     * 显示名称
     */
    private String title;
    /**
     * 打开的地址
     */
    private String path;
    /**
     * 组件名
     */
    private String component;
    /**
     * 重定向地址
     */
    private String redirect;
    /**
     * 上一级ID
     */
    private Long parentId;
    /**
     * 节点深度
     */
    private Integer level;
    /**
     * 总是显示根节点
     */
    private Boolean alwaysShow;
    /**
     * 是否隐藏当前节点
     */
    private Boolean hidden;
    /**
     * 显示信息
     */
    private MenuMetaResponse meta;
    /**
     * 子节点ID
     */
    private List<ResourceResponse> children = new ArrayList<>();
}
