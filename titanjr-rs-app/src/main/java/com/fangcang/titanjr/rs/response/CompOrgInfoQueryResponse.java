package com.fangcang.titanjr.rs.response;


import java.util.List;

import com.titanjr.fop.dto.OpenAccountCompany;

/**
 * Created by zhaoshan on 2016/4/13.
 */
public class CompOrgInfoQueryResponse extends BaseResponse {
    
    private List<OpenAccountCompany> companyOrgList;

	public List<OpenAccountCompany> getCompanyOrgList() {
		return companyOrgList;
	}

	public void setCompanyOrgList(List<OpenAccountCompany> companyOrgList) {
		this.companyOrgList = companyOrgList;
	}
    
    

}
