package com.github.liuyuyu.dictator.server.web.model.param;

import com.github.liuyuyu.dictator.common.Convertible;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class ConfigProfileDeleteParam implements Convertible,OperatorParam {
    /**
     * 自增主键
     */
    @NotNull private Long id;
    /**
     * 角色ID
     */
    private List<Long> roleIdList;
    /**
     * 操作者ID
     */
    private Long operatorId;

    /**
     * 操作者IP
     */
    private String operatorIp;
}
