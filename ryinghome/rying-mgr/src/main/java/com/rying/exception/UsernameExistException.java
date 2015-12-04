package com.rying.exception;

public class UsernameExistException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1670869676367005718L;

	public UsernameExistException() {
	}

	public UsernameExistException(String message) {
		super(message);
	}

	public UsernameExistException(Throwable cause) {
		super(cause);
	}

	public UsernameExistException(String message, Throwable cause) {
		super(message, cause);
	}

	public UsernameExistException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
