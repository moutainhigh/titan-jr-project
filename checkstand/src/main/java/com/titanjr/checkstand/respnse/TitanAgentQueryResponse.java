/** 
 * CopyRright ©2017 深圳市天下房仓科技有限公司 All Right Reserved.
 * 
 * @fileName TitanAgentPayResponse.java
 * @author Jerry
 * @date 2017年12月25日 下午3:20:51  
 */
package com.titanjr.checkstand.respnse;

import java.util.List;

import org.hibernate.validator.constraints.NotBlank;

import com.titanjr.checkstand.constants.AgentTradeCodeEnum;
import com.titanjr.checkstand.dto.TitanAgentResDetailDTO;

/**
 * 代付返回结果
 * @author Jerry
 * @date 2017年12月25日 下午3:20:51  
 */
public class TitanAgentQueryResponse extends RSResponse {

	/** 
	 * 
	 */
	private static final long serialVersionUID = 6448112507980722639L;
	
	/**
	 * 交易代码 {@link AgentTradeCodeEnum}
	 */
	@NotBlank
	private String tradeCode;
	
	/**
	 * 签名信息
	 */
	private String signMsg;  
	
	/**
	 * 查询返回码   00所有的交易都在处理中    01查询成功   02所有的交易都失败   03没有对应的交易<br>
	 * retCode为01时，交易明细才有值 
	 */
	private String retCode;
	
	/**
	 * 交易明细
	 */
	private List<TitanAgentResDetailDTO> details;
	

	public String getTradeCode() {
		return tradeCode;
	}

	public void setTradeCode(String tradeCode) {
		this.tradeCode = tradeCode;
	}

	public String getSignMsg() {
		return signMsg;
	}

	public void setSignMsg(String signMsg) {
		this.signMsg = signMsg;
	}

	public List<TitanAgentResDetailDTO> getDetails() {
		return details;
	}

	public void setDetails(List<TitanAgentResDetailDTO> details) {
		this.details = details;
	}

	public String getRetCode() {
		return retCode;
	}

	public void setRetCode(String retCode) {
		this.retCode = retCode;
	}

}
