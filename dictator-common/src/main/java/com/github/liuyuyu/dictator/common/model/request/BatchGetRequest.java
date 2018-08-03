package com.github.liuyuyu.dictator.common.model.request;

import com.github.liuyuyu.dictator.common.BaseProperties;
import lombok.Data;
import lombok.NonNull;

import java.util.Collection;
import java.util.Collections;

/**
 * @author liuyuyu
 */
@Data
public class BatchGetRequest extends BaseProperties {
    /**
     * 缓存的KEY
     */
    private Collection<String> keys = Collections.emptyList();

    public static BatchGetRequest from(@NonNull BaseProperties source) {
        BatchGetRequest target = new BatchGetRequest();
        target.setAppCode(source.getAppCode());
        target.setProfile(source.getProfile());
        target.setLastUpdatedTime(source.getLastUpdatedTime());
        return target;
    }
}
