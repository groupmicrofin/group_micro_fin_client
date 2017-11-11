package com.aglifetech.society.cust.validation;

import java.time.LocalDate;

import com.aglifetech.society.cust.model.Society;

public class SocietyValidator {
	
	public void validateSocietyData(Society society) {
		String societyName = society.getSocietyName();
			if (societyName == null) {
				throw new RuntimeException("Society Name  is Required field");
			}
		LocalDate socSterDate = society.getSocietyStartDate();
			if (socSterDate == null) {
				throw new RuntimeException("Society Start Date is Required field");
			}
		double shareAmount = society.getShareAmount();
			if (shareAmount==0.0) {
			throw new RuntimeException("Share Amount is Required field");
			}
		String schFreq =society.getScheduleFrequency();
			if (schFreq==null) {
				throw new RuntimeException("Schedule Frequency is not got Fill Up Required field for that");
			
			}
			
		double intRate =society.getIntrestRate();
			if (intRate == 0.0) {
				throw new RuntimeException("Interest Rate is Required field");
			}
		
	}

}
