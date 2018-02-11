package com.github.liuyuyu.dictator.server.service.zookeeper;

import com.github.liuyuyu.dictator.common.model.dto.DictatorValueResponse;
import com.github.liuyuyu.dictator.server.service.ConfigReadService;
import com.github.liuyuyu.dictator.server.service.ConfigWriteService;
import com.github.liuyuyu.dictator.server.service.param.CommonParam;
import com.github.liuyuyu.dictator.server.service.param.ConfigGetParam;
import com.github.liuyuyu.dictator.server.service.param.ConfigSetParam;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.imps.CuratorFrameworkState;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.data.Stat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;

import javax.annotation.PostConstruct;
import java.io.Closeable;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * @author liuyuyu
 */
@Slf4j
@Data
@NoArgsConstructor
@Order(1)
public class ZookeeperConfigService implements ConfigWriteService, ConfigReadService, Closeable {
    @Autowired
    private ZkProperties zkProperties;
    private CuratorFramework zkClient;
    /**
     * path分隔符
     */
    private String seperator = "/";

    public ZookeeperConfigService(ZkProperties zkProperties) {
        this.zkProperties = zkProperties;
    }

    @PostConstruct
    public void init() {
        this.zkClient = CuratorFrameworkFactory
                .newClient(this.zkProperties.getAddress(), this.zkProperties.getSessionTimeoutMs(), this.zkProperties.getConnectionTimeoutMs(), this.zkProperties.getRetryPolicy());
        CuratorFrameworkState curatorFrameworkState = this.zkClient.getState();
        if (CuratorFrameworkState.LATENT.equals(curatorFrameworkState)) {
            this.zkClient.start();
        }
        log.info("[ZookeeperConfigService]started.");
    }

    @Override
    public void close() throws IOException {
        this.zkClient.close();
    }

    @Override
    public DictatorValueResponse find(ConfigGetParam configGetParam) {
        String fullPath = this.seperator + configGetParam.toFullKey(this.seperator);
        log.debug("find node appId:{}", fullPath);
        String finalValue = configGetParam.getDefaultValue();//有默认值返回默认值;
        try {
            byte[] zkValueBytes = this.zkClient.getData().forPath(fullPath);
            if (zkValueBytes != null && zkValueBytes.length > 0) {
                finalValue = new String(zkValueBytes);
            }
        } catch (KeeperException.NoNodeException e) {
            //ignore
        } catch (Exception e) {
            throw ZKForPathException.of(e);
        }
        log.debug("find node appId:{},value:{}", fullPath, finalValue);
        DictatorValueResponse dictatorValueResponse = DictatorValueResponse.of();
        dictatorValueResponse.setValue(finalValue);
        dictatorValueResponse.setVersion("unknown");
        return dictatorValueResponse;
    }

    @Override
    public void save(ConfigSetParam configSetParam) {
        String fullPath = this.seperator + configSetParam.toFullKey(this.seperator);
        log.debug("save node appId:{},value:{}", fullPath, configSetParam.getValue());
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
        boolean exists = this.exists(configSetParam);
        if (exists) {
            try {
                this.zkClient.setData()
                        .forPath(this.seperator + configSetParam.toFullKey(this.seperator), configSetParam.getValue().getBytes());
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
                    .forPath(this.seperator + commonParam.toFullKey(this.seperator));
            return stat != null;
        } catch (Exception e) {
            log.warn("exists", e);
            return false;
        }
    }

    @Override
    public Map<String, String> findAll(CommonParam commonParam) {
        String fullPath = this.seperator + commonParam.toFullKey(this.seperator);
        log.debug("find node appId:{}", fullPath);
        Map<String, String> configMap = new HashMap<>();
        try {
            this.getChildren(fullPath, configMap);
        } catch (KeeperException.NoNodeException e) {
            //ignore
        } catch (Exception e) {
            throw ZKForPathException.of(e);
        }
        log.debug("find node appId:{},value:{}", fullPath, configMap);
        return configMap;
    }

    private void getChildren(@NonNull String parentNodePath, @NonNull Map<String, String> nodeMap) throws Exception {
        List<String> childNodeNameList = this.zkClient.getChildren().forPath(parentNodePath);
        for (String childNodePath : childNodeNameList) {
            byte[] zkValueBytes = this.zkClient.getData().forPath(parentNodePath + this.seperator + childNodePath);
            if (zkValueBytes != null && zkValueBytes.length > 0) {
                nodeMap.put(childNodePath, new String(zkValueBytes));
            }
        }
    }

    @Override
    public void saveIfNotExists(ConfigSetParam configSetParam) {
        boolean exists = this.exists(configSetParam);
        if (!exists) {
            this.save(configSetParam);
        }
    }

    @Override
    public boolean delete(CommonParam commonParam) {
        try {
            boolean exists = this.exists(commonParam);
            if (exists) {
                this.zkClient.delete()
                        .forPath(this.seperator + commonParam.toFullKey(this.seperator));
                log.debug("delete {} success.", commonParam);
                return true;
            }
        } catch (Exception e) {
            log.warn("delete", e);
        }
        return false;
    }
}
