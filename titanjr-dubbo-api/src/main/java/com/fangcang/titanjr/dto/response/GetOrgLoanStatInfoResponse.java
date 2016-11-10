package com.fangcang.titanjr.dto.response;

import com.fangcang.titanjr.dto.BaseRequestDTO;
import com.fangcang.titanjr.dto.bean.OrgLoanStatInfo;

public class GetOrgLoanStatInfoResponse extends BaseRequestDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private OrgLoanStatInfo orgLoanStatInfo;

	public OrgLoanStatInfo getOrgLoanStatInfo() {
		return orgLoanStatInfo;
	}

	public void setOrgLoanStatInfo(OrgLoanStatInfo orgLoanStatInfo) {
		this.orgLoanStatInfo = orgLoanStatInfo;
	}
}
