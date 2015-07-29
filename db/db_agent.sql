CREATE DATABASE  IF NOT EXISTS `db_agent` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `db_agent`;
-- MySQL dump 10.13  Distrib 5.6.17, for Win64 (x86_64)
--
-- Host: localhost    Database: db_agent
-- ------------------------------------------------------
-- Server version	5.6.21-log

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
-- Table structure for table `tb_agent`
--

DROP TABLE IF EXISTS `tb_agent`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_agent` (
  `id_item` int(11) NOT NULL AUTO_INCREMENT,
  `articulo` varchar(50) DEFAULT NULL,
  `item` varchar(50) DEFAULT NULL,
  `precio` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id_item`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_agent`
--

LOCK TABLES `tb_agent` WRITE;
/*!40000 ALTER TABLE `tb_agent` DISABLE KEYS */;
INSERT INTO `tb_agent` VALUES (1,'Celulares','Samsung','$300.000-$600.000'),(2,'Motos','AKT','$300.000-$600.000'),(3,'Electrodomesticos','Computadores','$1.000.000-$2.000.000'),(4,'Electrodomesticos','Cualquiera','$300.000-$600.000');
/*!40000 ALTER TABLE `tb_agent` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_saved_items`
--

DROP TABLE IF EXISTS `tb_saved_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_saved_items` (
  `id_item` int(11) NOT NULL AUTO_INCREMENT,
  `item_name` varchar(100) DEFAULT NULL,
  `item_price` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id_item`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_saved_items`
--

LOCK TABLES `tb_saved_items` WRITE;
/*!40000 ALTER TABLE `tb_saved_items` DISABLE KEYS */;
INSERT INTO `tb_saved_items` VALUES (1,' Honda Cb 110cc 051 Cc - 125 Cc ',' $ 3.200.000'),(2,' V3 Nuevo, Caja Original, Batería, Cargador, Manual. ',' $ 150.000');
/*!40000 ALTER TABLE `tb_saved_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_url_store`
--

DROP TABLE IF EXISTS `tb_url_store`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_url_store` (
  `url_id` int(11) NOT NULL AUTO_INCREMENT,
  `url` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`url_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_url_store`
--

LOCK TABLES `tb_url_store` WRITE;
/*!40000 ALTER TABLE `tb_url_store` DISABLE KEYS */;
INSERT INTO `tb_url_store` VALUES (1,'http://celulares.mercadolibre.com.co/'),(2,'http://motos.mercadolibre.com.co/'),(3,'http://computacion.mercadolibre.com.co/'),(4,'http://computacion.mercadolibre.com.co/');
/*!40000 ALTER TABLE `tb_url_store` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-06-05 14:41:54
