package com.github.liuyuyu.dictator.core;

import com.github.liuyuyu.dictator.api.ConfigService;
import com.github.liuyuyu.dictator.api.param.CommonParam;
import com.github.liuyuyu.dictator.api.param.ConfigGetParam;
import com.github.liuyuyu.dictator.api.param.ConfigSetParam;
import com.github.liuyuyu.dictator.core.exception.ZKForPathException;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.imps.CuratorFrameworkState;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.data.Stat;

import java.io.Closeable;
import java.io.IOException;

/*
 * @author liuyuyu
 */
@Slf4j
@Data
@NoArgsConstructor
public class ZookeeperConfigService implements ConfigService, Closeable {
    private ZkProperties zkProperties;
    @Setter(AccessLevel.PRIVATE)
    private CuratorFramework zkClient;
    /**
     * path分隔符
     */
    private String seperator = "/";

    public ZookeeperConfigService(ZkProperties zkProperties) {
        this.zkProperties = zkProperties;
    }

    public void init() {
        this.zkClient = CuratorFrameworkFactory
                .newClient(this.zkProperties.getZkAddress(), this.zkProperties.getSessionTimeoutMs(), this.zkProperties.getConnectionTimeoutMs(), this.zkProperties.getRetryPolicy());
        CuratorFrameworkState curatorFrameworkState = this.zkClient.getState();
        if (CuratorFrameworkState.LATENT.equals(curatorFrameworkState)) {
            this.zkClient.start();
        }
        log.info("[ZKConfigService]started.");
    }

    @Override
    public void close() throws IOException {
        this.zkClient.close();
    }

    @Override
    public String find(ConfigGetParam configGetParam) {
        String fullPath = configGetParam.toPath(this.seperator);
        log.debug("find node path:{}", fullPath);
        String finalValue = configGetParam.getDefaultValue();//有默认值返回默认值;
        try {
            byte[] zkValueBytes = this.zkClient.getData().forPath(fullPath);
            if(zkValueBytes != null && zkValueBytes.length > 0){
                finalValue = new String(zkValueBytes);
            }
        }catch (KeeperException.NoNodeException e){
            //ignore
        }catch (Exception e) {
            throw ZKForPathException.of(e);
        }
        log.debug("find node path:{},value:{}", fullPath, finalValue);
        return finalValue;
    }

    @Override
    public void save(ConfigSetParam configSetParam) {
        String fullPath = configSetParam.toPath(this.seperator);
        log.debug("save node path:{},value:{}", fullPath, configSetParam.getValue());
        try {
            this.zkClient.create()
                    .creatingParentsIfNeeded()
                    .forPath(fullPath, configSetParam.getValue().getBytes());
        } catch (Exception e) {
            throw ZKForPathException.of(e);
        }
    }

    @Override
    public void saveOrModify(ConfigSetParam configSetParam) {
        boolean exists = this.exists(configSetParam.to(CommonParam.class));
        if (exists) {
            try {
                this.zkClient.setData()
                        .forPath(configSetParam.toPath(this.seperator), configSetParam.getValue().getBytes());
            } catch (Exception e) {
                throw ZKForPathException.of(e);
            }
        } else {
            this.save(configSetParam);
        }
    }

    @Override
    public boolean exists(CommonParam commonParam) {
        try {
            Stat stat = this.zkClient.checkExists()
                    .creatingParentContainersIfNeeded()
                    .forPath(commonParam.toPath(this.seperator));
            return stat != null;
        } catch (Exception e) {
            log.warn("exists?,e:{}", e);
            return false;
        }
    }

    @Override
    public void saveIfNotExists(ConfigSetParam configSetParam) {
        boolean exists = this.exists(configSetParam);
        if (!exists) {
            this.save(configSetParam);
        }
    }
}
