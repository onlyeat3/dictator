package com.github.liuyuyu.dictator.server.web.model.param;

import com.github.liuyuyu.dictator.common.Convertible;
import com.github.liuyuyu.dictator.common.Resolvable;
import lombok.Data;

/**
 * @author liuyuyu
 */
@Data
public class DictatorUserSaveOrUpdateParam implements Convertible,Resolvable {
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
     * 所属角色
     */
    private String roles;
}
