/** 
 * CopyRright ©2017 深圳市天下房仓科技有限公司 All Right Reserved.
 * 
 * @fileName RBCardBINQueryResponse.java
 * @author Jerry
 * @date 2018年1月4日 下午4:45:03  
 */
package com.titanjr.checkstand.respnse;

import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.titanjr.checkstand.dto.RBBindCardDTO;

/**
 * @author Jerry
 * @date 2018年1月4日 下午4:45:03  
 */
public class RBBindCardQueryResponse extends RBBaseResponse {
	
	/** 
	 * 
	 */
	private static final long serialVersionUID = 2666381507125213138L;
	
	/**
	 * 用户ID
	 */
	private String member_id;
	
	private List<RBBindCardDTO> bind_card_list;
	
	
	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public List<RBBindCardDTO> getBind_card_list() {
		return bind_card_list;
	}

	public void setBind_card_list(List<RBBindCardDTO> bind_card_list) {
		this.bind_card_list = bind_card_list;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE); 
	}

}
