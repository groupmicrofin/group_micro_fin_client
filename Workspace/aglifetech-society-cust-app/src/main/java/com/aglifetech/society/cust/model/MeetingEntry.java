package com.aglifetech.society.cust.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class MeetingEntry {

	private Long id;
	private Long societyMasterId;
	private Long societyAcMaterId;
	private double loanDisbursedAmount;
	private double totalPaidAmount;
	private LocalDate meetingDate;
	private LocalDateTime createdDateTm;
	private String user;

	public Long getid() {
		return id;
	}

	public void setid(Long id) {
		this.id = id;
	}

	public Long getSocietyMasterId() {
		return societyMasterId;
	}

	public void setSocietyMasterId(Long societyID) {
		this.societyMasterId = societyID;
	}

	public Long getSocietyAccountMasterId() {
		return societyAcMaterId;
	}

	public void setSocietyAccountMasterId(Long societyAccountId) {
		this.societyAcMaterId = societyAccountId;
	}

	public double getLoanDisbursedAmount() {
		return loanDisbursedAmount;
	}

	public void setLoanDisbursedAmount(double loanDisbursedAmount) {
		this.loanDisbursedAmount = loanDisbursedAmount;
	}

	public double getTotalPaidAmount() {
		return totalPaidAmount;
	}

	public void setTotalPaidAmount(double totalPaidAmount) {
		this.totalPaidAmount = totalPaidAmount;
	}

	public LocalDate getMeetingDate() {
		return meetingDate;
	}

	public void setMeetingDate(LocalDate meetingDate) {
		this.meetingDate = meetingDate;
	}

	public LocalDateTime getCreatedDateTm() {
		return createdDateTm;
	}

	public void setCreatedDateTm(LocalDateTime createdDateTm) {
		this.createdDateTm = createdDateTm;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

}