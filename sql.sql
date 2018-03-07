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
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL,
  `createClass` varchar(100) DEFAULT NULL,
  `createUser` varchar(30) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `updateUser` varchar(45) DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `updateClass` varchar(45) DEFAULT NULL,
  `deleteFlg` varchar(1) DEFAULT '0',
  `deleteTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`,`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

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
INSERT INTO `announcement` VALUES (2,10,'31eqw',NULL,0,0,0,'com.minh.nguyen.service.ContestService','admin','2018-02-19 12:36:59','admin','2018-02-19 12:36:59','com.minh.nguyen.service.ContestService','0',NULL),(3,0,NULL,'Đây là một thông báo quan hết',0,1,1,'com.minh.nguyen.service.ContestService','admin','2018-02-19 13:51:40','admin','2018-02-19 13:51:40','com.minh.nguyen.service.ContestService','0',NULL),(5,10,'gehe',NULL,1,0,0,'com.minh.nguyen.service.ContestService','user','2018-02-19 15:36:23','admin','2018-03-02 18:34:05','com.minh.nguyen.service.ContestService','0',NULL),(6,0,NULL,'đá\r\nyy\r\neqwe\r\nfdsf',0,1,1,'com.minh.nguyen.service.ContestService','admin','2018-02-19 15:58:22','admin','2018-02-19 15:58:22','com.minh.nguyen.service.ContestService','0',NULL),(7,10,NULL,'đâsd',0,1,1,'com.minh.nguyen.service.ContestService','admin','2018-02-19 16:00:34','admin','2018-02-19 16:00:34','com.minh.nguyen.service.ContestService','0',NULL),(8,21,NULL,'qwe\r\nrrr',0,1,1,'com.minh.nguyen.service.ContestService','admin','2018-02-19 16:08:38','admin','2018-03-02 18:34:00','com.minh.nguyen.service.ContestService','0',NULL),(9,10,'what\r\nis this\r\nquestion','hehe',0,1,0,'com.minh.nguyen.service.ContestService','user','2018-02-19 21:34:36','admin','2018-02-19 21:47:16','com.minh.nguyen.service.ContestService','0',NULL),(10,0,'',NULL,0,0,0,'com.minh.nguyen.service.ContestService','user','2018-02-19 21:47:33','user','2018-02-19 21:47:33','com.minh.nguyen.service.ContestService','0',NULL);
/*!40000 ALTER TABLE `announcement` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `apply`
--

DROP TABLE IF EXISTS `apply`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `apply` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `stId` int(11) NOT NULL,
  `ceId` int(11) NOT NULL,
  `sentDate` datetime DEFAULT NULL,
  `isResolved` int(11) DEFAULT '0',
  `description` varchar(200) DEFAULT NULL,
  `createClass` varchar(100) DEFAULT NULL,
  `createUser` varchar(30) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `updateUser` varchar(45) DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `updateClass` varchar(45) DEFAULT NULL,
  `deleteFlg` varchar(1) DEFAULT '0',
  `deleteTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`,`stId`,`ceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `apply`
--

LOCK TABLES `apply` WRITE;
/*!40000 ALTER TABLE `apply` DISABLE KEYS */;
/*!40000 ALTER TABLE `apply` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `at_qn`
--

DROP TABLE IF EXISTS `at_qn`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `at_qn` (
  `atId` int(11) NOT NULL,
  `qnId` int(11) NOT NULL,
  `createClass` varchar(100) DEFAULT NULL,
  `createUser` varchar(30) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `updateUser` varchar(45) DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `updateClass` varchar(45) DEFAULT NULL,
  `deleteFlg` varchar(1) DEFAULT '0',
  `deleteTime` datetime DEFAULT NULL,
  PRIMARY KEY (`atId`,`qnId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `at_qn`
--

LOCK TABLES `at_qn` WRITE;
/*!40000 ALTER TABLE `at_qn` DISABLE KEYS */;
/*!40000 ALTER TABLE `at_qn` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `authority`
--

LOCK TABLES `authority` WRITE;
/*!40000 ALTER TABLE `authority` DISABLE KEYS */;
INSERT INTO `authority` VALUES (1,'CAN_CREATE_USER',NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL),(2,'CAN_EDIT_USER',NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL),(3,'CAN_CREATE_PROBLEM',NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL),(4,'CAN_EDIT_PROBLEM',NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL),(5,'CAN_VIEW_PROBLEM',NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL),(6,'CAN_CREATE_CONTEST',NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL),(7,'CAN_EDIT_CONTEST',NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL),(8,'CAN_VIEW_CONTEST',NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL),(9,'CAN_PARTICIPATE_CONTEST',NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL),(10,'CAN_VIEW_ALL_PROBLEM',NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL),(11,'CAN_VIEW_ALL_CONTEST',NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL),(12,'CAN_SUBMIT_PROBLEM',NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL),(13,'CAN_VIEW_ALL_SUBMISSION',NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL);
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
-- Table structure for table `ce_pm`
--

DROP TABLE IF EXISTS `ce_pm`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ce_pm` (
  `ceId` int(11) NOT NULL,
  `pmId` int(11) NOT NULL,
  `solveCnt` int(11) DEFAULT '0',
  `totalSubmission` int(11) DEFAULT '0',
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
  `name` varchar(45) NOT NULL,
  `intake` int(11) NOT NULL,
  `major` varchar(45) NOT NULL,
  `studentCnt` int(11) DEFAULT NULL,
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
  `isPublished` int(11) DEFAULT NULL,
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
INSERT INTO `contest` VALUES (5,'offical sandking hehe','2018-02-25 23:08:10',321,'123',NULL,1,'22','333',3,2,1,1,1,1,NULL,'admin',NULL,NULL,NULL,NULL,NULL,NULL),(6,'Lập trình hướng đối tượng','2018-02-14 10:00:00',1400,'Bài kiểm tra số 2 - Danh sách móc nối',0,1,'Qua bài kiểm tra số 1','Một tràng pháo tay thật to',3,2,0,1,2,1,'class com.minh.nguyen.service.BaseService','admin','2018-02-02 22:05:26','admin','2018-02-16 20:24:27','com.minh.nguyen.service.ContestService','0',NULL);
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `conversation`
--

LOCK TABLES `conversation` WRITE;
/*!40000 ALTER TABLE `conversation` DISABLE KEYS */;
INSERT INTO `conversation` VALUES (1,'public',NULL,'admin',NULL,'admin',NULL,NULL,'0',NULL);
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
  `createClass` varchar(100) DEFAULT NULL,
  `createUser` varchar(30) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `updateUser` varchar(45) DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `updateClass` varchar(45) DEFAULT NULL,
  `deleteFlg` varchar(1) DEFAULT '0',
  `deleteTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course`
--

LOCK TABLES `course` WRITE;
/*!40000 ALTER TABLE `course` DISABLE KEYS */;
INSERT INTO `course` VALUES (1,'contest#1','description#1','requirement#1',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cs_st`
--

DROP TABLE IF EXISTS `cs_st`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cs_st` (
  `csId` int(11) NOT NULL,
  `stId` int(11) NOT NULL,
  `createClass` varchar(100) DEFAULT NULL,
  `createUser` varchar(30) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `updateUser` varchar(45) DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `updateClass` varchar(45) DEFAULT NULL,
  `deleteFlg` varchar(1) DEFAULT '0',
  `deleteTime` datetime DEFAULT NULL,
  PRIMARY KEY (`csId`,`stId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cs_st`
--

LOCK TABLES `cs_st` WRITE;
/*!40000 ALTER TABLE `cs_st` DISABLE KEYS */;
/*!40000 ALTER TABLE `cs_st` ENABLE KEYS */;
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
INSERT INTO `ct_sn` VALUES (6,114,'com.minh.nguyen.service.ContestService','admin','2018-02-14 13:55:56','admin','2018-02-14 13:55:56','com.minh.nguyen.service.ContestService','0',NULL),(6,115,'com.minh.nguyen.service.ContestService','admin','2018-02-14 13:56:16','admin','2018-02-14 13:56:16','com.minh.nguyen.service.ContestService','0',NULL),(6,116,'com.minh.nguyen.service.ContestService','user','2018-02-14 14:14:57','user','2018-02-14 14:14:57','com.minh.nguyen.service.ContestService','0',NULL),(6,117,'com.minh.nguyen.service.ContestService','user','2018-02-14 14:15:12','user','2018-02-14 14:15:12','com.minh.nguyen.service.ContestService','0',NULL),(6,118,'com.minh.nguyen.service.ContestService','user','2018-02-14 15:46:32','user','2018-02-14 15:46:32','com.minh.nguyen.service.ContestService','0',NULL);
/*!40000 ALTER TABLE `ct_sn` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `groupchat`
--

DROP TABLE IF EXISTS `groupchat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `groupchat` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `createUser` varchar(30) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `updateUser` varchar(45) DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `updateClass` varchar(45) DEFAULT NULL,
  `deleteFlg` varchar(1) DEFAULT NULL,
  `deleteTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `groupchat`
--

LOCK TABLES `groupchat` WRITE;
/*!40000 ALTER TABLE `groupchat` DISABLE KEYS */;
/*!40000 ALTER TABLE `groupchat` ENABLE KEYS */;
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
INSERT INTO `input` VALUES (13,'323','44234234',1,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-20 15:11:01','minh.nt','2018-01-20 15:11:01','class com.minh.nguyen.service.BaseService','0',NULL),(17,'3123123','4444',1,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-20 15:13:33','minh.nt','2018-01-20 15:13:33','class com.minh.nguyen.service.BaseService','0',NULL),(18,'1 2','1\r\n2\r\n3',1,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-20 15:22:26','minh.nt','2018-01-29 23:03:08','class com.minh.nguyen.service.BaseService','1',NULL),(19,'5 10','15',1,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-20 15:22:29','admin','2018-02-14 13:43:30','com.minh.nguyen.service.ProblemService','0',NULL),(20,'123456789123456789123456789123456789123456789123456789123456789123456789123456789123456789123456789123456789','4444',0,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-20 15:37:52','minh.nt','2018-01-20 15:37:52','class com.minh.nguyen.service.BaseService','1',NULL),(21,'hehe','1234',0,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-20 16:08:05','minh.nt','2018-01-20 16:08:05','class com.minh.nguyen.service.BaseService','1',NULL),(22,'123 1','124\r\n',0,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-21 12:40:50','admin','2018-02-14 13:43:36','com.minh.nguyen.service.ProblemService','0',NULL),(23,'1 2','3',0,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-29 23:25:32','minh.nt','2018-01-29 23:25:32','class com.minh.nguyen.service.BaseService','1',NULL),(24,'123 333','456',0,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-29 23:25:55','minh.nt','2018-01-29 23:25:55','class com.minh.nguyen.service.BaseService','1',NULL),(25,'33 22','55',0,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-29 23:27:24','minh.nt','2018-01-29 23:27:24','class com.minh.nguyen.service.BaseService','1',NULL),(26,'3 5','8',0,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-13 10:23:48','minh.nt','2018-01-29 23:03:08','class com.minh.nguyen.service.BaseService','1',NULL),(27,'1 2','3',1,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-13 10:23:48','minh.nt','2018-02-04 14:21:54','class com.minh.nguyen.service.BaseService','0',NULL),(28,'1 2','3',1,'com.minh.nguyen.service.ProblemService','admin','2018-02-11 13:44:57','admin','2018-02-11 13:46:58','com.minh.nguyen.service.ProblemService','0',NULL),(29,'3 4','7',0,'com.minh.nguyen.service.ProblemService','admin','2018-02-11 13:44:57','admin','2018-02-11 13:46:58','com.minh.nguyen.service.ProblemService','0',NULL),(30,'5 4','9',0,'com.minh.nguyen.service.ProblemService','admin','2018-02-11 13:44:57','admin','2018-02-11 14:30:01','com.minh.nguyen.service.ProblemService','0',NULL),(31,'1 2','3',1,'com.minh.nguyen.service.ProblemService','admin','2018-02-14 13:27:48','admin','2018-02-14 13:30:03','com.minh.nguyen.service.ProblemService','0',NULL);
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
-- Table structure for table `lecturer`
--

DROP TABLE IF EXISTS `lecturer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lecturer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL,
  `degree` varchar(45) DEFAULT NULL,
  `createClass` varchar(100) DEFAULT NULL,
  `createUser` varchar(30) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `updateUser` varchar(45) DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `updateClass` varchar(45) DEFAULT NULL,
  `deleteFlg` varchar(1) DEFAULT '0',
  `deleteTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`,`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lecturer`
--

