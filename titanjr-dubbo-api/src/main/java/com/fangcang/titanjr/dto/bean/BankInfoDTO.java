package com.fangcang.titanjr.dto.bean;

import java.io.Serializable;

/**
 * Created by zhaoshan on 2016/6/14.
 */
public class BankInfoDTO implements Serializable {

    private String bankCode;
    private String bankName;
    private String bankCity;
    private String updateBatch;
    private Integer bankType;
    private Integer statusId;
    private String parentCode;

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankCity() {
        return bankCity;
    }

    public void setBankCity(String bankCity) {
        this.bankCity = bankCity;
    }

    public String getUpdateBatch() {
        return updateBatch;
    }

    public void setUpdateBatch(String updateBatch) {
        this.updateBatch = updateBatch;
    }

    public Integer getBankType() {
        return bankType;
    }

    public void setBankType(Integer bankType) {
        this.bankType = bankType;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }
}
