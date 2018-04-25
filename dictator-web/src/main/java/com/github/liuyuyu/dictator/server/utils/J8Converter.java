package com.github.liuyuyu.dictator.server.utils;

import com.google.common.collect.Lists;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.IntStream;

/**
 * @param <A> 集合类型
 * @param <B> map的key类型
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class J8Converter<A, B> {
    private List<A> sourceList = new ArrayList<>();

    public J8Converter(List<A> sourceList) {
        this.sourceList = sourceList;
    }

    public Map<B, List<A>> toMap(@NonNull Function<A, B> getKey) {
        Map<B, List<A>> map = new HashMap<>();
        this.sourceList.forEach(e -> {
            B key = getKey.apply(e);
            map.put(key, Lists.newArrayList());
        });

        this.sourceList.forEach(e -> {
            B key = getKey.apply(e);
            map.get(key).add(e);
        });
        return map;
    }

    public static void main(String[] args) {
        J8Converter<String, Long> j8Converter = new J8Converter<>();
        IntStream.range(0,999)
                .forEach(i-> j8Converter.sourceList.add(String.valueOf(i)));
        Map<Long, List<String>> longListMap = j8Converter.toMap(Long::valueOf);
        System.out.println(longListMap);
    }
}
