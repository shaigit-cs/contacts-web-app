CREATE DATABASE IF NOT EXISTS `contacts_db` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_polish_ci;

USE `contacts_db`;

CREATE TABLE IF NOT EXISTS `CONTACTS` (
  `contact_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `birthdate` date NOT NULL,
  `email` varchar(255) COLLATE utf8mb4_polish_ci NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_polish_ci NOT NULL,
  `phone` varchar(255) COLLATE utf8mb4_polish_ci NOT NULL,
  `phone_code` varchar(255) COLLATE utf8mb4_polish_ci NOT NULL,
  `surname` varchar(255) COLLATE utf8mb4_polish_ci NOT NULL,
  PRIMARY KEY (`contact_id`)
) ENGINE MyISAM DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_polish_ci;
