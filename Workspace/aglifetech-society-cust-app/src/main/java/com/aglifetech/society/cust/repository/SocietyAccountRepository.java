package com.aglifetech.society.cust.repository;

import java.util.List;

import com.aglifetech.society.cust.model.Society;
import com.aglifetech.society.cust.model.SocietyAccount;

public interface SocietyAccountRepository {

	public int addSociety(SocietyAccount societyAc);

	public int updateSociety(SocietyAccount societyAc);

	public int deleteSociety(SocietyAccount societyAc);

	public SocietyAccount findSocietyAccountById(Long id);

	public List<SocietyAccount> findSocietyAccountsBySocietyId(Long societyId);

	public Society findSocietyByAccountId(Long accountmasterId);
}
