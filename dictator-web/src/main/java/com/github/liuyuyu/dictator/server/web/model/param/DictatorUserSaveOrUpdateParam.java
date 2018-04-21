package com.github.liuyuyu.dictator.server.web.model.param;

import com.github.liuyuyu.dictator.common.Convertible;
import com.github.liuyuyu.dictator.common.Resolvable;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author liuyuyu
 */
@Data
public class DictatorUserSaveOrUpdateParam extends AbstractOperatorParam implements Convertible,Resolvable,OperatorParam {
    /**
     * 自增主键
     */
    private Long id;

    /**
     * 管理员姓名
     */
    @NotBlank private String userName;

    /**
     * 联系邮箱
     */
    @NotBlank private String email;

    /**
     * 联系手机
     */
    @NotBlank private String mobile;
}
