package com.github.liuyuyu.dictator.server.web.model.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ConfigProfileDto {
    /**
     * 自增主键
     */
    private Long id;

    /**
     * 环境名
     */
    private String profileName;

    /**
     * 环境代码
     */
    private String profileCode;

    /**
     * 环境描述
     */
    private String profileDesc;

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
    /**
     * 启用
     */
    private Boolean enable;
}