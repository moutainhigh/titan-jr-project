package com.titanjr.checkstand.request;

import java.util.Date;


/**
 * Created by zhaoshan on 2017/11/15.
 */
public abstract class BaseRequest {

    private String operator;
    private Date operateTime;
    
    private String requestType;//请求类型，内部使用，非渠道需要字段  @see RequestTypeEnum

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Date getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}
}
