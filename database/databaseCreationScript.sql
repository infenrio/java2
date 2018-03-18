SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `java2` DEFAULT CHARACTER SET utf8 ;
USE `java2` ;

DROP TABLE IF EXISTS `user_state` ;

CREATE TABLE IF NOT EXISTS `user_state` (
  `id` CHAR(10) NOT NULL,
  `text` CHAR(100) NOT NULL,
  PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `users` ;

CREATE TABLE IF NOT EXISTS `users` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `login` CHAR(32) NOT NULL,
  `name` CHAR(100) NOT NULL,
  `email` CHAR(100) NOT NULL,
  `state_idref` CHAR(10) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY (`login`),
  FOREIGN KEY (`state_idref`) REFERENCES `user_state`(`id`)
);

DROP TABLE IF EXISTS `announcement_state` ;

CREATE TABLE IF NOT EXISTS `announcement_state` (
  `id` CHAR(10) NOT NULL,
  `text` CHAR(100) NOT NULL,
  PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `announcements` ;

CREATE TABLE IF NOT EXISTS `announcements` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `title` CHAR(32) NOT NULL,
  `description` CHAR(100) NOT NULL,
  `user_idref` INT(11) NOT NULL,
  `state_idref` CHAR(10) NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`user_idref`) REFERENCES `users`(`id`),
  FOREIGN KEY (`state_idref`) REFERENCES `announcement_state`(`id`)
)

ENGINE = InnoDB
AUTO_INCREMENT = 1000;

INSERT INTO `user_state` VALUES ("ACTIVE", "User is active");
INSERT INTO `user_state` VALUES ("BANNED", "User is banned");

INSERT INTO `announcement_state` VALUES ("ACTIVE", "Announcement is active");
INSERT INTO `announcement_state` VALUES ("ARCHIVED", "Announcement is archived");
INSERT INTO `announcement_state` VALUES ("BANNED", "Announcement is banned");

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;