LOCK TABLES `lecturer` WRITE;
/*!40000 ALTER TABLE `lecturer` DISABLE KEYS */;
/*!40000 ALTER TABLE `lecturer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `message`
--

DROP TABLE IF EXISTS `message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `message` (
  `urId` int(11) DEFAULT NULL,
  `cnId` int(11) DEFAULT NULL,
  `content` varchar(200) DEFAULT NULL,
  `createClass` varchar(100) DEFAULT NULL,
  `createUser` varchar(30) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `updateUser` varchar(45) DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `updateClass` varchar(45) DEFAULT NULL,
  `deleteFlg` varchar(1) DEFAULT '0',
  `deleteTime` datetime DEFAULT NULL,
  KEY `conversation_message_id` (`cnId`) USING HASH
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message`
--

LOCK TABLES `message` WRITE;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
INSERT INTO `message` VALUES (2,1,'HEHE EZ VKL',NULL,NULL,'2018-03-07 21:37:55',NULL,NULL,NULL,NULL,NULL),(1,1,':))',NULL,NULL,'2018-03-07 21:38:10',NULL,NULL,NULL,NULL,NULL),(1,1,'NHUNG MA SAO LAI THE',NULL,NULL,'2018-03-07 21:38:20',NULL,NULL,NULL,NULL,NULL),(2,1,'the la sao',NULL,NULL,'2018-03-07 21:39:23',NULL,NULL,NULL,NULL,NULL),(2,1,'toi cha chieu gi ca',NULL,NULL,'2018-03-07 21:39:37',NULL,NULL,NULL,NULL,NULL),(2,1,'roi` ok',NULL,NULL,'2018-03-07 21:47:41',NULL,NULL,NULL,'0',NULL),(1,1,'danh vay thoi chu biet sao',NULL,NULL,'2018-03-07 21:47:51',NULL,NULL,NULL,'0',NULL),(1,1,'chiu nhe',NULL,NULL,'2018-03-07 21:56:10',NULL,NULL,NULL,'0',NULL),(2,1,'t cung cha biet fai lam the nao nua',NULL,NULL,'2018-03-07 21:58:20',NULL,NULL,NULL,'0',NULL),(1,1,'chiu',NULL,NULL,'2018-03-07 21:58:41',NULL,NULL,NULL,'0',NULL),(1,1,'the thi t moi bao deo duoc',NULL,NULL,'2018-03-07 21:58:46',NULL,NULL,NULL,'0',NULL),(1,1,'kho lam ong a',NULL,NULL,'2018-03-07 22:00:27',NULL,NULL,NULL,'0',NULL),(2,1,'cha biet lam the nao nua',NULL,NULL,'2018-03-07 22:00:33',NULL,NULL,NULL,'0',NULL),(2,1,'khong biet gi het nha',NULL,NULL,'2018-03-07 22:01:19',NULL,NULL,NULL,'0',NULL),(1,1,'h` sao',NULL,NULL,'2018-03-07 22:02:25',NULL,NULL,NULL,'0',NULL),(2,1,'chiu',NULL,NULL,'2018-03-07 22:02:29',NULL,NULL,NULL,'0',NULL),(2,1,'trong truong hop nhu vay tghi cung chi con mot cach duy nhatr la deo lam gi ca ',NULL,NULL,'2018-03-07 22:03:16',NULL,NULL,NULL,'0',NULL),(2,1,'qwe',NULL,NULL,'2018-03-07 22:16:57',NULL,NULL,NULL,'0',NULL),(2,1,'123',NULL,NULL,'2018-03-07 22:17:20',NULL,NULL,NULL,'0',NULL),(1,1,'deo the tin dc',NULL,NULL,'2018-03-07 22:17:57',NULL,NULL,NULL,'0',NULL);
/*!40000 ALTER TABLE `message` ENABLE KEYS */;
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
INSERT INTO `problem` VALUES (10,'sum','Tính tổng 2 số','                                                                                                                                                                                Con số 198 có gợi cho bạn điều gì không? Khi học lịch sử Việt \r\nNam, Vinh biết rằng ngày 19-8-1945 là ngày <font color=\"#00FF00\">Tổng khởi nghĩa, \r\nngày nhân dân cả nước ta nhất tề đứng lên làm cuộc Cách mạng \r\nTháng Tám vĩ đại. Hiện nay, 198 được đặt tên cho nhiều bệnh \r\nviện, công viên, đường phố trong cả nước. Con số này đã gợi ý \r\ncho Vinh khảo sát dãy số SEQ98 sau đây: Dãy số nguyên không âm a<sub>1</sub>, a<sub>2</sub>, ..., a<sub>n</sub> được gọi là dãy SEQ198 nếu không tồn tại hai chỉ số i và j (1 ≤ i,j ≤ n) mà a<sub>i</sub>-a<sub>j</sub> hoặc là bằng 1 hoặc là bằng 8 hoặc là bằng 9.</font><br><div><br></div><div><b><u>Ví dụ</u></b>:</div><div><ul><li>Dãy số nguyên 1, 3, 5, 7 là dãy SEQ123</li><li>Dãy\r\n số nguyên 7, 3, 5, 1, 9, 21 không phải là dãy SEQ198 bởi vì có \r\nhai phần tử 1 và 9 có hiệu 9 - 1 = 8. Tuy nhiên, sau khi xóa bớt \r\nphần tử 1, ta thu được dãy 7, 3, 5, 9, 21 là một dãy SEQ198.</li><li>Vinh quan tâm tới bài toán sau đây: Cho dãy số nguyên không âm b<sub>1</sub>, b<sub>2</sub>, ..., b<sub>m</sub>, hãy tìm cách loại bỏ một số ít nhất phần tử của dãy để được dãy còn lại là SEQ198.</li></ul></div>\r\n                                        \r\n                                        \r\n                                        \r\n                                        ','                                                                                                                                                                                <div><ul><li>Dòng đầu chứa số nguyên dương m;</li><li>Dòng thứ hai chứa m số nguyên không âm b<sub>1</sub>, b<sub>2</sub>, ..., b<sub>m</sub> (b<sub>i</sub> ≤ 109).</li></ul></div>\r\n                                        \r\n                                        \r\n                                        \r\n                                        ','                                                                                                                                                                                Ghi ra số nguyên k là số phần tử bị loại bỏ. Ghi số 0 nếu dãy đã cho là SEQ198.\r\n                                        \r\n                                        \r\n                                        \r\n                                        ','                                                                                                                                    Giải thích: với bất cứ các làm nào bạn đều nhận được chuỗi&nbsp;&nbsp;<code style=\"padding: 0px; font-family: Menlo, Monaco, \">xxxxxxxxxx</code><span style=\"background-color: rgb(255, 255, 255);\">.</span>\r\n                                        \r\n                                        \r\n                                        ',2,'#include <bits/stdc++.h>\r\nusing namespace std;\r\nint main(){\r\n    cout << \"Hello world!\";\r\n    int a,b;\r\n    cin >> a >> b;\r\n    cout << a + b;\r\n}\r\n                            ',1234,100,1,1,2,0,NULL,4,'class com.minh.nguyen.service.BaseService','admin','2018-01-13 10:23:48','admin','2018-02-24 21:41:24','com.minh.nguyen.service.ProblemService','0',NULL),(15,'tiger','Con hổ',NULL,NULL,NULL,NULL,NULL,NULL,2000,64,0,0,2,0,NULL,0,'class com.minh.nguyen.service.BaseService','admin','2018-02-03 22:20:31','minh.nt','2018-02-03 22:20:31','class com.minh.nguyen.service.BaseService','0',NULL),(17,'friends','Barney và bạn',NULL,NULL,NULL,NULL,NULL,NULL,2000,64,0,NULL,5,0,NULL,0,'com.minh.nguyen.service.ProblemService','admin','2018-02-07 23:16:51','admin','2018-02-07 23:16:51','com.minh.nguyen.service.ProblemService','0',NULL),(18,'sugar','Bán đường',NULL,NULL,NULL,NULL,NULL,NULL,2000,64,0,NULL,3,0,NULL,0,'com.minh.nguyen.service.ProblemService','admin','2018-02-07 23:19:19','admin','2018-02-07 23:25:05','com.minh.nguyen.service.ProblemService','0',NULL),(19,'newyear','Tinh năm',NULL,NULL,NULL,NULL,2,'ádasdádasd',2000,64,0,NULL,1,0,NULL,0,'com.minh.nguyen.service.ProblemService','admin','2018-02-09 23:32:02','admin','2018-02-09 23:34:23','com.minh.nguyen.service.ProblemService','0',NULL),(20,'phone','Rơi điện thoại','                                            <p class=\"MsoNormal\">After that the code will select a random problem for you\r\nfrom my problems database based on your previously solved problems, your skills\r\nand your weaknesses. But while I was coding for implementing this great idea, I\r\nfound that, all of my problems are scattered in <b>2</b> computers. So, I have\r\nto merge them before running my code.</p>\r\n\r\n<p class=\"MsoNormal\">Now you are given the number of problems in each of the\r\ncomputers, you have to find the total number of problems. You can safely assume\r\nthat no problem is stored in multiple computers. So, all the problems are\r\ndistinct.</p>                                            \r\n                                        \r\n                                        ','                                            <p class=\"MsoNormal\">Input starts with an integer <b>T (</b><b>≤ 125)</b>,\r\ndenoting the number of test cases.</p>\r\n\r\n<p class=\"MsoNormal\">Each case starts with a line containing two integers <b>a</b>\r\nand <b>b</b>. <b>a</b> denotes the number of problems in the first computer and\r\n<b>b</b> denotes the number of problems in the second computer. You can safely\r\nassume that <b>a</b> and <b>b</b> will be non-negative and not greater than <b>10</b>.</p>                                            \r\n                                        \r\n                                        ','                                            For each case, print the case number and the total number of\r\nproblems. See the samples for exact formatting.                                            \r\n                                        \r\n                                        ','                                                                                        \r\n                                        \r\n                                        ',2,'#include<bits/stdc++.h>\r\nusing namespace std;\r\nint main(){\r\n    int a,b;\r\n    cin >> a >> b;\r\n    cout << a + b;\r\n}',5123,64,1,1,2,3,'2018-02-14 15:14:57',9,'com.minh.nguyen.service.ProblemService','admin','2018-02-11 13:44:57','admin','2018-02-20 16:50:37','com.minh.nguyen.service.ProblemService','0',NULL),(21,'gift','Tặng quà','                                                                                                                                                                                                                                                                        asd<br>\r\n                                        \r\n                                        \r\n                                        \r\n                                        \r\n                                        ','                                                                                                                                                                                                                                                                        eeasd<br>\r\n                                        \r\n                                        \r\n                                        \r\n                                        ','                                                                                                                                                                                                                                                                        ww                                            \r\n                                        \r\n                                        \r\n                                        \r\n                                        \r\n                                        \r\n                                        \r\n                                        ','<br>',NULL,NULL,2000,64,0,0,1,0,NULL,0,'com.minh.nguyen.service.ProblemService','admin','2018-02-14 13:27:48','admin','2018-02-14 13:33:02','com.minh.nguyen.service.ProblemService','0',NULL);
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
INSERT INTO `re_auy` VALUES (1,1,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL),(1,2,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL),(1,3,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL),(1,6,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL),(1,10,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL),(1,11,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL),(1,13,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL),(2,1,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL),(2,2,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL),(2,3,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL),(2,6,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL),(2,10,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL),(2,11,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL),(2,13,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL),(4,10,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL),(4,11,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL),(4,13,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL);
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
INSERT INTO `role` VALUES (1,'ADMIN','Quản trị viên',NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL),(2,'LECTURER','Giảng viên',NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL),(3,'STUDENT','Sinh viên',NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL),(4,'SUPERVISOR','Lãnh đạo',NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL);
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
INSERT INTO `sn_sdl` VALUES (65,68,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:24:48','minh.nt','2018-01-31 23:24:48','class com.minh.nguyen.service.BaseService','0',NULL),(65,69,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:24:52','minh.nt','2018-01-31 23:24:52','class com.minh.nguyen.service.BaseService','0',NULL),(65,70,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:24:56','minh.nt','2018-01-31 23:24:56','class com.minh.nguyen.service.BaseService','0',NULL),(66,71,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:26:03','minh.nt','2018-01-31 23:26:03','class com.minh.nguyen.service.BaseService','0',NULL),(66,72,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:26:04','minh.nt','2018-01-31 23:26:04','class com.minh.nguyen.service.BaseService','0',NULL),(66,73,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:26:04','minh.nt','2018-01-31 23:26:04','class com.minh.nguyen.service.BaseService','0',NULL),(67,74,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:28:01','minh.nt','2018-01-31 23:28:01','class com.minh.nguyen.service.BaseService','0',NULL),(67,75,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:28:04','minh.nt','2018-01-31 23:28:04','class com.minh.nguyen.service.BaseService','0',NULL),(67,76,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:28:07','minh.nt','2018-01-31 23:28:07','class com.minh.nguyen.service.BaseService','0',NULL),(68,77,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:29:34','minh.nt','2018-01-31 23:29:34','class com.minh.nguyen.service.BaseService','0',NULL),(68,78,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:29:38','minh.nt','2018-01-31 23:29:38','class com.minh.nguyen.service.BaseService','0',NULL),(68,79,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:29:42','minh.nt','2018-01-31 23:29:42','class com.minh.nguyen.service.BaseService','0',NULL),(69,80,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:31:58','minh.nt','2018-01-31 23:31:58','class com.minh.nguyen.service.BaseService','0',NULL),(69,81,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:32:02','minh.nt','2018-01-31 23:32:02','class com.minh.nguyen.service.BaseService','0',NULL),(69,82,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:32:05','minh.nt','2018-01-31 23:32:05','class com.minh.nguyen.service.BaseService','0',NULL),(70,83,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:33:35','minh.nt','2018-01-31 23:33:35','class com.minh.nguyen.service.BaseService','0',NULL),(70,84,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:33:35','minh.nt','2018-01-31 23:33:35','class com.minh.nguyen.service.BaseService','0',NULL),(70,85,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:33:35','minh.nt','2018-01-31 23:33:35','class com.minh.nguyen.service.BaseService','0',NULL),(71,86,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:33:43','minh.nt','2018-01-31 23:33:43','class com.minh.nguyen.service.BaseService','0',NULL),(71,87,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:33:43','minh.nt','2018-01-31 23:33:43','class com.minh.nguyen.service.BaseService','0',NULL),(71,88,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:33:43','minh.nt','2018-01-31 23:33:43','class com.minh.nguyen.service.BaseService','0',NULL),(72,89,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:34:54','minh.nt','2018-01-31 23:34:54','class com.minh.nguyen.service.BaseService','0',NULL),(72,90,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:34:54','minh.nt','2018-01-31 23:34:54','class com.minh.nguyen.service.BaseService','0',NULL),(72,91,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:34:54','minh.nt','2018-01-31 23:34:54','class com.minh.nguyen.service.BaseService','0',NULL),(73,92,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:34:57','minh.nt','2018-01-31 23:34:57','class com.minh.nguyen.service.BaseService','0',NULL),(73,93,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:34:57','minh.nt','2018-01-31 23:34:57','class com.minh.nguyen.service.BaseService','0',NULL),(73,94,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:34:58','minh.nt','2018-01-31 23:34:58','class com.minh.nguyen.service.BaseService','0',NULL),(74,95,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:35:09','minh.nt','2018-01-31 23:35:09','class com.minh.nguyen.service.BaseService','0',NULL),(74,96,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:35:10','minh.nt','2018-01-31 23:35:10','class com.minh.nguyen.service.BaseService','0',NULL),(74,97,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:35:10','minh.nt','2018-01-31 23:35:10','class com.minh.nguyen.service.BaseService','0',NULL),(75,98,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:35:16','minh.nt','2018-01-31 23:35:16','class com.minh.nguyen.service.BaseService','0',NULL),(75,99,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:35:16','minh.nt','2018-01-31 23:35:16','class com.minh.nguyen.service.BaseService','0',NULL),(75,100,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:35:16','minh.nt','2018-01-31 23:35:16','class com.minh.nguyen.service.BaseService','0',NULL),(76,101,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:35:50','minh.nt','2018-01-31 23:35:50','class com.minh.nguyen.service.BaseService','0',NULL),(76,105,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:35:54','minh.nt','2018-01-31 23:35:54','class com.minh.nguyen.service.BaseService','0',NULL),(76,106,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:35:56','minh.nt','2018-01-31 23:35:56','class com.minh.nguyen.service.BaseService','0',NULL),(77,102,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:35:51','minh.nt','2018-01-31 23:35:51','class com.minh.nguyen.service.BaseService','0',NULL),(77,103,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:35:51','minh.nt','2018-01-31 23:35:51','class com.minh.nguyen.service.BaseService','0',NULL),(77,104,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:35:51','minh.nt','2018-01-31 23:35:51','class com.minh.nguyen.service.BaseService','0',NULL),(78,107,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:35:58','minh.nt','2018-01-31 23:35:58','class com.minh.nguyen.service.BaseService','0',NULL),(78,108,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:35:58','minh.nt','2018-01-31 23:35:58','class com.minh.nguyen.service.BaseService','0',NULL),(78,109,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:35:59','minh.nt','2018-01-31 23:35:59','class com.minh.nguyen.service.BaseService','0',NULL),(87,110,'com.minh.nguyen.service.JudgeService','admin','2018-02-11 14:23:54','admin','2018-02-11 14:23:54','com.minh.nguyen.service.JudgeService','0',NULL),(87,111,'com.minh.nguyen.service.JudgeService','admin','2018-02-11 14:23:54','admin','2018-02-11 14:23:54','com.minh.nguyen.service.JudgeService','0',NULL),(87,112,'com.minh.nguyen.service.JudgeService','admin','2018-02-11 14:23:54','admin','2018-02-11 14:23:54','com.minh.nguyen.service.JudgeService','0',NULL),(88,113,'com.minh.nguyen.service.JudgeService','admin','2018-02-11 14:29:14','admin','2018-02-11 14:29:14','com.minh.nguyen.service.JudgeService','0',NULL),(88,114,'com.minh.nguyen.service.JudgeService','admin','2018-02-11 14:29:15','admin','2018-02-11 14:29:15','com.minh.nguyen.service.JudgeService','0',NULL),(88,115,'com.minh.nguyen.service.JudgeService','admin','2018-02-11 14:29:16','admin','2018-02-11 14:29:16','com.minh.nguyen.service.JudgeService','0',NULL),(89,116,'com.minh.nguyen.service.JudgeService','admin','2018-02-11 14:30:24','admin','2018-02-11 14:30:24','com.minh.nguyen.service.JudgeService','0',NULL),(89,117,'com.minh.nguyen.service.JudgeService','admin','2018-02-11 14:30:24','admin','2018-02-11 14:30:24','com.minh.nguyen.service.JudgeService','0',NULL),(89,118,'com.minh.nguyen.service.JudgeService','admin','2018-02-11 14:30:24','admin','2018-02-11 14:30:24','com.minh.nguyen.service.JudgeService','0',NULL),(90,119,'com.minh.nguyen.service.JudgeService','admin','2018-02-11 14:35:50','admin','2018-02-11 14:35:50','com.minh.nguyen.service.JudgeService','0',NULL),(90,120,'com.minh.nguyen.service.JudgeService','admin','2018-02-11 14:35:50','admin','2018-02-11 14:35:50','com.minh.nguyen.service.JudgeService','0',NULL),(90,121,'com.minh.nguyen.service.JudgeService','admin','2018-02-11 14:35:51','admin','2018-02-11 14:35:51','com.minh.nguyen.service.JudgeService','0',NULL),(92,122,'com.minh.nguyen.service.JudgeService','admin','2018-02-11 15:13:59','admin','2018-02-11 15:13:59','com.minh.nguyen.service.JudgeService','0',NULL),(92,123,'com.minh.nguyen.service.JudgeService','admin','2018-02-11 15:13:59','admin','2018-02-11 15:13:59','com.minh.nguyen.service.JudgeService','0',NULL),(92,124,'com.minh.nguyen.service.JudgeService','admin','2018-02-11 15:13:59','admin','2018-02-11 15:13:59','com.minh.nguyen.service.JudgeService','0',NULL),(93,125,'com.minh.nguyen.service.JudgeService','admin','2018-02-11 15:36:50','admin','2018-02-11 15:36:50','com.minh.nguyen.service.JudgeService','0',NULL),(93,126,'com.minh.nguyen.service.JudgeService','admin','2018-02-11 15:36:50','admin','2018-02-11 15:36:50','com.minh.nguyen.service.JudgeService','0',NULL),(93,127,'com.minh.nguyen.service.JudgeService','admin','2018-02-11 15:36:50','admin','2018-02-11 15:36:50','com.minh.nguyen.service.JudgeService','0',NULL),(94,128,'com.minh.nguyen.service.JudgeService','admin','2018-02-11 15:48:55','admin','2018-02-11 15:48:55','com.minh.nguyen.service.JudgeService','0',NULL),(94,129,'com.minh.nguyen.service.JudgeService','admin','2018-02-11 15:48:55','admin','2018-02-11 15:48:55','com.minh.nguyen.service.JudgeService','0',NULL),(94,130,'com.minh.nguyen.service.JudgeService','admin','2018-02-11 15:48:55','admin','2018-02-11 15:48:55','com.minh.nguyen.service.JudgeService','0',NULL),(95,131,'com.minh.nguyen.service.JudgeService','user','2018-02-11 20:17:16','user','2018-02-11 20:17:16','com.minh.nguyen.service.JudgeService','0',NULL),(96,132,'com.minh.nguyen.service.JudgeService','user','2018-02-11 20:18:08','user','2018-02-11 20:18:08','com.minh.nguyen.service.JudgeService','0',NULL),(96,133,'com.minh.nguyen.service.JudgeService','user','2018-02-11 20:18:08','user','2018-02-11 20:18:08','com.minh.nguyen.service.JudgeService','0',NULL),(96,134,'com.minh.nguyen.service.JudgeService','user','2018-02-11 20:18:08','user','2018-02-11 20:18:08','com.minh.nguyen.service.JudgeService','0',NULL),(97,135,'com.minh.nguyen.service.JudgeService','user','2018-02-11 20:52:01','user','2018-02-11 20:52:01','com.minh.nguyen.service.JudgeService','0',NULL),(97,136,'com.minh.nguyen.service.JudgeService','user','2018-02-11 20:52:01','user','2018-02-11 20:52:01','com.minh.nguyen.service.JudgeService','0',NULL),(97,137,'com.minh.nguyen.service.JudgeService','user','2018-02-11 20:52:02','user','2018-02-11 20:52:02','com.minh.nguyen.service.JudgeService','0',NULL),(98,138,'com.minh.nguyen.service.JudgeService','user','2018-02-11 20:53:07','user','2018-02-11 20:53:07','com.minh.nguyen.service.JudgeService','0',NULL),(98,139,'com.minh.nguyen.service.JudgeService','user','2018-02-11 20:53:07','user','2018-02-11 20:53:07','com.minh.nguyen.service.JudgeService','0',NULL),(98,140,'com.minh.nguyen.service.JudgeService','user','2018-02-11 20:53:07','user','2018-02-11 20:53:07','com.minh.nguyen.service.JudgeService','0',NULL),(99,141,'com.minh.nguyen.service.JudgeService','user','2018-02-11 20:53:50','user','2018-02-11 20:53:50','com.minh.nguyen.service.JudgeService','0',NULL),(99,142,'com.minh.nguyen.service.JudgeService','user','2018-02-11 20:53:51','user','2018-02-11 20:53:51','com.minh.nguyen.service.JudgeService','0',NULL),(99,143,'com.minh.nguyen.service.JudgeService','user','2018-02-11 20:53:51','user','2018-02-11 20:53:51','com.minh.nguyen.service.JudgeService','0',NULL),(100,144,'com.minh.nguyen.service.JudgeService','user','2018-02-12 20:04:44','user','2018-02-12 20:04:44','com.minh.nguyen.service.JudgeService','0',NULL),(101,145,'com.minh.nguyen.service.JudgeService','user','2018-02-12 20:05:12','user','2018-02-12 20:05:12','com.minh.nguyen.service.JudgeService','0',NULL),(101,146,'com.minh.nguyen.service.JudgeService','user','2018-02-12 20:05:12','user','2018-02-12 20:05:12','com.minh.nguyen.service.JudgeService','0',NULL),(101,147,'com.minh.nguyen.service.JudgeService','user','2018-02-12 20:05:12','user','2018-02-12 20:05:12','com.minh.nguyen.service.JudgeService','0',NULL),(102,148,'com.minh.nguyen.service.JudgeService','user','2018-02-14 11:21:26','user','2018-02-14 11:21:26','com.minh.nguyen.service.JudgeService','0',NULL),(103,149,'com.minh.nguyen.service.JudgeService','user','2018-02-14 11:21:56','user','2018-02-14 11:21:56','com.minh.nguyen.service.JudgeService','0',NULL),(103,150,'com.minh.nguyen.service.JudgeService','user','2018-02-14 11:21:56','user','2018-02-14 11:21:56','com.minh.nguyen.service.JudgeService','0',NULL),(103,151,'com.minh.nguyen.service.JudgeService','user','2018-02-14 11:21:56','user','2018-02-14 11:21:56','com.minh.nguyen.service.JudgeService','0',NULL),(104,152,'com.minh.nguyen.service.JudgeService','user','2018-02-14 12:49:01','user','2018-02-14 12:49:01','com.minh.nguyen.service.JudgeService','0',NULL),(104,153,'com.minh.nguyen.service.JudgeService','user','2018-02-14 12:49:01','user','2018-02-14 12:49:01','com.minh.nguyen.service.JudgeService','0',NULL),(104,154,'com.minh.nguyen.service.JudgeService','user','2018-02-14 12:49:02','user','2018-02-14 12:49:02','com.minh.nguyen.service.JudgeService','0',NULL),(105,155,'com.minh.nguyen.service.JudgeService','user','2018-02-14 12:51:47','user','2018-02-14 12:51:47','com.minh.nguyen.service.JudgeService','0',NULL),(105,156,'com.minh.nguyen.service.JudgeService','user','2018-02-14 12:51:47','user','2018-02-14 12:51:47','com.minh.nguyen.service.JudgeService','0',NULL),(105,157,'com.minh.nguyen.service.JudgeService','user','2018-02-14 12:51:47','user','2018-02-14 12:51:47','com.minh.nguyen.service.JudgeService','0',NULL),(106,158,'com.minh.nguyen.service.JudgeService','user','2018-02-14 12:54:49','user','2018-02-14 12:54:49','com.minh.nguyen.service.JudgeService','0',NULL),(106,159,'com.minh.nguyen.service.JudgeService','user','2018-02-14 12:54:49','user','2018-02-14 12:54:49','com.minh.nguyen.service.JudgeService','0',NULL),(106,160,'com.minh.nguyen.service.JudgeService','user','2018-02-14 12:54:49','user','2018-02-14 12:54:49','com.minh.nguyen.service.JudgeService','0',NULL),(107,161,'com.minh.nguyen.service.JudgeService','user','2018-02-14 12:57:18','user','2018-02-14 12:57:18','com.minh.nguyen.service.JudgeService','0',NULL),(107,162,'com.minh.nguyen.service.JudgeService','user','2018-02-14 12:57:18','user','2018-02-14 12:57:18','com.minh.nguyen.service.JudgeService','0',NULL),(107,163,'com.minh.nguyen.service.JudgeService','user','2018-02-14 12:57:18','user','2018-02-14 12:57:18','com.minh.nguyen.service.JudgeService','0',NULL),(108,164,'com.minh.nguyen.service.JudgeService','admin','2018-02-14 13:30:45','admin','2018-02-14 13:30:45','com.minh.nguyen.service.JudgeService','0',NULL),(109,165,'com.minh.nguyen.service.JudgeService','admin','2018-02-14 13:31:47','admin','2018-02-14 13:31:47','com.minh.nguyen.service.JudgeService','0',NULL),(110,166,'com.minh.nguyen.service.JudgeService','admin','2018-02-14 13:39:26','admin','2018-02-14 13:39:26','com.minh.nguyen.service.JudgeService','0',NULL),(110,167,'com.minh.nguyen.service.JudgeService','admin','2018-02-14 13:39:27','admin','2018-02-14 13:39:27','com.minh.nguyen.service.JudgeService','0',NULL),(110,168,'com.minh.nguyen.service.JudgeService','admin','2018-02-14 13:39:27','admin','2018-02-14 13:39:27','com.minh.nguyen.service.JudgeService','0',NULL),(111,169,'com.minh.nguyen.service.JudgeService','admin','2018-02-14 13:39:50','admin','2018-02-14 13:39:50','com.minh.nguyen.service.JudgeService','0',NULL),(112,170,'com.minh.nguyen.service.JudgeService','admin','2018-02-14 13:44:02','admin','2018-02-14 13:44:02','com.minh.nguyen.service.JudgeService','0',NULL),(112,171,'com.minh.nguyen.service.JudgeService','admin','2018-02-14 13:44:02','admin','2018-02-14 13:44:02','com.minh.nguyen.service.JudgeService','0',NULL),(112,172,'com.minh.nguyen.service.JudgeService','admin','2018-02-14 13:44:02','admin','2018-02-14 13:44:02','com.minh.nguyen.service.JudgeService','0',NULL),(113,173,'com.minh.nguyen.service.JudgeService','admin','2018-02-14 13:54:54','admin','2018-02-14 13:54:54','com.minh.nguyen.service.JudgeService','0',NULL),(113,174,'com.minh.nguyen.service.JudgeService','admin','2018-02-14 13:54:54','admin','2018-02-14 13:54:54','com.minh.nguyen.service.JudgeService','0',NULL),(113,175,'com.minh.nguyen.service.JudgeService','admin','2018-02-14 13:54:54','admin','2018-02-14 13:54:54','com.minh.nguyen.service.JudgeService','0',NULL),(114,176,'com.minh.nguyen.service.JudgeService','admin','2018-02-14 13:56:01','admin','2018-02-14 13:56:01','com.minh.nguyen.service.JudgeService','0',NULL),(114,177,'com.minh.nguyen.service.JudgeService','admin','2018-02-14 13:56:01','admin','2018-02-14 13:56:01','com.minh.nguyen.service.JudgeService','0',NULL),(114,178,'com.minh.nguyen.service.JudgeService','admin','2018-02-14 13:56:01','admin','2018-02-14 13:56:01','com.minh.nguyen.service.JudgeService','0',NULL),(115,179,'com.minh.nguyen.service.JudgeService','admin','2018-02-14 13:56:21','admin','2018-02-14 13:56:21','com.minh.nguyen.service.JudgeService','0',NULL),(116,180,'com.minh.nguyen.service.JudgeService','user','2018-02-14 14:15:01','user','2018-02-14 14:15:01','com.minh.nguyen.service.JudgeService','0',NULL),(116,181,'com.minh.nguyen.service.JudgeService','user','2018-02-14 14:15:02','user','2018-02-14 14:15:02','com.minh.nguyen.service.JudgeService','0',NULL),(116,182,'com.minh.nguyen.service.JudgeService','user','2018-02-14 14:15:02','user','2018-02-14 14:15:02','com.minh.nguyen.service.JudgeService','0',NULL),(117,183,'com.minh.nguyen.service.JudgeService','user','2018-02-14 14:15:16','user','2018-02-14 14:15:16','com.minh.nguyen.service.JudgeService','0',NULL),(117,184,'com.minh.nguyen.service.JudgeService','user','2018-02-14 14:15:16','user','2018-02-14 14:15:16','com.minh.nguyen.service.JudgeService','0',NULL),(117,185,'com.minh.nguyen.service.JudgeService','user','2018-02-14 14:15:16','user','2018-02-14 14:15:16','com.minh.nguyen.service.JudgeService','0',NULL),(118,186,'com.minh.nguyen.service.JudgeService','user','2018-02-14 15:46:37','user','2018-02-14 15:46:37','com.minh.nguyen.service.JudgeService','0',NULL),(119,187,'com.minh.nguyen.service.JudgeService','admin','2018-02-16 20:27:23','admin','2018-02-16 20:27:23','com.minh.nguyen.service.JudgeService','0',NULL),(120,188,'com.minh.nguyen.service.JudgeService','admin','2018-02-16 20:27:33','admin','2018-02-16 20:27:33','com.minh.nguyen.service.JudgeService','0',NULL),(120,189,'com.minh.nguyen.service.JudgeService','admin','2018-02-16 20:27:34','admin','2018-02-16 20:27:34','com.minh.nguyen.service.JudgeService','0',NULL),(120,190,'com.minh.nguyen.service.JudgeService','admin','2018-02-16 20:27:34','admin','2018-02-16 20:27:34','com.minh.nguyen.service.JudgeService','0',NULL),(121,191,'com.minh.nguyen.service.JudgeService','admin','2018-02-16 20:35:05','admin','2018-02-16 20:35:05','com.minh.nguyen.service.JudgeService','0',NULL),(121,192,'com.minh.nguyen.service.JudgeService','admin','2018-02-16 20:35:06','admin','2018-02-16 20:35:06','com.minh.nguyen.service.JudgeService','0',NULL),(121,193,'com.minh.nguyen.service.JudgeService','admin','2018-02-16 20:35:06','admin','2018-02-16 20:35:06','com.minh.nguyen.service.JudgeService','0',NULL),(122,194,'com.minh.nguyen.service.JudgeService','user','2018-02-24 21:44:43','user','2018-02-24 21:44:43','com.minh.nguyen.service.JudgeService','0',NULL),(122,195,'com.minh.nguyen.service.JudgeService','user','2018-02-24 21:44:43','user','2018-02-24 21:44:43','com.minh.nguyen.service.JudgeService','0',NULL),(122,196,'com.minh.nguyen.service.JudgeService','user','2018-02-24 21:44:43','user','2018-02-24 21:44:43','com.minh.nguyen.service.JudgeService','0',NULL),(123,197,'com.minh.nguyen.service.JudgeService','user','2018-02-24 21:45:15','user','2018-02-24 21:45:15','com.minh.nguyen.service.JudgeService','0',NULL),(123,198,'com.minh.nguyen.service.JudgeService','user','2018-02-24 21:45:15','user','2018-02-24 21:45:15','com.minh.nguyen.service.JudgeService','0',NULL),(123,199,'com.minh.nguyen.service.JudgeService','user','2018-02-24 21:45:15','user','2018-02-24 21:45:15','com.minh.nguyen.service.JudgeService','0',NULL),(124,200,'com.minh.nguyen.service.JudgeService','user','2018-02-24 21:45:50','user','2018-02-24 21:45:50','com.minh.nguyen.service.JudgeService','0',NULL),(124,201,'com.minh.nguyen.service.JudgeService','user','2018-02-24 21:45:51','user','2018-02-24 21:45:51','com.minh.nguyen.service.JudgeService','0',NULL),(124,202,'com.minh.nguyen.service.JudgeService','user','2018-02-24 21:45:51','user','2018-02-24 21:45:51','com.minh.nguyen.service.JudgeService','0',NULL),(125,203,'com.minh.nguyen.service.JudgeService','user','2018-02-24 21:46:43','user','2018-02-24 21:46:43','com.minh.nguyen.service.JudgeService','0',NULL),(125,204,'com.minh.nguyen.service.JudgeService','user','2018-02-24 21:46:43','user','2018-02-24 21:46:43','com.minh.nguyen.service.JudgeService','0',NULL),(125,205,'com.minh.nguyen.service.JudgeService','user','2018-02-24 21:46:43','user','2018-02-24 21:46:43','com.minh.nguyen.service.JudgeService','0',NULL),(126,206,'com.minh.nguyen.service.JudgeService','admin','2018-02-25 21:01:36','admin','2018-02-25 21:01:36','com.minh.nguyen.service.JudgeService','0',NULL),(126,207,'com.minh.nguyen.service.JudgeService','admin','2018-02-25 21:01:36','admin','2018-02-25 21:01:36','com.minh.nguyen.service.JudgeService','0',NULL),(126,208,'com.minh.nguyen.service.JudgeService','admin','2018-02-25 21:01:36','admin','2018-02-25 21:01:36','com.minh.nguyen.service.JudgeService','0',NULL),(127,209,'com.minh.nguyen.service.JudgeService','admin','2018-02-25 21:01:43','admin','2018-02-25 21:01:43','com.minh.nguyen.service.JudgeService','0',NULL),(127,210,'com.minh.nguyen.service.JudgeService','admin','2018-02-25 21:01:43','admin','2018-02-25 21:01:43','com.minh.nguyen.service.JudgeService','0',NULL),(127,211,'com.minh.nguyen.service.JudgeService','admin','2018-02-25 21:01:43','admin','2018-02-25 21:01:43','com.minh.nguyen.service.JudgeService','0',NULL),(128,212,'com.minh.nguyen.service.JudgeService','admin','2018-02-25 21:04:27','admin','2018-02-25 21:04:27','com.minh.nguyen.service.JudgeService','0',NULL),(128,213,'com.minh.nguyen.service.JudgeService','admin','2018-02-25 21:04:27','admin','2018-02-25 21:04:27','com.minh.nguyen.service.JudgeService','0',NULL),(128,214,'com.minh.nguyen.service.JudgeService','admin','2018-02-25 21:04:27','admin','2018-02-25 21:04:27','com.minh.nguyen.service.JudgeService','0',NULL);
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
INSERT INTO `spring_session` VALUES ('053d32b8-32cb-472b-8880-f56e6e85d656',1520430202070,1520436373384,1800,'user'),('a3c87728-5d3d-4afc-b0d1-83be803c7209',1520432016264,1520435873068,1800,'admin');
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
INSERT INTO `spring_session_attributes` VALUES ('053d32b8-32cb-472b-8880-f56e6e85d656','currentUserAuthorities','�\�\0sr\0java.util.ArrayListx�\��\�a�\0I\0sizexp\0\0\0\0w\0\0\0\0x'),('053d32b8-32cb-472b-8880-f56e6e85d656','currentUserFullname','�\�\0t\0Okabe'),('053d32b8-32cb-472b-8880-f56e6e85d656','currentUserHandle','�\�\0t\0user'),('053d32b8-32cb-472b-8880-f56e6e85d656','currentUserId','�\�\0sr\0java.lang.Integer⠤���8\0I\0valuexr\0java.lang.Number����\��\0\0xp\0\0\0'),('053d32b8-32cb-472b-8880-f56e6e85d656','currentUserRoleId','�\�\0sr\0java.lang.Integer⠤���8\0I\0valuexr\0java.lang.Number����\��\0\0xp\0\0\0'),('053d32b8-32cb-472b-8880-f56e6e85d656','currentUserRoleName','�\�\0t\0\nSinh viên'),('053d32b8-32cb-472b-8880-f56e6e85d656','SPRING_SECURITY_CONTEXT','�\�\0sr\0=org.springframework.security.core.context.SecurityContextImpl\0\0\0\0\0\0�\0L\0authenticationt\02Lorg/springframework/security/core/Authentication;xpsr\0Oorg.springframework.security.authentication.UsernamePasswordAuthenticationToken\0\0\0\0\0\0�\0L\0credentialst\0Ljava/lang/Object;L\0	principalq\0~\0xr\0Gorg.springframework.security.authentication.AbstractAuthenticationTokenӪ(~nGd\0Z\0\rauthenticatedL\0authoritiest\0Ljava/util/Collection;L\0detailsq\0~\0xpsr\0&java.util.Collections$UnmodifiableList�%1�\�\0L\0listt\0Ljava/util/List;xr\0,java.util.Collections$UnmodifiableCollectionB\0�\�^�\0L\0cq\0~\0xpsr\0java.util.ArrayListx�\��\�a�\0I\0sizexp\0\0\0\0w\0\0\0\0xq\0~\0\rsr\0Horg.springframework.security.web.authentication.WebAuthenticationDetails\0\0\0\0\0\0�\0L\0\rremoteAddresst\0Ljava/lang/String;L\0	sessionIdq\0~\0xpt\00:0:0:0:0:0:0:1t\0$b79da889-25be-40bf-b9e1-89d2a036112apsr\02org.springframework.security.core.userdetails.User\0\0\0\0\0\0�\0Z\0accountNonExpiredZ\0accountNonLockedZ\0credentialsNonExpiredZ\0enabledL\0authoritiest\0Ljava/util/Set;L\0passwordq\0~\0L\0usernameq\0~\0xpsr\0%java.util.Collections$UnmodifiableSet��я��U\0\0xq\0~\0\nsr\0java.util.TreeSetݘP��\�[\0\0xpsr\0Forg.springframework.security.core.userdetails.User$AuthorityComparator\0\0\0\0\0\0�\0\0xpw\0\0\0\0xpt\0user'),('a3c87728-5d3d-4afc-b0d1-83be803c7209','currentUserAuthorities','�\�\0sr\0java.util.ArrayListx�\��\�a�\0I\0sizexp\0\0\0w\0\0\0sr\0java.lang.Integer⠤���8\0I\0valuexr\0java.lang.Number����\��\0\0xp\0\0\0sq\0~\0\0\0\0sq\0~\0\0\0\0sq\0~\0\0\0\0sq\0~\0\0\0\0\nsq\0~\0\0\0\0sq\0~\0\0\0\0\rx'),('a3c87728-5d3d-4afc-b0d1-83be803c7209','currentUserFullname','�\�\0t\0MakiseKurisu'),('a3c87728-5d3d-4afc-b0d1-83be803c7209','currentUserHandle','�\�\0t\0admin'),('a3c87728-5d3d-4afc-b0d1-83be803c7209','currentUserId','�\�\0sr\0java.lang.Integer⠤���8\0I\0valuexr\0java.lang.Number����\��\0\0xp\0\0\0'),('a3c87728-5d3d-4afc-b0d1-83be803c7209','currentUserRoleId','�\�\0sr\0java.lang.Integer⠤���8\0I\0valuexr\0java.lang.Number����\��\0\0xp\0\0\0'),('a3c87728-5d3d-4afc-b0d1-83be803c7209','currentUserRoleName','�\�\0t\0Quản trị viên'),('a3c87728-5d3d-4afc-b0d1-83be803c7209','org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository.CSRF_TOKEN','�\�\0sr\06org.springframework.security.web.csrf.DefaultCsrfTokenZ\�\�/��\�\0L\0\nheaderNamet\0Ljava/lang/String;L\0\rparameterNameq\0~\0L\0tokenq\0~\0xpt\0X-CSRF-TOKENt\0_csrft\0$2096a7c8-75e6-465b-bd00-3b58a7bf3367'),('a3c87728-5d3d-4afc-b0d1-83be803c7209','SPRING_SECURITY_CONTEXT','�\�\0sr\0=org.springframework.security.core.context.SecurityContextImpl\0\0\0\0\0\0�\0L\0authenticationt\02Lorg/springframework/security/core/Authentication;xpsr\0Oorg.springframework.security.authentication.UsernamePasswordAuthenticationToken\0\0\0\0\0\0�\0L\0credentialst\0Ljava/lang/Object;L\0	principalq\0~\0xr\0Gorg.springframework.security.authentication.AbstractAuthenticationTokenӪ(~nGd\0Z\0\rauthenticatedL\0authoritiest\0Ljava/util/Collection;L\0detailsq\0~\0xpsr\0&java.util.Collections$UnmodifiableList�%1�\�\0L\0listt\0Ljava/util/List;xr\0,java.util.Collections$UnmodifiableCollectionB\0�\�^�\0L\0cq\0~\0xpsr\0java.util.ArrayListx�\��\�a�\0I\0sizexp\0\0\0w\0\0\0sr\0Borg.springframework.security.core.authority.SimpleGrantedAuthority\0\0\0\0\0\0�\0L\0rolet\0Ljava/lang/String;xpt\0CAN_CREATE_CONTESTsq\0~\0t\0CAN_CREATE_PROBLEMsq\0~\0t\0CAN_CREATE_USERsq\0~\0t\0\rCAN_EDIT_USERsq\0~\0t\0CAN_VIEW_ALL_CONTESTsq\0~\0t\0CAN_VIEW_ALL_PROBLEMsq\0~\0t\0CAN_VIEW_ALL_SUBMISSIONxq\0~\0\rsr\0Horg.springframework.security.web.authentication.WebAuthenticationDetails\0\0\0\0\0\0�\0L\0\rremoteAddressq\0~\0L\0	sessionIdq\0~\0xpt\0	127.0.0.1t\0$dabfb6cf-c4d7-47ad-8a31-a943cacb790cpsr\02org.springframework.security.core.userdetails.User\0\0\0\0\0\0�\0Z\0accountNonExpiredZ\0accountNonLockedZ\0credentialsNonExpiredZ\0enabledL\0authoritiest\0Ljava/util/Set;L\0passwordq\0~\0L\0usernameq\0~\0xpsr\0%java.util.Collections$UnmodifiableSet��я��U\0\0xq\0~\0\nsr\0java.util.TreeSetݘP��\�[\0\0xpsr\0Forg.springframework.security.core.userdetails.User$AuthorityComparator\0\0\0\0\0\0�\0\0xpw\0\0\0q\0~\0q\0~\0q\0~\0q\0~\0q\0~\0q\0~\0\Zq\0~\0xpt\0admin');
/*!40000 ALTER TABLE `spring_session_attributes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL,
  `probSolveCnt` int(11) DEFAULT NULL,
  `createClass` varchar(100) DEFAULT NULL,
  `createUser` varchar(30) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `updateUser` varchar(45) DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `updateClass` varchar(45) DEFAULT NULL,
  `deleteFlg` varchar(1) DEFAULT '0',
  `deleteTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`,`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subject`
--

LOCK TABLES `subject` WRITE;
/*!40000 ALTER TABLE `subject` DISABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=129 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `submission`
--

LOCK TABLES `submission` WRITE;
/*!40000 ALTER TABLE `submission` DISABLE KEYS */;
INSERT INTO `submission` VALUES (114,10,2,1,121,0,'#include<bits/stdc++.h>\r\nusing namespace std;\r\nint main(){\r\n	int a,b;\r\ncin >> a >> b;\r\ncout << a + b;\r\n}',6,'Đúng',NULL,'com.minh.nguyen.service.ContestService','admin','2018-02-14 13:55:56','admin','2018-02-14 13:55:56','com.minh.nguyen.service.ContestService','0',NULL),(115,20,2,1,0,0,'#include<bits/stdc++.h>\r\nusing namespace std;\r\nint main(){\r\n	int a,b;\r\ncin >> a >> b;\r\ncout << a + b;\r\n}',1,'Lỗi biên dịch',NULL,'com.minh.nguyen.service.ContestService','admin','2018-02-14 13:56:16','admin','2018-02-14 13:56:16','com.minh.nguyen.service.ContestService','0',NULL),(116,20,2,2,149,0,'#include<bits/stdc++.h>\r\nusing namespace std;\r\nint main(){\r\n	int a,b;\r\ncin >> a >> b;\r\ncout << a + b;\r\n}',6,'Đúng',NULL,'com.minh.nguyen.service.ContestService','user','2018-02-14 14:14:57','user','2018-02-14 14:14:57','com.minh.nguyen.service.ContestService','0',NULL),(117,10,2,2,129,0,'#include<bits/stdc++.h>\r\nusing namespace std;\r\nint main(){\r\n	int a,b;\r\ncin >> a >> b;\r\ncout << a + b;\r\n}',6,'Đúng',NULL,'com.minh.nguyen.service.ContestService','user','2018-02-14 14:15:12','user','2018-02-14 14:15:12','com.minh.nguyen.service.ContestService','0',NULL),(118,10,2,2,0,0,'#include<bits/stdc++.h>\r\nusing namespace std;\r\nint main(){\r\n	int a,b;\r\ncin >> a >> b;\r\ncout << a= + b;\r\n}',1,'Lỗi biên dịch',NULL,'com.minh.nguyen.service.ContestService','user','2018-02-14 15:46:32','user','2018-02-14 15:46:32','com.minh.nguyen.service.ContestService','0',NULL),(119,20,2,1,0,0,'#include<bits/stdc++.h>\r\nusing namespace std;\r\nint main(){\r\n	int a,b;\r\ncin >> a >> b;\r\ncout << a + b;\r\n}',1,'Lỗi biên dịch',NULL,'com.minh.nguyen.service.ProblemService','admin','2018-02-16 20:27:18','admin','2018-02-16 20:27:18','com.minh.nguyen.service.ProblemService','0',NULL),(120,20,2,1,150,0,'#include<bits/stdc++.h>\r\nusing namespace std;\r\nint main(){\r\n	int a,b;\r\ncin >> a >> b;\r\ncout << a + b;\r\n}',6,'Đúng',NULL,'com.minh.nguyen.service.ProblemService','admin','2018-02-16 20:27:30','admin','2018-02-16 20:27:30','com.minh.nguyen.service.ProblemService','0',NULL),(121,20,2,1,179,0,'#include<bits/stdc++.h>\r\nusing namespace std;\r\nint main(){\r\n	int a,b;\r\ncin >> a >> b;\r\ncout << a + b;\r\n}',6,'Đúng',NULL,'com.minh.nguyen.service.ProblemService','admin','2018-02-16 20:35:02','admin','2018-02-16 20:35:02','com.minh.nguyen.service.ProblemService','0',NULL),(122,20,2,2,0,0,'#include<bits/stdc++.h>\r\nusing namespace std;\r\nint main(){\r\n    int a,b;\r\n    cout << a - 123;\r\n}',2,'Lỗi runtime',NULL,'com.minh.nguyen.service.ProblemService','user','2018-02-24 21:44:39','user','2018-02-24 21:44:39','com.minh.nguyen.service.ProblemService','0',NULL),(123,20,2,2,0,0,'#include<bits/stdc++.h>\r\nusing namespace std;\r\nint main(){\r\n    int a,b;\r\n    cout << a - 123;\r\n}',4,'Lỗi biên dịch',NULL,'com.minh.nguyen.service.ProblemService','user','2018-02-24 21:45:12','user','2018-02-24 21:45:12','com.minh.nguyen.service.ProblemService','0',NULL),(124,20,2,2,0,0,'#include<bits/stdc++.h>\r\nusing namespace std;\r\nint main(){\r\n    int a,b;\r\n    cout << a - 123;\r\n}',3,'Sai kết quả',NULL,'com.minh.nguyen.service.ProblemService','user','2018-02-24 21:45:48','user','2018-02-24 21:45:48','com.minh.nguyen.service.ProblemService','0',NULL),(125,20,1,2,0,0,'#include<bits/stdc++.h>\r\nusing namespace std;\r\nint main(){\r\n    cout <<123;\r\n}',6,'Đúng',NULL,'com.minh.nguyen.service.ProblemService','user','2018-02-24 21:46:43','user','2018-02-24 21:46:43','com.minh.nguyen.service.ProblemService','0',NULL),(126,20,2,1,0,0,'#include<bits/stdc++.h>\r\nusing namespace std;\r\nint main(){\r\n    int a,b;\r\n    cin >> a >> b;\r\n    cout << a + b;\r\n}',2,'Lỗi runtime',NULL,'com.minh.nguyen.service.ProblemService','admin','2018-02-25 21:01:32','admin','2018-02-25 21:01:32','com.minh.nguyen.service.ProblemService','0',NULL),(127,20,2,1,0,0,'#include<bits/stdc++.h>\r\nusing namespace std;\r\nint main(){\r\n    int a,b;\r\n    cin >> a >> b;\r\n    cout << a + b;\r\n}',2,'Lỗi runtime',NULL,'com.minh.nguyen.service.ProblemService','admin','2018-02-25 21:01:40','admin','2018-02-25 21:01:40','com.minh.nguyen.service.ProblemService','0',NULL),(128,20,2,1,25,0,'#include<bits/stdc++.h>\r\nusing namespace std;\r\nint main(){\r\n    int a,b;\r\n    cin >> a >> b;\r\n    cout << a + b;\r\n}',6,'Đúng',NULL,'com.minh.nguyen.service.ProblemService','admin','2018-02-25 21:04:24','admin','2018-02-25 21:04:24','com.minh.nguyen.service.ProblemService','0',NULL);
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
) ENGINE=InnoDB AUTO_INCREMENT=215 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `submitdetail`
--

