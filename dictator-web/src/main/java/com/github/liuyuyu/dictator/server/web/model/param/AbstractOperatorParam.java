package com.github.liuyuyu.dictator.server.web.model.param;

import lombok.Data;

@Data
public abstract class AbstractOperatorParam<T> {
    protected Long operatorId;
    protected String operatorIp;
}
