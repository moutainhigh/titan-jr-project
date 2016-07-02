package com.fangcang.titanjr.dto.request;

import com.fangcang.titanjr.dto.BaseRequestDTO;

import javax.validation.constraints.NotNull;

/**
 * Created by zhaoshan on 2016/5/9.
 */
public class FinancialUserBindRequest extends BaseRequestDTO{

    /**
	 * 
	 */
	private static final long serialVersionUID = 5868513930101928098L;
	@NotNull
    private Integer userid;
    @NotNull
    private Integer fcuserid;
    @NotNull
    private String loginUserName;
    @NotNull
    private String fcLoginUserName;
    @NotNull
    private String merchantCode;

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getLoginUserName() {
        return loginUserName;
    }

    public void setLoginUserName(String loginUserName) {
        this.loginUserName = loginUserName;
    }

    public Integer getFcuserid() {
		return fcuserid;
	}

	public void setFcuserid(Integer fcuserid) {
		this.fcuserid = fcuserid;
	}

	public String getFcLoginUserName() {
        return fcLoginUserName;
    }

    public void setFcLoginUserName(String fcLoginUserName) {
        this.fcLoginUserName = fcLoginUserName;
    }

    public String getMerchantCode() {
        return merchantCode;
    }

    public void setMerchantCode(String merchantCode) {
        this.merchantCode = merchantCode;
    }
}
