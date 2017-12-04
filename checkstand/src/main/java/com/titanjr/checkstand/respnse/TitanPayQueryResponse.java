/** 
 * CopyRright ©2017 深圳市天下房仓科技有限公司 All Right Reserved.
 * 
 * @fileName TLNBPayOrderQueryResponse.java
 * @author Jerry
 * @date 2017年12月1日 上午11:23:27  
 */
package com.titanjr.checkstand.respnse;

/**
 * @author Jerry
 * @date 2017年12月1日 上午11:23:27  
 */
public class TitanPayQueryResponse extends BaseResponse {

	/** 
	 * 
	 */
	private static final long serialVersionUID = -8576601030322372459L;
	
	/**
	 * 支付状态  1支付成功
	 */
	private String payStatsu;

	public String getPayStatsu() {
		return payStatsu;
	}

	public void setPayStatsu(String payStatsu) {
		this.payStatsu = payStatsu;
	}

}
