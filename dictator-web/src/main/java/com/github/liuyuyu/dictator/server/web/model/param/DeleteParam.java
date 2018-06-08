package com.github.liuyuyu.dictator.server.web.model.param;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author liuyuyu
 */
@Data
public class DeleteParam extends AbstractOperatorParam {
    @NotNull private Long id;
}
