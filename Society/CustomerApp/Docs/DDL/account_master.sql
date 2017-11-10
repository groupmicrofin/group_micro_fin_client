CREATE TABLE `societyclient`.`account_master` (
  `ID` INT(11) NOT NULL AUTO_INCREMENT,
  `society_master_id` INT(11) DEFAULT NULL,
  `society_account_refNo` VARCHAR(45) DEFAULT NULL,
  `member_name` VARCHAR(60) DEFAULT NULL,
  `email` VARCHAR(45) DEFAULT NULL,
  `phone_no` VARCHAR(45) DEFAULT NULL,
  `photo_id` VARCHAR(45) DEFAULT NULL,
  `last_meeting_dt` DATE DEFAULT NULL,
   `alert_dttm` DATETIME DEFAULT NULL,
  `user` VARCHAR(45) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `fk_society_master_idx` (`society_master_id`),
  CONSTRAINT `fk_society_master` FOREIGN KEY (`society_master_id`) REFERENCES `society_master` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ;