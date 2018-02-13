package com.github.liuyuyu.dictator.server.common.model.response;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liuyuyu
 */
@Data(staticConstructor = "of")
public class UserInfoResponse {
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
    private List<String> roles = new ArrayList<>();
}
