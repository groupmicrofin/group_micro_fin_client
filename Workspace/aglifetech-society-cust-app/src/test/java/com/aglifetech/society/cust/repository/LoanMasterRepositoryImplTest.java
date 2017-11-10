package com.aglifetech.society.cust.repository;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Before;

import org.junit.Ignore;
import org.junit.Test;

import com.aglifetech.society.cust.model.LoanMaster;

public class LoanMasterRepositoryImplTest {
	
	private LoanMasterRepository loanMasterRepo;

	

	@Before
	public void setUp() throws Exception {
		loanMasterRepo = new LoanMasterRepositoryImpl();
	}

	@Test
	public void testAddLoanDetail() {
	   LoanMaster loanDetail = getLoanDetail();
	   int result = loanMasterRepo.addLoanDetail(loanDetail);
	   assertTrue(result==1);
	}

	public LoanMaster getLoanDetail() {
		LoanMaster loanMaster = new LoanMaster();
		loanMaster.setAccountMasterId(1L);
		loanMaster.setPendingPrincipleLoan(0);
		loanMaster.setLoanDisbusmentAmount(0);
		loanMaster.setTotalIntrestPaid(0);
		loanMaster.setDisbursementDate(LocalDate.now());
		loanMaster.setAccountStatus(1);
		loanMaster.setCloseDate(LocalDate.now().plusMonths(1));
		return loanMaster;
	}

	@Test
	public void testUpdateDetai() {
		LoanMaster loanMaster = new LoanMaster();
		loanMaster.setLoanDisbusmentAmount(100);
		loanMaster.setTotalIntrestPaid(10);
		loanMaster.setPendingPrincipleLoan(50);
		loanMaster.setId(1L);
		loanMaster.setDisbursementDate(LocalDate.now());
		loanMaster.setAccountStatus(1);
		loanMaster.setCloseDate(LocalDate.now().plusMonths(1));
		int result = loanMasterRepo.updateDetail(loanMaster);
		assertTrue(result ==1);
	}

	@Ignore
	public void testDeleteSociety() {
		LoanMaster loanMaster = new LoanMaster();
		loanMaster.setId(2L);
		int result = loanMasterRepo.deleteLoanMasterDtl(loanMaster);
		assertTrue(result==1);
	}

	@Test
	public void testFindLoanDetailByAccountId() {
		
		LoanMaster resultLoanMaster = loanMasterRepo.findLoanDetailByAccountId(1L);
		System.out.println(resultLoanMaster.toString());
	}

}
