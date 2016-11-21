package com.fangcang.titanjr.dto.response;

import com.fangcang.titanjr.dto.BaseRequestDTO;
import com.fangcang.titanjr.dto.BaseResponseDTO;
import com.fangcang.titanjr.dto.bean.LoanCompanyEnsure;
import com.fangcang.titanjr.dto.bean.LoanCreditCompany;
import com.fangcang.titanjr.dto.bean.LoanCreditOrder;
import com.fangcang.titanjr.dto.bean.LoanPersonEnsure;

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
	private LoanCreditOrder creditOrder;

	// 企业担保信息
	private LoanCompanyEnsure companyEnsure;

	// 授信企业信息
	private LoanCreditCompany creditCompany;

	// 个人担保信息
	private LoanPersonEnsure loanPersonEnsure;

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public LoanCreditOrder getCreditOrder() {
		return creditOrder;
	}

	public void setCreditOrder(LoanCreditOrder creditOrder) {
		this.creditOrder = creditOrder;
	}

	public LoanCompanyEnsure getCompanyEnsure() {
		return companyEnsure;
	}

	public void setCompanyEnsure(LoanCompanyEnsure companyEnsure) {
		this.companyEnsure = companyEnsure;
	}

	public LoanCreditCompany getCreditCompany() {
		return creditCompany;
	}

	public void setCreditCompany(LoanCreditCompany creditCompany) {
		this.creditCompany = creditCompany;
	}

	public LoanPersonEnsure getLoanPersonEnsure() {
		return loanPersonEnsure;
	}

	public void setLoanPersonEnsure(LoanPersonEnsure loanPersonEnsure) {
		this.loanPersonEnsure = loanPersonEnsure;
	}
	
}
