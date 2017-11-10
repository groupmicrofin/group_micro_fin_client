package com.aglifetech.society.cust.integration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.Before;
import org.junit.Test;

import com.aglifetech.society.cust.model.MeetingEntry;
import com.aglifetech.society.cust.model.Society;
import com.aglifetech.society.cust.model.SocietyAccount;
import com.aglifetech.society.cust.model.SocietyAccountReport;
import com.aglifetech.society.cust.service.MeetingEntryService;
import com.aglifetech.society.cust.service.MeetingServiceImpl;
import com.aglifetech.society.cust.service.SocietyAccountService;
import com.aglifetech.society.cust.service.SocietyAccountServiceImpl;
import com.aglifetech.society.cust.service.SocietyDashBoardService;
import com.aglifetech.society.cust.service.SocietyDashBoardServiceImpl;
import com.aglifetech.society.cust.service.SocietyService;
import com.aglifetech.society.cust.service.SocietyServiceImpl;

public class SocietyBasicTestWithOutLoan {

	private SocietyService societyService;
	private SocietyAccountService socAcService;
	private SocietyDashBoardService socDeshServices;
	private MeetingEntryService meetService;

	@Before
	public void setUp() throws Exception {
		societyService = new SocietyServiceImpl();
		socAcService = new SocietyAccountServiceImpl();
		socDeshServices = new SocietyDashBoardServiceImpl();
		meetService = new MeetingServiceImpl();
	}

	@Test
	public void test() {
		// 1.1
		// Society society = getSociety();
		// societyService.addSociety(society);
		SocietyAccount socAcc = getSocAc();
		socAcService.addSocietyAccount(socAcc);
		SocietyAccountReport societyAcReport = socDeshServices.getDashBoard(1L);
		SocietyAccountReport ReportExpected = getSocietyAcReport();

		// TODO Create society account object and add
		// TODO 1.2 Report object from Report Service inp -> societyAccount
		// TODO ReportExpected getReportTest1.1() ,
		// assertTrue(report.equals(reportExpected))
		// TODO 1.3
	}

	public Society getSociety() {
		Society sc = new Society();

		sc.setSocietyRefId("11");
		sc.setSocietyName("Gokul");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
		String date = "15/10/2017";
		LocalDate localDate = LocalDate.parse(date, formatter);
		sc.setSocietyStartDate(localDate);
		sc.setShareAmount(100);
		sc.setIntrestRate(12);
		sc.setUser("jig");
		sc.setScheduleFrequency("0 0 20 ? * 1L *");

		// TODO
		return sc;
	}

	public SocietyAccount getSocAc() {
		SocietyAccount societyAcc = new SocietyAccount();
		societyAcc.setid(1L);
		societyAcc.setSocietyMasterID(1L);
		societyAcc.setSocietyAccountId("12");
		societyAcc.setMemberName("Niju Patel");
		societyAcc.setEmailId("niju@abc");
		societyAcc.setPhoneNum("9898989898");
		societyAcc.setPhotoId("voterId");

		return societyAcc;
	}

	public SocietyAccountReport getSocietyAcReport() {

		SocietyAccountReport socAcRepo = new SocietyAccountReport();
		socAcRepo.setSocietyAccountId(1L);
		socAcRepo.setTotalNoOfMeetings(11);
		socAcRepo.setTotalShareAmount(1100);
		socAcRepo.setPendingPrincipleLoanAmount(0);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
		String date = "30/09/2017";
		LocalDate lastMeetDate = LocalDate.parse(date, formatter);
		socAcRepo.setLastMeetingDate(lastMeetDate);
		String nxtMeetDt = "30/10/2017";
		socAcRepo.setNextMeetingDate(LocalDate.parse(nxtMeetDt, formatter));
		return null;
	}

	public MeetingEntry getMeetingEntryDetail() {
		MeetingEntry meetingEntry = new MeetingEntry();
		meetingEntry.setSocietyMasterId(1L);
		meetingEntry.setSocietyAccountMasterId(1L);
		meetingEntry.setLoanDisbursedAmount(0);
		// meetingEntry.setLoanInastallIntAmt(100);
		meetingEntry.setTotalPaidAmount(200);
		meetingEntry.setMeetingDate(LocalDate.now());
		meetingEntry.setCreatedDateTm(LocalDateTime.now());
		meetingEntry.setUser("JJTOPIVAlA");
		return meetingEntry;
	}
}
