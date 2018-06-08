package com.github.liuyuyu.dictator.client;

import com.github.liuyuyu.dictator.common.BaseProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

/*
 * @author liuyuyu
 */
@Slf4j
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
        if(StringUtils.isEmpty(this.getAppCode())){
            throw new IllegalArgumentException("dictator appCode is required");
        }
    }

    public void printCurrent(){
        log.info("Current dictator info:{}",this);
    }
}
