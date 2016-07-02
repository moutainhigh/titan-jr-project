package com.fangcang.titanjr.rs.response;

import java.util.List;

import com.fangcang.titanjr.rs.dto.AccountFlow;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
@XStreamAlias("finanaceEntrys")
public class AccountFlowEntry {
	
	@XStreamImplicit(itemFieldName="finanaceentry")
	private List<AccountFlow> accountFlow;

	public List<AccountFlow> getAccountFlow() {
		return accountFlow;
	}

	public void setAccountFlow(List<AccountFlow> accountFlow) {
		this.accountFlow = accountFlow;
	}
	
	
}
