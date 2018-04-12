package com.github.liuyuyu.dictator.server.web.model.param;

import com.github.liuyuyu.dictator.common.Convertible;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class RoleSaveParam implements Convertible {
    public static RoleSaveParam of(){
        return new RoleSaveParam();
    }
    /**
     * 自增主键
     */
    private Long id;

    /**
     * 角色名
     */
    @NotBlank private String roleName;

    /**
     * 后台操作人ID
     */
    private Long operatorId;

    /**
     * 操作者IP
     */
    private String operatorIp;
}