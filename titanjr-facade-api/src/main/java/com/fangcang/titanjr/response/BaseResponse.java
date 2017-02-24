package com.fangcang.titanjr.response;

import java.io.Serializable;


public class BaseResponse implements Serializable{
	
	public enum ReturnCode{
		CODE_SUCCESS("1","成功"),CODE_PARAM_ERROR("-100","参数错误"),CODE_SYS_ERROR("-200","系统错误");
		private String code;
		private String msg;
		
		private ReturnCode(String code,String msg){
			this.code = code;
			this.msg = msg;
		}

		public String getCode() {
			return code;
		}

		public String getMsg() {
			return msg;
		}
	}
	/**
	 * 执行成功
	 */
	public void putSuccess(){
		setResult(true);
		setReturnCode(ReturnCode.CODE_SUCCESS.getCode());
		setReturnMessage(ReturnCode.CODE_SUCCESS.getMsg());
	}
	/***
	 * 参数错误
	 */
	public void putParamError() {
		setResult(false);
		setReturnCode(ReturnCode.CODE_PARAM_ERROR.getCode());
		setReturnMessage(ReturnCode.CODE_PARAM_ERROR.getMsg());
	}
	
	/**
	 * 系统错误
	 */
	public void putSysError() {
		setResult(false);
		setReturnCode(ReturnCode.CODE_SYS_ERROR.getCode());
		setReturnMessage(ReturnCode.CODE_SYS_ERROR.getMsg());
	}
	/***
	 * 其他自定义错误
	 * @param code
	 * @param msg
	 */
	public void putErrorResult(String code,String msg) {
		setResult(false);
		setReturnCode(code);
		setReturnMessage(msg);
	}
	
	private String returnCode;
	
	private String returnMessage;
	
	//调用成功
	private boolean result = false;

	public String getReturnCode() {
		return returnCode;
	}
	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}
	public String getReturnMessage() {
		return returnMessage;
	}
	public void setReturnMessage(String returnMessage) {
		this.returnMessage = returnMessage;
	}
	public boolean isResult() {
		return result;
	}
	public void setResult(boolean result) {
		this.result = result;
	}

}
