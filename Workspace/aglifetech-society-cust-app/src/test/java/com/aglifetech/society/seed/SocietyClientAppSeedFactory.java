package com.aglifetech.society.seed;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.aglifetech.society.cust.model.MeetingEntry;
import com.aglifetech.society.cust.model.Society;
import com.aglifetech.society.cust.model.SocietyAccount;

public class SocietyClientAppSeedFactory {

	public static Society getSocietyDummy(Long societyId) {
		Society sc = new Society();
		sc.setId(societyId);
		sc.setSocietyRefId("33");
		sc.setSocietyName("Gokul");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
		String date = "26/01/2017";
		LocalDate localDate = LocalDate.parse(date, formatter);
		sc.setSocietyStartDate(localDate);
		sc.setShareAmount(100);
		sc.setIntrestRate(12);
		sc.setScheduleFrequency("0 0 0 ? * 0L *");
		return sc;
	}

	public static SocietyAccount getSocietyAccountDummy(Long societyId, Long societyAccountId) {

		SocietyAccount societyAcc = new SocietyAccount();
		societyAcc.setid(societyAccountId);
		societyAcc.setSocietyMasterID(societyId);
		societyAcc.setSocietyAccountId("13");
		societyAcc.setMemberName("Medha Patel");
		// societyAcc.setPendingPrincipalLoanAmount(1000);
		societyAcc.setEmailId("medha@abc");
		societyAcc.setPhoneNum("90909090");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
		String date = "30/09/2017";
		LocalDate localDate = LocalDate.parse(date, formatter);

		// societyAcc.setLastMeetingDate(localDate);
		societyAcc.setPhotoId("aadhar");

		return societyAcc;
	}

	public static MeetingEntry getMeetingEntryDtl(Long societyId, Long societyAccountId, double loanDisbus,
			double totalpaid, LocalDate meetingDate) {

		MeetingEntry meetingEntry = new MeetingEntry();
		meetingEntry.setSocietyMasterId(societyId);
		meetingEntry.setSocietyAccountMasterId(societyAccountId);
		meetingEntry.setLoanDisbursedAmount(loanDisbus);

		meetingEntry.setTotalPaidAmount(totalpaid);
		meetingEntry.setMeetingDate(meetingDate);

		return meetingEntry;

	}

}
