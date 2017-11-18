package com.aglifetech.society.cust.util;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

public class MeetingDateUtilTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetNextMeetingDate() {

		LocalDate currDate = LocalDate.now();
		String cronString = "0 0 0 ? * 0L";

		LocalDate nextDate;
		nextDate = MeetingDateUtil.getNextMeetingDate(currDate, cronString);

		// assertTrue(nextDate.equals(currDate.plusMonths(1L)));
	}

}
