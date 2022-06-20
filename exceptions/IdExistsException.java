package com.misha.jump.exceptions;
//exception when attempting to add employee with same id numbers

public class IdExistsException extends Exception {
private static final long serialVersionUID = 3894890100607995608L;
	
	public IdExistsException(int id) {
		super("An employee with ID: "+id+" already exists.");
	}
}
