package com.test.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtils {
	private final static String DB_URL = "jdbc:mysql://localhost/estatebasic?useUnicode=true&characterEncoding=UTF-8";
	private final static String USER = "root";
	private final static String PASS = "";
		
	public static Connection getConnection() {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(DB_URL, USER, PASS);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
}
