/** 
 * CopyRright ©2017 深圳市天下房仓科技有限公司 All Right Reserved.
 * 
 * @fileName TLGatewayPayDownloadRequest.java
 * @author Jerry
 * @date 2018年3月14日 上午11:18:56  
 */
package com.titanjr.checkstand.request;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 通联网关支付对账文件下载请求
 * @author Jerry
 * @date 2018年3月14日 上午11:18:56  
 */
public class TLGatewayPayDownloadRequest extends TLBaseRequest {
	
	/**
	 * 商户号，由通联统一分配
	 */
	@NotBlank
	private String mchtCd;

	/**
	 * 格式为 yyyy-MM-dd，是指通联结算的自然日期
	 */
	@NotBlank
	private String settleDate ;

	/**
	 * 签名字符串
	 */
	@NotBlank
	private String signMsg ;
	

	public String getMchtCd() {
		return mchtCd;
	}

	public void setMchtCd(String mchtCd) {
		this.mchtCd = mchtCd;
	}

	public String getSettleDate() {
		return settleDate;
	}

	public void setSettleDate(String settleDate) {
		this.settleDate = settleDate;
	}

	public String getSignMsg() {
		return signMsg;
	}

	public void setSignMsg(String signMsg) {
		this.signMsg = signMsg;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE); 
	}

}
