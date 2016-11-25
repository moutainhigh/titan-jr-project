package com.fangcang.titanjr.dto.response;

import com.fangcang.titanjr.dto.BaseResponseDTO;
import com.fangcang.titanjr.dto.bean.LoanCompanyAppendInfo;
import com.fangcang.titanjr.dto.bean.LoanCompanyEnsureBean;
import com.fangcang.titanjr.dto.bean.LoanCreditCompanyBean;
import com.fangcang.titanjr.dto.bean.LoanCreditOrderBean;
import com.fangcang.titanjr.dto.bean.LoanPersonEnsureBean;

/**
 * 授信数据保存请求
 * 
 * @author wengxitao
 *
 */
public class GetCreditInfoResponse extends BaseResponseDTO {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 授信单号
	private String orderNo;

	// 授信单基本信息
	private LoanCreditOrderBean creditOrder;

	// 企业担保信息
	private LoanCompanyEnsureBean companyEnsure;

	private LoanCompanyAppendInfo companyAppendInfo;

	// 授信企业信息
	private LoanCreditCompanyBean creditCompany;

	// 个人担保信息
	private LoanPersonEnsureBean loanPersonEnsure;

	public LoanCompanyAppendInfo getCompanyAppendInfo() {
		return companyAppendInfo;
	}

	public void setCompanyAppendInfo(LoanCompanyAppendInfo companyAppendInfo) {
		this.companyAppendInfo = companyAppendInfo;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public LoanCreditOrderBean getCreditOrder() {
		return creditOrder;
	}

	public void setCreditOrder(LoanCreditOrderBean creditOrder) {
		this.creditOrder = creditOrder;
	}

	public LoanCompanyEnsureBean getCompanyEnsure() {
		return companyEnsure;
	}

	public void setCompanyEnsure(LoanCompanyEnsureBean companyEnsure) {
		this.companyEnsure = companyEnsure;
	}

	public LoanCreditCompanyBean getCreditCompany() {
		return creditCompany;
	}

	public void setCreditCompany(LoanCreditCompanyBean creditCompany) {
		this.creditCompany = creditCompany;
	}

	public LoanPersonEnsureBean getLoanPersonEnsure() {
		return loanPersonEnsure;
	}

	public void setLoanPersonEnsure(LoanPersonEnsureBean loanPersonEnsure) {
		this.loanPersonEnsure = loanPersonEnsure;
	}

}
