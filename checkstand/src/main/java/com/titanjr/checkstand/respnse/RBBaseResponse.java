/** 
 * CopyRright ©2017 深圳市天下房仓科技有限公司 All Right Reserved.
 * 
 * @fileName RBBaseResponse.java
 * @author Jerry
 * @date 2018年1月4日 下午4:46:21  
 */
package com.titanjr.checkstand.respnse;

import java.io.Serializable;

/**
 * @author Jerry
 * @date 2018年1月4日 下午4:46:21  
 */
public class RBBaseResponse implements Serializable {

	/** 
	 * 
	 */
	private static final long serialVersionUID = -3293093961720859346L;
	
	private String merchant_id;
	private String result_code;
	private String result_msg;
	
	public String getMerchant_id() {
		return merchant_id;
	}
	public void setMerchant_id(String merchant_id) {
		this.merchant_id = merchant_id;
	}
	public String getResult_code() {
		return result_code;
	}
	public void setResult_code(String result_code) {
		this.result_code = result_code;
	}
	public String getResult_msg() {
		return result_msg;
	}
	public void setResult_msg(String result_msg) {
		this.result_msg = result_msg;
	}

}
