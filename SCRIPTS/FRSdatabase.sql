-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`flight`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`flight` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `seating_capacity` INT(11) NOT NULL,
  `reservation_capacity` INT(11) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`flight_schedule`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`flight_schedule` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `schedule_day` DATE NOT NULL,
  `flight_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_flight_schedule_Flight1_idx` (`flight_id` ASC) VISIBLE,
  CONSTRAINT `fk_flight_schedule_Flight1`
    FOREIGN KEY (`flight_id`)
    REFERENCES `mydb`.`flight` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`passenger`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`passenger` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `age` INT(11) NOT NULL,
  `gender` VARCHAR(45) NOT NULL,
  `seat_no` INT(11) NOT NULL,
  `booking_date` DATE NOT NULL,
  `flight_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Passenger_Flight1_idx` (`flight_id` ASC) VISIBLE,
  CONSTRAINT `fk_Passenger_Flight1`
    FOREIGN KEY (`flight_id`)
    REFERENCES `mydb`.`flight` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`ticket`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`ticket` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `payment_info` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`passenger_schedule`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`passenger_schedule` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `journey_date` DATETIME NOT NULL,
  `source` VARCHAR(45) NOT NULL,
  `destination` VARCHAR(45) NOT NULL,
  `reservation_type` VARCHAR(45) NOT NULL,
  `passenger_id` INT(11) NOT NULL,
  `ticket_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`, `ticket_id`),
  INDEX `fk_Schedule_Passenger1_idx` (`passenger_id` ASC) VISIBLE,
  INDEX `fk_passenger_schedule_ticket1_idx` (`ticket_id` ASC) VISIBLE,
  CONSTRAINT `fk_Schedule_Passenger1`
    FOREIGN KEY (`passenger_id`)
    REFERENCES `mydb`.`passenger` (`id`),
  CONSTRAINT `fk_passenger_schedule_ticket1`
    FOREIGN KEY (`ticket_id`)
    REFERENCES `mydb`.`ticket` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`user_credential`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`user_credential` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `type` VARCHAR(45) NOT NULL,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `login_status` TINYINT(4) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`user_profile`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`user_profile` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `date_of_birth` DATE NOT NULL,
  `gender` VARCHAR(45) NOT NULL,
  `address` VARCHAR(45) NOT NULL,
  `mobile_number` VARCHAR(45) NOT NULL,
  `email_id` VARCHAR(45) NOT NULL,
  `user_credential_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`, `user_credential_id`),
  INDEX `fk_user_profile_user_credential1_idx` (`user_credential_id` ASC) VISIBLE,
  CONSTRAINT `fk_user_profile_user_credential1`
    FOREIGN KEY (`user_credential_id`)
    REFERENCES `mydb`.`user_credential` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`reservation`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`reservation` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `journey_date` DATE NOT NULL,
  `no_of_seats` INT(11) NOT NULL,
  `user_profile_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Reservation_User_Profile1_idx` (`user_profile_id` ASC) VISIBLE,
  CONSTRAINT `fk_Reservation_User_Profile1`
    FOREIGN KEY (`user_profile_id`)
    REFERENCES `mydb`.`user_profile` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`reservation_has_flight`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`reservation_has_flight` (
  `reservation_id` INT(11) NOT NULL,
  `flight_id` INT(11) NOT NULL,
  INDEX `fk_Reservation_has_Flight_Flight1_idx` (`flight_id` ASC) VISIBLE,
  INDEX `fk_Reservation_has_Flight_Reservation1_idx` (`reservation_id` ASC) VISIBLE,
  CONSTRAINT `fk_Reservation_has_Flight_Flight1`
    FOREIGN KEY (`flight_id`)
    REFERENCES `mydb`.`flight` (`id`),
  CONSTRAINT `fk_Reservation_has_Flight_Reservation1`
    FOREIGN KEY (`reservation_id`)
    REFERENCES `mydb`.`reservation` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`route`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`route` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `source` VARCHAR(45) NOT NULL,
  `destination` VARCHAR(45) NOT NULL,
  `distance` FLOAT NOT NULL,
  `duration` INT(11) NOT NULL,
  `flight_schedule_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Route_flight_schedule1_idx` (`flight_schedule_id` ASC) VISIBLE,
  CONSTRAINT `fk_Route_flight_schedule1`
    FOREIGN KEY (`flight_schedule_id`)
    REFERENCES `mydb`.`flight_schedule` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
