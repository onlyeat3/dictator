package com.github.liuyuyu.dictator.server.common.model.request;

import com.github.liuyuyu.dictator.common.Convertible;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ConfigProfileDeleteRequest implements Convertible {
    /**
     * 自增主键
     */
    @NotNull private Long id;
    /**
     * 操作者ID
     */
    private Long operatorId;

    /**
     * 操作者IP
     */
    private String operatorIp;
}
