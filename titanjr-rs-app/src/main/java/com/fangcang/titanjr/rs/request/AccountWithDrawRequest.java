package com.fangcang.titanjr.rs.request;

import com.fangcang.titanjr.common.exception.RSValidateException;
import com.fangcang.titanjr.common.util.RequestValidationUtil;

/**
 * Created by zhaoshan on 2016/4/15.
 */
public class AccountWithDrawRequest extends BaseRequest{
	//提现金额 单位为分 100分为1元
	private String amount;
	
	//账户系统给商户分配的机构码
	private String merchantcode;
	
	//订单日期  格式2015-06-09 23:59:59		日期+时间
	private String orderdate;
	
	//商户系统的订单id
	private String userorderid;
	
	
	//------------可选---------
	//100		手续费（分）
	private Long userfee;
	
	//银行卡号
	private String cardno;
	

	@Override
	public void check() throws RSValidateException {
		//校验不能为空
        RequestValidationUtil.checkNotEmpty(this.getMerchantcode(), "merchantcode");
        RequestValidationUtil.checkNotEmpty(this.getUserid(), "userid");
        RequestValidationUtil.checkNotEmpty(this.getProductid(), "productid");
        RequestValidationUtil.checkNotEmpty(this.getAmount(), "amount");
        RequestValidationUtil.checkNotEmpty(this.getOrderdate(), "orderdate");
        RequestValidationUtil.checkNotEmpty(this.getUserorderid(), "userorderid");
        
		
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getMerchantcode() {
		return merchantcode;
	}

	public void setMerchantcode(String merchantcode) {
		this.merchantcode = merchantcode;
	}

	public String getOrderdate() {
		return orderdate;
	}

	public void setOrderdate(String orderdate) {
		this.orderdate = orderdate;
	}

	public String getUserorderid() {
		return userorderid;
	}

	public void setUserorderid(String userorderid) {
		this.userorderid = userorderid;
	}

	public Long getUserfee() {
		return userfee;
	}

	public void setUserfee(Long userfee) {
		this.userfee = userfee;
	}

	public String getCardno() {
		return cardno;
	}

	public void setCardno(String cardno) {
		this.cardno = cardno;
	}
	
}
