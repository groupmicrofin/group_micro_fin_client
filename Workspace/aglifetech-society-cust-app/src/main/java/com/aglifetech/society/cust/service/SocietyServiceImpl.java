package com.aglifetech.society.cust.service;

import com.aglifetech.society.cust.model.Society;
import com.aglifetech.society.cust.repository.SocietyRepository;
import com.aglifetech.society.cust.repository.SocietyRepositoryImpl;
import com.aglifetech.society.cust.validation.SocietyValidator;

public class SocietyServiceImpl implements SocietyService {

	SocietyRepository societyRepository;
	SocietyValidator socValiDate;

	public SocietyServiceImpl() {
		societyRepository = new SocietyRepositoryImpl();
		socValiDate = new SocietyValidator();
	}

	@Override
	public void addSociety(Society society) {
		socValiDate.validateSocietyData(society);
		/*
		 * LocalDate lastMeetingDate = society.getLastMeetingDate(); LocalDate nextMeet
		 * = MeetingDateUtil.getNextMeetingDate(lastMeetingDate,
		 * society.getScheduleFrequency()); LocalTime time = LocalTime.of(11, 00);
		 * LocalDateTime locTm = LocalDateTime.of(nextMeet.minusDays(1),time);
		 * 
		 * society.setAlertDateTime(locTm);
		 */
		society.setUser("SYSTEM");
		societyRepository.addSociety(society);
	}

	@Override
	public void updateSociety(Society society) {
		societyRepository.updateSociety(society);

	}

	/**
	 * This method will return appropriate next alert date and time based on
	 * configured parameters of cron and last meeting date
	 * 
	 * @param society
	 */
	/*
	 * public LocalDateTime getAlertDatetime(Society society) {
	 * 
	 * LocalDate lastMeetingDate = society.getLastMeetingDate(); String schedFreCron
	 * = society.getScheduleFrequency(); //Get Next meeting date.. - 1.... Time of
	 * alert...
	 * 
	 * 
	 * 
	 * boolean lastDateOfMonth = false; boolean dateOfMonth = false; boolean
	 * lastSundayOfMonth = false;
	 * 
	 * // Cron string having parameter for deriving Meeting day // Day based :
	 * Derive day of month e.x. 28 of every month or Last day of the // month, ex..>
	 * 0 0 0 28 * ? * (& ) 0 0 0 L * ? * // WeekDay based : e.x. Last Sunday of
	 * month, 0 0 0 1L * ? * String cronString = society.getScheduleFrequency();
	 * String cronValues[] = cronString.split(" "); int n =
	 * Integer.parseInt(cronValues[3]); String second = cronValues[3].substring(1);
	 * int cronDt = Integer.parseInt(cronValues[3].substring(0, 1)); if
	 * (cronValues[3].equals("L")) { LocalDate lastMeet =
	 * society.getLastMeetingDate(); LocalDate firstOfNextMonth =
	 * lastMeet.with(TemporalAdjusters.firstDayOfNextMonth()); LocalDate
	 * lastOfNextMonth = firstOfNextMonth.with(TemporalAdjusters.lastDayOfMonth());
	 * 
	 * return LocalDateTime.now();
	 * 
	 * /// how Can We put <28 in
	 * 
	 * } else if (n <= 28) { LocalDate lastMeet = society.getLastMeetingDate();
	 * LocalDate nextMeet = lastMeet.plusMonths(1);
	 * 
	 * } else if (cronDt <= 7 && second.equals("L")) {
	 * 
	 * } else if (cronDt <= 7 && second.equals("F")) {
	 * 
	 * } return LocalDateTime.now(); }
	 */
}
