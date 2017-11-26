package com.aglifetech.society.cust.util;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

public class MeetingDateUtil {

	/**
	 * This will return next meeting date based on last meeting date and cron
	 * expression E.x (1) For Last Sunday of every month cron expression would be :
	 * "0 0 0 ? * 0L *" (2) Third Sunday Of Every Month cron expression would br :
	 * "0 0 0 ? * 1#2 *" (3) For 25th of every month cron expression would be : "0 0
	 * 0 25 * ? *"
	 * 
	 * @param lastMeetingDate
	 * @param cronSchedExpression
	 * @return
	 */
	public static LocalDate getNextMeetingDate(LocalDate lastMeetingDate, String cronSchedExpression) {

		// derive day based or Date based based on cronSched Expression 0 0 0 ? * 0L *

		String cronValues[] = cronSchedExpression.split(" ");

		String cronStrDay = cronValues[3];// day of the frequency ? * can be allowed
		String cronStrWeekDay = cronValues[5];// weekday possible values 0 to 5 with second character allowed LF also *
		// allowed
		LocalDate nextMeetingDate = null;

		// To support LastDay of month
		if (cronStrDay.indexOf("L") >= 0) {
			if (cronStrDay.length() == 1) {
				nextMeetingDate = lastMeetingDate.with(TemporalAdjusters.firstDayOfNextMonth())
						.with(TemporalAdjusters.lastDayOfMonth());
			}

			// to Support Last Sunday n Friday etc Of Month

		} else if (cronStrWeekDay.indexOf("L") >= 0) {
			int day = Integer.parseInt(cronStrWeekDay.substring(0, 1));
			DayOfWeek dayOfWeek = getDayOfWeek(day);
			LocalDate nextMonthDate = lastMeetingDate.plusMonths(1);
			nextMeetingDate = nextMonthDate.with(TemporalAdjusters.lastDayOfMonth())
					.with(TemporalAdjusters.previousOrSame(dayOfWeek));

			// To support 1 ,2,3 Week days of Month
		} else if (cronStrWeekDay.indexOf("#") > 0) {
			int day = Integer.parseInt(cronStrWeekDay.substring(0, 1));
			int week = Integer.parseInt(cronStrWeekDay.substring(2, 3));
			DayOfWeek dayOfWeek = getDayOfWeek(day);
			LocalDate nextMonthDate = lastMeetingDate.plusMonths(1);
			nextMeetingDate = nextMonthDate.with(TemporalAdjusters.dayOfWeekInMonth(week, dayOfWeek));

			/*
			 * nextMeetingDate = nextMonthDate.with(TemporalAdjusters.firstDayOfMonth())
			 * .with(TemporalAdjusters.nextOrSame(dayOfWeek));
			 */
			// To Support day of month (1 to 28)
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
