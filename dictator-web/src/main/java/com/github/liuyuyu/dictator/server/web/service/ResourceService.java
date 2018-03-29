package com.github.liuyuyu.dictator.server.web.service;

import com.github.liuyuyu.dictator.server.mapper.DictatorResourceMapper;
import com.github.liuyuyu.dictator.server.mapper.DictatorRoleResourceMapper;
import com.github.liuyuyu.dictator.server.model.entity.DictatorResource;
import com.github.liuyuyu.dictator.server.web.annotation.TransactionalAutoRollback;
import com.github.liuyuyu.dictator.server.web.model.dto.DictatorResourceDto;
import com.github.liuyuyu.dictator.server.web.model.param.ResourceQueryParam;
import com.github.liuyuyu.dictator.server.web.model.param.ResourceSaveOrUpdateParam;
import com.github.pagehelper.PageInfo;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author liuyuyu
 */
@Service
public class ResourceService {
    @Autowired private DictatorResourceMapper resourceMapper;
    @Autowired private DictatorRoleResourceMapper roleResourceMapper;

    @TransactionalAutoRollback
    public Long saveOrUpdate(@NonNull ResourceSaveOrUpdateParam resourceSaveOrUpdateParam){
        DictatorResource dictatorResourceEntity = resourceSaveOrUpdateParam.to(DictatorResource.class);
        if(dictatorResourceEntity.getId() == null){
            DictatorResource parentResourceEntity = this.resourceMapper.selectByPrimaryKey(dictatorResourceEntity.getParentId());
            if(parentResourceEntity != null){
                dictatorResourceEntity.setParentIds(parentResourceEntity.getParentId() + "/" + resourceSaveOrUpdateParam.getParentId());
            }else{
                dictatorResourceEntity.setParentIds("/"+dictatorResourceEntity.getParentId());
            }
            this.resourceMapper.insertSelective(dictatorResourceEntity);
        }else{
            this.resourceMapper.updateByPrimaryKeySelective(dictatorResourceEntity);
        }
        return dictatorResourceEntity.getId();
    }

    @TransactionalAutoRollback
    public void delete(@NonNull Long resourceId){
        //删除资源
        this.resourceMapper.deleteByPrimaryKey(resourceId);
        //删除和角色的关联
        this.roleResourceMapper.deleteByResourceId(resourceId);
    }

    public PageInfo<DictatorResourceDto> findPage(@NonNull ResourceQueryParam resourceQueryParam){
        return this.resourceMapper.findByParam(resourceQueryParam);
    }

    public List<DictatorResourceDto> findMine(@NonNull Long userId) {
        return null;
    }
}
