package com.fangcang.titanjr.pay.req;

/***
 * 绑卡
 * @author luoqinglong
 * @date 2017年9月21日
 */
public class BindCardRequest {
	//虚拟机构
	private String orgCode;
	//开户行编码
	private String bankCode;
	//开户行名称
    private String bankName;
    //卡号
    private String accountNum;
     
    
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	public String getBankCode() {
		return bankCode;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getAccountNum() {
		return accountNum;
	}
	public void setAccountNum(String accountNum) {
		this.accountNum = accountNum;
	}
	
}
