/** 
 * CopyRright ©2017 深圳市天下房仓科技有限公司 All Right Reserved.
 * 
 * @fileName RBUnBindCardResponse.java
 * @author Jerry
 * @date 2018年1月9日 上午11:44:00  
 */
package com.titanjr.checkstand.respnse;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * @author Jerry
 * @date 2018年1月9日 上午11:44:00  
 */
public class RBUnBindCardResponse extends RBBaseResponse {

	/** 
	 * 
	 */
	private static final long serialVersionUID = 2578273491149946416L;
	
	/**
	 * 用户ID
	 */
	private String member_id;
	/**
	 * 签约时返回的绑卡ID
	 */
	private String bind_id;
	
	
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getBind_id() {
		return bind_id;
	}
	public void setBind_id(String bind_id) {
		this.bind_id = bind_id;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE); 
	}

}
