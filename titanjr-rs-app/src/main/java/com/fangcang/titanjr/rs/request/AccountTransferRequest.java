package com.fangcang.titanjr.rs.request;

import com.fangcang.titanjr.common.exception.RSValidateException;
import com.fangcang.titanjr.common.util.RequestValidationUtil;

/**
 * Created by zhaoshan on 2016/4/15.
 */
public class AccountTransferRequest extends BaseRequest{
    //----------------------必须--------------
	//转账指示，请联系供应商获取
	private String transfertype;
	//商户指示，请联系供应商获取
	private String conditioncode;
	//	M000001		发生方商户码
	private String merchantcode;
	//请求号
	private String requestno;
	//请求时间 请按格式填写（yyyy-MM-dd HH:mm:ss)
	private String requesttime;
	//金额
	private String amount;
	//手续费
	private String userfee;
	//接收方机构码
	private String intermerchantcode;
	//接收方产品号
	private String interproductid;
	//接收方用户id
	private String userrelateid;
	
	
	@Override
	public void check() throws RSValidateException {
		//校验不能为空
//        RequestValidationUtil.checkNotEmpty(this.getConstid(), "constid");
        RequestValidationUtil.checkNotEmpty(this.getUserid(), "userid");
        RequestValidationUtil.checkNotEmpty(this.getProductid(), "productid");
        RequestValidationUtil.checkNotEmpty(this.getRequestno(), "requestno");
        RequestValidationUtil.checkNotEmpty(this.getMerchantcode(), "merchantcode");
        
        RequestValidationUtil.checkNotEmpty(this.getTransfertype(), "transfertype");
        RequestValidationUtil.checkNotEmpty(this.getConditioncode(), "conditioncode");
        RequestValidationUtil.checkNotEmpty(this.getRequesttime(), "requesttime");
        RequestValidationUtil.checkNotEmpty(this.getAmount(), "amount");
        RequestValidationUtil.checkNotEmpty(this.getUserfee(), "userfee");
        RequestValidationUtil.checkNotEmpty(this.getIntermerchantcode(), "intermerchantcode");
        RequestValidationUtil.checkNotEmpty(this.getInterproductid(), "interproductid");
        RequestValidationUtil.checkNotEmpty(this.getUserrelateid(), "userrelateid");
        
        
        
		
	}


	public String getTransfertype() {
		return transfertype;
	}


	public void setTransfertype(String transfertype) {
		this.transfertype = transfertype;
	}


	public String getConditioncode() {
		return conditioncode;
	}


	public void setConditioncode(String conditioncode) {
		this.conditioncode = conditioncode;
	}


	public String getMerchantcode() {
		return merchantcode;
	}


	public void setMerchantcode(String merchantcode) {
		this.merchantcode = merchantcode;
	}


	public String getRequestno() {
		return requestno;
	}


	public void setRequestno(String requestno) {
		this.requestno = requestno;
	}


	public String getRequesttime() {
		return requesttime;
	}


	public void setRequesttime(String requesttime) {
		this.requesttime = requesttime;
	}


	public String getAmount() {
		return amount;
	}


	public void setAmount(String amount) {
		this.amount = amount;
	}


	public String getUserfee() {
		return userfee;
	}


	public void setUserfee(String userfee) {
		this.userfee = userfee;
	}


	public String getIntermerchantcode() {
		return intermerchantcode;
	}


	public void setIntermerchantcode(String intermerchantcode) {
		this.intermerchantcode = intermerchantcode;
	}


	public String getInterproductid() {
		return interproductid;
	}


	public void setInterproductid(String interproductid) {
		this.interproductid = interproductid;
	}


	public String getUserrelateid() {
		return userrelateid;
	}


	public void setUserrelateid(String userrelateid) {
		this.userrelateid = userrelateid;
	}
	
	
}
