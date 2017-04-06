package com.fangcang.titanjr.exception;

public class ServiceException extends Exception {

	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = 1L;
	
	public ServiceException(String msg)
	{
		super(msg);
	}
}
