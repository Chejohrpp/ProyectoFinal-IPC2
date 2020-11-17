-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema billeton
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema billeton
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `billeton` DEFAULT CHARACTER SET utf8 ;
USE `billeton` ;

-- -----------------------------------------------------
-- Table `billeton`.`cliente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `billeton`.`cliente` (
  `codigo` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(100) NOT NULL,
  `birth` DATE NOT NULL,
  `dpi` VARCHAR(16) NOT NULL,
  `direccion` VARCHAR(200) NOT NULL,
  `genero` VARCHAR(45) NOT NULL,
  `dpi_pdf` VARBINARY(8000) NULL,
  `password` BLOB NOT NULL,
  PRIMARY KEY (`codigo`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `billeton`.`gerente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `billeton`.`gerente` (
  `codigo` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(100) NOT NULL,
  `turno` VARCHAR(45) NOT NULL,
  `dpi` VARCHAR(16) NOT NULL,
  `direccion` VARCHAR(200) NOT NULL,
  `genero` VARCHAR(45) NOT NULL,
  `password` BLOB NOT NULL,
  PRIMARY KEY (`codigo`))
ENGINE = InnoDB
COMMENT = '		d';


-- -----------------------------------------------------
-- Table `billeton`.`cajero`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `billeton`.`cajero` (
  `codigo` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(100) NOT NULL,
  `turno` VARCHAR(45) NOT NULL,
  `dpi` VARCHAR(16) NOT NULL,
  `direccion` VARCHAR(200) NOT NULL,
  `genero` VARCHAR(45) NOT NULL,
  `password` BLOB NOT NULL,
  PRIMARY KEY (`codigo`))
ENGINE = InnoDB
COMMENT = '	dd			dd	';


-- -----------------------------------------------------
-- Table `billeton`.`cuenta`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `billeton`.`cuenta` (
  `codigo` INT NOT NULL AUTO_INCREMENT,
  `credito` DOUBLE NOT NULL,
  `creacion` DATE NOT NULL,
  `cliente_codigo` INT NOT NULL,
  PRIMARY KEY (`codigo`),
  INDEX `fk_cuenta_cliente1_idx` (`cliente_codigo` ASC) VISIBLE,
  CONSTRAINT `fk_cuenta_cliente1`
    FOREIGN KEY (`cliente_codigo`)
    REFERENCES `billeton`.`cliente` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `billeton`.`transaccion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `billeton`.`transaccion` (
  `codigo` INT NOT NULL AUTO_INCREMENT,
  `monto` DOUBLE NOT NULL,
  `tipo` VARCHAR(45) NOT NULL,
  `hora` TIME NOT NULL,
  `fecha` DATE NOT NULL,
  `nuevo_saldo` DOUBLE NULL,
  `cajero_codigo` INT NOT NULL,
  `cuenta_codigo` INT NOT NULL,
  PRIMARY KEY (`codigo`),
  INDEX `fk_transaccion_cajero_idx` (`cajero_codigo` ASC) VISIBLE,
  INDEX `fk_transaccion_cuenta1_idx` (`cuenta_codigo` ASC) VISIBLE,
  CONSTRAINT `fk_transaccion_cajero`
    FOREIGN KEY (`cajero_codigo`)
    REFERENCES `billeton`.`cajero` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_transaccion_cuenta1`
    FOREIGN KEY (`cuenta_codigo`)
    REFERENCES `billeton`.`cuenta` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `billeton`.`cuentas_asociadas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `billeton`.`cuentas_asociadas` (
  `codigo` INT NOT NULL AUTO_INCREMENT,
  `estado` TINYINT NULL,
  `estado_enviar` TINYINT NULL,
  `no_intentos` INT NULL,
  `cuenta_codigo` INT NOT NULL,
  `cliente_codigo` INT NOT NULL,
  `king_cuenta` INT NOT NULL,
  PRIMARY KEY (`codigo`),
  INDEX `fk_cuentas_asociadas_cuenta1_idx` (`cuenta_codigo` ASC) VISIBLE,
  INDEX `fk_cuentas_asociadas_cliente1_idx` (`cliente_codigo` ASC) VISIBLE,
  INDEX `fk_cuentas_asociadas_cliente2_idx` (`king_cuenta` ASC) VISIBLE,
  CONSTRAINT `fk_cuentas_asociadas_cuenta1`
    FOREIGN KEY (`cuenta_codigo`)
    REFERENCES `billeton`.`cuenta` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_cuentas_asociadas_cliente1`
    FOREIGN KEY (`cliente_codigo`)
    REFERENCES `billeton`.`cliente` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_cuentas_asociadas_cliente2`
    FOREIGN KEY (`king_cuenta`)
    REFERENCES `billeton`.`cliente` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `billeton`.`cambios`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `billeton`.`cambios` (
  `codigo` INT NOT NULL AUTO_INCREMENT,
  `tipo` VARCHAR(45) NOT NULL,
  `codigo_tipo` INT NOT NULL,
  `nombre` VARCHAR(100) NOT NULL,
  `fecha` DATE NOT NULL,
  `gerente_codigo` INT NOT NULL,
  PRIMARY KEY (`codigo`),
  INDEX `fk_cambios_gerente1_idx` (`gerente_codigo` ASC) VISIBLE,
  CONSTRAINT `fk_cambios_gerente1`
    FOREIGN KEY (`gerente_codigo`)
    REFERENCES `billeton`.`gerente` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
