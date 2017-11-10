package com.aglifetech.society.cust.repository;

import java.time.LocalDate;
import java.util.List;

import com.aglifetech.society.cust.model.LoanEntryBook;

public interface LoanEntryBookRepository {
	
	public int addLoanEntryDetail(LoanEntryBook loanDtl);
	
	public int deleteLoanEntryDetail(LoanEntryBook loanEntryBook);
	
	public List<LoanEntryBook> findLoanEntryDetailsByMeetingDate(Long accountMasterId, LocalDate meetDate);
	
}
