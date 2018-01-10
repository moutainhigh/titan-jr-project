/** 
 * CopyRright ©2017 深圳市天下房仓科技有限公司 All Right Reserved.
 * 
 * @fileName TitanCardBINQueryRequest.java
 * @author Jerry
 * @date 2018年1月4日 下午4:50:02  
 */
package com.titanjr.checkstand.respnse;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import com.titanjr.checkstand.dto.TitanBindCardDTO;

/**
 * 
 * @author Jerry
 * @date 2018年1月8日 下午6:33:25
 */
public class TitanBindCardQueryResponse extends RSResponse {

	/** 
	 * 
	 */
	private static final long serialVersionUID = -2757777071441871239L;
	
	/**
	 * 用户ID
	 */
	@NotBlank
	private String userId;
	/**
	 * 总记录条数
	 */
	@NotBlank
	private String count;
	/**
	 * 结果集
	 */
	@NotNull
	private List<TitanBindCardDTO> agentProtocolList;
	
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public List<TitanBindCardDTO> getAgentProtocolList() {
		return agentProtocolList;
	}
	public void setAgentProtocolList(List<TitanBindCardDTO> agentProtocolList) {
		this.agentProtocolList = agentProtocolList;
	}

}
