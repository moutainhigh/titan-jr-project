package com.titanjr.fop.dto;

import javax.validation.constraints.NotNull;

/**
 * 查询账户信息
 * Created by zhaoshan on 2018/1/4.
 */
public class BalanceQueryDTO {

    private String userId;

    private String constId;

    private String productId;

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

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }
}
