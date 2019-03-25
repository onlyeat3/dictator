-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        5.7.21-log - MySQL Community Server (GPL)
-- 服务器操作系统:                      Win64
-- HeidiSQL 版本:                  9.2.0.4947
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- 导出  表 dictator.dictator_config 结构
CREATE TABLE IF NOT EXISTS `dictator_config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `app_id` varchar(100) NOT NULL COMMENT '应用（服务）ID',
  `profile_id` bigint(20) NOT NULL COMMENT 'profile_id',
  `group_id` bigint(20) NOT NULL COMMENT '分组ID',
  `config_name` varchar(100) NOT NULL COMMENT '配置名',
  `config_value` varchar(500) NOT NULL COMMENT '配置值',
  `version` bigint(20) NOT NULL DEFAULT '0' COMMENT '配置版本',
  `created_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `operator_id` bigint(20) NOT NULL COMMENT '操作者ID',
  `operator_ip` varchar(50) NOT NULL COMMENT '操作者IP',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uq_config` (`app_id`,`profile_id`,`group_id`,`config_name`),
  KEY `idx_app_id` (`app_id`),
  KEY `idx_created_time` (`created_time`),
  KEY `idx_updated_time` (`updated_time`),
  KEY `idx_operator_id` (`operator_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='当前生效的配置';

-- 数据导出被取消选择。


-- 导出  表 dictator.dictator_config_group 结构
CREATE TABLE IF NOT EXISTS `dictator_config_group` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `group_name` varchar(20) NOT NULL COMMENT '分组名',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  `operator_id` bigint(20) NOT NULL COMMENT '后台操作人ID',
  `operator_ip` varchar(20) NOT NULL COMMENT '操作者IP',
  PRIMARY KEY (`id`),
  UNIQUE KEY `group_name` (`group_name`),
  KEY `ix_created_at` (`created_at`),
  KEY `ix_updated_at` (`updated_at`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='配置分组';

-- 数据导出被取消选择。


-- 导出  表 dictator.dictator_config_history 结构
CREATE TABLE IF NOT EXISTS `dictator_config_history` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `app_id` varchar(100) NOT NULL COMMENT '应用（服务）ID',
  `config_id` bigint(20) NOT NULL COMMENT '配置表ID',
  `profile_id` bigint(20) NOT NULL COMMENT 'profile_id',
  `group_id` bigint(20) NOT NULL COMMENT '分组ID',
  `config_name` varchar(100) NOT NULL COMMENT '配置名',
  `config_value` varchar(500) NOT NULL COMMENT '配置值',
  `version` bigint(20) NOT NULL DEFAULT '0' COMMENT '配置版本',
  `created_time` datetime NOT NULL COMMENT '创建时间',
  `updated_time` datetime NOT NULL COMMENT '修改时间',
  `history_created_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '记录创建时间',
  `operator_id` bigint(20) NOT NULL COMMENT '操作者ID',
  `operator_ip` varchar(50) NOT NULL COMMENT '操作者IP',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uq_config` (`app_id`,`profile_id`,`group_id`,`config_name`,`version`),
  KEY `idx_app_id` (`app_id`),
  KEY `idx_created_time` (`created_time`),
  KEY `idx_updated_time` (`updated_time`),
  KEY `idx_operator_id` (`operator_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='历史配置（只做备份不操作）';

-- 数据导出被取消选择。


-- 导出  表 dictator.dictator_config_profile 结构
CREATE TABLE IF NOT EXISTS `dictator_config_profile` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `profile_name` varchar(50) NOT NULL COMMENT '环境名',
  `profile_code` varchar(20) NOT NULL COMMENT '环境代码',
  `profile_desc` varchar(200) NOT NULL DEFAULT '' COMMENT '环境描述',
  `enable` bit(1) NOT NULL DEFAULT b'1' COMMENT '是否启用',
  `created_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `operator_id` bigint(20) NOT NULL COMMENT '操作者ID',
  `operator_ip` varchar(50) NOT NULL COMMENT '操作者IP',
  PRIMARY KEY (`id`),
  KEY `idx_created_time` (`created_time`),
  KEY `idx_updated_time` (`updated_time`),
  KEY `idx_operator_id` (`operator_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='配置的环境';

-- 数据导出被取消选择。


-- 导出  表 dictator.dictator_resource 结构
CREATE TABLE IF NOT EXISTS `dictator_resource` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `resource_name` varchar(20) NOT NULL COMMENT '资源名',
  `resource_type` tinyint(4) NOT NULL COMMENT '资源类型：菜单、按钮',
  `parent_id` bigint(20) NOT NULL COMMENT '父节点ID',
  `parent_ids` varchar(200) NOT NULL COMMENT '所有上级节点',
  `target_uri` varchar(200) NOT NULL COMMENT '目标URI',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  `operator_id` bigint(20) NOT NULL COMMENT '后台操作人ID',
  `operator_ip` varchar(20) NOT NULL COMMENT '操作者IP',
  PRIMARY KEY (`id`),
  UNIQUE KEY `resource_name` (`resource_name`),
  KEY `ix_created_at` (`created_at`),
  KEY `ix_updated_at` (`updated_at`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统资源';

-- 数据导出被取消选择。


-- 导出  表 dictator.dictator_role 结构
CREATE TABLE IF NOT EXISTS `dictator_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `role_name` varchar(20) NOT NULL COMMENT '角色名',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  `operator_id` bigint(20) NOT NULL COMMENT '后台操作人ID',
  `operator_ip` varchar(20) NOT NULL COMMENT '操作者IP',
  PRIMARY KEY (`id`),
  KEY `ix_created_at` (`created_at`),
  KEY `ix_updated_at` (`updated_at`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='后台角色';

-- 数据导出被取消选择。


-- 导出  表 dictator.dictator_role_group 结构
CREATE TABLE IF NOT EXISTS `dictator_role_group` (
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `group_id` bigint(20) NOT NULL COMMENT '分组ID',
  PRIMARY KEY (`role_id`,`group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色-分组关联表';

-- 数据导出被取消选择。


-- 导出  表 dictator.dictator_role_resource 结构
CREATE TABLE IF NOT EXISTS `dictator_role_resource` (
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `resource_id` bigint(20) NOT NULL COMMENT '资源ID',
  PRIMARY KEY (`role_id`,`resource_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色-资源关联表';

-- 数据导出被取消选择。


-- 导出  表 dictator.dictator_user 结构
CREATE TABLE IF NOT EXISTS `dictator_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `user_name` varchar(50) NOT NULL COMMENT '用户姓名',
  `password` varchar(200) NOT NULL COMMENT '密码',
  `email` varchar(50) NOT NULL COMMENT '联系邮箱',
  `mobile` varchar(20) NOT NULL COMMENT '联系手机',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  `operator_id` bigint(20) NOT NULL COMMENT '后台操作人ID',
  `operator_ip` varchar(20) NOT NULL COMMENT '操作者IP',
  PRIMARY KEY (`id`),
  KEY `ix_created_at` (`created_at`),
  KEY `ix_updated_at` (`updated_at`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='后台用户';

-- 数据导出被取消选择。


-- 导出  表 dictator.dictator_user_role 结构
CREATE TABLE IF NOT EXISTS `dictator_user_role` (
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户-角色关联表';

-- 数据导出被取消选择。
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
