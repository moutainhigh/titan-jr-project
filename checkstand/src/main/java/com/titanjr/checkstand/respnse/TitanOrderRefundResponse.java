/** 
 * CopyRright ©2017 深圳市天下房仓科技有限公司 All Right Reserved.
 * 
 * @fileName TitanOrderRefundResponse.java
 * @author Jerry
 * @date 2017年12月4日 上午11:39:48  
 */
package com.titanjr.checkstand.respnse;


/**
 * @author Jerry
 * @date 2017年12月4日 上午11:39:48  
 */
public class TitanOrderRefundResponse extends BaseResponse {
	/** 
	 * 
	 */
	private static final long serialVersionUID = 2255159199767542474L;
	
	private String refundStatus;
	

	public String getRefundStatus() {
		return refundStatus;
	}

	public void setRefundStatus(String refundStatus) {
		this.refundStatus = refundStatus;
	}

}
