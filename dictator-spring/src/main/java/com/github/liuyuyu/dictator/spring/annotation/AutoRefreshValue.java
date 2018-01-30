package com.github.liuyuyu.dictator.spring.annotation;

import java.lang.annotation.*;

/*
 * 只支持@org.springframework.beans.factory.annotation.Value标注的配置
 * @author liuyuyu
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AutoRefreshValue {
}
