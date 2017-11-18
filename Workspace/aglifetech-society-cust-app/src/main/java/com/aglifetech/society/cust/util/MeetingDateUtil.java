package com.aglifetech.society.cust.util;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

public class MeetingDateUtil {

	/**
	 * This will return next meeting date based on last meeting date and cron
	 * expression
	 * 
	 * @param lastMeetingDate
	 * @param cronSchedExpression
	 * @return
	 */
	public static LocalDate getNextMeetingDate(LocalDate lastMeetingDate, String cronSchedExpression) {

		// derive day based or Date based based on cronSched Expression 0 0 0 ? * 0L *

		System.out.println("Cron expression is :" + cronSchedExpression);
		/*
		 * CronSequenceGenerator cronSeqGen = new
		 * CronSequenceGenerator(cronSchedExpression);
		 * 
		 * Date lastDate = asDate(lastMeetingDate);
		 * System.out.println("Converted Date is:" + lastDate); Date nextDate =
		 * cronSeqGen.next(lastDate);
		 * 
		 * System.out.println("Changed date is: " + nextDate);
		 */

		String cronValues[] = cronSchedExpression.split(" ");

		String cronStrDay = cronValues[3];// day of the frequency ? * can be allowed
		String cronStrWeekDay = cronValues[5];// weekday possible values 0 to 5 with second character allowed LF also *
		// allowed
		LocalDate nextMeetingDate = null;

		// To support Day of month
		if (cronStrDay.indexOf("L") >= 0) {
			if (cronStrDay.length() == 1) {
				nextMeetingDate = lastMeetingDate.with(TemporalAdjusters.firstDayOfNextMonth())
						.with(TemporalAdjusters.lastDayOfMonth());
			}

		} else if (cronStrWeekDay.indexOf("L") >= 0) {
			int day = Integer.parseInt(cronStrWeekDay.substring(0, 1));
			DayOfWeek dayOfWeek = getDayOfWeek(day);
			LocalDate nextMonthDate = lastMeetingDate.plusMonths(1);
			nextMeetingDate = nextMonthDate.with(TemporalAdjusters.lastDayOfMonth())
					.with(TemporalAdjusters.previous(dayOfWeek));

		} else if (cronStrDay.indexOf("F") > 0) {
			int day = Integer.parseInt(cronStrDay.substring(0, 1));
			DayOfWeek dayOfWeek = getDayOfWeek(day);
			LocalDate nextMonthDate = lastMeetingDate.plusMonths(1);
			nextMeetingDate = nextMonthDate.with(TemporalAdjusters.firstDayOfMonth())
					.with(TemporalAdjusters.next(dayOfWeek));

		} else {
			int userChoiceDayOfMonth = Integer.parseInt(cronStrDay);
			LocalDate initial1 = LocalDate.of(lastMeetingDate.getYear(), lastMeetingDate.getMonth(),
					userChoiceDayOfMonth);
			nextMeetingDate = initial1.plusMonths(1);
		}

		System.out.println("nextMeetDate : " + nextMeetingDate);

		return nextMeetingDate;
	}

	/*
	 * String valueOfTime = PropertyUtil.getProperty("ALARM_TIME");
	 * System.out.println("Property in file is:" + valueOfTime);
	 * 
	 * return lastMeetingDate.plusMonths(1); }
	 */

	public static DayOfWeek getDayOfWeek(int dayOfWeekIndex) {
		DayOfWeek dayOfWeek = null;
		switch (dayOfWeekIndex) {
		case 0:
			dayOfWeek = dayOfWeek.SUNDAY;
			break;
		case 1:
			dayOfWeek = dayOfWeek.MONDAY;
			break;
		case 2:
			dayOfWeek = dayOfWeek.TUESDAY;
			break;
		case 3:
			dayOfWeek = dayOfWeek.WEDNESDAY;
			break;
		case 4:
			dayOfWeek = dayOfWeek.THURSDAY;
			break;
		case 5:
			dayOfWeek = dayOfWeek.FRIDAY;
			break;
		case 6:
			dayOfWeek = dayOfWeek.SATURDAY;
			break;
		}
		return dayOfWeek;
	}

	public static Date asDate(LocalDate localDate) {
		return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
	}

}
