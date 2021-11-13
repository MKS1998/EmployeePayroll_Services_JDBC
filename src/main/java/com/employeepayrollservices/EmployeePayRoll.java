package com.employeepayrollservices;

import java.util.Scanner;

public class EmployeePayRoll {
	public static void main(String[] args) {
		EmployeePayRollService employeePayRollService = new EmployeePayRollService();
		Scanner sc = new Scanner(System.in);

		final int EXIT = 10;
		int choice = 0;
		while (choice != EXIT) {
			System.out.println("Enter your choice\n 1. Execute query\n 2. update basic pay\n 3. display employee roll\n 10. EXIT");
			choice = sc.nextInt();
			switch (choice) {
			case 1: {
				String query = "select * from employee_payroll";
				employeePayRollService.queryExecute(query);
				employeePayRollService.display();
			}
			case 2: {
				System.out.println("enter employee name");
				String empName = sc.next();
				System.out.println("enter basic pay you want to update");
				double basicPay = sc.nextDouble();
				employeePayRollService.updateBasicPay(empName, basicPay);
			}
			case 3:
				employeePayRollService.display();
			case 10:
				System.out.println("good bye");
			}
		}
	}
}