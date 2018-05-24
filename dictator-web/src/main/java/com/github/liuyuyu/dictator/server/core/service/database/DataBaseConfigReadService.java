package com.github.liuyuyu.dictator.server.core.service.database;

import com.github.liuyuyu.dictator.common.model.dto.DictatorValueResponse;
import com.github.liuyuyu.dictator.server.core.service.ConfigReadService;
import com.github.liuyuyu.dictator.server.core.service.param.CommonParam;
import com.github.liuyuyu.dictator.server.core.service.param.ConfigGetParam;
import com.github.liuyuyu.dictator.server.mapper.DictatorConfigGroupMapper;
import com.github.liuyuyu.dictator.server.mapper.DictatorConfigHistoryMapper;
import com.github.liuyuyu.dictator.server.mapper.DictatorConfigMapper;
import com.github.liuyuyu.dictator.server.mapper.DictatorConfigProfileMapper;
import com.github.liuyuyu.dictator.server.model.entity.DictatorConfig;
import com.github.liuyuyu.dictator.server.model.entity.DictatorConfigHistory;
import com.github.liuyuyu.dictator.server.model.entity.DictatorConfigProfile;
import com.github.liuyuyu.dictator.server.utils.PageInfoUtils;
import com.github.liuyuyu.dictator.server.web.exception.enums.ConfigErrorMessageEnum;
import com.github.liuyuyu.dictator.server.web.model.dto.DictatorConfigDto;
import com.github.liuyuyu.dictator.server.web.model.param.ConfigListParam;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author liuyuyu
 */
@Component
@Order
public class DataBaseConfigReadService implements ConfigReadService {
    @Autowired
    private DictatorConfigMapper configMapper;
    @Autowired
    private DictatorConfigHistoryMapper configHistoryMapper;
    @Autowired
    private DictatorConfigGroupMapper configGroupMapper;
    @Autowired
    private DictatorConfigProfileMapper profileMapper;

    @Override
    public DictatorValueResponse find(ConfigGetParam configGetParam) {
        DictatorConfigProfile profile = this.profileMapper.findByCodeAndEnable(configGetParam.getProfile())
                .orElseThrow(ConfigErrorMessageEnum.PROFILE_NOT_EXISTS::serviceException);
        DictatorConfig dictatorConfigEntity = this.configMapper.findByGetParam(configGetParam, profile.getId());
        DictatorValueResponse dictatorValueResponse = DictatorValueResponse.of();
        if (dictatorConfigEntity != null) {
            dictatorValueResponse.setValue(dictatorConfigEntity.getConfigValue());
            dictatorValueResponse.setVersion(String.valueOf(dictatorConfigEntity.getVersion()));
        }
        return dictatorValueResponse;
    }

    @Override
    public boolean exists(CommonParam commonParam) {
        DictatorConfigProfile profile = this.profileMapper.findByCodeAndEnable(commonParam.getProfile())
                .orElseThrow(ConfigErrorMessageEnum.PROFILE_NOT_EXISTS::serviceException);
        return this.configMapper.countByParam(commonParam, profile.getId()) > 0;
    }

    @Override
    public Map<String, String> findAll(CommonParam commonParam) {
        DictatorConfigProfile dictatorConfigProfile = this.profileMapper.findByCodeAndEnable(commonParam.getProfile())
                .orElseThrow(ConfigErrorMessageEnum.PROFILE_NOT_EXISTS::serviceException);
        Long lastUpdatedTimeLong = commonParam.getLastUpdatedTime();
        Date lastUpdatedTime = null;
        if (lastUpdatedTimeLong != null) {
            lastUpdatedTime = new Date(lastUpdatedTimeLong);
        }
        return this.configMapper.findAllByGetParam(ConfigGetParam.from(commonParam), dictatorConfigProfile.getId(), lastUpdatedTime).stream()
                .collect(Collectors.toMap(DictatorConfig::getConfigName, DictatorConfig::getConfigValue));
    }

    public List<DictatorConfig> findAllValid() {
        return this.configMapper.selectAll();
    }

    public List<DictatorConfigHistory> findLastHourInvalid() {
        return this.configHistoryMapper.findLastHour();
    }

    public PageInfo<DictatorConfigDto> findPageValid(ConfigListParam configListParam) {
        //过滤不存在的角色
        if(configListParam.getRoleIdList().isEmpty()){
            List<Long> roleIdList = this.profileMapper.findByRoleIdList(configListParam.getRoleIdList());
            configListParam.setRoleIdList(roleIdList);
            if (roleIdList.isEmpty()) {
                return new PageInfo<>();
            }
        }
        configListParam.startPage();
        PageInfo<DictatorConfigDto> pageValid = this.configMapper.findPageValid(configListParam,configListParam.getProfileId(),configListParam.getRoleIdList()).toPageInfo();
        if (pageValid.getList().isEmpty()) {
            return pageValid;
        } else {
            List<Long> groupIdList = pageValid.getList().stream()
                    .map(DictatorConfigDto::getGroupId)
                    .collect(Collectors.toList());
            Map<Long, String> configIdConfigMap = this.configGroupMapper.findConfigNameMapByGroupIdList(groupIdList);
            List<Long> profileIdList = pageValid.getList().stream()
                    .map(DictatorConfigDto::getProfileId)
                    .collect(Collectors.toList());
            Map<Long, String> profileIdNameMap = this.profileMapper.findProfileNameByIdList(profileIdList);
            pageValid.getList()
                    .forEach(e -> {
                        String groupName = configIdConfigMap.getOrDefault(e.getGroupId(), "缺失");
                        e.setGroupName(groupName);
                        String profileName = profileIdNameMap.getOrDefault(e.getProfileId(),"缺失");
                        e.setProfileName(profileName);
                    });
            return pageValid;
        }
    }
}
