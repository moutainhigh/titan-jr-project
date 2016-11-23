package com.fangcang.titanjr.dto.request;

import com.fangcang.titanjr.dto.BaseRequestDTO;
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
public class LoanCreditSaveRequest extends BaseRequestDTO {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 组织编号
	private String orgCode;

	// 授信单基本信息
	private LoanCreditOrderBean creditOrder;

	// 企业担保信息
	private LoanCompanyEnsureBean companyEnsure;

	// 授信企业信息
	private LoanCreditCompanyBean creditCompany;

	// 个人担保信息
	private LoanPersonEnsureBean loanPersonEnsure;

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
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
