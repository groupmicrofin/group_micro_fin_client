CREATE TABLE `loan_master` (
  `Id` INT(11) NOT NULL AUTO_INCREMENT,
  `account_master_id` INT(11) DEFAULT NULL,
  `loan_disb_amount` DOUBLE DEFAULT NULL,
  `pending_princi_loan` DOUBLE DEFAULT NULL,
  `total_int_paid` DOUBLE DEFAULT NULL,
  `created_dttm` DATETIME DEFAULT NULL,
  `updated_dttm` DATETIME DEFAULT NULL,
  `account_status` INT(11) DEFAULT NULL,
  `disbursment_dt` DATE DEFAULT NULL,
  `close_dt` DATE DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `fkl_loan_master_idx` (`account_master_id`),
  CONSTRAINT `fkl_loan_master` FOREIGN KEY (`account_master_id`) REFERENCES `account_master` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=INNODB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8