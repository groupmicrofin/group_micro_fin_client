package com.aglifetech.society.cust.validator;

import java.time.LocalDate;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.aglifetech.society.cust.validation.MeetingEntryValidator;

public class MeettingEntryValidatorTest {

	MeetingEntryValidator meetingEntryValidator;

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		meetingEntryValidator = new MeetingEntryValidator();
	}

	@Test
	public void validateMeetingDateTestSuccess() {
		LocalDate lastMeetingDate = LocalDate.of(2016, 1, 1);
		LocalDate currentMeetingDate = LocalDate.of(2016, 2, 1);
		meetingEntryValidator.validateMeetingDate(lastMeetingDate, currentMeetingDate);
		lastMeetingDate = LocalDate.of(2016, 2, 10);
		currentMeetingDate = LocalDate.of(2016, 3, 20);
		meetingEntryValidator.validateMeetingDate(lastMeetingDate, currentMeetingDate);
		lastMeetingDate = LocalDate.of(2016, 11, 25);
		currentMeetingDate = LocalDate.of(2016, 12, 1);
		meetingEntryValidator.validateMeetingDate(lastMeetingDate, currentMeetingDate);

	}

	@Test
	public void validateMeetingDateTestFailure() {
		LocalDate lastMeetingDate = LocalDate.of(2016, 1, 1);
		LocalDate currentMeetingDate = LocalDate.of(2016, 3, 1);
		/*
		 * boolean testSuccess = false; try {
		 * meetingEntryValidator.validateMeetingDate(lastMeetingDate,
		 * currentMeetingDate); } catch (RuntimeException rte) { testSuccess = true; }
		 * assertTrue(testSuccess);
		 */
		expectedException.expect(RuntimeException.class);
		expectedException.expectMessage(Matchers.containsString("2-2016"));
		meetingEntryValidator.validateMeetingDate(lastMeetingDate, currentMeetingDate);

		lastMeetingDate = LocalDate.of(2016, 12, 1);
		currentMeetingDate = LocalDate.of(2017, 2, 1);

		expectedException.expect(RuntimeException.class);
		expectedException.expectMessage(Matchers.containsString("1-2017"));
		meetingEntryValidator.validateMeetingDate(lastMeetingDate, currentMeetingDate);

		lastMeetingDate = LocalDate.of(2017, 1, 1);
		currentMeetingDate = LocalDate.of(2016, 2, 1);

		expectedException.expect(RuntimeException.class);
		expectedException.expectMessage(Matchers.containsString("2-2017"));
		meetingEntryValidator.validateMeetingDate(lastMeetingDate, currentMeetingDate);

	}

}
