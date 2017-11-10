package com.aglifetech.society.cust.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyUtil {
	private static Properties myAppProperties;

	static {
		myAppProperties = new Properties();
		InputStream in = ClassLoader.getSystemResourceAsStream("mygroupfinance.properties");
		try {
			myAppProperties.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static String getProperty(String key) {
		return myAppProperties.getProperty(key);
	}
	
}
