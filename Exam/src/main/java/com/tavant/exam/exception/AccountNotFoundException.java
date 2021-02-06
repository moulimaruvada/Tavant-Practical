package com.tavant.exam.exception;

public class AccountNotFoundException extends Exception {
	
	public AccountNotFoundException(String msg) {
		super(msg);
	}

	
	@Override	
	public String toString() {
	
		return super.toString()+ this.getMessage();
	}
}
