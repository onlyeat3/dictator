package com.github.liuyuyu.dictator.client;

import com.github.liuyuyu.dictator.client.http.MediaTypeConstants;
import com.github.liuyuyu.dictator.common.ApiUrlConstants;
import com.github.liuyuyu.dictator.common.model.request.PropertyGetRequest;
import com.github.liuyuyu.dictator.common.model.response.DataWrapper;
import com.github.liuyuyu.dictator.common.utils.JsonUtils;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;

import java.io.IOException;

/*
 * @author liuyuyu
 */
@Slf4j
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DictatorClient {
    /**
     * 默认客户端实现
     */
    @Setter(AccessLevel.PRIVATE)
    private OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .build();

    private DictatorClientProperties dictatorClientProperties;

    public static DictatorClient of(@NonNull DictatorClientProperties dictatorClientProperties) {
        if(dictatorClientProperties.getAppId() == null){
            throw new IllegalArgumentException("appId can not be null.");
        }
        if(dictatorClientProperties.getDeploymentId() == null){
            throw new IllegalArgumentException("deploymentId can not be null.");
        }
        if(dictatorClientProperties.getServerUrl() == null){
            throw new IllegalArgumentException("serverUrl can not be null.");
        }
        DictatorClient dictatorClient = new DictatorClient();
        dictatorClient.setDictatorClientProperties(dictatorClientProperties);
        return dictatorClient;
    }

    public String get(@NonNull String propertyName) {
        PropertyGetRequest propertyGetRequest = PropertyGetRequest.from(this.dictatorClientProperties);
        propertyGetRequest.setPropertyName(propertyName);
        Request request = new Request.Builder()
                .url(String.format("%s/%s",this.dictatorClientProperties.getServerUrl(), ApiUrlConstants.CONFIG_GET_URI))
                .post(RequestBody.create(MediaTypeConstants.APPLICATION_JSON_UTF8, JsonUtils.toJson(propertyGetRequest)))
                .build();
        try {
            Response response = this.okHttpClient.newCall(request).execute();
            if(response.code() == 200){
                ResponseBody responseBody = response.body();
                if(responseBody != null){
                    String responseBodyString = responseBody.string();
                    log.debug("dictator server response:{}",responseBodyString);
                    DataWrapper dataWrapper = JsonUtils.toObject(responseBodyString, DataWrapper.class);
                    if(dataWrapper != null && dataWrapper.getResponseData() != null){
                        if(dataWrapper.getSuccess() != null && dataWrapper.getSuccess()){
                            return dataWrapper.getResponseData().getValue();
                        }
                    }
                    log.warn("properties load fail",JsonUtils.toJson(dataWrapper));
                }
            }
        } catch (IOException e) {
            log.error("properties load error",e);
        }
        return null;
    }
}
