package com.aglifetech.society.cust.service;

import java.time.LocalDate;

import com.aglifetech.society.cust.model.Society;
import com.aglifetech.society.cust.model.SocietyAccount;

public interface SocietyAccountService {

	public void addSocietyAccount(SocietyAccount societyAc);

	public void updateSocietyAccount(SocietyAccount societyAc);

	public void updateMeetingDate(SocietyAccount societyAc, LocalDate meetingDate);

	public Society getSocietyByAccountId(Long societyMaster, Long accountMasterId);

	public void deleteSocietyAccount(SocietyAccount societyaccount);

}
