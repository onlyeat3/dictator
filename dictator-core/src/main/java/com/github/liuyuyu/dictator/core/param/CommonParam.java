package com.github.liuyuyu.dictator.core.param;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/*
 * @author liuyuyu
 */
@Data
@NoArgsConstructor
public class CommonParam {
    /**
     * 寻找的路径
     */
    private String path;
    /**
     * 配置名
     */
    private String key;

    public String toPath(@NonNull String seperator) {
        return String.format("%s%s%s", this.getPath(), seperator, this.getKey());
    }
}
