package com.fangcang.titanjr.common.exception;

/**
 * 需要传递给controller层的提示信息，使用该异常抛出
 * @author luoqinglong
 * @2016年5月17日
 */
public class MessageServiceException extends Exception{
	private String code;
	public MessageServiceException() {
		super();
	}

	public MessageServiceException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public MessageServiceException(String message, Throwable cause) {
		super(message, cause);
	}
	public MessageServiceException(String code, String message, Throwable cause) {
		super(message, cause);
		this.code = code;
	}

	public MessageServiceException(String message) {
		super(message);
	}
	public MessageServiceException(String code,String message) {
		super(message);
		this.code = code;
	}

	public MessageServiceException(Throwable cause) {
		super(cause);
	}

	public String getCode() {
		return code;
	}

}
