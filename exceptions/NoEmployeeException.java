package com.misha.jump.exceptions;
//Exception for when trying to delete an employee that does not exist

public class NoEmployeeException extends Exception {
	
private static final long serialVersionUID = 1L;
	
	public NoEmployeeException() {
		super("There are no Employees of that ID to delete");
	}

}
