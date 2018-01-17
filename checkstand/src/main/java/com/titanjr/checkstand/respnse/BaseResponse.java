/** 
 * CopyRright ©2017 深圳市天下房仓科技有限公司 All Right Reserved.
 * 
 * @fileName Response.java
 * @author Jerry
 * @date 2018年1月15日 下午3:06:25  
 */
package com.titanjr.checkstand.respnse;

import java.io.Serializable;

/**
 * @author Jerry
 * @date 2018年1月15日 下午3:06:25  
 */
public class BaseResponse implements Serializable {

	/** 
	 * 
	 */
	private static final long serialVersionUID = 7434399969143059774L;
	
	private boolean isSuccess;
	
	private String message;
	
	public BaseResponse(){
	}
	
	public void putSuccess(){
		this.isSuccess = true;
	}
	public void putError(){
		this.isSuccess = false;
	}
	public void putSuccess(String succMsg){
		this.isSuccess = true;
		this.message = succMsg;
	}
	public void putError(String errMsg){
		this.isSuccess = false;
		this.message = errMsg;
	}
	
	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	

}
