package com.fangcang.titanjr.rs.request;

import java.util.Date;

import com.Rop.api.internal.util.RopHashMap;
import com.fangcang.titanjr.common.exception.RSValidateException;
import com.fangcang.titanjr.common.util.RequestValidationUtil;

public class OrderSaveWithCardRequest extends OrderOperateRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void check() throws RSValidateException {
		RequestValidationUtil.checkNotEmpty(this.getConstid(), "constid");
        RequestValidationUtil.checkNotEmpty(this.getUserid(), "userid");
        RequestValidationUtil.checkNotEmpty(this.getProductid(), "productid");
        RequestValidationUtil.checkNotEmpty(this.getOrdertypeid(), "ordertypeid");
        RequestValidationUtil.checkNotEmpty(this.getOrdertime(), "ordertime");
        RequestValidationUtil.checkNotEmpty(this.getUserorderid(), "userorderid");
        RequestValidationUtil.checkNotEmpty(this.getAmount(), "amount");
        RequestValidationUtil.checkNotEmpty(this.getAccountpurpose(), "accountpurpose");
    	RequestValidationUtil.checkNotEmpty(this.getAccountproperty(), "accountproperty");
    	RequestValidationUtil.checkNotEmpty(this.getCertificatenumber(), "certificatenumber");
    	RequestValidationUtil.checkNotEmpty(this.getCertificatetype(), "certificatetype");
    	RequestValidationUtil.checkNotEmpty(this.getAccountnumber(), "accountnumber");
    	RequestValidationUtil.checkNotEmpty(this.getAccounttypeid(), "accounttypeid");
    	RequestValidationUtil.checkNotEmpty(this.getBankheadname(), "bankheadname");
    	RequestValidationUtil.checkNotEmpty(this.getBankhead(), "bankhead");
    	RequestValidationUtil.checkNotEmpty(this.getAccountname(), "accountname");
    	RequestValidationUtil.checkNotEmpty(this.getCurrency(), "currency");
    	
	}
	//	账号类型 00银行卡，01存折，02信用卡。
	private String accounttypeid;
	
	//	账号名 银行卡或存折上的所有人姓名。
    private String accountname;
    
    //	币种（CNY）
    private String currency;
    
    //	卡号
    private String accountnumber;
    
    //账户目的(1:结算卡，2：其他卡, 3：提现卡,4:结算提现一体卡)
    private String accountpurpose;
    
    //	证件号
    private String certificatenumber;
    
    //	账户属性（1：对公，2：对私）
    private String accountproperty;
    
    //	银行总行联行号
    private String bankheadname;
    
    //开户证件类型0：身份证,1: 户口簿，2：护照,3.军官证,4.士兵证，5. 港澳居民来往内地通行证,6. 台湾同胞来往内地通行证,7. 临时身份证,8. 外国人居留证,9. 警官证, X.其他证件
    private String certificatetype;
    
    //	银行总行联行号
    private String bankhead;

	public String getAccounttypeid() {
		return accounttypeid;
	}
	public void setAccounttypeid(String accounttypeid) {
		this.accounttypeid = accounttypeid;
	}
	public String getAccountname() {
		return accountname;
	}
	public void setAccountname(String accountname) {
		this.accountname = accountname;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getAccountnumber() {
		return accountnumber;
	}
	public void setAccountnumber(String accountnumber) {
		this.accountnumber = accountnumber;
	}
	public String getAccountpurpose() {
		return accountpurpose;
	}
	public void setAccountpurpose(String accountpurpose) {
		this.accountpurpose = accountpurpose;
	}
	public String getCertificatenumber() {
		return certificatenumber;
	}
	public void setCertificatenumber(String certificatenumber) {
		this.certificatenumber = certificatenumber;
	}
	public String getAccountproperty() {
		return accountproperty;
	}
	public void setAccountproperty(String accountproperty) {
		this.accountproperty = accountproperty;
	}
	public String getBankheadname() {
		return bankheadname;
	}
	public void setBankheadname(String bankheadname) {
		this.bankheadname = bankheadname;
	}
	public String getCertificatetype() {
		return certificatetype;
	}
	public void setCertificatetype(String certificatetype) {
		this.certificatetype = certificatetype;
	}
	public String getBankhead() {
		return bankhead;
	}
	public void setBankhead(String bankhead) {
		this.bankhead = bankhead;
	}
	
}
