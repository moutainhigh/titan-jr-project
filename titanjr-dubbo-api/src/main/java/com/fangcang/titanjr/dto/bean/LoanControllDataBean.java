package com.fangcang.titanjr.dto.bean;

import java.io.Serializable;

/**
 * Created by zhaoshan on 2016/10/9.
 * 股东信息列表
 */
public class LoanControllDataBean implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 8159258875399514489L;
	//股东名称
    private String shareholderName;
    //出资金额
    private String contributionAmount;
    //股权占比
    private String equityRatio;

    
    public String getShareholderName() {
		return shareholderName;
	}

	public void setShareholderName(String shareholderName) {
		this.shareholderName = shareholderName;
	}

    public String getContributionAmount() {
		return contributionAmount;
	}

	public void setContributionAmount(String contributionAmount) {
		this.contributionAmount = contributionAmount;
	}

	public String getEquityRatio() {
        return equityRatio;
    }

    public void setEquityRatio(String equityRatio) {
        this.equityRatio = equityRatio;
    }
}

