package com.aglifetech.society.cust.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class LoanMaster {

	

	private Long id;
	private Long accountMasterId;
	private double loanDisbusmentAmount;
	private double pendingPrincipleLoan;
	private double totalIntrestPaid;
	private int accountStatus;
	private LocalDate disbursementDate;
	private LocalDate closeDate;
	private LocalDateTime createdDatetm;
	private LocalDateTime UpdatedDttm;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getAccountMasterId() {
		return accountMasterId;
	}

	public void setAccountMasterId(Long accountMasterId) {
		this.accountMasterId = accountMasterId;
	}

	public double getLoanDisbusmentAmount() {
		return loanDisbusmentAmount;
	}

	public void setLoanDisbusmentAmount(double loanDisbusmentAmount) {
		this.loanDisbusmentAmount = loanDisbusmentAmount;
	}

	public double getPendingPrincipleLoan() {
		return pendingPrincipleLoan;
	}

	public void setPendingPrincipleLoan(double pendingPrincipleLoan) {
		this.pendingPrincipleLoan = pendingPrincipleLoan;
	}

	public double getTotalIntrestPaid() {
		return totalIntrestPaid;
	}

	public void setTotalIntrestPaid(double totalIntrestPaid) {
		this.totalIntrestPaid = totalIntrestPaid;
	}
	
	public int getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(int accountStatus) {
		this.accountStatus = accountStatus;
	}

	public LocalDate getDisbursementDate() {
		return disbursementDate;
	}

	public void setDisbursementDate(LocalDate disbursementDate) {
		this.disbursementDate = disbursementDate;
	}

	public LocalDate getCloseDate() {
		return closeDate;
	}

	public void setCloseDate(LocalDate closeDate) {
		this.closeDate = closeDate;
	}

	public LocalDateTime getCreatedDatetm() {
		return createdDatetm;
	}

	public void setCreatedDatetm(LocalDateTime createdDatetm) {
		this.createdDatetm = createdDatetm;
	}

	public LocalDateTime getUpdatedDttm() {
		return UpdatedDttm;
	}

	public void setUpdatedDttm(LocalDateTime updatedDttm) {
		UpdatedDttm = updatedDttm;
	}
	@Override
	public String toString() {
		return "LoanMaster [id=" + id + ", accountMasterId=" + accountMasterId + ", loanDisbusmentAmount="
				+ loanDisbusmentAmount + ", pendingPrincipleLoan=" + pendingPrincipleLoan + ", totalIntrestPaid="
				+ totalIntrestPaid + ", accountStatus=" + accountStatus + ", disbursementDate=" + disbursementDate
				+ ", closeDate=" + closeDate + ", createdDatetm=" + createdDatetm + ", UpdatedDttm=" + UpdatedDttm
				+ "]";
	}
}
