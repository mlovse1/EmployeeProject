package com.misha.jump.employeeproject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.misha.jump.employeeproject.*;
import com.misha.jump.exceptions.IdExistsException;
import com.misha.jump.exceptions.NoEmployeeException;
import com.misha.jump.exceptions.NonNegativeException;
import com.misha.jump.exceptions.YesorNoException;

public class Menu {
	int getIDCount = Employees.idCounter;
	private static final int options = 7;

	public static void main(String[] args) throws IOException {

		File file = new File("Resources/EmployeeInfo.txt");
		String temp;
		String[] tempStrArray;
		if (!file.exists())
			file.createNewFile();

		FileReader fReader = new FileReader(file);
		BufferedReader reader = new BufferedReader(fReader);
		Scanner scan = new Scanner(System.in);

		ArrayList<String> fileLines = new ArrayList<String>();
		while ((temp = reader.readLine()) != null) {
			fileLines.add(temp);
		}
		reader.close();

		ArrayList<Employees> employeeSet = new ArrayList<Employees>();
		for (int i = 0; i < fileLines.size(); i++) {
			tempStrArray = fileLines.get(i).split(", ");
			employeeSet.add(new Employees(Integer.parseInt(tempStrArray[0]), tempStrArray[1], tempStrArray[2], 
					tempStrArray[3],Integer.parseInt(tempStrArray[4]), tempStrArray[5]));
		}
		for (Employees e : employeeSet) {
			e.formattingInput();
		}
		boolean cond = true;
		System.out.println("==========================================================");
		System.out.println("=            Please chooose an option?                   =");
		System.out.println("==========================================================");

		while (cond) {
			listOptions();
			int response = getValidIntResponse(options, scan);

			switch (response) {
			case 1:
				listAllEmployees(employeeSet, scan);
				break;
			case 2:
				addEmployee(employeeSet, scan);
				break;
			case 3:
				deleteEmployee(employeeSet, scan);
				break;
			case 4:
				updateEmployee(employeeSet, scan);
				break;
			case 5:
				System.out.println("Thank you! Have a great day!");
				endOfProgram(employeeSet, file);
				cond = false;

			}
		}
	}

	private static void listOptions() {

		System.out.println("========================OPTIONS===========================");
		System.out.println("==========================================================");
		System.out.println("Your options are as follows:");
		System.out.println("1: List all Employees.");
		System.out.println("2: Add an Employee");
		System.out.println("3: Remove an Employee.");
		System.out.println("4: Update an Employee.");
		System.out.println("5: Exit Management Software.");
		System.out.println("==========================================================");
		System.out.println("==========================================================");
		System.out.println();

	}

	private static void endOfProgram(ArrayList<Employees> employeeSet, File file) throws IOException {
		FileWriter fWriter = new FileWriter(file);
		BufferedWriter writer = new BufferedWriter(fWriter);
		for (Employees e : employeeSet) {
		//	e.formattingOutput();
			writer.write(e.getId() + ", " + e.getFirstName() + ", " + e.getLastName() + ", " + e.getDateofHire()
					+", " +e.getSalary() + ", " + e.getDepartment() + "\n");
		}
		writer.close();

	}

	private static int getValidIntResponse(int range, Scanner scan) {
		boolean cond = true;
		int optionHolder = -1;
		System.out.println("Enter the option you would like to use by number.");
		while (cond) {
			try {
				optionHolder = scan.nextInt();
				if (optionHolder < 1 || optionHolder > range)
					System.out.println("You must select a number between 1 and " + range + "!");
				else
					cond = false;
			} catch (InputMismatchException e) {
				System.out.println("Please enter a number!");
				scan.next();
			}
		}
		return optionHolder;
	}

	private static void listAllEmployees(ArrayList<Employees> employeeSet, Scanner scan) {
		System.out.println();
		System.out.println("Employee List: ");
		employeeSet.forEach(System.out::println);
		
					
	}


	private static void addEmployee(ArrayList<Employees> employeeSet, Scanner scan) {
		int getIDCount = Employees.idCounter;
		System.out.println("What is the First Name of the Employee that you will be adding?");
		String first_name = getStringValidated("First Name", scan);
		System.out.println("What is the Last Name of the Employee that you will be adding?");
		String last_name = getStringValidated("Last Name", scan);
		System.out.println("What is the Date of Hire of the Employee that you will be adding?");
		String date_of_hire = getStringValidated("Date of Hire", scan);
		System.out.println("What is the Salary of the Employee that you will be adding?");
		Integer salary = getValidInt(scan);
		System.out.println("What is the Department of the Employee that you are adding?");
		String department = getStringValidated("Department", scan);

		Employees addNewEmployee = new Employees(getIDCount, first_name, last_name, date_of_hire, salary, department);
		employeeSet.add(addNewEmployee);
		
	}

