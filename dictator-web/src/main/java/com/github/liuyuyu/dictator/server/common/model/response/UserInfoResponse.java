package com.github.liuyuyu.dictator.server.common.model.response;

import com.github.liuyuyu.dictator.common.Convertible;
import com.github.liuyuyu.dictator.server.common.model.dto.DictatorResourceDto;
import com.github.liuyuyu.dictator.server.common.model.dto.DictatorUserDto;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liuyuyu
 */
@Data
public class UserInfoResponse implements Convertible<UserInfoResponse,DictatorUserDto> {
    /**
     * 用户名
     */
    private String name;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 拥有角色
     */
    private List<DictatorResourceDto> resourceList = new ArrayList<>();

    public static UserInfoResponse of(){
        return new UserInfoResponse();
    }
}
