package com.github.liuyuyu.dictator.server.model.entity;

import java.util.Date;
import javax.persistence.*;
import lombok.*;

@Data
@Table(name = "dictator_user")
public class DictatorUser {
    /**
     * 自增主键
     */
    @Id
    private Long id;

    /**
     * 管理员姓名
     */
    @Column(name = "user_name")
    private String userName;
    /**
     * 密码
     */
    private String password;

    /**
     * 联系邮箱
     */
    private String email;

    /**
     * 联系手机
     */
    private String mobile;

    /**
     * 创建时间
     */
    @Column(name = "created_at")
    private Date createdAt;

    /**
     * 最后更新时间
     */
    @Column(name = "updated_at")
    private Date updatedAt;

    /**
     * 后台操作人ID
     */
    @Column(name = "operator_id")
    private Long operatorId;

    /**
     * 操作者IP
     */
    @Column(name = "operator_ip")
    private String operatorIp;
}