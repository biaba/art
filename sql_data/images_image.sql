-- MySQL dump 10.13  Distrib 8.0.23, for Win64 (x86_64)
--
-- Host: localhost    Database: images
-- ------------------------------------------------------
-- Server version	8.0.23

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
-- Table structure for table `image`
--

DROP TABLE IF EXISTS `image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `image` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `category` varchar(255) NOT NULL,
  `path` varchar(255) NOT NULL,
  `for_sale` bit(1) NOT NULL,
  `name` varchar(255) NOT NULL,
  `sold` bit(1) NOT NULL,
  `buyer_user_id` bigint DEFAULT NULL,
  `creator_user_id` bigint NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `price` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKkk2ep1n6i8okhkv0u32iqk08e` (`buyer_user_id`),
  KEY `FKilbcdmcwnf2b5ixo89xalyujq` (`creator_user_id`),
  CONSTRAINT `FKilbcdmcwnf2b5ixo89xalyujq` FOREIGN KEY (`creator_user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKkk2ep1n6i8okhkv0u32iqk08e` FOREIGN KEY (`buyer_user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `image`
--

LOCK TABLES `image` WRITE;
/*!40000 ALTER TABLE `image` DISABLE KEYS */;
INSERT INTO `image` VALUES (1,'PAINTING','car.jpg',_binary '','love for speed',_binary '\0',NULL,2,'favourite car in the summer',200),(2,'PAINTING','child.jpg',_binary '','hard times',_binary '\0',NULL,3,'childhood memories',200),(3,'DRAWING','flower.jpg',_binary '\0','living beauty',_binary '',2,1,'gardening throughout the year',0),(4,'DRAWING','flower2.jpg',_binary '\0','moms garden',_binary '\0',NULL,1,'the special one',0),(5,'PHOTOGRAPHY','flower3.jpg',_binary '','spring',_binary '\0',NULL,1,'19th centuries favourites',200),(6,'PHOTOGRAPHY','car2.jpg',_binary '','ready steady go',_binary '\0',NULL,1,'when nothing\'s more left',200),(7,'PHOTOGRAPHY','car3.jpg',_binary '','dream',_binary '\0',NULL,1,'speed - nothing to add',200);
/*!40000 ALTER TABLE `image` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-07-23 23:10:43
