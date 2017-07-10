package com.fangcang.titanjr.dto.bean;

import java.io.Serializable;

public class AccountBalance implements Serializable{
	//账户心跳计次
    private String pulsedegree;
    //可用余额（元）
    private String balanceusable;
    //贷记余额
    private String balancecredit;
    //提现余额
    private String balancesettle;
    //账户心跳时间(毫秒)
    private String pulsetime;
    //冻结余额
    private String balancefrozon;
    //账户ID
    private String finaccountid;
    //可信用消费余额
    private String balanceoverlimit;
    //账户余额(分)
    private String amount;
    //产品号
    private String productid;
    
    //用户Id
    private String userid;

	public String getPulsedegree() {
		return pulsedegree;
	}

	public void setPulsedegree(String pulsedegree) {
		this.pulsedegree = pulsedegree;
	}

	public String getBalanceusable() {
		return balanceusable;
	}

	public void setBalanceusable(String balanceusable) {
		this.balanceusable = balanceusable;
	}

	public String getBalancecredit() {
		return balancecredit;
	}

	public void setBalancecredit(String balancecredit) {
		this.balancecredit = balancecredit;
	}

	public String getBalancesettle() {
		return balancesettle;
	}

	public void setBalancesettle(String balancesettle) {
		this.balancesettle = balancesettle;
	}

	public String getPulsetime() {
		return pulsetime;
	}

	public void setPulsetime(String pulsetime) {
		this.pulsetime = pulsetime;
	}

	public String getBalancefrozon() {
		return balancefrozon;
	}

	public void setBalancefrozon(String balancefrozon) {
		this.balancefrozon = balancefrozon;
	}

	public String getFinaccountid() {
		return finaccountid;
	}

	public void setFinaccountid(String finaccountid) {
		this.finaccountid = finaccountid;
	}

	public String getBalanceoverlimit() {
		return balanceoverlimit;
	}

	public void setBalanceoverlimit(String balanceoverlimit) {
		this.balanceoverlimit = balanceoverlimit;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getProductid() {
		return productid;
	}

	public void setProductid(String productid) {
		this.productid = productid;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}
    
}
