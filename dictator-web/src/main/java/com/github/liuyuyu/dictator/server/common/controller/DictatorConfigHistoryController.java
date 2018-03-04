package com.github.liuyuyu.dictator.server.common.controller;

import com.github.liuyuyu.dictator.common.model.response.DataWrapper;
import com.github.liuyuyu.dictator.server.common.model.dto.DictatorConfigHistoryDto;
import com.github.liuyuyu.dictator.server.common.model.param.ConfigListParam;
import com.github.liuyuyu.dictator.server.common.model.request.ConfigIdRequest;
import com.github.liuyuyu.dictator.server.common.model.request.IdRequest;
import com.github.liuyuyu.dictator.server.common.service.ConfigHistoryService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/*
 * @author liuyuyu
 */
@RestController
@RequestMapping("/configHistory")
public class DictatorConfigHistoryController {

    @Autowired
    private ConfigHistoryService configHistoryService;

    @RequestMapping("/list")
    public DataWrapper list(@RequestBody @Valid ConfigListParam configListParam) {
        PageInfo<DictatorConfigHistoryDto> dictatorConfigDtoPageInfo = this.configHistoryService.findPageValid(configListParam);
        return DataWrapper.from(dictatorConfigDtoPageInfo);
    }

    @RequestMapping("/listByConfigId")
    public DataWrapper listByConfigId(@RequestBody @Valid ConfigIdRequest configIdRequest) {
        List<DictatorConfigHistoryDto> historyDtoList = this.configHistoryService.findAllByConfigId(configIdRequest.getConfigId());
        return DataWrapper.from(historyDtoList);
    }

    @RequestMapping("/recovery")
    public DataWrapper recovery(@RequestBody @Valid IdRequest idRequest) {
        this.configHistoryService.recovery(idRequest.getId());
        return DataWrapper.of();
    }
}
