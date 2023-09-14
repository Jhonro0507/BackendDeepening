-- -----------------------------------------------------
-- Schema workshop_BD
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `workshop_BD`;
USE `workshop_BD` ;

-- -----------------------------------------------------
-- Table `workshop_BD`.`jefe`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `workshop_BD`.`jefe` (
  `jefe_codigo` INT NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`jefe_codigo`));


-- -----------------------------------------------------
-- Table `workshop_BD`.`oficina`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `workshop_BD`.`oficina` (
  `ofi_codigo` INT NOT NULL AUTO_INCREMENT,
  `ofi_ciudad` TINYTEXT NOT NULL,
  `ofi_pais` TINYTEXT NOT NULL,
  `ofi_region` TINYTEXT NULL,
  `ofi_postal` VARCHAR(7) NULL,
  `ofi_telefono`  VARCHAR(10) NOT NULL,
  `ofi_direccion` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`ofi_codigo`));


-- -----------------------------------------------------
-- Table `workshop_BD`.`empleado`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `workshop_BD`.`empleado` (
  `emple_codigo` INT NOT NULL AUTO_INCREMENT,
  `emple_nombre` TINYTEXT NOT NULL,
  `emple_apellido1` TINYTEXT NOT NULL,
  `emple_apellido2` TINYTEXT NULL,
  `emple_extension` INT(2) NOT NULL,
  `emple_email` VARCHAR(45) NOT NULL,
  `emple_puesto` VARCHAR(20) NOT NULL,
  `emple_ofi_codigo` INT NOT NULL,
  `emple_jefe_codigo` INT NOT NULL,
  PRIMARY KEY (`emple_codigo`),
  FOREIGN KEY (`emple_jefe_codigo`) 
  REFERENCES `workshop_BD`.`jefe` (`jefe_codigo`),
  FOREIGN KEY (`emple_ofi_codigo`)
  REFERENCES `workshop_BD`.`oficina` (`ofi_codigo`));



-- -----------------------------------------------------
-- Table `workshop_BD`.`cliente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `workshop_BD`.`cliente` (
  `clien_codigo` INT NOT NULL AUTO_INCREMENT,
  `clien_nombre` TINYTEXT NOT NULL,
  `clien_nombre_contacto` TINYTEXT NOT NULL,
  `clien_apellido_contacto` TINYTEXT NULL,
  `clien_telefono` VARCHAR(10) NOT NULL,
  `clien_fax` VARCHAR(10) NULL,
  `clien_direccion1` VARCHAR(50) NOT NULL,
  `clien_direccion2` VARCHAR(50) NULL,
  `clien_ciudad` TINYTEXT NOT NULL,
  `clien_region` TINYTEXT NOT NULL,
  `clien_pais` TINYTEXT NOT NULL,
  `clien_postal` VARCHAR(6) NOT NULL,
  `clien_limite_credito` FLOAT(11,2) NULL,
  `clien_emple_codigo` INT NOT NULL,
  PRIMARY KEY (`clien_codigo`),
  FOREIGN KEY (`clien_emple_codigo`)
  REFERENCES `workshop_BD`.`empleado` (`emple_codigo`));


-- -----------------------------------------------------
-- Table `workshop_BD`.`pedido`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `workshop_BD`.`pedido` (
  `pedi_codigo` INT NOT NULL AUTO_INCREMENT,
  `pedi_fecha_pedido` DATE NOT NULL,
  `pedi_fecha_esperada` DATE NOT NULL,
  `pedi_fecha_entrega` DATE NOT NULL,
  `pedi_estado` VARCHAR(30) NOT NULL,
  `pedi_comentario` VARCHAR(100) NULL,
  `pedi_clien_codigo` INT NOT NULL,
  PRIMARY KEY (`pedi_codigo`),
  FOREIGN KEY (`pedi_clien_codigo`)
  REFERENCES `workshop_BD`.`cliente` (`clien_codigo`));


-- -----------------------------------------------------
-- Table `workshop_BD`.`pago`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `workshop_BD`.`pago` (
  `pago_id_transaccion` INT NOT NULL AUTO_INCREMENT,
  `pago_forma` TINYTEXT NOT NULL,
  `pago_fecha` DATE NOT NULL,
  `pago_total` FLOAT(15,2) NOT NULL,
  `pago_clien_codigo` INT NOT NULL,
  PRIMARY KEY (`pago_id_transaccion`),
  FOREIGN KEY (`pago_clien_codigo`)
  REFERENCES `workshop_BD`.`cliente` (`clien_codigo`));

-- -----------------------------------------------------
-- Table `workshop_BD`.`gama`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `workshop_BD`.`gama` (
  `gama_gama` INT NOT NULL AUTO_INCREMENT,
  `gama_descripcion` VARCHAR(100) NOT NULL,
  `gama_imagen` VARCHAR(3000) NOT NULL,
  PRIMARY KEY (`gama_gama`));


-- -----------------------------------------------------
-- Table `workshop_BD`.`producto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `workshop_BD`.`producto` (
  `produc_codigo` INT NOT NULL AUTO_INCREMENT,
  `produc_nombre` VARCHAR(30) NOT NULL,
  `produc_dimensiones` VARCHAR(20) NOT NULL,
  `produc_proveedor` VARCHAR(30) NOT NULL,
  `produc_descripcion` VARCHAR(100) NOT NULL,
  `produc_stock` INT NOT NULL,
  `produc_precio_venta` FLOAT(10,2) NOT NULL,
  `produc_precio_proveedor` FLOAT(10,2) NOT NULL,
  `produc_gama_gama` INT NOT NULL,
  PRIMARY KEY (`produc_codigo`),
  FOREIGN KEY (`produc_gama_gama`)
  REFERENCES `workshop_BD`.`gama` (`gama_gama`));

-- -----------------------------------------------------
-- Table `workshop_BD`.`detalle_pedido`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `workshop_BD`.`detalle_pedido` (
  `deta_cantidad` INT NOT NULL,
  `deta_pedi_codigo` INT NOT NULL,
  `deta_produc_codigo` INT NOT NULL,
  PRIMARY KEY (`deta_pedi_codigo`, `deta_produc_codigo`),
  FOREIGN KEY (`deta_pedi_codigo`)
  REFERENCES `workshop_BD`.`pedido` (`pedi_codigo`),
  FOREIGN KEY (`deta_produc_codigo`)
  REFERENCES `workshop_BD`.`producto` (`produc_codigo`));
