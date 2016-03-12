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

-- Export de la structure de la base pour webjava
CREATE DATABASE IF NOT EXISTS `webjava` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `webjava`;


-- Export de la structure de table webjava. comptes
CREATE TABLE IF NOT EXISTS `comptes` (
  `Login` varchar(25) NOT NULL,
  `Mdp` varchar(25) NOT NULL,
  `Nom` varchar(25) DEFAULT NULL,
  `Prenom` varchar(25) DEFAULT NULL,
  `Role` varchar(14) NOT NULL,
  PRIMARY KEY (`Login`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Export de données de la table webjava.comptes : ~1 rows (environ)
/*!40000 ALTER TABLE `comptes` DISABLE KEYS */;
INSERT INTO `comptes` (`Login`, `Mdp`, `Nom`, `Prenom`, `Role`) VALUES
	('admin', 'admin', NULL, NULL, 'Administrateur');
/*!40000 ALTER TABLE `comptes` ENABLE KEYS */;


-- Export de la structure de table webjava. reservations
CREATE TABLE IF NOT EXISTS `reservations` (
  `NumeroRes` int(5) NOT NULL,
  `Login` varchar(25) DEFAULT NULL,
  `NumeroVol` int(5) DEFAULT NULL,
  `NombrePlaces` int(2) DEFAULT NULL,
  `Confirmation` varchar(3) DEFAULT 'NON',
  PRIMARY KEY (`NumeroRes`),
  KEY `fk_res_cpt` (`Login`),
  KEY `fk_res_vol` (`NumeroVol`),
  CONSTRAINT `fk_res_cpt` FOREIGN KEY (`Login`) REFERENCES `comptes` (`Login`),
  CONSTRAINT `fk_res_vol` FOREIGN KEY (`NumeroVol`) REFERENCES `vols` (`NumeroVol`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Export de données de la table webjava.reservations : ~0 rows (environ)
/*!40000 ALTER TABLE `reservations` DISABLE KEYS */;
/*!40000 ALTER TABLE `reservations` ENABLE KEYS */;


-- Export de la structure de table webjava. vols
CREATE TABLE IF NOT EXISTS `vols` (
  `NumeroVol` int(5) NOT NULL AUTO_INCREMENT,
  `Destination` varchar(25) DEFAULT NULL,
  `DateDepart` date DEFAULT NULL,
  `NombrePlaces` int(3) DEFAULT NULL,
  `Prix` float DEFAULT NULL,
  PRIMARY KEY (`NumeroVol`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Export de données de la table webjava.vols : ~0 rows (environ)
/*!40000 ALTER TABLE `vols` DISABLE KEYS */;
/*!40000 ALTER TABLE `vols` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
