package com.fangcang.titanjr.dto.bean;

import java.io.Serializable;

/**
 * 收银台细项对应的银行列表功能
 * Created by zhaoshan on 2016/5/18.
 */
public class CashierItemBankDTO implements Serializable {

    private String bankMark;

    private String bankName;

    private String bankImage;

    public String getBankMark() {
        return bankMark;
    }

    public void setBankMark(String bankMark) {
        this.bankMark = bankMark;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankImage() {
        return bankImage;
    }

    public void setBankImage(String bankImage) {
        this.bankImage = bankImage;
    }
}
