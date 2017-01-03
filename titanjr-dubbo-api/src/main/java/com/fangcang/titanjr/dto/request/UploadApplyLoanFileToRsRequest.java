package com.fangcang.titanjr.dto.request;

import com.fangcang.titanjr.dto.BaseRequestDTO;

/***
 * 上传贷款文件到融数
 * @author luoqinglong
 * @date   2017年1月3日
 */
public class UploadApplyLoanFileToRsRequest extends BaseRequestDTO {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String orgCode;
	private String loanApplyOrderNo;
	/**
	 * 贷款类型：1-企业，2-个人
	 */
	private Integer Loantype;
	/***
	 * 合同附件,格式：111.jpg,2222.jpg
	 */
	private String contactNames;
	
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	public String getLoanApplyOrderNo() {
		return loanApplyOrderNo;
	}
	public void setLoanApplyOrderNo(String loanApplyOrderNo) {
		this.loanApplyOrderNo = loanApplyOrderNo;
	}
	public Integer getLoantype() {
		return Loantype;
	}
	public void setLoantype(Integer loantype) {
		Loantype = loantype;
	}
	public String getContactNames() {
		return contactNames;
	}
	public void setContactNames(String contactNames) {
		this.contactNames = contactNames;
	}
	
}
