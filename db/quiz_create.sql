CREATE DATABASE  IF NOT EXISTS `mydb` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `mydb`;
-- MySQL dump 10.13  Distrib 8.0.26, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: mydb
-- ------------------------------------------------------
-- Server version	8.0.26

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `answer`
--

DROP TABLE IF EXISTS `answer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `answer` (
  `id` int NOT NULL AUTO_INCREMENT,
  `question_id` int NOT NULL,
  `answer` tinyint NOT NULL,
  `description` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_answer_question1_idx` (`question_id`),
  CONSTRAINT `fk_answer_question1` FOREIGN KEY (`question_id`) REFERENCES `question` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=272 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `answer`
--

LOCK TABLES `answer` WRITE;
/*!40000 ALTER TABLE `answer` DISABLE KEYS */;
INSERT INTO `answer` VALUES (28,13,1,'параллелограмма'),(29,13,0,'прямоугольника'),(30,13,0,'квадрата'),(31,13,0,'трапеции'),(32,14,0,'7'),(33,14,0,'49'),(34,14,1,'14'),(35,14,0,'21'),(36,15,0,'30'),(37,15,0,'45'),(38,15,1,'60'),(39,15,0,'70'),(40,16,0,'230'),(41,16,1,'324'),(42,16,0,'170'),(43,16,0,'160'),(44,17,0,'echo(\"Hello World\");'),(45,17,1,'System.out.println(\"Hello World\");'),(46,17,0,'print (\"Hello World\");'),(47,17,0,'Console.WriteLine(\"Hello World\");'),(48,18,1,'False'),(49,18,0,'True'),(50,19,0,'# This is a comment'),(51,19,1,'// This is a comment'),(52,19,0,'/* This is a comment'),(53,20,1,'String'),(54,20,0,'myString'),(55,20,0,'Txt'),(56,20,0,'string'),(57,21,1,'int x = 5;'),(58,21,0,'float x = 5;'),(59,21,0,'x = 5;'),(60,21,0,'num x = 5'),(229,98,1,'12'),(230,98,1,'21'),(234,100,0,'1'),(235,100,1,'5'),(236,100,0,'10'),(237,101,1,'8'),(238,101,0,'5'),(239,101,0,'10'),(240,102,0,'8'),(241,102,0,'5'),(242,102,1,'10'),(250,107,0,'false'),(251,107,1,'true'),(252,107,1,'true'),(255,110,0,'3'),(256,110,0,'4'),(257,110,1,'5'),(258,110,0,'6'),(259,111,1,'20 см'),(260,111,0,'100π см'),(261,111,0,'10 см'),(262,111,0,'20π см'),(263,112,0,'y = x -1 '),(264,112,0,'y = 1 - x'),(265,112,0,'y = 1'),(266,112,1,'y = x'),(267,113,0,'13 см'),(268,113,0,'12 см'),(269,113,1,'9 см'),(270,113,0,'8 см'),(271,113,0,'6 см');
/*!40000 ALTER TABLE `answer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `question`
--

DROP TABLE IF EXISTS `question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `question` (
  `id` int NOT NULL AUTO_INCREMENT,
  `quiz_id` int NOT NULL,
  `description` varchar(500) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_question_test_idx` (`quiz_id`),
  CONSTRAINT `fk_question_test` FOREIGN KEY (`quiz_id`) REFERENCES `quiz` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=114 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question`
--

LOCK TABLES `question` WRITE;
/*!40000 ALTER TABLE `question` DISABLE KEYS */;
INSERT INTO `question` VALUES (13,7,'Ромб обладает всеми свойствами...'),(14,7,'В квадрате ABCD диагонали пересекаются в точке O. AO=7см чему равна диагональ BD?'),(15,7,'Величина одного из углов прямоугольной трапеции равна 120º. Найдите острый угол этой трапеции.'),(16,7,'В равнобедренной трапеции диагонали взаимно перпендикулярны, высота трапеции равна 18 см. Найдите площадь трапеции.'),(17,8,'What is a correct syntax to output \"Hello World\" in Java?'),(18,8,'Java is short for \"JavaScript\".'),(19,8,'How do you insert COMMENTS in Java code?'),(20,8,'Which data type is used to create a variable that should store text?'),(21,8,'How do you create a variable with the numeric value 5?'),(98,66,'1 question'),(100,6,'2+3=?'),(101,6,'5+3=?'),(102,6,'5+5=?'),(107,63,'Present simple'),(110,64,'У під\'їзді шістнадцятиповерхового будинку на першому поверсі розташовано 6 квартир, а на кожному з решти поверхів – по 8. На якому поверсі квартира № 31, якщо квартири від № 1 і далі пронумеровано послідовно від першого до останнього поверху?'),(111,64,'Точки  А та  В лежать на сфері радіуса 10 см. Укажіть найбільше можливе значення довжини відрізка АВ'),(112,64,'Укажіть функцію, графік якої проходить через початок координат.'),(113,64,'Визначте довжину апофеми правильної чотирикутної піраміди, якщо площа її повної поверхні дорівнює 208 см2, а довжина сторони основи - 8 см.');
/*!40000 ALTER TABLE `question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `quiz`
--

DROP TABLE IF EXISTS `quiz`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `quiz` (
  `id` int NOT NULL AUTO_INCREMENT,
  `difficult` enum('easy','medium','hard') NOT NULL,
  `duration` int NOT NULL,
  `topic_id` int NOT NULL,
  `description` text NOT NULL,
  `header` varchar(40) NOT NULL,
  `create_date` datetime NOT NULL,
  `archived` tinyint DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `test_header_uindex` (`header`),
  KEY `fk_test_topic1_idx` (`topic_id`),
  CONSTRAINT `fk_test_topic1` FOREIGN KEY (`topic_id`) REFERENCES `topic` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=69 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `quiz`
--

LOCK TABLES `quiz` WRITE;
/*!40000 ALTER TABLE `quiz` DISABLE KEYS */;
INSERT INTO `quiz` VALUES (6,'easy',20,1,'Algebra is a branch of mathematics dealing with symbols and the rules for manipulating those symbols. In elementary algebra, those symbols (today written as Latin and Greek letters) represent quantities without fixed values, known as variables. Just as sentences describe relationships between specific words, in algebra, equations describe relationships between variables.\r\n                ','Algebra','2021-09-15 23:57:09',0),(7,'medium',10,1,'Geometry, the branch of mathematics concerned with the shape of individual objects, spatial relationships among various objects, and the properties of surrounding space. It is one of the oldest branches of mathematics, having arisen in response to such practical problems as those found in surveying, and its name is derived from Greek words meaning “Earth measurement.” Eventually it was realized that geometry need not be limited to the study of flat surfaces (plane geometry) and rigid three-dimensional objects (solid geometry) but that even the most abstract thoughts and images might be represented and developed in geometric terms.\n\n','Geometry','2021-09-11 23:57:10',0),(8,'hard',10,2,'Java is a high-level, class-based, object-oriented programming language that is designed to have as few implementation dependencies as possible. It is a general-purpose programming language intended to let application developers write once, run anywhere (WORA), meaning that compiled Java code can run on all platforms that support Java without the need for recompilation. Java applications are typically compiled to bytecode that can run on any Java virtual machine (JVM) regardless of the underlying computer architecture. The syntax of Java is similar to C and C++, but has fewer low-level facilities than either of them. The Java runtime provides dynamic capabilities (such as reflection and runtime code modification) that are typically not available in traditional compiled languages. As of 2019, Java was one of the most popular programming languages in use according to GitHub, particularly for client-server web applications, with a reported 9 million developers.','Java','2021-09-08 23:57:10',0),(63,'medium',11,3,'english quiz  ','English','2021-09-28 19:12:23',0),(64,'hard',150,4,'Зо́внішнє незале́жне оці́нювання (ЗНО, раніше також Зовнішнє тестування, ЗТ) — іспити для вступу до вишів в Україні.\r\n\r\nКомплекс організаційних процедур (передусім — тестування) спрямований на визначення рівня навчальних досягнень випускників середніх навчальних закладів при їхньому вступі до закладів вищої освіти.\r\n                ','ЗНО Математика','2021-09-28 19:48:04',0),(66,'medium',213,2,'dasfdghfkgjhfzdsa','aweawweeqwa','2021-10-01 02:58:05',1);
/*!40000 ALTER TABLE `quiz` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `result`
--

DROP TABLE IF EXISTS `result`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `result` (
  `id` int NOT NULL AUTO_INCREMENT,
  `score` int NOT NULL,
  `user_id` int NOT NULL,
  `complete_date` timestamp NOT NULL,
  `start_date` timestamp NOT NULL,
  `quiz_id` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_result_user1_idx` (`user_id`),
  KEY `fk_result_test1_idx` (`quiz_id`),
  CONSTRAINT `fk_result_test1` FOREIGN KEY (`quiz_id`) REFERENCES `quiz` (`id`),
  CONSTRAINT `fk_result_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=128 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `result`
--

LOCK TABLES `result` WRITE;
/*!40000 ALTER TABLE `result` DISABLE KEYS */;
INSERT INTO `result` VALUES (1,100,1,'2021-09-08 20:56:53','2021-09-05 23:03:18',6),(2,50,1,'2021-09-08 20:56:55','2021-09-08 18:59:13',6),(3,75,1,'2021-09-08 20:56:56','2021-09-08 18:59:16',6),(4,50,1,'2021-09-09 21:00:00','2021-09-08 18:59:17',7),(5,50,1,'2021-09-09 21:00:00','2021-09-08 18:59:19',7),(6,90,1,'2021-09-08 20:56:59','2021-09-08 18:59:20',8),(7,99,1,'2021-09-08 20:57:00','2021-09-08 18:59:21',8),(8,100,1,'2021-09-08 20:57:00','2021-09-08 18:59:22',8),(9,50,1,'2021-09-09 21:00:00','2021-09-09 21:00:00',7),(10,50,1,'2021-09-09 21:00:00','2021-09-09 21:00:00',7),(11,50,1,'2021-09-09 21:00:00','2021-09-09 21:00:00',7),(12,50,1,'2021-09-09 21:00:00','2021-09-09 21:00:00',7),(13,50,1,'2021-09-09 21:00:00','2021-09-09 21:00:00',7),(14,0,1,'2021-09-09 21:00:00','2021-09-09 21:00:00',8),(15,50,1,'2021-09-09 21:00:00','2021-09-09 21:00:00',7),(16,50,1,'2021-09-09 21:00:00','2021-09-09 21:00:00',7),(17,50,1,'2021-09-09 21:00:00','2021-09-09 21:00:00',7),(18,0,1,'2021-09-10 16:16:40','2021-09-10 13:42:59',7),(19,20,1,'2021-09-10 16:34:04','2021-09-10 13:47:43',8),(20,20,1,'2021-09-10 16:34:04','2021-09-10 13:58:33',8),(21,20,1,'2021-09-10 16:34:04','2021-09-10 13:59:27',8),(22,0,1,'2021-09-10 16:16:40','2021-09-10 13:59:46',7),(23,0,1,'2021-09-10 16:16:40','2021-09-10 13:59:51',7),(24,0,1,'2021-09-10 16:16:40','2021-09-10 15:40:41',7),(25,20,1,'2021-09-10 16:34:04','2021-09-10 15:41:49',8),(26,0,1,'2021-09-10 15:43:35','2021-09-10 15:42:34',6),(27,0,1,'2021-09-10 16:16:40','2021-09-10 15:51:43',7),(28,20,1,'2021-09-10 16:34:04','2021-09-10 16:12:12',8),(29,0,1,'2021-09-10 16:16:40','2021-09-10 16:13:31',7),(30,0,1,'2021-09-10 16:16:40','2021-09-10 16:15:38',7),(31,0,1,'2021-09-10 16:16:43','2021-09-10 16:16:43',7),(32,20,1,'2021-09-10 16:34:04','2021-09-10 16:33:50',8),(33,0,2,'2021-09-11 21:35:48','2021-09-11 21:35:48',7),(34,0,1,'2021-09-15 22:17:25','2021-09-15 22:17:25',7),(35,0,1,'2021-09-15 22:19:04','2021-09-15 22:19:04',7),(36,0,1,'2021-09-16 09:54:13','2021-09-16 09:54:13',7),(37,0,1,'2021-09-16 09:54:17','2021-09-16 09:54:17',7),(38,0,1,'2021-09-16 14:12:51','2021-09-16 14:12:51',7),(39,0,1,'2021-09-16 14:56:33','2021-09-16 14:56:33',8),(40,0,1,'2021-09-16 14:57:43','2021-09-16 14:57:43',7),(41,0,1,'2021-09-16 15:02:36','2021-09-16 15:02:36',7),(42,66,1,'2021-09-16 20:27:53','2021-09-16 20:03:34',6),(43,66,1,'2021-09-16 20:27:53','2021-09-16 20:09:16',6),(44,66,1,'2021-09-16 20:27:53','2021-09-16 20:11:05',6),(45,66,1,'2021-09-16 20:27:53','2021-09-16 20:12:23',6),(46,66,1,'2021-09-16 20:27:53','2021-09-16 20:14:16',6),(47,66,1,'2021-09-16 20:27:53','2021-09-16 20:25:05',6),(48,66,1,'2021-09-16 20:27:53','2021-09-16 20:26:35',6),(49,66,1,'2021-09-16 20:27:53','2021-09-16 20:27:46',6),(50,0,1,'2021-09-16 22:18:53','2021-09-16 22:18:50',6),(53,0,2,'2021-09-26 11:42:48','2021-09-26 11:42:48',7),(54,100,2,'2021-09-26 12:25:07','2021-09-26 11:46:01',8),(55,100,2,'2021-09-26 12:25:07','2021-09-26 11:50:43',8),(56,100,2,'2021-09-26 12:25:07','2021-09-26 11:59:48',8),(57,100,2,'2021-09-26 12:25:07','2021-09-26 12:01:37',8),(58,100,2,'2021-09-26 12:25:07','2021-09-26 12:01:57',8),(59,100,2,'2021-09-26 12:25:07','2021-09-26 12:24:57',8),(60,0,2,'2021-09-26 12:33:21','2021-09-26 12:33:21',8),(61,40,2,'2021-09-26 12:35:09','2021-09-26 12:35:01',8),(62,60,2,'2021-09-26 12:35:48','2021-09-26 12:35:38',8),(65,0,2,'2021-09-26 19:35:36','2021-09-26 19:35:36',8),(66,0,2,'2021-09-26 19:37:26','2021-09-26 19:37:12',8),(123,100,2,'2021-09-30 23:58:14','2021-09-30 23:58:11',66),(124,100,2,'2021-10-01 00:34:20','2021-10-01 00:33:57',66),(125,100,2,'2021-10-01 00:34:28','2021-10-01 00:34:25',64),(126,0,2,'2021-10-03 20:34:50','2021-10-03 20:34:50',63),(127,0,2,'2021-10-03 20:36:54','2021-10-03 20:36:24',63);
/*!40000 ALTER TABLE `result` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (2,'admin'),(1,'user');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `topic`
--

DROP TABLE IF EXISTS `topic`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `topic` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `archived` tinyint NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `topic`
--

LOCK TABLES `topic` WRITE;
/*!40000 ALTER TABLE `topic` DISABLE KEYS */;
INSERT INTO `topic` VALUES (1,'newTopicName',0),(2,'Programming',0),(3,'English',0),(4,'Math',0);
/*!40000 ALTER TABLE `topic` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `password` varchar(255) NOT NULL,
  `name` varchar(45) NOT NULL,
  `surname` varchar(45) NOT NULL,
  `block` tinyint NOT NULL DEFAULT '0',
  `create_date` date NOT NULL,
  `role_id` int DEFAULT '1',
  `email` varchar(60) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `login_UNIQUE` (`email`),
  KEY `fk_user_role1_idx` (`role_id`),
  CONSTRAINT `fk_user_role1` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'EF797C8118F02DFB649607DD5D3F8C7623048C9C063D532CC95C5ED7A898A64F','Vlad','Kovalchuk',0,'2021-09-08',1,'user@mail.com'),(2,'EF797C8118F02DFB649607DD5D3F8C7623048C9C063D532CC95C5ED7A898A64F','Sasha','Kovalchuk',0,'2021-09-08',2,'admin@mail.com'),(3,'EF797C8118F02DFB649607DD5D3F8C7623048C9C063D532CC95C5ED7A898A64F','Oleg','Koval',1,'2021-09-08',2,'user1@mail.com'),(5,'EF797C8118F02DFB649607DD5D3F8C7623048C9C063D532CC95C5ED7A898A64F','Oleg','Koval',0,'2021-09-08',1,'user3@mail.com'),(7,'EF797C8118F02DFB649607DD5D3F8C7623048C9C063D532CC95C5ED7A898A64F','Oleg','Koval',0,'2021-09-08',1,'user5@mail.com'),(11,'021a642069200ffacb6f79f074e0b93d819f16bdb915fbd126433bd413f91e0634b4a92a4a0c0175','Sasha','Kovalchuk',0,'2021-09-21',1,'kovsas8@gmail.com'),(13,'eccbee91315251486cb38e7aeccbc2d2179ef89645bb2a5b37215f5f1a9d4ad075b6bd3af04224d3','Sasha','Kovalchuk',0,'2021-09-21',1,'kovsas@gmail.com'),(14,'0d158380fcd01e2c643fac70316b5e3545432d3f02f51054f7b200ffeba3a825d79c0918bf0b16ff','Sasha','Kovalchuk',0,'2021-09-21',1,'kovs3as8@gmail.com'),(15,'EF797C8118F02DFB649607DD5D3F8C7623048C9C063D532CC95C5ED7A898A64F','Sasha','Kovalchuk',0,'2021-09-21',1,'kovsas1@gmail.com'),(16,'EF797C8118F02DFB649607DD5D3F8C7623048C9C063D532CC95C5ED7A898A64F','Sasha','Kovalchuk',0,'2021-09-21',1,'user7@gmail.com'),(17,'EF797C8118F02DFB649607DD5D3F8C7623048C9C063D532CC95C5ED7A898A64F','Sasha','Kovalchuk',0,'2021-09-21',1,'user8@gmail.com'),(18,'EF797C8118F02DFB649607DD5D3F8C7623048C9C063D532CC95C5ED7A898A64F','Sasha','Kovalchuk',0,'2021-10-01',1,'kovsas8ads@gmail.com'),(19,'EF797C8118F02DFB649607DD5D3F8C7623048C9C063D532CC95C5ED7A898A64F','asdda','adssad',0,'2021-10-03',1,'admin23123@mail.com');
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

-- Dump completed on 2021-10-04  0:08:40
