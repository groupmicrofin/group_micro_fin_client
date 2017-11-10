package com.aglifetech.society.cust.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import com.aglifetech.society.cust.model.Society;
import com.aglifetech.society.cust.model.SocietyAccount;
import com.aglifetech.society.cust.repository.SocietyAccountRepository;
import com.aglifetech.society.cust.repository.SocietyAccountRepositoryImpl;
import com.aglifetech.society.cust.repository.SocietyRepository;
import com.aglifetech.society.cust.repository.SocietyRepositoryImpl;
import com.aglifetech.society.cust.util.MeetingDateUtil;

public class SocietyAccountServiceImpl implements SocietyAccountService {
	
	SocietyAccountRepository socAcRepo;
	SocietyRepository socRepo;
	
	public SocietyAccountServiceImpl() {
		socAcRepo = new SocietyAccountRepositoryImpl();
		socRepo = new SocietyRepositoryImpl();
		
	}
	
	@Override
	public void addSocietyAccount(SocietyAccount societyAc) {
		
		societyAc.setUser("SYSTEM");
		socAcRepo.addSociety(societyAc);
		
	}
	
	@Override
	public void updateSocietyAccount(SocietyAccount societyAc) {
		socAcRepo.updateSociety(societyAc);
		
	}
	
	@Override
	public void updateMeetingDate(SocietyAccount societyAc, LocalDate meetingDate) {
		
		societyAc.setLastMeetingDate(meetingDate);
		Long id = societyAc.getSocietyMasterID();
		Society society = socRepo.findSocietyById(id);
		
		LocalDate nextMeet = MeetingDateUtil.getNextMeetingDate(meetingDate, society.getScheduleFrequency());
		LocalTime time = LocalTime.of(11, 00);
		LocalDateTime locTm = LocalDateTime.of(nextMeet.minusDays(1), time);
		societyAc.setAlertDatetime(locTm);
		socAcRepo.updateSociety(societyAc);
		
	}
	
	@Override
	public Society getSocietyByAccountId(Long societyMaster, Long accountMasterId) {
		SocietyAccount socAc = socAcRepo.findSocietyAccountById(accountMasterId);
		Society society = socRepo.findSocietyById(societyMaster);
		return society;
	}
}