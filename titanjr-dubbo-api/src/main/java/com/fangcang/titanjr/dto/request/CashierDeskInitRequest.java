package com.fangcang.titanjr.dto.request;

import com.fangcang.titanjr.dto.BaseRequestDTO;

/**
 * Created by zhaoshan on 2016/5/18.
 */
public class CashierDeskInitRequest extends BaseRequestDTO {

    private String userId;

    private String constId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getConstId() {
        return constId;
    }

    public void setConstId(String constId) {
        this.constId = constId;
    }
}
