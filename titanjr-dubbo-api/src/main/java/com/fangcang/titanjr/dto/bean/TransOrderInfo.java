package com.fangcang.titanjr.dto.bean;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

public class TransOrderInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
		//订单号
		private String orderno;
		
		//订单状态<br />0 失效<br />1 正常<br />2 对账成功<br />3 对账失败<br />4 付款成功<br />5 付款失败	
		private String orderstatus;
		
		//订单金额
		private String amount;
		
		//交易类型<br />4015 网关充值<br />4013 代收<br />4014 代付<br />10011 商户冲正<br />10012 清结算冲正 <br />3001 转账交易
		private String funccode;
		
		//交易请求号
		private String requestno;
		
		//订单包号或冲正时的原始订单号
		private String orderpackageno;
		
		//订单的创建时间
	    private String createdtime;
	    
	    //订单的更新时间	
	    private String updatedtime;
	    
	    //错误信息码	
	    private String errorcode;
	    
	    //机构码	
	    private String merchantcode;
	    
	    //每日交易汇总表id
	    private String transsumid;

		public String getOrderno() {
			return orderno;
		}

		public void setOrderno(String orderno) {
			this.orderno = orderno;
		}

		public String getOrderstatus() {
			return orderstatus;
		}

		public void setOrderstatus(String orderstatus) {
			this.orderstatus = orderstatus;
		}

		public String getAmount() {
			return amount;
		}

		public void setAmount(String amount) {
			this.amount = amount;
		}

		public String getFunccode() {
			return funccode;
		}

		public void setFunccode(String funccode) {
			this.funccode = funccode;
		}

		public String getRequestno() {
			return requestno;
		}

		public void setRequestno(String requestno) {
			this.requestno = requestno;
		}

		public String getOrderpackageno() {
			return orderpackageno;
		}

		public void setOrderpackageno(String orderpackageno) {
			this.orderpackageno = orderpackageno;
		}

		public String getCreatedtime() {
			return createdtime;
		}

		public void setCreatedtime(String createdtime) {
			this.createdtime = createdtime;
		}

		public String getUpdatedtime() {
			return updatedtime;
		}

		public void setUpdatedtime(String updatedtime) {
			this.updatedtime = updatedtime;
		}

		public String getErrorcode() {
			return errorcode;
		}

		public void setErrorcode(String errorcode) {
			this.errorcode = errorcode;
		}

		public String getMerchantcode() {
			return merchantcode;
		}

		public void setMerchantcode(String merchantcode) {
			this.merchantcode = merchantcode;
		}

		public String getTranssumid() {
			return transsumid;
		}

		public void setTranssumid(String transsumid) {
			this.transsumid = transsumid;
		}

}
