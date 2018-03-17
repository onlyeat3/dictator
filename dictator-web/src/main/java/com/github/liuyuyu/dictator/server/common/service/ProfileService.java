package com.github.liuyuyu.dictator.server.common.service;

import com.github.liuyuyu.dictator.common.utils.BeanConverter;
import com.github.liuyuyu.dictator.server.common.annotation.TransactionalAutoRollback;
import com.github.liuyuyu.dictator.server.common.model.dto.ConfigProfileDto;
import com.github.liuyuyu.dictator.server.common.model.param.ConfigProfileDeleteParam;
import com.github.liuyuyu.dictator.server.common.model.param.ConfigProfileParam;
import com.github.liuyuyu.dictator.server.mapper.DictatorConfigProfileMapper;
import com.github.liuyuyu.dictator.server.model.entity.DictatorConfigProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/*
 * @author liuyuyu
 */
@Service
public class ProfileService {
    @Autowired
    private DictatorConfigProfileMapper profileMapper;
    @Autowired
    private ConfigService configService;
    @Autowired
    private ConfigHistoryService historyService;

    public List<ConfigProfileDto> findAll() {
        List<DictatorConfigProfile> configProfileList = this.profileMapper.selectAll();
        return BeanConverter.from(configProfileList)
                .toList(ConfigProfileDto.class);
    }

    public Map<Long, DictatorConfigProfile> findMapById(List<Long> profileIdList) {
        return this.profileMapper.findMapByIdList(profileIdList);
    }

    public void saveOrUpdate(ConfigProfileParam configProfileParam) {
        DictatorConfigProfile configProfile = configProfileParam.to(DictatorConfigProfile.class);
        if (configProfileParam.getId() != null) {
            this.profileMapper.updateByPrimaryKeySelective(configProfile);
        } else {
            this.profileMapper.insertSelective(configProfile);
        }
    }

    /**
     * 删除-profile直接删除，配置移动到历史记录
     */
    @TransactionalAutoRollback
    public void delete(ConfigProfileDeleteParam configProfileDeleteParam) {
        this.profileMapper.deleteByPrimaryKey(configProfileDeleteParam.getId());
        this.configService.deleteByProfileId(configProfileDeleteParam.getId());
    }
}
