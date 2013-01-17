package com.dipayan.core;

public class MissingValueException extends RuntimeException {

	private static final long serialVersionUID = 3637960594832286402L;
	
	public MissingValueException(String msg) {
		super(msg);
	}

}
