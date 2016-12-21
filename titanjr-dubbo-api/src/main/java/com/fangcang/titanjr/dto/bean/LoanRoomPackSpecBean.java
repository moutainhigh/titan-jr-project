package com.fangcang.titanjr.dto.bean;

import java.util.Date;

/**
 * 贷款包房贷规格
 * 
 * @author wengxitao
 *
 */
public class LoanRoomPackSpecBean extends LoanSpecBean {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String hotleName;// 酒店名称

	private Date beginDate;// 包房开始时间

	private Date endDate;// 包房结束时间

	private int roomNights;// 包房数量(间夜)

	private String accountName;// 账户名称

	private String account;// 账户号码

	private String bank;// 开户银行

	private String contractUrl;// 合同地址（多个以分号分割）
	
	private String amount;//贷款金额
	
	private String loanOrderNo;//贷款单号
	
	private String titanCode;

	public String getTitanCode() {
		return titanCode;
	}

	public void setTitanCode(String titanCode) {
		this.titanCode = titanCode;
	}

	public String getHotleName() {
		return hotleName;
	}

	public void setHotleName(String hotleName) {
		this.hotleName = hotleName;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getRoomNights() {
		return roomNights;
	}

	public void setRoomNights(int roomNights) {
		this.roomNights = roomNights;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getContractUrl() {
		return contractUrl;
	}

	public void setContractUrl(String contractUrl) {
		this.contractUrl = contractUrl;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getLoanOrderNo() {
		return loanOrderNo;
	}

	public void setLoanOrderNo(String loanOrderNo) {
		this.loanOrderNo = loanOrderNo;
	}
	
}
