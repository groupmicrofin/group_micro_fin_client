package com.aglifetech.society.cust.service;

import java.time.LocalDate;

import com.aglifetech.society.cust.model.LoanEntryBook;
import com.aglifetech.society.cust.repository.LoanEntryBookRepository;
import com.aglifetech.society.cust.repository.LoanEntryBookRepositoryImpl;

public class LoanEntryBookServiceImpl implements LoanEntryBookService {

	LoanEntryBookRepository loanEntryBookRepo;

	public LoanEntryBookServiceImpl() {
		loanEntryBookRepo = new LoanEntryBookRepositoryImpl();
	}

	@Override
	public void addLoanEntryDetail(LoanEntryBook loanEntryDtl) {
		loanEntryBookRepo.addLoanEntryDetail(loanEntryDtl);

	}

	@Override
	public void addLoanEntryBookDetail(Long loanMasterId, double txnAmt, String tractionCode, LocalDate meetingDate) {
		LoanEntryBook loanEntry = new LoanEntryBook();
		loanEntry.setLoanMasterId(loanMasterId);
		loanEntry.setTxnAmt(txnAmt);
		loanEntry.setTractionCode(tractionCode);
		loanEntry.setMeetingDate(meetingDate);
		loanEntryBookRepo.addLoanEntryDetail(loanEntry);
	}
}
