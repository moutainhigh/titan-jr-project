package com.fangcang.titanjr.dto.response;

import com.fangcang.titanjr.dto.BaseResponseDTO;
import com.fangcang.titanjr.entity.TitanBankcard;
/***
 * 虚拟机构绑卡状态
 * @author luoqinglong
 * @date 2017年9月1日
 */
public class BankCardStatusResponse extends BaseResponseDTO {
	
	/**
	 * 真实机构
	 */
	private String orgSubCode;
	/***
	 * 绑卡提示信息
	 */
	private String orgBankcardMsg;
	/**
	 * 绑卡状态,参考：BankCardEnum.BankCardStatusEnum
	 */
	private String orgBankcardStatus;
	
	
	private TitanBankcard bankcard;
	
	
	public String getOrgSubCode() {
		return orgSubCode;
	}
	public void setOrgSubCode(String orgSubCode) {
		this.orgSubCode = orgSubCode;
	}
	public String getOrgBankcardMsg() {
		return orgBankcardMsg;
	}
	public void setOrgBankcardMsg(String orgBankcardMsg) {
		this.orgBankcardMsg = orgBankcardMsg;
	}
	public String getOrgBankcardStatus() {
		return orgBankcardStatus;
	}
	public void setOrgBankcardStatus(String orgBankcardStatus) {
		this.orgBankcardStatus = orgBankcardStatus;
	}
	public TitanBankcard getBankcard() {
		return bankcard;
	}
	public void setBankcard(TitanBankcard bankcard) {
		this.bankcard = bankcard;
	}
	 
}
