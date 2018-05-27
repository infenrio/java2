SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `java2` DEFAULT CHARACTER SET utf8 ;
USE `java2` ;

DROP TABLE IF EXISTS `term` ;

CREATE TABLE IF NOT EXISTS `term` (
  `id` INT NOT NULL,
  `term_en` VARCHAR(100) NOT NULL,
  `term_lv` VARCHAR(100) NOT NULL,
  `term_ru` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `user_state` ;

CREATE TABLE IF NOT EXISTS `user_state` (
  `id` VARCHAR(10) NOT NULL,
  `title_term_idref` INT NOT NULL,
  `description_term_idref` INT NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`title_term_idref`) REFERENCES `term`(`id`),
  FOREIGN KEY (`description_term_idref`) REFERENCES `term`(`id`)
);

DROP TABLE IF EXISTS `users` ;

CREATE TABLE IF NOT EXISTS `users` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(32) NOT NULL,
  `password` VARCHAR(32) NOT NULL,
  `role` CHAR(1) NOT NULL DEFAULT 'U',
  `name` VARCHAR(100) NOT NULL,
  `email` VARCHAR(100) NOT NULL,
  `state_idref` VARCHAR(10) NOT NULL,
  `created_date` DATETIME DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY (`login`),
  FOREIGN KEY (`state_idref`) REFERENCES `user_state`(`id`)
)

ENGINE = InnoDB
AUTO_INCREMENT = 1000;

DROP TABLE IF EXISTS `announcement_state` ;

CREATE TABLE IF NOT EXISTS `announcement_state` (
  `id` VARCHAR(10) NOT NULL,
  `title_term_idref` INT NOT NULL,
  `description_term_idref` INT NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`title_term_idref`) REFERENCES `term`(`id`),
  FOREIGN KEY (`description_term_idref`) REFERENCES `term`(`id`)
);

DROP TABLE IF EXISTS `announcement_category` ;

CREATE TABLE IF NOT EXISTS `announcement_category` (
  `id` INT NOT NULL,
  `parent_category_idref` INT NULL,
  `title_term_idref` INT NOT NULL,
  `description_term_idref` INT NOT NULL,
  `created_date` DATETIME DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`parent_category_idref`) REFERENCES `announcement_category`(`id`),
  FOREIGN KEY (`title_term_idref`) REFERENCES `term`(`id`),
  FOREIGN KEY (`description_term_idref`) REFERENCES `term`(`id`)
)

ENGINE = InnoDB
AUTO_INCREMENT = 1000;

DROP TABLE IF EXISTS `announcements` ;

CREATE TABLE IF NOT EXISTS `announcements` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `category_idref` INT NOT NULL,
  `title` VARCHAR(32) NOT NULL,
  `description` VARCHAR(100) NOT NULL,
  `user_idref` INT NOT NULL,
  `state_idref` VARCHAR(10) NOT NULL,
  `created_date` DATETIME DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`category_idref`) REFERENCES `announcement_category`(`id`),
  FOREIGN KEY (`user_idref`) REFERENCES `users`(`id`),
  FOREIGN KEY (`state_idref`) REFERENCES `announcement_state`(`id`)
)

ENGINE = InnoDB
AUTO_INCREMENT = 1000;

INSERT INTO `term` VALUES (1000, "Active", "Aktīvs", "Активный");
INSERT INTO `term` VALUES (1001, "Banned", "Bloķēts", "Заблокирован");
INSERT INTO `term` VALUES (1002, "User is active", "Lietotājs ir aktīvs", "Пользователь активный");
INSERT INTO `term` VALUES (1003, "User is banned", "Lietotājs ir bloķēts", "Пользователь заблокирован");

INSERT INTO `term` VALUES (1004, "Active", "Aktīvs", "Активно");
INSERT INTO `term` VALUES (1005, "Archived", "Arhivēts", "Архивировано");
INSERT INTO `term` VALUES (1006, "Banned", "Bloķēts", "Заблокировано");
INSERT INTO `term` VALUES (1007, "Announcement is active", "Paziņojums ir aktīvs", "Объявление активно");
INSERT INTO `term` VALUES (1008, "Announcement is archived", "Paziņojums ir arhivēts", "Объявление архивировано");
INSERT INTO `term` VALUES (1009, "Announcement is banned", "Paziņojums ir bloķēts", "Объявление заблокировано");

INSERT INTO `term` VALUES (1010, "Items", "Priekšmeti", "Предметы");
INSERT INTO `term` VALUES (1011, "Category for different types of items", "Kategorija dažādiem priekšmetu tipiem", "Категория для разных типов предметов");

INSERT INTO `term` VALUES (1012, "Services", "Pakalpojumi", "Услуги");
INSERT INTO `term` VALUES (1013, "Category for different types of services", "Kategorija dažādiem pakalpojumu tipiem", "Категория для разных типов услуг");

INSERT INTO `term` VALUES (1014, "Transport", "Transports", "Транспорт");
INSERT INTO `term` VALUES (1015, "Category for different types of transport", "Kategorija dažādiem transporta tipiem", "Категория для разных типов транспорта");

INSERT INTO `term` VALUES (1016, "Help", "Palīdzība", "Помощь");
INSERT INTO `term` VALUES (1017, "Category for different types of help", "Kategorija dažādiem palīdzības tipiem", "Категория для разных типов помощи");

INSERT INTO `term` VALUES (1018, "Friendship", "Draudzība", "Дружба");
INSERT INTO `term` VALUES (1019, "Category for different types of friendship", "Kategorija dažādiem draudzības tipiem", "Категория для разных типов дружбы");


INSERT INTO `user_state` VALUES ("ACTIVE", 1000, 1002);
INSERT INTO `user_state` VALUES ("BANNED", 1001, 1003);

INSERT INTO `announcement_state` VALUES ("ACTIVE", 1004, 1007);
INSERT INTO `announcement_state` VALUES ("ARCHIVED", 1005, 1008);
INSERT INTO `announcement_state` VALUES ("BANNED", 1006, 1009);

INSERT INTO `announcement_category` (`id`, `title_term_idref`, `description_term_idref`) VALUES (1000, 1010, 1011);
INSERT INTO `announcement_category` (`id`, `title_term_idref`, `description_term_idref`) VALUES (1001, 1012, 1013);
INSERT INTO `announcement_category` (`id`, `parent_category_idref`, `title_term_idref`, `description_term_idref`) VALUES (1002, 1000, 1014, 1015);
INSERT INTO `announcement_category` (`id`, `parent_category_idref`, `title_term_idref`, `description_term_idref`) VALUES (1003, 1001, 1016, 1017);
INSERT INTO `announcement_category` (`id`, `parent_category_idref`, `title_term_idref`, `description_term_idref`) VALUES (1004, 1001, 1018, 1019);

INSERT INTO `users` (`login`, `password`, `role`, `name`, `email`, `state_idref`) VALUES ("admin", "admin", 'S', "admin", "admin@admin.com", "ACTIVE");

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;