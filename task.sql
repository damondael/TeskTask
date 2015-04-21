-- --------------------------------------------------------
-- Хост:                         127.0.0.1
-- Версия сервера:               5.6.14 - MySQL Community Server (GPL)
-- ОС Сервера:                   Win32
-- HeidiSQL Версия:              9.1.0.4867
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Дамп структуры базы данных magenta
CREATE DATABASE IF NOT EXISTS `magenta` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `magenta`;


-- Дамп структуры для таблица magenta.tasks
CREATE TABLE IF NOT EXISTS `tasks` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL DEFAULT '0',
  `name` varchar(50) NOT NULL DEFAULT '0',
  `lastStartTime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `totalTime` int(11) NOT NULL DEFAULT '0',
  `isActive` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `Индекс 2` (`name`,`userId`),
  KEY `FK_tasks_users` (`userId`),
  CONSTRAINT `FK_tasks_users` FOREIGN KEY (`userId`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8;

-- Дамп данных таблицы magenta.tasks: ~17 rows (приблизительно)
/*!40000 ALTER TABLE `tasks` DISABLE KEYS */;
INSERT IGNORE INTO `tasks` (`id`, `userId`, `name`, `lastStartTime`, `totalTime`, `isActive`) VALUES
	(26, 1, 'fdfsd', '2015-04-21 12:40:09', 120, 0),
	(27, 1, '321', '2015-04-21 12:43:04', 1998, 0),
	(28, 1, '2135', '2015-04-21 11:53:51', 1345, 0),
	(29, 1, '2135s', '2015-04-21 11:55:02', 1276, 0),
	(30, 1, 'tes?', '2015-04-21 12:16:27', 1, 0),
	(31, 1, 'tes?ss', '2015-04-21 12:16:32', 1, 0),
	(32, 1, '21321ss', '2015-04-21 12:22:14', 11, 0),
	(33, 1, 'dfgdf', '2015-04-21 12:32:55', 14, 0),
	(34, 1, 'dfsd111', '2015-04-21 12:33:28', 70, 0),
	(35, 1, 'gfhjgjgjjfg', '2015-04-21 12:36:55', 170, 0),
	(36, 1, 'dfsd', '2015-04-21 12:39:49', 6, 0),
	(37, 2, 'rte', '2015-04-21 12:42:42', 3, 0),
	(38, 2, '321', '2015-04-21 12:42:48', 3, 0),
	(39, 1, 'dasdas', '2015-04-21 13:18:37', 3, 0),
	(40, 10, 'ss', '2015-04-21 13:21:58', 2, 0),
	(41, 10, 'ss11', '2015-04-21 13:22:03', 0, 0),
	(42, 8, '123', '2015-04-21 13:30:25', 2, 0),
	(43, 8, 'sadsa', '2015-04-21 13:44:31', 22, 0);
/*!40000 ALTER TABLE `tasks` ENABLE KEYS */;


-- Дамп структуры для таблица magenta.users
CREATE TABLE IF NOT EXISTS `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `Индекс 2` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- Дамп данных таблицы magenta.users: ~8 rows (приблизительно)
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT IGNORE INTO `users` (`id`, `username`) VALUES
	(2, '11'),
	(6, '11sss'),
	(1, '12'),
	(8, '123'),
	(9, '12321s'),
	(10, '12??'),
	(7, 'sfsdfsd'),
	(3, 'ss1s'),
	(5, 'sss2'),
	(4, 'sss2s');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
