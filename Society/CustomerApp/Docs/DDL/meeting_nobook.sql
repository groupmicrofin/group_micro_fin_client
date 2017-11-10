CREATE TABLE `meeting_nobook` (
  `ID` INT(11) NOT NULL AUTO_INCREMENT,
  `society_master_id` INT(11) DEFAULT NULL,
  `account_master_id` INT(11) DEFAULT NULL,
  `ln_disbursed_amt` DOUBLE DEFAULT NULL,
  `total_paid_amt` DOUBLE DEFAULT NULL,
  `meeting_dt` DATE DEFAULT NULL,
  `user` VARCHAR(45) DEFAULT NULL,
  `created_dttm` DATETIME DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `fk_sociey_mas_Id_idx` (`society_master_id`),
  KEY `fk_society_Ac_mas_Id_idx` (`account_master_id`),
  CONSTRAINT `fk_society_Ac_mas_Id` FOREIGN KEY (`account_master_id`) REFERENCES `account_master` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_sociey_mas_Id` FOREIGN KEY (`society_master_id`) REFERENCES `society_master` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=INNODB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8