package com.github.liuyuyu.dictator.server.core.service.redis;

import com.github.liuyuyu.dictator.server.AbstractSpringBootTest;
import com.github.liuyuyu.dictator.server.core.service.param.CommonParam;
import com.github.liuyuyu.dictator.server.core.service.param.ConfigSetParam;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Map;
import java.util.stream.IntStream;

import static org.junit.Assert.*;

/**
 * @author liuyuyu
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RedisConfigServiceTest extends AbstractSpringBootTest {
    @Autowired private RedisConfigService configService;
    @Autowired private RedisTemplate redisTemplate;

    private String appId1 = "dictator-test";
    private String profile1 = "dev";

    private String appId2 = "dictator-test2";
    private String profile2 = "qa";

    @Test
    public void test1_save() {
        IntStream.range(0,10000)
                .forEach(i->{
                    ConfigSetParam param = new ConfigSetParam();
                    param.setValue("xxx"+i);
                    param.setKey("a"+i);

                    param.setLastUpdatedTime(super.getLastUpdatedTime());
                    param.setAppId(appId1);
                    param.setProfile(profile1);
                    this.configService.save(param);
                });
        IntStream.range(0,100000)
                .forEach(i->{
                    ConfigSetParam param = new ConfigSetParam();
                    param.setValue("xxx"+i);
                    param.setKey("a"+i);

                    param.setLastUpdatedTime(super.getLastUpdatedTime());
                    param.setAppId(appId2);
                    param.setProfile(profile2);
                    this.configService.save(param);
                });

        IntStream.range(0,200000)
                .forEach(i->{
                    ConfigSetParam param = new ConfigSetParam();
                    param.setValue("xxx"+i);
                    param.setKey("a"+i);

                    param.setLastUpdatedTime(super.getLastUpdatedTime());
                    param.setAppId(appId1);
                    param.setProfile(profile2);
                    this.configService.save(param);
                });
        IntStream.range(0,300000)
                .forEach(i->{
                    ConfigSetParam param = new ConfigSetParam();
                    param.setValue("xxx"+i);
                    param.setKey("a"+i);

                    param.setLastUpdatedTime(super.getLastUpdatedTime());
                    param.setAppId(appId2);
                    param.setProfile(profile1);
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
    public void test2_findAllApp1Profile1() {
        CommonParam param = new CommonParam();
        param.setAppId(this.appId1);
        param.setProfile(this.profile1);
        long start = System.currentTimeMillis();
        Map<String, String> map = this.configService.findAll(param);
//        map.keySet().forEach(System.out::println);
        System.out.println(System.currentTimeMillis() - start);
    }

    @Test
    public void test3_findAllApp2Profile2() {
        CommonParam param = new CommonParam();
        param.setAppId(this.appId2);
        param.setProfile(this.profile2);
        long start = System.currentTimeMillis();
        Map<String, String> map = this.configService.findAll(param);
//        map.keySet().forEach(System.out::println);
        System.out.println(System.currentTimeMillis() - start);
    }

    @Test
    public void test999999_clear(){
//        this.redisTemplate.keys()
    }
}