package com.fangcang.titanjr.rs.response;

import java.util.List;

import com.fangcang.titanjr.rs.dto.AccountFlow;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XStreamAlias("wheatfield_finanace_entrylist_query_response")
public class AccountFlowResponse {
	
	private String is_success;
	
	@XStreamAlias("finanaceEntrys")
	private AccountFlowEntry accountFlowEntry;

	public String getIs_success() {
		return is_success;
	}

	public void setIs_success(String is_success) {
		this.is_success = is_success;
	}

	public AccountFlowEntry getAccountFlowEntry() {
		return accountFlowEntry;
	}

	public void setAccountFlowEntry(AccountFlowEntry accountFlowEntry) {
		this.accountFlowEntry = accountFlowEntry;
	}


}
