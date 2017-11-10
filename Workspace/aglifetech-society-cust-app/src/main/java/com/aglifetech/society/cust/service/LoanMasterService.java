package com.aglifetech.society.cust.service;

import java.time.LocalDate;

import com.aglifetech.society.cust.model.LoanMaster;

public interface LoanMasterService {

	public void addLoanDetail(LoanMaster loanMaster);

	public void updateLoanMasterDetail(LoanMaster loanMaster);

	public boolean performInstallments(Long accountMasterId, double installPaidAmt, LocalDate meetingDate);

	public double calculateInterest(double pendingPrincLoanBalance, double interestRate);
}
