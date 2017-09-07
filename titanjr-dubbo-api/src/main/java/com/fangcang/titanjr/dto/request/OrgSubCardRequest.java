package com.fangcang.titanjr.dto.request;

/***
 * 机构绑卡
 * @author luoqinglong
 * @date 2017年9月5日
 */
public class OrgSubCardRequest extends CusBankCardBindRequest {
	/**
	 * 虚拟金融机构商家编码
	 */
	private String orgCode;

	
	
	
	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	
	
}
