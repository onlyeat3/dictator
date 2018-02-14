package com.github.liuyuyu.dictator.server.common.model.dto;

import com.github.liuyuyu.dictator.common.Convertible;
import com.github.liuyuyu.dictator.server.model.entity.DictatorUser;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liuyuyu
 */
@Data
public class DictatorUserDto implements Convertible<DictatorUser>{
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
}
