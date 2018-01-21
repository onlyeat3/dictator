package com.github.liuyuyu.dictator.springboot;

import org.springframework.context.annotation.Configuration;

/*
 * @author liuyuyu
 */
@Deprecated
@Configuration
public class DictatorAutoConfiguration {
    public static String SPRING_APPLICATION_PROPERTIES_FILE_PATH = "application.properties";
//
//    @Bean
//    public ZkProperties zkProperties() throws IOException {
//        Properties properties = new Properties();
//        properties.load(ClassLoader.getSystemClassLoader().getResourceAsStream(SPRING_APPLICATION_PROPERTIES_FILE_PATH));
//        String zkAddress = properties.getProperty("dictator.zkAddress");
//        String basePath = properties.getProperty("dictator.basePath");
//
//        ZkProperties zkProperties = new ZkProperties();
//        zkProperties.setZkAddress(zkAddress);
//        zkProperties.setBasePath(basePath);
//        return zkProperties;
//    }
//
//    @Bean
//    public static PropertyPlaceholderConfigurer properties(@Autowired ConfigService dictatorClient, @Autowired ZkProperties zkProperties) {
//        DictatorPropertySourcesPlaceholderConfigurer dictatorPropertySourcesPlaceholderConfigurer = new DictatorPropertySourcesPlaceholderConfigurer();
//        dictatorPropertySourcesPlaceholderConfigurer.setDictatorClient(dictatorClient);
//        dictatorPropertySourcesPlaceholderConfigurer.setZkProperties(zkProperties);
//        return dictatorPropertySourcesPlaceholderConfigurer;
//    }
//
//    @Bean(initMethod = "init")
//    public ZookeeperConfigService dictatorClient(@Autowired ZkProperties zkProperties) {
//        return new ZookeeperConfigService(zkProperties);
//    }
}
