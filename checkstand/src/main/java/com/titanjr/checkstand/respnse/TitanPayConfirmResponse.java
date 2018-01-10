/** 
 * CopyRright ©2017 深圳市天下房仓科技有限公司 All Right Reserved.
 * 
 * @fileName TitanPayConfirmResponse.java
 * @author Jerry
 * @date 2018年1月5日 下午2:11:46  
 */
package com.titanjr.checkstand.respnse;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @author Jerry
 * @date 2018年1月5日 下午2:11:46  
 */
public class TitanPayConfirmResponse extends RSResponse {

	/** 
	 * 
	 */
	private static final long serialVersionUID = 7367477642200840963L;
	
	/**
	 * 支付方式   41：新快捷支付
	 */
	@NotBlank
	private String payType;
	

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

}
