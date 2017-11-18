package com.aglifetech.society.cust.validation;

import java.time.LocalDate;

import com.aglifetech.society.cust.model.MeetingEntry;
import com.aglifetech.society.cust.model.SocietyAccount;

public class MeetingEntryValidator {

	public void validateMeeting(MeetingEntry meetingEntry, SocietyAccount societyAccount) {
		// Meeting date should have difference of based on monthly frequency
		LocalDate lastMeetingDate = societyAccount.getLastMeetingDate();
		LocalDate currentMeetingDate = meetingEntry.getMeetingDate();
		if (!societyAccount.isFirstMeeting()) {
			validateMeetingDate(lastMeetingDate, currentMeetingDate);
		}

	}

	public void validateMeetingDate(LocalDate lastMeetingDate, LocalDate currentMeetingDate) {

		int lastMeetingMonth = lastMeetingDate.getMonthValue();
		int lastMeetingYear = lastMeetingDate.getYear();

		int currMeetingMonth = currentMeetingDate.getMonthValue();
		int currMeetingYear = currentMeetingDate.getYear();

		int expectedMonthOfMeeting = lastMeetingMonth + 1;
		int expectedYearOfMeeting = lastMeetingYear;
		if (lastMeetingMonth == 12) {
			expectedMonthOfMeeting = 1;
			expectedYearOfMeeting = expectedYearOfMeeting + 1;

		}

		if (currMeetingMonth != expectedMonthOfMeeting) {
			throw new RuntimeException("Next Meeting Date is not proper, expected to be on month-year:"
					+ expectedMonthOfMeeting + "-" + expectedYearOfMeeting);
		}
		if (currMeetingYear != expectedYearOfMeeting) {
			throw new RuntimeException("Next Meeting Date is not proper, expected to be of same year (month-year):"
					+ expectedMonthOfMeeting + "-" + expectedYearOfMeeting);
		}
	}

	public void validateLastMeeting(MeetingEntry meetingEntry, SocietyAccount societyAccount) {
		LocalDate lastMeetingDateFromMeeting = meetingEntry.getMeetingDate();
		LocalDate lastMeetingDateFromSocAc = societyAccount.getLastMeetingDate();

		if (lastMeetingDateFromSocAc == null) {
			throw new RuntimeException("There is no meeting to delete");
		}

		if (!lastMeetingDateFromMeeting.equals(societyAccount.getLastMeetingDate())) {
			throw new RuntimeException(
					"This is not Last Meeting ,Delete All Meeting which are attended after this Meeting And then This Meeetting Should able to delete");
		}

	}

	public void validationAllData(MeetingEntry meetingEntry, double shareAmt) {
		Long accountMasterId = meetingEntry.getSocietyAccountMasterId();
		if (accountMasterId == null) {
			throw new RuntimeException("Account Master Id is Mandatory Field.");
		}
		Long societyMasterId = meetingEntry.getSocietyMasterId();
		if (societyMasterId == null) {
			throw new RuntimeException("Society Master Id is Mandatory Field.");
		}
		double totalPaid = meetingEntry.getTotalPaidAmount();
		if (totalPaid < shareAmt) {
			throw new RuntimeException("You can not pay below Share Amount");
		}
		LocalDate meetingDate = meetingEntry.getMeetingDate();

		if (meetingDate == null) {
			throw new RuntimeException("You can not pay below Share Amount");
		}
	}
}
