package com.fangcang.titanjr.common.exception;

public class GlobalServiceException extends Exception{

	public GlobalServiceException() {
		super();
	}

	public GlobalServiceException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public GlobalServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public GlobalServiceException(String message) {
		super(message);
	}

	public GlobalServiceException(Throwable cause) {
		super(cause);
	}

}
