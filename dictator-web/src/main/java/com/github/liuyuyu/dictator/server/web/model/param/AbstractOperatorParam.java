package com.github.liuyuyu.dictator.server.web.model.param;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.validator.constraints.NotBlank;

@Data
@NoArgsConstructor
public abstract class AbstractOperatorParam implements OperatorParam {
    @NonNull protected Long operatorId;
    @NotBlank protected String operatorIp;
}
