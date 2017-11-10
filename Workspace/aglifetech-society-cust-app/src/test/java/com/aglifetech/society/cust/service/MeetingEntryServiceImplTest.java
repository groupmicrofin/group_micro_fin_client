package com.aglifetech.society.cust.service;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Ignore;

import com.aglifetech.society.cust.model.MeetingEntry;

public class MeetingEntryServiceImplTest {

	MeetingEntryService meetService;

	@Before
	public void setup() {
		meetService = new MeetingServiceImpl();
	}

	@Ignore
	public void meetingServiceTest() {
		MeetingEntry meetEntry = getMeetingEntrydtl();
		meetService.addMeeting(meetEntry);

	}

	@Ignore
	public void meetingServiceWithLoanTest() {
		MeetingEntry meetEntry = getMeetingEntrydtl();
		meetEntry.setLoanDisbursedAmount(1000);
		meetService.addMeeting(meetEntry);

	}

	@Ignore
	public void meetingServiceWithInsatllAmtTest() {
		MeetingEntry meetEntry = getMeetingEntrydtl();
		meetEntry.setTotalPaidAmount(400);
		meetService.addMeeting(meetEntry);
	}

	public MeetingEntry getMeetingEntrydtl() {
		MeetingEntry meetingEntry = new MeetingEntry();
		meetingEntry.setSocietyMasterId(1L);
		meetingEntry.setSocietyAccountMasterId(1L);
		meetingEntry.setLoanDisbursedAmount(0);
		// meetingEntry.setLoanInastallIntAmt(100);
		meetingEntry.setTotalPaidAmount(100);
		meetingEntry.setMeetingDate(LocalDate.now());
		meetingEntry.setCreatedDateTm(LocalDateTime.now());
		meetingEntry.setUser("JJTOPIVAlA");
		return meetingEntry;

	}

}
