package com.fangcang.titanjr.dto.bean;


import java.io.Serializable;
import java.util.Date;

/**
 * 审核状态返回
 * 现在分5中状态
 *  OrgCheckResultEnum
 * Created by zhaoshan on 2016/4/25.
 */
public class CheckStatus implements Serializable{

    private String checkResultKey;
    private String checkResultMsg;
    private String checkUser;
    private Date checkTime;

    public String getCheckResultKey() {
        return checkResultKey;
    }

    public void setCheckResultKey(String checkResultKey) {
        this.checkResultKey = checkResultKey;
    }

    public String getCheckResultMsg() {
        return checkResultMsg;
    }

    public void setCheckResultMsg(String checkResultMsg) {
        this.checkResultMsg = checkResultMsg;
    }

    public String getCheckUser() {
        return checkUser;
    }

    public void setCheckUser(String checkUser) {
        this.checkUser = checkUser;
    }

    public Date getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(Date checkTime) {
        this.checkTime = checkTime;
    }
}
