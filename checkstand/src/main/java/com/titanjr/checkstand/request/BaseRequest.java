package com.titanjr.checkstand.request;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Created by zhaoshan on 2017/11/15.
 */
public abstract class BaseRequest {
	
	public final Logger logger = LoggerFactory.getLogger(BaseRequest.class);

    private String operator;
    private Date operateTime;

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
}
