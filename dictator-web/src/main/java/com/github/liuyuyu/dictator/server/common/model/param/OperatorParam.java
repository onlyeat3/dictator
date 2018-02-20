package com.github.liuyuyu.dictator.server.common.model.param;

import com.github.liuyuyu.dictator.server.common.model.dto.DictatorUserDto;

/*
 * @author liuyuyu
 */
public interface OperatorParam {
    default void join(DictatorUserDto userDto){
        this.setOperatorId(userDto.getId());
        this.setOperatorIp(userDto.getLoginIp());
    }

    void setOperatorIp(String ip);
    void setOperatorId(Long id);
}
