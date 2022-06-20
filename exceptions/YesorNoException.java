package com.misha.jump.exceptions;

//Exception that is thrown if a user tries to enter an acceptance other than Y or N 

public class YesorNoException extends Exception {
	private static final long serialVersionUID = 9098578360011113179L;

	public YesorNoException(){
		super("Invalid entry! Enter Y or N!");
	}
}
