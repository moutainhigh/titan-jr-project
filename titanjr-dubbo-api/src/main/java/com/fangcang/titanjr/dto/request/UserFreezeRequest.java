package com.fangcang.titanjr.dto.request;

import javax.validation.constraints.NotNull;

import com.fangcang.titanjr.dto.BaseRequestDTO;

public class UserFreezeRequest extends BaseRequestDTO{
	
	private static final long serialVersionUID = 5954021841778680229L;
	@NotNull
	private Integer tfsUserId;
	
	//操作类型 1.解冻，2.冻结
    @NotNull
    private Integer status;

	public Integer getTfsUserId() {
		return tfsUserId;
	}

	public void setTfsUserId(Integer tfsUserId) {
		this.tfsUserId = tfsUserId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
    
}
