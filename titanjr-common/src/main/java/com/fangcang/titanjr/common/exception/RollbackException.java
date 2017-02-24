package com.fangcang.titanjr.common.exception;

/**
 * 用户事务回滚的异常
 * @author luoqinglong
 * @2016年5月11日
 */
public class RollbackException extends Exception{

	public RollbackException() {
		super();
	}

	public RollbackException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public RollbackException(String message, Throwable cause) {
		super(message, cause);
	}

	public RollbackException(String message) {
		super(message);
	}

	public RollbackException(Throwable cause) {
		super(cause);
	}

}
