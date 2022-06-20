package com.misha.jump.exceptions;

public class NoEmployeeException extends Exception {
	
private static final long serialVersionUID = 1L;
	
	public NoEmployeeException() {
		super("There are no Employees of that ID to delete");
	}

}
