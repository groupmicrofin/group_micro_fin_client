package com.aglifetech.society.cust.service;

import java.time.LocalDate;
import java.util.List;

import com.aglifetech.society.common.TxnCode;
import com.aglifetech.society.cust.model.LoanMaster;
import com.aglifetech.society.cust.model.Society;
import com.aglifetech.society.cust.model.SocietyAccount;
import com.aglifetech.society.cust.repository.LoanMasterRepository;
import com.aglifetech.society.cust.repository.LoanMasterRepositoryImpl;
import com.aglifetech.society.cust.repository.SocietyAccountRepository;
import com.aglifetech.society.cust.repository.SocietyAccountRepositoryImpl;
import com.aglifetech.society.cust.repository.SocietyRepository;
import com.aglifetech.society.cust.repository.SocietyRepositoryImpl;

public class LoanMasterServiceImpl implements LoanMasterService {

	LoanMasterRepository loanMasterRepo;
	SocietyRepository societyRepo;
	SocietyAccountRepository societyAcRepo;
	LoanEntryBookService loanEntryBookService;

	public LoanMasterServiceImpl() {
		loanMasterRepo = new LoanMasterRepositoryImpl();
		societyRepo = new SocietyRepositoryImpl();
		societyAcRepo = new SocietyAccountRepositoryImpl();
		loanEntryBookService = new LoanEntryBookServiceImpl();
	}

	@Override
	public void addLoanDetail(LoanMaster loanMaster) {
		loanMasterRepo.addLoanDetail(loanMaster);

	}

	@Override
	public void updateLoanMasterDetail(LoanMaster loanMaster) {
		loanMasterRepo.updateDetail(loanMaster);

	}

	@Override
	public boolean performInstallments(Long accountMasterId, double installPaidAmt, LocalDate meetingDAte) {

		// TODO to make single call instead of 2 calls for getting society object
		SocietyAccount socAc = societyAcRepo.findSocietyAccountById(accountMasterId);
		Long id = socAc.getSocietyMasterID();
		Society soc = societyRepo.findSocietyById(id);
		double intRate = soc.getIntrestRate();
		double shareAmt = soc.getShareAmount();
		// double installmentAmount = installPaidAmt - shareAmt;

		// First perform interest charging to accounts
		double totalPendingInterestAmount = this.performMonthlyInterestCharging(accountMasterId, intRate, meetingDAte);
		System.out.println("Total Interest caclulated and added:" + totalPendingInterestAmount);

		performInstallmentPayment(accountMasterId, installPaidAmt, meetingDAte);
		return true;
	}

	public void performInstallmentPayment(Long accountMasterId, double installPaidAmt, LocalDate meetingDate) {
		// Perform installment payment only for install paid case
		if (installPaidAmt > 0.0) {
			double remainingInstallmentAmt = installPaidAmt;
			List<LoanMaster> activeLoanAccounts = loanMasterRepo.getActiveLoanAccounts(accountMasterId);
			for (LoanMaster loanMaster : activeLoanAccounts) {

				if (remainingInstallmentAmt == 0.0) {
					break;
				}
				// pending_princi_loan,account_status,close_dt
				double pendingLoanAmount = loanMaster.getPendingPrincipleLoan();
				double postInstallPendingLoanAmount = pendingLoanAmount - remainingInstallmentAmt;
				double loanInstallPaid = 0.0;
				// In case of loan having balance post installment payment (loan remain active),
				// perform payment of pendingPrinc amount
				if (postInstallPendingLoanAmount > 0.0) {
					loanMaster.setPendingPrincipleLoan(postInstallPendingLoanAmount);
					loanInstallPaid = remainingInstallmentAmt;
					remainingInstallmentAmt = 0.0;
					loanEntryBookService.addLoanEntryBookDetail(loanMaster.getId(), loanInstallPaid, TxnCode.INST_PAID,
							meetingDate);
				} else if (postInstallPendingLoanAmount <= 0.0) { // In case of pending loan amount and installpaid
																	// amount same, (loan close)
					loanMaster.setAccountStatus(0);
					loanMaster.setCloseDate(meetingDate);
					loanMaster.setPendingPrincipleLoan(0.0);
					remainingInstallmentAmt = remainingInstallmentAmt - pendingLoanAmount;// Next loan installment
					loanInstallPaid = pendingLoanAmount; // available amount
					loanEntryBookService.addLoanEntryBookDetail(loanMaster.getId(), loanInstallPaid, TxnCode.INST_PAID,
							meetingDate);
					loanEntryBookService.addLoanEntryBookDetail(loanMaster.getId(), 0.0, TxnCode.LN_CLOSED,
							meetingDate);
				}
				loanMasterRepo.updateDetail(loanMaster);
				// To find how much loan install paid for particular loan
			}
		}
	}

	/**
	 * Get All active loan accounts and perform interest calculation and update
	 * principle loan balance
	 * 
	 * @param accountMasterId
	 * @return
	 */
	public double performMonthlyInterestCharging(Long accountMasterId, double interestRate, LocalDate meetingDate) {
		double totalPendingInterestAmount = 0.0;

		List<LoanMaster> activeLoanAccounts = loanMasterRepo.getActiveLoanAccounts(accountMasterId);

		for (LoanMaster loanMaster : activeLoanAccounts) {
			double calculatedInt = calculateInterest(loanMaster.getPendingPrincipleLoan(), interestRate);
			double pendingLoanWithInt = loanMaster.getPendingPrincipleLoan() + calculatedInt;
			loanMaster.setPendingPrincipleLoan(pendingLoanWithInt);
			// TODO int charged
			loanMaster.setTotalIntrestPaid(loanMaster.getTotalIntrestPaid() + calculatedInt);
			// update loan master with charged interest details
			updateLoanMasterDetail(loanMaster);
			loanEntryBookService.addLoanEntryBookDetail(loanMaster.getId(), calculatedInt, TxnCode.INT_CHRG,
					meetingDate);

			// TODO call loan repository loan entry method, calculatedInt - INT_CHRG -
			// Meeting Date
			totalPendingInterestAmount = totalPendingInterestAmount + calculatedInt;
		}

		return totalPendingInterestAmount;
	}

	@Override
	public double calculateInterest(double pendingPrincLoanBalance, double interestRate) {
		double interestAmount = (pendingPrincLoanBalance * interestRate) / 1200;// (12 Months *100)
		return interestAmount;
	}

}
