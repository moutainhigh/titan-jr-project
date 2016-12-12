package com.fangcang.titanjr.dto.request;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

/***
 * 授信结果通知
 * @author luoqinglong
 * @date   2016年12月8日
 */
public class NotifyRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1316129673393022343L;
	/**
	 * 状态:4-审核不通过，5-通过，6-授信过期，7-授信失败，
	 */
	private Integer  status;
	// 通知的中文信息
	private String msg;
	
	/**
	 * 商户订单号
	 */
	@NotNull
	private String buessNo;
	
	
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getBuessNo() {
		return buessNo;
	}
	public void setBuessNo(String buessNo) {
		this.buessNo = buessNo;
	}
	
	
}
