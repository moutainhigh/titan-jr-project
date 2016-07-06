package com.fangcang.titanjr.entity;
// default package

import java.util.Date;

/**
 * TitanTransferReq entity. @author MyEclipse Persistence Tools
 */

public class TitanTransferReq implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -2560833954181492525L;
	private Integer transferreqid;
	private Integer transorderid;
	private Integer transfertype;
	private Integer conditioncode;
	private String merchantcode;
	private String userid;
	private String productid;
	private String intermerchantcode;
	private String userrelateid;
	private String interproductid;
	private Double amount;
	private Double userfee;
	private String requestno;
	private Date requesttime;
	private String useripaddress;
	private String remark;
	private Integer status;
	private String creator;
	private Date createtime;
	private String payorderno;

	// Constructors

	/** default constructor */
	public TitanTransferReq() {
	}

	/** minimal constructor */
	public TitanTransferReq(Integer transorderid) {
		this.transorderid = transorderid;
	}

	/** full constructor */
	public TitanTransferReq(Integer transorderid, Integer transfertype,
			Integer conditioncode, String merchantcode, String userid,
			String productid, String intermerchantcode, String userrelateid,
			String interproductid, Double amount, Double userfee,
			String requestno, Date requesttime, String useripaddress,
			String remark, Integer status, String creator, Date createtime,String payOrderNo) {
		this.transorderid = transorderid;
		this.transfertype = transfertype;
		this.conditioncode = conditioncode;
		this.merchantcode = merchantcode;
		this.userid = userid;
		this.productid = productid;
		this.intermerchantcode = intermerchantcode;
		this.userrelateid = userrelateid;
		this.interproductid = interproductid;
		this.amount = amount;
		this.userfee = userfee;
		this.requestno = requestno;
		this.requesttime = requesttime;
		this.useripaddress = useripaddress;
		this.remark = remark;
		this.status = status;
		this.creator = creator;
		this.createtime = createtime;
		this.payorderno=payorderno;
	}

	// Property accessors

	public Integer getTransferreqid() {
		return this.transferreqid;
	}

	public void setTransferreqid(Integer transferreqid) {
		this.transferreqid = transferreqid;
	}

	public Integer getTransorderid() {
		return this.transorderid;
	}

	public void setTransorderid(Integer transorderid) {
		this.transorderid = transorderid;
	}

	public Integer getTransfertype() {
		return this.transfertype;
	}

	public void setTransfertype(Integer transfertype) {
		this.transfertype = transfertype;
	}

	public Integer getConditioncode() {
		return this.conditioncode;
	}

	public void setConditioncode(Integer conditioncode) {
		this.conditioncode = conditioncode;
	}

	public String getMerchantcode() {
		return this.merchantcode;
	}

	public void setMerchantcode(String merchantcode) {
		this.merchantcode = merchantcode;
	}

	public String getUserid() {
		return this.userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getProductid() {
		return this.productid;
	}

	public void setProductid(String productid) {
		this.productid = productid;
	}

	public String getIntermerchantcode() {
		return this.intermerchantcode;
	}

	public void setIntermerchantcode(String intermerchantcode) {
		this.intermerchantcode = intermerchantcode;
	}

	public String getUserrelateid() {
		return this.userrelateid;
	}

	public void setUserrelateid(String userrelateid) {
		this.userrelateid = userrelateid;
	}

	public String getInterproductid() {
		return this.interproductid;
	}

	public void setInterproductid(String interproductid) {
		this.interproductid = interproductid;
	}

	public Double getAmount() {
		return this.amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getUserfee() {
		return this.userfee;
	}

	public void setUserfee(Double userfee) {
		this.userfee = userfee;
	}

	public String getRequestno() {
		return this.requestno;
	}

	public void setRequestno(String requestno) {
		this.requestno = requestno;
	}

	public Date getRequesttime() {
		return this.requesttime;
	}

	public void setRequesttime(Date requesttime) {
		this.requesttime = requesttime;
	}

	public String getUseripaddress() {
		return this.useripaddress;
	}

	public void setUseripaddress(String useripaddress) {
		this.useripaddress = useripaddress;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getCreator() {
		return this.creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Date getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getPayorderno() {
		return payorderno;
	}

	public void setPayorderno(String payorderno) {
		this.payorderno = payorderno;
	}

}