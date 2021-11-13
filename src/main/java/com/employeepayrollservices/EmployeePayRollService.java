package com.employeepayrollservices;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmployeePayRollService {

	public ArrayList<Employee> empList;
	//Creating prepared Statement
	PreparedStatement preparedStatement;
	//Calling getConfig() To Establish Connection
	Connection connection = EmployeeConfig.getConfig();

	/**
	 * Creating a Generic Type Method Which Is Expecting Queries Type Parameter
	 * 
	 */
	public List<Employee> queryExecute(String query) {
		empList = new ArrayList<>();
		try {
			preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Employee employee = new Employee();

				employee.setID(resultSet.getInt("id"));
				employee.setName(resultSet.getString("name"));
				employee.setPhoneNumber(resultSet.getInt("phone"));
				employee.setAddress(resultSet.getString("address"));
				employee.setDepartment(resultSet.getString("department"));
				employee.setStart(resultSet.getString("start"));
				employee.setBasicPay(resultSet.getDouble("basic_pay"));
				employee.setDeductions(resultSet.getDouble("deductions"));
				employee.setTaxablePay(resultSet.getDouble("taxable_pay"));
				employee.setIncomeTax(resultSet.getDouble("tax"));
				employee.setNetPay(resultSet.getDouble("net_pay"));

				empList.add(employee);
			}
		} catch (SQLException e) {
			throw new EmployeeException("Invalid Column Label");
		}
		return empList;
	}

	/**
	 * This Method Is Used To Print Records From The employee_payroll table
	 */
	public void display() {
		for (Employee i : empList) {
			System.out.println(i.toString());
		}
	}

	/**
	 * This Method Is Accepting Two Parameter And Used For Update The Basic Bay Into The Salary Column From The employee_payroll table
	 * 
	 */
	public double updateBasicPay(String empName, double basicPay) {
		String UPDATE = "update employee_payroll set basic_pay = ? where EmpName = ?";
		try {
			preparedStatement = connection.prepareStatement(UPDATE);
			preparedStatement.setDouble(1, basicPay);
			preparedStatement.setString(2, empName);
			preparedStatement.executeUpdate();
			System.out.println("update successfully");

		} catch (SQLException e) {
			e.printStackTrace();
		}
		String sql = "select * from employee_payroll";
		queryExecute(sql);
		for (Employee employee : empList) {
			if (employee.getName().equals(empName)) {
				return employee.getBasicPay();
			}
		}
		return 0.0;
	}

	/**
	 * This Is a Parameterized Method Which Is Used to Print The Employee From a Range Of Given Date
	 * 
	 */

	public void selectEmployee(LocalDate start, LocalDate end) {
		ArrayList<Employee> empSelected = new ArrayList<>();
		String select = "select * from employee_payroll where EmpStart between ?and ?";
		String sDate = String.valueOf(start);
		String eDate = String.valueOf(end);
		try {
			preparedStatement = connection.prepareStatement(select);
			preparedStatement.setString(1, sDate);
			preparedStatement.setString(2, eDate);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Employee employee = new Employee();

				employee.setID(resultSet.getInt("id"));
				employee.setName(resultSet.getString("name"));
				employee.setPhoneNumber(resultSet.getInt("phone"));
				employee.setAddress(resultSet.getString("address"));
				employee.setDepartment(resultSet.getString("department"));
				employee.setStart(resultSet.getString("start"));
				employee.setBasicPay(resultSet.getDouble("basic_pay"));
				employee.setGender(resultSet.getString("gender"));
				employee.setDeductions(resultSet.getDouble("deductions"));
				employee.setTaxablePay(resultSet.getDouble("taxable_pay"));
				employee.setIncomeTax(resultSet.getDouble("tax"));
				employee.setNetPay(resultSet.getDouble("net_pay"));

				empSelected.add(employee);
			}
			for (Employee employee : empSelected) {
				System.out.println(employee);
			}
		} catch (SQLException e) {
			throw new EmployeeException("Invalid date");
		}
	}

	/**
	 * This Method Is Used To Find Sum, Average, Min, Max From The employee_payroll Table
	 * 
	 */

	public void calculate() {
		Scanner sc = new Scanner(System.in);

		final int EXIT = 6;
		int choice = 0;
		while (choice != EXIT) {
			System.out.println("Enter Your Choice \n1. SUM\n2. AVG\n3. MIN\n4. MAX  \n5.COUNT\n6.EXIT\n");
			choice = sc.nextInt();
			switch (choice) {
			case 1:
				calculateQuery("SELECT Gender, SUM(BasicPay) FROM employee_payroll GROUP BY Gender");
				break;

			case 2:
				calculateQuery("SELECT Gender, AVG(BasicPay) FROM employee_payroll GROUP BY Gender");
				break;

			case 3:
				calculateQuery("SELECT Gender, MIN(BasicPay) FROM employee_payroll GROUP BY Gender");
				break;
			case 4:
				calculateQuery("SELECT Gender, MAX(BasicPay) FROM employee_payroll GROUP BY Gender");
				break;
			case 5:
				calculateQuery("SELECT Gender, COUNT(BasicPay) FROM employee_payroll GROUP BY Gender");
				break;
			}
		}
	}

	/**
	 * This Method Is Used To Print The Basic Pay By Using The Gender
	 * 
	 */
	public void calculateQuery(String calculate) {
		List<Employee> result = new ArrayList<>();

		try {
			preparedStatement = connection.prepareStatement(calculate);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Employee employee = new Employee();
				employee.setGender(resultSet.getString(1));
				employee.setBasicPay(resultSet.getDouble(2));

				result.add(employee);
			}
			if (calculate.contains("COUNT")) {
				for (Employee i : result) {
					System.out.println("Gender: " + i.getGender() + " COUNT: " + i.getBasicPay());
				}
			} else {
				for (Employee i : result) {
					System.out.println("Gender: " + i.getGender() + " Basic pay: " + i.getBasicPay());
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}