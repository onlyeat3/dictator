package com.github.liuyuyu.dictator.mariadbj;

import com.wix.mysql.EmbeddedMysql;
import com.wix.mysql.config.MysqldConfig;
import com.wix.mysql.config.SchemaConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import java.util.concurrent.TimeUnit;

import static com.wix.mysql.EmbeddedMysql.anEmbeddedMysql;
import static com.wix.mysql.config.Charset.UTF8;
import static com.wix.mysql.config.MysqldConfig.aMysqldConfig;
import static com.wix.mysql.distribution.Version.v5_6_23;

public class MySQLServiceStartListener implements SpringApplicationRunListener {

    public MySQLServiceStartListener() {
    }

    public MySQLServiceStartListener(SpringApplication springApplication, String... args) {

    }

    @Override
    public void starting() {
    }

    @Override
    public void environmentPrepared(ConfigurableEnvironment configurableEnvironment) {
    }

    @Override
    public void contextPrepared(ConfigurableApplicationContext configurableApplicationContext) {
    }

    @Override
    public void contextLoaded(ConfigurableApplicationContext configurableApplicationContext) {
        ConfigurableEnvironment environment = configurableApplicationContext.getEnvironment();
        Integer port = environment.getRequiredProperty("spring.datasource.port",Integer.class);
        String databaseName = environment.getProperty("spring.datasource.databaseName","test");
        String user = environment.getProperty("spring.datasource.username","");
        String password = environment.getProperty("spring.datasource.password","");
        MysqldConfig config = aMysqldConfig(v5_6_23)
                .withCharset(UTF8)
                .withPort(port)
                .withUser(user, password)
                .withTimeZone("Asia/Shanghai")
                .withTimeout(2, TimeUnit.HOURS)
                .withServerVariable("max_connect_errors", 666)
                .build();


        SchemaConfig cfg = SchemaConfig.aSchemaConfig(databaseName)
                .build();
        EmbeddedMysql mysqlServer = anEmbeddedMysql(config).start();
        if (!MySQLProperties.DEFAULT_TEST_DB.equals(databaseName)) {
            mysqlServer.dropSchema(cfg);
            mysqlServer.addSchema(cfg);
        }


    }

    @Override
    public void finished(ConfigurableApplicationContext configurableApplicationContext, Throwable throwable) {

    }
}
