package com.github.liuyuyu.dictator.server.core.service.redis;

import com.github.liuyuyu.dictator.server.AbstractSpringBootTest;
import com.github.liuyuyu.dictator.server.core.service.param.CommonParam;
import com.github.liuyuyu.dictator.server.core.service.param.ConfigSetParam;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;
import java.util.stream.IntStream;

import static org.junit.Assert.*;

/**
 * @author liuyuyu
 */
public class RedisConfigServiceTest extends AbstractSpringBootTest {
    @Autowired private RedisConfigService configService;

    @Test
    public void save() {
        IntStream.range(0,1_000_000)
                .forEach(i->{
                    ConfigSetParam param = new ConfigSetParam();
                    param.setValue("xxx"+i);
                    param.setKey("a"+i);

                    param.setLastUpdatedTime(super.getLastUpdatedTime());
                    param.setAppId(super.getAppId());
                    param.setProfile(super.getProfile());
                    this.configService.save(param);
                });
    }

    @Test
    public void saveOrModify() {
    }

    @Test
    public void saveIfNotExists() {
    }

    @Test
    public void delete() {
    }

    @Test
    public void find() {
    }

    @Test
    public void exists() {
    }

    @Test
    public void findAll() {
        CommonParam param = new CommonParam();
//        param.setAppId(super.getAppId());
//        param.setProfile(super.getProfile());
        long start = System.currentTimeMillis();
        Map<String, String> map = this.configService.findAll(param);
//        map.keySet().forEach(System.out::println);
        System.out.println(System.currentTimeMillis() - start);
    }
}