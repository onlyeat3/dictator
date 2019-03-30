package com.github.liuyuyu.dictator.server;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.liuyuyu.dictator.common.ErrorCodeEnum;
import com.github.liuyuyu.dictator.common.model.request.BatchGetRequest;
import com.github.liuyuyu.dictator.common.model.request.PropertyGetRequest;
import com.github.liuyuyu.dictator.common.model.response.DataWrapper;
import com.github.liuyuyu.dictator.service.param.ConfigGetParam;
import com.github.liuyuyu.dictator.service.redis.RedisConfigService;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Slf4j
@Component
public class DictatorServerVerticle extends AbstractVerticle {

    @Autowired
    private VertxProperties vertxProperties;
    @Autowired
    private RedisConfigService redisConfigService;

    @Autowired
    private Vertx vertx;

    @Override
    public void start() throws Exception {
        Router router = Router.router(vertx);
        router.route("/dictator/config/get").handler(routingContext -> {
            String resultString;
            try {
                PropertyGetRequest propertyGetRequest =JSONUtils.parse(routingContext.getBodyAsString(), PropertyGetRequest.class);
                ConfigGetParam configGetParam = ConfigGetParam.from(propertyGetRequest);
                DataWrapper dataWrapper = DataWrapper.from(this.redisConfigService.find(configGetParam));
                log.info("request:{},response:{}", propertyGetRequest, dataWrapper);
                resultString = JSONUtils.toJSON(dataWrapper);
            } catch (Exception e) {
                log.error("get error",e);
                resultString = JSONUtils.toJSON(DataWrapper.of(ErrorCodeEnum.SERVER_ERROR));
            }
            routingContext.response()
                    .putHeader("content-type", "application/json;charset=UTF-8")
                    .end(resultString);
        });

        router.route("/dictator/config/batch/get").handler(routingContext -> {
            String resultString;
            try {
                BatchGetRequest batchGetRequest = JSONUtils.parse(routingContext.getBodyAsString(), BatchGetRequest.class);
                DataWrapper dataWrapper = DataWrapper.from(this.redisConfigService.findAll(batchGetRequest));
                resultString = JSONUtils.toJSON(dataWrapper);
                log.info("request:{},response:{}", batchGetRequest, dataWrapper);
            } catch (Exception e) {
                log.error("batch_get error",e);
                resultString = JSONUtils.toJSON(DataWrapper.of(ErrorCodeEnum.SERVER_ERROR));
            }
            routingContext.response()
                    .putHeader("content-type", "application/json;charset=UTF-8")
                    .end(resultString);
        });

        vertx.createHttpServer()
                .requestHandler(router)
                .listen(this.vertxProperties.getPort());
        log.info("vert.x is listening {}", this.vertxProperties.getPort());
    }
}
