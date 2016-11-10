package com.fangcang.titanjr.dto.request;

import com.fangcang.titanjr.common.enums.LoanProductEnum;
import com.fangcang.titanjr.dto.BaseRequestDTO;
import com.fangcang.titanjr.dto.bean.LoanSpec;

public class ApplyLoanRequest extends BaseRequestDTO {
	
	private static final long serialVersionUID = 1L;

	//产品类型
	private LoanProductEnum productType;
	//金服机构编码
	private String orgCode;
	//贷款规格
	private LoanSpec lcanSpec;

	public LoanProductEnum getProductType() {
		return productType;
	}

	public void setProductType(LoanProductEnum productType) {
		this.productType = productType;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public LoanSpec getLcanSpec() {
		return lcanSpec;
	}

	public void setLcanSpec(LoanSpec lcanSpec) {
		this.lcanSpec = lcanSpec;
	}
}
