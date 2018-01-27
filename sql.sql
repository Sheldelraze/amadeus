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
  `problemName` varchar(45) DEFAULT NULL,
  `question` varchar(200) DEFAULT NULL,
  `answer` varchar(200) DEFAULT NULL,
  `timePosted` datetime DEFAULT NULL,
  `isHidden` int(11) DEFAULT '0',
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
-- Dumping data for table `announcement`
--

LOCK TABLES `announcement` WRITE;
/*!40000 ALTER TABLE `announcement` DISABLE KEYS */;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `authority`
--

LOCK TABLES `authority` WRITE;
/*!40000 ALTER TABLE `authority` DISABLE KEYS */;
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
-- Table structure for table `ce_qn`
--

DROP TABLE IF EXISTS `ce_qn`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ce_qn` (
  `ceId` int(11) NOT NULL,
  `qnId` int(11) NOT NULL,
  `createClass` varchar(100) DEFAULT NULL,
  `createUser` varchar(30) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `updateUser` varchar(45) DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `updateClass` varchar(45) DEFAULT NULL,
  `deleteFlg` varchar(1) DEFAULT '0',
  `deleteTime` datetime DEFAULT NULL,
  PRIMARY KEY (`ceId`,`qnId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ce_qn`
--

LOCK TABLES `ce_qn` WRITE;
/*!40000 ALTER TABLE `ce_qn` DISABLE KEYS */;
/*!40000 ALTER TABLE `ce_qn` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ce_ur_sn`
--

DROP TABLE IF EXISTS `ce_ur_sn`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ce_ur_sn` (
  `ceId` int(11) NOT NULL,
  `urId` int(11) NOT NULL,
  `snId` int(11) NOT NULL,
  `createClass` varchar(100) DEFAULT NULL,
  `createUser` varchar(30) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `updateUser` varchar(45) DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `updateClass` varchar(45) DEFAULT NULL,
  `deleteFlg` varchar(1) DEFAULT '0',
  `deleteTime` datetime DEFAULT NULL,
  PRIMARY KEY (`ceId`,`urId`,`snId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ce_ur_sn`
--

LOCK TABLES `ce_ur_sn` WRITE;
/*!40000 ALTER TABLE `ce_ur_sn` DISABLE KEYS */;
/*!40000 ALTER TABLE `ce_ur_sn` ENABLE KEYS */;
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
  `requirement` text,
  `prize` text,
  `showTest` int(11) DEFAULT NULL,
  `showSubmit` int(11) DEFAULT NULL,
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
-- Dumping data for table `contest`
--

LOCK TABLES `contest` WRITE;
/*!40000 ALTER TABLE `contest` DISABLE KEYS */;
INSERT INTO `contest` VALUES (1,'123123',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL),(2,'123','2018-02-01 07:48:00',123,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(3,'12312333','2018-01-26 07:50:00',13333,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `contest` ENABLE KEYS */;
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
  `solveCnt` int(11) DEFAULT '0',
  `firstSolve` datetime DEFAULT NULL,
  `totoalSubmission` int(11) DEFAULT '0',
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
/*!40000 ALTER TABLE `ct_pm` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ct_qn`
--

DROP TABLE IF EXISTS `ct_qn`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ct_qn` (
  `ctId` int(11) NOT NULL,
  `qnId` int(11) NOT NULL,
  `createClass` varchar(100) DEFAULT NULL,
  `createUser` varchar(30) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `updateUser` varchar(45) DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `updateClass` varchar(45) DEFAULT NULL,
  `deleteFlg` varchar(1) DEFAULT '0',
  `deleteTime` datetime DEFAULT NULL,
  PRIMARY KEY (`ctId`,`qnId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ct_qn`
--

LOCK TABLES `ct_qn` WRITE;
/*!40000 ALTER TABLE `ct_qn` DISABLE KEYS */;
/*!40000 ALTER TABLE `ct_qn` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ct_ur_sn`
--

