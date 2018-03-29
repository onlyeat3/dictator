package com.github.liuyuyu.dictator.server.web.service;

import com.github.liuyuyu.dictator.server.mapper.DictatorRoleMapper;
import com.github.liuyuyu.dictator.server.web.annotation.TransactionalAutoRollback;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author liuyuyu
 */
@Service
public class RoleService {
    @Autowired private DictatorRoleMapper roleMapper;


    @TransactionalAutoRollback
    public void saveOrUpdate(){

    }

    @TransactionalAutoRollback
    public void updatePermission(){

    }

    @TransactionalAutoRollback
    public void delete(){
        //删除角色
        //删除角色和用户的关联
        //删除角色和资源的关联
    }

    public void findDetail(@NonNull Long roleId){
        //角色基本信息
        //角色拥有的权限
    }
}
