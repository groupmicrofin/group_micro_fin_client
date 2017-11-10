package com.aglifetech.society.cust.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class LoanEntryBook {

	private long loanMasterId;
	private String tractionCode;
	private double txnAmt;
	private LocalDate meetingDate;
	private LocalDateTime createdDttm;

	public long getLoanMasterId() {
		return loanMasterId;
	}

	public void setLoanMasterId(long loanMasterId) {
		this.loanMasterId = loanMasterId;
	}

	public String getTractionCode() {
		return tractionCode;
	}

	public void setTractionCode(String tractionCode) {
		this.tractionCode = tractionCode;
	}

	public double getTxnAmt() {
		return txnAmt;
	}

	public void setTxnAmt(double txnAmt) {
		this.txnAmt = txnAmt;
	}

	public LocalDate getMeetingDate() {
		return meetingDate;
	}

	public void setMeetingDate(LocalDate meetingDate) {
		this.meetingDate = meetingDate;
	}

	public LocalDateTime getCreatedDttm() {
		return createdDttm;
	}

	public void setCreatedDttm(LocalDateTime createdDttm) {
		this.createdDttm = createdDttm;
	}

}
