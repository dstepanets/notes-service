package com.proxyseller.notes.exception;

public class ValidationException extends RuntimeException {
	public ValidationException() {
	}

	public ValidationException(String message) {
		super(message);
	}
}
