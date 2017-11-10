package com.agreecon.mysociety;

import java.time.LocalDate;
import java.time.Month;
import java.time.DayOfWeek;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AgreeconMysocietyApplication {

	public static void main(String[] args) {
		SpringApplication.run(AgreeconMysocietyApplication.class, args);
		//choice 1
		LocalDate initial = LocalDate.of(2012, 2, 13);
		LocalDate end = initial.withDayOfMonth(initial.lengthOfMonth());
		System.out.println("End Date of the month1:" + end.getDayOfMonth());
		//choice 2
		initial = LocalDate.now();
		LocalDate firstSundayOfWeek = initial.with(TemporalAdjusters.dayOfWeekInMonth(1, DayOfWeek.MONDAY));
		
		LocalDate lastSundayOfMonth = LocalDate
                .now()
                .with(TemporalAdjusters.lastDayOfMonth())
                .with(TemporalAdjusters.previous(DayOfWeek.SUNDAY));
		System.out.println("End Date of the month2:" + firstSundayOfWeek);
		System.out.println("End Date of the month2:" + lastSundayOfMonth);
		//choice 3
		int userChoiceDayOfMonth = 5;
		LocalDate currentDate = LocalDate.now();
		LocalDate initial1 = LocalDate.of(currentDate.getYear(), currentDate.getMonth(), userChoiceDayOfMonth);
		System.out.println("End Date of the month3:" + initial1);

	}
}
