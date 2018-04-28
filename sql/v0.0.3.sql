-- 0.0.3 变更的表结构和数据
ALTER TABLE `dictator_config` ADD COLUMN `remark` VARCHAR(280) NOT NULL DEFAULT '' COMMENT '备注' AFTER `version`;
ALTER TABLE `dictator_config_history` ADD COLUMN `remark` VARCHAR(280) NOT NULL DEFAULT '' COMMENT '备注' AFTER `version`;