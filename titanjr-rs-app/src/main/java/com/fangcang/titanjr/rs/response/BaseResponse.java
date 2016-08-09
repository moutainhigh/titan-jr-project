package com.fangcang.titanjr.rs.response;

import java.io.Serializable;

/**
 * Created by zhaoshan on 2016/4/8.
 */
public class BaseResponse implements Serializable {

    private boolean isSuccess = false;
    private String returnCode;
    private String returnMsg;
	private String operateStatus;

	public String getOperateStatus() {
		return operateStatus;
	}

	public void setOperateStatus(String operateStatus) {
		this.operateStatus = operateStatus;
	}
	

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getReturnMsg() {
        return returnMsg;
    }

    public void setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg;
    }
    
    
}
