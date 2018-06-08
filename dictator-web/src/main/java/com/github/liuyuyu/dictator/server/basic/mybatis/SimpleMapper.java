package com.github.liuyuyu.dictator.server.basic.mybatis;

import com.github.liuyuyu.dictator.server.utils.PageInfoUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import lombok.NonNull;
import org.apache.ibatis.exceptions.TooManyResultsException;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.weekend.Weekend;

import java.util.List;
import java.util.Optional;

/**
 * @author liuyuyu
 */
public interface SimpleMapper<T> extends Mapper<T> {
    default Optional<T> findOne(Weekend<T> weekend) {
        List<T> ts = this.selectByExample(weekend);
        if (ts.size() > 1) {
            throw new TooManyResultsException();
        } else {
            if (ts.isEmpty()) {
                return Optional.empty();
            }
            return Optional.ofNullable(ts.get(0));
        }
    }

    default Optional<T> findFirst(Weekend<T> weekend) {
        List<T> ts = this.selectByExample(weekend);
        if (ts.isEmpty()) {
            return Optional.empty();
        }
        return Optional.ofNullable(ts.get(0));
    }

    default List<T> findAll() {
        return this.selectAll();
    }

    default Optional<T> findById(@NonNull Long id) {
        T ts = this.selectByPrimaryKey(id);
        return Optional.ofNullable(ts);
    }

    default <R> PageInfo<R> findPage(Weekend<T> weekend, Class<R> clazz) {
        Page<T> page = (Page<T>) this.selectByExample(weekend);
        return PageInfoUtils.to(page.toPageInfo(), clazz);
    }

    default List<T> findAll(Weekend<T> weekend) {
        return this.selectByExample(weekend);
    }
}
