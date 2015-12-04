package com.rying.exception;

public class MenuNotFoundException extends Exception {
	private static final long serialVersionUID = 1L;

	public MenuNotFoundException() {
		super();
	}

	public MenuNotFoundException(String message) {
		super(message);
	}

	public MenuNotFoundException(Throwable cause) {
		super(cause);
	}

	public MenuNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

}
