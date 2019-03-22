-- 0.0.3 变更的表结构和数据
ALTER TABLE `dictator_config` ADD COLUMN `remark` VARCHAR(280) NOT NULL DEFAULT '' COMMENT '备注' AFTER `version`;
ALTER TABLE `dictator_config_history` ADD COLUMN `remark` VARCHAR(280) NOT NULL DEFAULT '' COMMENT '备注' AFTER `version`;

CREATE TABLE IF NOT EXISTS `dictator_role_profile` (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `profile_id` bigint(20) NOT NULL COMMENT '环境ID',
  `created_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `operator_id` bigint(20) NOT NULL COMMENT '操作者ID',
  `operator_ip` varchar(50) NOT NULL COMMENT '操作者IP',
  PRIMARY KEY (`role_id`,`profile_id`),
  KEY `idx_created_time` (`created_time`),
  KEY `idx_operator_id` (`operator_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色-环境关联';
ALTER TABLE `dictator_user` ADD UNIQUE INDEX `uq_user_name`(`user_name`) USING BTREE;

create table dictator_app
(
    `id` bigint NOT NULL primary key AUTO_INCREMENT COMMENT '自增主键',
    app_name varchar(50) not null comment 'app名称',
    app_code varchar(50) not null comment 'APP CODE',
    owner_email varchar(50) not null comment '所有者邮箱',
    `enable` bit NOT NULL DEFAULT 0 COMMENT '是否启用',
    `created_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `operator_id` bigint(20) NOT NULL COMMENT '操作者ID',
    `operator_ip` varchar(50) NOT NULL COMMENT '操作者IP',
    UNIQUE KEY `uq_app_id` (`app_id`),
    KEY `idx_created_time` (`created_time`),
    KEY `idx_updated_time` (`updated_time`),
    KEY `idx_operator_id` (`operator_id`)
)engine=InnoDB comment 'APP';

ALTER TABLE `dictator_config` MODIFY COLUMN `app_id` bigint NOT NULL COMMENT '应用（服务）ID' AFTER `id`;
ALTER TABLE `dictator_config_history` MODIFY COLUMN `app_id` bigint NOT NULL COMMENT '应用（服务）ID' AFTER `id`;

ALTER TABLE `dictator_config` ADD COLUMN `app_code` VARCHAR(50) NOT NULL COMMENT 'APP 代码' AFTER `app_id`;
ALTER TABLE `dictator_config_history` ADD COLUMN `app_code` VARCHAR(50) NOT NULL COMMENT 'APP 代码' AFTER `app_id`;