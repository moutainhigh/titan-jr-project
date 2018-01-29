package com.titanjr.fop.response;

import java.util.List;

import com.titanjr.fop.dto.OpenAccountPerson;

public class WheatfieldBatchqueryPersonResponse extends FopResponse{
	private String is_success = "false";
	private List<OpenAccountPerson> openaccountpersons;
	
	public String getIs_success() {
		return is_success;
	}
	public void setIs_success(String is_success) {
		this.is_success = is_success;
	}
	public List<OpenAccountPerson> getOpenaccountpersons() {
		return openaccountpersons;
	}
	public void setOpenaccountpersons(List<OpenAccountPerson> openaccountpersons) {
		this.openaccountpersons = openaccountpersons;
	}
}
