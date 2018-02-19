CREATE TABLE dictator_config
(
  id            BIGINT PRIMARY KEY    AUTO_INCREMENT COMMENT '自增主键',
  app_id        VARCHAR(100) NOT NULL COMMENT '应用（服务）ID',
  deployment_id VARCHAR(100) NOT NULL COMMENT '部署的ID（例如：机器名+环境名）',
  profile_id    LONG         NOT NULL COMMENT 'profile_id',
  profile       VARCHAR(20)  NOT NULL DEFAULT '' COMMENT '环境名',
  group_id      LONG         NOT NULL COMMENT '分组ID',
  config_name   VARCHAR(100) NOT NULL COMMENT '配置名',
  config_value  VARCHAR(500) NOT NULL COMMENT '配置值',
  version       BIGINT       NOT NULL DEFAULT 0 COMMENT '配置版本',
  created_time  DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  updated_time  DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  operator_id   BIGINT       NOT NULL COMMENT '操作者ID',
  operator_ip   VARCHAR(50)  NOT NULL COMMENT '操作者IP',
  INDEX `idx_app_id` (`app_id`),
  INDEX `idx_deployment_id` (`deployment_id`),
  INDEX `idx_created_time` (`created_time`),
  INDEX `idx_updated_time` (`updated_time`),
  INDEX `idx_operator_id` (`operator_id`)
)
  ENGINE = InnoDB
  COMMENT '当前生效的配置';
create table dictator_config_group
(
  id bigint primary key auto_increment comment '自增主键',
  group_name varchar(20) not null comment '分组名',
  created_at datetime default current_timestamp not null comment '创建时间',
  updated_at datetime default current_timestamp comment '最后更新时间' on update current_timestamp,
  operator_id bigint not null comment '后台操作人ID',
  operator_ip varchar(20) not null comment '操作者IP',
  unique(group_name),
  KEY `ix_created_at` (`created_at`),
  KEY `ix_updated_at` (`updated_at`)
)engine=InnoDB comment '配置分组';

CREATE TABLE dictator_config_history
(
  id                   BIGINT PRIMARY KEY    AUTO_INCREMENT COMMENT '自增主键',
  app_id               VARCHAR(100) NOT NULL COMMENT '应用（服务）ID',
  config_id            BIGINT       NOT NULL COMMENT '配置表ID',
  deployment_id        VARCHAR(100) NOT NULL COMMENT '部署的ID（例如：机器名+环境名）',
  profile_id           LONG         NOT NULL COMMENT 'profile_id',
  profile              VARCHAR(20)  NOT NULL DEFAULT '' COMMENT '环境名',
  group_id             LONG         NOT NULL COMMENT '分组ID',
  config_name          VARCHAR(100) NOT NULL COMMENT '配置名',
  config_value         VARCHAR(500) NOT NULL COMMENT '配置值',
  version              BIGINT       NOT NULL DEFAULT 0 COMMENT '配置版本',
  created_time         DATETIME     NOT NULL COMMENT '创建时间',
  updated_time         DATETIME     NOT NULL COMMENT '修改时间',
  history_created_time DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '记录创建时间',
  operator_id          BIGINT       NOT NULL COMMENT '操作者ID',
  operator_ip          VARCHAR(50)  NOT NULL COMMENT '操作者IP',
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

create table dictator_user
(
  id bigint primary key auto_increment comment '自增主键',
  user_name varchar(50) not null comment '用户姓名',
  password VARCHAR(200) NOT NULL COMMENT '密码',
  email varchar(50) not null comment '联系邮箱',
  mobile varchar(20) not null comment '联系手机',
  created_at datetime default current_timestamp not null comment '创建时间',
  updated_at datetime default current_timestamp comment '最后更新时间' on update current_timestamp,
  operator_id bigint not null comment '后台操作人ID',
  operator_ip varchar(20) not null comment '操作者IP',
  KEY `ix_created_at` (`created_at`),
  KEY `ix_updated_at` (`updated_at`)
)engine=InnoDB comment '后台用户';

create table dictator_user_role
(
  user_id bigint not null comment '用户ID',
  role_id bigint not null comment '角色ID',
  primary key (user_id,role_id)
)engine=InnoDB comment '用户-角色关联表';

create table dictator_role
(
  id bigint primary key auto_increment comment '自增主键',
  role_name varchar(20) not null comment '角色名',
  created_at datetime default current_timestamp not null comment '创建时间',
  updated_at datetime default current_timestamp comment '最后更新时间' on update current_timestamp,
  operator_id bigint not null comment '后台操作人ID',
  operator_ip varchar(20) not null comment '操作者IP',
  KEY `ix_created_at` (`created_at`),
  KEY `ix_updated_at` (`updated_at`)
)engine=InnoDB comment '后台角色';

create table dictator_role_resource(
  role_id bigint not null comment '角色ID',
  resource_id bigint not null comment '资源ID',
  primary key (role_id,resource_id)
)engine=InnoDB comment '角色-资源关联表';

create table dictator_role_group(
  role_id bigint not null comment '角色ID',
  group_id bigint not null comment '分组ID',
  primary key (role_id,group_id)
)engine=InnoDB comment '角色-分组关联表';

create table dictator_resource
(
  id bigint primary key auto_increment comment '自增主键',
  resource_name varchar(20) not null comment '资源名',
  resource_type tinyint not null comment '资源类型：菜单、按钮',
  parent_id bigint not null comment '父节点ID',
  parent_ids varchar(200) not null comment '所有上级节点',
  target_uri varchar(200) not null comment '目标URI',
  created_at datetime default current_timestamp not null comment '创建时间',
  updated_at datetime default current_timestamp comment '最后更新时间' on update current_timestamp,
  operator_id bigint not null comment '后台操作人ID',
  operator_ip varchar(20) not null comment '操作者IP',
  unique(resource_name),
  KEY `ix_created_at` (`created_at`),
  KEY `ix_updated_at` (`updated_at`)
)engine=InnoDB comment '系统资源';

ALTER TABLE `dictator_config` ADD UNIQUE INDEX `uq_config` (`app_id`, `deployment_id`, `profile_id`, `group_id`, `config_name`);