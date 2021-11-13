package com.employeepayrollservices;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class EmployeeConfig {

	private static Connection connection = null;
	static {
		String URL_JD = "jdbc:mysql://localhost:3306/employeepayrollservices";
		String USER_NAME = "root";
		String PASSWORD = "root";
		Connection connection = null;

		// Use the try block for checking connection
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Drivers loaded!!");
			connection = DriverManager.getConnection(URL_JD, USER_NAME, PASSWORD);
			System.out.println("connection Established!!");

			// Use the catch block for the handle the exception
		} catch (ClassNotFoundException e) {
			throw new EmployeeException("Invalid driver");
		} catch (SQLException throwables) {
			throw new EmployeeException("Invalid get connection parameters");
		}
	}

	public static Connection getConfig() {
		return connection;
	}
}