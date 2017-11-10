package com.aglifetech.society.cust.model;

import java.time.LocalDate;

public class SocietyAccountReport {

	private Long societyAccountId;
	private int totalNoOfMeetings;
	private double totalShareAmount;
	private double totalLoanAmount;
	private double loanInterestPaidAmount;
	private double pendingPrincipleLoanAmount;
	private double amountNeedToCloseAllLoan;
	private LocalDate nextMeetingDate;
	private LocalDate lastMeetingDate;

	public Long getSocietyAccountId() {
		return societyAccountId;
	}

	public void setSocietyAccountId(Long societyAccountId) {
		this.societyAccountId = societyAccountId;
	}

	public int getTotalNoOfMeetings() {
		return totalNoOfMeetings;
	}

	public void setTotalNoOfMeetings(int totalNoOfMeetings) {
		this.totalNoOfMeetings = totalNoOfMeetings;
	}

	public double getTotalShareAmount() {
		return totalShareAmount;
	}

	public void setTotalShareAmount(double totalShareAmount) {
		this.totalShareAmount = totalShareAmount;
	}

	public double getTotalLoanAmount() {
		return totalLoanAmount;
	}

	public void setTotalLoanAmount(double totalLoanAmount) {
		this.totalLoanAmount = totalLoanAmount;
	}

	public double getLoanInterestPaidAmount() {
		return loanInterestPaidAmount;
	}

	public void setLoanInterestPaidAmount(double loanInterestPaidAmount) {
		this.loanInterestPaidAmount = loanInterestPaidAmount;
	}

	public double getPendingPrincipleLoanAmount() {
		return pendingPrincipleLoanAmount;
	}

	public void setPendingPrincipleLoanAmount(double pendingPrincipleLoanAmount) {
		this.pendingPrincipleLoanAmount = pendingPrincipleLoanAmount;
	}

	public double getAmountNeedToCloseAllLoan() {
		return amountNeedToCloseAllLoan;
	}

	public void setAmountNeedToCloseAllLoan(double amountNeedToCloseAllLoan) {
		this.amountNeedToCloseAllLoan = amountNeedToCloseAllLoan;
	}

	public LocalDate getNextMeetingDate() {
		return nextMeetingDate;
	}

	public void setNextMeetingDate(LocalDate nextMeetingDate) {
		this.nextMeetingDate = nextMeetingDate;
	}

	public LocalDate getLastMeetingDate() {
		return lastMeetingDate;
	}

	public void setLastMeetingDate(LocalDate lastMeetingDate) {
		this.lastMeetingDate = lastMeetingDate;
	}

	@Override
	public String toString() {
		return "SocietyAccountReport [societyAccountId=" + societyAccountId + ", totalNoOfMeetings=" + totalNoOfMeetings
				+ ", totalShareAmount=" + totalShareAmount + ", totalLoanAmount=" + totalLoanAmount
				+ ", loanInterestPaidAmount=" + loanInterestPaidAmount + ", pendingPrincipleLoanAmount="
				+ pendingPrincipleLoanAmount + ", amountNeedToCloseAllLoan=" + amountNeedToCloseAllLoan
				+ ", nextMeetingDate=" + nextMeetingDate + ", lastMeetingDate=" + lastMeetingDate + "]";
	}

}
