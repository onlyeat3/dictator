package com.github.liuyuyu.dictator.server.core.service.redis;

import com.github.liuyuyu.dictator.common.model.dto.DictatorValueResponse;
import com.github.liuyuyu.dictator.common.utils.JsonUtils;
import com.github.liuyuyu.dictator.server.core.service.ConfigReadService;
import com.github.liuyuyu.dictator.server.core.service.ConfigWriteService;
import com.github.liuyuyu.dictator.server.core.service.param.CommonParam;
import com.github.liuyuyu.dictator.server.core.service.param.ConfigGetParam;
import com.github.liuyuyu.dictator.server.core.service.param.ConfigSetParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author liuyuyu
 */
@Slf4j
@Component
public class RedisConfigService implements ConfigWriteService, ConfigReadService {
    @Autowired private StringRedisTemplate redisTemplate;
    private final String seperator = ":";
    @Autowired private ExecutorService executorService;

    @Override
    public void save(ConfigSetParam configSetParam) {
        this.redisTemplate.opsForValue().set(configSetParam.toFullKey(this.seperator),configSetParam.toJson());
    }

    @Override
    public void saveOrModify(ConfigSetParam configSetParam) {
        this.save(configSetParam);
    }

    @Override
    public void saveIfNotExists(ConfigSetParam configSetParam) {
        this.redisTemplate.opsForValue().setIfAbsent(configSetParam.toFullKey(this.seperator),configSetParam.toJson());
    }

    @Override
    public boolean delete(CommonParam commonParam) {
        this.redisTemplate.delete(commonParam.toFullKey(this.seperator));
        return true;
    }

    @Override
    public DictatorValueResponse find(ConfigGetParam configGetParam) {
        String value = this.redisTemplate.opsForValue().get(configGetParam.toFullKey(this.seperator));
        if(value == null){
            return DictatorValueResponse.of();
        }
        CachedConfigInfo cachedConfigInfo = JsonUtils.toObject(value, CachedConfigInfo.class);
        DictatorValueResponse dictatorValueResponse = DictatorValueResponse.of();
        if(cachedConfigInfo != null){
            dictatorValueResponse.setValue(cachedConfigInfo.getValue());
        }
        dictatorValueResponse.setVersion("");
        return dictatorValueResponse;
    }

    @Override
    public boolean exists(CommonParam commonParam) {
        return this.redisTemplate.opsForValue().get(commonParam.toFullKey(this.seperator)) != null;
    }

    @Override
    public Map<String, String> findAll(CommonParam commonParam) {
        throw new RuntimeException("Not support.");
    }
}
