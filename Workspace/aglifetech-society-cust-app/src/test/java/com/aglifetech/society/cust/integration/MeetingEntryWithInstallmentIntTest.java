package com.aglifetech.society.cust.integration;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.Before;
import org.junit.Ignore;

import com.aglifetech.society.cust.model.MeetingEntry;
import com.aglifetech.society.cust.model.Society;
import com.aglifetech.society.cust.model.SocietyAccount;
import com.aglifetech.society.cust.service.MeetingEntryService;
import com.aglifetech.society.cust.service.MeetingServiceImpl;
import com.aglifetech.society.cust.service.SocietyAccountService;
import com.aglifetech.society.cust.service.SocietyAccountServiceImpl;
import com.aglifetech.society.cust.service.SocietyDashBoardService;
import com.aglifetech.society.cust.service.SocietyDashBoardServiceImpl;
import com.aglifetech.society.cust.service.SocietyService;
import com.aglifetech.society.cust.service.SocietyServiceImpl;

public class MeetingEntryWithInstallmentIntTest {
	private SocietyService societyService;
	private SocietyAccountService socAcService;
	private MeetingEntryService meetService;
	private SocietyDashBoardService socDashService;

	@Before
	public void setUp() {
		societyService = new SocietyServiceImpl();
		socAcService = new SocietyAccountServiceImpl();
		meetService = new MeetingServiceImpl();
		socDashService = new SocietyDashBoardServiceImpl();
	}

	@Ignore
	public void meetingEntryWithInstallmentTest() {
		// 2.1
		/*
		 * Society society = getSociety(); societyService.addSociety(society);
		 */
		// 2.2
		/*
		 * SocietyAccount socAc = getSocietyAccount();
		 * socAcService.addSocietyAccount(socAc);
		 */
		/// *
		// First meeting with 1000 loan
		/// *
		// MeetingEntry meetingEntry = getMeetingEntryDtl();
		/*
		 * meetingEntry.setLoanDisbursedAmount(0);
		 * 
		 * meetingEntry.setMeetingDate(socAc.getLastMeetingDate());
		 */
		// meetService.addMeeting(meetingEntry);
		// */
		// Second meeting with installment payment
		// /*
		MeetingEntry meetingEntry = getMeetingEntryDtl();
		meetingEntry.setLoanDisbursedAmount(1000);
		meetingEntry.setTotalPaidAmount(810);
		meetingEntry.setMeetingDate(LocalDate.now().plusMonths(3));
		meetService.addMeeting(meetingEntry);
		// */
		// SocietyAccountReport socAcRepo =socDashService.getDashBoard(8L);
		// System.out.println(socAcRepo.toString());
	}

	public Society getSociety() {
		Society sc = new Society();
		sc.setSocietyRefId("11");
		sc.setSocietyName("Gokul");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
		String date = "23/10/2017";
		LocalDate localDate = LocalDate.parse(date, formatter);
		sc.setSocietyStartDate(localDate);
		sc.setShareAmount(100);
		sc.setIntrestRate(12);
		// sc.setUser("jig");
		sc.setScheduleFrequency("0 0 0 L * ? *");

		// TODO
		return sc;

	}

	public SocietyAccount getSocietyAccount() {

		SocietyAccount societyAcc = new SocietyAccount();

		societyAcc.setSocietyMasterID(22L);
		societyAcc.setSocietyAccountId("13");
		societyAcc.setMemberName("Medha Patel");
		//societyAcc.setPendingPrincipalLoanAmount(0);
		societyAcc.setEmailId("medha@abc");
		societyAcc.setPhoneNum("9090909090");

		societyAcc.setLastMeetingDate(LocalDate.now());
		societyAcc.setPhotoId("aadhar");

		return societyAcc;
	}

	public MeetingEntry getMeetingEntryDtl() {

		MeetingEntry meetingEntry = new MeetingEntry();
		meetingEntry.setSocietyMasterId(22L);
		meetingEntry.setSocietyAccountMasterId(9L);
		meetingEntry.setLoanDisbursedAmount(1000);

		meetingEntry.setTotalPaidAmount(100);
		meetingEntry.setMeetingDate(LocalDate.now());

		return meetingEntry;

	}

}
