package com.github.liuyuyu.dictator.server.web.model.dto;

import lombok.Data;

import java.util.Date;

@Data
public class DictatorAppDto {
    /**
     * 自增主键
     */
    private Long id;

    /**
     * app名称
     */
    private String appName;

    /**
     * APP CODE
     */
    private String appCode;

    /**
     * 所有者邮箱
     */
    private String ownerEmail;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 修改时间
     */
    private Date updatedTime;

    /**
     * 操作者ID
     */
    private Long operatorId;

    /**
     * 操作者IP
     */
    private String operatorIp;
}