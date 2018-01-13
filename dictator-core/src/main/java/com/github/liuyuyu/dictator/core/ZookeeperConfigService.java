package com.github.liuyuyu.dictator.core;

import com.github.liuyuyu.dictator.core.exception.ZKForPathException;
import com.github.liuyuyu.dictator.core.param.CommonParam;
import com.github.liuyuyu.dictator.core.param.ConfigGetParam;
import com.github.liuyuyu.dictator.core.param.ConfigSetParam;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.imps.CuratorFrameworkState;
import org.apache.zookeeper.data.Stat;

import java.io.Closeable;
import java.io.IOException;

/*
 * @author liuyuyu
 */
@Slf4j
@Data
public class ZookeeperConfigService implements ConfigService,Closeable {

    private final ZkProperties zkProperties;
    private final CuratorFramework zkClient;

    public ZookeeperConfigService(ZkProperties zkProperties) {
        this.zkProperties = zkProperties;
        this.zkClient = CuratorFrameworkFactory
                .newClient(this.zkProperties.getZkAddress(),this.zkProperties.getSessionTimeoutMs(),this.zkProperties.getConnectionTimeoutMs(),this.zkProperties.getRetryPolicy());
    }

    public void init(){
        CuratorFrameworkState curatorFrameworkState = this.zkClient.getState();
        if(CuratorFrameworkState.LATENT.equals(curatorFrameworkState)){
            this.zkClient.start();
        }
        log.info("[ZKConfigService]started.");
    }

    @Override
    public void close() throws IOException {
        this.zkClient.close();
    }

    @Override
    public String get(ConfigGetParam configGetParam) {
        String fullPath = configGetParam.toPath();
        log.debug("get node path:{}",fullPath);
        String finalValue;
        try {
            finalValue = new String(this.zkClient.getData().forPath(fullPath));
        } catch (Exception e) {
            //有默认值返回默认值
            if(configGetParam.getDefaultValue() != null){
                finalValue = configGetParam.getDefaultValue();
            }else{
                throw ZKForPathException.of(e);
            }
        }
        log.debug("get node path:{},value:{}",fullPath,finalValue);
        return finalValue;
    }

    @Override
    public void set(ConfigSetParam configSetParam) {
        String fullPath = configSetParam.toPath();
        log.debug("set node path:{},value:{}",fullPath,configSetParam.getValue());
        try {
            this.zkClient.create()
                    .creatingParentsIfNeeded()
                    .forPath(fullPath,configSetParam.getValue().getBytes());
        } catch (Exception e) {
            throw ZKForPathException.of(e);
        }
    }

    @Override
    public void setOrModify(ConfigSetParam configSetParam) {
        boolean exists = this.exists(configSetParam.to(CommonParam.class));
        if(exists){
            try {
                this.zkClient.setData()
                        .forPath(configSetParam.toPath(),configSetParam.getValue().getBytes());
            } catch (Exception e) {
                throw ZKForPathException.of(e);
            }
        }else{
            this.set(configSetParam);
        }
    }

    @Override
    public boolean exists(CommonParam commonParam) {
        try {
            Stat stat = this.zkClient.checkExists()
                    .creatingParentContainersIfNeeded()
                    .forPath(commonParam.toPath());
            return stat != null;
        } catch (Exception e) {
            log.warn("exists?,e:{}",e);
            return false;
        }
    }

    @Override
    public void setIfNotExists(ConfigSetParam configSetParam) {
        boolean exists = this.exists(configSetParam);
        if(!exists){
            this.set(configSetParam);
        }
    }
}
