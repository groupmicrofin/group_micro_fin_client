package com.aglifetech.society.cust.integration;

import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.Before;
import org.junit.Test;

import com.aglifetech.society.cust.model.LoanMaster;
import com.aglifetech.society.cust.model.MeetingEntry;
import com.aglifetech.society.cust.model.Society;
import com.aglifetech.society.cust.model.SocietyAccount;
import com.aglifetech.society.cust.model.SocietyAccountReport;
import com.aglifetech.society.cust.repository.LoanMasterRepository;
import com.aglifetech.society.cust.repository.LoanMasterRepositoryImpl;
import com.aglifetech.society.cust.repository.SocietyAccountRepository;
import com.aglifetech.society.cust.repository.SocietyAccountRepositoryImpl;
import com.aglifetech.society.cust.repository.SocietyRepository;
import com.aglifetech.society.cust.repository.SocietyRepositoryImpl;
import com.aglifetech.society.cust.service.MeetingEntryService;
import com.aglifetech.society.cust.service.MeetingServiceImpl;
import com.aglifetech.society.cust.service.SocietyAccountService;
import com.aglifetech.society.cust.service.SocietyAccountServiceImpl;
import com.aglifetech.society.cust.service.SocietyDashBoardService;
import com.aglifetech.society.cust.service.SocietyDashBoardServiceImpl;
import com.aglifetech.society.cust.service.SocietyService;
import com.aglifetech.society.cust.service.SocietyServiceImpl;
import com.aglifetech.society.seed.SocietyClientAppSeedFactory;

public class MeetingEntryTestWithDeleteInstallment {
	private SocietyRepository socRepo;
	private SocietyService societyService;
	private SocietyAccountService socAcService;
	private SocietyAccountRepository socAcRepo;
	private MeetingEntryService meetingService;
	private LoanMasterRepository loanRepo;
	private SocietyDashBoardService socDashService;

	@Before
	public void setUp() {
		socRepo = new SocietyRepositoryImpl();
		societyService = new SocietyServiceImpl();
		socAcService = new SocietyAccountServiceImpl();
		meetingService = new MeetingServiceImpl();
		socAcRepo = new SocietyAccountRepositoryImpl();
		loanRepo = new LoanMasterRepositoryImpl();
		socDashService = new SocietyDashBoardServiceImpl();
	}

