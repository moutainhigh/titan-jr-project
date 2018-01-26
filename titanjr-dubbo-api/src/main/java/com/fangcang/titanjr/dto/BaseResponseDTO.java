package com.fangcang.titanjr.dto;

import java.io.Serializable;

import com.fangcang.titanjr.common.enums.TitanMsgCodeEnum;

/**
 * Created by zhaoshan on 2016/4/25.
 */
public class BaseResponseDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8695905724380391232L;
	protected static final  String CODE_SUCCESS = "1";//成功
	protected static final  String CODE_ERROR = "-100";//错误提示
	public static final  String FINANCE_SYS_ERROR = "-400";//系统错误
	
	  /**
     * 返回结果true/false
     */
    protected boolean result = false;
    /**
     * 返回信息的编码
     */
    protected String returnCode;
    /**
     * 返回的信息
     */
    protected String returnMessage;
    
    
	public enum ReturnCode{
		CODE_SUCCESS("1","成功"),CODE_PARAM_ERROR("-200","参数错误"),CODE_SYS_ERROR("-300","系统错误");
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
	public void putSuccess(String msg){
		setResult(true);
		setReturnCode(ReturnCode.CODE_SUCCESS.getCode());
		setReturnMessage(msg);
	}
	/***
	 * 参数错误
	 */
	public void putParamError() {
		setResult(false);
		setReturnCode(ReturnCode.CODE_PARAM_ERROR.getCode());
		setReturnMessage(ReturnCode.CODE_PARAM_ERROR.getMsg());
	}
	/***
	 * 参数错误
	 */
	public void putParamError(String msg) {
		setResult(false);
		setReturnCode(ReturnCode.CODE_PARAM_ERROR.getCode());
		setReturnMessage(msg);
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
	/**
	 * 提示错误信息
	 * @param msg
	 */
	public void putErrorResult(String msg) {
		setResult(false);
		setReturnCode(CODE_ERROR);
		setReturnMessage(msg);
	}
	
	public void putErrorResult(TitanMsgCodeEnum titanMsgCodeEnum){
		setResult(false);
		setReturnCode(titanMsgCodeEnum.getKey());
		setReturnMessage(titanMsgCodeEnum.getResMsg());
	}
  

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

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
}