package com.github.liuyuyu.dictator.server.web.model.param;

import com.github.liuyuyu.dictator.server.web.model.dto.DictatorUserDto;

/*
 * @author liuyuyu
 */
public interface OperatorParam {
    default void join(DictatorUserDto userDto) {
        this.setOperatorId(userDto.getId());
        this.setOperatorIp(userDto.getLoginIp());
    }

    void setOperatorIp(String ip);

    void setOperatorId(Long id);
}
