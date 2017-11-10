package com.aglifetech.society.cust.service;

import java.util.List;

import com.aglifetech.society.cust.model.LoanEntryBook;
import com.aglifetech.society.cust.model.LoanMaster;
import com.aglifetech.society.cust.model.MeetingEntry;
import com.aglifetech.society.cust.model.Society;
import com.aglifetech.society.cust.model.SocietyAccount;
import com.aglifetech.society.cust.repository.LoanEntryBookRepository;
import com.aglifetech.society.cust.repository.LoanEntryBookRepositoryImpl;
import com.aglifetech.society.cust.repository.LoanMasterRepository;
import com.aglifetech.society.cust.repository.LoanMasterRepositoryImpl;
import com.aglifetech.society.cust.repository.MeetingEntryRepository;
import com.aglifetech.society.cust.repository.MeetingEntryRepositoryImpl;
import com.aglifetech.society.cust.repository.SocietyAccountRepository;
import com.aglifetech.society.cust.repository.SocietyAccountRepositoryImpl;
import com.aglifetech.society.cust.repository.SocietyRepository;
import com.aglifetech.society.cust.repository.SocietyRepositoryImpl;
import com.aglifetech.society.cust.validation.MeetingEntryValidator;

public class MeetingServiceImpl implements MeetingEntryService {
	private MeetingEntryRepository meetingEntryRepo;
	private LoanMasterRepository loanmasterRpo;
	private SocietyRepository socRepo;
	private SocietyAccountRepository socAcRepo;
	private LoanMasterService loanService;
	private SocietyAccountService socAcService;
	private LoanEntryBookService loanEntryBookService;
	private MeetingEntryValidator meetingEntryValidator;
	private LoanEntryBookRepository loanEntryRepo;
	
	public MeetingServiceImpl() {
		meetingEntryRepo = new MeetingEntryRepositoryImpl();
		loanmasterRpo = new LoanMasterRepositoryImpl();
		socRepo = new SocietyRepositoryImpl();
		socAcRepo = new SocietyAccountRepositoryImpl();
		
		loanService = new LoanMasterServiceImpl();
		loanEntryBookService = new LoanEntryBookServiceImpl();
		socAcService = new SocietyAccountServiceImpl();
		meetingEntryValidator = new MeetingEntryValidator();
		loanEntryRepo = new LoanEntryBookRepositoryImpl();
	}
	
	@Override
	public void addMeeting(MeetingEntry meetingEntry) {
		
		// 1. Meeting Next Date appropriate set, Post success of meeting entry it should
		// update account master last_meeting_dt and alert_dttm
		// 3. Interest rate coming as 0 on first installment
		meetingEntry.setUser("SYSTEM");
		// Get Society
		SocietyAccount socAc = socAcRepo.findSocietyAccountById(meetingEntry.getSocietyAccountMasterId());
		Society soc = socAcService.getSocietyByAccountId(meetingEntry.getSocietyMasterId(),
		        meetingEntry.getSocietyAccountMasterId());
		
		meetingEntryValidator.validateMeeting(meetingEntry, socAc);
		
		double shareAmt = soc.getShareAmount();
		double loanDis = meetingEntry.getLoanDisbursedAmount();
		double paidAmt = meetingEntry.getTotalPaidAmount();
		double installMentAmt = paidAmt - shareAmt;
		// Add Meeting entry
		meetingEntryRepo.addMeeting(meetingEntry);
		// societyAccount service method... updateMeetingDate SocietyAccount parameter1,
		// update to db
		// meeting date...
		socAcService.updateMeetingDate(socAc, meetingEntry.getMeetingDate());
		
		// Perform Loan installment & Interest charging
		loanService.performInstallments(meetingEntry.getSocietyAccountMasterId(), installMentAmt,
		        meetingEntry.getMeetingDate());
		
		// Add Loan
		if (loanDis > 0) {
			LoanMaster loanMaster = new LoanMaster();
			loanMaster.setLoanDisbusmentAmount(loanDis);
			loanMaster.setPendingPrincipleLoan(loanDis);
			loanMaster.setAccountMasterId(meetingEntry.getSocietyAccountMasterId());
			loanMaster.setAccountStatus(1);
			loanMaster.setTotalIntrestPaid(0);
			loanMaster.setDisbursementDate(meetingEntry.getMeetingDate());
			loanmasterRpo.addLoanDetail(loanMaster);
			
			loanEntryBookService.addLoanEntryBookDetail(loanMaster.getId(), loanMaster.getLoanDisbusmentAmount(),
			        "LN_DISBUS", loanMaster.getDisbursementDate());
		}
	}
	
	@Override
	public void deleteLastMeeting(MeetingEntry meetingEntry) {
		SocietyAccount socAc = socAcRepo.findSocietyAccountById(meetingEntry.getSocietyAccountMasterId());
		List<LoanEntryBook> loanEntryDtl = loanEntryRepo.findLoanEntryDetailsByMeetingDate(
		        meetingEntry.getSocietyAccountMasterId(), socAc.getLastMeetingDate());
		
		for (LoanEntryBook loanBookDtl : loanEntryDtl) {
			String txnCode = loanBookDtl.getTractionCode();
			LoanMaster loanMaster = loanmasterRpo.findLoanDetailByLoanMasterId(loanBookDtl.getLoanMasterId());
			//Below is to make loan active if Loan was closed on Last Meeting
			double pendingLoan = loanMaster.getPendingPrincipleLoan();
			if (pendingLoan == 0) {
				loanMaster.setAccountStatus(1);
				loanMaster.setCloseDate(null);
			}
			double intAmount = loanMaster.getTotalIntrestPaid();
			//LoanDisbus=1000 > Delete loanMaster and LoanEntryBook 
			if (txnCode.equals("INT_CHRG")) {
				double interestCharge = loanBookDtl.getTxnAmt();
				pendingLoan = pendingLoan - interestCharge;
				double remainingInt = intAmount - interestCharge;
				loanMaster.setTotalIntrestPaid(remainingInt);
				loanMaster.setPendingPrincipleLoan(pendingLoan);
				loanmasterRpo.updateDetail(loanMaster);
				loanEntryRepo.deleteLoanEntryDetail(loanBookDtl);
				
			} else if (txnCode.equals("INST_PAID")) {
				double installmentPaid = loanBookDtl.getTxnAmt();
				pendingLoan = pendingLoan + installmentPaid;
				loanMaster.setPendingPrincipleLoan(pendingLoan);
				loanmasterRpo.updateDetail(loanMaster);
				loanEntryRepo.deleteLoanEntryDetail(loanBookDtl);
			} else if (txnCode.equals("LN_DISBUS")) {
				
			}
			meetingEntryRepo.deleteMeeting(meetingEntry);
		}
		
	}
	
}
