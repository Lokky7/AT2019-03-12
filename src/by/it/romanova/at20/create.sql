-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema romanova
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `romanova` ;

-- -----------------------------------------------------
-- Schema romanova
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `romanova` DEFAULT CHARACTER SET utf8;
USE `romanova`;

-- -----------------------------------------------------
-- Table `romanova`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `romanova`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(16) NOT NULL,
  `email` VARCHAR(255) NULL,
  `password` VARCHAR(32) NOT NULL,
  `create_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`));


-- -----------------------------------------------------
-- Table `romanova`.`category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `romanova`.`category` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE);


-- -----------------------------------------------------
-- Table `romanova`.`variable`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `romanova`.`variable` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `text` VARCHAR(45) NULL,
  `user_id` INT NOT NULL,
  `category_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_variable_user_idx` (`user_id` ASC) VISIBLE,
  INDEX `fk_variable_category1_idx` (`category_id` ASC) VISIBLE,
  CONSTRAINT `fk_variable_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `romanova`.`user` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_variable_category1`
    FOREIGN KEY (`category_id`)
    REFERENCES `romanova`.`category` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `romanova`.`user`
-- -----------------------------------------------------
START TRANSACTION;
USE `romanova`;
INSERT INTO `romanova`.`user` (`id`, `username`, `email`, `password`, `create_time`) VALUES (DEFAULT, 'default', 'def@mail.ru', 'qwerty', '2019-04-25');
INSERT INTO `romanova`.`user` (`id`, `username`, `email`, `password`, `create_time`) VALUES (DEFAULT, 'user', 'user@mail.ru', '12345', '2019-04-25');
INSERT INTO `romanova`.`user` (`id`, `username`, `email`, `password`, `create_time`) VALUES (DEFAULT, 'admin', 'a@mail.ru', '222222', '2019-04-25');

COMMIT;


-- -----------------------------------------------------
-- Data for table `romanova`.`category`
-- -----------------------------------------------------
START TRANSACTION;
USE `romanova`;
INSERT INTO `romanova`.`category` (`id`, `name`) VALUES (DEFAULT, 'SCALAR');
INSERT INTO `romanova`.`category` (`id`, `name`) VALUES (DEFAULT, 'VECTOR');
INSERT INTO `romanova`.`category` (`id`, `name`) VALUES (DEFAULT, 'MATRIX');

COMMIT;

