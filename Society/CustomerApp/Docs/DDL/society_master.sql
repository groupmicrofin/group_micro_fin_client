CREATE TABLE `societyclient`.`society_master` (
  `ID` INT(11) NOT NULL AUTO_INCREMENT,
  `society_ref_no` VARCHAR(20) DEFAULT NULL,
  `society_name` VARCHAR(45) DEFAULT NULL,
  `society_start_dt` DATE DEFAULT NULL,
  `share_amount` DOUBLE DEFAULT NULL,
  `int_rate` DOUBLE DEFAULT NULL,
  `schedule_frequency` VARCHAR(20) DEFAULT NULL,
 
  `created_dttm` DATETIME DEFAULT NULL,
  `last_updated_dttm` DATETIME DEFAULT NULL,
  `user` VARCHAR(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ID_UNIQUE` (`ID`),
  KEY `index3` (`society_ref_no`)
) ;