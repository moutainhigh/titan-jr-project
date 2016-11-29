<<<<<<< HEAD:titanjr-rs-app/src/main/java/com/fangcang/titanjr/rs/dto/ControllData.java
package com.fangcang.titanjr.rs.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by zhaoshan on 2016/10/9.
 * 股东信息列表
 */
public class ControllData implements Serializable {
	
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

=======
package test.fangcang.titanjr.rs.invoker.loancredit;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by zhaoshan on 2016/10/9.
 * 股东信息列表
 */
public class ControllData implements Serializable {
	
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

>>>>>>> dev:titanjr-rs-app/src/test/java/test/fangcang/titanjr/rs/invoker/loancredit/ControllData.java
