package com.aglifetech.society.cust.util;

public class CalculatorUtil {

	public static double round(double amount, int decimalPlaces) {
		double result = 0.0;
		double divisor = Math.pow(10.0, (double) decimalPlaces);

		result = Math.round(amount * divisor) / divisor;

		return result;
	}

}
