package com.aglifetech.society.cust.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.Scanner;

public class DateServices {

	public LocalDate getScheduleDate() {
		LocalDate scheduledate = null;
		Scanner userInput1 = new Scanner(System.in);
		System.out.println(" Meeting Schedule of Your Society");
		System.out.println("......a> Enter 1 for Date Based choice ");
		System.out.println("......b> Enter 2 for Day Based choice ");
		int userIp = userInput1.nextInt();
		if (userIp == 1) {
			System.out.println("Enter 99 For Month End Date");
			System.out.println("Enter Day of month (<=28)");
			int userIp1 = userInput1.nextInt();
			if (userIp1 == 99) {
				LocalDate initial = LocalDate.now();
				LocalDate meetingDate = initial.withDayOfMonth(initial.lengthOfMonth());
				scheduledate = meetingDate;
			} else if (userIp <= 28) {
				LocalDate currentDate = LocalDate.now();
				LocalDate initial1 = LocalDate.of(currentDate.getYear(), currentDate.getMonth(), userIp1);
				scheduledate = initial1;
			}

		} else if (userIp == 2) {
			System.out.println("Enter 1 for first Sunday");
			System.out.println("Enter 2 for last Sunday");
			int userIP = userInput1.nextInt();
			if (userIP == 1) {
				LocalDate initial = LocalDate.now();
				LocalDate firstSundayOfWeek = initial.with(TemporalAdjusters.dayOfWeekInMonth(1, DayOfWeek.SUNDAY));
				scheduledate=firstSundayOfWeek;
			} else if (userIP == 2) {
				LocalDate lastSundayOfMonth = LocalDate.now().with(TemporalAdjusters.lastDayOfMonth())
						.with(TemporalAdjusters.previous(DayOfWeek.SUNDAY));
				scheduledate=lastSundayOfMonth;
			}
		}
		return scheduledate;
	}
}