package com.github.liuyuyu.dictator.service.param;

import com.github.liuyuyu.dictator.common.BaseProperties;
import lombok.*;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
 * @author liuyuyu
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CommonParam extends BaseProperties {
    /**
     * 配置名
     */
    private String key;

    public String toFullKey(@NonNull String seperator) {
        List<String> pathElements = Stream.of(this.getAppCode(), this.getProfile(), this.getKey())
                .filter(Objects::nonNull)
                .filter(StringUtils::isNotBlank)
                .collect(Collectors.toList());
        return String.join(seperator, pathElements);
    }


}
