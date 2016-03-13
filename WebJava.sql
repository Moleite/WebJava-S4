-- --------------------------------------------------------
-- Hôte :                        127.0.0.1
-- Version du serveur:           5.6.20-log - MySQL Community Server (GPL)
-- SE du serveur:                Win32
-- HeidiSQL Version:             9.3.0.4984
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Export de la structure de la base pour tchou
CREATE DATABASE IF NOT EXISTS `tchou` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `tchou`;


-- Export de la structure de table tchou. billets
CREATE TABLE IF NOT EXISTS `billets` (
  `NumeroBillet` int(11) NOT NULL DEFAULT '0',
  `NumeroDepart` int(11) DEFAULT NULL,
  `LoginVoyageur` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`NumeroBillet`),
  KEY `fk_bil_dep` (`NumeroDepart`),
  KEY `fk_bil_voy` (`LoginVoyageur`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- Export de données de la table tchou.billets : 0 rows
/*!40000 ALTER TABLE `billets` DISABLE KEYS */;
/*!40000 ALTER TABLE `billets` ENABLE KEYS */;


-- Export de la structure de table tchou. departs
CREATE TABLE IF NOT EXISTS `departs` (
  `NumeroDepart` int(11) NOT NULL DEFAULT '0',
  `NumeroLigne` int(11) DEFAULT NULL,
  `DateDep` date DEFAULT NULL,
  `Capacite` int(11) DEFAULT NULL,
  PRIMARY KEY (`NumeroDepart`),
  KEY `fk_dep_lig` (`NumeroLigne`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- Export de données de la table tchou.departs : 6 rows
/*!40000 ALTER TABLE `departs` DISABLE KEYS */;
INSERT INTO `departs` (`NumeroDepart`, `NumeroLigne`, `DateDep`, `Capacite`) VALUES
	(60, 4, '2015-05-28', 300),
	(50, 4, '2015-04-05', 5),
	(40, 2, '2015-03-30', 4),
	(30, 1, '2015-03-22', 5),
	(20, 1, '2015-03-15', 2),
	(10, 1, '2015-03-12', 3);
/*!40000 ALTER TABLE `departs` ENABLE KEYS */;


-- Export de la structure de table tchou. lignes
CREATE TABLE IF NOT EXISTS `lignes` (
  `NumeroLigne` int(11) NOT NULL DEFAULT '0',
  `VilleDepart` varchar(20) DEFAULT NULL,
  `VilleDestination` varchar(20) DEFAULT NULL,
  `Duree` int(11) DEFAULT NULL,
  PRIMARY KEY (`NumeroLigne`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- Export de données de la table tchou.lignes : 4 rows
/*!40000 ALTER TABLE `lignes` DISABLE KEYS */;
INSERT INTO `lignes` (`NumeroLigne`, `VilleDepart`, `VilleDestination`, `Duree`) VALUES
	(1, 'Paris', 'Lyon', 120),
	(2, 'Paris', 'Bordeaux', 720),
	(3, 'Paris', 'Marseille', 900),
	(4, 'Brest', 'Paris', 620);
/*!40000 ALTER TABLE `lignes` ENABLE KEYS */;


-- Export de la structure de table tchou. voyageurs
CREATE TABLE IF NOT EXISTS `voyageurs` (
  `LoginVoyageur` varchar(20) NOT NULL DEFAULT '',
  `Password` varchar(20) DEFAULT NULL,
  `NomVoyageur` varchar(20) DEFAULT NULL,
  `AdresseVoyageur` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`LoginVoyageur`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- Export de données de la table tchou.voyageurs : 2 rows
/*!40000 ALTER TABLE `voyageurs` DISABLE KEYS */;
INSERT INTO `voyageurs` (`LoginVoyageur`, `Password`, `NomVoyageur`, `AdresseVoyageur`) VALUES
	('benharkat@gmail.com', 'mdp', 'test', 'test'),
	('test', 'test', 'test', 'test');
/*!40000 ALTER TABLE `voyageurs` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
