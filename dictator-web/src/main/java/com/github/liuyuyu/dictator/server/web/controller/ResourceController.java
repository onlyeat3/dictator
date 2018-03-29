package com.github.liuyuyu.dictator.server.web.controller;

import com.github.liuyuyu.dictator.common.model.response.DataWrapper;
import com.github.liuyuyu.dictator.server.web.model.response.MenuMetaResponse;
import com.github.liuyuyu.dictator.server.web.model.response.ResourceResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author liuyuyu
 */
@RestController
@RequestMapping("/resource")
public class ResourceController {

    @RequestMapping("/mine")
    public DataWrapper showMine() {
        List<ResourceResponse> resourceResponseList = IntStream.range(0, 10).boxed()
                .map(i -> {
                    ResourceResponse resourceResponse = ResourceResponse.of();
                    resourceResponse.setId(Long.valueOf(i));
                    resourceResponse.setName("menu" + i);
                    MenuMetaResponse menuMetaResponse = MenuMetaResponse.of();
                    menuMetaResponse.setIcon("icon-" + i);
                    menuMetaResponse.setTitle("菜单" + i);
                    resourceResponse.setLevel(1);
                    resourceResponse.setPath("/menu" + i);
                    resourceResponse.setParentId((long) (i - 1));
                    return resourceResponse;
                })
                .collect(Collectors.toList());
        return DataWrapper.from(resourceResponseList);
    }
}
