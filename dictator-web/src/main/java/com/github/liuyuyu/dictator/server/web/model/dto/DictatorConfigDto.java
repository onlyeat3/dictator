package com.github.liuyuyu.dictator.server.web.model.dto;

import lombok.Data;

import java.util.Date;

/*
 * @author liuyuyu
 */
@Data
public class DictatorConfigDto {
    /**
     * 自增主键
     */
    private Long id;
    /**
     * 应用（服务）ID
     */
    private String appId;
    /**
     * 环境名
     */
    private String profileName;
    /**
     * 配置名
     */
    private String configName;
    /**
     * 配置值
     */
    private String configValue;
    /**
     * 配置版本
     */
    private Long version;
    /**
     * 备注
     */
    private String remark;
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
     * profile_id
     */
    private Long profileId;
    /**
     * 分组ID
     */
    private Long groupId;
    /**
     * 分组名
     */
    private String groupName;

    public static DictatorConfigDto of() {
        return new DictatorConfigDto();
    }
}
