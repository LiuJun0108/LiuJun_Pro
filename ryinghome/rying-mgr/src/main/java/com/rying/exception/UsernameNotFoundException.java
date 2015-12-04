package com.rying.exception;

public class UsernameNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4387664699434352620L;

	public UsernameNotFoundException() {
	}

	public UsernameNotFoundException(String message) {
		super(message);
	}

	public UsernameNotFoundException(Throwable cause) {
		super(cause);
	}

	public UsernameNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public UsernameNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
