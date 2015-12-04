package com.rying.exception;

public class PasswordNotMatchException extends Exception {
	private static final long serialVersionUID = 1L;

	public PasswordNotMatchException() {
		super();
	}

	public PasswordNotMatchException(String message) {
		super(message);
	}

	public PasswordNotMatchException(Throwable cause) {
		super(cause);
	}

	public PasswordNotMatchException(String message, Throwable cause) {
		super(message, cause);
	}
}
