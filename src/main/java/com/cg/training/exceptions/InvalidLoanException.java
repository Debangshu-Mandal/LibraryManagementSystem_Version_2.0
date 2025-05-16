package com.cg.training.exceptions;

public class InvalidLoanException extends RuntimeException {

	static String msg = "Invalid Loan exception found";

	public InvalidLoanException() {
		super(msg);
	}

	public InvalidLoanException(String message) {
		super(message);
	}

	public InvalidLoanException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
