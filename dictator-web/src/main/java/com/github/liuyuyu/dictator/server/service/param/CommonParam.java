package com.github.liuyuyu.dictator.server.service.param;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
 * @author liuyuyu
 */
@Data
@NoArgsConstructor
public class CommonParam {
    /**
     * 应用ID
     */
    private String appId;
    /**
     * 部署的ID
     */
    private String deploymentId;
    /**
     * 配置名
     */
    private String key;

    public String toFullKey(@NonNull String seperator) {
        List<String> pathElements = Stream.of(this.getAppId(), this.deploymentId, this.getKey())
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        return String.join(seperator,pathElements);
    }
}
