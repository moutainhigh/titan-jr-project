/** 
 * CopyRright ©2017 深圳市天下房仓科技有限公司 All Right Reserved.
 * 
 * @fileName TitanAgentPayResponse.java
 * @author Jerry
 * @date 2017年12月25日 下午3:20:51  
 */
package com.titanjr.checkstand.respnse;

import org.hibernate.validator.constraints.NotBlank;

import com.fangcang.titanjr.common.enums.WithDrawStatusEnum;
import com.titanjr.checkstand.constants.TradeCodeEnum;

/**
 * 代付返回结果
 * @author Jerry
 * @date 2017年12月25日 下午3:20:51  
 */
public class TitanAgentPayResponse extends RSResponse {

	/** 
	 * 
	 */
	private static final long serialVersionUID = 6448112507980722639L;
	
	/**
	 * 交易代码 {@link TradeCodeEnum}
	 */
	@NotBlank
	private String tradeCode;
	
	/**
	 * 交易状态  {@link WithDrawStatusEnum}
	 */
	private String status;
	

	public String getTradeCode() {
		return tradeCode;
	}

	public void setTradeCode(String tradeCode) {
		this.tradeCode = tradeCode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
