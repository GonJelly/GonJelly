-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema happyhouse
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema happyhouse
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `happyhouse` DEFAULT CHARACTER SET utf8mb3 ;
USE `happyhouse` ;

-- -----------------------------------------------------
-- Table `happyhouse`.`city`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `happyhouse`.`city` (
  `Sigungu_code` VARCHAR(30) NOT NULL,
  `city_sido` VARCHAR(30) NOT NULL,
  `city_gungu` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`Sigungu_code`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `happyhouse`.`dong`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `happyhouse`.`dong` (
  `Eubmyundong_code` VARCHAR(30) NOT NULL COMMENT '법정동읍면동코드',
  `Sigungu_code` VARCHAR(45) NOT NULL COMMENT '법정동시군구코드',
  `Dong` VARCHAR(45) NOT NULL COMMENT '법정동',
  `dong_YN` TINYINT NOT NULL DEFAULT '1' COMMENT '존재여부',
  PRIMARY KEY (`Eubmyundong_code`, `Sigungu_code`),
  INDEX `dongToCity_idx` (`Sigungu_code` ASC) VISIBLE,
  CONSTRAINT `dongToCity`
    FOREIGN KEY (`Sigungu_code`)
    REFERENCES `happyhouse`.`city` (`Sigungu_code`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `happyhouse`.`road`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `happyhouse`.`road` (
  `Road_code` VARCHAR(30) NOT NULL COMMENT '도로명코드',
  `Road_Seq` VARCHAR(45) NULL DEFAULT NULL COMMENT '도로명일련번호코드',
  `Sigungu_Code` VARCHAR(45) NULL DEFAULT NULL COMMENT '도로명시군구코드',
  `Road_Bonbun` VARCHAR(45) NULL DEFAULT NULL COMMENT '도로명건물본번호코드',
  `Road_Bubun` VARCHAR(45) NULL DEFAULT NULL COMMENT '도로명건물부번호코드',
  `Road_Basement_Code` VARCHAR(45) NULL DEFAULT NULL COMMENT '도로명지상지하코드',
  `Road_Name` VARCHAR(45) NULL DEFAULT NULL COMMENT '도로명',
  PRIMARY KEY (`Road_code`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `happyhouse`.`houseinfo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `happyhouse`.`houseinfo` (
  `house_idx` INT NOT NULL AUTO_INCREMENT,
  `house_Code` VARCHAR(40) NOT NULL COMMENT '일련번호',
  `house_Name` VARCHAR(45) NOT NULL COMMENT '아파트명',
  `build_Year` VARCHAR(45) NOT NULL COMMENT '건축년도',
  `Deal_Year` VARCHAR(45) NOT NULL COMMENT '계약년도',
  `Deal_Month` VARCHAR(45) NOT NULL COMMENT '계약월',
  `Deal_Day` VARCHAR(45) NOT NULL COMMENT '계약일',
  `Deal_Amount` VARCHAR(45) NULL DEFAULT NULL COMMENT '거래금액',
  `Use_Area` VARCHAR(45) NOT NULL COMMENT '전용면적',
  `Floor` VARCHAR(45) NOT NULL COMMENT '층',
  `Sigungu_code` VARCHAR(45) NOT NULL,
  `Eubmyundong_code` VARCHAR(45) NOT NULL,
  `Road_code` VARCHAR(45) NOT NULL,
  `Bonbun` VARCHAR(45) NOT NULL COMMENT '법정동본번코드',
  `Bubun` VARCHAR(45) NOT NULL COMMENT '법정동부번코드',
  `Jibun` VARCHAR(45) NOT NULL COMMENT '지번',
  PRIMARY KEY (`house_idx`),
  INDEX `houseToCity_idx` (`Sigungu_code` ASC) VISIBLE,
  INDEX `houseToDong_idx` (`Eubmyundong_code` ASC) VISIBLE,
  INDEX `houseToLoad_idx` (`Road_code` ASC) VISIBLE,
  CONSTRAINT `houseToCity`
    FOREIGN KEY (`Sigungu_code`)
    REFERENCES `happyhouse`.`city` (`Sigungu_code`),
  CONSTRAINT `houseToDong`
    FOREIGN KEY (`Eubmyundong_code`)
    REFERENCES `happyhouse`.`dong` (`Eubmyundong_code`),
  CONSTRAINT `houseToLoad`
    FOREIGN KEY (`Road_code`)
    REFERENCES `happyhouse`.`road` (`Road_code`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;

CREATE TABLE IF NOT EXISTS `happyhouse`.`member` (
  memberId VARCHAR(45) NOT NULL,
  memberPw VARCHAR(45) NOT NULL,
  name VARCHAR(45) NOT NULL,
  mobile VARCHAR(45) NOT NULL,
  email VARCHAR(45) NOT NULL,
  age VARCHAR(45) NULL,
  entryDate VARCHAR(45) NULL,
  PRIMARY KEY (memberId))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `happyhouse`.`like`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `happyhouse`.`like` (
  `memberId` VARCHAR(20) NOT NULL,
  `Eubmyundong_code` VARCHAR(45) NULL DEFAULT NULL,
  `Sigungu_code` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`memberId`),
  UNIQUE INDEX `memberId_UNIQUE` (`memberId` ASC) VISIBLE,
  INDEX `Eubmyundong_code_idx` (`Eubmyundong_code` ASC) VISIBLE,
  INDEX `Sigungu_code_idx` (`Sigungu_code` ASC) VISIBLE,
  CONSTRAINT `Eubmyundong_code`
    FOREIGN KEY (`Eubmyundong_code`)
    REFERENCES `happyhouse`.`dong` (`Eubmyundong_code`),
  CONSTRAINT `memberId`
    FOREIGN KEY (`memberId`)
    REFERENCES `happyhouse`.`member` (`memberId`),
  CONSTRAINT `Sigungu_code`
    FOREIGN KEY (`Sigungu_code`)
    REFERENCES `happyhouse`.`dong` (`Sigungu_code`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