DROP TABLE IF EXISTS `ct_ur_sn`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ct_ur_sn` (
  `ctId` int(11) NOT NULL,
  `urId` int(11) NOT NULL,
  `snId` int(11) NOT NULL,
  `createClass` varchar(100) DEFAULT NULL,
  `createUser` varchar(30) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `updateUser` varchar(45) DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `updateClass` varchar(45) DEFAULT NULL,
  `deleteFlg` varchar(1) DEFAULT '0',
  `deleteTime` datetime DEFAULT NULL,
  PRIMARY KEY (`ctId`,`urId`,`snId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ct_ur_sn`
--

LOCK TABLES `ct_ur_sn` WRITE;
/*!40000 ALTER TABLE `ct_ur_sn` DISABLE KEYS */;
/*!40000 ALTER TABLE `ct_ur_sn` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `input`
--

LOCK TABLES `input` WRITE;
/*!40000 ALTER TABLE `input` DISABLE KEYS */;
INSERT INTO `input` VALUES (13,'323','44234234',1,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-20 15:11:01','minh.nt','2018-01-20 15:11:01','class com.minh.nguyen.service.BaseService','0',NULL),(17,'3123123','4444',1,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-20 15:13:33','minh.nt','2018-01-20 15:13:33','class com.minh.nguyen.service.BaseService','0',NULL),(18,'1231321231231231\r\n2323 12asd asd   ad asdqweq w asfsadf ssd xcv xcv  ad asdqweq w asfsadf ssd xcv xcv 2asd asd   ad asdqweq w asfsadf ssd xcv xc\r\n12313212312312312323 12asd asd   ad asdqweq w asfsadf ssd xcv xcv  ad asdqweq w asfsadf ssd xcv xcv 2asd asd   ad asdqcsadf ssd xcv xc 12313212312312312323 12asd asd   ad asdqweq w asfsadf ssd xcv xcv  ad asdqweq w asfsadf ssd xcv xcv 2asd asd   ad asdqweq w asfsadf ssd xcv xc\r\n12313212312312312323 12asd asd   ad asdqweq w asfsadf ssd xcv xcv  ad asdqweq w asfsadf ssd xcv xcv 2asd asd   ad asdqweq w asfsadf ssd xcv xc 12313212312312312323 12asd asd   ad asdqweq w asfsadf ssd xcv xcv  ad asdqweq w asfsadf ssd xcv xcv 2asd asd   ad asdqweq w asfsadf ssd xcv xc\r\n12313212312312312323 12asd asd   ad asdqweq w asfsadf ssd xcv xcv  ad asdqweq w asfsadf ssd xcv xcv 2asd asd   ad asdqweq w asfsadf ssd xcv xc','414213\r\n12312312312323 12asd asd   ad asdqweq w asfsadf ssd xcv xcv  ad asdqweq w asfsadf ssd xcv xcv 2asd asd   ad asdqweq w asfsadf ssd xcv xcv  ad asdqweq w asfsadf ssd xcv xcv \r\nad asdqweq w asfsadf ssd xcv xcv \r\netert df df gdfg wre wet rt erdfgd \r\nwer wsdl slkdv mlcv\r\n',1,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-20 15:22:26','minh.nt','2018-01-20 15:22:26','class com.minh.nguyen.service.BaseService','0',NULL),(19,'4444\r\n4444 4444 4444 asdasd we weq asd asd xcx dwt et dhs dgh dfhg dfhrtyrtyrtyrty\r\n4444 4444 4444 asdasd we weq asd asd xcx dwt et dhs dgh dfhg dfhrtyrtyrtyrty','4444\r\n4444 4444 4444 asdasd we weq asd asd xcx dwt et dhs dgh dfhg dfhrtyrtyrtyrty\r\n4444 4444 4444 asdasd we weq asd asd xcx dwt et dhs dgh dfhg dfhrtyrtyrtyrty',1,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-20 15:22:29','minh.nt','2018-01-20 15:22:29','class com.minh.nguyen.service.BaseService','0',NULL),(20,'123456789123456789123456789123456789123456789123456789123456789123456789123456789123456789123456789123456789','4444',0,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-20 15:37:52','minh.nt','2018-01-20 15:37:52','class com.minh.nguyen.service.BaseService','1',NULL),(21,'hehe','1234',0,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-20 16:08:05','minh.nt','2018-01-20 16:08:05','class com.minh.nguyen.service.BaseService','1',NULL),(22,'123','123',0,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-21 12:40:50','minh.nt','2018-01-21 12:40:50','class com.minh.nguyen.service.BaseService','0',NULL);
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
INSERT INTO `language` VALUES (1,'C++11','cpp',NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL),(2,'Java','java',NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL),(3,'C','c',NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL);
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
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `content` varchar(200) NOT NULL,
  `sentTime` datetime NOT NULL,
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
-- Dumping data for table `message`
--

LOCK TABLES `message` WRITE;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
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
INSERT INTO `pm_it` VALUES (10,18,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-20 15:22:26','minh.nt','2018-01-20 15:22:26','class com.minh.nguyen.service.BaseService','0',NULL),(10,19,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-20 15:22:29','minh.nt','2018-01-20 15:22:29','class com.minh.nguyen.service.BaseService','0',NULL),(10,20,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-20 15:37:52','minh.nt','2018-01-20 15:37:52','class com.minh.nguyen.service.BaseService','0',NULL),(10,21,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-20 16:08:05','minh.nt','2018-01-20 16:08:05','class com.minh.nguyen.service.BaseService','0',NULL),(10,22,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-21 12:40:50','minh.nt','2018-01-21 12:40:50','class com.minh.nguyen.service.BaseService','0',NULL);
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
  `sourceCode` text,
  `language` varchar(45) DEFAULT NULL,
  `timeLimit` int(11) DEFAULT '2000',
  `memoryLimit` int(11) DEFAULT '64',
  `isPublished` int(11) DEFAULT '0',
  `difficulty` int(11) DEFAULT '1',
  `createClass` varchar(100) DEFAULT NULL,
  `createUser` varchar(30) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `updateUser` varchar(45) DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `updateClass` varchar(45) DEFAULT NULL,
  `deleteFlg` varchar(1) DEFAULT '0',
  `deleteTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `problem`
