package com.aglifetech.society.cust.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.aglifetech.society.cust.util.PropertyUtil;

public class MyConnectionManager {

	private final static String DB_USER_NAME = PropertyUtil.getProperty("DB_USER_NAME");
	private final static String DB_USER_PASSWORD = "password";
	// Which database, where is located, name of db
	private final static String DB_URL = "jdbc:mysql://localhost/societyclient";
	private final static String JDBC_DRIVER = "com.mysql.jdbc.Driver";

	public static Connection getConnection() {
		// Step 1 - Register Driver
		try {
			Class.forName(JDBC_DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		Connection conn = null;

		try {

			// Step - 2 Get Database Connection
			conn = DriverManager.getConnection(DB_URL, DB_USER_NAME, DB_USER_PASSWORD);
			
			conn.setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return conn;
	}

}
