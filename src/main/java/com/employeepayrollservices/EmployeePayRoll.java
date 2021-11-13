package com.employeepayrollservices;

import java.time.LocalDate;
import java.util.Scanner;

/**
 *  This Class Is Used For To Take Input From Used Perform The Operation
 * 
 */

public class EmployeePayRoll {
	public static void main(String[] args) {
		EmployeePayRollService employeePayRollService = new EmployeePayRollService();
		Scanner scanner = new Scanner(System.in);

		final int EXIT = 10;
		int choice = 0;
		while (choice != EXIT) {
			System.out.println("Enter Your Choice\n1. Get employee data\n2. update basic pay\n3. display employee roll\n4. empdata range  \n5. calculate \n6. EXIT\n");
			choice = scanner.nextInt();
			
			//Using the Switch Case For The Taking The Choice from The User 
			switch (choice) {
			case 1:
				String query = "select * from employee_payroll";
				employeePayRollService.queryExecute(query);
				employeePayRollService.display();
				break;
			case 2:
				System.out.println("enter employee name");
				String empName = scanner.next();
				System.out.println("enter basic pay you want to update");
				double basic_pay = scanner.nextDouble();
				employeePayRollService.updateBasicPay(empName, basic_pay);
				break;

			case 3:
				employeePayRollService.display();
				break;
			case 4:
				System.out.println("enter initial date");
				LocalDate iDate = LocalDate.parse(scanner.next());
				System.out.println("enter final date");
				LocalDate eDate = LocalDate.parse(scanner.next());
				employeePayRollService.selectEmployee(iDate, eDate);
				break;

			case 5:
				employeePayRollService.calculate();
				break;

			case 6:
				System.out.println("Good Bye");
				System.exit(0);

			}
		}
	}

}