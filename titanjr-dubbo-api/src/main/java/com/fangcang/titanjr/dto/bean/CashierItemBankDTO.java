package com.fangcang.titanjr.dto.bean;

import java.io.Serializable;

/**
 * 收银台细项对应的银行列表功能
 * Created by zhaoshan on 2016/5/18.
 */
public class CashierItemBankDTO implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 2906389436247051578L;

	private String bankMark;

    private String bankName;

    private String bankImage;
    
    /**
     * 该银行支持卡类型  1储蓄卡  2信用卡  3两者都支持
     */
    private String supportType;

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

	public String getSupportType() {
		return supportType;
	}

	public void setSupportType(String supportType) {
		this.supportType = supportType;
	}
}
