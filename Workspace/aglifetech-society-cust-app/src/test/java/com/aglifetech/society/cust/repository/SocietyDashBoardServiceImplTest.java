package com.aglifetech.society.cust.repository;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.aglifetech.society.cust.model.Society;
import com.aglifetech.society.cust.model.SocietyAccount;
import com.aglifetech.society.cust.model.SocietyAccountReport;
import com.aglifetech.society.cust.service.SocietyDashBoardService;
import com.aglifetech.society.cust.service.SocietyDashBoardServiceImpl;

public class SocietyDashBoardServiceImplTest {

	private SocietyDashBoardService socDashService;

	SocietyRepository societyRepository;
	SocietyAccountRepository societyRepositoryAccount;

	@Before
	public void setUp() throws Exception {
		SocietyDashBoardServiceImpl socDashService1 = new SocietyDashBoardServiceImpl();

		societyRepository = Mockito.mock(SocietyRepositoryImpl.class);
		societyRepositoryAccount = Mockito.mock(SocietyAccountRepositoryImpl.class);

		socDashService1.setSocietyRepository(societyRepository);
		socDashService1.setSocietyAccountRepository(societyRepositoryAccount);

		socDashService = socDashService1;
	}

	@Test
	public void testGetDashBoard() {

		Society society = new Society();
		society.setSocietyStartDate(LocalDate.now().minusMonths(5));
		society.setShareAmount(100);
		society.setScheduleFrequency("0 0 20 ? * 1L *");

		SocietyAccount societyAccount = new SocietyAccount();
		societyAccount.setPendingPrincipalLoanAmount(0);
		societyAccount.setid(1L);
		societyAccount.setSocietyMasterID(1L);

		when(societyRepositoryAccount.findSocietyAccountById(1L)).thenReturn(societyAccount);
		when(societyRepository.findSocietyById(1L)).thenReturn(society);

		SocietyAccountReport socAcReport = socDashService.getDashBoard(1L);

		assertTrue(socAcReport.getTotalLoanAmount() == 0);
		assertTrue(socAcReport.getTotalNoOfMeetings() == 5);
		assertTrue(socAcReport.getTotalShareAmount() == 500);

		verify(societyRepositoryAccount, times(1)).findSocietyAccountById(1L);
		verify(societyRepository, times(1)).findSocietyById(1L);

	}

}