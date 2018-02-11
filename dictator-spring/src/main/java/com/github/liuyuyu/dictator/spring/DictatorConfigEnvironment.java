package com.github.liuyuyu.dictator.spring;

import com.github.liuyuyu.dictator.client.DictatorClient;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.StandardEnvironment;

/**
 * @author liuyuyu
 */
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DictatorConfigEnvironment extends StandardEnvironment {
    private DictatorPropertySource dictatorPropertySource;

    public DictatorConfigEnvironment() {
    }

    public DictatorConfigEnvironment(DictatorClient dictatorClient) {
        this.dictatorPropertySource.setDictatorClient(dictatorClient);
        //初始化
        this.dictatorPropertySource.refreshCache();
    }

    public static DictatorConfigEnvironment from(@NonNull DictatorClient dictatorClient) {
        return new DictatorConfigEnvironment(dictatorClient);
    }

    @Override
    protected void customizePropertySources(MutablePropertySources propertySources) {
        this.dictatorPropertySource = new DictatorPropertySource();
        propertySources.addFirst(this.dictatorPropertySource);
    }
}
