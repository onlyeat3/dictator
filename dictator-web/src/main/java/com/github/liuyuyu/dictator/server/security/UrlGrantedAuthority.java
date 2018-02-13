package com.github.liuyuyu.dictator.server.security;

import com.github.liuyuyu.dictator.server.model.entity.DictatorResource;
import lombok.NonNull;
import org.springframework.security.core.GrantedAuthority;

/**
 * @author liuyuyu
 */
public class UrlGrantedAuthority implements GrantedAuthority {
    private final DictatorResource dictatorResource;

    public UrlGrantedAuthority(DictatorResource dictatorResource) {
        this.dictatorResource = dictatorResource;
    }

    public static UrlGrantedAuthority from(@NonNull DictatorResource dictatorResource){
        return new UrlGrantedAuthority(dictatorResource);
    }

    @Override
    public String getAuthority() {
        return this.dictatorResource.getTargetUri();
    }
}
