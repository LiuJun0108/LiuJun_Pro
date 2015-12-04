package com.rying.exception;

public class FileTypeNotAllowException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7453769944513515306L;

	public FileTypeNotAllowException() {
	}

	public FileTypeNotAllowException(String message) {
		super(message);
	}

	public FileTypeNotAllowException(Throwable cause) {
		super(cause);
	}

	public FileTypeNotAllowException(String message, Throwable cause) {
		super(message, cause);
	}

	public FileTypeNotAllowException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
