package org.concatel.backEndExam3.exceptions;

public class EmptyNameException extends Exception {
	
	public EmptyNameException() { 
		super();
	}
	
	public EmptyNameException(String message) { 
		super(message);
	}

}