LOCK TABLES `submitdetail` WRITE;
/*!40000 ALTER TABLE `submitdetail` DISABLE KEYS */;
INSERT INTO `submitdetail` VALUES (68,'1 2',NULL,'1\r\n2\r\n3','Lỗi runtime',0,0,0,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:24:48','minh.nt','2018-01-31 23:24:48','class com.minh.nguyen.service.BaseService','0',NULL),(69,'5 10',NULL,'5\r\n10\r\n15','Lỗi runtime',0,0,0,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:24:52','minh.nt','2018-01-31 23:24:52','class com.minh.nguyen.service.BaseService','0',NULL),(70,'123 1',NULL,'123\r\n1\r\n124\r\n','Lỗi runtime',0,0,0,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:24:56','minh.nt','2018-01-31 23:24:56','class com.minh.nguyen.service.BaseService','0',NULL),(71,'1 2','5','1\r\n2\r\n3','Số dòng người dùng xuất (1) nhiều hơn so với kết quả (3)!',5,163,0,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:26:03','minh.nt','2018-01-31 23:26:03','class com.minh.nguyen.service.BaseService','0',NULL),(72,'5 10','5','5\r\n10\r\n15','Số dòng người dùng xuất (1) nhiều hơn so với kết quả (3)!',5,150,0,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:26:04','minh.nt','2018-01-31 23:26:04','class com.minh.nguyen.service.BaseService','0',NULL),(73,'123 1','5','123\r\n1\r\n124\r\n','Số dòng người dùng xuất (1) nhiều hơn so với kết quả (3)!',5,272,0,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:26:04','minh.nt','2018-01-31 23:26:04','class com.minh.nguyen.service.BaseService','0',NULL),(74,'1 2',NULL,'1\r\n2\r\n3','Lỗi runtime',0,0,0,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:28:01','minh.nt','2018-01-31 23:28:01','class com.minh.nguyen.service.BaseService','0',NULL),(75,'5 10',NULL,'5\r\n10\r\n15','Lỗi runtime',0,0,0,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:28:04','minh.nt','2018-01-31 23:28:04','class com.minh.nguyen.service.BaseService','0',NULL),(76,'123 1',NULL,'123\r\n1\r\n124\r\n','Lỗi runtime',0,0,0,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:28:07','minh.nt','2018-01-31 23:28:07','class com.minh.nguyen.service.BaseService','0',NULL),(77,'1 2',NULL,'1\r\n2\r\n3','Lỗi runtime',0,0,0,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:29:34','minh.nt','2018-01-31 23:29:34','class com.minh.nguyen.service.BaseService','0',NULL),(78,'5 10',NULL,'5\r\n10\r\n15','Lỗi runtime',0,0,0,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:29:38','minh.nt','2018-01-31 23:29:38','class com.minh.nguyen.service.BaseService','0',NULL),(79,'123 1',NULL,'123\r\n1\r\n124\r\n','Lỗi runtime',0,0,0,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:29:42','minh.nt','2018-01-31 23:29:42','class com.minh.nguyen.service.BaseService','0',NULL),(80,'1 2',NULL,'1\r\n2\r\n3','Lỗi runtime',0,3816,0,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:31:58','minh.nt','2018-01-31 23:31:58','class com.minh.nguyen.service.BaseService','0',NULL),(81,'5 10',NULL,'5\r\n10\r\n15','Lỗi runtime',0,3782,0,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:32:02','minh.nt','2018-01-31 23:32:02','class com.minh.nguyen.service.BaseService','0',NULL),(82,'123 1',NULL,'123\r\n1\r\n124\r\n','Lỗi runtime',0,3678,0,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:32:05','minh.nt','2018-01-31 23:32:05','class com.minh.nguyen.service.BaseService','0',NULL),(83,'1 2','1\r\n2\r\n3','1\r\n2\r\n3','Đúng',6,105,0,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:33:35','minh.nt','2018-01-31 23:33:35','class com.minh.nguyen.service.BaseService','0',NULL),(84,'5 10','5\r\n10\r\n15','5\r\n10\r\n15','Đúng',6,93,0,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:33:35','minh.nt','2018-01-31 23:33:35','class com.minh.nguyen.service.BaseService','0',NULL),(85,'123 1','123\r\n1\r\n124','123\r\n1\r\n124\r\n','Đúng',6,84,0,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:33:35','minh.nt','2018-01-31 23:33:35','class com.minh.nguyen.service.BaseService','0',NULL),(86,'1 2','1\r\n2\r\n3','1\r\n2\r\n3','Đúng',6,146,0,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:33:43','minh.nt','2018-01-31 23:33:43','class com.minh.nguyen.service.BaseService','0',NULL),(87,'5 10','5\r\n10\r\n15','5\r\n10\r\n15','Đúng',6,148,0,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:33:43','minh.nt','2018-01-31 23:33:43','class com.minh.nguyen.service.BaseService','0',NULL),(88,'123 1','123\r\n1\r\n124','123\r\n1\r\n124\r\n','Đúng',6,119,0,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:33:43','minh.nt','2018-01-31 23:33:43','class com.minh.nguyen.service.BaseService','0',NULL),(89,'1 2','1\r\n2\r\n3','1\r\n2\r\n3','Đúng',6,79,0,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:34:54','minh.nt','2018-01-31 23:34:54','class com.minh.nguyen.service.BaseService','0',NULL),(90,'5 10','5\r\n10\r\n15','5\r\n10\r\n15','Đúng',6,72,0,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:34:54','minh.nt','2018-01-31 23:34:54','class com.minh.nguyen.service.BaseService','0',NULL),(91,'123 1','123\r\n1\r\n124','123\r\n1\r\n124\r\n','Đúng',6,82,0,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:34:54','minh.nt','2018-01-31 23:34:54','class com.minh.nguyen.service.BaseService','0',NULL),(92,'1 2','1\r\n2\r\n3','1\r\n2\r\n3','Đúng',6,130,0,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:34:57','minh.nt','2018-01-31 23:34:57','class com.minh.nguyen.service.BaseService','0',NULL),(93,'5 10','5\r\n10\r\n15','5\r\n10\r\n15','Đúng',6,102,0,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:34:57','minh.nt','2018-01-31 23:34:57','class com.minh.nguyen.service.BaseService','0',NULL),(94,'123 1','123\r\n1\r\n124','123\r\n1\r\n124\r\n','Đúng',6,137,0,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:34:58','minh.nt','2018-01-31 23:34:58','class com.minh.nguyen.service.BaseService','0',NULL),(95,'1 2','1\r\n2\r\n3','1\r\n2\r\n3','Đúng',6,81,0,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:35:09','minh.nt','2018-01-31 23:35:09','class com.minh.nguyen.service.BaseService','0',NULL),(96,'5 10','5\r\n10\r\n15','5\r\n10\r\n15','Đúng',6,70,0,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:35:10','minh.nt','2018-01-31 23:35:10','class com.minh.nguyen.service.BaseService','0',NULL),(97,'123 1','123\r\n1\r\n124','123\r\n1\r\n124\r\n','Đúng',6,78,0,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:35:10','minh.nt','2018-01-31 23:35:10','class com.minh.nguyen.service.BaseService','0',NULL),(98,'1 2','1\r\n2\r\n3','1\r\n2\r\n3','Đúng',6,82,0,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:35:16','minh.nt','2018-01-31 23:35:16','class com.minh.nguyen.service.BaseService','0',NULL),(99,'5 10','5\r\n10\r\n15','5\r\n10\r\n15','Đúng',6,67,0,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:35:16','minh.nt','2018-01-31 23:35:16','class com.minh.nguyen.service.BaseService','0',NULL),(100,'123 1','123\r\n1\r\n124','123\r\n1\r\n124\r\n','Đúng',6,70,0,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:35:16','minh.nt','2018-01-31 23:35:16','class com.minh.nguyen.service.BaseService','0',NULL),(101,'1 2',NULL,'1\r\n2\r\n3','Lỗi runtime',0,3536,0,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:35:50','minh.nt','2018-01-31 23:35:50','class com.minh.nguyen.service.BaseService','0',NULL),(102,'1 2','1\r\n2\r\n1\r\n','1\r\n2\r\n3','Sai kết quả ở dòng thứ 3:\r\nOutput: 1\r\nKết quả: 3',5,80,0,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:35:51','minh.nt','2018-01-31 23:35:51','class com.minh.nguyen.service.BaseService','0',NULL),(103,'5 10','5\r\n10\r\n5\r\n','5\r\n10\r\n15','Sai kết quả ở dòng thứ 3:\r\nOutput: 5\r\nKết quả: 15',5,89,0,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:35:51','minh.nt','2018-01-31 23:35:51','class com.minh.nguyen.service.BaseService','0',NULL),(104,'123 1','123\r\n1\r\n123\r\n','123\r\n1\r\n124\r\n','Sai kết quả ở dòng thứ 3:\r\nOutput: 123\r\nKết quả: 124',5,85,0,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:35:51','minh.nt','2018-01-31 23:35:51','class com.minh.nguyen.service.BaseService','0',NULL),(105,'5 10',NULL,'5\r\n10\r\n15','Lỗi runtime',0,3221,0,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:35:54','minh.nt','2018-01-31 23:35:54','class com.minh.nguyen.service.BaseService','0',NULL),(106,'123 1',NULL,'123\r\n1\r\n124\r\n','Lỗi runtime',0,2872,0,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:35:56','minh.nt','2018-01-31 23:35:56','class com.minh.nguyen.service.BaseService','0',NULL),(107,'1 2','1\r\n2\r\n3\r\n','1\r\n2\r\n3','Đúng',6,81,0,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:35:58','minh.nt','2018-01-31 23:35:58','class com.minh.nguyen.service.BaseService','0',NULL),(108,'5 10','5\r\n10\r\n15\r\n','5\r\n10\r\n15','Đúng',6,89,0,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:35:58','minh.nt','2018-01-31 23:35:58','class com.minh.nguyen.service.BaseService','0',NULL),(109,'123 1','123\r\n1\r\n124\r\n','123\r\n1\r\n124\r\n','Đúng',6,71,0,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-31 23:35:59','minh.nt','2018-01-31 23:35:59','class com.minh.nguyen.service.BaseService','0',NULL),(110,'1 2','3','3','Đúng',6,159,0,'com.minh.nguyen.service.JudgeService','admin','2018-02-11 14:23:54','admin','2018-02-11 14:23:54','com.minh.nguyen.service.JudgeService','0',NULL),(111,'3 4','7','7','Đúng',6,80,0,'com.minh.nguyen.service.JudgeService','admin','2018-02-11 14:23:54','admin','2018-02-11 14:23:54','com.minh.nguyen.service.JudgeService','0',NULL),(112,'5 4','9',' 8','Sai kết quả ở dòng thứ 1:\r\nOutput: 9\r\nKết quả:  8',5,71,0,'com.minh.nguyen.service.JudgeService','admin','2018-02-11 14:23:54','admin','2018-02-11 14:23:54','com.minh.nguyen.service.JudgeService','0',NULL),(113,'1 2','3','3','Đúng',6,102,0,'com.minh.nguyen.service.JudgeService','admin','2018-02-11 14:29:14','admin','2018-02-11 14:29:14','com.minh.nguyen.service.JudgeService','0',NULL),(114,'3 4','7','7','Đúng',6,106,0,'com.minh.nguyen.service.JudgeService','admin','2018-02-11 14:29:15','admin','2018-02-11 14:29:15','com.minh.nguyen.service.JudgeService','0',NULL),(115,'5 4','9',' 8','Sai kết quả ở dòng thứ 1:\r\nOutput: 9\r\nKết quả:  8',5,80,0,'com.minh.nguyen.service.JudgeService','admin','2018-02-11 14:29:16','admin','2018-02-11 14:29:16','com.minh.nguyen.service.JudgeService','0',NULL),(116,'1 2','3','3','Đúng',6,127,0,'com.minh.nguyen.service.JudgeService','admin','2018-02-11 14:30:24','admin','2018-02-11 14:30:24','com.minh.nguyen.service.JudgeService','0',NULL),(117,'3 4','7','7','Đúng',6,100,0,'com.minh.nguyen.service.JudgeService','admin','2018-02-11 14:30:24','admin','2018-02-11 14:30:24','com.minh.nguyen.service.JudgeService','0',NULL),(118,'5 4','9','9','Đúng',6,145,0,'com.minh.nguyen.service.JudgeService','admin','2018-02-11 14:30:24','admin','2018-02-11 14:30:24','com.minh.nguyen.service.JudgeService','0',NULL),(119,'1 2','3','3','Đúng',6,83,0,'com.minh.nguyen.service.JudgeService','admin','2018-02-11 14:35:50','admin','2018-02-11 14:35:50','com.minh.nguyen.service.JudgeService','0',NULL),(120,'3 4','7','7','Đúng',6,80,0,'com.minh.nguyen.service.JudgeService','admin','2018-02-11 14:35:50','admin','2018-02-11 14:35:50','com.minh.nguyen.service.JudgeService','0',NULL),(121,'5 4','9','9','Đúng',6,97,0,'com.minh.nguyen.service.JudgeService','admin','2018-02-11 14:35:51','admin','2018-02-11 14:35:50','com.minh.nguyen.service.JudgeService','0',NULL),(122,'1 2','3','3','Đúng',6,145,0,'com.minh.nguyen.service.JudgeService','admin','2018-02-11 15:13:59','admin','2018-02-11 15:13:59','com.minh.nguyen.service.JudgeService','0',NULL),(123,'3 4','7','7','Đúng',6,111,0,'com.minh.nguyen.service.JudgeService','admin','2018-02-11 15:13:59','admin','2018-02-11 15:13:59','com.minh.nguyen.service.JudgeService','0',NULL),(124,'5 4','9','9','Đúng',6,72,0,'com.minh.nguyen.service.JudgeService','admin','2018-02-11 15:13:59','admin','2018-02-11 15:13:59','com.minh.nguyen.service.JudgeService','0',NULL),(125,'1 2','2','3','Sai kết quả ở dòng thứ 1:\r\nOutput: 2\r\nKết quả: 3',5,186,0,'com.minh.nguyen.service.JudgeService','admin','2018-02-11 15:36:50','admin','2018-02-11 15:36:50','com.minh.nguyen.service.JudgeService','0',NULL),(126,'3 4','6','7','Sai kết quả ở dòng thứ 1:\r\nOutput: 6\r\nKết quả: 7',5,121,0,'com.minh.nguyen.service.JudgeService','admin','2018-02-11 15:36:50','admin','2018-02-11 15:36:50','com.minh.nguyen.service.JudgeService','0',NULL),(127,'5 4','8','9','Sai kết quả ở dòng thứ 1:\r\nOutput: 8\r\nKết quả: 9',5,121,0,'com.minh.nguyen.service.JudgeService','admin','2018-02-11 15:36:50','admin','2018-02-11 15:36:50','com.minh.nguyen.service.JudgeService','0',NULL),(128,'1 2','3','3','Đúng',6,191,0,'com.minh.nguyen.service.JudgeService','admin','2018-02-11 15:48:55','admin','2018-02-11 15:48:55','com.minh.nguyen.service.JudgeService','0',NULL),(129,'3 4','7','7','Đúng',6,141,0,'com.minh.nguyen.service.JudgeService','admin','2018-02-11 15:48:55','admin','2018-02-11 15:48:55','com.minh.nguyen.service.JudgeService','0',NULL),(130,'5 4','9','9','Đúng',6,128,0,'com.minh.nguyen.service.JudgeService','admin','2018-02-11 15:48:55','admin','2018-02-11 15:48:55','com.minh.nguyen.service.JudgeService','0',NULL),(131,NULL,NULL,NULL,NULL,0,0,0,'com.minh.nguyen.service.JudgeService','user','2018-02-11 20:17:16','user','2018-02-11 20:17:16','com.minh.nguyen.service.JudgeService','0',NULL),(132,'1 2','3','3','Đúng',6,367,0,'com.minh.nguyen.service.JudgeService','user','2018-02-11 20:18:08','user','2018-02-11 20:18:08','com.minh.nguyen.service.JudgeService','0',NULL),(133,'3 4','7','7','Đúng',6,153,0,'com.minh.nguyen.service.JudgeService','user','2018-02-11 20:18:08','user','2018-02-11 20:18:08','com.minh.nguyen.service.JudgeService','0',NULL),(134,'5 4','9','9','Đúng',6,164,0,'com.minh.nguyen.service.JudgeService','user','2018-02-11 20:18:08','user','2018-02-11 20:18:08','com.minh.nguyen.service.JudgeService','0',NULL),(135,'1 2','3','3','Đúng',6,244,0,'com.minh.nguyen.service.JudgeService','user','2018-02-11 20:52:01','user','2018-02-11 20:52:01','com.minh.nguyen.service.JudgeService','0',NULL),(136,'3 4','7','7','Đúng',6,133,0,'com.minh.nguyen.service.JudgeService','user','2018-02-11 20:52:01','user','2018-02-11 20:52:01','com.minh.nguyen.service.JudgeService','0',NULL),(137,'5 4','9','9','Đúng',6,231,0,'com.minh.nguyen.service.JudgeService','user','2018-02-11 20:52:02','user','2018-02-11 20:52:02','com.minh.nguyen.service.JudgeService','0',NULL),(138,'1 2','3','3','Đúng',6,123,0,'com.minh.nguyen.service.JudgeService','user','2018-02-11 20:53:07','user','2018-02-11 20:53:07','com.minh.nguyen.service.JudgeService','0',NULL),(139,'3 4','7','7','Đúng',6,154,0,'com.minh.nguyen.service.JudgeService','user','2018-02-11 20:53:07','user','2018-02-11 20:53:07','com.minh.nguyen.service.JudgeService','0',NULL),(140,'5 4','9','9','Đúng',6,91,0,'com.minh.nguyen.service.JudgeService','user','2018-02-11 20:53:07','user','2018-02-11 20:53:07','com.minh.nguyen.service.JudgeService','0',NULL),(141,'1 2','3','3','Đúng',6,129,0,'com.minh.nguyen.service.JudgeService','user','2018-02-11 20:53:50','user','2018-02-11 20:53:50','com.minh.nguyen.service.JudgeService','0',NULL),(142,'3 4','7','7','Đúng',6,176,0,'com.minh.nguyen.service.JudgeService','user','2018-02-11 20:53:51','user','2018-02-11 20:53:51','com.minh.nguyen.service.JudgeService','0',NULL),(143,'5 4','9','9','Đúng',6,112,0,'com.minh.nguyen.service.JudgeService','user','2018-02-11 20:53:51','user','2018-02-11 20:53:51','com.minh.nguyen.service.JudgeService','0',NULL),(144,NULL,NULL,NULL,NULL,0,0,0,'com.minh.nguyen.service.JudgeService','user','2018-02-12 20:04:44','user','2018-02-12 20:04:44','com.minh.nguyen.service.JudgeService','0',NULL),(145,'1 2','3','3','Đúng',6,110,0,'com.minh.nguyen.service.JudgeService','user','2018-02-12 20:05:12','user','2018-02-12 20:05:12','com.minh.nguyen.service.JudgeService','0',NULL),(146,'3 4','7','7','Đúng',6,81,0,'com.minh.nguyen.service.JudgeService','user','2018-02-12 20:05:12','user','2018-02-12 20:05:12','com.minh.nguyen.service.JudgeService','0',NULL),(147,'5 4','9','9','Đúng',6,88,0,'com.minh.nguyen.service.JudgeService','user','2018-02-12 20:05:12','user','2018-02-12 20:05:12','com.minh.nguyen.service.JudgeService','0',NULL),(148,NULL,NULL,NULL,NULL,0,0,0,'com.minh.nguyen.service.JudgeService','user','2018-02-14 11:21:26','user','2018-02-14 11:21:26','com.minh.nguyen.service.JudgeService','0',NULL),(149,'5 10','15','5\r\n10\r\n15','Số dòng người dùng xuất (1) ít hơn so với kết quả (3)!',5,121,0,'com.minh.nguyen.service.JudgeService','user','2018-02-14 11:21:56','user','2018-02-14 11:21:56','com.minh.nguyen.service.JudgeService','0',NULL),(150,'123 1','124','123\r\n1\r\n124\r\n','Số dòng người dùng xuất (1) ít hơn so với kết quả (3)!',5,91,0,'com.minh.nguyen.service.JudgeService','user','2018-02-14 11:21:56','user','2018-02-14 11:21:56','com.minh.nguyen.service.JudgeService','0',NULL),(151,'1 2','3','3','Đúng',6,105,0,'com.minh.nguyen.service.JudgeService','user','2018-02-14 11:21:56','user','2018-02-14 11:21:56','com.minh.nguyen.service.JudgeService','0',NULL),(152,'1 2','3','3','Đúng',6,208,0,'com.minh.nguyen.service.JudgeService','user','2018-02-14 12:49:01','user','2018-02-14 12:49:01','com.minh.nguyen.service.JudgeService','0',NULL),(153,'3 4','7','7','Đúng',6,114,0,'com.minh.nguyen.service.JudgeService','user','2018-02-14 12:49:01','user','2018-02-14 12:49:01','com.minh.nguyen.service.JudgeService','0',NULL),(154,'5 4','9','9','Đúng',6,116,0,'com.minh.nguyen.service.JudgeService','user','2018-02-14 12:49:02','user','2018-02-14 12:49:02','com.minh.nguyen.service.JudgeService','0',NULL),(155,'1 2','3','3','Đúng',6,95,0,'com.minh.nguyen.service.JudgeService','user','2018-02-14 12:51:47','user','2018-02-14 12:51:47','com.minh.nguyen.service.JudgeService','0',NULL),(156,'3 4','7','7','Đúng',6,91,0,'com.minh.nguyen.service.JudgeService','user','2018-02-14 12:51:47','user','2018-02-14 12:51:47','com.minh.nguyen.service.JudgeService','0',NULL),(157,'5 4','9','9','Đúng',6,89,0,'com.minh.nguyen.service.JudgeService','user','2018-02-14 12:51:47','user','2018-02-14 12:51:47','com.minh.nguyen.service.JudgeService','0',NULL),(158,'1 2','3','3','Đúng',6,122,0,'com.minh.nguyen.service.JudgeService','user','2018-02-14 12:54:49','user','2018-02-14 12:54:49','com.minh.nguyen.service.JudgeService','0',NULL),(159,'3 4','7','7','Đúng',6,118,0,'com.minh.nguyen.service.JudgeService','user','2018-02-14 12:54:49','user','2018-02-14 12:54:49','com.minh.nguyen.service.JudgeService','0',NULL),(160,'5 4','9','9','Đúng',6,111,0,'com.minh.nguyen.service.JudgeService','user','2018-02-14 12:54:49','user','2018-02-14 12:54:49','com.minh.nguyen.service.JudgeService','0',NULL),(161,'1 2','3','3','Đúng',6,97,0,'com.minh.nguyen.service.JudgeService','user','2018-02-14 12:57:18','user','2018-02-14 12:57:18','com.minh.nguyen.service.JudgeService','0',NULL),(162,'3 4','7','7','Đúng',6,84,0,'com.minh.nguyen.service.JudgeService','user','2018-02-14 12:57:18','user','2018-02-14 12:57:18','com.minh.nguyen.service.JudgeService','0',NULL),(163,'5 4','9','9','Đúng',6,80,0,'com.minh.nguyen.service.JudgeService','user','2018-02-14 12:57:18','user','2018-02-14 12:57:18','com.minh.nguyen.service.JudgeService','0',NULL),(164,'1 2',NULL,'3','Lỗi runtime',0,99,0,'com.minh.nguyen.service.JudgeService','admin','2018-02-14 13:30:45','admin','2018-02-14 13:30:45','com.minh.nguyen.service.JudgeService','0',NULL),(165,'1 2',NULL,'3','Lỗi runtime',0,248,0,'com.minh.nguyen.service.JudgeService','admin','2018-02-14 13:31:47','admin','2018-02-14 13:31:47','com.minh.nguyen.service.JudgeService','0',NULL),(166,'1 2','3','3','Đúng',6,117,0,'com.minh.nguyen.service.JudgeService','admin','2018-02-14 13:39:26','admin','2018-02-14 13:39:26','com.minh.nguyen.service.JudgeService','0',NULL),(167,'3 4','7','7','Đúng',6,244,0,'com.minh.nguyen.service.JudgeService','admin','2018-02-14 13:39:27','admin','2018-02-14 13:39:27','com.minh.nguyen.service.JudgeService','0',NULL),(168,'5 4','9','9','Đúng',6,144,0,'com.minh.nguyen.service.JudgeService','admin','2018-02-14 13:39:27','admin','2018-02-14 13:39:27','com.minh.nguyen.service.JudgeService','0',NULL),(169,'1 2',NULL,'3','Lỗi runtime',0,186,0,'com.minh.nguyen.service.JudgeService','admin','2018-02-14 13:39:50','admin','2018-02-14 13:39:50','com.minh.nguyen.service.JudgeService','0',NULL),(170,'5 10','15','15','Đúng',6,180,0,'com.minh.nguyen.service.JudgeService','admin','2018-02-14 13:44:02','admin','2018-02-14 13:44:02','com.minh.nguyen.service.JudgeService','0',NULL),(171,'123 1','124','124\r\n','Đúng',6,142,0,'com.minh.nguyen.service.JudgeService','admin','2018-02-14 13:44:02','admin','2018-02-14 13:44:02','com.minh.nguyen.service.JudgeService','0',NULL),(172,'1 2','3','3','Đúng',6,107,0,'com.minh.nguyen.service.JudgeService','admin','2018-02-14 13:44:02','admin','2018-02-14 13:44:02','com.minh.nguyen.service.JudgeService','0',NULL),(173,'5 10','15','15','Đúng',6,177,0,'com.minh.nguyen.service.JudgeService','admin','2018-02-14 13:54:54','admin','2018-02-14 13:54:54','com.minh.nguyen.service.JudgeService','0',NULL),(174,'123 1','124','124\r\n','Đúng',6,119,0,'com.minh.nguyen.service.JudgeService','admin','2018-02-14 13:54:54','admin','2018-02-14 13:54:54','com.minh.nguyen.service.JudgeService','0',NULL),(175,'1 2','3','3','Đúng',6,105,0,'com.minh.nguyen.service.JudgeService','admin','2018-02-14 13:54:54','admin','2018-02-14 13:54:54','com.minh.nguyen.service.JudgeService','0',NULL),(176,'5 10','15','15','Đúng',6,126,0,'com.minh.nguyen.service.JudgeService','admin','2018-02-14 13:56:01','admin','2018-02-14 13:56:01','com.minh.nguyen.service.JudgeService','0',NULL),(177,'123 1','124','124\r\n','Đúng',6,121,0,'com.minh.nguyen.service.JudgeService','admin','2018-02-14 13:56:01','admin','2018-02-14 13:56:01','com.minh.nguyen.service.JudgeService','0',NULL),(178,'1 2','3','3','Đúng',6,116,0,'com.minh.nguyen.service.JudgeService','admin','2018-02-14 13:56:01','admin','2018-02-14 13:56:01','com.minh.nguyen.service.JudgeService','0',NULL),(179,NULL,NULL,NULL,NULL,0,0,0,'com.minh.nguyen.service.JudgeService','admin','2018-02-14 13:56:21','admin','2018-02-14 13:56:21','com.minh.nguyen.service.JudgeService','0',NULL),(180,'1 2','3','3','Đúng',6,160,0,'com.minh.nguyen.service.JudgeService','user','2018-02-14 14:15:01','user','2018-02-14 14:15:01','com.minh.nguyen.service.JudgeService','0',NULL),(181,'3 4','7','7','Đúng',6,175,0,'com.minh.nguyen.service.JudgeService','user','2018-02-14 14:15:02','user','2018-02-14 14:15:02','com.minh.nguyen.service.JudgeService','0',NULL),(182,'5 4','9','9','Đúng',6,112,0,'com.minh.nguyen.service.JudgeService','user','2018-02-14 14:15:02','user','2018-02-14 14:15:02','com.minh.nguyen.service.JudgeService','0',NULL),(183,'5 10','15','15','Đúng',6,111,0,'com.minh.nguyen.service.JudgeService','user','2018-02-14 14:15:16','user','2018-02-14 14:15:16','com.minh.nguyen.service.JudgeService','0',NULL),(184,'123 1','124','124\r\n','Đúng',6,101,0,'com.minh.nguyen.service.JudgeService','user','2018-02-14 14:15:16','user','2018-02-14 14:15:16','com.minh.nguyen.service.JudgeService','0',NULL),(185,'1 2','3','3','Đúng',6,175,0,'com.minh.nguyen.service.JudgeService','user','2018-02-14 14:15:16','user','2018-02-14 14:15:16','com.minh.nguyen.service.JudgeService','0',NULL),(186,NULL,NULL,NULL,'submission-snId-118-aman.cpp: In function \'int main()\':\r\nsubmission-snId-118-aman.cpp:6:10: error: no match for \'operator=\' (operand types are \'std::basic_ostream<char>\' and \'int\')\r\n cout << a= + b;\r\n          ^\r\nsubmission-snId-118-aman.cpp:6:10: note: candidate is:\r\nIn file included from C:/Program Files (x86)/CodeBlocks/MinGW/lib/gcc/mingw32/4.9.2/include/c++/istream:39:0,\r\n                 from C:/Program Files (x86)/CodeBlocks/MinGW/lib/gcc/mingw32/4.9.2/include/c++/sstream:38,\r\n                 from C:/Program Files (x86)/CodeBlocks/MinGW/lib/gcc/mingw32/4.9.2/include/c++/complex:45,\r\n                 from C:/Program Files (x86)/CodeBlocks/MinGW/lib/gcc/mingw32/4.9.2/include/c++/ccomplex:38,\r\n                 from C:/Program Files (x86)/CodeBlocks/MinGW/lib/gcc/mingw32/4.9.2/include/c++/mingw32/bits/stdc++.h:52,\r\nsubmission-snId-118-aman.cpp:1:\r\nC:/Program Files (x86)/CodeBlocks/MinGW/lib/gcc/mingw32/4.9.2/include/c++/ostream:58:11: note: std::basic_ostream<char>& std::basic_ostream<char>::operator=(const std::basic_ostream<char>&) <deleted>\r\n     class basic_ostream : virtual public basic_ios<_CharT, _Traits>\r\n           ^\r\nC:/Program Files (x86)/CodeBlocks/MinGW/lib/gcc/mingw32/4.9.2/include/c++/ostream:58:11: note:   no known conversion for argument 1 from \'int\' to \'const std::basic_ostream<char>&\'\r',0,0,0,'com.minh.nguyen.service.JudgeService','user','2018-02-14 15:46:37','user','2018-02-14 15:46:37','com.minh.nguyen.service.JudgeService','0',NULL),(187,NULL,NULL,NULL,NULL,0,0,0,'com.minh.nguyen.service.JudgeService','admin','2018-02-16 20:27:23','admin','2018-02-16 20:27:23','com.minh.nguyen.service.JudgeService','0',NULL),(188,'1 2','3','3','Đúng',6,88,0,'com.minh.nguyen.service.JudgeService','admin','2018-02-16 20:27:33','admin','2018-02-16 20:27:33','com.minh.nguyen.service.JudgeService','0',NULL),(189,'3 4','7','7','Đúng',6,169,0,'com.minh.nguyen.service.JudgeService','admin','2018-02-16 20:27:34','admin','2018-02-16 20:27:34','com.minh.nguyen.service.JudgeService','0',NULL),(190,'5 4','9','9','Đúng',6,194,0,'com.minh.nguyen.service.JudgeService','admin','2018-02-16 20:27:34','admin','2018-02-16 20:27:34','com.minh.nguyen.service.JudgeService','0',NULL),(191,'1 2','3','3','Đúng',6,104,0,'com.minh.nguyen.service.JudgeService','admin','2018-02-16 20:35:05','admin','2018-02-16 20:35:05','com.minh.nguyen.service.JudgeService','0',NULL),(192,'3 4','7','7','Đúng',6,106,0,'com.minh.nguyen.service.JudgeService','admin','2018-02-16 20:35:05','admin','2018-02-16 20:35:05','com.minh.nguyen.service.JudgeService','0',NULL),(193,'5 4','9','9','Đúng',6,328,0,'com.minh.nguyen.service.JudgeService','admin','2018-02-16 20:35:06','admin','2018-02-16 20:35:06','com.minh.nguyen.service.JudgeService','0',NULL),(194,'1 2',NULL,'3','Lỗi runtime',0,56,0,'com.minh.nguyen.service.JudgeService','user','2018-02-24 21:44:43','user','2018-02-24 21:44:43','com.minh.nguyen.service.JudgeService','0',NULL),(195,'3 4',NULL,'7','Lỗi runtime',0,38,0,'com.minh.nguyen.service.JudgeService','user','2018-02-24 21:44:43','user','2018-02-24 21:44:43','com.minh.nguyen.service.JudgeService','0',NULL),(196,'5 4',NULL,'9','Lỗi runtime',0,24,0,'com.minh.nguyen.service.JudgeService','user','2018-02-24 21:44:43','user','2018-02-24 21:44:43','com.minh.nguyen.service.JudgeService','0',NULL),(197,'1 2',NULL,'3','Lỗi runtime',0,55,0,'com.minh.nguyen.service.JudgeService','user','2018-02-24 21:45:15','user','2018-02-24 21:45:15','com.minh.nguyen.service.JudgeService','0',NULL),(198,'3 4',NULL,'7','Lỗi runtime',0,66,0,'com.minh.nguyen.service.JudgeService','user','2018-02-24 21:45:15','user','2018-02-24 21:45:15','com.minh.nguyen.service.JudgeService','0',NULL),(199,'5 4',NULL,'9','Lỗi runtime',0,41,0,'com.minh.nguyen.service.JudgeService','user','2018-02-24 21:45:15','user','2018-02-24 21:45:15','com.minh.nguyen.service.JudgeService','0',NULL),(200,'1 2',NULL,'3','Lỗi runtime',0,33,0,'com.minh.nguyen.service.JudgeService','user','2018-02-24 21:45:50','user','2018-02-24 21:45:50','com.minh.nguyen.service.JudgeService','0',NULL),(201,'3 4',NULL,'7','Lỗi runtime',0,58,0,'com.minh.nguyen.service.JudgeService','user','2018-02-24 21:45:51','user','2018-02-24 21:45:51','com.minh.nguyen.service.JudgeService','0',NULL),(202,'5 4',NULL,'9','Lỗi runtime',0,34,0,'com.minh.nguyen.service.JudgeService','user','2018-02-24 21:45:51','user','2018-02-24 21:45:51','com.minh.nguyen.service.JudgeService','0',NULL),(203,'1 2',NULL,'3','Lỗi runtime',0,15,0,'com.minh.nguyen.service.JudgeService','user','2018-02-24 21:46:43','user','2018-02-24 21:46:43','com.minh.nguyen.service.JudgeService','0',NULL),(204,'3 4',NULL,'7','Lỗi runtime',0,15,0,'com.minh.nguyen.service.JudgeService','user','2018-02-24 21:46:43','user','2018-02-24 21:46:43','com.minh.nguyen.service.JudgeService','0',NULL),(205,'5 4',NULL,'9','Lỗi runtime',0,15,0,'com.minh.nguyen.service.JudgeService','user','2018-02-24 21:46:43','user','2018-02-24 21:46:43','com.minh.nguyen.service.JudgeService','0',NULL),(206,'1 2',NULL,'3','Lỗi runtime',0,67,0,'com.minh.nguyen.service.JudgeService','admin','2018-02-25 21:01:36','admin','2018-02-25 21:01:36','com.minh.nguyen.service.JudgeService','0',NULL),(207,'3 4',NULL,'7','Lỗi runtime',0,29,0,'com.minh.nguyen.service.JudgeService','admin','2018-02-25 21:01:36','admin','2018-02-25 21:01:36','com.minh.nguyen.service.JudgeService','0',NULL),(208,'5 4',NULL,'9','Lỗi runtime',0,38,0,'com.minh.nguyen.service.JudgeService','admin','2018-02-25 21:01:36','admin','2018-02-25 21:01:36','com.minh.nguyen.service.JudgeService','0',NULL),(209,'1 2',NULL,'3','Lỗi runtime',0,43,0,'com.minh.nguyen.service.JudgeService','admin','2018-02-25 21:01:43','admin','2018-02-25 21:01:43','com.minh.nguyen.service.JudgeService','0',NULL),(210,'3 4',NULL,'7','Lỗi runtime',0,26,0,'com.minh.nguyen.service.JudgeService','admin','2018-02-25 21:01:43','admin','2018-02-25 21:01:43','com.minh.nguyen.service.JudgeService','0',NULL),(211,'5 4',NULL,'9','Lỗi runtime',0,24,0,'com.minh.nguyen.service.JudgeService','admin','2018-02-25 21:01:43','admin','2018-02-25 21:01:43','com.minh.nguyen.service.JudgeService','0',NULL),(212,'1 2','3','3','Đúng',6,32,0,'com.minh.nguyen.service.JudgeService','admin','2018-02-25 21:04:27','admin','2018-02-25 21:04:27','com.minh.nguyen.service.JudgeService','0',NULL),(213,'3 4','7','7','Đúng',6,23,0,'com.minh.nguyen.service.JudgeService','admin','2018-02-25 21:04:27','admin','2018-02-25 21:04:27','com.minh.nguyen.service.JudgeService','0',NULL),(214,'5 4','9','9','Đúng',6,22,0,'com.minh.nguyen.service.JudgeService','admin','2018-02-25 21:04:27','admin','2018-02-25 21:04:27','com.minh.nguyen.service.JudgeService','0',NULL);
/*!40000 ALTER TABLE `submitdetail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `supervisor`
--

DROP TABLE IF EXISTS `supervisor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `supervisor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL,
  `degree` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`,`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `supervisor`
--

LOCK TABLES `supervisor` WRITE;
/*!40000 ALTER TABLE `supervisor` DISABLE KEYS */;
/*!40000 ALTER TABLE `supervisor` ENABLE KEYS */;
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
-- Table structure for table `tbl_material`
--

