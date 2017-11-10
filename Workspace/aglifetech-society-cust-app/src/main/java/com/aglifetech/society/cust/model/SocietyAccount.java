package com.aglifetech.society.cust.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class SocietyAccount {
	private Long id;
	private Long societyMasterID;
	private String societyAccountId;
	private String memberName;
	private String emailId;
	private String phoneNum;
	private String photoId;
	private LocalDate lastMeetingDate;
	private LocalDateTime alertDatetime;
	private String user;
	private boolean flgFirstMeeting;
	
	public boolean isFirstMeeting() {
		if (id != null) {
			if (lastMeetingDate == null) {
				flgFirstMeeting = true;
			}
		}
		return flgFirstMeeting;
	}
	
	public Long getid() {
		return id;
	}
	
	public void setid(Long id) {
		this.id = id;
	}
	
	public Long getSocietyMasterID() {
		return societyMasterID;
	}
	
	public void setSocietyMasterID(Long societyMasterID) {
		this.societyMasterID = societyMasterID;
	}
	
	public String getSocietyAccountId() {
		return societyAccountId;
	}
	
	public void setSocietyAccountId(String societyAccountId) {
		this.societyAccountId = societyAccountId;
	}
	
	public String getMemberName() {
		return memberName;
	}
	
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	
	public String getEmailId() {
		return emailId;
	}
	
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	
	public String getPhoneNum() {
		return phoneNum;
	}
	
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	
	public String getPhotoId() {
		return photoId;
	}
	
	public void setPhotoId(String photoId) {
		this.photoId = photoId;
	}
	
	public LocalDate getLastMeetingDate() {
		return lastMeetingDate;
	}
	
	public void setLastMeetingDate(LocalDate lastMeetingDate) {
		this.lastMeetingDate = lastMeetingDate;
	}
	
	public LocalDateTime getAlertDatetime() {
		return alertDatetime;
	}
	
	public void setAlertDatetime(LocalDateTime alertDatetime) {
		this.alertDatetime = alertDatetime;
	}
	
	public String getUser() {
		return user;
	}
	
	public void setUser(String user) {
		this.user = user;
	}
	
	@Override
	public String toString() {
		return "SocietyAccount [id=" + id + ", societyMasterID=" + societyMasterID + ", societyAccountId="
		        + societyAccountId + ", memberName=" + memberName + ", emailId=" + emailId + ", phoneNum=" + phoneNum
		        + ", photoId=" + photoId + ", lastMeetingDate=" + lastMeetingDate + ", alertDatetime=" + alertDatetime
		        + ", user=" + user + ", flgFirstMeeting=" + flgFirstMeeting + "]";
	}
	
}
