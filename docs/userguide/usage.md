# 使用
## 服务端
按照安装文档启动即可

## 客户端

### Spring/Spring Boot

按照安装文档做好配置之后，就可以通过dictator的客户端集成功能简便的获取属性。
Spring 有两种使用配置文件的方式

#### xml配置
```java
<bean id="jdbcDataSource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
    <property name="driverClassName" value="com.mysql.jdbc.Driver"/>
    <property name="url" value="${spring.datasource.url}"/>
    <property name="username" value="${spring.datasource.username}"/>
    <property name="password" value="${spring.datasource.password}"/>
</bean>
```

#### Java Config
```java
@Component
public class DataSourceProperties{
    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;
}
```

#### 自动刷新
dictator提供了定时更新配置的功能，通常情况，我们希望配置修改之后应用立刻拿到。但是总有一些情况略微特殊。如果一个Bean的配置信息只在初始化的时候使用，同步配置到客户端也不会有什么效果（比如：数据库的ip修改）。
因为不确定到底哪些bean需要同步，需要开发者通过注解```@AutoRefreshValue```告诉dictator

```java
@com.github.liuyuyu.dictator.spring.annotation.AutoRefreshValue
@Component
public class DataSourceProperties{
    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;
}
```
### 同步配置的时间间隔
为了简单和稳定，dictator使用定时拉取的方式更新配置。默认的频率是1秒一次，如果这个频率不太符合自己的使用场景，可以通过在dictator服务端修改配置
```
dictator.client.refresh.rate:1000
```
