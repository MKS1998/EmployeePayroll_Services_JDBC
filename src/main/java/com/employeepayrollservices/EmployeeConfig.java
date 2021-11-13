package com.employeepayrollservices;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *  This Class Is Used For Establish Connection Between Java Program And Database
 */

public class EmployeeConfig {

    private static Connection connection = null;
/**
 * Using The Static Block Creating The Connection
 */
    static {
        String URL_JD = "jdbc:mysql://localhost:3306/employeepayrollservices";
        String USER_NAME = "root";
        String PASSWORD = "root";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL_JD, USER_NAME, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new EmployeeException("Invalid driver");
        } catch (SQLException throwable) {
            throw new EmployeeException("Invalid get connection parameters");
        }
    }
    /**
     * Creating The getConfig Method To Return The Connection
     */
    public static Connection getConfig(){
        return connection;
    }
}