/** 
 * CopyRright ©2017 深圳市天下房仓科技有限公司 All Right Reserved.
 * 
 * @fileName RBCardAuthRequest.java
 * @author Jerry
 * @date 2018年1月8日 下午3:15:37  
 */
package com.titanjr.checkstand.request;

/**
 * 融宝卡密鉴权请求
 * @author Jerry
 * @date 2018年1月8日 下午3:15:37  
 */
public class RBCardAuthRequest extends RBBaseRequest {

	/** 
	 * 
	 */
	private static final long serialVersionUID = 6991868634339234470L;
	
	/**
	 * 签约时返回的绑卡ID
	 */
	private String bind_id;
	/**
	 * 商户的用户ID
	 */
	private String member_id;
	/**
	 * 商户订单号
	 */
	private String order_no;
	/**
	 * 终端类型  web、wap、mobile
	 */
	private String terminal_type;
	/**
	 * 前端展示返回url
	 */
	private String return_url;
	/**
	 * 后台异步通知地址
	 */
	private String notify_url;
	
	
	public String getBind_id() {
		return bind_id;
	}
	public void setBind_id(String bind_id) {
		this.bind_id = bind_id;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getOrder_no() {
		return order_no;
	}
	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}
	public String getTerminal_type() {
		return terminal_type;
	}
	public void setTerminal_type(String terminal_type) {
		this.terminal_type = terminal_type;
	}
	public String getReturn_url() {
		return return_url;
	}
	public void setReturn_url(String return_url) {
		this.return_url = return_url;
	}
	public String getNotify_url() {
		return notify_url;
	}
	public void setNotify_url(String notify_url) {
		this.notify_url = notify_url;
	}

}
