package com.github.liuyuyu.dictator.server.web.model.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ConfigGroupDto {
    /**
     * 自增主键
     */
    private Long id;

    /**
     * 分组名
     */
    private String groupName;

    /**
     * 创建时间
     */
    private Date createdAt;

    /**
     * 最后更新时间
     */
    private Date updatedAt;

    /**
     * 后台操作人ID
     */
    private Long operatorId;

    /**
     * 操作者IP
     */
    private String operatorIp;
}