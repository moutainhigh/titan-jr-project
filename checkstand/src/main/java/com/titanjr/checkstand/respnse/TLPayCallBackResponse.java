/** 
 * CopyRright ©2017 深圳市天下房仓科技有限公司 All Right Reserved.
 * 
 * @fileName ConfirmPageCallBackResponse.java
 * @author Jerry
 * @date 2018年1月15日 下午6:47:06  
 */
package com.titanjr.checkstand.respnse;

import com.fangcang.titanjr.dto.request.RechargeResultConfirmRequest;

/**
 * @author Jerry
 * @date 2018年1月15日 下午6:47:06  
 */
public class TLPayCallBackResponse extends BaseResponse {

	/** 
	 * 
	 */
	private static final long serialVersionUID = 1958342933968047720L;
	
	private RechargeResultConfirmRequest rechargeResultConfirmRequest;
	
	private String payConfirmPageUrl;
	

	public RechargeResultConfirmRequest getRechargeResultConfirmRequest() {
		return rechargeResultConfirmRequest;
	}

	public void setRechargeResultConfirmRequest(
			RechargeResultConfirmRequest rechargeResultConfirmRequest) {
		this.rechargeResultConfirmRequest = rechargeResultConfirmRequest;
	}

	public String getPayConfirmPageUrl() {
		return payConfirmPageUrl;
	}

	public void setPayConfirmPageUrl(String payConfirmPageUrl) {
		this.payConfirmPageUrl = payConfirmPageUrl;
	}

}
