package com.fangcang.titanjr.pay.req;

import java.io.Serializable;

/**
 * 融数通知data实体
 * @author luoqinglong
 * @date   2016年12月20日
 */
public class NotifyDataObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2814758983924672512L;
	private String status;
	
	private String msg;
	
	/**
	 * 类型:1-企业，2-个人
	 */
	private String type;
	/**
	 * 融数订单号
	 */
	private String orderNo;
	/**
	 * 泰坦云金融订单号
	 */
	private String buessNo;
	
	private String sign;
	
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getBuessNo() {
		return buessNo;
	}
	public void setBuessNo(String buessNo) {
		this.buessNo = buessNo;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	
}