DROP TABLE IF EXISTS `tbl_material`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_material` (
  `material_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `material_name` varchar(45) DEFAULT NULL,
  `material_stored_address` text,
  `material_upload_date` varchar(45) DEFAULT NULL,
  `material_uploader_id` int(11) DEFAULT NULL,
  `material_download_count` int(11) DEFAULT '0',
  `material_downloader_id` text,
  `material_description` text,
  `material_is_deleted` int(11) DEFAULT NULL,
  `createClass` varchar(100) DEFAULT NULL,
  `createUser` varchar(30) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `updateUser` varchar(45) DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `updateClass` varchar(45) DEFAULT NULL,
  `deleteFlg` varchar(1) DEFAULT '0',
  `deleteTime` datetime DEFAULT NULL,
  PRIMARY KEY (`material_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_material`
--

LOCK TABLES `tbl_material` WRITE;
/*!40000 ALTER TABLE `tbl_material` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_material` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_notification`
--

DROP TABLE IF EXISTS `tbl_notification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_notification` (
  `notification_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `notification_reciever_id` int(11) DEFAULT NULL,
  `notification_type` int(11) DEFAULT NULL,
  `notification_application_id` int(11) DEFAULT NULL,
  `notification_message_id` int(11) DEFAULT NULL,
  `notification_contest_id` int(11) DEFAULT NULL,
  `notification_announcement_id` int(11) DEFAULT NULL,
  `notification_survey_id` int(11) DEFAULT NULL,
  `notification_description` text,
  `notification_id_read` int(11) DEFAULT '0',
  `createClass` varchar(100) DEFAULT NULL,
  `createUser` varchar(30) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `updateUser` varchar(45) DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `updateClass` varchar(45) DEFAULT NULL,
  `deleteFlg` varchar(1) DEFAULT '0',
  `deleteTime` datetime DEFAULT NULL,
  PRIMARY KEY (`notification_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_notification`
--

LOCK TABLES `tbl_notification` WRITE;
/*!40000 ALTER TABLE `tbl_notification` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_notification` ENABLE KEYS */;
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
-- Table structure for table `ur_ce_auy`
--

DROP TABLE IF EXISTS `ur_ce_auy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ur_ce_auy` (
  `urId` int(11) NOT NULL,
  `ceId` int(11) NOT NULL,
  `ayId` int(11) NOT NULL,
  `createClass` varchar(100) DEFAULT NULL,
  `createUser` varchar(30) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `updateUser` varchar(45) DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `updateClass` varchar(45) DEFAULT NULL,
  `deleteFlg` varchar(1) DEFAULT '0',
  `deleteTime` datetime DEFAULT NULL,
  PRIMARY KEY (`urId`,`ayId`,`ceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ur_ce_auy`
--

LOCK TABLES `ur_ce_auy` WRITE;
/*!40000 ALTER TABLE `ur_ce_auy` DISABLE KEYS */;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ur_cn`
--

LOCK TABLES `ur_cn` WRITE;
/*!40000 ALTER TABLE `ur_cn` DISABLE KEYS */;
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
-- Table structure for table `ur_gt`
--

DROP TABLE IF EXISTS `ur_gt`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ur_gt` (
  `gtId` int(11) NOT NULL AUTO_INCREMENT,
  `urId` int(11) NOT NULL,
  PRIMARY KEY (`gtId`,`urId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ur_gt`
--

LOCK TABLES `ur_gt` WRITE;
/*!40000 ALTER TABLE `ur_gt` DISABLE KEYS */;
/*!40000 ALTER TABLE `ur_gt` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ur_me`
--

DROP TABLE IF EXISTS `ur_me`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ur_me` (
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ur_me`
--

LOCK TABLES `ur_me` WRITE;
/*!40000 ALTER TABLE `ur_me` DISABLE KEYS */;
/*!40000 ALTER TABLE `ur_me` ENABLE KEYS */;
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
  `accountType` int(11) DEFAULT NULL,
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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'admin','$2a$10$uZ.FzqENy9bWinvCb66DF.JjnTD/G3wH3GaqSv.8Z7GXv8LrEgEuy','MakiseKurisu',NULL,NULL,NULL,NULL,NULL,1,0,0,0,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL),(2,'user','$2a$10$uZ.FzqENy9bWinvCb66DF.JjnTD/G3wH3GaqSv.8Z7GXv8LrEgEuy','Okabe',NULL,NULL,NULL,NULL,NULL,3,0,0,0,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL),(3,'student','$2a$10$uZ.FzqENy9bWinvCb66DF.JjnTD/G3wH3GaqSv.8Z7GXv8LrEgEuy','Haru',NULL,NULL,NULL,NULL,NULL,3,0,0,0,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL),(4,'lecturer','$2a$10$uZ.FzqENy9bWinvCb66DF.JjnTD/G3wH3GaqSv.8Z7GXv8LrEgEuy','Suzuha',NULL,NULL,NULL,NULL,NULL,2,0,0,0,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL),(5,'supervisor','$2a$10$uZ.FzqENy9bWinvCb66DF.JjnTD/G3wH3GaqSv.8Z7GXv8LrEgEuy','Ferris',NULL,NULL,NULL,NULL,NULL,4,0,0,0,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL);
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

-- Dump completed on 2018-03-08  0:54:27
