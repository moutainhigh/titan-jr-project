package com.fangcang.titanjr.common.exception;

public class GlobalDaoException extends Exception {

	public GlobalDaoException() {
		super();
	}

	public GlobalDaoException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public GlobalDaoException(String message, Throwable cause) {
		super(message, cause);
	}

	public GlobalDaoException(String message) {
		super(message);
	}

	public GlobalDaoException(Throwable cause) {
		super(cause);
	}

}
