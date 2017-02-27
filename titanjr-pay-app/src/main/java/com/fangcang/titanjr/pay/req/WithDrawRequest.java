package com.fangcang.titanjr.pay.req;

/**
 * 账户提现请求参数
 * Created by zhaoshan on 2016/6/15.
 */
public class WithDrawRequest 
{
	private String userId;
	@Deprecated
	private String fcUserId;
	private String tfsUserId;
	private String orderNo;
	private Integer useNewBankCard;
    private Integer hasBindBanCard;
    private String bankCode;
    private String bankName;
    private String accountNum;
    private String accountName;
    private String password;
    private String originalAccount;
    private String originalBankName;
    private String amount;
    private String branchCode;
    private String cityCode;
    private String cityName;
    
    public String getUserId() {
  		return userId;
  	}

  	public void setUserId(String userId) {
  		this.userId = userId;
  	}

  	public String getFcUserId() {
  		return fcUserId;
  	}

  	public void setFcUserId(String fcUserId) {
  		this.fcUserId = fcUserId;
  	}

  	public String getOrderNo() {
  		return orderNo;
  	}

  	public void setOrderNo(String orderNo) {
  		this.orderNo = orderNo;
  	}

    public Integer getUseNewBankCard() {
        return useNewBankCard;
    }

    public void setUseNewBankCard(Integer useNewBankCard) {
        this.useNewBankCard = useNewBankCard;
    }

    public Integer getHasBindBanCard() {
        return hasBindBanCard;
    }

    public void setHasBindBanCard(Integer hasBindBanCard) {
        this.hasBindBanCard = hasBindBanCard;
    }

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

    public String getAccountNum() {
        return accountNum;
    }

    public void setAccountNum(String accountNum) {
        this.accountNum = accountNum;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOriginalAccount() {
        return originalAccount;
    }

    public void setOriginalAccount(String originalAccount) {
        this.originalAccount = originalAccount;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

	public String getOriginalBankName() {
		return originalBankName;
	}

	public void setOriginalBankName(String originalBankName) {
		this.originalBankName = originalBankName;
	}


	public String getTfsUserId() {
		return tfsUserId;
	}

	public void setTfsUserId(String tfsUserId) {
		this.tfsUserId = tfsUserId;
	}
	public String getBranchCode() {
		return branchCode;
	}

	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;

	}
    
}
