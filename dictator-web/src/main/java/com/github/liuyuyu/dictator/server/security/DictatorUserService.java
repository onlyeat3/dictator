package com.github.liuyuyu.dictator.server.security;

import com.github.liuyuyu.dictator.server.mapper.DictatorResourceMapper;
import com.github.liuyuyu.dictator.server.mapper.DictatorRoleMapper;
import com.github.liuyuyu.dictator.server.mapper.DictatorUserMapper;
import com.github.liuyuyu.dictator.server.model.entity.DictatorResource;
import com.github.liuyuyu.dictator.server.model.entity.DictatorRole;
import com.github.liuyuyu.dictator.server.model.entity.DictatorUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DictatorUserService implements UserDetailsService {

    @Autowired
    private DictatorUserMapper userMapper;
    @Autowired
    private DictatorResourceMapper resourceMapper;
    @Autowired
    private DictatorRoleMapper roleMapper;

    public UserDetails loadUserByUsername(String username) {
        Optional<DictatorUser> userOptional = this.userMapper.findByUsername(username);
        DictatorUser dictatorUser = userOptional
                .orElseThrow(() -> new UsernameNotFoundException(username));
        List<DictatorRole> dictatorRoleList = this.roleMapper.findByUserId(dictatorUser.getId());
        List<UrlGrantedAuthority> urlGrantedAuthorityList = new ArrayList<>();
        if (!dictatorRoleList.isEmpty()) {
            List<Long> roleIdList = dictatorRoleList.stream()
                    .map(DictatorRole::getId)
                    .collect(Collectors.toList());
            if (!roleIdList.isEmpty()) {
                List<DictatorResource> resourceList = this.resourceMapper.findByRoleIdList(roleIdList);
                urlGrantedAuthorityList = resourceList.stream()
                        .map(UrlGrantedAuthority::from)
                        .collect(Collectors.toList());
            }
        }
        return new DictatorUserDetails(username, dictatorUser.getPassword(), urlGrantedAuthorityList);
    }
}