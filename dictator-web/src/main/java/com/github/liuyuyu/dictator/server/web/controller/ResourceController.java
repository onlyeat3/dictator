package com.github.liuyuyu.dictator.server.web.controller;

import com.github.liuyuyu.dictator.common.model.response.DataWrapper;
import com.github.liuyuyu.dictator.common.utils.BeanConverter;
import com.github.liuyuyu.dictator.server.utils.ResourceName;
import com.github.liuyuyu.dictator.server.web.model.dto.DictatorResourceDto;
import com.github.liuyuyu.dictator.server.web.model.dto.DictatorUserDto;
import com.github.liuyuyu.dictator.server.web.model.param.ResourceSaveOrUpdateParam;
import com.github.liuyuyu.dictator.server.web.model.request.IdRequest;
import com.github.liuyuyu.dictator.server.web.model.response.ResourceResponse;
import com.github.liuyuyu.dictator.server.web.mvc.CurrentUser;
import com.github.liuyuyu.dictator.server.web.service.ResourceService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author liuyuyu
 */
@ResourceName(value = "资源",uri = "resource")
@RestController
@RequestMapping("/resource")
public class ResourceController {
    @Autowired private ResourceService resourceService;

    @ResourceName("登录角色已有资源")
    @RequestMapping("/mine")
    public DataWrapper showMine(@CurrentUser DictatorUserDto currentUser) {
        List<DictatorResourceDto> mineResourceDtoList = this.resourceService.findMine(currentUser.getId());
        List<ResourceResponse> resourceResponseList = BeanConverter.from(mineResourceDtoList)
                .toList(ResourceResponse.class);
        return DataWrapper.from(resourceResponseList);
    }

    @ResourceName("资源列表")
    @RequestMapping("/list")
    public DataWrapper showList(){
        List<DictatorResourceDto> resourceDtoList = new ArrayList<>();
        DictatorResourceDto root = new DictatorResourceDto();
        root.setId(0L);
        root.setResourceName("根节点");
        resourceDtoList.add(root);
        List<DictatorResourceDto> childrenList = this.resourceService.findByParentId(Collections.singletonList(0L));
        root.setChildren(childrenList);
        return DataWrapper.from(resourceDtoList);
    }

    @ResourceName("资源增加/编辑")
    @RequestMapping("/saveOrUpdate")
    public DataWrapper saveOrUpdate(@RequestBody @Valid ResourceSaveOrUpdateParam param,@CurrentUser DictatorUserDto currentUser){
        param.setOperatorId(currentUser.getId());
        param.setOperatorIp(currentUser.getLoginIp());
        this.resourceService.saveOrUpdate(param);
        return DataWrapper.of();
    }

    @ResourceName("删除资源")
    @RequestMapping("/delete")
    public DataWrapper delete(@RequestBody @Valid IdRequest idRequest){
        this.resourceService.delete(idRequest.getId());
        return DataWrapper.of();
    }
}
