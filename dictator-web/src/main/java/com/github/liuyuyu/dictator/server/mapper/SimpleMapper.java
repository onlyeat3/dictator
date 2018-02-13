package com.github.liuyuyu.dictator.server.mapper;

import org.apache.ibatis.exceptions.TooManyResultsException;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.weekend.Weekend;

import java.util.List;
import java.util.Optional;

/**
 * @author liuyuyu
 */
public interface SimpleMapper<T> extends Mapper<T> {
    default Optional<T> findOne(Weekend weekend){
        List<T> ts = this.selectByExample(weekend);
        if(ts.size() > 1){
            throw new TooManyResultsException();
        }else{
            if(ts.isEmpty()){
                return Optional.empty();
            }
            return Optional.ofNullable(ts.get(0));
        }
    }
}
