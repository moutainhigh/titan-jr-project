package com.titanjr.checkstand.request;

import java.util.Date;

/**
 * Created by zhaoshan on 2017/11/15.
 */
public abstract class BaseRequest {

    private String operator;
    private Date operateTime;

    public abstract boolean validate();

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
