package com.rying.exception;

public class ResourcesNotFoundException extends Exception {
	private static final long serialVersionUID = 1L;

	public ResourcesNotFoundException() {
		super();
	}

	public ResourcesNotFoundException(String message) {
		super(message);
	}

	public ResourcesNotFoundException(Throwable cause) {
		super(cause);
	}

	public ResourcesNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}
