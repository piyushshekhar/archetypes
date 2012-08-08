/*
SQLyog Enterprise - MySQL GUI v8.12 
MySQL - 5.5.8-log : Database - samplehello
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

CREATE DATABASE /*!32312 IF NOT EXISTS*/`samplehello` /*!40100 DEFAULT CHARACTER SET latin1 */;

/*USE `samplehello`;*/

/*Table structure for table `helloworld` */

DROP TABLE IF EXISTS `helloworld`;

CREATE TABLE `helloworld` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(250) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Data for the table `helloworld` */

insert  into `helloworld`(`id`,`name`) values (1,'helloworld');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
