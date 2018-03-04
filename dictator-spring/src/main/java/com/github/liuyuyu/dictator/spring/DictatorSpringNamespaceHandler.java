package com.github.liuyuyu.dictator.spring;

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
            return BeanDefinitionBuilder.rootBeanDefinition(DictatorPropertySourcesPlaceholderConfigurer.class)
                    .getBeanDefinition();
        }
    }
}
