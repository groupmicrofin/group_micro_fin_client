package com.aglifetech.society.cust.repository;

import java.util.List;

import com.aglifetech.society.cust.model.Society;

public interface SocietyRepository {
	
	public int addSociety(Society society);
	
	public int updateSociety(Society society);
	
	public int deleteSociety(Society society);
	
	public List<Society> findAllSocieties();
	
	public Society findSocietyById(Long id);
	
	public Society findSocietyByRefId(String userSocietyId);

}
