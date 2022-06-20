package com.misha.jump.employeeproject;
//first name, last name,employee id (needs to be unique), Date of Employment, salary, and
//department as a String (Bonus below for Department as its own class





public class Employees{
	
	public static int idCounter = 1;
	private int id;
	private String first_name;
	private String last_name;
	private String date_of_hire;
	private int salary;
	private String department;
	private int dataTypes =4;
	
	public Employees(int id, String first_name, String last_name, String date_of_hire, int salary, String department) {
		super();
		this.first_name = first_name;
		this.last_name = last_name;
		this.date_of_hire = date_of_hire;
		this.salary = salary;
		this.department = department;
		this.id = idCounter++;
	}

	
	public String getFirstName() {
		return first_name;
	}

	public void setFirstName(String first_name) {
		this.first_name = first_name;
	}
	
	public String getLastName() {
		return last_name;
	}
	
	public void setLastName(String last_name) {
		this.last_name = last_name;
	}

	public String getDateofHire() {
		return date_of_hire;
	}
	
	public void setDateofHire(String date_of_hire) {
		this.date_of_hire = date_of_hire;
	}
	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public int getId() {
		return id;
	}
//formats string for displaying
	@Override
	public String toString() {
//		return "Employee [id= " + id + ", First name= " + first_name + ", Last name= "+ last_name +", "
//				+ "Date of Hire= " + date_of_hire + "salary= " + salary + ", department= " + department + "]";
		return "Employee [Id= " + id + ", First name= " + first_name + ", Last name= "+ last_name 
				+", Date of Hire= " + date_of_hire + ", Salary= " + salary + ", Department= " + department + "]";
	}
	
	
	public void formattingInput() {
		this.first_name=this.first_name.replaceAll("_", " ");
		this.last_name=this.last_name.replaceAll("_"," ");
		this.date_of_hire=this.date_of_hire.replaceAll("_"," ");
		this.department=this.department.replaceAll("_,", " ");
		
	}
	
	//Convert back to format required for reading from file
//	public void formattingOutput() {
//		this.first_name=this.first_name.replaceAll(" ", "_");
//		this.last_name=this.last_name.replaceAll(" ", "_");                
//		this.date_of_hire=this.date_of_hire.replaceAll(" ","_");
//		this.department=this.department.replaceAll(" ", "_");
//	}
	
	public int getDataTypes() {
		return dataTypes;
	}
	
}