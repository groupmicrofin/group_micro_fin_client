package com.aglifetech.society.cust.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	public static void main(String[] args) {
		Date date = new Date();
		convertDateToString(date);
		convertDateTimeToString(date);
		convertStringToDate("20171212");
		convertStringToDateTime("201712121100000");
	}

	public static String convertDateToString(Date inputDate) {
		String sDate = null;
		SimpleDateFormat ft = new SimpleDateFormat("yyyyMMdd");
		sDate = ft.format(inputDate);
		System.out.println("convertDateToString:-" + sDate);
		return sDate;
	}

	public static String convertDateTimeToString(Date inputDate) {
		String sDate = null;
		SimpleDateFormat ft = new SimpleDateFormat("yyyyMMddHHmmss");
		sDate = ft.format(inputDate);
		System.out.println("convertDateTimeToString" + sDate);
		return sDate;
	}

	public static Date convertStringToDate(String inputDateString) {
		Date sDate = null;
		DateFormat formatter = new SimpleDateFormat("dyyyyMMdd");
		try {
			sDate = formatter.parse(inputDateString);
			System.out.println("convertStringToDate" + sDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sDate;
	}

	public static Date convertStringToDateTime(String inputDateTimeString) {
		Date sDate = null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		try {
			sDate = formatter.parse(inputDateTimeString);
			System.out.println("convertStringToDateTime" + sDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sDate;
	}

}
