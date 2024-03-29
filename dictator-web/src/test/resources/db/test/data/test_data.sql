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
-- 正在导出表  dictator.dictator_config 的数据：~4 rows (大约)
/*!40000 ALTER TABLE `dictator_config` DISABLE KEYS */;
INSERT INTO `dictator_config` (`id`, `app_id`, `profile_id`, `group_id`, `config_name`, `config_value`, `version`, `created_time`, `updated_time`, `operator_id`, `operator_ip`) VALUES
	(34, 'dictator-demo', 1, 1, 'spring.datasource.url', 'jdbc:mysql://test', 2, '2018-02-17 12:33:10', '2018-03-03 21:30:43', 1, '0:0:0:0:0:0:0:1'),
	(35, 'dictator-demo', 1, 1, 'spring.datasource.username', 'root', 0, '2018-03-03 21:31:05', '2018-03-03 21:31:05', 1, '0:0:0:0:0:0:0:1'),
	(36, 'dictator-demo', 1, 1, 'spring.datasource.driver-class-name', 'com.mysql.jdbc.Driver', 0, '2018-03-03 21:32:00', '2018-03-03 21:32:00', 1, '0:0:0:0:0:0:0:1'),
	(37, 'dictator-demo', 1, 1, 'spring.datasource.password', 'root', 0, '2018-03-03 21:32:21', '2018-03-03 21:32:21', 1, '0:0:0:0:0:0:0:1');
/*!40000 ALTER TABLE `dictator_config` ENABLE KEYS */;

-- 正在导出表  dictator.dictator_config_group 的数据：~1 rows (大约)
/*!40000 ALTER TABLE `dictator_config_group` DISABLE KEYS */;
INSERT INTO `dictator_config_group` (`id`, `group_name`, `created_at`, `updated_at`, `operator_id`, `operator_ip`) VALUES
	(1, '开发环境1', '2018-02-17 13:10:39', '2018-02-17 13:10:39', 0, ' ');
/*!40000 ALTER TABLE `dictator_config_group` ENABLE KEYS */;

-- 正在导出表  dictator.dictator_config_history 的数据：~15 rows (大约)
/*!40000 ALTER TABLE `dictator_config_history` DISABLE KEYS */;
INSERT INTO `dictator_config_history` (`id`, `app_id`, `config_id`, `profile_id`, `group_id`, `config_name`, `config_value`, `version`, `created_time`, `updated_time`, `history_created_time`, `operator_id`, `operator_ip`) VALUES
	(1, 'app', 1, 1, 1, 'spring.profile.active', 'dev', 1, '2018-02-17 12:33:10', '2018-02-20 14:34:53', '2018-02-20 14:41:46', 1, '0:0:0:0:0:0:0:1'),
	(35, 'app', 1, 1, 1, 'spring.banner.enable', 'false', 0, '2018-02-17 12:33:10', '2018-02-20 14:40:45', '2018-02-20 14:50:48', 1, '0:0:0:0:0:0:0:1'),
	(36, 'app', 8, 1, 1, 'spring.active.profile', 'dev', 1, '2018-02-18 21:20:30', '2018-02-20 14:05:41', '2018-02-20 14:50:50', 1, '0:0:0:0:0:0:0:1'),
	(37, 'app', 3, 1, 1, 'aaa', 'aaa', 1, '2018-02-19 14:21:53', '2018-02-20 14:05:47', '2018-02-20 14:50:51', 1, '0:0:0:0:0:0:0:1'),
	(38, 'abb', 9, 1, 1, 'spring.a', 'aaaaa', 0, '2018-02-20 13:10:43', '2018-02-20 13:10:43', '2018-02-20 14:50:52', 1, '0:0:0:0:0:0:0:1'),
	(39, 'aaa', 10, 1, 1, 'asdfdsfafdsa', 'sfdafdasfdas', 0, '2018-02-20 13:15:48', '2018-02-20 13:15:48', '2018-02-20 14:50:52', 1, '0:0:0:0:0:0:0:1'),
	(40, 'aaaf', 11, 1, 1, 'asfdfdsafdas', 'fdasasfdsfdafasd', 0, '2018-02-20 13:16:20', '2018-02-20 13:16:20', '2018-02-20 14:50:54', 1, '0:0:0:0:0:0:0:1'),
	(41, 'asffdasfdas', 12, 1, 1, 'fafdasfdasfdas', 'fdasfdsafsdasfda', 0, '2018-02-20 13:36:30', '2018-02-20 13:36:30', '2018-02-20 14:50:55', 1, '0:0:0:0:0:0:0:1'),
	(42, 'safd', 13, 1, 1, 'fasdsfadsda', 'sadffsdafsdadafsad', 1, '2018-02-20 13:42:21', '2018-02-20 14:05:25', '2018-02-20 14:50:56', 1, '0:0:0:0:0:0:0:1'),
	(43, 'aaaaaa', 14, 1, 1, 'fsadsdfaxczv', 'dsfagasdf', 1, '2018-02-20 13:42:32', '2018-02-20 14:05:33', '2018-02-20 14:50:57', 1, '0:0:0:0:0:0:0:1'),
	(44, 'asfdfdsafasdasfd', 15, 1, 1, 'gsadfdasdsa', 'xczvhdfssdaf', 1, '2018-02-20 13:43:00', '2018-02-20 13:43:00', '2018-02-20 14:50:58', 1, '0:0:0:0:0:0:0:1'),
	(45, 'a', 16, 1, 1, 'cc', 'fggasgasgas', 3, '2018-02-20 14:09:49', '2018-02-20 14:13:16', '2018-02-20 14:50:59', 1, '0:0:0:0:0:0:0:1'),
	(46, 'aafsafsdafsd', 17, 1, 1, 'ghsadf', 'aaaa', 3, '2018-02-20 14:13:41', '2018-02-20 14:21:56', '2018-02-20 14:51:01', 1, '0:0:0:0:0:0:0:1'),
	(47, 'app', 34, 1, 1, 'spring.profile.active', 'dev', 0, '2018-02-17 12:33:10', '2018-02-20 14:34:39', '2018-02-20 14:51:49', 1, '0:0:0:0:0:0:0:1'),
	(48, 'app', 34, 1, 1, 'spring.datasource.url', 'jdbc:mysql://test', 1, '2018-02-17 12:33:10', '2018-02-20 15:27:11', '2018-03-03 21:30:43', 1, '0:0:0:0:0:0:0:1');
/*!40000 ALTER TABLE `dictator_config_history` ENABLE KEYS */;

-- 正在导出表  dictator.dictator_config_profile 的数据：~1 rows (大约)
/*!40000 ALTER TABLE `dictator_config_profile` DISABLE KEYS */;
INSERT INTO `dictator_config_profile` (`id`, `profile_name`, `profile_code`, `profile_desc`, `enable`, `created_time`, `updated_time`, `operator_id`, `operator_ip`) VALUES
	(1, '开发环境', 'dev', '', b'1', '2018-02-20 12:10:34', '2018-02-20 12:10:35', 0, ' ');
/*!40000 ALTER TABLE `dictator_config_profile` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
