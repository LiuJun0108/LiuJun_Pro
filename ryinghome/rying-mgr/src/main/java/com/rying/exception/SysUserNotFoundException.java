package com.rying.exception;

public class SysUserNotFoundException extends Exception {
	private static final long serialVersionUID = 1L;

	public SysUserNotFoundException() {
		super();
	}

	public SysUserNotFoundException(String message) {
		super(message);
	}

	public SysUserNotFoundException(Throwable cause) {
		super(cause);
	}

	public SysUserNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

}
