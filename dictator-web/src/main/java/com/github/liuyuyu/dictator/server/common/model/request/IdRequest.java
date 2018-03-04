package com.github.liuyuyu.dictator.server.common.model.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

/*
 * @author liuyuyu
 */
@Data
public class IdRequest {
    @NotNull
    private Long id;

    public static IdRequest of() {
        return new IdRequest();
    }
}
