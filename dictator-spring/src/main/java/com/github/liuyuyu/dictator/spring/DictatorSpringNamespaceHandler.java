package com.github.liuyuyu.dictator.spring;

import com.github.liuyuyu.dictator.client.DictatorClientProperties;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractBeanDefinitionParser;
import org.springframework.beans.factory.xml.NamespaceHandlerSupport;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

/**
 * @author liuyuyu
 */
public class DictatorSpringNamespaceHandler extends NamespaceHandlerSupport {

    @Override
    public void init() {
        super.registerBeanDefinitionParser("config", new DictatorBeanParser());
    }

    class DictatorBeanParser extends AbstractBeanDefinitionParser {

        @Override
        protected boolean shouldGenerateId() {
            return true;
        }

        @Override
        protected AbstractBeanDefinition parseInternal(Element element, ParserContext parserContext) {
            DictatorClientProperties dictatorClientProperties = DictatorClientProperties.of();
            dictatorClientProperties.setProfile(element.getAttribute("profile"));
            dictatorClientProperties.setServerUrl(element.getAttribute("serverUrl"));
            dictatorClientProperties.setAppCode(element.getAttribute("appCode"));
            return BeanDefinitionBuilder.rootBeanDefinition(DictatorPropertySourcesPlaceholderConfigurer.class)
                    .addPropertyValue("dictatorClientProperties",dictatorClientProperties)
                    .getBeanDefinition();
        }
    }
}
