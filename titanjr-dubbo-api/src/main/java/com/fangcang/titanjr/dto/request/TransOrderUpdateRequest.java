package com.fangcang.titanjr.dto.request;

import com.fangcang.titanjr.dto.BaseRequestDTO;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by zhaoshan on 2016/6/13.
 */
public class TransOrderUpdateRequest extends BaseRequestDTO {

    @NotEmpty
    private String userId;
    @NotEmpty
    private String userOrderId;

    private String remark;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserOrderId() {
        return userOrderId;
    }

    public void setUserOrderId(String userOrderId) {
        this.userOrderId = userOrderId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
