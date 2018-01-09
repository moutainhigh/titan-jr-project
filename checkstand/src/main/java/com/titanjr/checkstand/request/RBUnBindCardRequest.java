/** 
 * CopyRright ©2017 深圳市天下房仓科技有限公司 All Right Reserved.
 * 
 * @fileName RBUnBindCardRequest.java
 * @author Jerry
 * @date 2018年1月9日 上午11:41:31  
 */
package com.titanjr.checkstand.request;

/**
 * @author Jerry
 * @date 2018年1月9日 上午11:41:31  
 */
public class RBUnBindCardRequest extends RBBaseRequest {

	/** 
	 * 
	 */
	private static final long serialVersionUID = -7258902432529620584L;
	
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

}
