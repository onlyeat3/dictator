package com.github.liuyuyu.dictator.server.web.controller;

import com.github.liuyuyu.dictator.common.model.response.DataWrapper;
import com.github.liuyuyu.dictator.common.utils.BeanConverter;
import com.github.liuyuyu.dictator.server.web.model.dto.DictatorResourceDto;
import com.github.liuyuyu.dictator.server.web.model.dto.DictatorUserDto;
import com.github.liuyuyu.dictator.server.web.model.param.ResourceQueryParam;
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
import java.util.List;

/**
 * @author liuyuyu
 */
@RestController
@RequestMapping("/resource")
public class ResourceController {
    @Autowired private ResourceService resourceService;

    @RequestMapping("/mine")
    public DataWrapper showMine(@CurrentUser DictatorUserDto currentUser) {
        List<DictatorResourceDto> mineResourceDtoList = this.resourceService.findMine(currentUser.getId());
        List<ResourceResponse> resourceResponseList = BeanConverter.from(mineResourceDtoList)
                .toList(ResourceResponse.class);
        return DataWrapper.from(resourceResponseList);
    }

    @RequestMapping("/list")
    public DataWrapper showList(@RequestBody @Valid ResourceQueryParam param){
        PageInfo<DictatorResourceDto> page = this.resourceService.findPage(param);
        return DataWrapper.from(page);
    }

    @RequestMapping("/saveOrUpdate")
    public DataWrapper saveOrUpdate(@RequestBody @Valid ResourceSaveOrUpdateParam param,@CurrentUser DictatorUserDto currentUser){
        param.setOperatorId(currentUser.getId());
        param.setOperatorIp(currentUser.getLoginIp());
        this.resourceService.saveOrUpdate(param);
        return DataWrapper.of();
    }

    @RequestMapping("/delete")
    public DataWrapper delete(@RequestBody @Valid IdRequest idRequest){
        this.resourceService.delete(idRequest.getId());
        return DataWrapper.of();
    }
}
