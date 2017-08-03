package com.fangcang.titanjr.dto.response.gateway;

import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import com.fangcang.titanjr.dto.bean.gateway.QuickPayCardDTO;

public class QueryQuickPayBindCardResponse extends RSBaseResponse {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4535143650121386484L;
	
	private String merchantNo;
	
	private String userId;
	
	private List<QuickPayCardDTO> agentProtocolList;
	
	private String version;
	
	private String signType;
	
	private String signMsg;


	public String getMerchantNo() {
		return merchantNo;
	}

	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getSignType() {
		return signType;
	}

	public void setSignType(String signType) {
		this.signType = signType;
	}

	public String getSignMsg() {
		return signMsg;
	}

	public void setSignMsg(String signMsg) {
		this.signMsg = signMsg;
	}
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List<QuickPayCardDTO> getAgentProtocolList() {
		return agentProtocolList;
	}

	public void setAgentProtocolList(List<QuickPayCardDTO> agentProtocolList) {
		this.agentProtocolList = agentProtocolList;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

}
