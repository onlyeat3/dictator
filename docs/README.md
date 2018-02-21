# dictator是什么?

```dictator``` 是一个Java的分布式配置中心.它的出现是为了解决Java微服务架构产生的"多模块*多环境*多机器"重复配置问题。

## 出现的问题

微服务从一个概念到现在成为事实上大中型项目标准架构，它带来的问题也被猿们充分认识到。
下面拿一个栗子说，现在有一个中型的商城网站，咱们用微服务的架构去搭建，大概需要下面几个模块：
```
member-service #会员服务
product-service #商品服务
order-service #订单服务
pay-service #支付服务
```
以Spring Boot为例，现在我们先给会员服务写好数据库配置
```
member-service
```
```properties
spring.datasource.driver-class-name=com.mysql.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost/test?useUnicode=true&characterEncoding=UTF-8&useSSL=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&nullNamePatternMatchesAll=true
spring.datasource.username=username
spring.datasource.password=password
```
然后```x4```

我们期望还希望在不同的环境使用不同的配置，比如不同的数据库机器IP。假设有开发、测试、线上三个环境，我们就需要把上面的配置再```x3```

到了双十一，订单量会暴增，我们给四个核心服务增加四台机器，我们现在需要的配置份数是再```x4```

合计一下，虽然其中有些是相同，考虑到每个服务都可能有自己的特殊配置（订单服务未支付订单的失效时间、会员服务的密码输入错误次数），我们认为每一份都不相同。总共就是需要```1x4x3x4=48```份配置文件！

我们每次开一个新的模块都需要重新添加配置。一般Spring Boot项目都被打成jar包，每次修改配置文件都需要重新打包+发布！修改一次配置文件就需要重新发布一次，手上一堆新需求要做，哪来的时间去重新发布一遍？

我们需要一些改变。

## 怎么解决？

在写第一行dictator代码之前，我曾经想过使用配置文件放到jar的方式去组织配置，最后发现配置的粒度很难掌握，消耗的时间并不少。不能热加载的问题也依然存在，我也就放弃了这种方式。

dictator是一个配置中心，它能够管理所有的配置。它的组成结构是
```
客户端：调用服务端，获取当前应用的配置信息、web框架集成
服务端：提供配置获取API
管理端：网页的配置管理后台
```

dictator客户端集成在web框架里，比如Spring Boot,当Spring Boot启动时注入dictator的配置。配置变更时，客户端会重新加载配置并刷新Spring Bean，从而实现了配置中心化管理和即时刷新。