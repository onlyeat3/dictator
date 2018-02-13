package com.github.liuyuyu.dictator.server.model.entity;

import java.util.Date;
import javax.persistence.*;
import lombok.*;

@Data
@Table(name = "dictator_resource")
public class DictatorResource {
    /**
     * 自增主键
     */
    private Long id;

    /**
     * 资源名
     */
    @Column(name = "resource_name")
    private String resourceName;

    /**
     * 资源类型：菜单、按钮
     */
    @Column(name = "resource_type")
    private Byte resourceType;

    /**
     * 父节点ID
     */
    @Column(name = "parent_id")
    private Long parentId;

    /**
     * 所有上级节点
     */
    @Column(name = "parent_ids")
    private String parentIds;

    /**
     * 目标URI
     */
    @Column(name = "target_uri")
    private String targetUri;

    /**
     * 创建时间
     */
    @Column(name = "created_at")
    private Date createdAt;

    /**
     * 最后更新时间
     */
    @Column(name = "updated_at")
    private Date updatedAt;

    /**
     * 后台操作人ID
     */
    @Column(name = "operator_id")
    private Long operatorId;

    /**
     * 操作者IP
     */
    @Column(name = "operator_ip")
    private String operatorIp;
}