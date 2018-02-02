package com.github.liuyuyu.dictator.server.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @author liuyuyu
 */
public class ZkCondition implements Condition {

    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        String zkEnableString = conditionContext.getEnvironment().getProperty("dictator.zk.enable");
        if(StringUtils.isNotBlank(zkEnableString)){
            return Boolean.valueOf(zkEnableString);
        }
        return false;
    }
}
