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
    @NonNull
    private String path;
    /**
     * 配置名
     */
    @NonNull private String key;

    public String toPath(){
        return String.format("%s/%s", this.getPath(), this.getKey());
    }
}
