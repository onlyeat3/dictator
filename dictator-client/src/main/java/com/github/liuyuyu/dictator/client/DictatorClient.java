package com.github.liuyuyu.dictator.client;

import com.github.liuyuyu.dictator.client.http.MediaTypeConstants;
import com.github.liuyuyu.dictator.common.ApiUrlConstants;
import com.github.liuyuyu.dictator.common.BaseProperties;
import com.github.liuyuyu.dictator.common.model.request.PropertyGetRequest;
import com.github.liuyuyu.dictator.common.utils.JsonUtils;
import lombok.Data;
import lombok.NonNull;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/*
 * @author liuyuyu
 */
@Data
public class DictatorClient {
    /**
     * 默认客户端实现
     */
    private OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .build();

    private BaseProperties baseProperties;
    /**
     * 配置中心服务段地址
     */
    private String serverUrl;

    public static DictatorClient of(@NonNull DictatorClientProperties dictatorClientProperties,@NonNull String serverUrl) {
        BaseProperties baseProperties = new BaseProperties();
        baseProperties.setAppId(dictatorClientProperties.getAppId());
        baseProperties.setDeploymentId(dictatorClientProperties.getDeploymentId());

        DictatorClient dictatorClient = new DictatorClient();
        dictatorClient.setBaseProperties(baseProperties);
        dictatorClient.setServerUrl(serverUrl);
        return dictatorClient;
    }

    public void get(@NonNull String propertyName){
        PropertyGetRequest propertyGetRequest = PropertyGetRequest.from(this.baseProperties);
        Request request = new Request.Builder()
                .url(String.format("%s/%s",this.serverUrl, ApiUrlConstants.CONFIG_GET_URI))
                .post(RequestBody.create(MediaTypeConstants.APPLICATION_JSON_UTF8, JsonUtils.toJson(propertyGetRequest)))
                .build();
        this.okHttpClient.newCall(request);
    }
}
