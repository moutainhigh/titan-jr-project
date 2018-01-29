package com.titanjr.fop.response;

import java.util.List;

import com.titanjr.fop.dto.OpenAccountCompany;

public class WheatfieldBatchqueryCompanyResponse extends FopResponse{
	private String is_success = "false";
	private List<OpenAccountCompany> openaccountcompanys;
	
	public String getIs_success() {
		return is_success;
	}
	public void setIs_success(String is_success) {
		this.is_success = is_success;
	}
	public List<OpenAccountCompany> getOpenaccountcompanys() {
		return openaccountcompanys;
	}
	public void setOpenaccountcompanys(List<OpenAccountCompany> openaccountcompanys) {
		this.openaccountcompanys = openaccountcompanys;
	}
	
}