--

LOCK TABLES `problem` WRITE;
/*!40000 ALTER TABLE `problem` DISABLE KEYS */;
INSERT INTO `problem` VALUES (10,'aman','Lịch sử Việt Nam','                                                                                                                                    Con số 198 có gợi cho bạn điều gì không? Khi học lịch sử Việt \r\nNam, Vinh biết rằng ngày 19-8-1945 là ngày <font color=\"#00FF00\">Tổng khởi nghĩa, \r\nngày nhân dân cả nước ta nhất tề đứng lên làm cuộc Cách mạng \r\nTháng Tám vĩ đại. Hiện nay, 198 được đặt tên cho nhiều bệnh \r\nviện, công viên, đường phố trong cả nước. Con số này đã gợi ý \r\ncho Vinh khảo sát dãy số SEQ98 sau đây: Dãy số nguyên không âm a<sub>1</sub>, a<sub>2</sub>, ..., a<sub>n</sub> được gọi là dãy SEQ198 nếu không tồn tại hai chỉ số i và j (1 ≤ i,j ≤ n) mà a<sub>i</sub>-a<sub>j</sub> hoặc là bằng 1 hoặc là bằng 8 hoặc là bằng 9.</font><br><div><br></div><div><b><u>Ví dụ</u></b>:</div><div><ul><li>Dãy số nguyên 1, 3, 5, 7 là dãy SEQ123</li><li>Dãy\r\n số nguyên 7, 3, 5, 1, 9, 21 không phải là dãy SEQ198 bởi vì có \r\nhai phần tử 1 và 9 có hiệu 9 - 1 = 8. Tuy nhiên, sau khi xóa bớt \r\nphần tử 1, ta thu được dãy 7, 3, 5, 9, 21 là một dãy SEQ198.</li><li>Vinh quan tâm tới bài toán sau đây: Cho dãy số nguyên không âm b<sub>1</sub>, b<sub>2</sub>, ..., b<sub>m</sub>, hãy tìm cách loại bỏ một số ít nhất phần tử của dãy để được dãy còn lại là SEQ198.</li></ul></div>\r\n                                        \r\n                                        \r\n                                        ','                                                                                                                                    <div><ul><li>Dòng đầu chứa số nguyên dương m;</li><li>Dòng thứ hai chứa m số nguyên không âm b<sub>1</sub>, b<sub>2</sub>, ..., b<sub>m</sub> (b<sub>i</sub> ≤ 109).</li></ul></div>\r\n                                        \r\n                                        \r\n                                        ','                                                                                                                                    Ghi ra số nguyên k là số phần tử bị loại bỏ. Ghi số 0 nếu dãy đã cho là SEQ198.\r\n                                        \r\n                                        \r\n                                        ','                                                                                        Giải thích: với bất cứ các làm nào bạn đều nhận được chuỗi&nbsp;&nbsp;<code style=\"padding: 0px; font-family: Menlo, Monaco, \">xxxxxxxxxx</code><span style=\"background-color: rgb(255, 255, 255);\">.</span>\r\n                                        \r\n                                        ','#include <bits/stdc++.h>\r\nusing namespace std;\r\nint main(){\r\n    int a,b;\r\n    cin >> a >> b;\r\n    cout << a + b;\r\n}\r\n                            ','cpp',5546,233,1,4,'class com.minh.nguyen.service.BaseService','minh.nt','2018-01-13 10:23:48','minh.nt','2018-01-26 19:57:34','class com.minh.nguyen.service.BaseService','0',NULL);
/*!40000 ALTER TABLE `problem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `question`
--

DROP TABLE IF EXISTS `question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `question` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(45) DEFAULT NULL,
  `timePosted` datetime DEFAULT NULL,
  `isAnswered` int(11) DEFAULT NULL,
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
-- Dumping data for table `question`
--

LOCK TABLES `question` WRITE;
/*!40000 ALTER TABLE `question` DISABLE KEYS */;
/*!40000 ALTER TABLE `question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `re_auy`
--

DROP TABLE IF EXISTS `re_auy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `re_auy` (
  `ayId` int(11) NOT NULL,
  `reId` varchar(45) NOT NULL,
  `createClass` varchar(100) DEFAULT NULL,
  `createUser` varchar(30) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `updateUser` varchar(45) DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `updateClass` varchar(45) DEFAULT NULL,
  `deleteFlg` varchar(1) DEFAULT '0',
  `deleteTime` datetime DEFAULT NULL,
  PRIMARY KEY (`ayId`,`reId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `re_auy`
--

LOCK TABLES `re_auy` WRITE;
/*!40000 ALTER TABLE `re_auy` DISABLE KEYS */;
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
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
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
  `time` datetime DEFAULT NULL,
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `submission`
--

