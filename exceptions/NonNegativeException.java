package com.misha.jump.exceptions;

public class NonNegativeException extends Exception {

	private static final long serialVersionUID = -5267027215831035537L;

	public NonNegativeException() {
		super("ID must be a positive non 0 number!");
	}
	
	
	
}
