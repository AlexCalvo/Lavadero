-- MySQL Script generated by MySQL Workbench
-- Sun 31 Mar 2019 05:20:33 PM CEST
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

-- -----------------------------------------------------
-- Schema Lavadero
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `Lavadero` DEFAULT CHARACTER SET utf8 ;
USE `Lavadero` ;

-- -----------------------------------------------------
-- Table `Lavadero`.`Complementos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Lavadero`.`Complementos` (
  `nombre` VARCHAR(45) NOT NULL,
  `precio` DOUBLE NOT NULL,
  PRIMARY KEY (`nombre`));


-- -----------------------------------------------------
-- Table `Lavadero`.`Modelo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Lavadero`.`Modelo` (
  `Nombre` VARCHAR(100) NOT NULL,
  `Precio` DOUBLE NOT NULL,
  PRIMARY KEY (`Nombre`));


-- -----------------------------------------------------
-- Table `Lavadero`.`Trabajador`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Lavadero`.`Trabajador` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `Nombre` VARCHAR(100) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
AUTO_INCREMENT = 5;


-- -----------------------------------------------------
-- Table `Lavadero`.`Lavados`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Lavadero`.`Lavados` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `Matricula` VARCHAR(45) NULL DEFAULT NULL,
  `Modelo` VARCHAR(100) NULL DEFAULT NULL,
  `Hora` TIME NULL DEFAULT NULL,
  `Fecha` DATE NULL DEFAULT NULL,
  `Telefono` VARCHAR(45) NULL DEFAULT NULL,
  `Trabajador_id` INT(11) NULL DEFAULT NULL,
  `Complemento_id` VARCHAR(45) NULL DEFAULT NULL,
  `Observaciones` VARCHAR(300) NULL DEFAULT NULL,
  `Propietario` VARCHAR(45) NULL DEFAULT NULL,
  `Factura` TINYINT(1) NOT NULL DEFAULT FALSE,
  PRIMARY KEY (`id`),
  INDEX `fk_Lavados_Trabajador1_idx` (`Trabajador_id` ASC),
  INDEX `fk_Lavados_Modelo1_idx` (`Modelo` ASC),
  INDEX `fk_Lavados_1_Complemento1_idx` (`Complemento_id` ASC),
  CONSTRAINT `fk_Lavados_1_Complemento1`
    FOREIGN KEY (`Complemento_id`)
    REFERENCES `Lavadero`.`Complementos` (`nombre`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Lavados_Modelo1`
    FOREIGN KEY (`Modelo`)
    REFERENCES `Lavadero`.`Modelo` (`Nombre`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Lavados_Trabajador1`
    FOREIGN KEY (`Trabajador_id`)
    REFERENCES `Lavadero`.`Trabajador` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
AUTO_INCREMENT = 23;