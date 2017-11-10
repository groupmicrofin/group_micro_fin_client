package com.aglifetech.society.cust.repository;

import java.util.List;

import com.aglifetech.society.cust.model.LoanMaster;

public interface LoanMasterRepository {
	
	public int addLoanDetail(LoanMaster loanDtl);
	
	public int updateDetail(LoanMaster loanDtl);
	
	public int deleteLoanMasterDtl(LoanMaster loanDtl);
	
	public LoanMaster findLoanDetailByAccountId(Long id);
	
	public List<LoanMaster> getActiveLoanAccounts(Long id);
	
	public LoanMaster findLoanDetailByLoanMasterId(Long LoanMasterId);
	
}
