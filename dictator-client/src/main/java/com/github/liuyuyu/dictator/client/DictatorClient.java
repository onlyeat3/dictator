package com.github.liuyuyu.dictator.client;

import com.github.liuyuyu.dictator.api.param.CommonParam;
import com.github.liuyuyu.dictator.client.http.MediaTypeConstants;
import lombok.Data;
import lombok.NonNull;
import okhttp3.MediaType;
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

    public static DictatorClient of(@NonNull DictatorClientProperties dictatorClientProperties) {
        DictatorClient dictatorClient = new DictatorClient();
        CommonParam commonParam = new CommonParam();
        commonParam.setAppId(dictatorClientProperties.getAppId());
        commonParam.setDeploymentId(dictatorClientProperties.getDeploymentId());
        return dictatorClient;
    }

    public void get(@NonNull String propertyName){
        Request request = new Request.Builder()
                .post(RequestBody.create(MediaTypeConstants.APPLICATION_JSON_UTF8, ""))
                .build();
        this.okHttpClient.newCall(request);
    }
}
