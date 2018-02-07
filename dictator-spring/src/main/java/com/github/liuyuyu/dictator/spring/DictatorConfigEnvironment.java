package com.github.liuyuyu.dictator.spring;

import com.github.liuyuyu.dictator.client.DictatorClient;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.StandardEnvironment;

/**
 * @author liuyuyu
 */
@EqualsAndHashCode(callSuper = true)
@Data(staticConstructor = "of")
@ToString(callSuper = true)
public class DictatorConfigEnvironment extends StandardEnvironment {
    private DictatorClient dictatorClient;

    @Override
    protected void customizePropertySources(MutablePropertySources propertySources) {
        super.customizePropertySources(propertySources);
        DictatorPropertySource dictatorPropertySource = new DictatorPropertySource();
        dictatorPropertySource.setDictatorClient(dictatorClient);
        propertySources.addLast(dictatorPropertySource);
    }

    public static DictatorConfigEnvironment from(@NonNull DictatorClient dictatorClient){
        DictatorConfigEnvironment dictatorConfigEnvironment = DictatorConfigEnvironment.of();
        dictatorConfigEnvironment.setDictatorClient(dictatorClient);
        return dictatorConfigEnvironment;
    }
}