LOCK TABLES `submission` WRITE;
/*!40000 ALTER TABLE `submission` DISABLE KEYS */;
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
  `result` varchar(300) DEFAULT NULL,
  `createClass` varchar(100) DEFAULT NULL,
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
-- Dumping data for table `submitdetail`
--

LOCK TABLES `submitdetail` WRITE;
/*!40000 ALTER TABLE `submitdetail` DISABLE KEYS */;
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
-- Table structure for table `ur_ct_auy`
--

DROP TABLE IF EXISTS `ur_ct_auy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ur_ct_auy` (
  `urId` int(11) NOT NULL,
  `ctId` int(11) NOT NULL,
  `ayId` int(11) NOT NULL,
  `createClass` varchar(100) DEFAULT NULL,
  `createUser` varchar(30) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `updateUser` varchar(45) DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `updateClass` varchar(45) DEFAULT NULL,
  `deleteFlg` varchar(1) DEFAULT '0',
  `deleteTime` datetime DEFAULT NULL,
  PRIMARY KEY (`urId`,`ctId`,`ayId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ur_ct_auy`
--

LOCK TABLES `ur_ct_auy` WRITE;
/*!40000 ALTER TABLE `ur_ct_auy` DISABLE KEYS */;
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
  `ayId` int(11) NOT NULL,
  `urId` int(11) NOT NULL,
  `createClass` varchar(100) DEFAULT NULL,
  `createUser` varchar(30) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `updateUser` varchar(45) DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `updateClass` varchar(45) DEFAULT NULL,
  `deleteFlg` varchar(1) DEFAULT '0',
  `deleteTime` datetime DEFAULT NULL,
  PRIMARY KEY (`pmId`,`ayId`,`urId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ur_pm_auy`
--

LOCK TABLES `ur_pm_auy` WRITE;
/*!40000 ALTER TABLE `ur_pm_auy` DISABLE KEYS */;
/*!40000 ALTER TABLE `ur_pm_auy` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ur_re`
--

DROP TABLE IF EXISTS `ur_re`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ur_re` (
  `urId` int(11) NOT NULL,
  `reId` int(11) NOT NULL,
  `createClass` varchar(100) DEFAULT NULL,
  `createUser` varchar(30) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `updateUser` varchar(45) DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `updateClass` varchar(45) DEFAULT NULL,
  `deleteFlg` varchar(1) DEFAULT '0',
  `deleteTime` datetime DEFAULT NULL,
  PRIMARY KEY (`urId`,`reId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ur_re`
--

LOCK TABLES `ur_re` WRITE;
/*!40000 ALTER TABLE `ur_re` DISABLE KEYS */;
/*!40000 ALTER TABLE `ur_re` ENABLE KEYS */;
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
  `password` varchar(45) DEFAULT NULL,
  `fullname` varchar(45) DEFAULT NULL,
  `phoneNumber` varchar(45) DEFAULT NULL,
  `emailAddress` varchar(45) DEFAULT NULL,
  `dateOfBirth` varchar(45) DEFAULT NULL,
  `avatar` varchar(200) DEFAULT NULL,
  `description` varchar(1000) DEFAULT NULL,
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
  UNIQUE KEY `user_id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
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

-- Dump completed on 2018-01-27 12:40:34
