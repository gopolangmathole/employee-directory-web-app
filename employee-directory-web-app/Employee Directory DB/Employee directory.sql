drop database if exists employee_directory;

Create database employee_directory;

USE employee_directory;
DROP TABLE IF EXISTS `employee_address`;
CREATE TABLE `employee_address` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `street_address` varchar(128) DEFAULT NULL,
  `city` varchar(45) DEFAULT NULL,
  `postal_code` varchar(45) DEFAULT NULL,
  `country` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `employment_contract` varchar(45) NOT NULL,
  `gender` varchar(6) NOT NULL,
  `email` nvarchar(150) NOT NULL,
  `image` varchar(255) DEFAULT NULL,
  `last_update` datetime NOT NULL,
  `employee_address_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_DETAIL_idx` (`employee_address_id`),
  CONSTRAINT `FK_DETAIL` FOREIGN KEY (`employee_address_id`) 
  REFERENCES `employee_address` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


/*Creating customer logging table*/
CREATE TABLE `exception` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` int(5) NOT NULL,
  `exception` mediumtext NOT NULL,
  `time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=latin1