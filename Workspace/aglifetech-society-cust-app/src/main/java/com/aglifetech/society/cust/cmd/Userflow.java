package com.aglifetech.society.cust.cmd;

public class Userflow {

	//
	// SocietyService socServices = new SocietyService();
	// SocietyAccountServices accountServices = new SocietyAccountServices();
	// MeetingEntryServices meetingServices = new MeetingEntryServices();
	// SocietyReportServices reportServices = new SocietyReportServices();
	// DateServices dateServices = new DateServices();
	//
	// boolean toBeContinue = true;
	//
	// public void getUserFlow() {
	// while (toBeContinue) {
	// System.out.println("#### WELCOME ####");
	// System.out.println("Press 0 For Add New Society ");
	// System.out.println("Press 1 For Add New SocietyAccount ");
	// System.out.println("Press 2 For MeetingEntry");
	// System.out.println("Press 3 For Balance Report ");
	// System.out.println("Press 4 for Exit");
	// System.out.println("####.........####");
	// Scanner user_Input = new Scanner(System.in);
	// int input = user_Input.nextInt();
	//
	// if (input == 0) {
	// Society society = getSocietydetailFromUser();
	// socServices.getlistOfSocieties(society);
	// System.out.println("Society SuccessFully Added...");
	// /**Society society = new Society();
	// society.setSocietyId(1);
	// society.setSocietyName("Gokul Society");
	// society.setDateBasedScheduleFrequency(99);
	// society.setDayBasedWeekDayIndx(0);
	// society.setDayBasedWeekIndx(0);
	// society.setAlertDateTime(LocalDateTime.now());
	// society.setIntrestRate(12);
	// society.setMeetingFrequency(1);
	// society.setShareAmount(100);
	// society.setSocietyStartDate(LocalDate.now());
	// */
	// } else if (input == 1) {
	// System.out.println("Enter Society Id");
	// int iP = user_Input.nextInt();
	// Society societyDtl = socServices.getDetailById(iP);
	// if (societyDtl == null) {
	// System.out.println("Invalid Id");
	// } else {
	//
	// SocietyAccount detailOfCust = getCustomerDetailsFromUser();
	//
	// accountServices.getlistOfSocietyAccount(detailOfCust);
	// System.out.println("Society Account SuccessFully Added...");
	// }
	//
	// } else if (input == 2) {
	// System.out.println("Enter Society Id");
	// int iP = user_Input.nextInt();
	// Society societyDtl = socServices.getDetailById(iP);
	// System.out.println("Enter SocietyAccount Id");
	// SocietyAccount societyAccount = accountServices.getDetailById(iP);
	// if (societyAccount == null && societyDtl == null) {
	// System.out.println("Invalid Id");
	// } else {
	// MeetingEntry meeting2 = getMeetingEntryFromUser();
	// meeting2.setSocietyAccountId(iP);
	//// double actualLoanDisbus = meeting2.getLoanDisbursedAmount()
	//// + societyAccount.getPendingPrincipleLoanAmount();
	//// meeting2.setLoanDisbursedAmount(actualLoanDisbus);
	// meetingServices.storeDetailOfMeeting(meeting2);
	// System.out.println("Meeting Detail Successfully Added");
	// }
	// } else if (input == 3) {
	// System.out.println("Enter Society Id");
	// int inPut = user_Input.nextInt();
	// Society societyDtl = socServices.getDetailById(inPut);
	// System.out.println("Enter SocietyAccount Id");
	// int inPut1 = user_Input.nextInt();
	// SocietyAccount societyAccount = accountServices.getDetailById(inPut1);
	// MeetingEntry meetEntry = meetingServices.getDetailById(inPut);
	// SocietyReport socUserReport = reportServices.getSocietyReportById(inPut);
	// SocietyReport accountantReport = new SocietyReport();
	//
	// int id1 = societyAccount.getSocietyAccountId();
	// accountantReport.setSocietyAccountId(id1);
	// double totalLoanAmount = meetEntry.getLoanDisbursedAmount();
	// LocalDate d1 = societyDtl.getSocietyStartDate();
	// LocalDate d2 = meetEntry.getMeetingDate();
	/// *
	// long monthBetween = ChronoUnit.MONTHS.between(d1, d2);
	// int noOfmeeting = (int) monthBetween;
	// double totalShare = monthBetween * societyAccount.getShareAmount();
	//
	// double totalAmountCalculate = meetEntry.getLoanDisbursedAmount()
	// + socUserReport.getPendingPrincipleLoanAmount();
	// double interestAmountMonthly = ((societyAccount.getIntrestRate()) *
	// (totalAmountCalculate)) / 1200;
	// double totalpaybleAmount = totalAmountCalculate + interestAmountMonthly;
	// double pureinstallment = meetEntry.getPaidAmount() -
	// societyAccount.getShareAmount();
	// double pendingLoanAmount = totalpaybleAmount - pureinstallment;
	// double minimumInstallment = pendingLoanAmount / 10;
	// accountantReport.setNextMinimumInstallmentAmount(minimumInstallment);
	// accountantReport.setPendingPrincipleLoanAmount(pendingLoanAmount);
	// accountantReport.setTotalShareAmount(totalShare);
	// accountantReport.setTotalNoOfMeetings(noOfmeeting);
	// reportServices.addStorageOfSocietyReport(accountantReport);
	// System.out.println("Your Society Account Id.>");
	//
	// System.out.println("..>>" + socUserReport.getSocietyAccountId());
	// System.out.println(" Total Number Of Meetings");
	// System.out.println("..>>" + socUserReport.getTotalNoOfMeetings());
	// System.out.println("Total Share Amount");
	// System.out.println("..>>" + socUserReport.getTotalShareAmount());
	// System.out.println("Pending Loan Amount");
	// System.out.println("..>>" + socUserReport.getPendingPrincipleLoanAmount());
	// System.out.println("Next Meeting Minimum Installment Require To pay");
	// System.out.println("..>>" + socUserReport.getNextMinimumInstallmentAmount());
	// System.out.println("Next Meeting Date");
	// System.out.println("..>>");
	//
	// */
	//
	// } else if (input == 4) {
	// System.exit(0);
	// }
	// System.out.println("Do you Want Continue ?");
	// System.out.println("....)))))))ENTER 1 FOR CONTINUE AND 2 FOR EXIT");
	// int choiseInput = user_Input.nextInt();
	// if (choiseInput == 1) {
	// toBeContinue = true;
	// } else if (choiseInput == 2) {
	// toBeContinue = false;
	// }
	//
	// }
	// }
	//
	// public SocietyAccount getCustomerDetailsFromUser() {
	// SocietyAccount socAccount = new SocietyAccount();
	// Scanner userInput = new Scanner(System.in);
	// System.out.println("Enter Your SocietyAccount Id");
	// int socAccountId = userInput.nextInt();
	// System.out.println(" Enter Member Name..<If you don`t want to give name just
	// type> NO >");
	// String custName = userInput.next();
	// System.out.println("Enter Pending Principle Loan Amount ");
	// double pendingLoan = userInput.nextDouble();
	// System.out.println("Enter Your Email Address(Optional)");
	// String email = userInput.next();
	//
	// socAccount.getSocietyID();
	//
	// return null;
	// }
	//
	// public Society getSocietydetailFromUser() {
	//
	// Scanner userInput = new Scanner(System.in);
	// System.out.println("Enter Society Id ");
	// int socId = userInput.nextInt();
	// System.out.println(" Enter Society Name..<If you don`t want to give name just
	// type> NO >");
	// String socName = userInput.next();
	//
	// System.out.println("Enter Society Start Date(d/MM/yyyy)");
	// String StrDate = userInput.next();
	// DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
	// LocalDate startDate = LocalDate.parse(StrDate, formatter);
	// System.out.println("Enter Share Amount");
	// double shareAmt = userInput.nextDouble();
	// System.out.println("Enter Interest Rate (Year)");
	// double intRate = userInput.nextDouble();

}