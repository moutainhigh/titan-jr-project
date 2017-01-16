package com.fangcang.titanjr.dto.request;

import com.fangcang.titanjr.dto.BaseRequestDTO;
/**
 * 应收账款申请协议确认
 * @author luoqinglong
 * @date   2016年12月15日
 */
public class AgreementConfirmRequest extends BaseRequestDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4025679264343445702L;
	/**
	 * 授信申请的单号
	 */
	private String buessNo;
	
	public String getBuessNo() {
		return buessNo;
	}
	public void setBuessNo(String buessNo) {
		this.buessNo = buessNo;
	}
	
}
