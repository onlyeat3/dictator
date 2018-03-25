package com.github.liuyuyu.dictator.client;

import com.github.liuyuyu.dictator.common.BaseProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import org.apache.commons.lang3.StringUtils;

/*
 * @author liuyuyu
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class DictatorClientProperties extends BaseProperties {

    /**
     * 配置中心服务端地址
     */
    private String serverUrl;

    public static DictatorClientProperties of() {
        return new DictatorClientProperties();
    }

    public void verify() {
        if(StringUtils.isEmpty(this.serverUrl)){
            throw new IllegalArgumentException("dictator serverUrl is required");
        }
        if(StringUtils.isEmpty(this.getAppId())){
            throw new IllegalArgumentException("dictator appId is required");
        }
    }
}
