package com.github.liuyuyu.dictator.common.model.response;

import com.github.liuyuyu.dictator.common.ErrorCodeEnum;
import lombok.Data;

/*
 * @author liuyuyu
 */
@Data
public class DataWrapper {
    /**
     * 是否成功
     */
    private Boolean success = Boolean.TRUE;

    private String code = "SUCCESS";

    private String msg = "成功";
    /**
     * 属性值
     */
    private Object data;

    public static DataWrapper of() {
        return new DataWrapper();
    }

    public static DataWrapper of(ErrorCodeEnum errorCodeEnum) {
        DataWrapper dataWrapper = new DataWrapper();
        dataWrapper.setCode(errorCodeEnum.getCode());
        dataWrapper.setSuccess(Boolean.FALSE);
        dataWrapper.setMsg(errorCodeEnum.getDefaultMessage());
        return dataWrapper;
    }

    public static DataWrapper from(Object data) {
        DataWrapper dataWrapper = new DataWrapper();
        dataWrapper.setData(data);
        return dataWrapper;
    }

    public <T> T getData(Class<T> clazz){
        return clazz.cast(this.data);
    }
}
