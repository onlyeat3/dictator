package com.github.liuyuyu.dictator.server.core.service.param;

import com.github.liuyuyu.dictator.common.Convertible;
import com.github.liuyuyu.dictator.common.utils.JsonUtils;
import com.github.liuyuyu.dictator.server.core.service.zookeeper.ZookeeperConfigInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/*
 * @author liuyuyu
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@ToString(callSuper = true)
public class ConfigSetParam extends CommonParam implements Convertible {
    /**
     * 配置值
     */
    private String value;

    public String toJson() {
        ZookeeperConfigInfo zookeeperConfigInfo = new ZookeeperConfigInfo();
        zookeeperConfigInfo.setValue(this.getValue());
        zookeeperConfigInfo.setLastUpdatedTime(this.getLastUpdatedTime());
        return JsonUtils.toJson(zookeeperConfigInfo, true);
    }
}
