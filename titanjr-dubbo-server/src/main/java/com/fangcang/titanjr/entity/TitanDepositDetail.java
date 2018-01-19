package com.fangcang.titanjr.entity;

import java.io.Serializable;
/**
 * 备付金资金变动
 * @author luoqinglong
 * @date 2017年12月27日
 */
import java.util.Date;
public class TitanDepositDetail implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5632723516645135598L;
	private String depositdetailid;
	private String accountcode;
	private Long transorderid;
	private Integer tradetype;
	private Long fee;
	private Long amount;
	private Integer status;
	private Date createtime;
	private Date updatetime;
	
	public String getDepositdetailid() {
		return depositdetailid;
	}
	public void setDepositdetailid(String depositdetailid) {
		this.depositdetailid = depositdetailid;
	}
	public String getAccountcode() {
		return accountcode;
	}
	public void setAccountcode(String accountcode) {
		this.accountcode = accountcode;
	}
	public Long getTransorderid() {
		return transorderid;
	}
	public void setTransorderid(Long transorderid) {
		this.transorderid = transorderid;
	}
	public Integer getTradetype() {
		return tradetype;
	}
	public void setTradetype(Integer tradetype) {
		this.tradetype = tradetype;
	}
	public Long getFee() {
		return fee;
	}
	public void setFee(Long fee) {
		this.fee = fee;
	}
	public Long getAmount() {
		return amount;
	}
	public void setAmount(Long amount) {
		this.amount = amount;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public Date getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}
	
	
}
