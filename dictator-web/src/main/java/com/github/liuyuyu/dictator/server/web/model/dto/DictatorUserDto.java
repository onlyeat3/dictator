package com.github.liuyuyu.dictator.server.web.model.dto;

import com.github.liuyuyu.dictator.common.Convertible;
import com.github.liuyuyu.dictator.common.Resolvable;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liuyuyu
 */
@Data
public class DictatorUserDto implements Convertible,Resolvable {
    /**
     * 自增主键
     */
    private Long id;

    /**
     * 管理员姓名
     */
    private String userName;

    /**
     * 联系邮箱
     */
    private String email;

    /**
     * 联系手机
     */
    private String mobile;
    /**
     * 当前token
     */
    private String token;
    /**
     * 拥有的资源
     */
    private List<DictatorResourceDto> resourceList = new ArrayList<>();
    /**
     * 登录IP
     */
    private String loginIp;

    /**
     * 所属角色
     */
    private String roles;
    /**
     * 角色ID
     */
    private List<Long> roleIdList = new ArrayList<>();
}
