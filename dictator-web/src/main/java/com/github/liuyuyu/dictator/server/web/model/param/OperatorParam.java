package com.github.liuyuyu.dictator.server.web.model.param;

import com.github.liuyuyu.dictator.server.web.model.dto.DictatorUserDto;

import java.util.List;

/*
 * @author liuyuyu
 */
public interface OperatorParam {
    default void join(DictatorUserDto userDto) {
        this.setOperatorId(userDto.getId());
        this.setOperatorIp(userDto.getLoginIp());
        this.setRoleIdList(userDto.getRoleIdList());
    }

    void setOperatorIp(String ip);

    void setOperatorId(Long id);

    default void setRoleIdList(List<Long> roleIdList){}
}
