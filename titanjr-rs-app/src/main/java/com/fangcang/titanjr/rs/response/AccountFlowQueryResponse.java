package com.fangcang.titanjr.rs.response;

import java.util.List;

import com.Rop.api.domain.Finanaceentry;
import com.fangcang.titanjr.rs.dto.AccountFlow;

/**
 * Created by zhaoshan on 2016/4/15.
 */
public class AccountFlowQueryResponse extends BaseResponse{
	
	private List<AccountFlow> accountFlow;

	public List<AccountFlow> getAccountFlow() {
		return accountFlow;
	}

	public void setAccountFlow(List<AccountFlow> accountFlow) {
		this.accountFlow = accountFlow;
	}

	
	
}
