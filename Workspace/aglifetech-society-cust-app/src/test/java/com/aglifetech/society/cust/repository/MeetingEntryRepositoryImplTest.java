package com.aglifetech.society.cust.repository;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;

import com.aglifetech.society.cust.model.MeetingEntry;
import com.aglifetech.society.cust.model.SocietyAccount;

public class MeetingEntryRepositoryImplTest {

	private MeetingEntryRepository meetEntryRepo;

	@Before
	public void setUp() throws Exception {
		meetEntryRepo = new MeetingEntryRepositoryImpl();
	}

	@Test
	public void testAddMeeting() {
		MeetingEntry meetingEntry = getMeeting();

		int result = meetEntryRepo.addMeeting(meetingEntry);

		assertTrue(result == 1);

	}

	public MeetingEntry getMeeting() {
		MeetingEntry meetingEntry = new MeetingEntry();
		meetingEntry.setSocietyMasterId(1L);
		meetingEntry.setSocietyAccountMasterId(1L);
		meetingEntry.setLoanDisbursedAmount(0);
		//meetingEntry.setLoanInastallIntAmt(100);
		meetingEntry.setTotalPaidAmount(200);
		meetingEntry.setMeetingDate(LocalDate.now());
		meetingEntry.setCreatedDateTm(LocalDateTime.now());
		meetingEntry.setUser("JJTOPIVAlA");
		return meetingEntry;
	}

}
