package com.github.liuyuyu.dictator.springboot;

import com.github.liuyuyu.dictator.spring.DictatorPropertyManager;
import com.github.liuyuyu.dictator.spring.DictatorPropertySource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * @author liuyuyu
 */
@Slf4j
public class DictatorSpringBootStartEventListener implements SpringApplicationRunListener, Ordered {

    public DictatorSpringBootStartEventListener() {
    }

    public DictatorSpringBootStartEventListener(SpringApplication springApplication, String... args) {

    }

    @Override
    public void starting() {
        this.printBanner();
    }

    @Override
    public void environmentPrepared(ConfigurableEnvironment configurableEnvironment) {
        configurableEnvironment.getPropertySources().addFirst(new DictatorPropertySource());
    }

    @Override
    public void contextPrepared(ConfigurableApplicationContext configurableApplicationContext) {
        DictatorPropertyManager.init(configurableApplicationContext.getEnvironment());
        log.info("dictator loaded.");
    }

    @Override
    public void contextLoaded(ConfigurableApplicationContext configurableApplicationContext) {
    }

    @Override
    public void finished(ConfigurableApplicationContext configurableApplicationContext, Throwable throwable) {
    }

    private void printBanner() {
        String banner = "            88 88                                                              \n" +
                "            88 \"\"              ,d                 ,d                           \n" +
                "            88                 88                 88                           \n" +
                "    ,adPPYb,88 88  ,adPPYba, MM88MMM ,adPPYYba, MM88MMM ,adPPYba,  8b,dPPYba,  \n" +
                "   a8\"    `Y88 88 a8\"     \"\"   88    \"\"     `Y8   88   a8\"     \"8a 88P'   \"Y8  \n" +
                "   8b       88 88 8b           88    ,adPPPPP88   88   8b       d8 88          \n" +
                "   \"8a,   ,d88 88 \"8a,   ,aa   88,   88,    ,88   88,  \"8a,   ,a8\" 88          \n" +
                "    `\"yong\"zhe ge  `kuangjia\"'  \"de `\"doushi     \"dashuaibi `\"!!!!!\"'            \n" +
                "                                                                                ";
        System.out.println("\n" + banner);
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }
}
