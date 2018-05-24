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