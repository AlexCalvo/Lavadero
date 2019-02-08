-- MySQL Script generated by MySQL Workbench
-- Fri 08 Feb 2019 04:24:49 PM CET
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema Lavadero
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `Lavadero` ;

-- -----------------------------------------------------
-- Schema Lavadero
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `Lavadero` DEFAULT CHARACTER SET utf8 ;
USE `Lavadero` ;

-- -----------------------------------------------------
-- Table `Lavadero`.`Propietarios`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Lavadero`.`Propietarios` ;

CREATE TABLE IF NOT EXISTS `Lavadero`.`Propietarios` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `Nombre` VARCHAR(100) NULL,
  `Telefono` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Lavadero`.`Trabajador`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Lavadero`.`Trabajador` ;

CREATE TABLE IF NOT EXISTS `Lavadero`.`Trabajador` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `Nombre` VARCHAR(100) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Lavadero`.`Lavados`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Lavadero`.`Lavados` ;

CREATE TABLE IF NOT EXISTS `Lavadero`.`Lavados` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `Matricula` VARCHAR(45) NULL,
  `Marca` VARCHAR(100) NULL,
  `Modelo` VARCHAR(100) NULL,
  `Hora` TIME NULL,
  `Fecha` DATE NULL,
  `Tam` VARCHAR(45) NULL,
  `Precio` INT NULL,
  `Telefono` VARCHAR(45) NULL,
  `Propietarios_id` INT NULL,
  `Trabajador_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Lavados_Propietarios_idx` (`Propietarios_id` ASC),
  INDEX `fk_Lavados_Trabajador1_idx` (`Trabajador_id` ASC),
  CONSTRAINT `fk_Lavados_Propietarios`
    FOREIGN KEY (`Propietarios_id`)
    REFERENCES `Lavadero`.`Propietarios` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Lavados_Trabajador1`
    FOREIGN KEY (`Trabajador_id`)
    REFERENCES `Lavadero`.`Trabajador` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)

ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
