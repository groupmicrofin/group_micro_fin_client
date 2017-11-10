package com.aglifetech.society.cust.repository;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.aglifetech.society.cust.model.SocietyAccount;

public class SocietyAccountRepositoryImplTest {

	private SocietyAccountRepository socAccRepository;

	@Before
	public void setUp() throws Exception {
		socAccRepository = new SocietyAccountRepositoryImpl();
	}

	@Test
	public void testAddSocietyAccount() {

		SocietyAccount societyAc = getSocietyAcc();

		int result = socAccRepository.addSociety(societyAc);

		assertTrue(result == 1);

	}

	public SocietyAccount getSocietyAcc() {
		SocietyAccount societyAccDt = new SocietyAccount();
		societyAccDt.setSocietyMasterID(1L);
		societyAccDt.setSocietyAccountId("123");
		societyAccDt.setMemberName("Jay bhavani");
		//societyAccDt.setPendingPrincipalLoanAmount(100);
		societyAccDt.setEmailId("jig@abc.com");
		societyAccDt.setPhoneNum("9898989898");
		societyAccDt.setPhotoId("Abc123");
		societyAccDt.setUser("Jig");
		return societyAccDt;
	}

	@Test
	public void testUpdateSociety() {
		SocietyAccount socAc = new SocietyAccount();
		socAc.setEmailId("niju@abc");
		socAc.setPhoneNum("9898767676");
		socAc.setUser("Aku");
		socAc.setid(1L);
		int result = socAccRepository.updateSociety(socAc);
		assertTrue(result == 1);
	}

	@Ignore
	public void testDeleteSociety() {
		SocietyAccount societyAc = new SocietyAccount();
		societyAc.setid(9L);
		int result = socAccRepository.deleteSociety(societyAc);
		assertTrue(result == 1);
	}

	@Test
	public void testFindSocietyAccountById() {
		SocietyAccount socAc = socAccRepository.findSocietyAccountById(1L);
		assertTrue(socAc.getid() == 1L);
	}

	@Test
	public void testFindSocietyAccountsBySocietyId() {
		List<SocietyAccount> socAcs = socAccRepository.findSocietyAccountsBySocietyId(1L);
		for (SocietyAccount ac : socAcs) {
			System.out.println(ac.toString());
		}

	}
}