	@Test
	public void meetingEntryWithInstallmentAndLoanNobookTest() {
		// 3.1
		Society society = SocietyClientAppSeedFactory.getSocietyDummy(2L);
		society.setScheduleFrequency("0 0 0 ? * 0L *");
		societyService.addSociety(society);
		// Validate 3.1
		Society soc = socRepo.findSocietyById(society.getId());
		// findSocietyById society, getDate == , getShareAmount = 100
		DateTimeFormatter format = DateTimeFormatter.ofPattern("d/MM/yyyy");

		// AssertTest
		assertTrue(soc.getShareAmount() == 100);
		assertTrue(soc.getIntrestRate() == 12);
		assertTrue(soc.getSocietyStartDate().equals(LocalDate.parse("26/01/2017", format)));
		assertTrue(soc.getSocietyRefId().equals("33"));

		// 3.2
		SocietyAccount socAc = SocietyClientAppSeedFactory.getSocietyAccountDummy(society.getId(), 5L);
		socAcService.addSocietyAccount(socAc);

		// Validate 3.2
		SocietyAccount socAccount = socAcRepo.findSocietyAccountById(socAc.getid());
		assertTrue(socAccount.getSocietyMasterID().equals(soc.getId()));
		assertTrue(socAccount.isFirstMeeting());
		System.out.println("###### Value of last Meeting Date:" + socAccount.getLastMeetingDate());
		// 3.3
		MeetingEntry meetingEntry = SocietyClientAppSeedFactory.getMeetingEntryDtl(society.getId(), socAc.getid(), 1000,
				100, LocalDate.of(2017, 10, 31));
		meetingService.addMeeting(meetingEntry);
		// Validate 3.3
		LoanMaster loanMaster = loanRepo.findLoanDetailByAccountId(socAccount.getid());
		assertTrue(loanMaster.getLoanDisbusmentAmount() == 1000 || loanMaster.getPendingPrincipleLoan() == 1000
				|| loanMaster.getTotalIntrestPaid() == 0 || loanMaster.getAccountStatus() == 1);
		// Report service.. get report... value assert int loan installment... //
		// loanservice.findLoan ... getLoan install_paid int_paid ==0

		// Validate 3.3 Report
		SocietyAccountReport socAcReport = socDashService.getDashBoard(socAc.getid());
		System.out.println("3.3:-" + socAcReport.toString());

		assertTrue(socAcReport.getTotalNoOfMeetings() == 10);
		assertTrue(socAcReport.getTotalShareAmount() == 1000);
		assertTrue(socAcReport.getTotalLoanAmount() == 1000);
		assertTrue(socAcReport.getPendingPrincipleLoanAmount() == 1000);
		assertTrue(socAcReport.getAmountNeedToCloseAllLoan() == 1010);
		assertTrue(socAcReport.getNextMeetingDate().equals(LocalDate.of(2017, 11, 26)));

		// 3.5
		socAc = socAcRepo.findSocietyAccountById(socAc.getid());
		meetingEntry = SocietyClientAppSeedFactory.getMeetingEntryDtl(society.getId(), socAc.getid(), 0, 400,
				socAc.getLastMeetingDate().plusDays(30));
		meetingService.addMeeting(meetingEntry);

		// Validate 3.5 Report

		socAcReport = socDashService.getDashBoard(socAc.getid());
		System.out.println(socAcReport.toString());
		assertTrue(socAcReport.getTotalNoOfMeetings() == 11);
		assertTrue(socAcReport.getTotalShareAmount() == 1100);

		assertTrue(socAcReport.getPendingPrincipleLoanAmount() == 710);
		assertTrue(socAcReport.getAmountNeedToCloseAllLoan() == 717.1);
		assertTrue(socAcReport.getNextMeetingDate().equals(LocalDate.of(2017, 12, 24)));
		// 3.7
		socAc = socAcRepo.findSocietyAccountById(socAc.getid());
		meetingEntry = SocietyClientAppSeedFactory.getMeetingEntryDtl(society.getId(), socAc.getid(), 2000, 400,
				socAc.getLastMeetingDate().plusDays(31));
		meetingService.addMeeting(meetingEntry); // Validate 3.7 Report socAcReport =
		socAcReport = socDashService.getDashBoard(socAc.getid());
		System.out.println(socAcReport.toString());
		assertTrue(socAcReport.getTotalNoOfMeetings() == 12);
		assertTrue(socAcReport.getTotalShareAmount() == 1200);

		assertTrue(socAcReport.getPendingPrincipleLoanAmount() == 2417.1);
		assertTrue(socAcReport.getAmountNeedToCloseAllLoan() == 2441.2709999999997);
		assertTrue(socAcReport.getNextMeetingDate().equals(LocalDate.of(2017, 1, 21)));

		// 3.9
		socAc = socAcRepo.findSocietyAccountById(socAc.getid());
		meetingEntry = SocietyClientAppSeedFactory.getMeetingEntryDtl(society.getId(), socAc.getid(), 0, 800,
				socAc.getLastMeetingDate().plusDays(31));
		meetingService.addMeeting(meetingEntry);

		// Validate 3.9 Report
		socAcReport = socDashService.getDashBoard(socAc.getid());
		System.out.println("3.9:-" + socAcReport.toString());
		assertTrue(socAcReport.getTotalNoOfMeetings() == 13);
		assertTrue(socAcReport.getTotalShareAmount() == 1300);

		assertTrue(socAcReport.getPendingPrincipleLoanAmount() == 1741.27);
		assertTrue(socAcReport.getAmountNeedToCloseAllLoan() == 1758.68);
		assertTrue(socAcReport.getNextMeetingDate().equals(LocalDate.of(2017, 02, 25)));
		meetingService.deleteLastMeeting(meetingEntry);
		meetingEntry.setMeetingDate(LocalDate.of(2017, 12, 31));
		meetingService.deleteLastMeeting(meetingEntry);
		meetingEntry.setMeetingDate(LocalDate.of(2017, 11, 30));
		meetingService.deleteLastMeeting(meetingEntry);
		meetingEntry.setMeetingDate(LocalDate.of(2017, 10, 31));
		meetingService.deleteLastMeeting(meetingEntry);
		/*
		 * meetingEntry.setMeetingDate(LocalDate.of(2017,9,30));
		 * meetingService.deleteLastMeeting(meetingEntry);
		 */
	}

}
