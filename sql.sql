-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: amadeus
-- ------------------------------------------------------
-- Server version	5.7.17-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `announcement`
--

DROP TABLE IF EXISTS `announcement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `announcement` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `pmId` int(11) DEFAULT NULL,
  `question` varchar(200) DEFAULT NULL,
  `answer` varchar(200) DEFAULT NULL,
  `isHidden` int(11) DEFAULT '0',
  `isAnswered` int(11) DEFAULT '0',
  `isFromCreator` int(11) DEFAULT '0',
  `createClass` varchar(100) DEFAULT NULL,
  `createUser` varchar(30) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `updateUser` varchar(45) DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `updateClass` varchar(45) DEFAULT NULL,
  `deleteFlg` varchar(1) DEFAULT '0',
  `deleteTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `announcement`
--

LOCK TABLES `announcement` WRITE;
/*!40000 ALTER TABLE `announcement` DISABLE KEYS */;
INSERT INTO `announcement` VALUES (2,10,'31eqw',NULL,0,0,0,'com.minh.nguyen.service.ContestService','admin','2018-02-19 12:36:59','admin','2018-02-19 12:36:59','com.minh.nguyen.service.ContestService','0',NULL),(3,0,NULL,'ƒê√¢y l√† m·ªôt th√¥ng b√°o quan h·∫øt',0,1,1,'com.minh.nguyen.service.ContestService','admin','2018-02-19 13:51:40','admin','2018-02-19 13:51:40','com.minh.nguyen.service.ContestService','0',NULL),(5,10,'gehe',NULL,1,0,0,'com.minh.nguyen.service.ContestService','user','2018-02-19 15:36:23','admin','2018-03-02 18:34:05','com.minh.nguyen.service.ContestService','0',NULL),(6,0,NULL,'ƒë√°\r\nyy\r\neqwe\r\nfdsf',0,1,1,'com.minh.nguyen.service.ContestService','admin','2018-02-19 15:58:22','admin','2018-02-19 15:58:22','com.minh.nguyen.service.ContestService','0',NULL),(7,10,NULL,'ƒë√¢sd',0,1,1,'com.minh.nguyen.service.ContestService','admin','2018-02-19 16:00:34','admin','2018-02-19 16:00:34','com.minh.nguyen.service.ContestService','0',NULL),(8,21,NULL,'qwe\r\nrrr',0,1,1,'com.minh.nguyen.service.ContestService','admin','2018-02-19 16:08:38','admin','2018-03-02 18:34:00','com.minh.nguyen.service.ContestService','0',NULL),(9,10,'what\r\nis this\r\nquestion','hehe',0,1,0,'com.minh.nguyen.service.ContestService','user','2018-02-19 21:34:36','admin','2018-02-19 21:47:16','com.minh.nguyen.service.ContestService','0',NULL),(10,0,'',NULL,0,0,0,'com.minh.nguyen.service.ContestService','user','2018-02-19 21:47:33','user','2018-02-19 21:47:33','com.minh.nguyen.service.ContestService','0',NULL);
/*!40000 ALTER TABLE `announcement` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `application`
--

DROP TABLE IF EXISTS `application`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `application` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `urId` int(11) DEFAULT NULL,
  `ceId` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT '0',
  `createClass` varchar(100) DEFAULT NULL,
  `createUser` varchar(30) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `updateUser` varchar(45) DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `updateClass` varchar(45) DEFAULT NULL,
  `deleteFlg` varchar(1) DEFAULT '0',
  `deleteTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `index_numerwan` (`id`,`urId`,`ceId`,`status`),
  KEY `index_numerchu` (`urId`,`ceId`,`status`),
  KEY `index_numertree` (`urId`,`ceId`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `application`
--

LOCK TABLES `application` WRITE;
/*!40000 ALTER TABLE `application` DISABLE KEYS */;
INSERT INTO `application` VALUES (6,2,3,-1,'com.minh.nguyen.service.CourseService','user','2018-03-21 23:30:20','admin','2018-03-21 23:47:43','com.minh.nguyen.service.CourseService','0',NULL),(7,2,3,-1,'com.minh.nguyen.service.CourseService','user','2018-03-22 11:24:47','admin','2018-03-22 11:28:04','com.minh.nguyen.service.CourseService','0',NULL),(8,2,3,-1,'com.minh.nguyen.service.CourseService','user','2018-03-22 11:28:44','admin','2018-03-22 11:29:45','com.minh.nguyen.service.CourseService','0',NULL),(9,2,3,-1,'com.minh.nguyen.service.CourseService','user','2018-03-22 11:30:10','admin','2018-03-22 11:31:06','com.minh.nguyen.service.CourseService','0',NULL),(10,2,3,-1,'com.minh.nguyen.service.CourseService','user','2018-03-22 11:31:47','admin','2018-03-22 11:32:26','com.minh.nguyen.service.CourseService','0',NULL),(11,2,3,-1,'com.minh.nguyen.service.CourseService','user','2018-03-22 11:37:51','admin','2018-03-22 11:38:28','com.minh.nguyen.service.CourseService','0',NULL),(12,2,3,-1,'com.minh.nguyen.service.CourseService','user','2018-03-22 13:23:46','admin','2018-03-22 13:24:20','com.minh.nguyen.service.CourseService','0',NULL),(13,2,3,-1,'com.minh.nguyen.service.CourseService','user','2018-03-22 13:25:10','admin','2018-03-22 13:25:43','com.minh.nguyen.service.CourseService','0',NULL),(14,2,3,-1,'com.minh.nguyen.service.CourseService','user','2018-03-22 13:27:30','admin','2018-03-22 13:28:06','com.minh.nguyen.service.CourseService','0',NULL),(15,2,3,1,'com.minh.nguyen.service.CourseService','user','2018-03-22 14:07:40','admin','2018-03-22 14:07:56','com.minh.nguyen.service.CourseService','0',NULL);
/*!40000 ALTER TABLE `application` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `authority`
--

DROP TABLE IF EXISTS `authority`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `authority` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `createClass` varchar(100) DEFAULT NULL,
  `createUser` varchar(30) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `updateUser` varchar(45) DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `updateClass` varchar(45) DEFAULT NULL,
  `deleteFlg` varchar(1) DEFAULT '0',
  `deleteTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `authority`
--

LOCK TABLES `authority` WRITE;
/*!40000 ALTER TABLE `authority` DISABLE KEYS */;
INSERT INTO `authority` VALUES (1,'CAN_CREATE_USER',NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL),(2,'CAN_EDIT_USER',NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL),(3,'CAN_CREATE_PROBLEM',NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL),(4,'CAN_EDIT_PROBLEM',NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL),(5,'CAN_VIEW_PROBLEM',NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL),(6,'CAN_CREATE_CONTEST',NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL),(7,'CAN_EDIT_CONTEST',NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL),(8,'CAN_VIEW_CONTEST',NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL),(9,'CAN_PARTICIPATE_CONTEST',NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL),(10,'CAN_VIEW_ALL_PROBLEM',NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL),(11,'CAN_VIEW_ALL_CONTEST',NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL),(12,'CAN_SUBMIT_PROBLEM',NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL),(13,'CAN_VIEW_ALL_SUBMISSION',NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL),(14,'CAN_PARTICIPATE_COURSE',NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL),(15,'CAN_VIEW_COURSE',NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL),(16,'CAN_EDIT_COURSE',NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL),(17,'CAN_VIEW_ALL_COURSE',NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL),(18,'CAN_CREATE_COURSE',NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL),(19,'CAN_UPLOAD_MATERIAL',NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL),(20,'CAN_VIEW_ALL_MATERIAL',NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL);
/*!40000 ALTER TABLE `authority` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ce_at`
--

DROP TABLE IF EXISTS `ce_at`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ce_at` (
  `ceId` int(11) NOT NULL,
  `atId` int(11) NOT NULL,
  `createClass` varchar(100) DEFAULT NULL,
  `createUser` varchar(30) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `updateUser` varchar(45) DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `updateClass` varchar(45) DEFAULT NULL,
  `deleteFlg` varchar(1) DEFAULT '0',
  `deleteTime` datetime DEFAULT NULL,
  PRIMARY KEY (`ceId`,`atId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ce_at`
--

LOCK TABLES `ce_at` WRITE;
/*!40000 ALTER TABLE `ce_at` DISABLE KEYS */;
/*!40000 ALTER TABLE `ce_at` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ce_ml`
--

DROP TABLE IF EXISTS `ce_ml`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ce_ml` (
  `ceId` int(11) NOT NULL,
  `mlId` int(11) NOT NULL,
  `isHidden` int(11) DEFAULT NULL,
  `createClass` varchar(100) DEFAULT NULL,
  `createUser` varchar(30) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `updateUser` varchar(45) DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `updateClass` varchar(45) DEFAULT NULL,
  `deleteFlg` varchar(1) DEFAULT '0',
  `deleteTime` datetime DEFAULT NULL,
  PRIMARY KEY (`ceId`,`mlId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ce_ml`
--

LOCK TABLES `ce_ml` WRITE;
/*!40000 ALTER TABLE `ce_ml` DISABLE KEYS */;
INSERT INTO `ce_ml` VALUES (3,11,1,'com.minh.nguyen.service.CourseService','admin','2018-03-19 14:54:07','admin','2018-03-20 11:36:53','com.minh.nguyen.service.CourseService','0',NULL),(3,12,0,'com.minh.nguyen.service.CourseService','admin','2018-03-19 14:42:29','admin','2018-03-19 14:42:29','com.minh.nguyen.service.CourseService','0',NULL);
/*!40000 ALTER TABLE `ce_ml` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ce_pm`
--

DROP TABLE IF EXISTS `ce_pm`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ce_pm` (
  `ceId` int(11) NOT NULL,
  `pmId` int(11) NOT NULL,
  `isHidden` int(11) DEFAULT NULL,
  `createClass` varchar(100) DEFAULT NULL,
  `createUser` varchar(30) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `updateUser` varchar(45) DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `updateClass` varchar(45) DEFAULT NULL,
  `deleteFlg` varchar(1) DEFAULT '0',
  `deleteTime` datetime DEFAULT NULL,
  PRIMARY KEY (`ceId`,`pmId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ce_pm`
--

LOCK TABLES `ce_pm` WRITE;
/*!40000 ALTER TABLE `ce_pm` DISABLE KEYS */;
INSERT INTO `ce_pm` VALUES (3,10,0,'com.minh.nguyen.service.CourseService','admin','2018-03-23 13:40:25','admin','2018-03-23 13:40:25','com.minh.nguyen.service.CourseService','0',NULL);
/*!40000 ALTER TABLE `ce_pm` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ce_sn`
--

DROP TABLE IF EXISTS `ce_sn`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ce_sn` (
  `ceId` int(11) NOT NULL,
  `snId` int(11) NOT NULL,
  `createClass` varchar(100) DEFAULT NULL,
  `createUser` varchar(30) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `updateUser` varchar(45) DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `updateClass` varchar(45) DEFAULT NULL,
  `deleteFlg` varchar(1) DEFAULT '0',
  `deleteTime` datetime DEFAULT NULL,
  PRIMARY KEY (`ceId`,`snId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ce_sn`
--

LOCK TABLES `ce_sn` WRITE;
/*!40000 ALTER TABLE `ce_sn` DISABLE KEYS */;
INSERT INTO `ce_sn` VALUES (3,171,'com.minh.nguyen.service.CourseService','user','2018-03-23 13:49:13','user','2018-03-23 13:49:13','com.minh.nguyen.service.CourseService','0',NULL),(3,172,'com.minh.nguyen.service.CourseService','user','2018-03-23 13:49:46','user','2018-03-23 13:49:46','com.minh.nguyen.service.CourseService','0',NULL),(3,173,'com.minh.nguyen.service.CourseService','user','2018-03-23 13:50:50','user','2018-03-23 13:50:50','com.minh.nguyen.service.CourseService','0',NULL),(3,180,'com.minh.nguyen.service.CourseService','user','2018-03-23 14:19:47','user','2018-03-23 14:19:47','com.minh.nguyen.service.CourseService','0',NULL);
/*!40000 ALTER TABLE `ce_sn` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `class`
--

DROP TABLE IF EXISTS `class`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `class` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `intake` int(11) DEFAULT NULL,
  `major` varchar(45) DEFAULT NULL,
  `academicYear` varchar(45) DEFAULT NULL,
  `createClass` varchar(100) DEFAULT NULL,
  `createUser` varchar(30) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `updateUser` varchar(45) DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `updateClass` varchar(45) DEFAULT NULL,
  `deleteFlg` varchar(1) DEFAULT '0',
  `deleteTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `class`
--

LOCK TABLES `class` WRITE;
/*!40000 ALTER TABLE `class` DISABLE KEYS */;
/*!40000 ALTER TABLE `class` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contest`
--

DROP TABLE IF EXISTS `contest`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `contest` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `startTime` datetime DEFAULT NULL,
  `duration` int(11) DEFAULT NULL,
  `description` text,
  `isPublic` int(11) DEFAULT '0',
  `requirement` text,
  `prize` text,
  `showTest` int(11) DEFAULT NULL,
  `judgeType` int(11) DEFAULT NULL,
  `showStatus` int(11) DEFAULT NULL,
  `canPractice` int(11) DEFAULT NULL,
  `showSubmit` int(11) DEFAULT NULL,
  `showToAll` int(11) DEFAULT '0',
  `createClass` varchar(100) DEFAULT NULL,
  `createUser` varchar(30) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `updateUser` varchar(45) DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `updateClass` varchar(45) DEFAULT NULL,
  `deleteFlg` varchar(1) DEFAULT '0',
  `deleteTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contest`
--

LOCK TABLES `contest` WRITE;
/*!40000 ALTER TABLE `contest` DISABLE KEYS */;
INSERT INTO `contest` VALUES (5,'offical sandking hehe','2018-02-25 23:08:10',321,'123',1,'22','333',3,2,1,1,1,1,NULL,'admin',NULL,NULL,NULL,NULL,NULL,NULL),(6,'L·∫≠p tr√¨nh h∆∞·ªõng ƒë·ªëi t∆∞·ª£ng','2018-02-14 10:00:00',1400,'B√†i ki·ªÉm tra s·ªë 2 - Danh s√°ch m√≥c n·ªëi',1,'Qua b√†i ki·ªÉm tra s·ªë 1','M·ªôt tr√†ng ph√°o tay th·∫≠t to',1,2,1,1,1,1,'class com.minh.nguyen.service.BaseService','admin','2018-02-02 22:05:26','admin','2018-03-23 14:17:50','com.minh.nguyen.service.ContestService','0',NULL);
/*!40000 ALTER TABLE `contest` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `conversation`
--

DROP TABLE IF EXISTS `conversation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `conversation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `topic` varchar(100) NOT NULL,
  `createClass` varchar(100) DEFAULT NULL,
  `createUser` varchar(30) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `updateUser` varchar(45) DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `updateClass` varchar(45) DEFAULT NULL,
  `deleteFlg` varchar(1) DEFAULT '0',
  `deleteTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `topic_UNIQUE` (`topic`),
  KEY `btree_topic` (`topic`) USING HASH
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `conversation`
--

LOCK TABLES `conversation` WRITE;
/*!40000 ALTER TABLE `conversation` DISABLE KEYS */;
INSERT INTO `conversation` VALUES (1,'public',NULL,'admin',NULL,'admin',NULL,NULL,'0',NULL),(19,'2_3',NULL,NULL,'2018-03-12 23:08:15',NULL,NULL,NULL,'0',NULL),(20,'1_2',NULL,NULL,'2018-03-12 23:08:16',NULL,NULL,NULL,'0',NULL),(21,'2_4',NULL,NULL,'2018-03-12 23:08:17',NULL,NULL,NULL,'0',NULL),(22,'2_5',NULL,NULL,'2018-03-12 23:08:18',NULL,NULL,NULL,'0',NULL),(23,'2_6',NULL,NULL,'2018-03-12 23:08:19',NULL,NULL,NULL,'0',NULL),(24,'2_13',NULL,NULL,'2018-03-12 23:08:21',NULL,NULL,NULL,'0',NULL),(25,'2_14',NULL,NULL,'2018-03-12 23:08:23',NULL,NULL,NULL,'0',NULL),(26,'1_4',NULL,NULL,'2018-03-13 01:02:33',NULL,NULL,NULL,'0',NULL),(27,'1_3',NULL,NULL,'2018-03-13 01:02:36',NULL,NULL,NULL,'0',NULL),(28,'1_5',NULL,NULL,'2018-03-13 01:02:56',NULL,NULL,NULL,'0',NULL),(29,'1_6',NULL,NULL,'2018-03-13 01:02:57',NULL,NULL,NULL,'0',NULL),(30,'1_13',NULL,NULL,'2018-03-13 19:45:14',NULL,NULL,NULL,'0',NULL),(31,'1_10',NULL,NULL,'2018-03-14 13:05:46',NULL,NULL,NULL,'0',NULL),(32,'1_11',NULL,NULL,'2018-03-14 13:07:43',NULL,NULL,NULL,'0',NULL);
/*!40000 ALTER TABLE `conversation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course`
--

DROP TABLE IF EXISTS `course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `course` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `description` text,
  `requirement` text,
  `showSubmit` int(11) DEFAULT '1',
  `startTime` datetime DEFAULT NULL,
  `endTime` datetime DEFAULT NULL,
  `createClass` varchar(100) DEFAULT NULL,
  `createUser` varchar(30) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `updateUser` varchar(45) DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `updateClass` varchar(45) DEFAULT NULL,
  `deleteFlg` varchar(1) DEFAULT '0',
  `deleteTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course`
--

LOCK TABLES `course` WRITE;
/*!40000 ALTER TABLE `course` DISABLE KEYS */;
INSERT INTO `course` VALUES (3,'china_namba_wan','haha urmomgay ok','no u r',2,'2018-03-05 00:00:00','2018-06-07 00:00:00','com.minh.nguyen.service.CourseService','admin','2018-03-15 21:43:00','admin','2018-03-23 22:50:09','com.minh.nguyen.service.CourseService','0',NULL);
/*!40000 ALTER TABLE `course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cs_ur`
--

DROP TABLE IF EXISTS `cs_ur`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cs_ur` (
  `csId` int(11) NOT NULL,
  `urId` int(11) NOT NULL,
  `createClass` varchar(100) DEFAULT NULL,
  `createUser` varchar(30) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `updateUser` varchar(45) DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `updateClass` varchar(45) DEFAULT NULL,
  `deleteFlg` varchar(1) DEFAULT '0',
  `deleteTime` datetime DEFAULT NULL,
  PRIMARY KEY (`csId`,`urId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cs_ur`
--

LOCK TABLES `cs_ur` WRITE;
/*!40000 ALTER TABLE `cs_ur` DISABLE KEYS */;
/*!40000 ALTER TABLE `cs_ur` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ct_at`
--

DROP TABLE IF EXISTS `ct_at`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ct_at` (
  `ctId` int(11) NOT NULL,
  `atId` int(11) NOT NULL,
  `createClass` varchar(100) DEFAULT NULL,
  `createUser` varchar(30) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `updateUser` varchar(45) DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `updateClass` varchar(45) DEFAULT NULL,
  `deleteFlg` varchar(1) DEFAULT '0',
  `deleteTime` datetime DEFAULT NULL,
  PRIMARY KEY (`ctId`,`atId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ct_at`
--

LOCK TABLES `ct_at` WRITE;
/*!40000 ALTER TABLE `ct_at` DISABLE KEYS */;
INSERT INTO `ct_at` VALUES (6,2,'com.minh.nguyen.service.ContestService','admin','2018-02-19 12:36:59','admin','2018-02-19 12:36:59','com.minh.nguyen.service.ContestService','0',NULL),(6,3,'com.minh.nguyen.service.ContestService','admin','2018-02-19 13:51:40','admin','2018-02-19 13:51:40','com.minh.nguyen.service.ContestService','0',NULL),(6,5,'com.minh.nguyen.service.ContestService','user','2018-02-19 15:36:23','user','2018-02-19 15:36:23','com.minh.nguyen.service.ContestService','0',NULL),(6,6,'com.minh.nguyen.service.ContestService','admin','2018-02-19 15:58:22','admin','2018-02-19 15:58:22','com.minh.nguyen.service.ContestService','0',NULL),(6,7,'com.minh.nguyen.service.ContestService','admin','2018-02-19 16:00:34','admin','2018-02-19 16:00:34','com.minh.nguyen.service.ContestService','0',NULL),(6,8,'com.minh.nguyen.service.ContestService','admin','2018-02-19 16:08:38','admin','2018-02-19 16:08:38','com.minh.nguyen.service.ContestService','0',NULL),(6,9,'com.minh.nguyen.service.ContestService','user','2018-02-19 21:34:36','user','2018-02-19 21:34:36','com.minh.nguyen.service.ContestService','0',NULL);
/*!40000 ALTER TABLE `ct_at` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ct_pm`
--

DROP TABLE IF EXISTS `ct_pm`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ct_pm` (
  `ctId` int(10) unsigned NOT NULL,
  `pmId` int(11) NOT NULL,
  `isHidden` int(11) DEFAULT NULL,
  `createClass` varchar(100) DEFAULT NULL,
  `createUser` varchar(30) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `updateUser` varchar(45) DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `updateClass` varchar(45) DEFAULT NULL,
  `deleteFlg` varchar(1) DEFAULT '0',
  `deleteTime` datetime DEFAULT NULL,
  PRIMARY KEY (`ctId`,`pmId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ct_pm`
--

LOCK TABLES `ct_pm` WRITE;
/*!40000 ALTER TABLE `ct_pm` DISABLE KEYS */;
INSERT INTO `ct_pm` VALUES (6,10,0,'class com.minh.nguyen.service.BaseService','minh.nt','2018-02-04 11:07:31','admin','2018-02-14 14:57:11','com.minh.nguyen.service.ContestService','0',NULL),(6,15,1,'com.minh.nguyen.service.ContestService','admin','2018-02-04 23:22:03','admin','2018-02-04 23:22:07','com.minh.nguyen.service.ContestService','0',NULL),(6,20,0,'com.minh.nguyen.service.ContestService','admin','2018-02-14 14:13:47','admin','2018-02-14 14:56:45','com.minh.nguyen.service.ContestService','0',NULL),(6,21,0,'com.minh.nguyen.service.ContestService','admin','2018-02-14 13:30:26','admin','2018-02-19 18:47:35','com.minh.nguyen.service.ContestService','0',NULL);
/*!40000 ALTER TABLE `ct_pm` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ct_sn`
--

DROP TABLE IF EXISTS `ct_sn`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ct_sn` (
  `ctId` int(11) NOT NULL,
  `snId` int(11) NOT NULL,
  `createClass` varchar(100) DEFAULT NULL,
  `createUser` varchar(30) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `updateUser` varchar(45) DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `updateClass` varchar(45) DEFAULT NULL,
  `deleteFlg` varchar(1) DEFAULT '0',
  `deleteTime` datetime DEFAULT NULL,
  PRIMARY KEY (`ctId`,`snId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ct_sn`
--

LOCK TABLES `ct_sn` WRITE;
/*!40000 ALTER TABLE `ct_sn` DISABLE KEYS */;
INSERT INTO `ct_sn` VALUES (6,114,'com.minh.nguyen.service.ContestService','admin','2018-02-14 13:55:56','admin','2018-02-14 13:55:56','com.minh.nguyen.service.ContestService','0',NULL),(6,115,'com.minh.nguyen.service.ContestService','admin','2018-02-14 13:56:16','admin','2018-02-14 13:56:16','com.minh.nguyen.service.ContestService','0',NULL),(6,116,'com.minh.nguyen.service.ContestService','user','2018-02-14 14:14:57','user','2018-02-14 14:14:57','com.minh.nguyen.service.ContestService','0',NULL),(6,117,'com.minh.nguyen.service.ContestService','user','2018-02-14 14:15:12','user','2018-02-14 14:15:12','com.minh.nguyen.service.ContestService','0',NULL),(6,118,'com.minh.nguyen.service.ContestService','user','2018-02-14 15:46:32','user','2018-02-14 15:46:32','com.minh.nguyen.service.ContestService','0',NULL),(6,143,'com.minh.nguyen.service.ContestService','user','2018-03-12 13:49:20','user','2018-03-12 13:49:20','com.minh.nguyen.service.ContestService','0',NULL),(6,155,'com.minh.nguyen.service.ContestService','user','2018-03-13 13:22:02','user','2018-03-13 13:22:02','com.minh.nguyen.service.ContestService','0',NULL),(6,174,'com.minh.nguyen.service.ContestService','user','2018-03-23 13:57:02','user','2018-03-23 13:57:02','com.minh.nguyen.service.ContestService','0',NULL),(6,175,'com.minh.nguyen.service.ContestService','user','2018-03-23 13:59:47','user','2018-03-23 13:59:47','com.minh.nguyen.service.ContestService','0',NULL),(6,176,'com.minh.nguyen.service.ContestService','user','2018-03-23 14:01:05','user','2018-03-23 14:01:05','com.minh.nguyen.service.ContestService','0',NULL),(6,177,'com.minh.nguyen.service.ContestService','user','2018-03-23 14:01:55','user','2018-03-23 14:01:55','com.minh.nguyen.service.ContestService','0',NULL),(6,178,'com.minh.nguyen.service.ContestService','user','2018-03-23 14:18:22','user','2018-03-23 14:18:22','com.minh.nguyen.service.ContestService','0',NULL),(6,179,'com.minh.nguyen.service.ContestService','user','2018-03-23 14:18:40','user','2018-03-23 14:18:40','com.minh.nguyen.service.ContestService','0',NULL);
/*!40000 ALTER TABLE `ct_sn` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `input`
--

DROP TABLE IF EXISTS `input`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `input` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `input` mediumtext,
  `output` mediumtext,
  `showInput` int(11) DEFAULT NULL,
  `createClass` varchar(100) DEFAULT NULL,
  `createUser` varchar(30) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `updateUser` varchar(45) DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `updateClass` varchar(45) DEFAULT NULL,
  `deleteFlg` varchar(1) DEFAULT '0',
  `deleteTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `input`
--

LOCK TABLES `input` WRITE;
/*!40000 ALTER TABLE `input` DISABLE KEYS */;
INSERT INTO `input` VALUES (13,'323','44234234',1,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-20 15:11:01','minh.nt','2018-01-20 15:11:01','class com.minh.nguyen.service.BaseService','0',NULL),(17,'3123123','4444',1,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-20 15:13:33','minh.nt','2018-01-20 15:13:33','class com.minh.nguyen.service.BaseService','0',NULL),(18,'1 2','1\r\n2\r\n3',1,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-20 15:22:26','minh.nt','2018-01-29 23:03:08','class com.minh.nguyen.service.BaseService','1',NULL),(19,'5 10','15',0,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-20 15:22:29','admin','2018-03-17 13:32:20','com.minh.nguyen.service.ProblemService','0',NULL),(20,'123456789123456789123456789123456789123456789123456789123456789123456789123456789123456789123456789123456789','4444',0,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-20 15:37:52','minh.nt','2018-01-20 15:37:52','class com.minh.nguyen.service.BaseService','1',NULL),(21,'hehe','1234',0,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-20 16:08:05','minh.nt','2018-01-20 16:08:05','class com.minh.nguyen.service.BaseService','1',NULL),(22,'123 1','124\r\n',0,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-21 12:40:50','admin','2018-02-14 13:43:36','com.minh.nguyen.service.ProblemService','0',NULL),(23,'1 2','3',0,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-29 23:25:32','minh.nt','2018-01-29 23:25:32','class com.minh.nguyen.service.BaseService','1',NULL),(24,'123 333','456',0,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-29 23:25:55','minh.nt','2018-01-29 23:25:55','class com.minh.nguyen.service.BaseService','1',NULL),(25,'33 22','55',0,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-29 23:27:24','minh.nt','2018-01-29 23:27:24','class com.minh.nguyen.service.BaseService','1',NULL),(26,'3 5','8',0,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-13 10:23:48','minh.nt','2018-01-29 23:03:08','class com.minh.nguyen.service.BaseService','1',NULL),(27,'1 2','3',1,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-13 10:23:48','minh.nt','2018-02-04 14:21:54','class com.minh.nguyen.service.BaseService','0',NULL),(28,'1 2','3',1,'com.minh.nguyen.service.ProblemService','admin','2018-02-11 13:44:57','admin','2018-02-11 13:46:58','com.minh.nguyen.service.ProblemService','0',NULL),(29,'3 4','7',0,'com.minh.nguyen.service.ProblemService','admin','2018-02-11 13:44:57','admin','2018-02-11 13:46:58','com.minh.nguyen.service.ProblemService','0',NULL),(30,'5 4','9',0,'com.minh.nguyen.service.ProblemService','admin','2018-02-11 13:44:57','admin','2018-02-11 14:30:01','com.minh.nguyen.service.ProblemService','0',NULL),(31,'1 2','3',1,'com.minh.nguyen.service.ProblemService','admin','2018-02-14 13:27:48','admin','2018-02-14 13:30:03','com.minh.nguyen.service.ProblemService','0',NULL);
/*!40000 ALTER TABLE `input` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `language`
--

DROP TABLE IF EXISTS `language`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `language` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  `extension` varchar(45) NOT NULL,
  `createUser` varchar(30) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `createClass` varchar(45) DEFAULT NULL,
  `updateUser` varchar(45) DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `updateClass` varchar(45) DEFAULT NULL,
  `deleteFlg` varchar(1) DEFAULT NULL,
  `deleteTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `language`
--

LOCK TABLES `language` WRITE;
/*!40000 ALTER TABLE `language` DISABLE KEYS */;
INSERT INTO `language` VALUES (1,'C','c',NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL),(2,'C++11','cpp',NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL),(3,'Java','java',NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL);
/*!40000 ALTER TABLE `language` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `material`
--

DROP TABLE IF EXISTS `material`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `material` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `sutId` int(11) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `storedLocation` varchar(300) DEFAULT NULL,
  `downloadCnt` int(11) DEFAULT '0',
  `description` text,
  `isPublic` int(11) DEFAULT '1',
  `status` int(11) DEFAULT '0',
  `createClass` varchar(100) DEFAULT NULL,
  `createUser` varchar(30) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `updateUser` varchar(45) DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `updateClass` varchar(45) DEFAULT NULL,
  `deleteFlg` varchar(1) DEFAULT '0',
  `deleteTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `material`
--

LOCK TABLES `material` WRITE;
/*!40000 ALTER TABLE `material` DISABLE KEYS */;
INSERT INTO `material` VALUES (11,1,'logo-light-text.png','E:\\storage\\material\\userId-1\\logo-light-text.png',3,'picture random picture',0,1,'com.minh.nguyen.service.MaterialService','admin','2018-03-17 21:37:10','admin','2018-03-19 15:11:16','com.minh.nguyen.service.MaterialService','0',NULL),(12,NULL,'cpu-z_1.83-en.zip','E:\\storage\\material\\userId-4\\cpu-z_1.83-en.zip',0,NULL,1,1,'com.minh.nguyen.service.MaterialService','lecturer','2018-03-19 14:27:10','lecturer','2018-03-19 14:27:48','com.minh.nguyen.service.MaterialService','0',NULL);
/*!40000 ALTER TABLE `material` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `message`
--

DROP TABLE IF EXISTS `message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `message` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `urId` int(11) DEFAULT NULL,
  `cnId` int(11) DEFAULT NULL,
  `content` varchar(300) DEFAULT NULL,
  `createClass` varchar(100) DEFAULT NULL,
  `createUser` varchar(30) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `updateUser` varchar(45) DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `updateClass` varchar(45) DEFAULT NULL,
  `deleteFlg` varchar(1) DEFAULT '0',
  `deleteTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `conversation_message_id` (`cnId`) USING HASH
) ENGINE=InnoDB AUTO_INCREMENT=69 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message`
--

LOCK TABLES `message` WRITE;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
INSERT INTO `message` VALUES (13,1,20,'qweqwe',NULL,NULL,'2018-03-13 19:02:02',NULL,NULL,NULL,'0',NULL),(14,1,20,'what',NULL,NULL,'2018-03-13 19:14:40',NULL,NULL,NULL,'0',NULL),(15,1,20,'hi friend',NULL,NULL,'2018-03-13 20:54:29',NULL,NULL,NULL,'0',NULL),(16,1,20,'hohahah',NULL,NULL,'2018-03-13 20:54:39',NULL,NULL,NULL,'0',NULL),(17,2,20,'howdy',NULL,NULL,'2018-03-14 10:56:29',NULL,NULL,NULL,'0',NULL),(18,2,20,'random',NULL,NULL,'2018-03-14 10:56:35',NULL,NULL,NULL,'0',NULL),(19,2,19,'ups',NULL,NULL,'2018-03-14 11:24:59',NULL,NULL,NULL,'0',NULL),(20,2,19,'qwe',NULL,NULL,'2018-03-14 11:25:01',NULL,NULL,NULL,'0',NULL),(21,2,20,'what',NULL,NULL,'2018-03-14 11:25:15',NULL,NULL,NULL,'0',NULL),(22,2,21,'1',NULL,NULL,'2018-03-14 11:26:37',NULL,NULL,NULL,'0',NULL),(23,2,19,'2',NULL,NULL,'2018-03-14 11:26:39',NULL,NULL,NULL,'0',NULL),(24,2,20,'3',NULL,NULL,'2018-03-14 11:26:42',NULL,NULL,NULL,'0',NULL),(25,2,19,'upsq',NULL,NULL,'2018-03-14 11:26:49',NULL,NULL,NULL,'0',NULL),(26,2,24,'hi',NULL,NULL,'2018-03-14 11:26:57',NULL,NULL,NULL,'0',NULL),(27,2,20,'1 message',NULL,NULL,'2018-03-14 11:27:09',NULL,NULL,NULL,'0',NULL),(28,2,19,'2 message',NULL,NULL,'2018-03-14 11:27:15',NULL,NULL,NULL,'0',NULL),(29,2,19,'3 message',NULL,NULL,'2018-03-14 11:27:23',NULL,NULL,NULL,'0',NULL),(30,2,19,'wer',NULL,NULL,'2018-03-14 11:27:26',NULL,NULL,NULL,'0',NULL),(31,2,21,'random message',NULL,NULL,'2018-03-14 11:27:32',NULL,NULL,NULL,'0',NULL),(32,2,19,'africa',NULL,NULL,'2018-03-14 11:27:48',NULL,NULL,NULL,'0',NULL),(33,2,20,'waka waka',NULL,NULL,'2018-03-14 11:27:53',NULL,NULL,NULL,'0',NULL),(34,4,26,'hi there',NULL,NULL,'2018-03-14 12:29:09',NULL,NULL,NULL,'0',NULL),(35,6,29,'hi',NULL,NULL,'2018-03-14 13:05:29',NULL,NULL,NULL,'0',NULL),(36,10,31,'qwe',NULL,NULL,'2018-03-14 13:05:47',NULL,NULL,NULL,'0',NULL),(37,11,32,'this is a test of  a very long message',NULL,NULL,'2018-03-14 13:07:51',NULL,NULL,NULL,'0',NULL),(38,1,20,'hi',NULL,NULL,'2018-03-14 13:46:48',NULL,NULL,NULL,'0',NULL),(39,1,20,'what',NULL,NULL,'2018-03-14 13:46:55',NULL,NULL,NULL,'0',NULL),(40,1,20,'send message',NULL,NULL,'2018-03-14 13:47:21',NULL,NULL,NULL,'0',NULL),(41,1,20,'try',NULL,NULL,'2018-03-14 13:47:36',NULL,NULL,NULL,'0',NULL),(42,1,20,'wqwe',NULL,NULL,'2018-03-14 13:47:51',NULL,NULL,NULL,'0',NULL),(43,1,20,'hi there',NULL,NULL,'2018-03-14 13:49:00',NULL,NULL,NULL,'0',NULL),(44,1,20,'magic',NULL,NULL,'2018-03-14 13:50:28',NULL,NULL,NULL,'0',NULL),(45,1,20,'qwe',NULL,NULL,'2018-03-14 13:52:37',NULL,NULL,NULL,'0',NULL),(46,1,20,'what',NULL,NULL,'2018-03-14 13:53:08',NULL,NULL,NULL,'0',NULL),(47,1,20,'werwer',NULL,NULL,'2018-03-14 13:53:41',NULL,NULL,NULL,'0',NULL),(48,1,20,'magic here',NULL,NULL,'2018-03-14 13:55:33',NULL,NULL,NULL,'0',NULL),(49,1,20,'werwer',NULL,NULL,'2018-03-14 13:57:32',NULL,NULL,NULL,'0',NULL),(50,1,20,'blah blayh blah',NULL,NULL,'2018-03-14 13:57:48',NULL,NULL,NULL,'0',NULL),(51,2,20,'h sao',NULL,NULL,'2018-03-14 13:59:32',NULL,NULL,NULL,'0',NULL),(52,2,20,'rrrr',NULL,NULL,'2018-03-14 14:02:50',NULL,NULL,NULL,'0',NULL),(53,2,20,'hehe',NULL,NULL,'2018-03-14 14:04:26',NULL,NULL,NULL,'0',NULL),(54,2,20,'howdy',NULL,NULL,'2018-03-14 14:21:40',NULL,NULL,NULL,'0',NULL),(55,2,20,'what\'s up',NULL,NULL,'2018-03-14 14:24:20',NULL,NULL,NULL,'0',NULL),(56,2,20,'qweqwe',NULL,NULL,'2018-03-14 14:27:07',NULL,NULL,NULL,'0',NULL),(57,2,20,'eeeeeee',NULL,NULL,'2018-03-14 14:30:16',NULL,NULL,NULL,'0',NULL),(58,2,20,'qweqwe',NULL,NULL,'2018-03-14 14:34:04',NULL,NULL,NULL,'0',NULL),(59,2,20,'hehehe',NULL,NULL,'2018-03-14 14:34:25',NULL,NULL,NULL,'0',NULL),(60,2,20,'haha yes',NULL,NULL,'2018-03-14 14:38:01',NULL,NULL,NULL,'0',NULL),(61,2,1,'haha yes',NULL,NULL,'2018-03-14 14:38:15',NULL,NULL,NULL,'0',NULL),(62,2,1,'test',NULL,NULL,'2018-03-21 18:40:42',NULL,NULL,NULL,'0',NULL),(63,2,1,'okok',NULL,NULL,'2018-03-21 18:41:07',NULL,NULL,NULL,'0',NULL),(64,2,20,'hehe of course',NULL,NULL,'2018-03-21 21:30:00',NULL,NULL,NULL,'0',NULL),(65,2,20,'no',NULL,NULL,'2018-03-21 21:34:16',NULL,NULL,NULL,'0',NULL),(66,2,20,'yo',NULL,NULL,'2018-03-21 21:35:32',NULL,NULL,NULL,'0',NULL),(67,2,20,'huhuhu',NULL,NULL,'2018-03-21 21:40:06',NULL,NULL,NULL,'0',NULL),(68,1,20,'hola amigo',NULL,NULL,'2018-04-02 13:29:50',NULL,NULL,NULL,'0',NULL);
/*!40000 ALTER TABLE `message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `messagenotification`
--

DROP TABLE IF EXISTS `messagenotification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `messagenotification` (
  `meId` int(11) NOT NULL AUTO_INCREMENT,
  `urId` int(10) NOT NULL,
  `isRead` int(11) DEFAULT '0',
  `createClass` varchar(100) DEFAULT NULL,
  `createUser` varchar(30) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `updateUser` varchar(45) DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `updateClass` varchar(45) DEFAULT NULL,
  `deleteFlg` varchar(1) DEFAULT '0',
  `deleteTime` datetime DEFAULT NULL,
  PRIMARY KEY (`meId`,`urId`)
) ENGINE=InnoDB AUTO_INCREMENT=69 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `messagenotification`
--

LOCK TABLES `messagenotification` WRITE;
/*!40000 ALTER TABLE `messagenotification` DISABLE KEYS */;
INSERT INTO `messagenotification` VALUES (13,1,1,NULL,NULL,'2018-03-13 19:02:02',NULL,NULL,NULL,'0',NULL),(13,2,1,NULL,NULL,'2018-03-13 19:02:02',NULL,NULL,NULL,'0',NULL),(14,1,1,NULL,NULL,'2018-03-13 19:14:40',NULL,NULL,NULL,'0',NULL),(14,2,1,NULL,NULL,'2018-03-13 19:14:40',NULL,NULL,NULL,'0',NULL),(15,1,1,NULL,NULL,'2018-03-13 20:54:29',NULL,NULL,NULL,'0',NULL),(15,2,1,NULL,NULL,'2018-03-13 20:54:29',NULL,NULL,NULL,'0',NULL),(16,1,1,NULL,NULL,'2018-03-13 20:54:39',NULL,NULL,NULL,'0',NULL),(16,2,1,NULL,NULL,'2018-03-13 20:54:39',NULL,NULL,NULL,'0',NULL),(17,1,1,NULL,NULL,'2018-03-14 10:56:29',NULL,NULL,NULL,'0',NULL),(17,2,1,NULL,NULL,'2018-03-14 10:56:29',NULL,NULL,NULL,'0',NULL),(18,1,1,NULL,NULL,'2018-03-14 10:56:35',NULL,NULL,NULL,'0',NULL),(18,2,1,NULL,NULL,'2018-03-14 10:56:35',NULL,NULL,NULL,'0',NULL),(19,2,1,NULL,NULL,'2018-03-14 11:24:59',NULL,NULL,NULL,'0',NULL),(19,3,0,NULL,NULL,'2018-03-14 11:24:59',NULL,NULL,NULL,'0',NULL),(20,2,1,NULL,NULL,'2018-03-14 11:25:01',NULL,NULL,NULL,'0',NULL),(20,3,0,NULL,NULL,'2018-03-14 11:25:01',NULL,NULL,NULL,'0',NULL),(21,1,1,NULL,NULL,'2018-03-14 11:25:15',NULL,NULL,NULL,'0',NULL),(21,2,1,NULL,NULL,'2018-03-14 11:25:15',NULL,NULL,NULL,'0',NULL),(22,2,1,NULL,NULL,'2018-03-14 11:26:37',NULL,NULL,NULL,'0',NULL),(22,4,0,NULL,NULL,'2018-03-14 11:26:37',NULL,NULL,NULL,'0',NULL),(23,2,1,NULL,NULL,'2018-03-14 11:26:39',NULL,NULL,NULL,'0',NULL),(23,3,0,NULL,NULL,'2018-03-14 11:26:39',NULL,NULL,NULL,'0',NULL),(24,1,1,NULL,NULL,'2018-03-14 11:26:42',NULL,NULL,NULL,'0',NULL),(24,2,1,NULL,NULL,'2018-03-14 11:26:42',NULL,NULL,NULL,'0',NULL),(25,2,1,NULL,NULL,'2018-03-14 11:26:49',NULL,NULL,NULL,'0',NULL),(25,3,0,NULL,NULL,'2018-03-14 11:26:49',NULL,NULL,NULL,'0',NULL),(26,2,1,NULL,NULL,'2018-03-14 11:26:57',NULL,NULL,NULL,'0',NULL),(26,13,0,NULL,NULL,'2018-03-14 11:26:57',NULL,NULL,NULL,'0',NULL),(27,1,1,NULL,NULL,'2018-03-14 11:27:09',NULL,NULL,NULL,'0',NULL),(27,2,1,NULL,NULL,'2018-03-14 11:27:09',NULL,NULL,NULL,'0',NULL),(28,2,1,NULL,NULL,'2018-03-14 11:27:15',NULL,NULL,NULL,'0',NULL),(28,3,0,NULL,NULL,'2018-03-14 11:27:15',NULL,NULL,NULL,'0',NULL),(29,2,1,NULL,NULL,'2018-03-14 11:27:23',NULL,NULL,NULL,'0',NULL),(29,3,0,NULL,NULL,'2018-03-14 11:27:23',NULL,NULL,NULL,'0',NULL),(30,2,1,NULL,NULL,'2018-03-14 11:27:26',NULL,NULL,NULL,'0',NULL),(30,3,0,NULL,NULL,'2018-03-14 11:27:26',NULL,NULL,NULL,'0',NULL),(31,2,1,NULL,NULL,'2018-03-14 11:27:32',NULL,NULL,NULL,'0',NULL),(31,4,0,NULL,NULL,'2018-03-14 11:27:32',NULL,NULL,NULL,'0',NULL),(32,2,1,NULL,NULL,'2018-03-14 11:27:48',NULL,NULL,NULL,'0',NULL),(32,3,0,NULL,NULL,'2018-03-14 11:27:48',NULL,NULL,NULL,'0',NULL),(33,1,1,NULL,NULL,'2018-03-14 11:27:53',NULL,NULL,NULL,'0',NULL),(33,2,1,NULL,NULL,'2018-03-14 11:27:53',NULL,NULL,NULL,'0',NULL),(34,1,1,NULL,NULL,'2018-03-14 12:29:09',NULL,NULL,NULL,'0',NULL),(34,4,1,NULL,NULL,'2018-03-14 12:29:09',NULL,NULL,NULL,'0',NULL),(35,1,1,NULL,NULL,'2018-03-14 13:05:29',NULL,NULL,NULL,'0',NULL),(35,6,1,NULL,NULL,'2018-03-14 13:05:29',NULL,NULL,NULL,'0',NULL),(36,1,1,NULL,NULL,'2018-03-14 13:05:47',NULL,NULL,NULL,'0',NULL),(36,10,1,NULL,NULL,'2018-03-14 13:05:47',NULL,NULL,NULL,'0',NULL),(37,1,1,NULL,NULL,'2018-03-14 13:07:51',NULL,NULL,NULL,'0',NULL),(37,11,1,NULL,NULL,'2018-03-14 13:07:51',NULL,NULL,NULL,'0',NULL),(38,1,1,NULL,NULL,'2018-03-14 13:46:48',NULL,NULL,NULL,'0',NULL),(38,2,1,NULL,NULL,'2018-03-14 13:46:48',NULL,NULL,NULL,'0',NULL),(39,1,1,NULL,NULL,'2018-03-14 13:46:55',NULL,NULL,NULL,'0',NULL),(39,2,1,NULL,NULL,'2018-03-14 13:46:55',NULL,NULL,NULL,'0',NULL),(40,1,1,NULL,NULL,'2018-03-14 13:47:21',NULL,NULL,NULL,'0',NULL),(40,2,1,NULL,NULL,'2018-03-14 13:47:21',NULL,NULL,NULL,'0',NULL),(41,1,1,NULL,NULL,'2018-03-14 13:47:36',NULL,NULL,NULL,'0',NULL),(41,2,1,NULL,NULL,'2018-03-14 13:47:36',NULL,NULL,NULL,'0',NULL),(42,1,1,NULL,NULL,'2018-03-14 13:47:51',NULL,NULL,NULL,'0',NULL),(42,2,1,NULL,NULL,'2018-03-14 13:47:51',NULL,NULL,NULL,'0',NULL),(43,1,1,NULL,NULL,'2018-03-14 13:49:00',NULL,NULL,NULL,'0',NULL),(43,2,1,NULL,NULL,'2018-03-14 13:49:00',NULL,NULL,NULL,'0',NULL),(44,1,1,NULL,NULL,'2018-03-14 13:50:28',NULL,NULL,NULL,'0',NULL),(44,2,1,NULL,NULL,'2018-03-14 13:50:28',NULL,NULL,NULL,'0',NULL),(45,1,1,NULL,NULL,'2018-03-14 13:52:37',NULL,NULL,NULL,'0',NULL),(45,2,1,NULL,NULL,'2018-03-14 13:52:37',NULL,NULL,NULL,'0',NULL),(46,1,1,NULL,NULL,'2018-03-14 13:53:08',NULL,NULL,NULL,'0',NULL),(46,2,1,NULL,NULL,'2018-03-14 13:53:08',NULL,NULL,NULL,'0',NULL),(47,1,1,NULL,NULL,'2018-03-14 13:53:41',NULL,NULL,NULL,'0',NULL),(47,2,1,NULL,NULL,'2018-03-14 13:53:41',NULL,NULL,NULL,'0',NULL),(48,1,1,NULL,NULL,'2018-03-14 13:55:33',NULL,NULL,NULL,'0',NULL),(48,2,1,NULL,NULL,'2018-03-14 13:55:33',NULL,NULL,NULL,'0',NULL),(49,1,1,NULL,NULL,'2018-03-14 13:57:32',NULL,NULL,NULL,'0',NULL),(49,2,1,NULL,NULL,'2018-03-14 13:57:32',NULL,NULL,NULL,'0',NULL),(50,1,1,NULL,NULL,'2018-03-14 13:57:48',NULL,NULL,NULL,'0',NULL),(50,2,1,NULL,NULL,'2018-03-14 13:57:48',NULL,NULL,NULL,'0',NULL),(51,1,1,NULL,NULL,'2018-03-14 13:59:32',NULL,NULL,NULL,'0',NULL),(51,2,1,NULL,NULL,'2018-03-14 13:59:32',NULL,NULL,NULL,'0',NULL),(52,1,1,NULL,NULL,'2018-03-14 14:02:50',NULL,NULL,NULL,'0',NULL),(52,2,1,NULL,NULL,'2018-03-14 14:02:50',NULL,NULL,NULL,'0',NULL),(53,1,1,NULL,NULL,'2018-03-14 14:04:26',NULL,NULL,NULL,'0',NULL),(53,2,1,NULL,NULL,'2018-03-14 14:04:26',NULL,NULL,NULL,'0',NULL),(54,1,1,NULL,NULL,'2018-03-14 14:21:40',NULL,NULL,NULL,'0',NULL),(54,2,1,NULL,NULL,'2018-03-14 14:21:40',NULL,NULL,NULL,'0',NULL),(55,1,1,NULL,NULL,'2018-03-14 14:24:20',NULL,NULL,NULL,'0',NULL),(55,2,1,NULL,NULL,'2018-03-14 14:24:20',NULL,NULL,NULL,'0',NULL),(56,1,1,NULL,NULL,'2018-03-14 14:27:07',NULL,NULL,NULL,'0',NULL),(56,2,1,NULL,NULL,'2018-03-14 14:27:07',NULL,NULL,NULL,'0',NULL),(57,1,1,NULL,NULL,'2018-03-14 14:30:16',NULL,NULL,NULL,'0',NULL),(57,2,1,NULL,NULL,'2018-03-14 14:30:16',NULL,NULL,NULL,'0',NULL),(58,1,1,NULL,NULL,'2018-03-14 14:34:04',NULL,NULL,NULL,'0',NULL),(58,2,1,NULL,NULL,'2018-03-14 14:34:04',NULL,NULL,NULL,'0',NULL),(59,1,1,NULL,NULL,'2018-03-14 14:34:25',NULL,NULL,NULL,'0',NULL),(59,2,1,NULL,NULL,'2018-03-14 14:34:25',NULL,NULL,NULL,'0',NULL),(60,1,1,NULL,NULL,'2018-03-14 14:38:01',NULL,NULL,NULL,'0',NULL),(60,2,1,NULL,NULL,'2018-03-14 14:38:01',NULL,NULL,NULL,'0',NULL),(64,1,1,NULL,NULL,'2018-03-21 21:30:00',NULL,NULL,NULL,'0',NULL),(64,2,1,NULL,NULL,'2018-03-21 21:30:00',NULL,NULL,NULL,'0',NULL),(65,1,1,NULL,NULL,'2018-03-21 21:34:16',NULL,NULL,NULL,'0',NULL),(65,2,1,NULL,NULL,'2018-03-21 21:34:16',NULL,NULL,NULL,'0',NULL),(66,1,1,NULL,NULL,'2018-03-21 21:35:32',NULL,NULL,NULL,'0',NULL),(66,2,1,NULL,NULL,'2018-03-21 21:35:32',NULL,NULL,NULL,'0',NULL),(67,1,1,NULL,NULL,'2018-03-21 21:40:06',NULL,NULL,NULL,'0',NULL),(67,2,1,NULL,NULL,'2018-03-21 21:40:06',NULL,NULL,NULL,'0',NULL),(68,1,1,NULL,NULL,'2018-04-02 13:29:50',NULL,NULL,NULL,'0',NULL),(68,2,0,NULL,NULL,'2018-04-02 13:29:50',NULL,NULL,NULL,'0',NULL);
/*!40000 ALTER TABLE `messagenotification` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notification`
--

DROP TABLE IF EXISTS `notification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `notification` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `urId` int(11) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `content` varchar(200) DEFAULT NULL,
  `isRead` int(11) DEFAULT '0',
  `link` varchar(200) DEFAULT NULL,
  `createClass` varchar(100) DEFAULT NULL,
  `createUser` varchar(30) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `updateUser` varchar(45) DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `updateClass` varchar(45) DEFAULT NULL,
  `deleteFlg` varchar(1) DEFAULT '0',
  `deleteTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `index_no` (`urId`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notification`
--

LOCK TABLES `notification` WRITE;
/*!40000 ALTER TABLE `notification` DISABLE KEYS */;
INSERT INTO `notification` VALUES (7,2,2,'B·∫°n ƒë√£ ƒë∆∞·ª£c ch·∫•p nh·∫≠n tham gia kh√≥a h·ªçc  <b>course_number1</b>',1,'/course/3','com.minh.nguyen.service.CourseService','admin','2018-03-21 23:30:52','user','2018-03-22 00:18:31','com.minh.nguyen.service.GeneralService','0',NULL),(8,2,4,'B·∫°n ƒë√£ b·ªã lo·∫°i kh·ªèi kh√≥a h·ªçc  <b>course_number1</b>',1,'/course/3','com.minh.nguyen.service.CourseService','admin','2018-03-21 23:47:43','user','2018-03-22 00:18:31','com.minh.nguyen.service.GeneralService','0',NULL),(9,1,1,'<b>Okabe</b> mu·ªën ƒë∆∞·ª£c gia nh·∫≠p kh√≥a h·ªçc <b>course_number1</b>',1,'/course/3/role','com.minh.nguyen.service.CourseService','user','2018-03-22 11:24:47','admin','2018-03-22 11:24:54','com.minh.nguyen.service.GeneralService','0',NULL),(10,2,2,'B·∫°n ƒë√£ ƒë∆∞·ª£c ch·∫•p nh·∫≠n tham gia kh√≥a h·ªçc  <b>course_number1</b>',1,'/course/3','com.minh.nguyen.service.CourseService','admin','2018-03-22 11:26:24','user','2018-03-22 11:26:31','com.minh.nguyen.service.GeneralService','0',NULL),(11,2,4,'B·∫°n ƒë√£ b·ªã lo·∫°i kh·ªèi kh√≥a h·ªçc  <b>course_number1</b>',1,'/course/3','com.minh.nguyen.service.CourseService','admin','2018-03-22 11:28:04','user','2018-03-22 11:28:11','com.minh.nguyen.service.GeneralService','0',NULL),(12,1,1,'<b>Okabe</b> mu·ªën ƒë∆∞·ª£c gia nh·∫≠p kh√≥a h·ªçc <b>course_number1</b>',1,'/course/3/role','com.minh.nguyen.service.CourseService','user','2018-03-22 11:28:44','admin','2018-03-22 11:29:12','com.minh.nguyen.service.GeneralService','0',NULL),(13,2,2,'B·∫°n ƒë√£ ƒë∆∞·ª£c ch·∫•p nh·∫≠n tham gia kh√≥a h·ªçc  <b>course_number1</b>',1,'/course/3','com.minh.nguyen.service.CourseService','admin','2018-03-22 11:29:20','user','2018-03-22 11:29:33','com.minh.nguyen.service.GeneralService','0',NULL),(14,2,4,'B·∫°n ƒë√£ b·ªã lo·∫°i kh·ªèi kh√≥a h·ªçc  <b>course_number1</b>',1,'/course/3','com.minh.nguyen.service.CourseService','admin','2018-03-22 11:29:45','user','2018-03-22 11:29:56','com.minh.nguyen.service.GeneralService','0',NULL),(15,1,1,'<b>Okabe</b> mu·ªën ƒë∆∞·ª£c gia nh·∫≠p kh√≥a h·ªçc <b>course_number1</b>',1,'/course/3/role','com.minh.nguyen.service.CourseService','user','2018-03-22 11:30:10','admin','2018-03-22 11:30:23','com.minh.nguyen.service.GeneralService','0',NULL),(16,2,3,'B·∫°n ƒë√£ b·ªã t·ª´ ch·ªëi tham gia kh√≥a h·ªçc  <b>course_number1</b>',1,'/course/3','com.minh.nguyen.service.CourseService','admin','2018-03-22 11:31:06','user','2018-03-22 11:31:21','com.minh.nguyen.service.GeneralService','0',NULL),(17,1,1,'<b>Okabe</b> mu·ªën ƒë∆∞·ª£c gia nh·∫≠p kh√≥a h·ªçc <b>course_number1</b>',1,'/course/3/role','com.minh.nguyen.service.CourseService','user','2018-03-22 11:31:47','admin','2018-03-22 11:31:56','com.minh.nguyen.service.GeneralService','0',NULL),(18,2,2,'B·∫°n ƒë√£ ƒë∆∞·ª£c ch·∫•p nh·∫≠n tham gia kh√≥a h·ªçc  <b>course_number1</b>',1,'/course/3','com.minh.nguyen.service.CourseService','admin','2018-03-22 11:32:04','user','2018-03-22 11:32:32','com.minh.nguyen.service.GeneralService','0',NULL),(19,2,4,'B·∫°n ƒë√£ b·ªã lo·∫°i kh·ªèi kh√≥a h·ªçc  <b>course_number1</b>',1,'/course/3','com.minh.nguyen.service.CourseService','admin','2018-03-22 11:32:26','user','2018-03-22 11:32:32','com.minh.nguyen.service.GeneralService','0',NULL),(20,1,1,'<b>Okabe</b> mu·ªën ƒë∆∞·ª£c gia nh·∫≠p kh√≥a h·ªçc <b>course_number1</b>',1,'/course/3/role','com.minh.nguyen.service.CourseService','user','2018-03-22 11:37:51','admin','2018-03-22 11:37:55','com.minh.nguyen.service.GeneralService','0',NULL),(21,2,2,'B·∫°n ƒë√£ ƒë∆∞·ª£c ch·∫•p nh·∫≠n tham gia kh√≥a h·ªçc  <b>course_number1</b>',1,'/course/3','com.minh.nguyen.service.CourseService','admin','2018-03-22 11:38:05','user','2018-03-22 11:38:16','com.minh.nguyen.service.GeneralService','0',NULL),(22,2,4,'B·∫°n ƒë√£ b·ªã lo·∫°i kh·ªèi kh√≥a h·ªçc  <b>course_number1</b>',1,'/course/3','com.minh.nguyen.service.CourseService','admin','2018-03-22 11:38:28','user','2018-03-22 13:24:25','com.minh.nguyen.service.GeneralService','0',NULL),(23,1,1,'<b>Okabe</b> mu·ªën ƒë∆∞·ª£c gia nh·∫≠p kh√≥a h·ªçc <b>course_number1</b>',1,'/course/3/role','com.minh.nguyen.service.CourseService','user','2018-03-22 13:23:46','admin','2018-03-22 13:23:54','com.minh.nguyen.service.GeneralService','0',NULL),(24,2,2,'B·∫°n ƒë√£ ƒë∆∞·ª£c ch·∫•p nh·∫≠n tham gia kh√≥a h·ªçc  <b>course_number1</b>',1,'/course/3','com.minh.nguyen.service.CourseService','admin','2018-03-22 13:24:05','user','2018-03-22 13:24:25','com.minh.nguyen.service.GeneralService','0',NULL),(25,2,4,'B·∫°n ƒë√£ b·ªã lo·∫°i kh·ªèi kh√≥a h·ªçc  <b>course_number1</b>',1,'/course/3','com.minh.nguyen.service.CourseService','admin','2018-03-22 13:24:20','user','2018-03-22 13:24:25','com.minh.nguyen.service.GeneralService','0',NULL),(26,1,1,'<b>Okabe</b> mu·ªën ƒë∆∞·ª£c gia nh·∫≠p kh√≥a h·ªçc <b>course_number1</b>',1,'/course/3/role','com.minh.nguyen.service.CourseService','user','2018-03-22 13:25:10','admin','2018-03-22 13:27:44','com.minh.nguyen.service.GeneralService','0',NULL),(27,2,2,'B·∫°n ƒë√£ ƒë∆∞·ª£c ch·∫•p nh·∫≠n tham gia kh√≥a h·ªçc  <b>course_number1</b>',1,'/course/3','com.minh.nguyen.service.CourseService','admin','2018-03-22 13:25:31','user','2018-03-22 13:25:49','com.minh.nguyen.service.GeneralService','0',NULL),(28,2,4,'B·∫°n ƒë√£ b·ªã lo·∫°i kh·ªèi kh√≥a h·ªçc  <b>course_number1</b>',1,'/course/3','com.minh.nguyen.service.CourseService','admin','2018-03-22 13:25:43','user','2018-03-22 13:25:49','com.minh.nguyen.service.GeneralService','0',NULL),(29,1,1,'<b>Okabe</b> mu·ªën ƒë∆∞·ª£c gia nh·∫≠p kh√≥a h·ªçc <b>course_number1</b>',1,'/course/3/role','com.minh.nguyen.service.CourseService','user','2018-03-22 13:27:30','admin','2018-03-22 13:27:44','com.minh.nguyen.service.GeneralService','0',NULL),(30,2,2,'B·∫°n ƒë√£ ƒë∆∞·ª£c ch·∫•p nh·∫≠n tham gia kh√≥a h·ªçc  <b>course_number1</b>',1,'/course/3','com.minh.nguyen.service.CourseService','admin','2018-03-22 13:27:47','user','2018-03-22 13:28:12','com.minh.nguyen.service.GeneralService','0',NULL),(31,2,4,'B·∫°n ƒë√£ b·ªã lo·∫°i kh·ªèi kh√≥a h·ªçc  <b>course_number1</b>',1,'/course/3','com.minh.nguyen.service.CourseService','admin','2018-03-22 13:28:06','user','2018-03-22 13:28:12','com.minh.nguyen.service.GeneralService','0',NULL),(32,1,1,'<b>Okabe</b> mu·ªën ƒë∆∞·ª£c gia nh·∫≠p kh√≥a h·ªçc <b>course_number1</b>',1,'/course/3/role','com.minh.nguyen.service.CourseService','user','2018-03-22 14:07:40','admin','2018-03-22 14:07:49','com.minh.nguyen.service.GeneralService','0',NULL),(33,2,2,'B·∫°n ƒë√£ ƒë∆∞·ª£c ch·∫•p nh·∫≠n tham gia kh√≥a h·ªçc  <b>course_number1</b>',1,'/course/3','com.minh.nguyen.service.CourseService','admin','2018-03-22 14:07:56','user','2018-03-22 14:12:07','com.minh.nguyen.service.GeneralService','0',NULL);
/*!40000 ALTER TABLE `notification` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pm_it`
--

DROP TABLE IF EXISTS `pm_it`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pm_it` (
  `pmId` int(11) NOT NULL,
  `itId` int(11) NOT NULL,
  `createClass` varchar(100) DEFAULT NULL,
  `createUser` varchar(30) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `updateUser` varchar(45) DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `updateClass` varchar(45) DEFAULT NULL,
  `deleteFlg` varchar(1) DEFAULT '0',
  `deleteTime` datetime DEFAULT NULL,
  PRIMARY KEY (`pmId`,`itId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pm_it`
--

LOCK TABLES `pm_it` WRITE;
/*!40000 ALTER TABLE `pm_it` DISABLE KEYS */;
INSERT INTO `pm_it` VALUES (10,18,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-20 15:22:26','minh.nt','2018-01-20 15:22:26','class com.minh.nguyen.service.BaseService','0',NULL),(10,19,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-20 15:22:29','minh.nt','2018-01-20 15:22:29','class com.minh.nguyen.service.BaseService','0',NULL),(10,20,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-20 15:37:52','minh.nt','2018-01-20 15:37:52','class com.minh.nguyen.service.BaseService','0',NULL),(10,21,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-20 16:08:05','minh.nt','2018-01-20 16:08:05','class com.minh.nguyen.service.BaseService','0',NULL),(10,22,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-21 12:40:50','minh.nt','2018-01-21 12:40:50','class com.minh.nguyen.service.BaseService','0',NULL),(10,23,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-29 23:25:32','minh.nt','2018-01-29 23:25:32','class com.minh.nguyen.service.BaseService','0',NULL),(10,24,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-29 23:25:55','minh.nt','2018-01-29 23:25:55','class com.minh.nguyen.service.BaseService','0',NULL),(10,25,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-29 23:27:24','minh.nt','2018-01-29 23:27:24','class com.minh.nguyen.service.BaseService','0',NULL),(10,26,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-29 23:29:31','minh.nt','2018-01-29 23:29:31','class com.minh.nguyen.service.BaseService','0',NULL),(10,27,'class com.minh.nguyen.service.BaseService','minh.nt','2018-02-04 14:23:44','minh.nt','2018-02-04 14:23:44','class com.minh.nguyen.service.BaseService','0',NULL),(20,28,'com.minh.nguyen.service.ProblemService','admin','2018-02-11 13:47:09','admin','2018-02-11 13:47:09','com.minh.nguyen.service.ProblemService','0',NULL),(20,29,'com.minh.nguyen.service.ProblemService','admin','2018-02-11 13:47:14','admin','2018-02-11 13:47:14','com.minh.nguyen.service.ProblemService','0',NULL),(20,30,'com.minh.nguyen.service.ProblemService','admin','2018-02-11 13:47:18','admin','2018-02-11 13:47:18','com.minh.nguyen.service.ProblemService','0',NULL),(21,31,'com.minh.nguyen.service.ProblemService','admin','2018-02-14 13:29:53','admin','2018-02-14 13:29:53','com.minh.nguyen.service.ProblemService','0',NULL);
/*!40000 ALTER TABLE `pm_it` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pm_tg`
--

DROP TABLE IF EXISTS `pm_tg`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pm_tg` (
  `pmId` int(11) NOT NULL,
  `tgId` int(11) NOT NULL,
  `createUser` varchar(30) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `updateUser` varchar(45) DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `updateClass` varchar(45) DEFAULT NULL,
  `deleteFlg` varchar(1) DEFAULT NULL,
  `deleteTime` datetime DEFAULT NULL,
  PRIMARY KEY (`pmId`,`tgId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pm_tg`
--

LOCK TABLES `pm_tg` WRITE;
/*!40000 ALTER TABLE `pm_tg` DISABLE KEYS */;
INSERT INTO `pm_tg` VALUES (10,1,NULL,NULL,NULL,NULL,NULL,'0',NULL),(10,2,NULL,NULL,NULL,NULL,NULL,'0',NULL);
/*!40000 ALTER TABLE `pm_tg` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `problem`
--

DROP TABLE IF EXISTS `problem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `problem` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `code` varchar(45) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `statement` text,
  `input` text,
  `output` text,
  `note` text,
  `leId` int(11) DEFAULT NULL,
  `sourceCode` text,
  `timeLimit` int(11) DEFAULT '2000',
  `memoryLimit` int(11) DEFAULT '64',
  `isPublished` int(11) DEFAULT '0',
  `isPublic` int(11) DEFAULT '0',
  `difficulty` int(11) DEFAULT '1',
  `solveCnt` int(11) DEFAULT '0',
  `firstSolve` datetime DEFAULT NULL,
  `totalSubmission` int(11) DEFAULT '0',
  `createClass` varchar(100) DEFAULT NULL,
  `createUser` varchar(30) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `updateUser` varchar(45) DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `updateClass` varchar(45) DEFAULT NULL,
  `deleteFlg` varchar(1) DEFAULT '0',
  `deleteTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `problem`
--

LOCK TABLES `problem` WRITE;
/*!40000 ALTER TABLE `problem` DISABLE KEYS */;
INSERT INTO `problem` VALUES (10,'sum','T√≠nh t·ªïng 2 s·ªë','\r\n                                            \r\n                                            <span style=\"background-color: rgb(206, 222, 231);\"><b><i>\r\n                                            \r\n                                                                                                                                                                                                                            Con</i></b> s√¥ÃÅ 198 coÃÅ g∆°Ã£i cho baÃ£n ƒëi√™ÃÄu giÃÄ kh√¥ng?</span> Khi hoÃ£c liÃ£ch s∆∞Ãâ Vi√™Ã£t \r\nNam, Vinh bi√™ÃÅt rƒÉÃÄng ngaÃÄy 19-8-1945 laÃÄ ngaÃÄy <font color=\"#00FF00\">T√¥Ãâng kh∆°Ãâi nghiÃÉa, \r\nngaÃÄy nh√¢n d√¢n caÃâ n∆∞∆°ÃÅc ta nh√¢ÃÅt t√™ÃÄ ƒë∆∞ÃÅng l√™n laÃÄm cu√¥Ã£c CaÃÅch maÃ£ng \r\nThaÃÅng TaÃÅm viÃÉ ƒëaÃ£i. Hi√™Ã£n nay, 198 ƒë∆∞∆°Ã£c ƒëƒÉÃ£t t√™n cho nhi√™ÃÄu b√™Ã£nh \r\nvi√™Ã£n, c√¥ng vi√™n, ƒë∆∞∆°ÃÄng ph√¥ÃÅ trong caÃâ n∆∞∆°ÃÅc. Con s√¥ÃÅ naÃÄy ƒëaÃÉ g∆°Ã£i yÃÅ \r\ncho Vinh khaÃâo saÃÅt daÃÉy s√¥ÃÅ SEQ98 sau ƒë√¢y: DaÃÉy s√¥ÃÅ nguy√™n kh√¥ng √¢m a<sub>1</sub>, a<sub>2</sub>, ..., a<sub>n</sub> ƒë∆∞∆°Ã£c goÃ£i laÃÄ daÃÉy SEQ198 n√™ÃÅu kh√¥ng t√¥ÃÄn taÃ£i hai chiÃâ s√¥ÃÅ i vaÃÄ j (1 ‚â§ i,j ‚â§ n) maÃÄ a<sub>i</sub>-a<sub>j</sub> hoƒÉÃ£c laÃÄ bƒÉÃÄng 1 hoƒÉÃ£c laÃÄ bƒÉÃÄng 8 hoƒÉÃ£c laÃÄ bƒÉÃÄng 9.</font><br><div><br></div><div><b><u>ViÃÅ duÃ£</u></b>:</div><div><ul><li>DaÃÉy s√¥ÃÅ nguy√™n 1, 3, 5, 7 laÃÄ daÃÉy SEQ123</li><li>DaÃÉy\r\n s√¥ÃÅ nguy√™n 7, 3, 5, 1, 9, 21 kh√¥ng phaÃâi laÃÄ daÃÉy SEQ198 b∆°Ãâi viÃÄ coÃÅ \r\nhai ph√¢ÃÄn t∆∞Ãâ 1 vaÃÄ 9 coÃÅ hi√™Ã£u 9 - 1 = 8. Tuy nhi√™n, sau khi xoÃÅa b∆°ÃÅt \r\nph√¢ÃÄn t∆∞Ãâ 1, ta thu ƒë∆∞∆°Ã£c daÃÉy 7, 3, 5, 9, 21 laÃÄ m√¥Ã£t daÃÉy SEQ198.</li><li>Vinh quan t√¢m t∆°ÃÅi baÃÄi toaÃÅn sau ƒë√¢y: Cho daÃÉy s√¥ÃÅ nguy√™n kh√¥ng √¢m b<sub>1</sub>, b<sub>2</sub>, ..., b<sub>m</sub>, haÃÉy tiÃÄm caÃÅch loaÃ£i boÃâ m√¥Ã£t s√¥ÃÅ iÃÅt nh√¢ÃÅt ph√¢ÃÄn t∆∞Ãâ cuÃâa daÃÉy ƒë√™Ãâ ƒë∆∞∆°Ã£c daÃÉy coÃÄn laÃ£i laÃÄ SEQ198.</li></ul></div>\r\n                                        \r\n                                        \r\n                                        \r\n                                        \r\n                                        \r\n                                        \r\n                                        \r\n                                        ','\r\n                                            \r\n                                            \r\n                                                                                                                                                                                                                                                                                                                                                                                                                                                        <div><ul><li><i><u><span style=\"font-size: 14px;\">DoÃÄng</span></u></i><span style=\"font-size: 14px;\"> ƒë√¢ÃÄu ch∆∞ÃÅa s√¥ÃÅ nguy√™n d∆∞∆°ng m;</span></li><li>DoÃÄng th∆∞ÃÅ hai ch∆∞ÃÅa m s√¥ÃÅ nguy√™n kh√¥ng √¢m b<sub>1</sub>, b<sub>2</sub>, ..., b<sub>m</sub> (b<sub>i</sub> ‚â§ 109).</li></ul></div>\r\n                                        \r\n                                        \r\n                                        \r\n                                        \r\n                                        \r\n                                        \r\n                                        \r\n                                        \r\n                                        \r\n                                        \r\n                                        \r\n                                        ','\r\n                                            \r\n                                            <i><u>\r\n                                                                                                                                                                                                                                                                                                                                                                                                                                                        Ghi </u></i>ra s√¥ÃÅ nguy√™n k laÃÄ s√¥ÃÅ ph√¢ÃÄn t∆∞Ãâ biÃ£ loaÃ£i boÃâ. Ghi s√¥ÃÅ 0 n√™ÃÅu daÃÉy <span style=\"background-color: rgb(107, 165, 74);\">ƒëaÃÉ cho laÃÄ SEQ198.\r\n                                        \r\n                                        \r\n                                        \r\n                                        \r\n                                        \r\n                                        \r\n                                        \r\n                                        \r\n                                        \r\n                                        \r\n                                        \r\n                                        </span>','<strike>\r\n                                            \r\n                                                                                                                                                                                                                                                                                                                                                                                                            Gi·∫£i th√≠ch: </strike>v·ªõi b·∫•t c·ª© c√°c l√†m n√†o b·∫°n ƒë·ªÅu nh·∫≠n ƒë∆∞·ª£c chu·ªói&nbsp;&nbsp;<code style=\"padding: 0px; font-family: Menlo, Monaco, \">xxxxxxxxxx</code><span style=\"background-color: rgb(255, 255, 255);\">.</span>\r\n                                        \r\n                                        \r\n                                        \r\n                                        \r\n                                        \r\n                                        \r\n                                        \r\n                                        \r\n                                        \r\n                                        ',2,'#include <bits/stdc++.h>\r\nusing namespace std;\r\nint main(){\r\n    cout << \"Hello world!\";\r\n    int a,b;\r\n    cin >> a >> b;\r\n    cout << a + b;\r\n}\r\n                            ',1234,100,1,1,2,0,'2018-03-23 13:59:47',36,'class com.minh.nguyen.service.BaseService','admin','2018-01-13 10:23:48','admin','2018-03-19 21:42:42','com.minh.nguyen.service.ProblemService','0',NULL),(15,'tiger','Con h·ªï',NULL,NULL,NULL,NULL,NULL,NULL,2000,64,0,0,2,0,NULL,0,'class com.minh.nguyen.service.BaseService','admin','2018-02-03 22:20:31','minh.nt','2018-02-03 22:20:31','class com.minh.nguyen.service.BaseService','0',NULL),(17,'friends','Barney v√† b·∫°n',NULL,NULL,NULL,NULL,NULL,NULL,2000,64,0,NULL,5,0,NULL,0,'com.minh.nguyen.service.ProblemService','admin','2018-02-07 23:16:51','admin','2018-02-07 23:16:51','com.minh.nguyen.service.ProblemService','0',NULL),(18,'sugar','B√°n ƒë∆∞·ªùng',NULL,NULL,NULL,NULL,NULL,NULL,2000,64,0,NULL,3,0,NULL,0,'com.minh.nguyen.service.ProblemService','admin','2018-02-07 23:19:19','admin','2018-02-07 23:25:05','com.minh.nguyen.service.ProblemService','0',NULL),(19,'newyear','Tinh nƒÉm',NULL,NULL,NULL,NULL,2,'√°dasd√°dasd',2000,64,0,NULL,1,0,NULL,0,'com.minh.nguyen.service.ProblemService','admin','2018-02-09 23:32:02','admin','2018-02-09 23:34:23','com.minh.nguyen.service.ProblemService','0',NULL),(20,'phone','R∆°i ƒëi·ªán tho·∫°i','                                            <p class=\"MsoNormal\">After that the code will select a random problem for you\r\nfrom my problems database based on your previously solved problems, your skills\r\nand your weaknesses. But while I was coding for implementing this great idea, I\r\nfound that, all of my problems are scattered in <b>2</b> computers. So, I have\r\nto merge them before running my code.</p>\r\n\r\n<p class=\"MsoNormal\">Now you are given the number of problems in each of the\r\ncomputers, you have to find the total number of problems. You can safely assume\r\nthat no problem is stored in multiple computers. So, all the problems are\r\ndistinct.</p>                                            \r\n                                        \r\n                                        ','                                            <p class=\"MsoNormal\">Input starts with an integer <b>T (</b><b>‚â§ 125)</b>,\r\ndenoting the number of test cases.</p>\r\n\r\n<p class=\"MsoNormal\">Each case starts with a line containing two integers <b>a</b>\r\nand <b>b</b>. <b>a</b> denotes the number of problems in the first computer and\r\n<b>b</b> denotes the number of problems in the second computer. You can safely\r\nassume that <b>a</b> and <b>b</b> will be non-negative and not greater than <b>10</b>.</p>                                            \r\n                                        \r\n                                        ','                                            For each case, print the case number and the total number of\r\nproblems. See the samples for exact formatting.                                            \r\n                                        \r\n                                        ','                                                                                        \r\n                                        \r\n                                        ',2,'#include<bits/stdc++.h>\r\nusing namespace std;\r\nint main(){\r\n    int a,b;\r\n    cin >> a >> b;\r\n    cout << a + b;\r\n}',5123,64,1,1,2,3,'2018-02-14 15:14:57',11,'com.minh.nguyen.service.ProblemService','admin','2018-02-11 13:44:57','admin','2018-02-20 16:50:37','com.minh.nguyen.service.ProblemService','0',NULL),(21,'gift','T·∫∑ng qu√†','                                                                                                                                                                                                                                                                        asd<br>\r\n                                        \r\n                                        \r\n                                        \r\n                                        \r\n                                        ','                                                                                                                                                                                                                                                                        eeasd<br>\r\n                                        \r\n                                        \r\n                                        \r\n                                        ','                                                                                                                                                                                                                                                                        ww                                            \r\n                                        \r\n                                        \r\n                                        \r\n                                        \r\n                                        \r\n                                        \r\n                                        ','<br>',NULL,NULL,2000,64,0,0,1,0,NULL,0,'com.minh.nguyen.service.ProblemService','admin','2018-02-14 13:27:48','admin','2018-02-14 13:33:02','com.minh.nguyen.service.ProblemService','0',NULL);
/*!40000 ALTER TABLE `problem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `re_auy`
--

DROP TABLE IF EXISTS `re_auy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `re_auy` (
  `reId` int(11) NOT NULL,
  `auyId` int(11) NOT NULL,
  `createClass` varchar(100) DEFAULT NULL,
  `createUser` varchar(30) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `updateUser` varchar(45) DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `updateClass` varchar(45) DEFAULT NULL,
  `deleteFlg` varchar(1) DEFAULT '0',
  `deleteTime` datetime DEFAULT NULL,
  PRIMARY KEY (`reId`,`auyId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `re_auy`
--

LOCK TABLES `re_auy` WRITE;
/*!40000 ALTER TABLE `re_auy` DISABLE KEYS */;
INSERT INTO `re_auy` VALUES (1,1,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL),(1,2,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL),(1,3,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL),(1,6,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL),(1,10,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL),(1,11,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL),(1,13,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL),(1,18,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL),(1,19,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL),(1,20,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL),(2,1,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL),(2,2,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL),(2,3,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL),(2,6,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL),(2,10,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL),(2,11,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL),(2,13,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL),(2,19,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL),(4,10,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL),(4,11,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL),(4,13,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL);
/*!40000 ALTER TABLE `re_auy` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `text` varchar(45) DEFAULT NULL,
  `createClass` varchar(100) DEFAULT NULL,
  `createUser` varchar(30) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `updateUser` varchar(45) DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `updateClass` varchar(45) DEFAULT NULL,
  `deleteFlg` varchar(1) DEFAULT '0',
  `deleteTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'ADMIN','Qu·∫£n tr·ªã vi√™n',NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL),(2,'LECTURER','Gi·∫£ng vi√™n',NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL),(3,'STUDENT','Sinh vi√™n',NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL),(4,'SUPERVISOR','L√£nh ƒë·∫°o',NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL);
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sn_sdl`
--

DROP TABLE IF EXISTS `sn_sdl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sn_sdl` (
  `snId` int(10) unsigned NOT NULL,
  `sDlId` int(10) unsigned NOT NULL,
  `createClass` varchar(100) DEFAULT NULL,
  `createUser` varchar(30) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `updateUser` varchar(45) DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `updateClass` varchar(45) DEFAULT NULL,
  `deleteFlg` varchar(1) DEFAULT NULL,
  `deleteTime` datetime DEFAULT NULL,
  PRIMARY KEY (`snId`,`sDlId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sn_sdl`
--

LOCK TABLES `sn_sdl` WRITE;
/*!40000 ALTER TABLE `sn_sdl` DISABLE KEYS */;
INSERT INTO `sn_sdl` VALUES (65,68,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:24:48','minh.nt','2018-01-31 23:24:48','class com.minh.nguyen.service.BaseService','0',NULL),(65,69,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:24:52','minh.nt','2018-01-31 23:24:52','class com.minh.nguyen.service.BaseService','0',NULL),(65,70,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:24:56','minh.nt','2018-01-31 23:24:56','class com.minh.nguyen.service.BaseService','0',NULL),(66,71,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:26:03','minh.nt','2018-01-31 23:26:03','class com.minh.nguyen.service.BaseService','0',NULL),(66,72,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:26:04','minh.nt','2018-01-31 23:26:04','class com.minh.nguyen.service.BaseService','0',NULL),(66,73,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:26:04','minh.nt','2018-01-31 23:26:04','class com.minh.nguyen.service.BaseService','0',NULL),(67,74,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:28:01','minh.nt','2018-01-31 23:28:01','class com.minh.nguyen.service.BaseService','0',NULL),(67,75,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:28:04','minh.nt','2018-01-31 23:28:04','class com.minh.nguyen.service.BaseService','0',NULL),(67,76,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:28:07','minh.nt','2018-01-31 23:28:07','class com.minh.nguyen.service.BaseService','0',NULL),(68,77,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:29:34','minh.nt','2018-01-31 23:29:34','class com.minh.nguyen.service.BaseService','0',NULL),(68,78,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:29:38','minh.nt','2018-01-31 23:29:38','class com.minh.nguyen.service.BaseService','0',NULL),(68,79,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:29:42','minh.nt','2018-01-31 23:29:42','class com.minh.nguyen.service.BaseService','0',NULL),(69,80,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:31:58','minh.nt','2018-01-31 23:31:58','class com.minh.nguyen.service.BaseService','0',NULL),(69,81,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:32:02','minh.nt','2018-01-31 23:32:02','class com.minh.nguyen.service.BaseService','0',NULL),(69,82,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:32:05','minh.nt','2018-01-31 23:32:05','class com.minh.nguyen.service.BaseService','0',NULL),(70,83,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:33:35','minh.nt','2018-01-31 23:33:35','class com.minh.nguyen.service.BaseService','0',NULL),(70,84,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:33:35','minh.nt','2018-01-31 23:33:35','class com.minh.nguyen.service.BaseService','0',NULL),(70,85,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:33:35','minh.nt','2018-01-31 23:33:35','class com.minh.nguyen.service.BaseService','0',NULL),(71,86,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:33:43','minh.nt','2018-01-31 23:33:43','class com.minh.nguyen.service.BaseService','0',NULL),(71,87,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:33:43','minh.nt','2018-01-31 23:33:43','class com.minh.nguyen.service.BaseService','0',NULL),(71,88,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:33:43','minh.nt','2018-01-31 23:33:43','class com.minh.nguyen.service.BaseService','0',NULL),(72,89,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:34:54','minh.nt','2018-01-31 23:34:54','class com.minh.nguyen.service.BaseService','0',NULL),(72,90,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:34:54','minh.nt','2018-01-31 23:34:54','class com.minh.nguyen.service.BaseService','0',NULL),(72,91,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:34:54','minh.nt','2018-01-31 23:34:54','class com.minh.nguyen.service.BaseService','0',NULL),(73,92,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:34:57','minh.nt','2018-01-31 23:34:57','class com.minh.nguyen.service.BaseService','0',NULL),(73,93,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:34:57','minh.nt','2018-01-31 23:34:57','class com.minh.nguyen.service.BaseService','0',NULL),(73,94,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:34:58','minh.nt','2018-01-31 23:34:58','class com.minh.nguyen.service.BaseService','0',NULL),(74,95,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:35:09','minh.nt','2018-01-31 23:35:09','class com.minh.nguyen.service.BaseService','0',NULL),(74,96,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:35:10','minh.nt','2018-01-31 23:35:10','class com.minh.nguyen.service.BaseService','0',NULL),(74,97,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:35:10','minh.nt','2018-01-31 23:35:10','class com.minh.nguyen.service.BaseService','0',NULL),(75,98,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:35:16','minh.nt','2018-01-31 23:35:16','class com.minh.nguyen.service.BaseService','0',NULL),(75,99,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:35:16','minh.nt','2018-01-31 23:35:16','class com.minh.nguyen.service.BaseService','0',NULL),(75,100,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:35:16','minh.nt','2018-01-31 23:35:16','class com.minh.nguyen.service.BaseService','0',NULL),(76,101,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:35:50','minh.nt','2018-01-31 23:35:50','class com.minh.nguyen.service.BaseService','0',NULL),(76,105,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:35:54','minh.nt','2018-01-31 23:35:54','class com.minh.nguyen.service.BaseService','0',NULL),(76,106,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:35:56','minh.nt','2018-01-31 23:35:56','class com.minh.nguyen.service.BaseService','0',NULL),(77,102,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:35:51','minh.nt','2018-01-31 23:35:51','class com.minh.nguyen.service.BaseService','0',NULL),(77,103,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:35:51','minh.nt','2018-01-31 23:35:51','class com.minh.nguyen.service.BaseService','0',NULL),(77,104,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:35:51','minh.nt','2018-01-31 23:35:51','class com.minh.nguyen.service.BaseService','0',NULL),(78,107,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:35:58','minh.nt','2018-01-31 23:35:58','class com.minh.nguyen.service.BaseService','0',NULL),(78,108,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:35:58','minh.nt','2018-01-31 23:35:58','class com.minh.nguyen.service.BaseService','0',NULL),(78,109,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:35:59','minh.nt','2018-01-31 23:35:59','class com.minh.nguyen.service.BaseService','0',NULL),(87,110,'com.minh.nguyen.service.JudgeService','admin','2018-02-11 14:23:54','admin','2018-02-11 14:23:54','com.minh.nguyen.service.JudgeService','0',NULL),(87,111,'com.minh.nguyen.service.JudgeService','admin','2018-02-11 14:23:54','admin','2018-02-11 14:23:54','com.minh.nguyen.service.JudgeService','0',NULL),(87,112,'com.minh.nguyen.service.JudgeService','admin','2018-02-11 14:23:54','admin','2018-02-11 14:23:54','com.minh.nguyen.service.JudgeService','0',NULL),(88,113,'com.minh.nguyen.service.JudgeService','admin','2018-02-11 14:29:14','admin','2018-02-11 14:29:14','com.minh.nguyen.service.JudgeService','0',NULL),(88,114,'com.minh.nguyen.service.JudgeService','admin','2018-02-11 14:29:15','admin','2018-02-11 14:29:15','com.minh.nguyen.service.JudgeService','0',NULL),(88,115,'com.minh.nguyen.service.JudgeService','admin','2018-02-11 14:29:16','admin','2018-02-11 14:29:16','com.minh.nguyen.service.JudgeService','0',NULL),(89,116,'com.minh.nguyen.service.JudgeService','admin','2018-02-11 14:30:24','admin','2018-02-11 14:30:24','com.minh.nguyen.service.JudgeService','0',NULL),(89,117,'com.minh.nguyen.service.JudgeService','admin','2018-02-11 14:30:24','admin','2018-02-11 14:30:24','com.minh.nguyen.service.JudgeService','0',NULL),(89,118,'com.minh.nguyen.service.JudgeService','admin','2018-02-11 14:30:24','admin','2018-02-11 14:30:24','com.minh.nguyen.service.JudgeService','0',NULL),(90,119,'com.minh.nguyen.service.JudgeService','admin','2018-02-11 14:35:50','admin','2018-02-11 14:35:50','com.minh.nguyen.service.JudgeService','0',NULL),(90,120,'com.minh.nguyen.service.JudgeService','admin','2018-02-11 14:35:50','admin','2018-02-11 14:35:50','com.minh.nguyen.service.JudgeService','0',NULL),(90,121,'com.minh.nguyen.service.JudgeService','admin','2018-02-11 14:35:51','admin','2018-02-11 14:35:51','com.minh.nguyen.service.JudgeService','0',NULL),(92,122,'com.minh.nguyen.service.JudgeService','admin','2018-02-11 15:13:59','admin','2018-02-11 15:13:59','com.minh.nguyen.service.JudgeService','0',NULL),(92,123,'com.minh.nguyen.service.JudgeService','admin','2018-02-11 15:13:59','admin','2018-02-11 15:13:59','com.minh.nguyen.service.JudgeService','0',NULL),(92,124,'com.minh.nguyen.service.JudgeService','admin','2018-02-11 15:13:59','admin','2018-02-11 15:13:59','com.minh.nguyen.service.JudgeService','0',NULL),(93,125,'com.minh.nguyen.service.JudgeService','admin','2018-02-11 15:36:50','admin','2018-02-11 15:36:50','com.minh.nguyen.service.JudgeService','0',NULL),(93,126,'com.minh.nguyen.service.JudgeService','admin','2018-02-11 15:36:50','admin','2018-02-11 15:36:50','com.minh.nguyen.service.JudgeService','0',NULL),(93,127,'com.minh.nguyen.service.JudgeService','admin','2018-02-11 15:36:50','admin','2018-02-11 15:36:50','com.minh.nguyen.service.JudgeService','0',NULL),(94,128,'com.minh.nguyen.service.JudgeService','admin','2018-02-11 15:48:55','admin','2018-02-11 15:48:55','com.minh.nguyen.service.JudgeService','0',NULL),(94,129,'com.minh.nguyen.service.JudgeService','admin','2018-02-11 15:48:55','admin','2018-02-11 15:48:55','com.minh.nguyen.service.JudgeService','0',NULL),(94,130,'com.minh.nguyen.service.JudgeService','admin','2018-02-11 15:48:55','admin','2018-02-11 15:48:55','com.minh.nguyen.service.JudgeService','0',NULL),(95,131,'com.minh.nguyen.service.JudgeService','user','2018-02-11 20:17:16','user','2018-02-11 20:17:16','com.minh.nguyen.service.JudgeService','0',NULL),(96,132,'com.minh.nguyen.service.JudgeService','user','2018-02-11 20:18:08','user','2018-02-11 20:18:08','com.minh.nguyen.service.JudgeService','0',NULL),(96,133,'com.minh.nguyen.service.JudgeService','user','2018-02-11 20:18:08','user','2018-02-11 20:18:08','com.minh.nguyen.service.JudgeService','0',NULL),(96,134,'com.minh.nguyen.service.JudgeService','user','2018-02-11 20:18:08','user','2018-02-11 20:18:08','com.minh.nguyen.service.JudgeService','0',NULL),(97,135,'com.minh.nguyen.service.JudgeService','user','2018-02-11 20:52:01','user','2018-02-11 20:52:01','com.minh.nguyen.service.JudgeService','0',NULL),(97,136,'com.minh.nguyen.service.JudgeService','user','2018-02-11 20:52:01','user','2018-02-11 20:52:01','com.minh.nguyen.service.JudgeService','0',NULL),(97,137,'com.minh.nguyen.service.JudgeService','user','2018-02-11 20:52:02','user','2018-02-11 20:52:02','com.minh.nguyen.service.JudgeService','0',NULL),(98,138,'com.minh.nguyen.service.JudgeService','user','2018-02-11 20:53:07','user','2018-02-11 20:53:07','com.minh.nguyen.service.JudgeService','0',NULL),(98,139,'com.minh.nguyen.service.JudgeService','user','2018-02-11 20:53:07','user','2018-02-11 20:53:07','com.minh.nguyen.service.JudgeService','0',NULL),(98,140,'com.minh.nguyen.service.JudgeService','user','2018-02-11 20:53:07','user','2018-02-11 20:53:07','com.minh.nguyen.service.JudgeService','0',NULL),(99,141,'com.minh.nguyen.service.JudgeService','user','2018-02-11 20:53:50','user','2018-02-11 20:53:50','com.minh.nguyen.service.JudgeService','0',NULL),(99,142,'com.minh.nguyen.service.JudgeService','user','2018-02-11 20:53:51','user','2018-02-11 20:53:51','com.minh.nguyen.service.JudgeService','0',NULL),(99,143,'com.minh.nguyen.service.JudgeService','user','2018-02-11 20:53:51','user','2018-02-11 20:53:51','com.minh.nguyen.service.JudgeService','0',NULL),(100,144,'com.minh.nguyen.service.JudgeService','user','2018-02-12 20:04:44','user','2018-02-12 20:04:44','com.minh.nguyen.service.JudgeService','0',NULL),(101,145,'com.minh.nguyen.service.JudgeService','user','2018-02-12 20:05:12','user','2018-02-12 20:05:12','com.minh.nguyen.service.JudgeService','0',NULL),(101,146,'com.minh.nguyen.service.JudgeService','user','2018-02-12 20:05:12','user','2018-02-12 20:05:12','com.minh.nguyen.service.JudgeService','0',NULL),(101,147,'com.minh.nguyen.service.JudgeService','user','2018-02-12 20:05:12','user','2018-02-12 20:05:12','com.minh.nguyen.service.JudgeService','0',NULL),(102,148,'com.minh.nguyen.service.JudgeService','user','2018-02-14 11:21:26','user','2018-02-14 11:21:26','com.minh.nguyen.service.JudgeService','0',NULL),(103,149,'com.minh.nguyen.service.JudgeService','user','2018-02-14 11:21:56','user','2018-02-14 11:21:56','com.minh.nguyen.service.JudgeService','0',NULL),(103,150,'com.minh.nguyen.service.JudgeService','user','2018-02-14 11:21:56','user','2018-02-14 11:21:56','com.minh.nguyen.service.JudgeService','0',NULL),(103,151,'com.minh.nguyen.service.JudgeService','user','2018-02-14 11:21:56','user','2018-02-14 11:21:56','com.minh.nguyen.service.JudgeService','0',NULL),(104,152,'com.minh.nguyen.service.JudgeService','user','2018-02-14 12:49:01','user','2018-02-14 12:49:01','com.minh.nguyen.service.JudgeService','0',NULL),(104,153,'com.minh.nguyen.service.JudgeService','user','2018-02-14 12:49:01','user','2018-02-14 12:49:01','com.minh.nguyen.service.JudgeService','0',NULL),(104,154,'com.minh.nguyen.service.JudgeService','user','2018-02-14 12:49:02','user','2018-02-14 12:49:02','com.minh.nguyen.service.JudgeService','0',NULL),(105,155,'com.minh.nguyen.service.JudgeService','user','2018-02-14 12:51:47','user','2018-02-14 12:51:47','com.minh.nguyen.service.JudgeService','0',NULL),(105,156,'com.minh.nguyen.service.JudgeService','user','2018-02-14 12:51:47','user','2018-02-14 12:51:47','com.minh.nguyen.service.JudgeService','0',NULL),(105,157,'com.minh.nguyen.service.JudgeService','user','2018-02-14 12:51:47','user','2018-02-14 12:51:47','com.minh.nguyen.service.JudgeService','0',NULL),(106,158,'com.minh.nguyen.service.JudgeService','user','2018-02-14 12:54:49','user','2018-02-14 12:54:49','com.minh.nguyen.service.JudgeService','0',NULL),(106,159,'com.minh.nguyen.service.JudgeService','user','2018-02-14 12:54:49','user','2018-02-14 12:54:49','com.minh.nguyen.service.JudgeService','0',NULL),(106,160,'com.minh.nguyen.service.JudgeService','user','2018-02-14 12:54:49','user','2018-02-14 12:54:49','com.minh.nguyen.service.JudgeService','0',NULL),(107,161,'com.minh.nguyen.service.JudgeService','user','2018-02-14 12:57:18','user','2018-02-14 12:57:18','com.minh.nguyen.service.JudgeService','0',NULL),(107,162,'com.minh.nguyen.service.JudgeService','user','2018-02-14 12:57:18','user','2018-02-14 12:57:18','com.minh.nguyen.service.JudgeService','0',NULL),(107,163,'com.minh.nguyen.service.JudgeService','user','2018-02-14 12:57:18','user','2018-02-14 12:57:18','com.minh.nguyen.service.JudgeService','0',NULL),(108,164,'com.minh.nguyen.service.JudgeService','admin','2018-02-14 13:30:45','admin','2018-02-14 13:30:45','com.minh.nguyen.service.JudgeService','0',NULL),(109,165,'com.minh.nguyen.service.JudgeService','admin','2018-02-14 13:31:47','admin','2018-02-14 13:31:47','com.minh.nguyen.service.JudgeService','0',NULL),(110,166,'com.minh.nguyen.service.JudgeService','admin','2018-02-14 13:39:26','admin','2018-02-14 13:39:26','com.minh.nguyen.service.JudgeService','0',NULL),(110,167,'com.minh.nguyen.service.JudgeService','admin','2018-02-14 13:39:27','admin','2018-02-14 13:39:27','com.minh.nguyen.service.JudgeService','0',NULL),(110,168,'com.minh.nguyen.service.JudgeService','admin','2018-02-14 13:39:27','admin','2018-02-14 13:39:27','com.minh.nguyen.service.JudgeService','0',NULL),(111,169,'com.minh.nguyen.service.JudgeService','admin','2018-02-14 13:39:50','admin','2018-02-14 13:39:50','com.minh.nguyen.service.JudgeService','0',NULL),(112,170,'com.minh.nguyen.service.JudgeService','admin','2018-02-14 13:44:02','admin','2018-02-14 13:44:02','com.minh.nguyen.service.JudgeService','0',NULL),(112,171,'com.minh.nguyen.service.JudgeService','admin','2018-02-14 13:44:02','admin','2018-02-14 13:44:02','com.minh.nguyen.service.JudgeService','0',NULL),(112,172,'com.minh.nguyen.service.JudgeService','admin','2018-02-14 13:44:02','admin','2018-02-14 13:44:02','com.minh.nguyen.service.JudgeService','0',NULL),(113,173,'com.minh.nguyen.service.JudgeService','admin','2018-02-14 13:54:54','admin','2018-02-14 13:54:54','com.minh.nguyen.service.JudgeService','0',NULL),(113,174,'com.minh.nguyen.service.JudgeService','admin','2018-02-14 13:54:54','admin','2018-02-14 13:54:54','com.minh.nguyen.service.JudgeService','0',NULL),(113,175,'com.minh.nguyen.service.JudgeService','admin','2018-02-14 13:54:54','admin','2018-02-14 13:54:54','com.minh.nguyen.service.JudgeService','0',NULL),(114,176,'com.minh.nguyen.service.JudgeService','admin','2018-02-14 13:56:01','admin','2018-02-14 13:56:01','com.minh.nguyen.service.JudgeService','0',NULL),(114,177,'com.minh.nguyen.service.JudgeService','admin','2018-02-14 13:56:01','admin','2018-02-14 13:56:01','com.minh.nguyen.service.JudgeService','0',NULL),(114,178,'com.minh.nguyen.service.JudgeService','admin','2018-02-14 13:56:01','admin','2018-02-14 13:56:01','com.minh.nguyen.service.JudgeService','0',NULL),(115,179,'com.minh.nguyen.service.JudgeService','admin','2018-02-14 13:56:21','admin','2018-02-14 13:56:21','com.minh.nguyen.service.JudgeService','0',NULL),(116,180,'com.minh.nguyen.service.JudgeService','user','2018-02-14 14:15:01','user','2018-02-14 14:15:01','com.minh.nguyen.service.JudgeService','0',NULL),(116,181,'com.minh.nguyen.service.JudgeService','user','2018-02-14 14:15:02','user','2018-02-14 14:15:02','com.minh.nguyen.service.JudgeService','0',NULL),(116,182,'com.minh.nguyen.service.JudgeService','user','2018-02-14 14:15:02','user','2018-02-14 14:15:02','com.minh.nguyen.service.JudgeService','0',NULL),(117,183,'com.minh.nguyen.service.JudgeService','user','2018-02-14 14:15:16','user','2018-02-14 14:15:16','com.minh.nguyen.service.JudgeService','0',NULL),(117,184,'com.minh.nguyen.service.JudgeService','user','2018-02-14 14:15:16','user','2018-02-14 14:15:16','com.minh.nguyen.service.JudgeService','0',NULL),(117,185,'com.minh.nguyen.service.JudgeService','user','2018-02-14 14:15:16','user','2018-02-14 14:15:16','com.minh.nguyen.service.JudgeService','0',NULL),(118,186,'com.minh.nguyen.service.JudgeService','user','2018-02-14 15:46:37','user','2018-02-14 15:46:37','com.minh.nguyen.service.JudgeService','0',NULL),(119,187,'com.minh.nguyen.service.JudgeService','admin','2018-02-16 20:27:23','admin','2018-02-16 20:27:23','com.minh.nguyen.service.JudgeService','0',NULL),(120,188,'com.minh.nguyen.service.JudgeService','admin','2018-02-16 20:27:33','admin','2018-02-16 20:27:33','com.minh.nguyen.service.JudgeService','0',NULL),(120,189,'com.minh.nguyen.service.JudgeService','admin','2018-02-16 20:27:34','admin','2018-02-16 20:27:34','com.minh.nguyen.service.JudgeService','0',NULL),(120,190,'com.minh.nguyen.service.JudgeService','admin','2018-02-16 20:27:34','admin','2018-02-16 20:27:34','com.minh.nguyen.service.JudgeService','0',NULL),(121,191,'com.minh.nguyen.service.JudgeService','admin','2018-02-16 20:35:05','admin','2018-02-16 20:35:05','com.minh.nguyen.service.JudgeService','0',NULL),(121,192,'com.minh.nguyen.service.JudgeService','admin','2018-02-16 20:35:06','admin','2018-02-16 20:35:06','com.minh.nguyen.service.JudgeService','0',NULL),(121,193,'com.minh.nguyen.service.JudgeService','admin','2018-02-16 20:35:06','admin','2018-02-16 20:35:06','com.minh.nguyen.service.JudgeService','0',NULL),(122,194,'com.minh.nguyen.service.JudgeService','user','2018-02-24 21:44:43','user','2018-02-24 21:44:43','com.minh.nguyen.service.JudgeService','0',NULL),(122,195,'com.minh.nguyen.service.JudgeService','user','2018-02-24 21:44:43','user','2018-02-24 21:44:43','com.minh.nguyen.service.JudgeService','0',NULL),(122,196,'com.minh.nguyen.service.JudgeService','user','2018-02-24 21:44:43','user','2018-02-24 21:44:43','com.minh.nguyen.service.JudgeService','0',NULL),(123,197,'com.minh.nguyen.service.JudgeService','user','2018-02-24 21:45:15','user','2018-02-24 21:45:15','com.minh.nguyen.service.JudgeService','0',NULL),(123,198,'com.minh.nguyen.service.JudgeService','user','2018-02-24 21:45:15','user','2018-02-24 21:45:15','com.minh.nguyen.service.JudgeService','0',NULL),(123,199,'com.minh.nguyen.service.JudgeService','user','2018-02-24 21:45:15','user','2018-02-24 21:45:15','com.minh.nguyen.service.JudgeService','0',NULL),(124,200,'com.minh.nguyen.service.JudgeService','user','2018-02-24 21:45:50','user','2018-02-24 21:45:50','com.minh.nguyen.service.JudgeService','0',NULL),(124,201,'com.minh.nguyen.service.JudgeService','user','2018-02-24 21:45:51','user','2018-02-24 21:45:51','com.minh.nguyen.service.JudgeService','0',NULL),(124,202,'com.minh.nguyen.service.JudgeService','user','2018-02-24 21:45:51','user','2018-02-24 21:45:51','com.minh.nguyen.service.JudgeService','0',NULL),(125,203,'com.minh.nguyen.service.JudgeService','user','2018-02-24 21:46:43','user','2018-02-24 21:46:43','com.minh.nguyen.service.JudgeService','0',NULL),(125,204,'com.minh.nguyen.service.JudgeService','user','2018-02-24 21:46:43','user','2018-02-24 21:46:43','com.minh.nguyen.service.JudgeService','0',NULL),(125,205,'com.minh.nguyen.service.JudgeService','user','2018-02-24 21:46:43','user','2018-02-24 21:46:43','com.minh.nguyen.service.JudgeService','0',NULL),(126,206,'com.minh.nguyen.service.JudgeService','admin','2018-02-25 21:01:36','admin','2018-02-25 21:01:36','com.minh.nguyen.service.JudgeService','0',NULL),(126,207,'com.minh.nguyen.service.JudgeService','admin','2018-02-25 21:01:36','admin','2018-02-25 21:01:36','com.minh.nguyen.service.JudgeService','0',NULL),(126,208,'com.minh.nguyen.service.JudgeService','admin','2018-02-25 21:01:36','admin','2018-02-25 21:01:36','com.minh.nguyen.service.JudgeService','0',NULL),(127,209,'com.minh.nguyen.service.JudgeService','admin','2018-02-25 21:01:43','admin','2018-02-25 21:01:43','com.minh.nguyen.service.JudgeService','0',NULL),(127,210,'com.minh.nguyen.service.JudgeService','admin','2018-02-25 21:01:43','admin','2018-02-25 21:01:43','com.minh.nguyen.service.JudgeService','0',NULL),(127,211,'com.minh.nguyen.service.JudgeService','admin','2018-02-25 21:01:43','admin','2018-02-25 21:01:43','com.minh.nguyen.service.JudgeService','0',NULL),(128,212,'com.minh.nguyen.service.JudgeService','admin','2018-02-25 21:04:27','admin','2018-02-25 21:04:27','com.minh.nguyen.service.JudgeService','0',NULL),(128,213,'com.minh.nguyen.service.JudgeService','admin','2018-02-25 21:04:27','admin','2018-02-25 21:04:27','com.minh.nguyen.service.JudgeService','0',NULL),(128,214,'com.minh.nguyen.service.JudgeService','admin','2018-02-25 21:04:27','admin','2018-02-25 21:04:27','com.minh.nguyen.service.JudgeService','0',NULL),(129,215,'com.minh.nguyen.service.JudgeService','user','2018-03-11 22:53:48','user','2018-03-11 22:53:48','com.minh.nguyen.service.JudgeService','0',NULL),(130,216,'com.minh.nguyen.service.JudgeService','user','2018-03-11 22:54:13','user','2018-03-11 22:54:13','com.minh.nguyen.service.JudgeService','0',NULL),(130,217,'com.minh.nguyen.service.JudgeService','user','2018-03-11 22:54:13','user','2018-03-11 22:54:13','com.minh.nguyen.service.JudgeService','0',NULL),(130,218,'com.minh.nguyen.service.JudgeService','user','2018-03-11 22:54:13','user','2018-03-11 22:54:13','com.minh.nguyen.service.JudgeService','0',NULL),(131,219,'com.minh.nguyen.service.JudgeService','user','2018-03-11 22:55:02','user','2018-03-11 22:55:02','com.minh.nguyen.service.JudgeService','0',NULL),(131,220,'com.minh.nguyen.service.JudgeService','user','2018-03-11 22:55:02','user','2018-03-11 22:55:02','com.minh.nguyen.service.JudgeService','0',NULL),(131,221,'com.minh.nguyen.service.JudgeService','user','2018-03-11 22:55:02','user','2018-03-11 22:55:02','com.minh.nguyen.service.JudgeService','0',NULL),(132,222,'com.minh.nguyen.service.JudgeService','user','2018-03-11 23:00:01','user','2018-03-11 23:00:01','com.minh.nguyen.service.JudgeService','0',NULL),(132,223,'com.minh.nguyen.service.JudgeService','user','2018-03-11 23:00:02','user','2018-03-11 23:00:02','com.minh.nguyen.service.JudgeService','0',NULL),(132,224,'com.minh.nguyen.service.JudgeService','user','2018-03-11 23:00:02','user','2018-03-11 23:00:02','com.minh.nguyen.service.JudgeService','0',NULL),(135,225,'com.minh.nguyen.service.JudgeService','user','2018-03-11 23:06:47','user','2018-03-11 23:06:47','com.minh.nguyen.service.JudgeService','0',NULL),(135,226,'com.minh.nguyen.service.JudgeService','user','2018-03-11 23:06:48','user','2018-03-11 23:06:48','com.minh.nguyen.service.JudgeService','0',NULL),(135,227,'com.minh.nguyen.service.JudgeService','user','2018-03-11 23:06:48','user','2018-03-11 23:06:48','com.minh.nguyen.service.JudgeService','0',NULL),(136,228,'com.minh.nguyen.service.JudgeService','user','2018-03-11 23:09:57','user','2018-03-11 23:09:57','com.minh.nguyen.service.JudgeService','0',NULL),(136,229,'com.minh.nguyen.service.JudgeService','user','2018-03-11 23:09:57','user','2018-03-11 23:09:57','com.minh.nguyen.service.JudgeService','0',NULL),(136,230,'com.minh.nguyen.service.JudgeService','user','2018-03-11 23:09:57','user','2018-03-11 23:09:57','com.minh.nguyen.service.JudgeService','0',NULL),(138,231,'com.minh.nguyen.service.JudgeService','user','2018-03-12 13:19:28','user','2018-03-12 13:19:28','com.minh.nguyen.service.JudgeService','0',NULL),(139,232,'com.minh.nguyen.service.JudgeService','user','2018-03-12 13:23:10','user','2018-03-12 13:23:10','com.minh.nguyen.service.JudgeService','0',NULL),(139,233,'com.minh.nguyen.service.JudgeService','user','2018-03-12 13:23:10','user','2018-03-12 13:23:10','com.minh.nguyen.service.JudgeService','0',NULL),(139,234,'com.minh.nguyen.service.JudgeService','user','2018-03-12 13:23:10','user','2018-03-12 13:23:10','com.minh.nguyen.service.JudgeService','0',NULL),(140,235,'com.minh.nguyen.service.JudgeService','user','2018-03-12 13:25:41','user','2018-03-12 13:25:41','com.minh.nguyen.service.JudgeService','0',NULL),(140,236,'com.minh.nguyen.service.JudgeService','user','2018-03-12 13:25:41','user','2018-03-12 13:25:41','com.minh.nguyen.service.JudgeService','0',NULL),(140,237,'com.minh.nguyen.service.JudgeService','user','2018-03-12 13:25:41','user','2018-03-12 13:25:41','com.minh.nguyen.service.JudgeService','0',NULL),(141,238,'com.minh.nguyen.service.JudgeService','user','2018-03-12 13:25:57','user','2018-03-12 13:25:57','com.minh.nguyen.service.JudgeService','0',NULL),(141,239,'com.minh.nguyen.service.JudgeService','user','2018-03-12 13:25:57','user','2018-03-12 13:25:57','com.minh.nguyen.service.JudgeService','0',NULL),(141,240,'com.minh.nguyen.service.JudgeService','user','2018-03-12 13:25:57','user','2018-03-12 13:25:57','com.minh.nguyen.service.JudgeService','0',NULL),(142,241,'com.minh.nguyen.service.JudgeService','user','2018-03-12 13:26:17','user','2018-03-12 13:26:17','com.minh.nguyen.service.JudgeService','0',NULL),(142,242,'com.minh.nguyen.service.JudgeService','user','2018-03-12 13:26:17','user','2018-03-12 13:26:17','com.minh.nguyen.service.JudgeService','0',NULL),(142,243,'com.minh.nguyen.service.JudgeService','user','2018-03-12 13:26:18','user','2018-03-12 13:26:18','com.minh.nguyen.service.JudgeService','0',NULL),(143,244,'com.minh.nguyen.service.JudgeService','user','2018-03-12 13:49:25','user','2018-03-12 13:49:25','com.minh.nguyen.service.JudgeService','0',NULL),(143,245,'com.minh.nguyen.service.JudgeService','user','2018-03-12 13:49:25','user','2018-03-12 13:49:25','com.minh.nguyen.service.JudgeService','0',NULL),(143,246,'com.minh.nguyen.service.JudgeService','user','2018-03-12 13:49:25','user','2018-03-12 13:49:25','com.minh.nguyen.service.JudgeService','0',NULL),(144,247,'com.minh.nguyen.service.JudgeService','user','2018-03-12 22:35:36','user','2018-03-12 22:35:36','com.minh.nguyen.service.JudgeService','0',NULL),(145,248,'com.minh.nguyen.service.JudgeService','user','2018-03-12 22:35:45','user','2018-03-12 22:35:45','com.minh.nguyen.service.JudgeService','0',NULL),(145,249,'com.minh.nguyen.service.JudgeService','user','2018-03-12 22:35:45','user','2018-03-12 22:35:45','com.minh.nguyen.service.JudgeService','0',NULL),(145,250,'com.minh.nguyen.service.JudgeService','user','2018-03-12 22:35:45','user','2018-03-12 22:35:45','com.minh.nguyen.service.JudgeService','0',NULL),(146,251,'com.minh.nguyen.service.JudgeService','user','2018-03-13 12:37:01','user','2018-03-13 12:37:01','com.minh.nguyen.service.JudgeService','0',NULL),(147,252,'com.minh.nguyen.service.JudgeService','user','2018-03-13 12:37:13','user','2018-03-13 12:37:13','com.minh.nguyen.service.JudgeService','0',NULL),(147,253,'com.minh.nguyen.service.JudgeService','user','2018-03-13 12:37:13','user','2018-03-13 12:37:13','com.minh.nguyen.service.JudgeService','0',NULL),(147,254,'com.minh.nguyen.service.JudgeService','user','2018-03-13 12:37:13','user','2018-03-13 12:37:13','com.minh.nguyen.service.JudgeService','0',NULL),(148,255,'com.minh.nguyen.service.JudgeService','user','2018-03-13 12:37:34','user','2018-03-13 12:37:34','com.minh.nguyen.service.JudgeService','0',NULL),(148,256,'com.minh.nguyen.service.JudgeService','user','2018-03-13 12:37:35','user','2018-03-13 12:37:35','com.minh.nguyen.service.JudgeService','0',NULL),(148,257,'com.minh.nguyen.service.JudgeService','user','2018-03-13 12:37:35','user','2018-03-13 12:37:35','com.minh.nguyen.service.JudgeService','0',NULL),(149,258,'com.minh.nguyen.service.JudgeService','user','2018-03-13 12:39:11','user','2018-03-13 12:39:11','com.minh.nguyen.service.JudgeService','0',NULL),(149,259,'com.minh.nguyen.service.JudgeService','user','2018-03-13 12:39:11','user','2018-03-13 12:39:11','com.minh.nguyen.service.JudgeService','0',NULL),(149,260,'com.minh.nguyen.service.JudgeService','user','2018-03-13 12:39:11','user','2018-03-13 12:39:11','com.minh.nguyen.service.JudgeService','0',NULL),(150,261,'com.minh.nguyen.service.JudgeService','user','2018-03-13 12:40:57','user','2018-03-13 12:40:57','com.minh.nguyen.service.JudgeService','0',NULL),(150,262,'com.minh.nguyen.service.JudgeService','user','2018-03-13 12:40:57','user','2018-03-13 12:40:57','com.minh.nguyen.service.JudgeService','0',NULL),(150,263,'com.minh.nguyen.service.JudgeService','user','2018-03-13 12:40:57','user','2018-03-13 12:40:57','com.minh.nguyen.service.JudgeService','0',NULL),(151,264,'com.minh.nguyen.service.JudgeService','user','2018-03-13 12:44:52','user','2018-03-13 12:44:52','com.minh.nguyen.service.JudgeService','0',NULL),(151,265,'com.minh.nguyen.service.JudgeService','user','2018-03-13 12:44:53','user','2018-03-13 12:44:53','com.minh.nguyen.service.JudgeService','0',NULL),(151,266,'com.minh.nguyen.service.JudgeService','user','2018-03-13 12:44:53','user','2018-03-13 12:44:53','com.minh.nguyen.service.JudgeService','0',NULL),(152,267,'com.minh.nguyen.service.JudgeService','admin','2018-03-13 13:11:46','admin','2018-03-13 13:11:46','com.minh.nguyen.service.JudgeService','0',NULL),(152,268,'com.minh.nguyen.service.JudgeService','admin','2018-03-13 13:11:47','admin','2018-03-13 13:11:47','com.minh.nguyen.service.JudgeService','0',NULL),(152,269,'com.minh.nguyen.service.JudgeService','admin','2018-03-13 13:11:47','admin','2018-03-13 13:11:47','com.minh.nguyen.service.JudgeService','0',NULL),(153,270,'com.minh.nguyen.service.JudgeService','admin','2018-03-13 13:14:34','admin','2018-03-13 13:14:34','com.minh.nguyen.service.JudgeService','0',NULL),(153,271,'com.minh.nguyen.service.JudgeService','admin','2018-03-13 13:14:34','admin','2018-03-13 13:14:34','com.minh.nguyen.service.JudgeService','0',NULL),(153,272,'com.minh.nguyen.service.JudgeService','admin','2018-03-13 13:14:34','admin','2018-03-13 13:14:34','com.minh.nguyen.service.JudgeService','0',NULL),(154,273,'com.minh.nguyen.service.JudgeService','admin','2018-03-13 13:19:07','admin','2018-03-13 13:19:07','com.minh.nguyen.service.JudgeService','0',NULL),(154,274,'com.minh.nguyen.service.JudgeService','admin','2018-03-13 13:19:07','admin','2018-03-13 13:19:07','com.minh.nguyen.service.JudgeService','0',NULL),(154,275,'com.minh.nguyen.service.JudgeService','admin','2018-03-13 13:19:07','admin','2018-03-13 13:19:07','com.minh.nguyen.service.JudgeService','0',NULL),(155,276,'com.minh.nguyen.service.JudgeService','user','2018-03-13 13:22:06','user','2018-03-13 13:22:06','com.minh.nguyen.service.JudgeService','0',NULL),(155,277,'com.minh.nguyen.service.JudgeService','user','2018-03-13 13:22:06','user','2018-03-13 13:22:06','com.minh.nguyen.service.JudgeService','0',NULL),(155,278,'com.minh.nguyen.service.JudgeService','user','2018-03-13 13:22:06','user','2018-03-13 13:22:06','com.minh.nguyen.service.JudgeService','0',NULL),(156,279,'com.minh.nguyen.service.JudgeService','admin','2018-03-13 19:29:47','admin','2018-03-13 19:29:47','com.minh.nguyen.service.JudgeService','0',NULL),(156,280,'com.minh.nguyen.service.JudgeService','admin','2018-03-13 19:29:47','admin','2018-03-13 19:29:47','com.minh.nguyen.service.JudgeService','0',NULL),(156,281,'com.minh.nguyen.service.JudgeService','admin','2018-03-13 19:29:47','admin','2018-03-13 19:29:47','com.minh.nguyen.service.JudgeService','0',NULL),(157,282,'com.minh.nguyen.service.JudgeService','admin','2018-03-16 20:13:50','admin','2018-03-16 20:13:50','com.minh.nguyen.service.JudgeService','0',NULL),(158,283,'com.minh.nguyen.service.JudgeService','admin','2018-03-16 20:14:00','admin','2018-03-16 20:14:00','com.minh.nguyen.service.JudgeService','0',NULL),(158,284,'com.minh.nguyen.service.JudgeService','admin','2018-03-16 20:14:00','admin','2018-03-16 20:14:00','com.minh.nguyen.service.JudgeService','0',NULL),(158,285,'com.minh.nguyen.service.JudgeService','admin','2018-03-16 20:14:01','admin','2018-03-16 20:14:01','com.minh.nguyen.service.JudgeService','0',NULL),(159,286,'com.minh.nguyen.service.JudgeService','admin','2018-03-21 21:03:50','admin','2018-03-21 21:03:50','com.minh.nguyen.service.JudgeService','0',NULL),(160,287,'com.minh.nguyen.service.JudgeService','admin','2018-03-21 21:03:57','admin','2018-03-21 21:03:57','com.minh.nguyen.service.JudgeService','0',NULL),(160,288,'com.minh.nguyen.service.JudgeService','admin','2018-03-21 21:03:57','admin','2018-03-21 21:03:57','com.minh.nguyen.service.JudgeService','0',NULL),(160,289,'com.minh.nguyen.service.JudgeService','admin','2018-03-21 21:03:57','admin','2018-03-21 21:03:57','com.minh.nguyen.service.JudgeService','0',NULL),(161,290,'com.minh.nguyen.service.JudgeService','admin','2018-03-21 21:04:39','admin','2018-03-21 21:04:39','com.minh.nguyen.service.JudgeService','0',NULL),(161,291,'com.minh.nguyen.service.JudgeService','admin','2018-03-21 21:04:39','admin','2018-03-21 21:04:39','com.minh.nguyen.service.JudgeService','0',NULL),(161,292,'com.minh.nguyen.service.JudgeService','admin','2018-03-21 21:04:39','admin','2018-03-21 21:04:39','com.minh.nguyen.service.JudgeService','0',NULL),(162,293,'com.minh.nguyen.service.JudgeService','admin','2018-03-21 21:07:32','admin','2018-03-21 21:07:32','com.minh.nguyen.service.JudgeService','0',NULL),(162,294,'com.minh.nguyen.service.JudgeService','admin','2018-03-21 21:07:32','admin','2018-03-21 21:07:32','com.minh.nguyen.service.JudgeService','0',NULL),(162,295,'com.minh.nguyen.service.JudgeService','admin','2018-03-21 21:07:32','admin','2018-03-21 21:07:32','com.minh.nguyen.service.JudgeService','0',NULL),(163,296,'com.minh.nguyen.service.JudgeService','admin','2018-03-21 21:08:53','admin','2018-03-21 21:08:53','com.minh.nguyen.service.JudgeService','0',NULL),(163,297,'com.minh.nguyen.service.JudgeService','admin','2018-03-21 21:08:53','admin','2018-03-21 21:08:53','com.minh.nguyen.service.JudgeService','0',NULL),(163,298,'com.minh.nguyen.service.JudgeService','admin','2018-03-21 21:08:53','admin','2018-03-21 21:08:53','com.minh.nguyen.service.JudgeService','0',NULL),(164,299,'com.minh.nguyen.service.JudgeService','admin','2018-03-21 21:10:29','admin','2018-03-21 21:10:29','com.minh.nguyen.service.JudgeService','0',NULL),(164,300,'com.minh.nguyen.service.JudgeService','admin','2018-03-21 21:10:30','admin','2018-03-21 21:10:30','com.minh.nguyen.service.JudgeService','0',NULL),(164,301,'com.minh.nguyen.service.JudgeService','admin','2018-03-21 21:10:30','admin','2018-03-21 21:10:30','com.minh.nguyen.service.JudgeService','0',NULL),(165,302,'com.minh.nguyen.service.JudgeService','admin','2018-03-21 21:14:36','admin','2018-03-21 21:14:36','com.minh.nguyen.service.JudgeService','0',NULL),(165,303,'com.minh.nguyen.service.JudgeService','admin','2018-03-21 21:14:36','admin','2018-03-21 21:14:36','com.minh.nguyen.service.JudgeService','0',NULL),(165,304,'com.minh.nguyen.service.JudgeService','admin','2018-03-21 21:14:36','admin','2018-03-21 21:14:36','com.minh.nguyen.service.JudgeService','0',NULL),(166,305,'com.minh.nguyen.service.JudgeService','admin','2018-03-21 21:17:08','admin','2018-03-21 21:17:08','com.minh.nguyen.service.JudgeService','0',NULL),(166,306,'com.minh.nguyen.service.JudgeService','admin','2018-03-21 21:17:08','admin','2018-03-21 21:17:08','com.minh.nguyen.service.JudgeService','0',NULL),(166,307,'com.minh.nguyen.service.JudgeService','admin','2018-03-21 21:17:09','admin','2018-03-21 21:17:09','com.minh.nguyen.service.JudgeService','0',NULL),(167,308,'com.minh.nguyen.service.JudgeService','admin','2018-03-21 21:17:17','admin','2018-03-21 21:17:17','com.minh.nguyen.service.JudgeService','0',NULL),(167,309,'com.minh.nguyen.service.JudgeService','admin','2018-03-21 21:17:17','admin','2018-03-21 21:17:17','com.minh.nguyen.service.JudgeService','0',NULL),(167,310,'com.minh.nguyen.service.JudgeService','admin','2018-03-21 21:17:17','admin','2018-03-21 21:17:17','com.minh.nguyen.service.JudgeService','0',NULL),(168,312,'com.minh.nguyen.service.JudgeService','admin','2018-03-21 21:18:45','admin','2018-03-21 21:18:45','com.minh.nguyen.service.JudgeService','0',NULL),(168,314,'com.minh.nguyen.service.JudgeService','admin','2018-03-21 21:18:45','admin','2018-03-21 21:18:45','com.minh.nguyen.service.JudgeService','0',NULL),(168,316,'com.minh.nguyen.service.JudgeService','admin','2018-03-21 21:18:46','admin','2018-03-21 21:18:46','com.minh.nguyen.service.JudgeService','0',NULL),(169,311,'com.minh.nguyen.service.JudgeService','admin','2018-03-21 21:18:45','admin','2018-03-21 21:18:45','com.minh.nguyen.service.JudgeService','0',NULL),(169,313,'com.minh.nguyen.service.JudgeService','admin','2018-03-21 21:18:45','admin','2018-03-21 21:18:45','com.minh.nguyen.service.JudgeService','0',NULL),(169,315,'com.minh.nguyen.service.JudgeService','admin','2018-03-21 21:18:46','admin','2018-03-21 21:18:46','com.minh.nguyen.service.JudgeService','0',NULL),(170,317,'com.minh.nguyen.service.JudgeService','admin','2018-03-21 21:19:07','admin','2018-03-21 21:19:07','com.minh.nguyen.service.JudgeService','0',NULL),(170,318,'com.minh.nguyen.service.JudgeService','admin','2018-03-21 21:19:07','admin','2018-03-21 21:19:07','com.minh.nguyen.service.JudgeService','0',NULL),(170,319,'com.minh.nguyen.service.JudgeService','admin','2018-03-21 21:19:07','admin','2018-03-21 21:19:07','com.minh.nguyen.service.JudgeService','0',NULL),(171,320,'com.minh.nguyen.service.JudgeService','user','2018-03-23 13:49:18','user','2018-03-23 13:49:18','com.minh.nguyen.service.JudgeService','0',NULL),(172,321,'com.minh.nguyen.service.JudgeService','user','2018-03-23 13:49:51','user','2018-03-23 13:49:51','com.minh.nguyen.service.JudgeService','0',NULL),(173,322,'com.minh.nguyen.service.JudgeService','user','2018-03-23 13:50:55','user','2018-03-23 13:50:55','com.minh.nguyen.service.JudgeService','0',NULL),(174,323,'com.minh.nguyen.service.JudgeService','user','2018-03-23 13:57:08','user','2018-03-23 13:57:08','com.minh.nguyen.service.JudgeService','0',NULL),(175,324,'com.minh.nguyen.service.JudgeService','user','2018-03-23 13:59:52','user','2018-03-23 13:59:52','com.minh.nguyen.service.JudgeService','0',NULL),(175,325,'com.minh.nguyen.service.JudgeService','user','2018-03-23 13:59:52','user','2018-03-23 13:59:52','com.minh.nguyen.service.JudgeService','0',NULL),(175,326,'com.minh.nguyen.service.JudgeService','user','2018-03-23 13:59:53','user','2018-03-23 13:59:53','com.minh.nguyen.service.JudgeService','0',NULL),(176,327,'com.minh.nguyen.service.JudgeService','user','2018-03-23 14:01:21','user','2018-03-23 14:01:21','com.minh.nguyen.service.JudgeService','0',NULL),(177,328,'com.minh.nguyen.service.JudgeService','user','2018-03-23 14:02:14','user','2018-03-23 14:02:14','com.minh.nguyen.service.JudgeService','0',NULL),(178,329,'com.minh.nguyen.service.JudgeService','user','2018-03-23 14:18:32','user','2018-03-23 14:18:32','com.minh.nguyen.service.JudgeService','0',NULL),(178,330,'com.minh.nguyen.service.JudgeService','user','2018-03-23 14:18:32','user','2018-03-23 14:18:32','com.minh.nguyen.service.JudgeService','0',NULL),(178,331,'com.minh.nguyen.service.JudgeService','user','2018-03-23 14:18:32','user','2018-03-23 14:18:32','com.minh.nguyen.service.JudgeService','0',NULL),(179,332,'com.minh.nguyen.service.JudgeService','user','2018-03-23 14:18:46','user','2018-03-23 14:18:46','com.minh.nguyen.service.JudgeService','0',NULL),(179,333,'com.minh.nguyen.service.JudgeService','user','2018-03-23 14:18:46','user','2018-03-23 14:18:46','com.minh.nguyen.service.JudgeService','0',NULL),(179,334,'com.minh.nguyen.service.JudgeService','user','2018-03-23 14:18:47','user','2018-03-23 14:18:47','com.minh.nguyen.service.JudgeService','0',NULL),(180,335,'com.minh.nguyen.service.JudgeService','user','2018-03-23 14:19:53','user','2018-03-23 14:19:53','com.minh.nguyen.service.JudgeService','0',NULL),(180,336,'com.minh.nguyen.service.JudgeService','user','2018-03-23 14:19:53','user','2018-03-23 14:19:53','com.minh.nguyen.service.JudgeService','0',NULL),(180,337,'com.minh.nguyen.service.JudgeService','user','2018-03-23 14:19:53','user','2018-03-23 14:19:53','com.minh.nguyen.service.JudgeService','0',NULL);
/*!40000 ALTER TABLE `sn_sdl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `spring_session`
--

DROP TABLE IF EXISTS `spring_session`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `spring_session` (
  `SESSION_ID` char(36) NOT NULL,
  `CREATION_TIME` bigint(20) NOT NULL,
  `LAST_ACCESS_TIME` bigint(20) NOT NULL,
  `MAX_INACTIVE_INTERVAL` int(11) NOT NULL,
  `PRINCIPAL_NAME` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`SESSION_ID`),
  KEY `SPRING_SESSION_IX1` (`LAST_ACCESS_TIME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `spring_session`
--

LOCK TABLES `spring_session` WRITE;
/*!40000 ALTER TABLE `spring_session` DISABLE KEYS */;
INSERT INTO `spring_session` VALUES ('85406a56-79b9-4fe6-b195-466f7fc92b59',1522650596580,1522650598022,1800,'user');
/*!40000 ALTER TABLE `spring_session` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `spring_session_attributes`
--

DROP TABLE IF EXISTS `spring_session_attributes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `spring_session_attributes` (
  `SESSION_ID` char(36) NOT NULL,
  `ATTRIBUTE_NAME` varchar(200) NOT NULL,
  `ATTRIBUTE_BYTES` blob NOT NULL,
  PRIMARY KEY (`SESSION_ID`,`ATTRIBUTE_NAME`),
  KEY `SPRING_SESSION_ATTRIBUTES_IX1` (`SESSION_ID`),
  CONSTRAINT `SPRING_SESSION_ATTRIBUTES_FK` FOREIGN KEY (`SESSION_ID`) REFERENCES `spring_session` (`SESSION_ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `spring_session_attributes`
--

LOCK TABLES `spring_session_attributes` WRITE;
/*!40000 ALTER TABLE `spring_session_attributes` DISABLE KEYS */;
INSERT INTO `spring_session_attributes` VALUES ('85406a56-79b9-4fe6-b195-466f7fc92b59','currentUserAuthorities','¨\Ì\0sr\0java.util.ArrayListxÅ\“ô\«aù\0I\0sizexp\0\0\0\0w\0\0\0\0x'),('85406a56-79b9-4fe6-b195-466f7fc92b59','currentUserFullname','¨\Ì\0t\0Okabe'),('85406a56-79b9-4fe6-b195-466f7fc92b59','currentUserHandle','¨\Ì\0t\0user'),('85406a56-79b9-4fe6-b195-466f7fc92b59','currentUserId','¨\Ì\0sr\0java.lang.Integer‚†§˜Åá8\0I\0valuexr\0java.lang.NumberÜ¨ïî\‡ã\0\0xp\0\0\0'),('85406a56-79b9-4fe6-b195-466f7fc92b59','currentUserRoleId','¨\Ì\0sr\0java.lang.Integer‚†§˜Åá8\0I\0valuexr\0java.lang.NumberÜ¨ïî\‡ã\0\0xp\0\0\0'),('85406a56-79b9-4fe6-b195-466f7fc92b59','currentUserRoleName','¨\Ì\0t\0\nSinh vi√™n'),('85406a56-79b9-4fe6-b195-466f7fc92b59','org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository.CSRF_TOKEN','¨\Ì\0sr\06org.springframework.security.web.csrf.DefaultCsrfTokenZ\Ô∑\»/¢˚\’\0L\0\nheaderNamet\0Ljava/lang/String;L\0\rparameterNameq\0~\0L\0tokenq\0~\0xpt\0X-CSRF-TOKENt\0_csrft\0$9c63285c-b09e-4f0c-aa2e-cd6111ab6242'),('85406a56-79b9-4fe6-b195-466f7fc92b59','SPRING_SECURITY_CONTEXT','¨\Ì\0sr\0=org.springframework.security.core.context.SecurityContextImpl\0\0\0\0\0\0§\0L\0authenticationt\02Lorg/springframework/security/core/Authentication;xpsr\0Oorg.springframework.security.authentication.UsernamePasswordAuthenticationToken\0\0\0\0\0\0§\0L\0credentialst\0Ljava/lang/Object;L\0	principalq\0~\0xr\0Gorg.springframework.security.authentication.AbstractAuthenticationToken”™(~nGd\0Z\0\rauthenticatedL\0authoritiest\0Ljava/util/Collection;L\0detailsq\0~\0xpsr\0&java.util.Collections$UnmodifiableList¸%1µ\Ïé\0L\0listt\0Ljava/util/List;xr\0,java.util.Collections$UnmodifiableCollectionB\0Ä\À^˜\0L\0cq\0~\0xpsr\0java.util.ArrayListxÅ\“ô\«aù\0I\0sizexp\0\0\0\0w\0\0\0\0xq\0~\0\rsr\0Horg.springframework.security.web.authentication.WebAuthenticationDetails\0\0\0\0\0\0§\0L\0\rremoteAddresst\0Ljava/lang/String;L\0	sessionIdq\0~\0xpt\00:0:0:0:0:0:0:1t\0$63d9f97d-d0a1-4a8b-a19e-8e206ebf3929psr\02org.springframework.security.core.userdetails.User\0\0\0\0\0\0§\0Z\0accountNonExpiredZ\0accountNonLockedZ\0credentialsNonExpiredZ\0enabledL\0authoritiest\0Ljava/util/Set;L\0passwordq\0~\0L\0usernameq\0~\0xpsr\0%java.util.Collections$UnmodifiableSetÄí—èõÄU\0\0xq\0~\0\nsr\0java.util.TreeSet›òPìï\Ìá[\0\0xpsr\0Forg.springframework.security.core.userdetails.User$AuthorityComparator\0\0\0\0\0\0§\0\0xpw\0\0\0\0xpt\0user'),('85406a56-79b9-4fe6-b195-466f7fc92b59','SPRING_SECURITY_LAST_EXCEPTION','¨\Ì\0sr\0Rorg.springframework.security.authentication.InternalAuthenticationServiceException¨ReÛòP\0\0xr\0Jorg.springframework.security.authentication.AuthenticationServiceException\È\Èhh~t,\ƒ\0\0xr\09org.springframework.security.core.AuthenticationExceptionQ0\‚gT\0\0xr\0\Zjava.lang.RuntimeExceptionû_G\n4É\Â\0\0xr\0java.lang.Exception\–˝>\Z;\ƒ\0\0xr\0java.lang.Throwable\’\∆5\'9w∏\À\0L\0causet\0Ljava/lang/Throwable;L\0\rdetailMessaget\0Ljava/lang/String;[\0\nstackTracet\0[Ljava/lang/StackTraceElement;L\0suppressedExceptionst\0Ljava/util/List;xpsr\0/org.springframework.jdbc.BadSqlGrammarException<\Í\Ì\‘.\’{\—\0L\0sqlq\0~\0xr\0?org.springframework.dao.InvalidDataAccessResourceUsageException6£e¬ôŸ±\0\0xr\07org.springframework.dao.NonTransientDataAccessExceptionåµ9µ¿\“\0\0xr\0+org.springframework.dao.DataAccessException∑®Bw\ \Z\≈\n\0\0xr\0/org.springframework.core.NestedRuntimeExceptionK~vHÀèü\0\0\0xq\0~\0sr\09com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException`o˜˝\ËÇd\0\0xr\0 java.sql.SQLSyntaxErrorException\ÊidÉ5)\0\0xr\0!java.sql.SQLNonTransientExceptionÅ¶º\nwÛ\—}\0\0xr\0java.sql.SQLException°\È0\€>u\‹\0I\0\nvendorCodeL\0SQLStateq\0~\0L\0nextt\0Ljava/sql/SQLException;xq\0~\0q\0~\0t\0,Unknown column \'accountType\' in \'field list\'ur\0[Ljava.lang.StackTraceElement;F*<<˝\"9\0\0xp\0\0\0msr\0java.lang.StackTraceElementa	≈ö&6›Ö\0I\0\nlineNumberL\0declaringClassq\0~\0L\0fileNameq\0~\0L\0\nmethodNameq\0~\0xpˇˇˇ˛t\0)sun.reflect.NativeConstructorAccessorImplt\0\"NativeConstructorAccessorImpl.javat\0newInstance0sq\0~\0\Z\0\0\09q\0~\0q\0~\0t\0newInstancesq\0~\0\Z\0\0\0-t\0-sun.reflect.DelegatingConstructorAccessorImplt\0&DelegatingConstructorAccessorImpl.javaq\0~\0 sq\0~\0\Z\0\0t\0java.lang.reflect.Constructort\0Constructor.javaq\0~\0 sq\0~\0\Z\0\0©t\0com.mysql.jdbc.Utilt\0	Util.javat\0handleNewInstancesq\0~\0\Z\0\0òq\0~\0(q\0~\0)t\0getInstancesq\0~\0\Z\0\0Øt\0com.mysql.jdbc.SQLErrort\0\rSQLError.javat\0createSQLExceptionsq\0~\0\Z\0\0Öt\0com.mysql.jdbc.MysqlIOt\0MysqlIO.javat\0checkErrorPacketsq\0~\0\Z\0\0Eq\0~\02q\0~\03q\0~\04sq\0~\0\Z\0\0	\ﬂq\0~\02q\0~\03t\0sendCommandsq\0~\0\Z\0\0\nxq\0~\02q\0~\03t\0sqlQueryDirectsq\0~\0\Z\0\0	∑t\0com.mysql.jdbc.ConnectionImplt\0ConnectionImpl.javat\0execSQLsq\0~\0\Z\0\0Bt\0 com.mysql.jdbc.PreparedStatementt\0PreparedStatement.javat\0executeInternalsq\0~\0\Z\0\0≠q\0~\0?q\0~\0@t\0executesq\0~\0\Zˇˇˇ˛t\0$sun.reflect.NativeMethodAccessorImplt\0NativeMethodAccessorImpl.javat\0invoke0sq\0~\0\Z\0\0\09q\0~\0Eq\0~\0Ft\0invokesq\0~\0\Z\0\0\0+t\0(sun.reflect.DelegatingMethodAccessorImplt\0!DelegatingMethodAccessorImpl.javaq\0~\0Isq\0~\0\Z\0\0^t\0java.lang.reflect.Methodt\0Method.javaq\0~\0Isq\0~\0\Z\0\0\0rt\0:org.apache.tomcat.jdbc.pool.StatementFacade$StatementProxyt\0StatementFacade.javaq\0~\0Isq\0~\0\Zˇˇˇˇt\0com.sun.proxy.$Proxy144pq\0~\0Csq\0~\0\Zˇˇˇ˛q\0~\0Eq\0~\0Fq\0~\0Gsq\0~\0\Z\0\0\09q\0~\0Eq\0~\0Fq\0~\0Isq\0~\0\Z\0\0\0+q\0~\0Kq\0~\0Lq\0~\0Isq\0~\0\Z\0\0^q\0~\0Nq\0~\0Oq\0~\0Isq\0~\0\Z\0\0\0;t\06org.apache.ibatis.logging.jdbc.PreparedStatementLoggert\0PreparedStatementLogger.javaq\0~\0Isq\0~\0\Zˇˇˇˇt\0com.sun.proxy.$Proxy145pq\0~\0Csq\0~\0\Z\0\0\0?t\0=org.apache.ibatis.executor.statement.PreparedStatementHandlert\0PreparedStatementHandler.javat\0querysq\0~\0\Z\0\0\0Ot\0<org.apache.ibatis.executor.statement.RoutingStatementHandlert\0RoutingStatementHandler.javaq\0~\0asq\0~\0\Z\0\0\0?t\0)org.apache.ibatis.executor.SimpleExecutort\0SimpleExecutor.javat\0doQuerysq\0~\0\Z\0\0Dt\0\'org.apache.ibatis.executor.BaseExecutort\0BaseExecutor.javat\0queryFromDatabasesq\0~\0\Z\0\0\0úq\0~\0jq\0~\0kq\0~\0asq\0~\0\Z\0\0\0mt\0*org.apache.ibatis.executor.CachingExecutort\0CachingExecutor.javaq\0~\0asq\0~\0\Z\0\0\0Sq\0~\0oq\0~\0pq\0~\0asq\0~\0\Z\0\0\0ît\04org.apache.ibatis.session.defaults.DefaultSqlSessiont\0DefaultSqlSession.javat\0\nselectListsq\0~\0\Z\0\0\0çq\0~\0sq\0~\0tq\0~\0usq\0~\0\Zˇˇˇ˛q\0~\0Eq\0~\0Fq\0~\0Gsq\0~\0\Z\0\0\09q\0~\0Eq\0~\0Fq\0~\0Isq\0~\0\Z\0\0\0+q\0~\0Kq\0~\0Lq\0~\0Isq\0~\0\Z\0\0^q\0~\0Nq\0~\0Oq\0~\0Isq\0~\0\Z\0\0±t\0;org.mybatis.spring.SqlSessionTemplate$SqlSessionInterceptort\0SqlSessionTemplate.javaq\0~\0Isq\0~\0\Zˇˇˇˇt\0com.sun.proxy.$Proxy86pq\0~\0usq\0~\0\Z\0\0\0\Êt\0%org.mybatis.spring.SqlSessionTemplateq\0~\0}q\0~\0usq\0~\0\Z\0\0\0ât\0&org.apache.ibatis.binding.MapperMethodt\0MapperMethod.javat\0executeForManysq\0~\0\Z\0\0\0Kq\0~\0Éq\0~\0Ñq\0~\0Csq\0~\0\Z\0\0\0;t\0%org.apache.ibatis.binding.MapperProxyt\0MapperProxy.javaq\0~\0Isq\0~\0\Zˇˇˇˇt\0com.sun.proxy.$Proxy93pt\0selectWithExamplesq\0~\0\Z\0\0\0/t\02com.minh.nguyen.config.Security.UserAuthenticationt\0UserAuthentication.javat\0loadUserByUsernamesq\0~\0\Z\0\0\0rt\0Iorg.springframework.security.authentication.dao.DaoAuthenticationProvidert\0DaoAuthenticationProvider.javat\0retrieveUsersq\0~\0\Z\0\0\0êt\0Yorg.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvidert\0.AbstractUserDetailsAuthenticationProvider.javat\0authenticatesq\0~\0\Z\0\0\0Æt\0;org.springframework.security.authentication.ProviderManagert\0ProviderManager.javaq\0~\0òsq\0~\0\Z\0\0\0\«q\0~\0öq\0~\0õq\0~\0òsq\0~\0\Z\0\0\0^t\0Torg.springframework.security.web.authentication.UsernamePasswordAuthenticationFiltert\0)UsernamePasswordAuthenticationFilter.javat\0attemptAuthenticationsq\0~\0\Z\0\0\0\‘t\0Vorg.springframework.security.web.authentication.AbstractAuthenticationProcessingFiltert\0+AbstractAuthenticationProcessingFilter.javat\0doFiltersq\0~\0\Z\0\0Kt\0Dorg.springframework.security.web.FilterChainProxy$VirtualFilterChaint\0FilterChainProxy.javaq\0~\0§sq\0~\0\Z\0\0\0tt\0Corg.springframework.security.web.authentication.logout.LogoutFiltert\0LogoutFilter.javaq\0~\0§sq\0~\0\Z\0\0Kq\0~\0¶q\0~\0ßq\0~\0§sq\0~\0\Z\0\0\0|t\00org.springframework.security.web.csrf.CsrfFiltert\0CsrfFilter.javat\0doFilterInternalsq\0~\0\Z\0\0\0kt\03org.springframework.web.filter.OncePerRequestFiltert\0OncePerRequestFilter.javaq\0~\0§sq\0~\0\Z\0\0Kq\0~\0¶q\0~\0ßq\0~\0§sq\0~\0\Z\0\0\0@t\0:org.springframework.security.web.header.HeaderWriterFiltert\0HeaderWriterFilter.javaq\0~\0Øsq\0~\0\Z\0\0\0kq\0~\0±q\0~\0≤q\0~\0§sq\0~\0\Z\0\0Kq\0~\0¶q\0~\0ßq\0~\0§sq\0~\0\Z\0\0\0it\0Iorg.springframework.security.web.context.SecurityContextPersistenceFiltert\0%SecurityContextPersistenceFilter.javaq\0~\0§sq\0~\0\Z\0\0Kq\0~\0¶q\0~\0ßq\0~\0§sq\0~\0\Z\0\0\08t\0Worg.springframework.security.web.context.request.async.WebAsyncManagerIntegrationFiltert\0%WebAsyncManagerIntegrationFilter.javaq\0~\0Øsq\0~\0\Z\0\0\0kq\0~\0±q\0~\0≤q\0~\0§sq\0~\0\Z\0\0Kq\0~\0¶q\0~\0ßq\0~\0§sq\0~\0\Z\0\0\0\÷t\01org.springframework.security.web.FilterChainProxyq\0~\0ßq\0~\0Øsq\0~\0\Z\0\0\0±q\0~\0\√q\0~\0ßq\0~\0§sq\0~\0\Z\0\0[t\04org.springframework.web.filter.DelegatingFilterProxyt\0\ZDelegatingFilterProxy.javat\0invokeDelegatesq\0~\0\Z\0\0q\0~\0\∆q\0~\0\«q\0~\0§sq\0~\0\Z\0\0\0¡t\0/org.apache.catalina.core.ApplicationFilterChaint\0ApplicationFilterChain.javat\0internalDoFiltersq\0~\0\Z\0\0\0¶q\0~\0\Àq\0~\0\Ãq\0~\0§sq\0~\0\Z\0\0\0ct\03org.springframework.web.filter.RequestContextFiltert\0RequestContextFilter.javaq\0~\0Øsq\0~\0\Z\0\0\0kq\0~\0±q\0~\0≤q\0~\0§sq\0~\0\Z\0\0\0¡q\0~\0\Àq\0~\0\Ãq\0~\0\Õsq\0~\0\Z\0\0\0¶q\0~\0\Àq\0~\0\Ãq\0~\0§sq\0~\0\Z\0\0\0lt\07org.springframework.web.filter.HttpPutFormContentFiltert\0HttpPutFormContentFilter.javaq\0~\0Øsq\0~\0\Z\0\0\0kq\0~\0±q\0~\0≤q\0~\0§sq\0~\0\Z\0\0\0¡q\0~\0\Àq\0~\0\Ãq\0~\0\Õsq\0~\0\Z\0\0\0¶q\0~\0\Àq\0~\0\Ãq\0~\0§sq\0~\0\Z\0\0\0Qt\05org.springframework.web.filter.HiddenHttpMethodFiltert\0HiddenHttpMethodFilter.javaq\0~\0Øsq\0~\0\Z\0\0\0kq\0~\0±q\0~\0≤q\0~\0§sq\0~\0\Z\0\0\0¡q\0~\0\Àq\0~\0\Ãq\0~\0\Õsq\0~\0\Z\0\0\0¶q\0~\0\Àq\0~\0\Ãq\0~\0§sq\0~\0\Z\0\0\0ßt\0<org.springframework.session.web.http.SessionRepositoryFiltert\0SessionRepositoryFilter.javaq\0~\0Øsq\0~\0\Z\0\0\0Pt\09org.springframework.session.web.http.OncePerRequestFilterq\0~\0≤q\0~\0§sq\0~\0\Z\0\0\0¡q\0~\0\Àq\0~\0\Ãq\0~\0\Õsq\0~\0\Z\0\0\0¶q\0~\0\Àq\0~\0\Ãq\0~\0§sq\0~\0\Z\0\0\0\≈t\06org.springframework.web.filter.CharacterEncodingFiltert\0CharacterEncodingFilter.javaq\0~\0Øsq\0~\0\Z\0\0\0kq\0~\0±q\0~\0≤q\0~\0§sq\0~\0\Z\0\0\0¡q\0~\0\Àq\0~\0\Ãq\0~\0\Õsq\0~\0\Z\0\0\0¶q\0~\0\Àq\0~\0\Ãq\0~\0§sq\0~\0\Z\0\0\0\«t\0-org.apache.catalina.core.StandardWrapperValvet\0StandardWrapperValve.javaq\0~\0Isq\0~\0\Z\0\0\0`t\0-org.apache.catalina.core.StandardContextValvet\0StandardContextValve.javaq\0~\0Isq\0~\0\Z\0\0\ﬁt\03org.apache.catalina.authenticator.AuthenticatorBaset\0AuthenticatorBase.javaq\0~\0Isq\0~\0\Z\0\0\0åt\0*org.apache.catalina.core.StandardHostValvet\0StandardHostValve.javaq\0~\0Isq\0~\0\Z\0\0\0Qt\0+org.apache.catalina.valves.ErrorReportValvet\0ErrorReportValve.javaq\0~\0Isq\0~\0\Z\0\0\0Wt\0,org.apache.catalina.core.StandardEngineValvet\0StandardEngineValve.javaq\0~\0Isq\0~\0\Z\0\0Vt\0+org.apache.catalina.connector.CoyoteAdaptert\0CoyoteAdapter.javat\0servicesq\0~\0\Z\0\0#t\0(org.apache.coyote.http11.Http11Processort\0Http11Processor.javaq\0~sq\0~\0\Z\0\0\0Bt\0(org.apache.coyote.AbstractProcessorLightt\0AbstractProcessorLight.javat\0processsq\0~\0\Z\0\0dt\04org.apache.coyote.AbstractProtocol$ConnectionHandlert\0AbstractProtocol.javaq\0~\nsq\0~\0\Z\0\0≥t\06org.apache.tomcat.util.net.NioEndpoint$SocketProcessort\0NioEndpoint.javat\0doRunsq\0~\0\Z\0\0\01t\0.org.apache.tomcat.util.net.SocketProcessorBaset\0SocketProcessorBase.javat\0runsq\0~\0\Z\0\0yt\0\'java.util.concurrent.ThreadPoolExecutort\0ThreadPoolExecutor.javat\0	runWorkersq\0~\0\Z\0\0gt\0.java.util.concurrent.ThreadPoolExecutor$Workerq\0~q\0~sq\0~\0\Z\0\0\0=t\0:org.apache.tomcat.util.threads.TaskThread$WrappingRunnablet\0TaskThread.javaq\0~sq\0~\0\Z\0\0\Èt\0java.lang.Threadt\0Thread.javaq\0~sr\0&java.util.Collections$UnmodifiableList¸%1µ\Ïé\0L\0listq\0~\0	xr\0,java.util.Collections$UnmodifiableCollectionB\0Ä\À^˜\0L\0ct\0Ljava/util/Collection;xpsr\0java.util.ArrayListxÅ\“ô\«aù\0I\0sizexp\0\0\0\0w\0\0\0\0xq\0~\'x\0\0t\042S22pts\r\n### Error querying database.  Cause: com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Unknown column \'accountType\' in \'field list\'\r\n### The error may exist in com/minh/nguyen/mapper/UserMapper.java (best guess)\r\n### The error may involve com.minh.nguyen.mapper.UserMapper.selectWithExample-Inline\r\n### The error occurred while setting parameters\r\n### SQL: SELECT id AS id,handle AS handle,password AS password,fullname AS fullname,phoneNumber AS phoneNumber,emailAddress AS emailAddress,dateOfBirth AS dateOfBirth,avatar AS avatar,description AS description,loginAttempt AS loginAttempt,isOnline AS isOnline,isActived AS isActived,isLocked AS isLocked,reId AS reId,lastLogin AS lastLogin,expiredDate AS expiredDate,accountType AS accountType,createTime AS createTime,createUser AS createUser,createClass AS createClass,updateTime AS updateTime,updateUser AS updateUser,updateClass AS updateClass,deleteFlg AS deleteFlg,deleteTime AS deleteTime FROM user WHERE (handle = ? AND deleteFlg=\'0\')\r\n### Cause: com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Unknown column \'accountType\' in \'field list\'\n; bad SQL grammar []uq\0~\0\0\0\0Isq\0~\0\Z\0\0\0\Át\0Corg.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslatort\0\'SQLErrorCodeSQLExceptionTranslator.javat\0doTranslatesq\0~\0\Z\0\0\0It\0Gorg.springframework.jdbc.support.AbstractFallbackSQLExceptionTranslatort\0+AbstractFallbackSQLExceptionTranslator.javat\0	translatesq\0~\0\Z\0\0\0It\0-org.mybatis.spring.MyBatisExceptionTranslatort\0MyBatisExceptionTranslator.javat\0translateExceptionIfPossiblesq\0~\0\Z\0\0æq\0~\0|q\0~\0}q\0~\0Isq\0~\0\Zˇˇˇˇq\0~\0pq\0~\0usq\0~\0\Z\0\0\0\Êq\0~\0Åq\0~\0}q\0~\0usq\0~\0\Z\0\0\0âq\0~\0Éq\0~\0Ñq\0~\0Ösq\0~\0\Z\0\0\0Kq\0~\0Éq\0~\0Ñq\0~\0Csq\0~\0\Z\0\0\0;q\0~\0àq\0~\0âq\0~\0Isq\0~\0\Zˇˇˇˇq\0~\0ãpq\0~\0åsq\0~\0\Z\0\0\0/q\0~\0éq\0~\0èq\0~\0êsq\0~\0\Z\0\0\0rq\0~\0íq\0~\0ìq\0~\0îsq\0~\0\Z\0\0\0êq\0~\0ñq\0~\0óq\0~\0òsq\0~\0\Z\0\0\0Æq\0~\0öq\0~\0õq\0~\0òsq\0~\0\Z\0\0\0\«q\0~\0öq\0~\0õq\0~\0òsq\0~\0\Z\0\0\0^q\0~\0ûq\0~\0üq\0~\0†sq\0~\0\Z\0\0\0\‘q\0~\0¢q\0~\0£q\0~\0§sq\0~\0\Z\0\0Kq\0~\0¶q\0~\0ßq\0~\0§sq\0~\0\Z\0\0\0tq\0~\0©q\0~\0™q\0~\0§sq\0~\0\Z\0\0Kq\0~\0¶q\0~\0ßq\0~\0§sq\0~\0\Z\0\0\0|q\0~\0≠q\0~\0Æq\0~\0Øsq\0~\0\Z\0\0\0kq\0~\0±q\0~\0≤q\0~\0§sq\0~\0\Z\0\0Kq\0~\0¶q\0~\0ßq\0~\0§sq\0~\0\Z\0\0\0@q\0~\0µq\0~\0∂q\0~\0Øsq\0~\0\Z\0\0\0kq\0~\0±q\0~\0≤q\0~\0§sq\0~\0\Z\0\0Kq\0~\0¶q\0~\0ßq\0~\0§sq\0~\0\Z\0\0\0iq\0~\0∫q\0~\0ªq\0~\0§sq\0~\0\Z\0\0Kq\0~\0¶q\0~\0ßq\0~\0§sq\0~\0\Z\0\0\08q\0~\0æq\0~\0øq\0~\0Øsq\0~\0\Z\0\0\0kq\0~\0±q\0~\0≤q\0~\0§sq\0~\0\Z\0\0Kq\0~\0¶q\0~\0ßq\0~\0§sq\0~\0\Z\0\0\0\÷q\0~\0\√q\0~\0ßq\0~\0Øsq\0~\0\Z\0\0\0±q\0~\0\√q\0~\0ßq\0~\0§sq\0~\0\Z\0\0[q\0~\0\∆q\0~\0\«q\0~\0\»sq\0~\0\Z\0\0q\0~\0\∆q\0~\0\«q\0~\0§sq\0~\0\Z\0\0\0¡q\0~\0\Àq\0~\0\Ãq\0~\0\Õsq\0~\0\Z\0\0\0¶q\0~\0\Àq\0~\0\Ãq\0~\0§sq\0~\0\Z\0\0\0cq\0~\0\–q\0~\0\—q\0~\0Øsq\0~\0\Z\0\0\0kq\0~\0±q\0~\0≤q\0~\0§sq\0~\0\Z\0\0\0¡q\0~\0\Àq\0~\0\Ãq\0~\0\Õsq\0~\0\Z\0\0\0¶q\0~\0\Àq\0~\0\Ãq\0~\0§sq\0~\0\Z\0\0\0lq\0~\0\÷q\0~\0\◊q\0~\0Øsq\0~\0\Z\0\0\0kq\0~\0±q\0~\0≤q\0~\0§sq\0~\0\Z\0\0\0¡q\0~\0\Àq\0~\0\Ãq\0~\0\Õsq\0~\0\Z\0\0\0¶q\0~\0\Àq\0~\0\Ãq\0~\0§sq\0~\0\Z\0\0\0Qq\0~\0\‹q\0~\0\›q\0~\0Øsq\0~\0\Z\0\0\0kq\0~\0±q\0~\0≤q\0~\0§sq\0~\0\Z\0\0\0¡q\0~\0\Àq\0~\0\Ãq\0~\0\Õsq\0~\0\Z\0\0\0¶q\0~\0\Àq\0~\0\Ãq\0~\0§sq\0~\0\Z\0\0\0ßq\0~\0\‚q\0~\0\„q\0~\0Øsq\0~\0\Z\0\0\0Pq\0~\0\Âq\0~\0≤q\0~\0§sq\0~\0\Z\0\0\0¡q\0~\0\Àq\0~\0\Ãq\0~\0\Õsq\0~\0\Z\0\0\0¶q\0~\0\Àq\0~\0\Ãq\0~\0§sq\0~\0\Z\0\0\0\≈q\0~\0\Èq\0~\0\Íq\0~\0Øsq\0~\0\Z\0\0\0kq\0~\0±q\0~\0≤q\0~\0§sq\0~\0\Z\0\0\0¡q\0~\0\Àq\0~\0\Ãq\0~\0\Õsq\0~\0\Z\0\0\0¶q\0~\0\Àq\0~\0\Ãq\0~\0§sq\0~\0\Z\0\0\0\«q\0~\0\Ôq\0~\0q\0~\0Isq\0~\0\Z\0\0\0`q\0~\0Úq\0~\0Ûq\0~\0Isq\0~\0\Z\0\0\ﬁq\0~\0ıq\0~\0ˆq\0~\0Isq\0~\0\Z\0\0\0åq\0~\0¯q\0~\0˘q\0~\0Isq\0~\0\Z\0\0\0Qq\0~\0˚q\0~\0¸q\0~\0Isq\0~\0\Z\0\0\0Wq\0~\0˛q\0~\0ˇq\0~\0Isq\0~\0\Z\0\0Vq\0~q\0~q\0~sq\0~\0\Z\0\0#q\0~q\0~q\0~sq\0~\0\Z\0\0\0Bq\0~q\0~	q\0~\nsq\0~\0\Z\0\0dq\0~q\0~\rq\0~\nsq\0~\0\Z\0\0≥q\0~q\0~q\0~sq\0~\0\Z\0\0\01q\0~q\0~q\0~sq\0~\0\Z\0\0yq\0~q\0~q\0~sq\0~\0\Z\0\0gq\0~q\0~q\0~sq\0~\0\Z\0\0\0=q\0~q\0~q\0~sq\0~\0\Z\0\0\Èq\0~ q\0~!q\0~q\0~%xt\0\0t\r\n### Error querying database.  Cause: com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Unknown column \'accountType\' in \'field list\'\r\n### The error may exist in com/minh/nguyen/mapper/UserMapper.java (best guess)\r\n### The error may involve com.minh.nguyen.mapper.UserMapper.selectWithExample-Inline\r\n### The error occurred while setting parameters\r\n### SQL: SELECT id AS id,handle AS handle,password AS password,fullname AS fullname,phoneNumber AS phoneNumber,emailAddress AS emailAddress,dateOfBirth AS dateOfBirth,avatar AS avatar,description AS description,loginAttempt AS loginAttempt,isOnline AS isOnline,isActived AS isActived,isLocked AS isLocked,reId AS reId,lastLogin AS lastLogin,expiredDate AS expiredDate,accountType AS accountType,createTime AS createTime,createUser AS createUser,createClass AS createClass,updateTime AS updateTime,updateUser AS updateUser,updateClass AS updateClass,deleteFlg AS deleteFlg,deleteTime AS deleteTime FROM user WHERE (handle = ? AND deleteFlg=\'0\')\r\n### Cause: com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Unknown column \'accountType\' in \'field list\'\n; bad SQL grammar []; nested exception is com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Unknown column \'accountType\' in \'field list\'uq\0~\0\0\0\0>sq\0~\0\Z\0\0\0~q\0~\0íq\0~\0ìq\0~\0îsq\0~\0\Z\0\0\0êq\0~\0ñq\0~\0óq\0~\0òsq\0~\0\Z\0\0\0Æq\0~\0öq\0~\0õq\0~\0òsq\0~\0\Z\0\0\0\«q\0~\0öq\0~\0õq\0~\0òsq\0~\0\Z\0\0\0^q\0~\0ûq\0~\0üq\0~\0†sq\0~\0\Z\0\0\0\‘q\0~\0¢q\0~\0£q\0~\0§sq\0~\0\Z\0\0Kq\0~\0¶q\0~\0ßq\0~\0§sq\0~\0\Z\0\0\0tq\0~\0©q\0~\0™q\0~\0§sq\0~\0\Z\0\0Kq\0~\0¶q\0~\0ßq\0~\0§sq\0~\0\Z\0\0\0|q\0~\0≠q\0~\0Æq\0~\0Øsq\0~\0\Z\0\0\0kq\0~\0±q\0~\0≤q\0~\0§sq\0~\0\Z\0\0Kq\0~\0¶q\0~\0ßq\0~\0§sq\0~\0\Z\0\0\0@q\0~\0µq\0~\0∂q\0~\0Øsq\0~\0\Z\0\0\0kq\0~\0±q\0~\0≤q\0~\0§sq\0~\0\Z\0\0Kq\0~\0¶q\0~\0ßq\0~\0§sq\0~\0\Z\0\0\0iq\0~\0∫q\0~\0ªq\0~\0§sq\0~\0\Z\0\0Kq\0~\0¶q\0~\0ßq\0~\0§sq\0~\0\Z\0\0\08q\0~\0æq\0~\0øq\0~\0Øsq\0~\0\Z\0\0\0kq\0~\0±q\0~\0≤q\0~\0§sq\0~\0\Z\0\0Kq\0~\0¶q\0~\0ßq\0~\0§sq\0~\0\Z\0\0\0\÷q\0~\0\√q\0~\0ßq\0~\0Øsq\0~\0\Z\0\0\0±q\0~\0\√q\0~\0ßq\0~\0§sq\0~\0\Z\0\0[q\0~\0\∆q\0~\0\«q\0~\0\»sq\0~\0\Z\0\0q\0~\0\∆q\0~\0\«q\0~\0§sq\0~\0\Z\0\0\0¡q\0~\0\Àq\0~\0\Ãq\0~\0\Õsq\0~\0\Z\0\0\0¶q\0~\0\Àq\0~\0\Ãq\0~\0§sq\0~\0\Z\0\0\0cq\0~\0\–q\0~\0\—q\0~\0Øsq\0~\0\Z\0\0\0kq\0~\0±q\0~\0≤q\0~\0§sq\0~\0\Z\0\0\0¡q\0~\0\Àq\0~\0\Ãq\0~\0\Õsq\0~\0\Z\0\0\0¶q\0~\0\Àq\0~\0\Ãq\0~\0§sq\0~\0\Z\0\0\0lq\0~\0\÷q\0~\0\◊q\0~\0Øsq\0~\0\Z\0\0\0kq\0~\0±q\0~\0≤q\0~\0§sq\0~\0\Z\0\0\0¡q\0~\0\Àq\0~\0\Ãq\0~\0\Õsq\0~\0\Z\0\0\0¶q\0~\0\Àq\0~\0\Ãq\0~\0§sq\0~\0\Z\0\0\0Qq\0~\0\‹q\0~\0\›q\0~\0Øsq\0~\0\Z\0\0\0kq\0~\0±q\0~\0≤q\0~\0§sq\0~\0\Z\0\0\0¡q\0~\0\Àq\0~\0\Ãq\0~\0\Õsq\0~\0\Z\0\0\0¶q\0~\0\Àq\0~\0\Ãq\0~\0§sq\0~\0\Z\0\0\0ßq\0~\0\‚q\0~\0\„q\0~\0Øsq\0~\0\Z\0\0\0Pq\0~\0\Âq\0~\0≤q\0~\0§sq\0~\0\Z\0\0\0¡q\0~\0\Àq\0~\0\Ãq\0~\0\Õsq\0~\0\Z\0\0\0¶q\0~\0\Àq\0~\0\Ãq\0~\0§sq\0~\0\Z\0\0\0\≈q\0~\0\Èq\0~\0\Íq\0~\0Øsq\0~\0\Z\0\0\0kq\0~\0±q\0~\0≤q\0~\0§sq\0~\0\Z\0\0\0¡q\0~\0\Àq\0~\0\Ãq\0~\0\Õsq\0~\0\Z\0\0\0¶q\0~\0\Àq\0~\0\Ãq\0~\0§sq\0~\0\Z\0\0\0\«q\0~\0\Ôq\0~\0q\0~\0Isq\0~\0\Z\0\0\0`q\0~\0Úq\0~\0Ûq\0~\0Isq\0~\0\Z\0\0\ﬁq\0~\0ıq\0~\0ˆq\0~\0Isq\0~\0\Z\0\0\0åq\0~\0¯q\0~\0˘q\0~\0Isq\0~\0\Z\0\0\0Qq\0~\0˚q\0~\0¸q\0~\0Isq\0~\0\Z\0\0\0Wq\0~\0˛q\0~\0ˇq\0~\0Isq\0~\0\Z\0\0Vq\0~q\0~q\0~sq\0~\0\Z\0\0#q\0~q\0~q\0~sq\0~\0\Z\0\0\0Bq\0~q\0~	q\0~\nsq\0~\0\Z\0\0dq\0~q\0~\rq\0~\nsq\0~\0\Z\0\0≥q\0~q\0~q\0~sq\0~\0\Z\0\0\01q\0~q\0~q\0~sq\0~\0\Z\0\0yq\0~q\0~q\0~sq\0~\0\Z\0\0gq\0~q\0~q\0~sq\0~\0\Z\0\0\0=q\0~q\0~q\0~sq\0~\0\Z\0\0\Èq\0~ q\0~!q\0~q\0~%x');
/*!40000 ALTER TABLE `spring_session_attributes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subject`
--

DROP TABLE IF EXISTS `subject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `subject` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `credit` int(11) DEFAULT '0',
  `createClass` varchar(100) DEFAULT NULL,
  `createUser` varchar(30) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `updateUser` varchar(45) DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `updateClass` varchar(45) DEFAULT NULL,
  `deleteFlg` varchar(1) DEFAULT '0',
  `deleteTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subject`
--

LOCK TABLES `subject` WRITE;
/*!40000 ALTER TABLE `subject` DISABLE KEYS */;
INSERT INTO `subject` VALUES (1,'K·ªπ thu·∫≠t l·∫≠p tr√¨nh',4,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL),(2,'To√°n r·ªùi r·∫°c',4,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL),(3,'L·∫≠p tr√¨nh h∆∞·ªõng ƒë·ªëi t∆∞·ª£ng',4,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL),(4,'C·∫•u tr√∫c d·ªØ li·ªáu v√† gi·∫£i thu·∫≠t',4,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL);
/*!40000 ALTER TABLE `subject` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `submission`
--

DROP TABLE IF EXISTS `submission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `submission` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `pmId` int(11) DEFAULT NULL,
  `leId` int(11) DEFAULT NULL,
  `urId` int(11) DEFAULT NULL,
  `timeRun` int(11) DEFAULT NULL,
  `memoryUsed` int(11) DEFAULT NULL,
  `sourceCode` text,
  `judgeStatus` int(11) DEFAULT '0',
  `verdict` varchar(45) DEFAULT NULL,
  `isPublic` int(11) DEFAULT '1',
  `createClass` varchar(100) DEFAULT NULL,
  `createUser` varchar(30) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `updateUser` varchar(45) DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `updateClass` varchar(45) DEFAULT NULL,
  `deleteFlg` varchar(1) DEFAULT '0',
  `deleteTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=181 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `submission`
--

LOCK TABLES `submission` WRITE;
/*!40000 ALTER TABLE `submission` DISABLE KEYS */;
INSERT INTO `submission` VALUES (114,10,2,1,121,0,'#include<bits/stdc++.h>\r\nusing namespace std;\r\nint main(){\r\n	int a,b;\r\ncin >> a >> b;\r\ncout << a + b;\r\n}',6,'ƒê√∫ng',NULL,'com.minh.nguyen.service.ContestService','admin','2018-02-14 13:55:56','admin','2018-02-14 13:55:56','com.minh.nguyen.service.ContestService','0',NULL),(115,20,2,1,0,0,'#include<bits/stdc++.h>\r\nusing namespace std;\r\nint main(){\r\n	int a,b;\r\ncin >> a >> b;\r\ncout << a + b;\r\n}',1,'L·ªói bi√™n d·ªãch',NULL,'com.minh.nguyen.service.ContestService','admin','2018-02-14 13:56:16','admin','2018-02-14 13:56:16','com.minh.nguyen.service.ContestService','0',NULL),(116,20,2,2,149,0,'#include<bits/stdc++.h>\r\nusing namespace std;\r\nint main(){\r\n	int a,b;\r\ncin >> a >> b;\r\ncout << a + b;\r\n}',6,'ƒê√∫ng',NULL,'com.minh.nguyen.service.ContestService','user','2018-02-14 14:14:57','user','2018-02-14 14:14:57','com.minh.nguyen.service.ContestService','0',NULL),(117,10,2,2,129,0,'#include<bits/stdc++.h>\r\nusing namespace std;\r\nint main(){\r\n	int a,b;\r\ncin >> a >> b;\r\ncout << a + b;\r\n}',6,'ƒê√∫ng',NULL,'com.minh.nguyen.service.ContestService','user','2018-02-14 14:15:12','user','2018-02-14 14:15:12','com.minh.nguyen.service.ContestService','0',NULL),(118,10,2,2,0,0,'#include<bits/stdc++.h>\r\nusing namespace std;\r\nint main(){\r\n	int a,b;\r\ncin >> a >> b;\r\ncout << a= + b;\r\n}',1,'L·ªói bi√™n d·ªãch',NULL,'com.minh.nguyen.service.ContestService','user','2018-02-14 15:46:32','user','2018-02-14 15:46:32','com.minh.nguyen.service.ContestService','0',NULL),(119,20,2,1,0,0,'#include<bits/stdc++.h>\r\nusing namespace std;\r\nint main(){\r\n	int a,b;\r\ncin >> a >> b;\r\ncout << a + b;\r\n}',1,'L·ªói bi√™n d·ªãch',NULL,'com.minh.nguyen.service.ProblemService','admin','2018-02-16 20:27:18','admin','2018-02-16 20:27:18','com.minh.nguyen.service.ProblemService','0',NULL),(120,20,2,1,150,0,'#include<bits/stdc++.h>\r\nusing namespace std;\r\nint main(){\r\n	int a,b;\r\ncin >> a >> b;\r\ncout << a + b;\r\n}',6,'ƒê√∫ng',NULL,'com.minh.nguyen.service.ProblemService','admin','2018-02-16 20:27:30','admin','2018-02-16 20:27:30','com.minh.nguyen.service.ProblemService','0',NULL),(121,20,2,1,179,0,'#include<bits/stdc++.h>\r\nusing namespace std;\r\nint main(){\r\n	int a,b;\r\ncin >> a >> b;\r\ncout << a + b;\r\n}',6,'ƒê√∫ng',NULL,'com.minh.nguyen.service.ProblemService','admin','2018-02-16 20:35:02','admin','2018-02-16 20:35:02','com.minh.nguyen.service.ProblemService','0',NULL),(122,20,2,2,0,0,'#include<bits/stdc++.h>\r\nusing namespace std;\r\nint main(){\r\n    int a,b;\r\n    cout << a - 123;\r\n}',2,'L·ªói runtime',NULL,'com.minh.nguyen.service.ProblemService','user','2018-02-24 21:44:39','user','2018-02-24 21:44:39','com.minh.nguyen.service.ProblemService','0',NULL),(123,20,2,2,0,0,'#include<bits/stdc++.h>\r\nusing namespace std;\r\nint main(){\r\n    int a,b;\r\n    cout << a - 123;\r\n}',4,'L·ªói bi√™n d·ªãch',NULL,'com.minh.nguyen.service.ProblemService','user','2018-02-24 21:45:12','user','2018-02-24 21:45:12','com.minh.nguyen.service.ProblemService','0',NULL),(124,20,2,2,0,0,'#include<bits/stdc++.h>\r\nusing namespace std;\r\nint main(){\r\n    int a,b;\r\n    cout << a - 123;\r\n}',3,'Sai k·∫øt qu·∫£',NULL,'com.minh.nguyen.service.ProblemService','user','2018-02-24 21:45:48','user','2018-02-24 21:45:48','com.minh.nguyen.service.ProblemService','0',NULL),(125,20,1,2,0,0,'#include<bits/stdc++.h>\r\nusing namespace std;\r\nint main(){\r\n    cout <<123;\r\n}',6,'ƒê√∫ng',NULL,'com.minh.nguyen.service.ProblemService','user','2018-02-24 21:46:43','user','2018-02-24 21:46:43','com.minh.nguyen.service.ProblemService','0',NULL),(126,20,2,1,0,0,'#include<bits/stdc++.h>\r\nusing namespace std;\r\nint main(){\r\n    int a,b;\r\n    cin >> a >> b;\r\n    cout << a + b;\r\n}',2,'L·ªói runtime',NULL,'com.minh.nguyen.service.ProblemService','admin','2018-02-25 21:01:32','admin','2018-02-25 21:01:32','com.minh.nguyen.service.ProblemService','0',NULL),(127,20,2,1,0,0,'#include<bits/stdc++.h>\r\nusing namespace std;\r\nint main(){\r\n    int a,b;\r\n    cin >> a >> b;\r\n    cout << a + b;\r\n}',2,'L·ªói runtime',NULL,'com.minh.nguyen.service.ProblemService','admin','2018-02-25 21:01:40','admin','2018-02-25 21:01:40','com.minh.nguyen.service.ProblemService','0',NULL),(128,20,2,1,25,0,'#include<bits/stdc++.h>\r\nusing namespace std;\r\nint main(){\r\n    int a,b;\r\n    cin >> a >> b;\r\n    cout << a + b;\r\n}',6,'ƒê√∫ng',NULL,'com.minh.nguyen.service.ProblemService','admin','2018-02-25 21:04:24','admin','2018-02-25 21:04:24','com.minh.nguyen.service.ProblemService','0',NULL),(129,10,2,2,0,0,'#include<bits/stdc++.h>\r\nusing namespace std;\r\nint main(){\r\n    \r\n    int a,c;\r\n    cin >> a >> c;\r\n    cout << a + c;\r\n}',1,'L·ªói bi√™n d·ªãch',NULL,'com.minh.nguyen.service.ProblemService','user','2018-03-11 22:53:38','user','2018-03-11 22:53:38','com.minh.nguyen.service.ProblemService','0',NULL),(130,10,2,2,0,0,'#include<bits/stdc++.h>\r\nusing namespace std;\r\nint main(){\r\n    \r\n    int a,c;\r\n    cin >> a >> c;\r\n    cout << a + c;\r\n}',2,'L·ªói runtime',NULL,'com.minh.nguyen.service.ProblemService','user','2018-03-11 22:54:10','user','2018-03-11 22:54:10','com.minh.nguyen.service.ProblemService','0',NULL),(131,10,2,2,0,0,'#include<bits/stdc++.h>\r\nusing namespace std;\r\nint main(){\r\n    \r\n    int a,c;\r\n    cin >> a >> c;\r\n    cout << a + c;\r\n}',2,'L·ªói runtime',NULL,'com.minh.nguyen.service.ProblemService','user','2018-03-11 22:54:35','user','2018-03-11 22:54:35','com.minh.nguyen.service.ProblemService','0',NULL),(132,10,2,2,144,0,'#include<bits/stdc++.h>\r\nusing namespace std;\r\nint main(){\r\n    \r\n    int a,c;\r\n    cin >> a >> c;\r\n    cout << a + c;\r\n}',6,'ƒê√∫ng',NULL,'com.minh.nguyen.service.ProblemService','user','2018-03-11 22:59:56','user','2018-03-11 22:59:56','com.minh.nguyen.service.ProblemService','0',NULL),(135,10,2,2,92,0,'#include<bits/stdc++.h>\r\nusing namespace std;\r\nint main(){\r\n    \r\n    int a,c;\r\n    cin >> a >> c;\r\n    cout << a + c;\r\n}',6,'ƒê√∫ng',NULL,'com.minh.nguyen.service.ProblemService','user','2018-03-11 23:06:37','user','2018-03-11 23:06:37','com.minh.nguyen.service.ProblemService','0',NULL),(136,10,2,2,103,0,'#include<bits/stdc++.h>\r\nusing namespace std;\r\nint main(){\r\n    \r\n    int a,c;\r\n    cin >> a >> c;\r\n    cout << a + c;\r\n}',6,'ƒê√∫ng',NULL,'com.minh.nguyen.service.ProblemService','user','2018-03-11 23:07:23','user','2018-03-11 23:07:23','com.minh.nguyen.service.ProblemService','0',NULL),(137,10,2,2,0,0,'#include<bits/stdc++.h>\r\nusing namespace std;\r\nint main(){\r\n    \r\n    int a,c;\r\n    cin >> a >> c;\r\n    cout << a + c;\r\n}',0,'ƒêang bi√™n d·ªãch...',NULL,'com.minh.nguyen.service.ProblemService','user','2018-03-11 23:14:57','user','2018-03-11 23:14:57','com.minh.nguyen.service.ProblemService','0',NULL),(138,10,2,2,0,0,'#include<bits/stdc++.h>\r\nusing namespace std;\r\nint main(){\r\n    \r\n    int a,c;\r\n    cin >> a >> c;\r\n    cout << a + c;\r\n}',1,'L·ªói bi√™n d·ªãch',NULL,'com.minh.nguyen.service.ProblemService','user','2018-03-12 13:19:23','user','2018-03-12 13:19:23','com.minh.nguyen.service.ProblemService','0',NULL),(139,10,2,2,85,0,'#include<bits/stdc++.h>\r\nusing namespace std;\r\nint main(){\r\n    \r\n    int a,c;\r\n    cin >> a >> c;\r\n    cout << a + c;\r\n}',6,'ƒê√∫ng',NULL,'com.minh.nguyen.service.ProblemService','user','2018-03-12 13:23:06','user','2018-03-12 13:23:06','com.minh.nguyen.service.ProblemService','0',NULL),(140,10,2,2,93,0,'#include<bits/stdc++.h>\r\nusing namespace std;\r\nint main(){\r\n    \r\n    int a,c;\r\n    cin >> a >> c;\r\n    cout << a + c;\r\n}',6,'ƒê√∫ng',NULL,'com.minh.nguyen.service.ProblemService','user','2018-03-12 13:25:37','user','2018-03-12 13:25:37','com.minh.nguyen.service.ProblemService','0',NULL),(141,10,2,2,123,0,'#include<bits/stdc++.h>\r\nusing namespace std;\r\nint main(){\r\n    \r\n    int a,c;\r\n    cin >> a >> c;\r\n    cout << a + c;\r\n}',6,'ƒê√∫ng',NULL,'com.minh.nguyen.service.ProblemService','user','2018-03-12 13:25:53','user','2018-03-12 13:25:53','com.minh.nguyen.service.ProblemService','0',NULL),(142,10,2,2,117,0,'#include<bits/stdc++.h>\r\nusing namespace std;\r\nint main(){\r\n    \r\n    int a,c;\r\n    cin >> a >> c;\r\n    cout << a + c;\r\n}',6,'ƒê√∫ng',NULL,'com.minh.nguyen.service.ProblemService','user','2018-03-12 13:26:13','user','2018-03-12 13:26:13','com.minh.nguyen.service.ProblemService','0',NULL),(143,10,2,2,101,0,'#include<bits/stdc++.h>\r\nusing namespace std;\r\nint main(){\r\n    \r\n    int a,c;\r\n    cin >> a >> c;\r\n    cout << a + c;\r\n}',6,'ƒê√∫ng',NULL,'com.minh.nguyen.service.ContestService','user','2018-03-12 13:49:20','user','2018-03-12 13:49:20','com.minh.nguyen.service.ContestService','0',NULL),(144,10,2,2,0,0,'#include<bits/stdc++.h>\r\nusing namespace std;\r\nint main(){\r\n    \r\n    int a,c;\r\n    cin >> a >> c;\r\n    cout << a + c;\r\n}',1,'L·ªói bi√™n d·ªãch',NULL,'com.minh.nguyen.service.ProblemService','user','2018-03-12 22:35:31','user','2018-03-12 22:35:31','com.minh.nguyen.service.ProblemService','0',NULL),(145,10,2,2,105,0,'#include<bits/stdc++.h>\r\nusing namespace std;\r\nint main(){\r\n    \r\n    int a,c;\r\n    cin >> a >> c;\r\n    cout << a + c;\r\n}',6,'ƒê√∫ng',NULL,'com.minh.nguyen.service.ProblemService','user','2018-03-12 22:35:41','user','2018-03-12 22:35:41','com.minh.nguyen.service.ProblemService','0',NULL),(146,10,2,2,0,0,'#include<bits/stdc++.h>\r\nusing namespace std;\r\nint main(){\r\n    int a,b;\r\n    cin >> a >> b;\r\n    cout << a + b;\r\n}',1,'L·ªói bi√™n d·ªãch',NULL,'com.minh.nguyen.service.ProblemService','user','2018-03-13 12:36:56','user','2018-03-13 12:36:49','com.minh.nguyen.service.ProblemService','0',NULL),(147,10,2,2,110,0,'#include<bits/stdc++.h>\r\nusing namespace std;\r\nint main(){\r\n    int a,b;\r\n    cin >> a >> b;\r\n    cout << a + b;\r\n}',6,'ƒê√∫ng',NULL,'com.minh.nguyen.service.ProblemService','user','2018-03-13 12:37:10','user','2018-03-13 12:37:10','com.minh.nguyen.service.ProblemService','0',NULL),(148,10,2,2,120,0,'#include<bits/stdc++.h>\r\nusing namespace std;\r\nint main(){\r\n    int a,b;\r\n    cin >> a >> b;\r\n    cout << a + b;\r\n}',6,'ƒê√∫ng',NULL,'com.minh.nguyen.service.ProblemService','user','2018-03-13 12:37:31','user','2018-03-13 12:37:31','com.minh.nguyen.service.ProblemService','0',NULL),(149,10,2,2,75,0,'#include<bits/stdc++.h>\r\nusing namespace std;\r\nint main(){\r\n    int a,b;\r\n    cin >> a >> b;\r\n    cout << a + b;\r\n}',6,'ƒê√∫ng',NULL,'com.minh.nguyen.service.ProblemService','user','2018-03-13 12:39:09','user','2018-03-13 12:39:09','com.minh.nguyen.service.ProblemService','0',NULL),(150,10,2,2,86,0,'#include<bits/stdc++.h>\r\nusing namespace std;\r\nint main(){\r\n    int a,b;\r\n    cin >> a >> b;\r\n    cout << a + b;\r\n}',6,'ƒê√∫ng',NULL,'com.minh.nguyen.service.ProblemService','user','2018-03-13 12:40:54','user','2018-03-13 12:40:54','com.minh.nguyen.service.ProblemService','0',NULL),(151,10,2,2,79,0,'#include<bits/stdc++.h>\r\nusing namespace std;\r\nint main(){\r\n    int a,b;\r\n    cin >> a >> b;\r\n    cout << a + b;\r\n}',6,'ƒê√∫ng',NULL,'com.minh.nguyen.service.ProblemService','user','2018-03-13 12:44:50','user','2018-03-13 12:44:50','com.minh.nguyen.service.ProblemService','0',NULL),(152,10,2,1,82,0,'#include<bits/stdc++.h>\r\nusing namespace std;\r\nint main(){\r\n    int a,b;\r\n    cin >> a >> b;\r\n    cout << a + b;\r\n}',6,'ƒê√∫ng',NULL,'com.minh.nguyen.service.ProblemService','admin','2018-03-13 13:11:43','admin','2018-03-13 13:11:43','com.minh.nguyen.service.ProblemService','0',NULL),(153,10,2,1,89,0,'#include<bits/stdc++.h>\r\nusing namespace std;\r\nint main(){\r\n    int a,b;\r\n    cin >> a >> b;\r\n    cout << a + b;\r\n}',6,'ƒê√∫ng',NULL,'com.minh.nguyen.service.ProblemService','admin','2018-03-13 13:14:31','admin','2018-03-13 13:14:31','com.minh.nguyen.service.ProblemService','0',NULL),(154,10,2,1,93,0,'#include<bits/stdc++.h>\r\nusing namespace std;\r\nint main(){\r\n    int a,b;\r\n    cin >> a >> b;\r\n    cout << a + b;\r\n}',6,'ƒê√∫ng',NULL,'com.minh.nguyen.service.ProblemService','admin','2018-03-13 13:19:04','admin','2018-03-13 13:19:04','com.minh.nguyen.service.ProblemService','0',NULL),(155,10,2,2,109,0,'#include<bits/stdc++.h>\r\nusing namespace std;\r\nint main(){\r\n    \r\n    int a,c;\r\n    cin >> a >> c;\r\n    cout << a + c;\r\n}',6,'ƒê√∫ng',NULL,'com.minh.nguyen.service.ContestService','user','2018-03-13 13:22:02','user','2018-03-13 13:22:02','com.minh.nguyen.service.ContestService','0',NULL),(156,10,2,1,89,0,'#include<bits/stdc++.h>\r\nusing namespace std;\r\nint main(){\r\n    int a,b;\r\n    cin >>a >> b;\r\n    cout <<a + b;\r\n}',6,'ƒê√∫ng',NULL,'com.minh.nguyen.service.ProblemService','admin','2018-03-13 19:29:42','admin','2018-03-13 19:29:42','com.minh.nguyen.service.ProblemService','0',NULL),(157,10,2,1,0,0,'#include<bits/stdc++.h>\r\nusing namespace std;\r\nint main(){\r\n    int a,b;\r\n    cin >> a >> b;\r\n    cout << a + b;\r\n}',1,'L·ªói bi√™n d·ªãch',NULL,'com.minh.nguyen.service.ProblemService','admin','2018-03-16 20:13:45','admin','2018-03-16 20:13:45','com.minh.nguyen.service.ProblemService','0',NULL),(158,10,2,1,86,0,'#include<bits/stdc++.h>\r\nusing namespace std;\r\nint main(){\r\n    int a,b;\r\n    cin >> a >> b;\r\n    cout << a + b;\r\n}',6,'ƒê√∫ng',NULL,'com.minh.nguyen.service.ProblemService','admin','2018-03-16 20:13:57','admin','2018-03-16 20:13:57','com.minh.nguyen.service.ProblemService','0',NULL),(159,10,2,1,0,0,'#include<bits/stdc++.h>\r\nusing namespace std;\r\nint main(){\r\n    int a,b;\r\n    cin >>a >> b;\r\n    cout <<a + b;\r\n}',1,'L·ªói bi√™n d·ªãch',NULL,'com.minh.nguyen.service.ProblemService','admin','2018-03-21 21:03:44','admin','2018-03-21 21:03:44','com.minh.nguyen.service.ProblemService','0',NULL),(160,10,2,1,108,0,'#include<bits/stdc++.h>\r\nusing namespace std;\r\nint main(){\r\n    int a,b;\r\n    cin >>a >> b;\r\n    cout <<a + b;\r\n}',6,'ƒê√∫ng',NULL,'com.minh.nguyen.service.ProblemService','admin','2018-03-21 21:03:53','admin','2018-03-21 21:03:53','com.minh.nguyen.service.ProblemService','0',NULL),(161,10,2,1,87,0,'#include<bits/stdc++.h>\r\nusing namespace std;\r\nint main(){\r\n    int a,b;\r\n    cin >>a >> b;\r\n    cout <<a + b;\r\n}',6,'ƒê√∫ng',NULL,'com.minh.nguyen.service.ProblemService','admin','2018-03-21 21:04:35','admin','2018-03-21 21:04:35','com.minh.nguyen.service.ProblemService','0',NULL),(162,10,2,1,96,0,'#include<bits/stdc++.h>\r\nusing namespace std;\r\nint main(){\r\n    int a,b;\r\n    cin >>a >> b;\r\n    cout <<a + b;\r\n}',6,'ƒê√∫ng',NULL,'com.minh.nguyen.service.ProblemService','admin','2018-03-21 21:07:28','admin','2018-03-21 21:07:28','com.minh.nguyen.service.ProblemService','0',NULL),(163,10,2,1,102,0,'#include<bits/stdc++.h>\r\nusing namespace std;\r\nint main(){\r\n    int a,b;\r\n    cin >>a >> b;\r\n    cout <<a + b;\r\n}',6,'ƒê√∫ng',NULL,'com.minh.nguyen.service.ProblemService','admin','2018-03-21 21:08:49','admin','2018-03-21 21:08:49','com.minh.nguyen.service.ProblemService','0',NULL),(164,10,2,1,147,0,'#include<bits/stdc++.h>\r\nusing namespace std;\r\nint main(){\r\n    int a,b;\r\n    cin >>a >> b;\r\n    cout <<a + b;\r\n}',6,'ƒê√∫ng',NULL,'com.minh.nguyen.service.ProblemService','admin','2018-03-21 21:10:24','admin','2018-03-21 21:10:24','com.minh.nguyen.service.ProblemService','0',NULL),(165,10,2,1,107,0,'#include<bits/stdc++.h>\r\nusing namespace std;\r\nint main(){\r\n    int a,b;\r\n    cin >>a >> b;\r\n    cout <<a + b;\r\n}',6,'ƒê√∫ng',NULL,'com.minh.nguyen.service.ProblemService','admin','2018-03-21 21:14:33','admin','2018-03-21 21:14:33','com.minh.nguyen.service.ProblemService','0',NULL),(166,10,2,1,85,0,'#include<bits/stdc++.h>\r\nusing namespace std;\r\nint main(){\r\n    int a,b;\r\n    cin >>a >> b;\r\n    cout <<a + b;\r\n}',6,'ƒê√∫ng',NULL,'com.minh.nguyen.service.ProblemService','admin','2018-03-21 21:17:05','admin','2018-03-21 21:17:05','com.minh.nguyen.service.ProblemService','0',NULL),(167,10,2,1,105,0,'#include<bits/stdc++.h>\r\nusing namespace std;\r\nint main(){\r\n    int a,b;\r\n    cin >>a >> b;\r\n    cout <<a + b;\r\n}',6,'ƒê√∫ng',NULL,'com.minh.nguyen.service.ProblemService','admin','2018-03-21 21:17:14','admin','2018-03-21 21:17:14','com.minh.nguyen.service.ProblemService','0',NULL),(168,10,2,1,177,0,'#include<bits/stdc++.h>\r\nusing namespace std;\r\nint main(){\r\n    int a,b;\r\n    cin >>a >> b;\r\n    cout <<a + b;\r\n}',6,'ƒê√∫ng',NULL,'com.minh.nguyen.service.ProblemService','admin','2018-03-21 21:18:40','admin','2018-03-21 21:18:40','com.minh.nguyen.service.ProblemService','0',NULL),(169,10,2,1,141,0,'#include<bits/stdc++.h>\r\nusing namespace std;\r\nint main(){\r\n    int a,b;\r\n    cin >>a >> b;\r\n    cout <<a + b;\r\n}',6,'ƒê√∫ng',NULL,'com.minh.nguyen.service.ProblemService','admin','2018-03-21 21:18:40','admin','2018-03-21 21:18:40','com.minh.nguyen.service.ProblemService','0',NULL),(170,10,2,1,110,0,'#include<bits/stdc++.h>\r\nusing namespace std;\r\nint main(){\r\n    int a,b;\r\n    cin >>a >> b;\r\n    cout <<a + b + 2;\r\n}',5,'Sai k·∫øt qu·∫£',NULL,'com.minh.nguyen.service.ProblemService','admin','2018-03-21 21:19:04','admin','2018-03-21 21:19:04','com.minh.nguyen.service.ProblemService','0',NULL),(171,10,2,2,0,0,'#include<bits/stdc++.h>\r\nusing namespace std;\r\nint main(){\r\n    int a,b;\r\n    cin >> a >> b;\r\n    cout << a + b;\r\n}',1,'L·ªói bi√™n d·ªãch',NULL,'com.minh.nguyen.service.CourseService','user','2018-03-23 13:49:13','user','2018-03-23 13:49:13','com.minh.nguyen.service.CourseService','0',NULL),(172,10,2,2,0,0,'#include<bits/stdc++.h>\r\nusing namespace std;\r\nint main(){\r\n    int a,b;\r\n    cin >> a >> b;\r\n    cout << a + b;\r\n}',1,'L·ªói bi√™n d·ªãch',NULL,'com.minh.nguyen.service.CourseService','user','2018-03-23 13:49:46','user','2018-03-23 13:49:46','com.minh.nguyen.service.CourseService','0',NULL),(173,10,2,2,0,0,'#include<bits/stdc++.h>\r\nusing namespace std;\r\nint main(){\r\n    int a,b;\r\n    cin >> a >> b;\r\n    cout << a + b;\r\n}',1,'L·ªói bi√™n d·ªãch',NULL,'com.minh.nguyen.service.CourseService','user','2018-03-23 13:50:50','user','2018-03-23 13:50:50','com.minh.nguyen.service.CourseService','0',NULL),(174,10,2,2,0,0,'#include<bits/stdc++.h>\r\nusing namespace std;\r\nint main(){\r\n    int a,b;\r\n    cin >> a >> b;\r\n    cout << a + b;\r\n}',1,'L·ªói bi√™n d·ªãch',NULL,'com.minh.nguyen.service.ContestService','user','2018-03-23 13:57:02','user','2018-03-23 13:57:02','com.minh.nguyen.service.ContestService','0',NULL),(175,10,2,2,191,0,'#include<bits/stdc++.h>\r\nusing namespace std;\r\nint main(){\r\n    int a,b;\r\n    cin >> a >> b;\r\n    cout << a + b;\r\n}',6,'ƒê√∫ng',NULL,'com.minh.nguyen.service.ContestService','user','2018-03-23 13:59:47','user','2018-03-23 13:59:47','com.minh.nguyen.service.ContestService','0',NULL),(176,10,2,2,0,0,'#include<bits/stdc++.h>\r\nusing namespace std;\r\nint main(){\r\n    int a,b;\r\n    cin >> a >> b;\r\n    cout << a + b;\r\n}',1,'L·ªói bi√™n d·ªãch',NULL,'com.minh.nguyen.service.ContestService','user','2018-03-23 14:01:05','user','2018-03-23 14:01:05','com.minh.nguyen.service.ContestService','0',NULL),(177,10,2,2,0,0,'#include<bits/stdc++.h>\r\nusing namespace std;\r\nint main(){\r\n    int a,b;\r\n    cin >> a >> b;\r\n    cout << a + b;\r\n}',1,'L·ªói bi√™n d·ªãch',NULL,'com.minh.nguyen.service.ContestService','user','2018-03-23 14:01:55','user','2018-03-23 14:01:55','com.minh.nguyen.service.ContestService','0',NULL),(178,20,2,2,119,0,'#include<bits/stdc++.h>\r\nusing namespace std;\r\nint main(){\r\n    int a,b;\r\n    cin >> a >> b;\r\n    cout << a + b;\r\n}',6,'ƒê√∫ng',NULL,'com.minh.nguyen.service.ContestService','user','2018-03-23 14:18:22','user','2018-03-23 14:18:22','com.minh.nguyen.service.ContestService','0',NULL),(179,20,2,2,121,0,'#include<bits/stdc++.h>\r\nusing namespace std;\r\nint main(){\r\n    int a,b;\r\n    cin >> a >> b;\r\n    cout << a + b;\r\n}',6,'ƒê√∫ng',NULL,'com.minh.nguyen.service.ContestService','user','2018-03-23 14:18:40','user','2018-03-23 14:18:40','com.minh.nguyen.service.ContestService','0',NULL),(180,10,2,2,95,0,'#include<bits/stdc++.h>\r\nusing namespace std;\r\nint main(){\r\n    int a,b;\r\n    cin >> a >> b;\r\n    cout << a + b;\r\n}',6,'ƒê√∫ng',NULL,'com.minh.nguyen.service.CourseService','user','2018-03-23 14:19:47','user','2018-03-23 14:19:47','com.minh.nguyen.service.CourseService','0',NULL);
/*!40000 ALTER TABLE `submission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `submitdetail`
--

DROP TABLE IF EXISTS `submitdetail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `submitdetail` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `input` text,
  `output` text,
  `answer` text,
  `result` varchar(5000) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `timeRun` int(11) DEFAULT NULL,
  `memoryUsed` int(11) DEFAULT NULL,
  `createClass` varchar(100) DEFAULT NULL,
  `createUser` varchar(30) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `updateUser` varchar(45) DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `updateClass` varchar(45) DEFAULT NULL,
  `deleteFlg` varchar(1) DEFAULT NULL,
  `deleteTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=338 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `submitdetail`
--

LOCK TABLES `submitdetail` WRITE;
/*!40000 ALTER TABLE `submitdetail` DISABLE KEYS */;
INSERT INTO `submitdetail` VALUES (68,'1 2',NULL,'1\r\n2\r\n3','L·ªói runtime',0,0,0,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:24:48','minh.nt','2018-01-31 23:24:48','class com.minh.nguyen.service.BaseService','0',NULL),(69,'5 10',NULL,'5\r\n10\r\n15','L·ªói runtime',0,0,0,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:24:52','minh.nt','2018-01-31 23:24:52','class com.minh.nguyen.service.BaseService','0',NULL),(70,'123 1',NULL,'123\r\n1\r\n124\r\n','L·ªói runtime',0,0,0,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:24:56','minh.nt','2018-01-31 23:24:56','class com.minh.nguyen.service.BaseService','0',NULL),(71,'1 2','5','1\r\n2\r\n3','S·ªë d√≤ng ng∆∞·ªùi d√πng xu·∫•t (1) nhi·ªÅu h∆°n so v·ªõi k·∫øt qu·∫£ (3)!',5,163,0,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:26:03','minh.nt','2018-01-31 23:26:03','class com.minh.nguyen.service.BaseService','0',NULL),(72,'5 10','5','5\r\n10\r\n15','S·ªë d√≤ng ng∆∞·ªùi d√πng xu·∫•t (1) nhi·ªÅu h∆°n so v·ªõi k·∫øt qu·∫£ (3)!',5,150,0,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:26:04','minh.nt','2018-01-31 23:26:04','class com.minh.nguyen.service.BaseService','0',NULL),(73,'123 1','5','123\r\n1\r\n124\r\n','S·ªë d√≤ng ng∆∞·ªùi d√πng xu·∫•t (1) nhi·ªÅu h∆°n so v·ªõi k·∫øt qu·∫£ (3)!',5,272,0,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:26:04','minh.nt','2018-01-31 23:26:04','class com.minh.nguyen.service.BaseService','0',NULL),(74,'1 2',NULL,'1\r\n2\r\n3','L·ªói runtime',0,0,0,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:28:01','minh.nt','2018-01-31 23:28:01','class com.minh.nguyen.service.BaseService','0',NULL),(75,'5 10',NULL,'5\r\n10\r\n15','L·ªói runtime',0,0,0,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:28:04','minh.nt','2018-01-31 23:28:04','class com.minh.nguyen.service.BaseService','0',NULL),(76,'123 1',NULL,'123\r\n1\r\n124\r\n','L·ªói runtime',0,0,0,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:28:07','minh.nt','2018-01-31 23:28:07','class com.minh.nguyen.service.BaseService','0',NULL),(77,'1 2',NULL,'1\r\n2\r\n3','L·ªói runtime',0,0,0,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:29:34','minh.nt','2018-01-31 23:29:34','class com.minh.nguyen.service.BaseService','0',NULL),(78,'5 10',NULL,'5\r\n10\r\n15','L·ªói runtime',0,0,0,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:29:38','minh.nt','2018-01-31 23:29:38','class com.minh.nguyen.service.BaseService','0',NULL),(79,'123 1',NULL,'123\r\n1\r\n124\r\n','L·ªói runtime',0,0,0,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:29:42','minh.nt','2018-01-31 23:29:42','class com.minh.nguyen.service.BaseService','0',NULL),(80,'1 2',NULL,'1\r\n2\r\n3','L·ªói runtime',0,3816,0,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:31:58','minh.nt','2018-01-31 23:31:58','class com.minh.nguyen.service.BaseService','0',NULL),(81,'5 10',NULL,'5\r\n10\r\n15','L·ªói runtime',0,3782,0,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:32:02','minh.nt','2018-01-31 23:32:02','class com.minh.nguyen.service.BaseService','0',NULL),(82,'123 1',NULL,'123\r\n1\r\n124\r\n','L·ªói runtime',0,3678,0,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:32:05','minh.nt','2018-01-31 23:32:05','class com.minh.nguyen.service.BaseService','0',NULL),(83,'1 2','1\r\n2\r\n3','1\r\n2\r\n3','ƒê√∫ng',6,105,0,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:33:35','minh.nt','2018-01-31 23:33:35','class com.minh.nguyen.service.BaseService','0',NULL),(84,'5 10','5\r\n10\r\n15','5\r\n10\r\n15','ƒê√∫ng',6,93,0,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:33:35','minh.nt','2018-01-31 23:33:35','class com.minh.nguyen.service.BaseService','0',NULL),(85,'123 1','123\r\n1\r\n124','123\r\n1\r\n124\r\n','ƒê√∫ng',6,84,0,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:33:35','minh.nt','2018-01-31 23:33:35','class com.minh.nguyen.service.BaseService','0',NULL),(86,'1 2','1\r\n2\r\n3','1\r\n2\r\n3','ƒê√∫ng',6,146,0,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:33:43','minh.nt','2018-01-31 23:33:43','class com.minh.nguyen.service.BaseService','0',NULL),(87,'5 10','5\r\n10\r\n15','5\r\n10\r\n15','ƒê√∫ng',6,148,0,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:33:43','minh.nt','2018-01-31 23:33:43','class com.minh.nguyen.service.BaseService','0',NULL),(88,'123 1','123\r\n1\r\n124','123\r\n1\r\n124\r\n','ƒê√∫ng',6,119,0,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:33:43','minh.nt','2018-01-31 23:33:43','class com.minh.nguyen.service.BaseService','0',NULL),(89,'1 2','1\r\n2\r\n3','1\r\n2\r\n3','ƒê√∫ng',6,79,0,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:34:54','minh.nt','2018-01-31 23:34:54','class com.minh.nguyen.service.BaseService','0',NULL),(90,'5 10','5\r\n10\r\n15','5\r\n10\r\n15','ƒê√∫ng',6,72,0,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:34:54','minh.nt','2018-01-31 23:34:54','class com.minh.nguyen.service.BaseService','0',NULL),(91,'123 1','123\r\n1\r\n124','123\r\n1\r\n124\r\n','ƒê√∫ng',6,82,0,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:34:54','minh.nt','2018-01-31 23:34:54','class com.minh.nguyen.service.BaseService','0',NULL),(92,'1 2','1\r\n2\r\n3','1\r\n2\r\n3','ƒê√∫ng',6,130,0,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:34:57','minh.nt','2018-01-31 23:34:57','class com.minh.nguyen.service.BaseService','0',NULL),(93,'5 10','5\r\n10\r\n15','5\r\n10\r\n15','ƒê√∫ng',6,102,0,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:34:57','minh.nt','2018-01-31 23:34:57','class com.minh.nguyen.service.BaseService','0',NULL),(94,'123 1','123\r\n1\r\n124','123\r\n1\r\n124\r\n','ƒê√∫ng',6,137,0,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:34:58','minh.nt','2018-01-31 23:34:58','class com.minh.nguyen.service.BaseService','0',NULL),(95,'1 2','1\r\n2\r\n3','1\r\n2\r\n3','ƒê√∫ng',6,81,0,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:35:09','minh.nt','2018-01-31 23:35:09','class com.minh.nguyen.service.BaseService','0',NULL),(96,'5 10','5\r\n10\r\n15','5\r\n10\r\n15','ƒê√∫ng',6,70,0,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:35:10','minh.nt','2018-01-31 23:35:10','class com.minh.nguyen.service.BaseService','0',NULL),(97,'123 1','123\r\n1\r\n124','123\r\n1\r\n124\r\n','ƒê√∫ng',6,78,0,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:35:10','minh.nt','2018-01-31 23:35:10','class com.minh.nguyen.service.BaseService','0',NULL),(98,'1 2','1\r\n2\r\n3','1\r\n2\r\n3','ƒê√∫ng',6,82,0,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:35:16','minh.nt','2018-01-31 23:35:16','class com.minh.nguyen.service.BaseService','0',NULL),(99,'5 10','5\r\n10\r\n15','5\r\n10\r\n15','ƒê√∫ng',6,67,0,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:35:16','minh.nt','2018-01-31 23:35:16','class com.minh.nguyen.service.BaseService','0',NULL),(100,'123 1','123\r\n1\r\n124','123\r\n1\r\n124\r\n','ƒê√∫ng',6,70,0,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:35:16','minh.nt','2018-01-31 23:35:16','class com.minh.nguyen.service.BaseService','0',NULL),(101,'1 2',NULL,'1\r\n2\r\n3','L·ªói runtime',0,3536,0,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:35:50','minh.nt','2018-01-31 23:35:50','class com.minh.nguyen.service.BaseService','0',NULL),(102,'1 2','1\r\n2\r\n1\r\n','1\r\n2\r\n3','Sai k·∫øt qu·∫£ ·ªü d√≤ng th·ª© 3:\r\nOutput: 1\r\nK·∫øt qu·∫£: 3',5,80,0,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:35:51','minh.nt','2018-01-31 23:35:51','class com.minh.nguyen.service.BaseService','0',NULL),(103,'5 10','5\r\n10\r\n5\r\n','5\r\n10\r\n15','Sai k·∫øt qu·∫£ ·ªü d√≤ng th·ª© 3:\r\nOutput: 5\r\nK·∫øt qu·∫£: 15',5,89,0,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:35:51','minh.nt','2018-01-31 23:35:51','class com.minh.nguyen.service.BaseService','0',NULL),(104,'123 1','123\r\n1\r\n123\r\n','123\r\n1\r\n124\r\n','Sai k·∫øt qu·∫£ ·ªü d√≤ng th·ª© 3:\r\nOutput: 123\r\nK·∫øt qu·∫£: 124',5,85,0,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:35:51','minh.nt','2018-01-31 23:35:51','class com.minh.nguyen.service.BaseService','0',NULL),(105,'5 10',NULL,'5\r\n10\r\n15','L·ªói runtime',0,3221,0,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:35:54','minh.nt','2018-01-31 23:35:54','class com.minh.nguyen.service.BaseService','0',NULL),(106,'123 1',NULL,'123\r\n1\r\n124\r\n','L·ªói runtime',0,2872,0,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:35:56','minh.nt','2018-01-31 23:35:56','class com.minh.nguyen.service.BaseService','0',NULL),(107,'1 2','1\r\n2\r\n3\r\n','1\r\n2\r\n3','ƒê√∫ng',6,81,0,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:35:58','minh.nt','2018-01-31 23:35:58','class com.minh.nguyen.service.BaseService','0',NULL),(108,'5 10','5\r\n10\r\n15\r\n','5\r\n10\r\n15','ƒê√∫ng',6,89,0,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:35:58','minh.nt','2018-01-31 23:35:58','class com.minh.nguyen.service.BaseService','0',NULL),(109,'123 1','123\r\n1\r\n124\r\n','123\r\n1\r\n124\r\n','ƒê√∫ng',6,71,0,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:35:59','minh.nt','2018-01-31 23:35:59','class com.minh.nguyen.service.BaseService','0',NULL),(110,'1 2','3','3','ƒê√∫ng',6,159,0,'com.minh.nguyen.service.JudgeService','admin','2018-02-11 14:23:54','admin','2018-02-11 14:23:54','com.minh.nguyen.service.JudgeService','0',NULL),(111,'3 4','7','7','ƒê√∫ng',6,80,0,'com.minh.nguyen.service.JudgeService','admin','2018-02-11 14:23:54','admin','2018-02-11 14:23:54','com.minh.nguyen.service.JudgeService','0',NULL),(112,'5 4','9',' 8','Sai k·∫øt qu·∫£ ·ªü d√≤ng th·ª© 1:\r\nOutput: 9\r\nK·∫øt qu·∫£:  8',5,71,0,'com.minh.nguyen.service.JudgeService','admin','2018-02-11 14:23:54','admin','2018-02-11 14:23:54','com.minh.nguyen.service.JudgeService','0',NULL),(113,'1 2','3','3','ƒê√∫ng',6,102,0,'com.minh.nguyen.service.JudgeService','admin','2018-02-11 14:29:14','admin','2018-02-11 14:29:14','com.minh.nguyen.service.JudgeService','0',NULL),(114,'3 4','7','7','ƒê√∫ng',6,106,0,'com.minh.nguyen.service.JudgeService','admin','2018-02-11 14:29:15','admin','2018-02-11 14:29:15','com.minh.nguyen.service.JudgeService','0',NULL),(115,'5 4','9',' 8','Sai k·∫øt qu·∫£ ·ªü d√≤ng th·ª© 1:\r\nOutput: 9\r\nK·∫øt qu·∫£:  8',5,80,0,'com.minh.nguyen.service.JudgeService','admin','2018-02-11 14:29:16','admin','2018-02-11 14:29:16','com.minh.nguyen.service.JudgeService','0',NULL),(116,'1 2','3','3','ƒê√∫ng',6,127,0,'com.minh.nguyen.service.JudgeService','admin','2018-02-11 14:30:24','admin','2018-02-11 14:30:24','com.minh.nguyen.service.JudgeService','0',NULL),(117,'3 4','7','7','ƒê√∫ng',6,100,0,'com.minh.nguyen.service.JudgeService','admin','2018-02-11 14:30:24','admin','2018-02-11 14:30:24','com.minh.nguyen.service.JudgeService','0',NULL),(118,'5 4','9','9','ƒê√∫ng',6,145,0,'com.minh.nguyen.service.JudgeService','admin','2018-02-11 14:30:24','admin','2018-02-11 14:30:24','com.minh.nguyen.service.JudgeService','0',NULL),(119,'1 2','3','3','ƒê√∫ng',6,83,0,'com.minh.nguyen.service.JudgeService','admin','2018-02-11 14:35:50','admin','2018-02-11 14:35:50','com.minh.nguyen.service.JudgeService','0',NULL),(120,'3 4','7','7','ƒê√∫ng',6,80,0,'com.minh.nguyen.service.JudgeService','admin','2018-02-11 14:35:50','admin','2018-02-11 14:35:50','com.minh.nguyen.service.JudgeService','0',NULL),(121,'5 4','9','9','ƒê√∫ng',6,97,0,'com.minh.nguyen.service.JudgeService','admin','2018-02-11 14:35:51','admin','2018-02-11 14:35:50','com.minh.nguyen.service.JudgeService','0',NULL),(122,'1 2','3','3','ƒê√∫ng',6,145,0,'com.minh.nguyen.service.JudgeService','admin','2018-02-11 15:13:59','admin','2018-02-11 15:13:59','com.minh.nguyen.service.JudgeService','0',NULL),(123,'3 4','7','7','ƒê√∫ng',6,111,0,'com.minh.nguyen.service.JudgeService','admin','2018-02-11 15:13:59','admin','2018-02-11 15:13:59','com.minh.nguyen.service.JudgeService','0',NULL),(124,'5 4','9','9','ƒê√∫ng',6,72,0,'com.minh.nguyen.service.JudgeService','admin','2018-02-11 15:13:59','admin','2018-02-11 15:13:59','com.minh.nguyen.service.JudgeService','0',NULL),(125,'1 2','2','3','Sai k·∫øt qu·∫£ ·ªü d√≤ng th·ª© 1:\r\nOutput: 2\r\nK·∫øt qu·∫£: 3',5,186,0,'com.minh.nguyen.service.JudgeService','admin','2018-02-11 15:36:50','admin','2018-02-11 15:36:50','com.minh.nguyen.service.JudgeService','0',NULL),(126,'3 4','6','7','Sai k·∫øt qu·∫£ ·ªü d√≤ng th·ª© 1:\r\nOutput: 6\r\nK·∫øt qu·∫£: 7',5,121,0,'com.minh.nguyen.service.JudgeService','admin','2018-02-11 15:36:50','admin','2018-02-11 15:36:50','com.minh.nguyen.service.JudgeService','0',NULL),(127,'5 4','8','9','Sai k·∫øt qu·∫£ ·ªü d√≤ng th·ª© 1:\r\nOutput: 8\r\nK·∫øt qu·∫£: 9',5,121,0,'com.minh.nguyen.service.JudgeService','admin','2018-02-11 15:36:50','admin','2018-02-11 15:36:50','com.minh.nguyen.service.JudgeService','0',NULL),(128,'1 2','3','3','ƒê√∫ng',6,191,0,'com.minh.nguyen.service.JudgeService','admin','2018-02-11 15:48:55','admin','2018-02-11 15:48:55','com.minh.nguyen.service.JudgeService','0',NULL),(129,'3 4','7','7','ƒê√∫ng',6,141,0,'com.minh.nguyen.service.JudgeService','admin','2018-02-11 15:48:55','admin','2018-02-11 15:48:55','com.minh.nguyen.service.JudgeService','0',NULL),(130,'5 4','9','9','ƒê√∫ng',6,128,0,'com.minh.nguyen.service.JudgeService','admin','2018-02-11 15:48:55','admin','2018-02-11 15:48:55','com.minh.nguyen.service.JudgeService','0',NULL),(131,NULL,NULL,NULL,NULL,0,0,0,'com.minh.nguyen.service.JudgeService','user','2018-02-11 20:17:16','user','2018-02-11 20:17:16','com.minh.nguyen.service.JudgeService','0',NULL),(132,'1 2','3','3','ƒê√∫ng',6,367,0,'com.minh.nguyen.service.JudgeService','user','2018-02-11 20:18:08','user','2018-02-11 20:18:08','com.minh.nguyen.service.JudgeService','0',NULL),(133,'3 4','7','7','ƒê√∫ng',6,153,0,'com.minh.nguyen.service.JudgeService','user','2018-02-11 20:18:08','user','2018-02-11 20:18:08','com.minh.nguyen.service.JudgeService','0',NULL),(134,'5 4','9','9','ƒê√∫ng',6,164,0,'com.minh.nguyen.service.JudgeService','user','2018-02-11 20:18:08','user','2018-02-11 20:18:08','com.minh.nguyen.service.JudgeService','0',NULL),(135,'1 2','3','3','ƒê√∫ng',6,244,0,'com.minh.nguyen.service.JudgeService','user','2018-02-11 20:52:01','user','2018-02-11 20:52:01','com.minh.nguyen.service.JudgeService','0',NULL),(136,'3 4','7','7','ƒê√∫ng',6,133,0,'com.minh.nguyen.service.JudgeService','user','2018-02-11 20:52:01','user','2018-02-11 20:52:01','com.minh.nguyen.service.JudgeService','0',NULL),(137,'5 4','9','9','ƒê√∫ng',6,231,0,'com.minh.nguyen.service.JudgeService','user','2018-02-11 20:52:02','user','2018-02-11 20:52:02','com.minh.nguyen.service.JudgeService','0',NULL),(138,'1 2','3','3','ƒê√∫ng',6,123,0,'com.minh.nguyen.service.JudgeService','user','2018-02-11 20:53:07','user','2018-02-11 20:53:07','com.minh.nguyen.service.JudgeService','0',NULL),(139,'3 4','7','7','ƒê√∫ng',6,154,0,'com.minh.nguyen.service.JudgeService','user','2018-02-11 20:53:07','user','2018-02-11 20:53:07','com.minh.nguyen.service.JudgeService','0',NULL),(140,'5 4','9','9','ƒê√∫ng',6,91,0,'com.minh.nguyen.service.JudgeService','user','2018-02-11 20:53:07','user','2018-02-11 20:53:07','com.minh.nguyen.service.JudgeService','0',NULL),(141,'1 2','3','3','ƒê√∫ng',6,129,0,'com.minh.nguyen.service.JudgeService','user','2018-02-11 20:53:50','user','2018-02-11 20:53:50','com.minh.nguyen.service.JudgeService','0',NULL),(142,'3 4','7','7','ƒê√∫ng',6,176,0,'com.minh.nguyen.service.JudgeService','user','2018-02-11 20:53:51','user','2018-02-11 20:53:51','com.minh.nguyen.service.JudgeService','0',NULL),(143,'5 4','9','9','ƒê√∫ng',6,112,0,'com.minh.nguyen.service.JudgeService','user','2018-02-11 20:53:51','user','2018-02-11 20:53:51','com.minh.nguyen.service.JudgeService','0',NULL),(144,NULL,NULL,NULL,NULL,0,0,0,'com.minh.nguyen.service.JudgeService','user','2018-02-12 20:04:44','user','2018-02-12 20:04:44','com.minh.nguyen.service.JudgeService','0',NULL),(145,'1 2','3','3','ƒê√∫ng',6,110,0,'com.minh.nguyen.service.JudgeService','user','2018-02-12 20:05:12','user','2018-02-12 20:05:12','com.minh.nguyen.service.JudgeService','0',NULL),(146,'3 4','7','7','ƒê√∫ng',6,81,0,'com.minh.nguyen.service.JudgeService','user','2018-02-12 20:05:12','user','2018-02-12 20:05:12','com.minh.nguyen.service.JudgeService','0',NULL),(147,'5 4','9','9','ƒê√∫ng',6,88,0,'com.minh.nguyen.service.JudgeService','user','2018-02-12 20:05:12','user','2018-02-12 20:05:12','com.minh.nguyen.service.JudgeService','0',NULL),(148,NULL,NULL,NULL,NULL,0,0,0,'com.minh.nguyen.service.JudgeService','user','2018-02-14 11:21:26','user','2018-02-14 11:21:26','com.minh.nguyen.service.JudgeService','0',NULL),(149,'5 10','15','5\r\n10\r\n15','S·ªë d√≤ng ng∆∞·ªùi d√πng xu·∫•t (1) √≠t h∆°n so v·ªõi k·∫øt qu·∫£ (3)!',5,121,0,'com.minh.nguyen.service.JudgeService','user','2018-02-14 11:21:56','user','2018-02-14 11:21:56','com.minh.nguyen.service.JudgeService','0',NULL),(150,'123 1','124','123\r\n1\r\n124\r\n','S·ªë d√≤ng ng∆∞·ªùi d√πng xu·∫•t (1) √≠t h∆°n so v·ªõi k·∫øt qu·∫£ (3)!',5,91,0,'com.minh.nguyen.service.JudgeService','user','2018-02-14 11:21:56','user','2018-02-14 11:21:56','com.minh.nguyen.service.JudgeService','0',NULL),(151,'1 2','3','3','ƒê√∫ng',6,105,0,'com.minh.nguyen.service.JudgeService','user','2018-02-14 11:21:56','user','2018-02-14 11:21:56','com.minh.nguyen.service.JudgeService','0',NULL),(152,'1 2','3','3','ƒê√∫ng',6,208,0,'com.minh.nguyen.service.JudgeService','user','2018-02-14 12:49:01','user','2018-02-14 12:49:01','com.minh.nguyen.service.JudgeService','0',NULL),(153,'3 4','7','7','ƒê√∫ng',6,114,0,'com.minh.nguyen.service.JudgeService','user','2018-02-14 12:49:01','user','2018-02-14 12:49:01','com.minh.nguyen.service.JudgeService','0',NULL),(154,'5 4','9','9','ƒê√∫ng',6,116,0,'com.minh.nguyen.service.JudgeService','user','2018-02-14 12:49:02','user','2018-02-14 12:49:02','com.minh.nguyen.service.JudgeService','0',NULL),(155,'1 2','3','3','ƒê√∫ng',6,95,0,'com.minh.nguyen.service.JudgeService','user','2018-02-14 12:51:47','user','2018-02-14 12:51:47','com.minh.nguyen.service.JudgeService','0',NULL),(156,'3 4','7','7','ƒê√∫ng',6,91,0,'com.minh.nguyen.service.JudgeService','user','2018-02-14 12:51:47','user','2018-02-14 12:51:47','com.minh.nguyen.service.JudgeService','0',NULL),(157,'5 4','9','9','ƒê√∫ng',6,89,0,'com.minh.nguyen.service.JudgeService','user','2018-02-14 12:51:47','user','2018-02-14 12:51:47','com.minh.nguyen.service.JudgeService','0',NULL),(158,'1 2','3','3','ƒê√∫ng',6,122,0,'com.minh.nguyen.service.JudgeService','user','2018-02-14 12:54:49','user','2018-02-14 12:54:49','com.minh.nguyen.service.JudgeService','0',NULL),(159,'3 4','7','7','ƒê√∫ng',6,118,0,'com.minh.nguyen.service.JudgeService','user','2018-02-14 12:54:49','user','2018-02-14 12:54:49','com.minh.nguyen.service.JudgeService','0',NULL),(160,'5 4','9','9','ƒê√∫ng',6,111,0,'com.minh.nguyen.service.JudgeService','user','2018-02-14 12:54:49','user','2018-02-14 12:54:49','com.minh.nguyen.service.JudgeService','0',NULL),(161,'1 2','3','3','ƒê√∫ng',6,97,0,'com.minh.nguyen.service.JudgeService','user','2018-02-14 12:57:18','user','2018-02-14 12:57:18','com.minh.nguyen.service.JudgeService','0',NULL),(162,'3 4','7','7','ƒê√∫ng',6,84,0,'com.minh.nguyen.service.JudgeService','user','2018-02-14 12:57:18','user','2018-02-14 12:57:18','com.minh.nguyen.service.JudgeService','0',NULL),(163,'5 4','9','9','ƒê√∫ng',6,80,0,'com.minh.nguyen.service.JudgeService','user','2018-02-14 12:57:18','user','2018-02-14 12:57:18','com.minh.nguyen.service.JudgeService','0',NULL),(164,'1 2',NULL,'3','L·ªói runtime',0,99,0,'com.minh.nguyen.service.JudgeService','admin','2018-02-14 13:30:45','admin','2018-02-14 13:30:45','com.minh.nguyen.service.JudgeService','0',NULL),(165,'1 2',NULL,'3','L·ªói runtime',0,248,0,'com.minh.nguyen.service.JudgeService','admin','2018-02-14 13:31:47','admin','2018-02-14 13:31:47','com.minh.nguyen.service.JudgeService','0',NULL),(166,'1 2','3','3','ƒê√∫ng',6,117,0,'com.minh.nguyen.service.JudgeService','admin','2018-02-14 13:39:26','admin','2018-02-14 13:39:26','com.minh.nguyen.service.JudgeService','0',NULL),(167,'3 4','7','7','ƒê√∫ng',6,244,0,'com.minh.nguyen.service.JudgeService','admin','2018-02-14 13:39:27','admin','2018-02-14 13:39:27','com.minh.nguyen.service.JudgeService','0',NULL),(168,'5 4','9','9','ƒê√∫ng',6,144,0,'com.minh.nguyen.service.JudgeService','admin','2018-02-14 13:39:27','admin','2018-02-14 13:39:27','com.minh.nguyen.service.JudgeService','0',NULL),(169,'1 2',NULL,'3','L·ªói runtime',0,186,0,'com.minh.nguyen.service.JudgeService','admin','2018-02-14 13:39:50','admin','2018-02-14 13:39:50','com.minh.nguyen.service.JudgeService','0',NULL),(170,'5 10','15','15','ƒê√∫ng',6,180,0,'com.minh.nguyen.service.JudgeService','admin','2018-02-14 13:44:02','admin','2018-02-14 13:44:02','com.minh.nguyen.service.JudgeService','0',NULL),(171,'123 1','124','124\r\n','ƒê√∫ng',6,142,0,'com.minh.nguyen.service.JudgeService','admin','2018-02-14 13:44:02','admin','2018-02-14 13:44:02','com.minh.nguyen.service.JudgeService','0',NULL),(172,'1 2','3','3','ƒê√∫ng',6,107,0,'com.minh.nguyen.service.JudgeService','admin','2018-02-14 13:44:02','admin','2018-02-14 13:44:02','com.minh.nguyen.service.JudgeService','0',NULL),(173,'5 10','15','15','ƒê√∫ng',6,177,0,'com.minh.nguyen.service.JudgeService','admin','2018-02-14 13:54:54','admin','2018-02-14 13:54:54','com.minh.nguyen.service.JudgeService','0',NULL),(174,'123 1','124','124\r\n','ƒê√∫ng',6,119,0,'com.minh.nguyen.service.JudgeService','admin','2018-02-14 13:54:54','admin','2018-02-14 13:54:54','com.minh.nguyen.service.JudgeService','0',NULL),(175,'1 2','3','3','ƒê√∫ng',6,105,0,'com.minh.nguyen.service.JudgeService','admin','2018-02-14 13:54:54','admin','2018-02-14 13:54:54','com.minh.nguyen.service.JudgeService','0',NULL),(176,'5 10','15','15','ƒê√∫ng',6,126,0,'com.minh.nguyen.service.JudgeService','admin','2018-02-14 13:56:01','admin','2018-02-14 13:56:01','com.minh.nguyen.service.JudgeService','0',NULL),(177,'123 1','124','124\r\n','ƒê√∫ng',6,121,0,'com.minh.nguyen.service.JudgeService','admin','2018-02-14 13:56:01','admin','2018-02-14 13:56:01','com.minh.nguyen.service.JudgeService','0',NULL),(178,'1 2','3','3','ƒê√∫ng',6,116,0,'com.minh.nguyen.service.JudgeService','admin','2018-02-14 13:56:01','admin','2018-02-14 13:56:01','com.minh.nguyen.service.JudgeService','0',NULL),(179,NULL,NULL,NULL,NULL,0,0,0,'com.minh.nguyen.service.JudgeService','admin','2018-02-14 13:56:21','admin','2018-02-14 13:56:21','com.minh.nguyen.service.JudgeService','0',NULL),(180,'1 2','3','3','ƒê√∫ng',6,160,0,'com.minh.nguyen.service.JudgeService','user','2018-02-14 14:15:01','user','2018-02-14 14:15:01','com.minh.nguyen.service.JudgeService','0',NULL),(181,'3 4','7','7','ƒê√∫ng',6,175,0,'com.minh.nguyen.service.JudgeService','user','2018-02-14 14:15:02','user','2018-02-14 14:15:02','com.minh.nguyen.service.JudgeService','0',NULL),(182,'5 4','9','9','ƒê√∫ng',6,112,0,'com.minh.nguyen.service.JudgeService','user','2018-02-14 14:15:02','user','2018-02-14 14:15:02','com.minh.nguyen.service.JudgeService','0',NULL),(183,'5 10','15','15','ƒê√∫ng',6,111,0,'com.minh.nguyen.service.JudgeService','user','2018-02-14 14:15:16','user','2018-02-14 14:15:16','com.minh.nguyen.service.JudgeService','0',NULL),(184,'123 1','124','124\r\n','ƒê√∫ng',6,101,0,'com.minh.nguyen.service.JudgeService','user','2018-02-14 14:15:16','user','2018-02-14 14:15:16','com.minh.nguyen.service.JudgeService','0',NULL),(185,'1 2','3','3','ƒê√∫ng',6,175,0,'com.minh.nguyen.service.JudgeService','user','2018-02-14 14:15:16','user','2018-02-14 14:15:16','com.minh.nguyen.service.JudgeService','0',NULL),(186,NULL,NULL,NULL,'submission-snId-118-aman.cpp: In function \'int main()\':\r\nsubmission-snId-118-aman.cpp:6:10: error: no match for \'operator=\' (operand types are \'std::basic_ostream<char>\' and \'int\')\r\n cout << a= + b;\r\n          ^\r\nsubmission-snId-118-aman.cpp:6:10: note: candidate is:\r\nIn file included from C:/Program Files (x86)/CodeBlocks/MinGW/lib/gcc/mingw32/4.9.2/include/c++/istream:39:0,\r\n                 from C:/Program Files (x86)/CodeBlocks/MinGW/lib/gcc/mingw32/4.9.2/include/c++/sstream:38,\r\n                 from C:/Program Files (x86)/CodeBlocks/MinGW/lib/gcc/mingw32/4.9.2/include/c++/complex:45,\r\n                 from C:/Program Files (x86)/CodeBlocks/MinGW/lib/gcc/mingw32/4.9.2/include/c++/ccomplex:38,\r\n                 from C:/Program Files (x86)/CodeBlocks/MinGW/lib/gcc/mingw32/4.9.2/include/c++/mingw32/bits/stdc++.h:52,\r\nsubmission-snId-118-aman.cpp:1:\r\nC:/Program Files (x86)/CodeBlocks/MinGW/lib/gcc/mingw32/4.9.2/include/c++/ostream:58:11: note: std::basic_ostream<char>& std::basic_ostream<char>::operator=(const std::basic_ostream<char>&) <deleted>\r\n     class basic_ostream : virtual public basic_ios<_CharT, _Traits>\r\n           ^\r\nC:/Program Files (x86)/CodeBlocks/MinGW/lib/gcc/mingw32/4.9.2/include/c++/ostream:58:11: note:   no known conversion for argument 1 from \'int\' to \'const std::basic_ostream<char>&\'\r',0,0,0,'com.minh.nguyen.service.JudgeService','user','2018-02-14 15:46:37','user','2018-02-14 15:46:37','com.minh.nguyen.service.JudgeService','0',NULL),(187,NULL,NULL,NULL,NULL,0,0,0,'com.minh.nguyen.service.JudgeService','admin','2018-02-16 20:27:23','admin','2018-02-16 20:27:23','com.minh.nguyen.service.JudgeService','0',NULL),(188,'1 2','3','3','ƒê√∫ng',6,88,0,'com.minh.nguyen.service.JudgeService','admin','2018-02-16 20:27:33','admin','2018-02-16 20:27:33','com.minh.nguyen.service.JudgeService','0',NULL),(189,'3 4','7','7','ƒê√∫ng',6,169,0,'com.minh.nguyen.service.JudgeService','admin','2018-02-16 20:27:34','admin','2018-02-16 20:27:34','com.minh.nguyen.service.JudgeService','0',NULL),(190,'5 4','9','9','ƒê√∫ng',6,194,0,'com.minh.nguyen.service.JudgeService','admin','2018-02-16 20:27:34','admin','2018-02-16 20:27:34','com.minh.nguyen.service.JudgeService','0',NULL),(191,'1 2','3','3','ƒê√∫ng',6,104,0,'com.minh.nguyen.service.JudgeService','admin','2018-02-16 20:35:05','admin','2018-02-16 20:35:05','com.minh.nguyen.service.JudgeService','0',NULL),(192,'3 4','7','7','ƒê√∫ng',6,106,0,'com.minh.nguyen.service.JudgeService','admin','2018-02-16 20:35:05','admin','2018-02-16 20:35:05','com.minh.nguyen.service.JudgeService','0',NULL),(193,'5 4','9','9','ƒê√∫ng',6,328,0,'com.minh.nguyen.service.JudgeService','admin','2018-02-16 20:35:06','admin','2018-02-16 20:35:06','com.minh.nguyen.service.JudgeService','0',NULL),(194,'1 2',NULL,'3','L·ªói runtime',0,56,0,'com.minh.nguyen.service.JudgeService','user','2018-02-24 21:44:43','user','2018-02-24 21:44:43','com.minh.nguyen.service.JudgeService','0',NULL),(195,'3 4',NULL,'7','L·ªói runtime',0,38,0,'com.minh.nguyen.service.JudgeService','user','2018-02-24 21:44:43','user','2018-02-24 21:44:43','com.minh.nguyen.service.JudgeService','0',NULL),(196,'5 4',NULL,'9','L·ªói runtime',0,24,0,'com.minh.nguyen.service.JudgeService','user','2018-02-24 21:44:43','user','2018-02-24 21:44:43','com.minh.nguyen.service.JudgeService','0',NULL),(197,'1 2',NULL,'3','L·ªói runtime',0,55,0,'com.minh.nguyen.service.JudgeService','user','2018-02-24 21:45:15','user','2018-02-24 21:45:15','com.minh.nguyen.service.JudgeService','0',NULL),(198,'3 4',NULL,'7','L·ªói runtime',0,66,0,'com.minh.nguyen.service.JudgeService','user','2018-02-24 21:45:15','user','2018-02-24 21:45:15','com.minh.nguyen.service.JudgeService','0',NULL),(199,'5 4',NULL,'9','L·ªói runtime',0,41,0,'com.minh.nguyen.service.JudgeService','user','2018-02-24 21:45:15','user','2018-02-24 21:45:15','com.minh.nguyen.service.JudgeService','0',NULL),(200,'1 2',NULL,'3','L·ªói runtime',0,33,0,'com.minh.nguyen.service.JudgeService','user','2018-02-24 21:45:50','user','2018-02-24 21:45:50','com.minh.nguyen.service.JudgeService','0',NULL),(201,'3 4',NULL,'7','L·ªói runtime',0,58,0,'com.minh.nguyen.service.JudgeService','user','2018-02-24 21:45:51','user','2018-02-24 21:45:51','com.minh.nguyen.service.JudgeService','0',NULL),(202,'5 4',NULL,'9','L·ªói runtime',0,34,0,'com.minh.nguyen.service.JudgeService','user','2018-02-24 21:45:51','user','2018-02-24 21:45:51','com.minh.nguyen.service.JudgeService','0',NULL),(203,'1 2',NULL,'3','L·ªói runtime',0,15,0,'com.minh.nguyen.service.JudgeService','user','2018-02-24 21:46:43','user','2018-02-24 21:46:43','com.minh.nguyen.service.JudgeService','0',NULL),(204,'3 4',NULL,'7','L·ªói runtime',0,15,0,'com.minh.nguyen.service.JudgeService','user','2018-02-24 21:46:43','user','2018-02-24 21:46:43','com.minh.nguyen.service.JudgeService','0',NULL),(205,'5 4',NULL,'9','L·ªói runtime',0,15,0,'com.minh.nguyen.service.JudgeService','user','2018-02-24 21:46:43','user','2018-02-24 21:46:43','com.minh.nguyen.service.JudgeService','0',NULL),(206,'1 2',NULL,'3','L·ªói runtime',0,67,0,'com.minh.nguyen.service.JudgeService','admin','2018-02-25 21:01:36','admin','2018-02-25 21:01:36','com.minh.nguyen.service.JudgeService','0',NULL),(207,'3 4',NULL,'7','L·ªói runtime',0,29,0,'com.minh.nguyen.service.JudgeService','admin','2018-02-25 21:01:36','admin','2018-02-25 21:01:36','com.minh.nguyen.service.JudgeService','0',NULL),(208,'5 4',NULL,'9','L·ªói runtime',0,38,0,'com.minh.nguyen.service.JudgeService','admin','2018-02-25 21:01:36','admin','2018-02-25 21:01:36','com.minh.nguyen.service.JudgeService','0',NULL),(209,'1 2',NULL,'3','L·ªói runtime',0,43,0,'com.minh.nguyen.service.JudgeService','admin','2018-02-25 21:01:43','admin','2018-02-25 21:01:43','com.minh.nguyen.service.JudgeService','0',NULL),(210,'3 4',NULL,'7','L·ªói runtime',0,26,0,'com.minh.nguyen.service.JudgeService','admin','2018-02-25 21:01:43','admin','2018-02-25 21:01:43','com.minh.nguyen.service.JudgeService','0',NULL),(211,'5 4',NULL,'9','L·ªói runtime',0,24,0,'com.minh.nguyen.service.JudgeService','admin','2018-02-25 21:01:43','admin','2018-02-25 21:01:43','com.minh.nguyen.service.JudgeService','0',NULL),(212,'1 2','3','3','ƒê√∫ng',6,32,0,'com.minh.nguyen.service.JudgeService','admin','2018-02-25 21:04:27','admin','2018-02-25 21:04:27','com.minh.nguyen.service.JudgeService','0',NULL),(213,'3 4','7','7','ƒê√∫ng',6,23,0,'com.minh.nguyen.service.JudgeService','admin','2018-02-25 21:04:27','admin','2018-02-25 21:04:27','com.minh.nguyen.service.JudgeService','0',NULL),(214,'5 4','9','9','ƒê√∫ng',6,22,0,'com.minh.nguyen.service.JudgeService','admin','2018-02-25 21:04:27','admin','2018-02-25 21:04:27','com.minh.nguyen.service.JudgeService','0',NULL),(215,NULL,NULL,NULL,NULL,0,0,0,'com.minh.nguyen.service.JudgeService','user','2018-03-11 22:53:48','user','2018-03-11 22:53:48','com.minh.nguyen.service.JudgeService','0',NULL),(216,'5 10',NULL,'15','L·ªói runtime',0,90,0,'com.minh.nguyen.service.JudgeService','user','2018-03-11 22:54:13','user','2018-03-11 22:54:13','com.minh.nguyen.service.JudgeService','0',NULL),(217,'123 1',NULL,'124\r\n','L·ªói runtime',0,72,0,'com.minh.nguyen.service.JudgeService','user','2018-03-11 22:54:13','user','2018-03-11 22:54:13','com.minh.nguyen.service.JudgeService','0',NULL),(218,'1 2',NULL,'3','L·ªói runtime',0,79,0,'com.minh.nguyen.service.JudgeService','user','2018-03-11 22:54:13','user','2018-03-11 22:54:13','com.minh.nguyen.service.JudgeService','0',NULL),(219,'5 10',NULL,'15','L·ªói runtime',0,84,0,'com.minh.nguyen.service.JudgeService','user','2018-03-11 22:55:02','user','2018-03-11 22:55:02','com.minh.nguyen.service.JudgeService','0',NULL),(220,'123 1',NULL,'124\r\n','L·ªói runtime',0,87,0,'com.minh.nguyen.service.JudgeService','user','2018-03-11 22:55:02','user','2018-03-11 22:55:02','com.minh.nguyen.service.JudgeService','0',NULL),(221,'1 2',NULL,'3','L·ªói runtime',0,377,0,'com.minh.nguyen.service.JudgeService','user','2018-03-11 22:55:02','user','2018-03-11 22:55:02','com.minh.nguyen.service.JudgeService','0',NULL),(222,'5 10','15','15','ƒê√∫ng',6,165,0,'com.minh.nguyen.service.JudgeService','user','2018-03-11 23:00:01','user','2018-03-11 23:00:01','com.minh.nguyen.service.JudgeService','0',NULL),(223,'123 1','124','124\r\n','ƒê√∫ng',6,183,0,'com.minh.nguyen.service.JudgeService','user','2018-03-11 23:00:02','user','2018-03-11 23:00:02','com.minh.nguyen.service.JudgeService','0',NULL),(224,'1 2','3','3','ƒê√∫ng',6,86,0,'com.minh.nguyen.service.JudgeService','user','2018-03-11 23:00:02','user','2018-03-11 23:00:02','com.minh.nguyen.service.JudgeService','0',NULL),(225,'5 10','15','15','ƒê√∫ng',6,99,0,'com.minh.nguyen.service.JudgeService','user','2018-03-11 23:06:47','user','2018-03-11 23:06:47','com.minh.nguyen.service.JudgeService','0',NULL),(226,'123 1','124','124\r\n','ƒê√∫ng',6,94,0,'com.minh.nguyen.service.JudgeService','user','2018-03-11 23:06:48','user','2018-03-11 23:06:48','com.minh.nguyen.service.JudgeService','0',NULL),(227,'1 2','3','3','ƒê√∫ng',6,85,0,'com.minh.nguyen.service.JudgeService','user','2018-03-11 23:06:48','user','2018-03-11 23:06:48','com.minh.nguyen.service.JudgeService','0',NULL),(228,'5 10','15','15','ƒê√∫ng',6,117,0,'com.minh.nguyen.service.JudgeService','user','2018-03-11 23:09:57','user','2018-03-11 23:09:57','com.minh.nguyen.service.JudgeService','0',NULL),(229,'123 1','124','124\r\n','ƒê√∫ng',6,119,0,'com.minh.nguyen.service.JudgeService','user','2018-03-11 23:09:57','user','2018-03-11 23:09:57','com.minh.nguyen.service.JudgeService','0',NULL),(230,'1 2','3','3','ƒê√∫ng',6,75,0,'com.minh.nguyen.service.JudgeService','user','2018-03-11 23:09:57','user','2018-03-11 23:09:57','com.minh.nguyen.service.JudgeService','0',NULL),(231,NULL,NULL,NULL,NULL,0,0,0,'com.minh.nguyen.service.JudgeService','user','2018-03-12 13:19:28','user','2018-03-12 13:19:28','com.minh.nguyen.service.JudgeService','0',NULL),(232,'5 10','15','15','ƒê√∫ng',6,102,0,'com.minh.nguyen.service.JudgeService','user','2018-03-12 13:23:10','user','2018-03-12 13:23:10','com.minh.nguyen.service.JudgeService','0',NULL),(233,'123 1','124','124\r\n','ƒê√∫ng',6,78,0,'com.minh.nguyen.service.JudgeService','user','2018-03-12 13:23:10','user','2018-03-12 13:23:10','com.minh.nguyen.service.JudgeService','0',NULL),(234,'1 2','3','3','ƒê√∫ng',6,76,0,'com.minh.nguyen.service.JudgeService','user','2018-03-12 13:23:10','user','2018-03-12 13:23:10','com.minh.nguyen.service.JudgeService','0',NULL),(235,'5 10','15','15','ƒê√∫ng',6,86,0,'com.minh.nguyen.service.JudgeService','user','2018-03-12 13:25:41','user','2018-03-12 13:25:41','com.minh.nguyen.service.JudgeService','0',NULL),(236,'123 1','124','124\r\n','ƒê√∫ng',6,76,0,'com.minh.nguyen.service.JudgeService','user','2018-03-12 13:25:41','user','2018-03-12 13:25:41','com.minh.nguyen.service.JudgeService','0',NULL),(237,'1 2','3','3','ƒê√∫ng',6,119,0,'com.minh.nguyen.service.JudgeService','user','2018-03-12 13:25:41','user','2018-03-12 13:25:41','com.minh.nguyen.service.JudgeService','0',NULL),(238,'5 10','15','15','ƒê√∫ng',6,110,0,'com.minh.nguyen.service.JudgeService','user','2018-03-12 13:25:57','user','2018-03-12 13:25:57','com.minh.nguyen.service.JudgeService','0',NULL),(239,'123 1','124','124\r\n','ƒê√∫ng',6,127,0,'com.minh.nguyen.service.JudgeService','user','2018-03-12 13:25:57','user','2018-03-12 13:25:57','com.minh.nguyen.service.JudgeService','0',NULL),(240,'1 2','3','3','ƒê√∫ng',6,133,0,'com.minh.nguyen.service.JudgeService','user','2018-03-12 13:25:57','user','2018-03-12 13:25:57','com.minh.nguyen.service.JudgeService','0',NULL),(241,'5 10','15','15','ƒê√∫ng',6,99,0,'com.minh.nguyen.service.JudgeService','user','2018-03-12 13:26:17','user','2018-03-12 13:26:17','com.minh.nguyen.service.JudgeService','0',NULL),(242,'123 1','124','124\r\n','ƒê√∫ng',6,145,0,'com.minh.nguyen.service.JudgeService','user','2018-03-12 13:26:17','user','2018-03-12 13:26:17','com.minh.nguyen.service.JudgeService','0',NULL),(243,'1 2','3','3','ƒê√∫ng',6,107,0,'com.minh.nguyen.service.JudgeService','user','2018-03-12 13:26:18','user','2018-03-12 13:26:18','com.minh.nguyen.service.JudgeService','0',NULL),(244,'5 10','15','15','ƒê√∫ng',6,103,0,'com.minh.nguyen.service.JudgeService','user','2018-03-12 13:49:25','user','2018-03-12 13:49:25','com.minh.nguyen.service.JudgeService','0',NULL),(245,'123 1','124','124\r\n','ƒê√∫ng',6,106,0,'com.minh.nguyen.service.JudgeService','user','2018-03-12 13:49:25','user','2018-03-12 13:49:25','com.minh.nguyen.service.JudgeService','0',NULL),(246,'1 2','3','3','ƒê√∫ng',6,95,0,'com.minh.nguyen.service.JudgeService','user','2018-03-12 13:49:25','user','2018-03-12 13:49:25','com.minh.nguyen.service.JudgeService','0',NULL),(247,NULL,NULL,NULL,NULL,0,0,0,'com.minh.nguyen.service.JudgeService','user','2018-03-12 22:35:36','user','2018-03-12 22:35:36','com.minh.nguyen.service.JudgeService','0',NULL),(248,'5 10','15','15','ƒê√∫ng',6,130,0,'com.minh.nguyen.service.JudgeService','user','2018-03-12 22:35:45','user','2018-03-12 22:35:45','com.minh.nguyen.service.JudgeService','0',NULL),(249,'123 1','124','124\r\n','ƒê√∫ng',6,91,0,'com.minh.nguyen.service.JudgeService','user','2018-03-12 22:35:45','user','2018-03-12 22:35:45','com.minh.nguyen.service.JudgeService','0',NULL),(250,'1 2','3','3','ƒê√∫ng',6,95,0,'com.minh.nguyen.service.JudgeService','user','2018-03-12 22:35:45','user','2018-03-12 22:35:45','com.minh.nguyen.service.JudgeService','0',NULL),(251,NULL,NULL,NULL,NULL,0,0,0,'com.minh.nguyen.service.JudgeService','user','2018-03-13 12:37:01','user','2018-03-13 12:37:01','com.minh.nguyen.service.JudgeService','0',NULL),(252,'5 10','15','15','ƒê√∫ng',6,114,0,'com.minh.nguyen.service.JudgeService','user','2018-03-13 12:37:13','user','2018-03-13 12:37:13','com.minh.nguyen.service.JudgeService','0',NULL),(253,'123 1','124','124\r\n','ƒê√∫ng',6,104,0,'com.minh.nguyen.service.JudgeService','user','2018-03-13 12:37:13','user','2018-03-13 12:37:13','com.minh.nguyen.service.JudgeService','0',NULL),(254,'1 2','3','3','ƒê√∫ng',6,112,0,'com.minh.nguyen.service.JudgeService','user','2018-03-13 12:37:13','user','2018-03-13 12:37:13','com.minh.nguyen.service.JudgeService','0',NULL),(255,'5 10','15','15','ƒê√∫ng',6,176,0,'com.minh.nguyen.service.JudgeService','user','2018-03-13 12:37:34','user','2018-03-13 12:37:34','com.minh.nguyen.service.JudgeService','0',NULL),(256,'123 1','124','124\r\n','ƒê√∫ng',6,97,0,'com.minh.nguyen.service.JudgeService','user','2018-03-13 12:37:35','user','2018-03-13 12:37:35','com.minh.nguyen.service.JudgeService','0',NULL),(257,'1 2','3','3','ƒê√∫ng',6,88,0,'com.minh.nguyen.service.JudgeService','user','2018-03-13 12:37:35','user','2018-03-13 12:37:35','com.minh.nguyen.service.JudgeService','0',NULL),(258,'5 10','15','15','ƒê√∫ng',6,86,0,'com.minh.nguyen.service.JudgeService','user','2018-03-13 12:39:11','user','2018-03-13 12:39:11','com.minh.nguyen.service.JudgeService','0',NULL),(259,'123 1','124','124\r\n','ƒê√∫ng',6,68,0,'com.minh.nguyen.service.JudgeService','user','2018-03-13 12:39:11','user','2018-03-13 12:39:11','com.minh.nguyen.service.JudgeService','0',NULL),(260,'1 2','3','3','ƒê√∫ng',6,71,0,'com.minh.nguyen.service.JudgeService','user','2018-03-13 12:39:11','user','2018-03-13 12:39:11','com.minh.nguyen.service.JudgeService','0',NULL),(261,'5 10','15','15','ƒê√∫ng',6,89,0,'com.minh.nguyen.service.JudgeService','user','2018-03-13 12:40:57','user','2018-03-13 12:40:57','com.minh.nguyen.service.JudgeService','0',NULL),(262,'123 1','124','124\r\n','ƒê√∫ng',6,93,0,'com.minh.nguyen.service.JudgeService','user','2018-03-13 12:40:57','user','2018-03-13 12:40:57','com.minh.nguyen.service.JudgeService','0',NULL),(263,'1 2','3','3','ƒê√∫ng',6,78,0,'com.minh.nguyen.service.JudgeService','user','2018-03-13 12:40:57','user','2018-03-13 12:40:57','com.minh.nguyen.service.JudgeService','0',NULL),(264,'5 10','15','15','ƒê√∫ng',6,88,0,'com.minh.nguyen.service.JudgeService','user','2018-03-13 12:44:52','user','2018-03-13 12:44:52','com.minh.nguyen.service.JudgeService','0',NULL),(265,'123 1','124','124\r\n','ƒê√∫ng',6,80,0,'com.minh.nguyen.service.JudgeService','user','2018-03-13 12:44:53','user','2018-03-13 12:44:53','com.minh.nguyen.service.JudgeService','0',NULL),(266,'1 2','3','3','ƒê√∫ng',6,69,0,'com.minh.nguyen.service.JudgeService','user','2018-03-13 12:44:53','user','2018-03-13 12:44:53','com.minh.nguyen.service.JudgeService','0',NULL),(267,'5 10','15','15','ƒê√∫ng',6,91,0,'com.minh.nguyen.service.JudgeService','admin','2018-03-13 13:11:46','admin','2018-03-13 13:11:46','com.minh.nguyen.service.JudgeService','0',NULL),(268,'123 1','124','124\r\n','ƒê√∫ng',6,79,0,'com.minh.nguyen.service.JudgeService','admin','2018-03-13 13:11:47','admin','2018-03-13 13:11:47','com.minh.nguyen.service.JudgeService','0',NULL),(269,'1 2','3','3','ƒê√∫ng',6,78,0,'com.minh.nguyen.service.JudgeService','admin','2018-03-13 13:11:47','admin','2018-03-13 13:11:47','com.minh.nguyen.service.JudgeService','0',NULL),(270,'5 10','15','15','ƒê√∫ng',6,86,0,'com.minh.nguyen.service.JudgeService','admin','2018-03-13 13:14:34','admin','2018-03-13 13:14:34','com.minh.nguyen.service.JudgeService','0',NULL),(271,'123 1','124','124\r\n','ƒê√∫ng',6,111,0,'com.minh.nguyen.service.JudgeService','admin','2018-03-13 13:14:34','admin','2018-03-13 13:14:34','com.minh.nguyen.service.JudgeService','0',NULL),(272,'1 2','3','3','ƒê√∫ng',6,71,0,'com.minh.nguyen.service.JudgeService','admin','2018-03-13 13:14:34','admin','2018-03-13 13:14:34','com.minh.nguyen.service.JudgeService','0',NULL),(273,'5 10','15','15','ƒê√∫ng',6,90,0,'com.minh.nguyen.service.JudgeService','admin','2018-03-13 13:19:07','admin','2018-03-13 13:19:07','com.minh.nguyen.service.JudgeService','0',NULL),(274,'123 1','124','124\r\n','ƒê√∫ng',6,77,0,'com.minh.nguyen.service.JudgeService','admin','2018-03-13 13:19:07','admin','2018-03-13 13:19:07','com.minh.nguyen.service.JudgeService','0',NULL),(275,'1 2','3','3','ƒê√∫ng',6,114,0,'com.minh.nguyen.service.JudgeService','admin','2018-03-13 13:19:07','admin','2018-03-13 13:19:07','com.minh.nguyen.service.JudgeService','0',NULL),(276,'5 10','15','15','ƒê√∫ng',6,117,0,'com.minh.nguyen.service.JudgeService','user','2018-03-13 13:22:06','user','2018-03-13 13:22:06','com.minh.nguyen.service.JudgeService','0',NULL),(277,'123 1','124','124\r\n','ƒê√∫ng',6,116,0,'com.minh.nguyen.service.JudgeService','user','2018-03-13 13:22:06','user','2018-03-13 13:22:06','com.minh.nguyen.service.JudgeService','0',NULL),(278,'1 2','3','3','ƒê√∫ng',6,96,0,'com.minh.nguyen.service.JudgeService','user','2018-03-13 13:22:06','user','2018-03-13 13:22:06','com.minh.nguyen.service.JudgeService','0',NULL),(279,'5 10','15','15','ƒê√∫ng',6,95,0,'com.minh.nguyen.service.JudgeService','admin','2018-03-13 19:29:47','admin','2018-03-13 19:29:47','com.minh.nguyen.service.JudgeService','0',NULL),(280,'123 1','124','124\r\n','ƒê√∫ng',6,88,0,'com.minh.nguyen.service.JudgeService','admin','2018-03-13 19:29:47','admin','2018-03-13 19:29:47','com.minh.nguyen.service.JudgeService','0',NULL),(281,'1 2','3','3','ƒê√∫ng',6,86,0,'com.minh.nguyen.service.JudgeService','admin','2018-03-13 19:29:47','admin','2018-03-13 19:29:47','com.minh.nguyen.service.JudgeService','0',NULL),(282,NULL,NULL,NULL,NULL,0,0,0,'com.minh.nguyen.service.JudgeService','admin','2018-03-16 20:13:50','admin','2018-03-16 20:13:50','com.minh.nguyen.service.JudgeService','0',NULL),(283,'5 10','15','15','ƒê√∫ng',6,94,0,'com.minh.nguyen.service.JudgeService','admin','2018-03-16 20:14:00','admin','2018-03-16 20:14:00','com.minh.nguyen.service.JudgeService','0',NULL),(284,'123 1','124','124\r\n','ƒê√∫ng',6,77,0,'com.minh.nguyen.service.JudgeService','admin','2018-03-16 20:14:00','admin','2018-03-16 20:14:00','com.minh.nguyen.service.JudgeService','0',NULL),(285,'1 2','3','3','ƒê√∫ng',6,89,0,'com.minh.nguyen.service.JudgeService','admin','2018-03-16 20:14:01','admin','2018-03-16 20:14:01','com.minh.nguyen.service.JudgeService','0',NULL),(286,NULL,NULL,NULL,NULL,0,0,0,'com.minh.nguyen.service.JudgeService','admin','2018-03-21 21:03:50','admin','2018-03-21 21:03:50','com.minh.nguyen.service.JudgeService','0',NULL),(287,'5 10','15','15','ƒê√∫ng',6,155,0,'com.minh.nguyen.service.JudgeService','admin','2018-03-21 21:03:57','admin','2018-03-21 21:03:57','com.minh.nguyen.service.JudgeService','0',NULL),(288,'123 1','124','124\r\n','ƒê√∫ng',6,82,0,'com.minh.nguyen.service.JudgeService','admin','2018-03-21 21:03:57','admin','2018-03-21 21:03:57','com.minh.nguyen.service.JudgeService','0',NULL),(289,'1 2','3','3','ƒê√∫ng',6,87,0,'com.minh.nguyen.service.JudgeService','admin','2018-03-21 21:03:57','admin','2018-03-21 21:03:57','com.minh.nguyen.service.JudgeService','0',NULL),(290,'5 10','15','15','ƒê√∫ng',6,95,0,'com.minh.nguyen.service.JudgeService','admin','2018-03-21 21:04:39','admin','2018-03-21 21:04:39','com.minh.nguyen.service.JudgeService','0',NULL),(291,'123 1','124','124\r\n','ƒê√∫ng',6,82,0,'com.minh.nguyen.service.JudgeService','admin','2018-03-21 21:04:39','admin','2018-03-21 21:04:39','com.minh.nguyen.service.JudgeService','0',NULL),(292,'1 2','3','3','ƒê√∫ng',6,84,0,'com.minh.nguyen.service.JudgeService','admin','2018-03-21 21:04:39','admin','2018-03-21 21:04:39','com.minh.nguyen.service.JudgeService','0',NULL),(293,'5 10','15','15','ƒê√∫ng',6,118,0,'com.minh.nguyen.service.JudgeService','admin','2018-03-21 21:07:32','admin','2018-03-21 21:07:32','com.minh.nguyen.service.JudgeService','0',NULL),(294,'123 1','124','124\r\n','ƒê√∫ng',6,88,0,'com.minh.nguyen.service.JudgeService','admin','2018-03-21 21:07:32','admin','2018-03-21 21:07:32','com.minh.nguyen.service.JudgeService','0',NULL),(295,'1 2','3','3','ƒê√∫ng',6,83,0,'com.minh.nguyen.service.JudgeService','admin','2018-03-21 21:07:32','admin','2018-03-21 21:07:32','com.minh.nguyen.service.JudgeService','0',NULL),(296,'5 10','15','15','ƒê√∫ng',6,109,0,'com.minh.nguyen.service.JudgeService','admin','2018-03-21 21:08:53','admin','2018-03-21 21:08:53','com.minh.nguyen.service.JudgeService','0',NULL),(297,'123 1','124','124\r\n','ƒê√∫ng',6,87,0,'com.minh.nguyen.service.JudgeService','admin','2018-03-21 21:08:53','admin','2018-03-21 21:08:53','com.minh.nguyen.service.JudgeService','0',NULL),(298,'1 2','3','3','ƒê√∫ng',6,112,0,'com.minh.nguyen.service.JudgeService','admin','2018-03-21 21:08:53','admin','2018-03-21 21:08:53','com.minh.nguyen.service.JudgeService','0',NULL),(299,'5 10','15','15','ƒê√∫ng',6,156,0,'com.minh.nguyen.service.JudgeService','admin','2018-03-21 21:10:29','admin','2018-03-21 21:10:29','com.minh.nguyen.service.JudgeService','0',NULL),(300,'123 1','124','124\r\n','ƒê√∫ng',6,133,0,'com.minh.nguyen.service.JudgeService','admin','2018-03-21 21:10:30','admin','2018-03-21 21:10:30','com.minh.nguyen.service.JudgeService','0',NULL),(301,'1 2','3','3','ƒê√∫ng',6,154,0,'com.minh.nguyen.service.JudgeService','admin','2018-03-21 21:10:30','admin','2018-03-21 21:10:30','com.minh.nguyen.service.JudgeService','0',NULL),(302,'5 10','15','15','ƒê√∫ng',6,88,0,'com.minh.nguyen.service.JudgeService','admin','2018-03-21 21:14:36','admin','2018-03-21 21:14:36','com.minh.nguyen.service.JudgeService','0',NULL),(303,'123 1','124','124\r\n','ƒê√∫ng',6,109,0,'com.minh.nguyen.service.JudgeService','admin','2018-03-21 21:14:36','admin','2018-03-21 21:14:36','com.minh.nguyen.service.JudgeService','0',NULL),(304,'1 2','3','3','ƒê√∫ng',6,126,0,'com.minh.nguyen.service.JudgeService','admin','2018-03-21 21:14:36','admin','2018-03-21 21:14:36','com.minh.nguyen.service.JudgeService','0',NULL),(305,'5 10','15','15','ƒê√∫ng',6,89,0,'com.minh.nguyen.service.JudgeService','admin','2018-03-21 21:17:08','admin','2018-03-21 21:17:08','com.minh.nguyen.service.JudgeService','0',NULL),(306,'123 1','124','124\r\n','ƒê√∫ng',6,78,0,'com.minh.nguyen.service.JudgeService','admin','2018-03-21 21:17:08','admin','2018-03-21 21:17:08','com.minh.nguyen.service.JudgeService','0',NULL),(307,'1 2','3','3','ƒê√∫ng',6,88,0,'com.minh.nguyen.service.JudgeService','admin','2018-03-21 21:17:09','admin','2018-03-21 21:17:09','com.minh.nguyen.service.JudgeService','0',NULL),(308,'5 10','15','15','ƒê√∫ng',6,157,0,'com.minh.nguyen.service.JudgeService','admin','2018-03-21 21:17:17','admin','2018-03-21 21:17:17','com.minh.nguyen.service.JudgeService','0',NULL),(309,'123 1','124','124\r\n','ƒê√∫ng',6,84,0,'com.minh.nguyen.service.JudgeService','admin','2018-03-21 21:17:17','admin','2018-03-21 21:17:17','com.minh.nguyen.service.JudgeService','0',NULL),(310,'1 2','3','3','ƒê√∫ng',6,74,0,'com.minh.nguyen.service.JudgeService','admin','2018-03-21 21:17:17','admin','2018-03-21 21:17:17','com.minh.nguyen.service.JudgeService','0',NULL),(311,'5 10','15','15','ƒê√∫ng',6,112,0,'com.minh.nguyen.service.JudgeService','admin','2018-03-21 21:18:45','admin','2018-03-21 21:18:45','com.minh.nguyen.service.JudgeService','0',NULL),(312,'5 10','15','15','ƒê√∫ng',6,180,0,'com.minh.nguyen.service.JudgeService','admin','2018-03-21 21:18:45','admin','2018-03-21 21:18:45','com.minh.nguyen.service.JudgeService','0',NULL),(313,'123 1','124','124\r\n','ƒê√∫ng',6,149,0,'com.minh.nguyen.service.JudgeService','admin','2018-03-21 21:18:45','admin','2018-03-21 21:18:45','com.minh.nguyen.service.JudgeService','0',NULL),(314,'123 1','124','124\r\n','ƒê√∫ng',6,154,0,'com.minh.nguyen.service.JudgeService','admin','2018-03-21 21:18:45','admin','2018-03-21 21:18:45','com.minh.nguyen.service.JudgeService','0',NULL),(315,'1 2','3','3','ƒê√∫ng',6,162,0,'com.minh.nguyen.service.JudgeService','admin','2018-03-21 21:18:46','admin','2018-03-21 21:18:46','com.minh.nguyen.service.JudgeService','0',NULL),(316,'1 2','3','3','ƒê√∫ng',6,198,0,'com.minh.nguyen.service.JudgeService','admin','2018-03-21 21:18:46','admin','2018-03-21 21:18:46','com.minh.nguyen.service.JudgeService','0',NULL),(317,'5 10','17','15','Sai k·∫øt qu·∫£ ·ªü d√≤ng th·ª© 1:\r\nOutput: 17\r\nK·∫øt qu·∫£: 15',5,116,0,'com.minh.nguyen.service.JudgeService','admin','2018-03-21 21:19:07','admin','2018-03-21 21:19:07','com.minh.nguyen.service.JudgeService','0',NULL),(318,'123 1','126','124\r\n','Sai k·∫øt qu·∫£ ·ªü d√≤ng th·ª© 1:\r\nOutput: 126\r\nK·∫øt qu·∫£: 124',5,82,0,'com.minh.nguyen.service.JudgeService','admin','2018-03-21 21:19:07','admin','2018-03-21 21:19:07','com.minh.nguyen.service.JudgeService','0',NULL),(319,'1 2','5','3','Sai k·∫øt qu·∫£ ·ªü d√≤ng th·ª© 1:\r\nOutput: 5\r\nK·∫øt qu·∫£: 3',5,132,0,'com.minh.nguyen.service.JudgeService','admin','2018-03-21 21:19:07','admin','2018-03-21 21:19:07','com.minh.nguyen.service.JudgeService','0',NULL),(320,NULL,NULL,NULL,NULL,0,0,0,'com.minh.nguyen.service.JudgeService','user','2018-03-23 13:49:18','user','2018-03-23 13:49:18','com.minh.nguyen.service.JudgeService','0',NULL),(321,NULL,NULL,NULL,NULL,0,0,0,'com.minh.nguyen.service.JudgeService','user','2018-03-23 13:49:51','user','2018-03-23 13:49:51','com.minh.nguyen.service.JudgeService','0',NULL),(322,NULL,NULL,NULL,NULL,0,0,0,'com.minh.nguyen.service.JudgeService','user','2018-03-23 13:50:55','user','2018-03-23 13:50:55','com.minh.nguyen.service.JudgeService','0',NULL),(323,NULL,NULL,NULL,NULL,0,0,0,'com.minh.nguyen.service.JudgeService','user','2018-03-23 13:57:08','user','2018-03-23 13:57:08','com.minh.nguyen.service.JudgeService','0',NULL),(324,'5 10','15','15','ƒê√∫ng',6,156,0,'com.minh.nguyen.service.JudgeService','user','2018-03-23 13:59:52','user','2018-03-23 13:59:52','com.minh.nguyen.service.JudgeService','0',NULL),(325,'123 1','124','124\r\n','ƒê√∫ng',6,166,0,'com.minh.nguyen.service.JudgeService','user','2018-03-23 13:59:52','user','2018-03-23 13:59:52','com.minh.nguyen.service.JudgeService','0',NULL),(326,'1 2','3','3','ƒê√∫ng',6,251,0,'com.minh.nguyen.service.JudgeService','user','2018-03-23 13:59:53','user','2018-03-23 13:59:53','com.minh.nguyen.service.JudgeService','0',NULL),(327,NULL,NULL,NULL,NULL,0,0,0,'com.minh.nguyen.service.JudgeService','user','2018-03-23 14:01:21','user','2018-03-23 14:01:21','com.minh.nguyen.service.JudgeService','0',NULL),(328,NULL,NULL,NULL,NULL,0,0,0,'com.minh.nguyen.service.JudgeService','user','2018-03-23 14:02:13','user','2018-03-23 14:02:13','com.minh.nguyen.service.JudgeService','0',NULL),(329,'1 2','3','3','ƒê√∫ng',6,142,0,'com.minh.nguyen.service.JudgeService','user','2018-03-23 14:18:31','user','2018-03-23 14:18:31','com.minh.nguyen.service.JudgeService','0',NULL),(330,'3 4','7','7','ƒê√∫ng',6,123,0,'com.minh.nguyen.service.JudgeService','user','2018-03-23 14:18:32','user','2018-03-23 14:18:32','com.minh.nguyen.service.JudgeService','0',NULL),(331,'5 4','9','9','ƒê√∫ng',6,94,0,'com.minh.nguyen.service.JudgeService','user','2018-03-23 14:18:32','user','2018-03-23 14:18:32','com.minh.nguyen.service.JudgeService','0',NULL),(332,'1 2','3','3','ƒê√∫ng',6,98,0,'com.minh.nguyen.service.JudgeService','user','2018-03-23 14:18:46','user','2018-03-23 14:18:46','com.minh.nguyen.service.JudgeService','0',NULL),(333,'3 4','7','7','ƒê√∫ng',6,103,0,'com.minh.nguyen.service.JudgeService','user','2018-03-23 14:18:46','user','2018-03-23 14:18:46','com.minh.nguyen.service.JudgeService','0',NULL),(334,'5 4','9','9','ƒê√∫ng',6,162,0,'com.minh.nguyen.service.JudgeService','user','2018-03-23 14:18:46','user','2018-03-23 14:18:46','com.minh.nguyen.service.JudgeService','0',NULL),(335,'5 10','15','15','ƒê√∫ng',6,93,0,'com.minh.nguyen.service.JudgeService','user','2018-03-23 14:19:53','user','2018-03-23 14:19:53','com.minh.nguyen.service.JudgeService','0',NULL),(336,'123 1','124','124\r\n','ƒê√∫ng',6,95,0,'com.minh.nguyen.service.JudgeService','user','2018-03-23 14:19:53','user','2018-03-23 14:19:53','com.minh.nguyen.service.JudgeService','0',NULL),(337,'1 2','3','3','ƒê√∫ng',6,98,0,'com.minh.nguyen.service.JudgeService','user','2018-03-23 14:19:53','user','2018-03-23 14:19:53','com.minh.nguyen.service.JudgeService','0',NULL);
/*!40000 ALTER TABLE `submitdetail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tag`
--

DROP TABLE IF EXISTS `tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tag` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  `createUser` varchar(30) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `updateUser` varchar(45) DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `updateClass` varchar(45) DEFAULT NULL,
  `deleteFlg` varchar(1) DEFAULT NULL,
  `deleteTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tag`
--

LOCK TABLES `tag` WRITE;
/*!40000 ALTER TABLE `tag` DISABLE KEYS */;
INSERT INTO `tag` VALUES (1,'DFS','minh.nt','2017-02-03 00:00:00','minh.nt','2017-02-03 00:00:00','test','0',NULL),(2,'Tree','minh.nt','2017-02-03 00:00:00','minh.nt','2017-02-03 00:00:00','test','0',NULL);
/*!40000 ALTER TABLE `tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_survey`
--

DROP TABLE IF EXISTS `tbl_survey`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_survey` (
  `survey_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `survey_creator_id` int(11) DEFAULT NULL,
  `survey_student_id` text,
  `survey_name` varchar(45) DEFAULT NULL,
  `survey_description` text,
  `survey_created_date` varchar(45) DEFAULT NULL,
  `survey_question_id` text,
  `survey_is_deleted` int(11) DEFAULT '0',
  `createClass` varchar(100) DEFAULT NULL,
  `createUser` varchar(30) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `updateUser` varchar(45) DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `updateClass` varchar(45) DEFAULT NULL,
  `deleteFlg` varchar(1) DEFAULT '0',
  `deleteTime` datetime DEFAULT NULL,
  PRIMARY KEY (`survey_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_survey`
--

LOCK TABLES `tbl_survey` WRITE;
/*!40000 ALTER TABLE `tbl_survey` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_survey` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_survey_question`
--

DROP TABLE IF EXISTS `tbl_survey_question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_survey_question` (
  `question_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `question_survey_id` int(11) DEFAULT NULL,
  `question_content` text,
  `question_detail_id` text,
  `createClass` varchar(100) DEFAULT NULL,
  `createUser` varchar(30) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `updateUser` varchar(45) DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `updateClass` varchar(45) DEFAULT NULL,
  `deleteFlg` varchar(1) DEFAULT '0',
  `deleteTime` datetime DEFAULT NULL,
  PRIMARY KEY (`question_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_survey_question`
--

LOCK TABLES `tbl_survey_question` WRITE;
/*!40000 ALTER TABLE `tbl_survey_question` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_survey_question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ur_auy`
--

DROP TABLE IF EXISTS `ur_auy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ur_auy` (
  `urId` int(11) NOT NULL,
  `auyId` int(11) NOT NULL,
  `createClass` varchar(100) DEFAULT NULL,
  `createUser` varchar(30) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `updateUser` varchar(45) DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `updateClass` varchar(45) DEFAULT NULL,
  `deleteFlg` varchar(1) DEFAULT '0',
  `deleteTime` datetime DEFAULT NULL,
  PRIMARY KEY (`urId`,`auyId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ur_auy`
--

LOCK TABLES `ur_auy` WRITE;
/*!40000 ALTER TABLE `ur_auy` DISABLE KEYS */;
/*!40000 ALTER TABLE `ur_auy` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ur_ce_auy`
--

DROP TABLE IF EXISTS `ur_ce_auy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ur_ce_auy` (
  `urId` int(11) NOT NULL,
  `ceId` int(11) NOT NULL,
  `auyId` int(11) NOT NULL,
  `createClass` varchar(100) DEFAULT NULL,
  `createUser` varchar(30) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `updateUser` varchar(45) DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `updateClass` varchar(45) DEFAULT NULL,
  `deleteFlg` varchar(1) DEFAULT '0',
  `deleteTime` datetime DEFAULT NULL,
  PRIMARY KEY (`urId`,`auyId`,`ceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ur_ce_auy`
--

LOCK TABLES `ur_ce_auy` WRITE;
/*!40000 ALTER TABLE `ur_ce_auy` DISABLE KEYS */;
INSERT INTO `ur_ce_auy` VALUES (1,2,15,'com.minh.nguyen.service.CourseService','admin','2018-03-15 14:38:49','admin','2018-03-15 14:38:49','com.minh.nguyen.service.CourseService','0',NULL),(1,3,15,'com.minh.nguyen.service.CourseService','admin','2018-03-15 21:43:00','admin','2018-03-15 21:43:00','com.minh.nguyen.service.CourseService','0',NULL),(1,2,16,'com.minh.nguyen.service.CourseService','admin','2018-03-15 14:38:49','admin','2018-03-15 14:38:49','com.minh.nguyen.service.CourseService','0',NULL),(1,3,16,'com.minh.nguyen.service.CourseService','admin','2018-03-15 21:43:00','admin','2018-03-15 21:43:00','com.minh.nguyen.service.CourseService','0',NULL),(2,3,14,'com.minh.nguyen.service.CourseService','admin','2018-03-22 14:07:56','admin','2018-03-22 14:07:56','com.minh.nguyen.service.CourseService','0',NULL);
/*!40000 ALTER TABLE `ur_ce_auy` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ur_cn`
--

DROP TABLE IF EXISTS `ur_cn`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ur_cn` (
  `urId` int(11) NOT NULL AUTO_INCREMENT,
  `cnId` int(11) NOT NULL,
  `createClass` varchar(100) DEFAULT NULL,
  `createUser` varchar(30) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `updateUser` varchar(45) DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `updateClass` varchar(45) DEFAULT NULL,
  `deleteFlg` varchar(1) DEFAULT '0',
  `deleteTime` datetime DEFAULT NULL,
  PRIMARY KEY (`urId`,`cnId`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ur_cn`
--

LOCK TABLES `ur_cn` WRITE;
/*!40000 ALTER TABLE `ur_cn` DISABLE KEYS */;
INSERT INTO `ur_cn` VALUES (1,20,NULL,NULL,'2018-03-12 23:08:16',NULL,NULL,NULL,'0',NULL),(1,26,NULL,NULL,'2018-03-13 01:02:33',NULL,NULL,NULL,'0',NULL),(1,27,NULL,NULL,'2018-03-13 01:02:36',NULL,NULL,NULL,'0',NULL),(1,28,NULL,NULL,'2018-03-13 01:02:56',NULL,NULL,NULL,'0',NULL),(1,29,NULL,NULL,'2018-03-13 01:02:57',NULL,NULL,NULL,'0',NULL),(1,30,NULL,NULL,'2018-03-13 19:45:14',NULL,NULL,NULL,'0',NULL),(1,31,NULL,NULL,'2018-03-14 13:05:46',NULL,NULL,NULL,'0',NULL),(1,32,NULL,NULL,'2018-03-14 13:07:43',NULL,NULL,NULL,'0',NULL),(2,19,NULL,NULL,'2018-03-12 23:08:15',NULL,NULL,NULL,'0',NULL),(2,20,NULL,NULL,'2018-03-12 23:08:16',NULL,NULL,NULL,'0',NULL),(2,21,NULL,NULL,'2018-03-12 23:08:17',NULL,NULL,NULL,'0',NULL),(2,22,NULL,NULL,'2018-03-12 23:08:19',NULL,NULL,NULL,'0',NULL),(2,23,NULL,NULL,'2018-03-12 23:08:19',NULL,NULL,NULL,'0',NULL),(2,24,NULL,NULL,'2018-03-12 23:08:21',NULL,NULL,NULL,'0',NULL),(2,25,NULL,NULL,'2018-03-12 23:08:23',NULL,NULL,NULL,'0',NULL),(3,19,NULL,NULL,'2018-03-12 23:08:15',NULL,NULL,NULL,'0',NULL),(3,27,NULL,NULL,'2018-03-13 01:02:36',NULL,NULL,NULL,'0',NULL),(4,21,NULL,NULL,'2018-03-12 23:08:17',NULL,NULL,NULL,'0',NULL),(4,26,NULL,NULL,'2018-03-13 01:02:33',NULL,NULL,NULL,'0',NULL),(5,22,NULL,NULL,'2018-03-12 23:08:19',NULL,NULL,NULL,'0',NULL),(5,28,NULL,NULL,'2018-03-13 01:02:56',NULL,NULL,NULL,'0',NULL),(6,23,NULL,NULL,'2018-03-12 23:08:19',NULL,NULL,NULL,'0',NULL),(6,29,NULL,NULL,'2018-03-13 01:02:57',NULL,NULL,NULL,'0',NULL),(10,31,NULL,NULL,'2018-03-14 13:05:46',NULL,NULL,NULL,'0',NULL),(11,32,NULL,NULL,'2018-03-14 13:07:43',NULL,NULL,NULL,'0',NULL),(13,24,NULL,NULL,'2018-03-12 23:08:21',NULL,NULL,NULL,'0',NULL),(13,30,NULL,NULL,'2018-03-13 19:45:14',NULL,NULL,NULL,'0',NULL),(14,25,NULL,NULL,'2018-03-12 23:08:23',NULL,NULL,NULL,'0',NULL);
/*!40000 ALTER TABLE `ur_cn` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ur_ct_auy`
--

DROP TABLE IF EXISTS `ur_ct_auy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ur_ct_auy` (
  `urId` int(11) NOT NULL,
  `ctId` int(11) NOT NULL,
  `auyId` int(11) NOT NULL,
  `createClass` varchar(100) DEFAULT NULL,
  `createUser` varchar(30) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `updateUser` varchar(45) DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `updateClass` varchar(45) DEFAULT NULL,
  `deleteFlg` varchar(1) DEFAULT '0',
  `deleteTime` datetime DEFAULT NULL,
  PRIMARY KEY (`urId`,`ctId`,`auyId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ur_ct_auy`
--

LOCK TABLES `ur_ct_auy` WRITE;
/*!40000 ALTER TABLE `ur_ct_auy` DISABLE KEYS */;
INSERT INTO `ur_ct_auy` VALUES (1,5,7,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL),(1,5,9,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL),(1,6,7,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL),(1,6,8,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL),(2,5,7,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL),(2,6,9,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL),(3,5,9,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL),(3,6,9,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL),(4,5,9,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL);
/*!40000 ALTER TABLE `ur_ct_auy` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ur_pm_auy`
--

DROP TABLE IF EXISTS `ur_pm_auy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ur_pm_auy` (
  `pmId` int(11) NOT NULL,
  `auyId` int(11) NOT NULL,
  `urId` int(11) NOT NULL,
  `createClass` varchar(100) DEFAULT NULL,
  `createUser` varchar(30) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `updateUser` varchar(45) DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `updateClass` varchar(45) DEFAULT NULL,
  `deleteFlg` varchar(1) DEFAULT '0',
  `deleteTime` datetime DEFAULT NULL,
  PRIMARY KEY (`pmId`,`auyId`,`urId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ur_pm_auy`
--

LOCK TABLES `ur_pm_auy` WRITE;
/*!40000 ALTER TABLE `ur_pm_auy` DISABLE KEYS */;
INSERT INTO `ur_pm_auy` VALUES (10,4,1,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL),(10,5,1,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL),(17,4,1,NULL,NULL,NULL,'admin','2018-02-07 23:16:51','com.minh.nguyen.service.ProblemService','0',NULL),(17,5,1,NULL,NULL,NULL,'admin','2018-02-07 23:16:51','com.minh.nguyen.service.ProblemService','0',NULL),(18,4,1,NULL,NULL,NULL,'admin','2018-02-07 23:19:19','com.minh.nguyen.service.ProblemService','0',NULL),(18,5,1,NULL,NULL,NULL,'admin','2018-02-07 23:19:19','com.minh.nguyen.service.ProblemService','0',NULL),(19,4,1,'com.minh.nguyen.service.ProblemService','admin','2018-02-09 23:32:02','admin','2018-02-09 23:32:02','com.minh.nguyen.service.ProblemService','0',NULL),(19,5,1,'com.minh.nguyen.service.ProblemService','admin','2018-02-09 23:32:02','admin','2018-02-09 23:32:02','com.minh.nguyen.service.ProblemService','0',NULL),(20,4,1,'com.minh.nguyen.service.ProblemService','admin','2018-02-11 13:44:57','admin','2018-02-11 13:44:57','com.minh.nguyen.service.ProblemService','0',NULL),(20,4,2,'com.minh.nguyen.service.ProblemService','admin','2018-02-16 23:31:36','admin','2018-02-16 23:31:36','com.minh.nguyen.service.ProblemService','0',NULL),(20,5,1,'com.minh.nguyen.service.ProblemService','admin','2018-02-11 13:44:57','admin','2018-02-11 13:44:57','com.minh.nguyen.service.ProblemService','0',NULL),(20,5,2,'com.minh.nguyen.service.ProblemService','admin','2018-02-16 23:31:36','admin','2018-02-16 23:31:36','com.minh.nguyen.service.ProblemService','0',NULL),(21,4,1,'com.minh.nguyen.service.ProblemService','admin','2018-02-14 13:27:48','admin','2018-02-14 13:27:48','com.minh.nguyen.service.ProblemService','0',NULL),(21,5,1,'com.minh.nguyen.service.ProblemService','admin','2018-02-14 13:27:48','admin','2018-02-14 13:27:48','com.minh.nguyen.service.ProblemService','0',NULL);
/*!40000 ALTER TABLE `ur_pm_auy` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `handle` varchar(45) NOT NULL,
  `password` varchar(100) DEFAULT NULL,
  `fullname` varchar(45) DEFAULT NULL,
  `phoneNumber` varchar(45) DEFAULT NULL,
  `emailAddress` varchar(45) DEFAULT NULL,
  `dateOfBirth` varchar(45) DEFAULT NULL,
  `avatar` varchar(200) DEFAULT NULL,
  `description` varchar(1000) DEFAULT NULL,
  `reId` int(11) DEFAULT NULL,
  `loginAttempt` int(11) DEFAULT '0',
  `isOnline` int(11) DEFAULT '0',
  `isActived` int(11) DEFAULT '0',
  `isLocked` int(11) DEFAULT '0',
  `lastLogin` datetime DEFAULT NULL,
  `expiredDate` datetime DEFAULT NULL,
  `createClass` varchar(100) DEFAULT NULL,
  `createUser` varchar(30) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `updateUser` varchar(45) DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `updateClass` varchar(45) DEFAULT NULL,
  `deleteFlg` varchar(1) DEFAULT '0',
  `deleteTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`,`handle`),
  UNIQUE KEY `user_id_UNIQUE` (`id`),
  UNIQUE KEY `handle_UNIQUE` (`handle`),
  KEY `handle_btree` (`handle`) USING HASH
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'admin','$2a$10$uZ.FzqENy9bWinvCb66DF.JjnTD/G3wH3GaqSv.8Z7GXv8LrEgEuy','MakiseKurisu',NULL,NULL,NULL,NULL,NULL,1,0,0,0,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL),(2,'user','$2a$10$uZ.FzqENy9bWinvCb66DF.JjnTD/G3wH3GaqSv.8Z7GXv8LrEgEuy','Okabe',NULL,NULL,NULL,NULL,NULL,3,0,0,0,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL),(3,'student','$2a$10$uZ.FzqENy9bWinvCb66DF.JjnTD/G3wH3GaqSv.8Z7GXv8LrEgEuy','Haru',NULL,NULL,NULL,NULL,NULL,3,0,0,0,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL),(4,'lecturer','$2a$10$uZ.FzqENy9bWinvCb66DF.JjnTD/G3wH3GaqSv.8Z7GXv8LrEgEuy','Suzuha',NULL,NULL,NULL,NULL,NULL,2,0,0,0,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL),(5,'supervisor','$2a$10$uZ.FzqENy9bWinvCb66DF.JjnTD/G3wH3GaqSv.8Z7GXv8LrEgEuy','Ferris',NULL,NULL,NULL,NULL,NULL,4,0,0,0,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL),(6,'student1','$2a$10$uZ.FzqENy9bWinvCb66DF.JjnTD/G3wH3GaqSv.8Z7GXv8LrEgEuy','student1',NULL,NULL,NULL,NULL,NULL,3,0,0,0,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL),(7,'student2','$2a$10$uZ.FzqENy9bWinvCb66DF.JjnTD/G3wH3GaqSv.8Z7GXv8LrEgEuy','student2',NULL,NULL,NULL,NULL,NULL,3,0,0,0,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL),(8,'student3','$2a$10$uZ.FzqENy9bWinvCb66DF.JjnTD/G3wH3GaqSv.8Z7GXv8LrEgEuy','student3',NULL,NULL,NULL,NULL,NULL,3,0,0,0,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL),(9,'student4','$2a$10$uZ.FzqENy9bWinvCb66DF.JjnTD/G3wH3GaqSv.8Z7GXv8LrEgEuy','student4',NULL,NULL,NULL,NULL,NULL,3,0,0,0,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL),(10,'student5','$2a$10$uZ.FzqENy9bWinvCb66DF.JjnTD/G3wH3GaqSv.8Z7GXv8LrEgEuy','student5',NULL,NULL,NULL,NULL,NULL,3,0,0,0,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL),(11,'student6','$2a$10$uZ.FzqENy9bWinvCb66DF.JjnTD/G3wH3GaqSv.8Z7GXv8LrEgEuy','student6',NULL,NULL,NULL,NULL,NULL,3,0,0,0,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL),(12,'student7','$2a$10$uZ.FzqENy9bWinvCb66DF.JjnTD/G3wH3GaqSv.8Z7GXv8LrEgEuy','student7',NULL,NULL,NULL,NULL,NULL,3,0,0,0,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL),(13,'student8','$2a$10$uZ.FzqENy9bWinvCb66DF.JjnTD/G3wH3GaqSv.8Z7GXv8LrEgEuy','student8',NULL,NULL,NULL,NULL,NULL,3,0,0,0,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL),(14,'student9','$2a$10$uZ.FzqENy9bWinvCb66DF.JjnTD/G3wH3GaqSv.8Z7GXv8LrEgEuy','student9',NULL,NULL,NULL,NULL,NULL,3,0,0,0,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL),(15,'student10','$2a$10$uZ.FzqENy9bWinvCb66DF.JjnTD/G3wH3GaqSv.8Z7GXv8LrEgEuy','student10',NULL,NULL,NULL,NULL,NULL,3,0,0,0,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-04-02 13:32:03
