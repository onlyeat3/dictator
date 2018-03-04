package com.github.liuyuyu.dictator.server.utils;

import com.github.liuyuyu.dictator.common.utils.BeanConverter;
import com.github.pagehelper.PageInfo;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author liuyuyu
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PageInfoUtils {
    public static <A, B> PageInfo<B> to(@NonNull PageInfo<A> sourcePageInfo, @NonNull Class<B> targetClass) {
        PageInfo<B> resultPageInfo = new PageInfo<>();
        BeanUtils.copyProperties(sourcePageInfo, resultPageInfo);
        List<B> list = BeanConverter.from(sourcePageInfo.getList())
                .toList(targetClass);
        resultPageInfo.setList(list);
        return resultPageInfo;
    }

    public static <A, B> PageInfo<B> to(@NonNull PageInfo<A> sourcePageInfo, Function<A, B> mapFunction) {
        PageInfo<B> resultPageInfo = new PageInfo<>();
        BeanUtils.copyProperties(sourcePageInfo, resultPageInfo);
        List<B> collect = sourcePageInfo.getList().stream()
                .map(mapFunction)
                .collect(Collectors.toList());
        resultPageInfo.setList(collect);
        return resultPageInfo;
    }
}
