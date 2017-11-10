CREATE TABLE `loan_nobook` (
  `loan_master_id` INT(11) NOT NULL,
  `txn_code`VARCHAR(45) DEFAULT NULL,
  `txn_amount` DOUBLE NOT NULL,
  `meeting_dt` DATE NOT NULL,
  `audit_created_dttm` DATETIME NOT NULL,
  KEY `fk_loan_master` (`loan_master_id`),
  CONSTRAINT `fk_loan_master` FOREIGN KEY (`loan_master_id`) REFERENCES `loan_master` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=INNODB DEFAULT CHARSET=utf8
