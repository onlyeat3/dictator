create database dictator;
use dictator;
CREATE TABLE dictator_config
(
  id            BIGINT PRIMARY KEY    AUTO_INCREMENT
  COMMENT '自增主键',
  app_id        VARCHAR(100) NOT NULL
  COMMENT '应用（服务）ID',
  deployment_id VARCHAR(100) NOT NULL
  COMMENT '部署的ID（例如：机器名+环境名）',
  profile_id    BIGINT       NOT NULL
  COMMENT 'profile_id',
  group_id      BIGINT       NOT NULL
  COMMENT '分组ID',
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
CREATE TABLE dictator_config_group
(
  id          BIGINT PRIMARY KEY AUTO_INCREMENT
  COMMENT '自增主键',
  group_name  VARCHAR(20)                        NOT NULL
  COMMENT '分组名',
  created_at  DATETIME DEFAULT current_timestamp NOT NULL
  COMMENT '创建时间',
  updated_at  DATETIME           DEFAULT current_timestamp
  COMMENT '最后更新时间' ON UPDATE current_timestamp,
  operator_id BIGINT                             NOT NULL
  COMMENT '后台操作人ID',
  operator_ip VARCHAR(20)                        NOT NULL
  COMMENT '操作者IP',
  UNIQUE (group_name),
  KEY `ix_created_at` (`created_at`),
  KEY `ix_updated_at` (`updated_at`)
)
  ENGINE = InnoDB
  COMMENT '配置分组';

CREATE TABLE dictator_config_history
(
  id                   BIGINT PRIMARY KEY    AUTO_INCREMENT
  COMMENT '自增主键',
  app_id               VARCHAR(100) NOT NULL
  COMMENT '应用（服务）ID',
  config_id            BIGINT       NOT NULL
  COMMENT '配置表ID',
  deployment_id        VARCHAR(100) NOT NULL
  COMMENT '部署的ID（例如：机器名+环境名）',
  profile_id           BIGINT       NOT NULL
  COMMENT 'profile_id',
  group_id             BIGINT       NOT NULL
  COMMENT '分组ID',
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


CREATE TABLE dictator_config_profile
(
  id           BIGINT PRIMARY KEY    AUTO_INCREMENT COMMENT '自增主键',
  profile_name VARCHAR(50)  NOT NULL COMMENT '环境名',
  profile_code VARCHAR(20)  NOT NULL COMMENT '环境代码',
  profile_desc VARCHAR(200) NOT NULL DEFAULT '' COMMENT '环境描述',
  enable       bit(1)       not null comment '是否启用',
  created_time DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  updated_time DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  operator_id  BIGINT       NOT NULL COMMENT '操作者ID',
  operator_ip  VARCHAR(50)  NOT NULL COMMENT '操作者IP',
  INDEX `idx_created_time` (`created_time`),
  INDEX `idx_updated_time` (`updated_time`),
  INDEX `idx_operator_id` (`operator_id`)
)
  ENGINE = InnoDB
  COMMENT '配置的环境';

CREATE TABLE dictator_user
(
  id          BIGINT PRIMARY KEY AUTO_INCREMENT
  COMMENT '自增主键',
  user_name   VARCHAR(50)                        NOT NULL
  COMMENT '用户姓名',
  password    VARCHAR(200)                       NOT NULL
  COMMENT '密码',
  email       VARCHAR(50)                        NOT NULL
  COMMENT '联系邮箱',
  mobile      VARCHAR(20)                        NOT NULL
  COMMENT '联系手机',
  created_at  DATETIME DEFAULT current_timestamp NOT NULL
  COMMENT '创建时间',
  updated_at  DATETIME           DEFAULT current_timestamp
  COMMENT '最后更新时间' ON UPDATE current_timestamp,
  operator_id BIGINT                             NOT NULL
  COMMENT '后台操作人ID',
  operator_ip VARCHAR(20)                        NOT NULL
  COMMENT '操作者IP',
  KEY `ix_created_at` (`created_at`),
  KEY `ix_updated_at` (`updated_at`)
)
  ENGINE = InnoDB
  COMMENT '后台用户';

CREATE TABLE dictator_user_role
(
  user_id BIGINT NOT NULL
  COMMENT '用户ID',
  role_id BIGINT NOT NULL
  COMMENT '角色ID',
  PRIMARY KEY (user_id, role_id)
)
  ENGINE = InnoDB
  COMMENT '用户-角色关联表';

CREATE TABLE dictator_role
(
  id          BIGINT PRIMARY KEY AUTO_INCREMENT
  COMMENT '自增主键',
  role_name   VARCHAR(20)                        NOT NULL
  COMMENT '角色名',
  created_at  DATETIME DEFAULT current_timestamp NOT NULL
  COMMENT '创建时间',
  updated_at  DATETIME           DEFAULT current_timestamp
  COMMENT '最后更新时间' ON UPDATE current_timestamp,
  operator_id BIGINT                             NOT NULL
  COMMENT '后台操作人ID',
  operator_ip VARCHAR(20)                        NOT NULL
  COMMENT '操作者IP',
  KEY `ix_created_at` (`created_at`),
  KEY `ix_updated_at` (`updated_at`)
)
  ENGINE = InnoDB
  COMMENT '后台角色';

CREATE TABLE dictator_role_resource (
  role_id     BIGINT NOT NULL
  COMMENT '角色ID',
  resource_id BIGINT NOT NULL
  COMMENT '资源ID',
  PRIMARY KEY (role_id, resource_id)
)
  ENGINE = InnoDB
  COMMENT '角色-资源关联表';

CREATE TABLE dictator_role_group (
  role_id  BIGINT NOT NULL
  COMMENT '角色ID',
  group_id BIGINT NOT NULL
  COMMENT '分组ID',
  PRIMARY KEY (role_id, group_id)
)
  ENGINE = InnoDB
  COMMENT '角色-分组关联表';

CREATE TABLE dictator_resource
(
  id            BIGINT PRIMARY KEY AUTO_INCREMENT
  COMMENT '自增主键',
  resource_name VARCHAR(20)                        NOT NULL
  COMMENT '资源名',
  resource_type TINYINT                            NOT NULL
  COMMENT '资源类型：菜单、按钮',
  parent_id     BIGINT                             NOT NULL
  COMMENT '父节点ID',
  parent_ids    VARCHAR(200)                       NOT NULL
  COMMENT '所有上级节点',
  target_uri    VARCHAR(200)                       NOT NULL
  COMMENT '目标URI',
  created_at    DATETIME DEFAULT current_timestamp NOT NULL
  COMMENT '创建时间',
  updated_at    DATETIME           DEFAULT current_timestamp
  COMMENT '最后更新时间' ON UPDATE current_timestamp,
  operator_id   BIGINT                             NOT NULL
  COMMENT '后台操作人ID',
  operator_ip   VARCHAR(20)                        NOT NULL
  COMMENT '操作者IP',
  UNIQUE (resource_name),
  KEY `ix_created_at` (`created_at`),
  KEY `ix_updated_at` (`updated_at`)
)
  ENGINE = InnoDB
  COMMENT '系统资源';

ALTER TABLE `dictator_config`
  ADD UNIQUE INDEX `uq_config` (`app_id`, `deployment_id`, `profile_id`, `group_id`, `config_name`);
ALTER TABLE `dictator_config_history`
  ADD UNIQUE INDEX `uq_config` (`app_id`, `deployment_id`, `profile_id`, `group_id`, `config_name`, `version`);