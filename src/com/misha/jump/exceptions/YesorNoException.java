package com.misha.jump.exceptions;

public class YesorNoException extends Exception {
	private static final long serialVersionUID = 9098578360011113179L;

	public YesorNoException(){
		super("Invalid entry! Enter Y or N!");
	}
}
