package com.github.liuyuyu.dictator.server.web.service;

import com.github.liuyuyu.dictator.common.utils.BeanConverter;
import com.github.liuyuyu.dictator.common.utils.UUIDUtils;
import com.github.liuyuyu.dictator.server.mapper.DictatorResourceMapper;
import com.github.liuyuyu.dictator.server.mapper.DictatorRoleMapper;
import com.github.liuyuyu.dictator.server.mapper.DictatorUserMapper;
import com.github.liuyuyu.dictator.server.model.entity.DictatorRole;
import com.github.liuyuyu.dictator.server.model.entity.DictatorUser;
import com.github.liuyuyu.dictator.server.web.exception.ServiceException;
import com.github.liuyuyu.dictator.server.web.exception.enums.UserErrorMessageEnum;
import com.github.liuyuyu.dictator.server.web.model.dto.DictatorResourceDto;
import com.github.liuyuyu.dictator.server.web.model.dto.DictatorUserDto;
import com.github.liuyuyu.dictator.server.web.model.param.DictatorUserSaveOrUpdateParam;
import com.github.liuyuyu.dictator.server.web.model.param.LoginParam;
import com.github.liuyuyu.dictator.server.web.model.param.UpdatePasswordParam;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author liuyuyu
 */
@Service
public class UserService {
    @Autowired
    private DictatorUserMapper userMapper;
    @Autowired
    private DictatorResourceMapper resourceMapper;
    @Autowired
    private DictatorRoleMapper roleMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public DictatorUserDto login(@NonNull LoginParam loginParam) {
        //用户校验
        Optional<DictatorUser> userOptional = this.userMapper.findByUsername(loginParam.getUsername());
        DictatorUser dictatorUser = userOptional.orElseThrow(() -> ServiceException.from(UserErrorMessageEnum.USER_NOT_FOUND));
        //密码校验
        if (!this.passwordEncoder.matches(loginParam.getPassword(),dictatorUser.getPassword())) {
            throw ServiceException.from(UserErrorMessageEnum.INCORRECT_PASSWORD);
        }
        DictatorUserDto dictatorUserDto = new DictatorUserDto();
        dictatorUserDto.from(dictatorUser);
        dictatorUserDto.setToken(UUIDUtils.next());
        return dictatorUserDto;
    }

    public DictatorUserDto findUserInfo(@NonNull Long userId) {
        DictatorUser dictatorUser = this.userMapper.selectByPrimaryKey(userId);
        if (dictatorUser != null) {
            DictatorUserDto dictatorUserDto = new DictatorUserDto();
            dictatorUserDto.from(dictatorUser);
            List<Long> roleIdList = this.roleMapper.findByUserId(dictatorUserDto.getId()).stream()
                    .map(DictatorRole::getId)
                    .collect(Collectors.toList());
            if (!roleIdList.isEmpty()) {
                List<DictatorResourceDto> resourceList = BeanConverter.from(this.resourceMapper.findByRoleIdList(roleIdList))
                        .toList(DictatorResourceDto.class);

                dictatorUserDto.setResourceList(resourceList);
            }
            return dictatorUserDto;
        } else {
            return null;
        }
    }

    public void saveOrUpdate(DictatorUserSaveOrUpdateParam userSaveOrUpdateParam) {
        DictatorUser userEntity = userSaveOrUpdateParam.to(DictatorUser.class);
        if (userSaveOrUpdateParam.getId() == null) {
            userEntity.setPassword(this.genDefaultPassword());
            this.userMapper.insertSelective(userEntity);
        } else {
            this.userMapper.updateByPrimaryKeySelective(userEntity);
        }
    }

    public String genDefaultPassword(){
        return this.passwordEncoder.encode("123456");
    }

    public List<DictatorUserDto> findAll() {
        return this.userMapper.findAllSummary();
    }

    public void updatePassword(@NonNull UpdatePasswordParam updatePasswordParam) {

    }
}
