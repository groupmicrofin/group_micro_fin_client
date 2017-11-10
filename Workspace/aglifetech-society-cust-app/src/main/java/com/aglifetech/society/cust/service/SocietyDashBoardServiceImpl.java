package com.aglifetech.society.cust.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import com.aglifetech.society.cust.model.LoanMaster;
import com.aglifetech.society.cust.model.Society;
import com.aglifetech.society.cust.model.SocietyAccount;
import com.aglifetech.society.cust.model.SocietyAccountReport;
import com.aglifetech.society.cust.repository.LoanMasterRepository;
import com.aglifetech.society.cust.repository.LoanMasterRepositoryImpl;
import com.aglifetech.society.cust.repository.SocietyAccountRepository;
import com.aglifetech.society.cust.repository.SocietyAccountRepositoryImpl;
import com.aglifetech.society.cust.repository.SocietyRepository;
import com.aglifetech.society.cust.repository.SocietyRepositoryImpl;
import com.aglifetech.society.cust.util.MeetingDateUtil;

/**
 * This class will be used to get reporting dashboard related fetch
 * 
 * @author Ankit
 *
 */
public class SocietyDashBoardServiceImpl implements SocietyDashBoardService {

	private SocietyRepository socRepo;
	private SocietyAccountRepository socAcRepo;
	private SocietyAccountService socAcService;
	private LoanMasterRepository loanMasterRepo;
	private LoanMasterService loanMasterService;

	public SocietyDashBoardServiceImpl() {
		socRepo = new SocietyRepositoryImpl();
		socAcRepo = new SocietyAccountRepositoryImpl();
		socAcService = new SocietyAccountServiceImpl();
		loanMasterRepo = new LoanMasterRepositoryImpl();
		loanMasterService = new LoanMasterServiceImpl();
	}

	public void setSocietyRepository(SocietyRepository scr) {
		this.socRepo = scr;
	}

	public void setSocietyAccountRepository(SocietyAccountRepository socAcRepo) {
		this.socAcRepo = socAcRepo;
	}

	/**
	 * This method will be used to get dashboard level report data
	 * 
	 * @see com.aglifetech.society.cust.service.SocietyDashBoardService#getDashBoard(java.lang.Long)
	 * @param Long
	 *            id : This will be society id
	 */
	@Override
	public SocietyAccountReport getDashBoard(Long societyAccountId) {

		SocietyAccount societyAccount = socAcRepo.findSocietyAccountById(societyAccountId);
		Society society = socRepo.findSocietyById(societyAccount.getSocietyMasterID());

		LocalDate from = society.getSocietyStartDate();
		LocalDate lastMeetingDate = societyAccount.getLastMeetingDate();
		int totalMeetings = (int) from.withDayOfMonth(1).until(lastMeetingDate.withDayOfMonth(1), ChronoUnit.MONTHS)
				+ 1;
		double shareAmt = society.getShareAmount();
		double totalShareAmt = (totalMeetings * shareAmt);
		// Total Pending Loan balance
		List<LoanMaster> activeAccounts = loanMasterRepo.getActiveLoanAccounts(societyAccountId);
		double totalPendingLoanBalance = 0.0;
		for (LoanMaster loanmaster : activeAccounts) {
			totalPendingLoanBalance = totalPendingLoanBalance + loanmaster.getPendingPrincipleLoan();

		}
		double calculateInt = loanMasterService.calculateInterest(totalPendingLoanBalance, society.getIntrestRate());
		double amtForCloseAllLoan = totalPendingLoanBalance + calculateInt;
		LocalDate nextMeet = MeetingDateUtil.getNextMeetingDate(lastMeetingDate, society.getScheduleFrequency());
		SocietyAccountReport socReport = new SocietyAccountReport();
		socReport.setSocietyAccountId(societyAccount.getid());
		socReport.setTotalNoOfMeetings(totalMeetings);
		socReport.setTotalShareAmount(totalShareAmt);
		socReport.setTotalLoanAmount(totalPendingLoanBalance);
		socReport.setPendingPrincipleLoanAmount(totalPendingLoanBalance);
		socReport.setAmountNeedToCloseAllLoan(amtForCloseAllLoan);
		socReport.setLastMeetingDate(lastMeetingDate);
		socReport.setNextMeetingDate(nextMeet);
		return socReport;

	}

}
