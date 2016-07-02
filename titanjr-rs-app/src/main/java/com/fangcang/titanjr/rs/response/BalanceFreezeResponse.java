package com.fangcang.titanjr.rs.response;

/**
 * Created by zhaoshan on 2016/4/13.
 */
public class BalanceFreezeResponse extends BaseResponse {

    private String authcode;

    private String operateStatus;
    
    public String getAuthcode() {
        return authcode;
    }

    public void setAuthcode(String authcode) {
        this.authcode = authcode;
    }

	public String getOperateStatus() {
		return operateStatus;
	}

	public void setOperateStatus(String operateStatus) {
		this.operateStatus = operateStatus;
	}
    
    
}