	private static int getValidInt(Scanner scan) {
		boolean cond = true;
		int holder = 0;
		while (cond) {
			try {
				holder = scan.nextInt();
				cond = false;
			} catch (InputMismatchException e) {
				System.out.println("Please enter a number!");
				scan.next();
			}
		}
		return holder;
	}

	private static void deleteEmployee(ArrayList<Employees> employeeSet, Scanner scan) {

		System.out.println("What is the ID of the Employee that you would like to remove?");

		int address = searchEmployeeID(employeeSet, scan);
		Employees holder = employeeSet.remove(address);
		System.out.println("Employee: " + holder.getId() + ", " + holder.getFirstName() + " has been removed!");

	}

	private static int searchEmployeeID(ArrayList<Employees> employeeSet, Scanner scan) {
		int address = -1;
		boolean cond = false;
		while (!cond) {
			try {
				int empID = getValidInt(scan);

				for (int i = 0; i < employeeSet.size(); i++) {
					if (empID == employeeSet.get(i).getId()) {
						address = i;
						i = employeeSet.size() + 1;
						cond = true;
					}
				}
				if (address == -1)
					throw new NoEmployeeException();

			} catch (NoEmployeeException e) {
				System.out.println(e.getMessage());
				System.out.println("Please enter in a valid ID!");
			}
		}
		return address;
	}

	private static void updateEmployee(ArrayList<Employees> employeeSet, Scanner scan) {
		System.out.println("What is the ID of the Employee " + "that you would like to update?");
		int address = searchEmployeeID(employeeSet, scan);
		int dataTypes = employeeSet.get(address).getDataTypes();
		Employees currentEmployee = employeeSet.get(address);

		System.out.println("What do you need to update for " + employeeSet.get(address).getFirstName() + "?");

		System.out.println("1: Change First Name.");
		System.out.println("2: Change Last Name.");
		System.out.println("3: Change Date of Hire.");
		System.out.println("4: Change the Salary.");
		System.out.println("5: Change the Department.");

		int answer = getValidIntResponse(dataTypes, scan);

		switch (answer) {
		case 1:
			System.out.println("What would you like to change " + currentEmployee.getFirstName() + " " + 
					currentEmployee.getLastName() + "'s First Name to? ");
			String newFirstName = scan.nextLine();
			currentEmployee.setFirstName(newFirstName);
			System.out.println("Name successfully changed to " + currentEmployee.getFirstName() + ".");
			break;
		case 2:
			System.out.println("What would you like to change " + currentEmployee.getFirstName() + " " + 
					currentEmployee.getLastName() + "'s Last Name to? ");
			String newLastName = scan.nextLine();
			currentEmployee.setLastName(newLastName);
			System.out.println("Name successfully changed to " + currentEmployee.getLastName() + ".");
			break;
		case 3:
			System.out.println("What would you like to change " + currentEmployee.getFirstName() + " " + 
					currentEmployee.getLastName() + "'s Date of Hire to? ");
			String newDateofHire = scan.nextLine();
			currentEmployee.setLastName(newDateofHire);
			System.out.println("Date of Hire successfully changed to " + currentEmployee.getDateofHire() + ".");
			break;
		case 4:
			System.out.println("What would you like to change " + currentEmployee.getFirstName() + " " + 
					currentEmployee.getLastName() + "'s Salary to? ");
			int newSalary = scan.nextInt();
			scan.nextLine();
			currentEmployee.setSalary(newSalary);
			System.out.println("Name successfully changed to " + currentEmployee.getDateofHire() + ".");
		case 5:
			System.out.println("What would you like to change " + currentEmployee.getFirstName() + " " + 
		currentEmployee.getLastName() + "'s Department to? "
					+ "(Current Department is :" + currentEmployee.getDepartment() + ")");
			String newDepartment = scan.nextLine();
			currentEmployee.setDepartment(newDepartment);
			System.out.println("Name successfully changed to " + currentEmployee.getDepartment() + ".");
		default:
			System.out.println("This should not be reached.");
		}
	}

//validation methods
	private static String getStringValidated(String string, Scanner scan) {
		String holder = null;
		char acceptance;
		boolean cond = false;
		while (!cond) {
			scan.nextLine();
			holder = scan.nextLine();
			System.out.println(
					"You entered: " + holder + " for the new " + string + ". " + "\n Is this Acceptable?(Y/N)");
			acceptance = getYorN(scan);
			if (acceptance == 'N') {
				System.out.println("What would you like to change " + string + " to?");
			} else
				cond = true;
		}
		return holder;
	}

	private static char getYorN(Scanner scan) {
		boolean cond = false;
		char acceptance = 'l';
		while (!cond) {
			try {
				acceptance = scan.next().toUpperCase().charAt(0);
				if (acceptance != 'Y' && acceptance != 'N')
					throw new YesorNoException();
				cond = true;

			} catch (YesorNoException e) {
				System.out.println(e.getMessage());
			}
		}
		return acceptance;
	}

}
