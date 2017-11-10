package com.aglifetech.society.cust.service;

import java.time.LocalDate;

import com.aglifetech.society.cust.model.LoanEntryBook;

public interface LoanEntryBookService {

	public void addLoanEntryDetail(LoanEntryBook loanDtl);

	public void addLoanEntryBookDetail(Long loanMasterId, double txnAmt, String tractionCode, LocalDate meetingDate);
}
