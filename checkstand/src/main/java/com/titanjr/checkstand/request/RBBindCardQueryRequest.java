/** 
 * CopyRright ©2017 深圳市天下房仓科技有限公司 All Right Reserved.
 * 
 * @fileName RBCardBINQueryRequest.java
 * @author Jerry
 * @date 2018年1月4日 下午4:42:14  
 */
package com.titanjr.checkstand.request;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 融宝绑卡列表查询请求
 * @author Jerry
 * @date 2018年1月8日 下午6:20:28
 */
public class RBBindCardQueryRequest extends RBBaseRequest {
	
	/** 
	 * 
	 */
	private static final long serialVersionUID = 4551790039120468822L;
	
	/**
	 * 商户的用户ID
	 */
	@NotBlank
	private String member_id;
	/**
	 * 卡类型   0表示储蓄卡，1表示信用卡
	 */
	@NotBlank
	private String bank_card_type;
	
	
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getBank_card_type() {
		return bank_card_type;
	}
	public void setBank_card_type(String bank_card_type) {
		this.bank_card_type = bank_card_type;
	}

}
