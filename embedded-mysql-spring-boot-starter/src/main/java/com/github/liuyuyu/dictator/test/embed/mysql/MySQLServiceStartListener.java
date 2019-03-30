package com.github.liuyuyu.dictator.test.embed.mysql;

import com.wix.mysql.EmbeddedMysql;
import com.wix.mysql.Sources;
import com.wix.mysql.SqlScriptSource;
import com.wix.mysql.config.MysqldConfig;
import com.wix.mysql.config.SchemaConfig;
import org.apache.commons.io.FileUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
        List<String> dataFileLocations = Arrays.asList(environment.getProperty("spring.datasource.script-locations", String[].class));
        String[] moreFileLocations = environment.getProperty("spring.datasource.scriptLocations", String[].class);
        if (moreFileLocations != null) {
            dataFileLocations.addAll(Arrays.asList(moreFileLocations));
        }

        List<Resource> resourcesList = new ArrayList<>();
        if (dataFileLocations != null) {
            for (String dataFileLocation : dataFileLocations) {
                try {
                    Resource[] resources = configurableApplicationContext.getResources(dataFileLocation);
                    resourcesList.addAll(Arrays.asList(resources));
                } catch (IOException e) {
                    //ignore
                }
            }
        }

//        Path path = Paths.get(resourcesList.get(0).getURI());
//        String sql = new String(Files.readAllBytes(path));

        MysqldConfig config = aMysqldConfig(v5_6_23)
                .withCharset(UTF8)
                .withPort(port)
                .withUser(user, password)
                .withTimeZone("Asia/Shanghai")
                .withTimeout(2, TimeUnit.HOURS)
                .withServerVariable("max_connect_errors", 666)
                .build();

        EmbeddedMysql mysqlServer = anEmbeddedMysql(config).start();
        if (!resourcesList.isEmpty()) {
            for (Resource resource : resourcesList) {
                try {
                    mysqlServer.executeScripts(databaseName,Sources.fromFile(resource.getFile()));
                } catch (IOException e) {
                    throw new RuntimeException(String.format("data file:%s load fail",resource.getFilename()),e);
                }
            }
        }
    }

    @Override
    public void finished(ConfigurableApplicationContext configurableApplicationContext, Throwable throwable) {

    }
}
