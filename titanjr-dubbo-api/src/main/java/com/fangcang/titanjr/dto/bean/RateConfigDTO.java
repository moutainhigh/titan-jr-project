package com.fangcang.titanjr.dto.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by zhaoshan on 2016/5/18.
 */
public class RateConfigDTO  implements Serializable {
    private Integer rateConfigId;
    private String userId;
    private Integer cashierItemId;
    private Integer busType;
    private Integer rateType;
    private Float rsRate;
    private Float standRate;
    private Float executionRate;
    private String description;
    private Date expiration;

    public Integer getRateConfigId() {
        return rateConfigId;
    }

    public void setRateConfigId(Integer rateConfigId) {
        this.rateConfigId = rateConfigId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getCashierItemId() {
        return cashierItemId;
    }

    public void setCashierItemId(Integer cashierItemId) {
        this.cashierItemId = cashierItemId;
    }

    public Integer getBusType() {
        return busType;
    }

    public void setBusType(Integer busType) {
        this.busType = busType;
    }

    public Integer getRateType() {
        return rateType;
    }

    public void setRateType(Integer rateType) {
        this.rateType = rateType;
    }

    public Float getRsRate() {
        return rsRate;
    }

    public void setRsRate(Float rsRate) {
        this.rsRate = rsRate;
    }

    public Float getStandRate() {
        return standRate;
    }

    public void setStandRate(Float standRate) {
        this.standRate = standRate;
    }

    public Float getExecutionRate() {
        return executionRate;
    }

    public void setExecutionRate(Float executionRate) {
        this.executionRate = executionRate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getExpiration() {
        return expiration;
    }

    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }
}
