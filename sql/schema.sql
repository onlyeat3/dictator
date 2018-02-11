CREATE TABLE dictator_config
(
  id            BIGINT PRIMARY KEY    AUTO_INCREMENT
  COMMENT '自增主键',
  app_id        VARCHAR(100) NOT NULL
  COMMENT '应用（服务）ID',
  deployment_id VARCHAR(100) NOT NULL
  COMMENT '部署的ID（例如：机器名+环境名）',
  profile       VARCHAR(20)  NOT NULL DEFAULT ''
  COMMENT '环境',
  config_name   VARCHAR(100) NOT NULL
  COMMENT '配置名',
  config_value  VARCHAR(500) NOT NULL
  COMMENT '配置值',
  version       BIGINT       NOT NULL DEFAULT 0
  COMMENT '配置版本',
  created_time  DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP
  COMMENT '创建时间',
  updated_time  DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
  COMMENT '修改时间',
  operator_id   BIGINT       NOT NULL
  COMMENT '操作者ID',
  operator_ip   VARCHAR(50)  NOT NULL
  COMMENT '操作者IP',
  INDEX `idx_app_id` (`app_id`),
  INDEX `idx_deployment_id` (`deployment_id`),
  INDEX `idx_created_time` (`created_time`),
  INDEX `idx_updated_time` (`updated_time`),
  INDEX `idx_operator_id` (`operator_id`)
)
  ENGINE = InnoDB
  COMMENT '当前生效的配置';

CREATE TABLE dictator_config_history
(
  id                   BIGINT PRIMARY KEY
  COMMENT '自增主键',
  app_id               VARCHAR(100) NOT NULL
  COMMENT '应用（服务）ID',
  deployment_id        VARCHAR(100) NOT NULL
  COMMENT '部署的ID（例如：机器名+环境名）',
  profile              VARCHAR(20)  NOT NULL DEFAULT ''
  COMMENT '环境',
  config_name          VARCHAR(100) NOT NULL
  COMMENT '配置名',
  config_value         VARCHAR(500) NOT NULL
  COMMENT '配置值',
  version              BIGINT       NOT NULL DEFAULT 0
  COMMENT '配置版本',
  created_time         DATETIME     NOT NULL
  COMMENT '创建时间',
  updated_time         DATETIME     NOT NULL
  COMMENT '修改时间',
  history_created_time DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP
  COMMENT '记录创建时间',
  operator_id          BIGINT       NOT NULL
  COMMENT '操作者ID',
  operator_ip          VARCHAR(50)  NOT NULL
  COMMENT '操作者IP',
  INDEX `idx_app_id` (`app_id`),
  INDEX `idx_deployment_id` (`deployment_id`),
  INDEX `idx_created_time` (`created_time`),
  INDEX `idx_updated_time` (`updated_time`),
  INDEX `idx_operator_id` (`operator_id`)
)
  ENGINE = InnoDB
  COMMENT '历史配置（只做备份不操作）';


CREATE TABLE dictator_user
(
  id           BIGINT PRIMARY KEY   AUTO_INCREMENT
  COMMENT '自增主键',
  user_name    VARCHAR(50) NOT NULL
  COMMENT '用户名',
  nick_name    VARCHAR(10) NOT NULL
  COMMENT '显示的昵称',
  mobile       VARCHAR(20) NOT NULL
  COMMENT '手机号',
  email        VARCHAR(50) NOT NULL
  COMMENT '邮箱',
  created_time DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP
  COMMENT '创建时间',
  updated_time DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
  COMMENT '修改时间',
  operator_id  BIGINT      NOT NULL
  COMMENT '操作者ID',
  operator_ip  VARCHAR(50) NOT NULL
  COMMENT '操作者IP',
  INDEX `idx_created_time` (`created_time`),
  INDEX `idx_updated_time` (`updated_time`),
  INDEX `idx_operator_id` (`operator_id`)
)
  ENGINE = InnoDB
  COMMENT '管理员';

CREATE TABLE dictator_config_environment
(
  id           BIGINT PRIMARY KEY    AUTO_INCREMENT
  COMMENT '自增主键',
  env_name     VARCHAR(50)  NOT NULL
  COMMENT '环境名',
  env_code     VARCHAR(20)  NOT NULL
  COMMENT '环境代码',
  env_desc     VARCHAR(200) NOT NULL DEFAULT ''
  COMMENT '环境描述',
  created_time DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP
  COMMENT '创建时间',
  updated_time DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
  COMMENT '修改时间',
  operator_id  BIGINT       NOT NULL
  COMMENT '操作者ID',
  operator_ip  VARCHAR(50)  NOT NULL
  COMMENT '操作者IP',
  INDEX `idx_created_time` (`created_time`),
  INDEX `idx_updated_time` (`updated_time`),
  INDEX `idx_operator_id` (`operator_id`)
)
  ENGINE = InnoDB
  COMMENT '配置的环境';