package com.aglifetech.society.cust.validation;

import java.time.LocalDate;

import com.aglifetech.society.cust.model.SocietyAccount;

public class SocietyAccountValidator {
	
    	public void validatesocietyAccountData(SocietyAccount societyAccount) {
    		
    		
    		String socRefId = societyAccount.getSocietyAccountId();
	    		if (socRefId == null) {
					throw new RuntimeException("Society Reference Id is Required field");
				}
    		String memberName = societyAccount.getMemberName();
	    		if (memberName == null) {
					throw new RuntimeException("Member Name is Required field");
				}
    		String email = societyAccount.getEmailId();
	    		if (email == null ) {
					throw new RuntimeException(" Email address is Required field");
	    		}
    		String phoneNum = societyAccount.getPhoneNum();
	    		if (phoneNum == null ) {
					throw new RuntimeException(" Phone Number is Required field");
				} else if (phoneNum.length()<10  ) {
					throw new RuntimeException("Phone Number Length is Less then 10 digits it must be in 10 digits.");
				} else if (phoneNum.length()>10  ) {
					throw new RuntimeException("Phone Number Length is Greater then 10 digits it must be in 10 digits.");
				}
    		
    		LocalDate lastmeetDate = societyAccount.getLastMeetingDate();
    		if (lastmeetDate == null ) {
				throw new RuntimeException(" Last Meeting Date is Required field");
    		}
		
    	}
	
    	
}
