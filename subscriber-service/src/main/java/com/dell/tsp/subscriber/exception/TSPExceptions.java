package com.dell.tsp.subscriber.exception;

public class TSPExceptions extends Exception {
	
	private static final long serialVersionUID = 1L;
	private String errorMessage;

	public String getErrorMessage() {
		return errorMessage;
	}

	public TSPExceptions(String errorMessage) {
		super(errorMessage);
		this.errorMessage = errorMessage;
	}

	public TSPExceptions() {
		super();
	}
}
