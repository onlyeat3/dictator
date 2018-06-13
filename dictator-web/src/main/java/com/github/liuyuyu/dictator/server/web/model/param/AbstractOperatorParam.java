package com.github.liuyuyu.dictator.server.web.model.param;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.validator.constraints.NotBlank;

@Data
@NoArgsConstructor
public abstract class AbstractOperatorParam implements OperatorParam {
    protected Long operatorId;
    protected String operatorIp;
}
