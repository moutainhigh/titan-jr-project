package com.fangcang.titanjr.dto.request;

import com.fangcang.titanjr.dto.BaseRequestDTO;

public class JudgeAllowNoPwdPayRequest extends  BaseRequestDTO{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//二级机构识别号
	private String userid;
	//支付金额
	private String money;
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}
}
