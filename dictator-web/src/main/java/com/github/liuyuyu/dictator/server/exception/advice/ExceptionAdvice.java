package com.github.liuyuyu.dictator.server.exception.advice;

import com.github.liuyuyu.dictator.common.ErrorCodeEnum;
import com.github.liuyuyu.dictator.common.model.response.DataWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.stream.Collectors;

/*
 * @author liuyuyu
 */
@Slf4j
@ControllerAdvice
@ResponseBody
public class ExceptionAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public DataWrapper handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        DataWrapper dataWrapper = DataWrapper.of(ErrorCodeEnum.INVALID_PARAMETER);
        List<String> errorMessageList = ex.getBindingResult().getFieldErrors().stream()
                .map(e-> String.format("参数%s,%s",e.getField(), e.getDefaultMessage()))
                .collect(Collectors.toList());
        dataWrapper.setData(errorMessageList);
        log.warn("ex",ex);
        return dataWrapper;
    }

    @ExceptionHandler(Throwable.class)
    public DataWrapper handleThrowable(Throwable ex){
        DataWrapper dataWrapper = DataWrapper.of();
        dataWrapper.setMsg(ex.getMessage());
        dataWrapper.setCode("FAIL");
        dataWrapper.setSuccess(Boolean.FALSE);
        log.warn("ex",ex);
        return dataWrapper;
    }
}
