package com.github.liuyuyu.dictator.server.common.model.param;

import com.github.liuyuyu.dictator.common.Convertible;
import com.github.liuyuyu.dictator.server.model.entity.DictatorConfigHistory;
import lombok.Data;

@Data
public class HistoryConfigUpdateParam implements Convertible<HistoryConfigUpdateParam, DictatorConfigHistory> {

    /**
     * 应用（服务）ID
     */
    private String appId;

    /**
     * 部署的ID（例如：机器名+环境名）
     */
    private String deploymentId;

    /**
     * 环境名
     */
    private String profile;

    /**
     * 配置名
     */
    private String configName;

    /**
     * 配置值
     */
    private String configValue;

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
}