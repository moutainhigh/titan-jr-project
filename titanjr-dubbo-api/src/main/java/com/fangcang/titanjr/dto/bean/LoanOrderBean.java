package com.fangcang.titanjr.dto.bean;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

/**
 * 贷款订单
 * @author Administrator
 *
 */
public class LoanOrderBean implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	//序号
	private Integer id;
	
	//贷款单号
	private String orderNo;
	
	//授信单号
	private String creditOrderNo;
	
	//泰坦金融企业编号
	private String orgCode;
	
	//申请贷款金额
	private BigInteger amount;
	
	//实际贷款金额
	private BigInteger actualAmount;
	
	//贷款利率
	private float rate;
	
	//融数产品编号
	private String rsPId;
	
	//融数机构编号
	private String rsOrgId;
	
	//1 包房贷 2 运营贷
	private Integer productType;
	
	//产品编号
	private String productId;
	
	//创建日期
	private Date createtime;
	
	//费率模板
	private String ratetmp;
	
	//0 无效贷款  1 贷款申请中  2 待放款   3 已放款  4 放款失败  5 贷款失败  6 已逾期 7 已结清
	private Integer status;
	
	//贷款失败原因
	private String errormsg;
	
	//放款时间
	private Date relmoneytime;
	
	//产品规格 ID
	private String productspecId;
	
	//1 按日计利，随借随还
	private Integer repaymentType;
	
	//用户还款到期日
	private Date actualRepaymentDate;
	
	//最后一次还款时间
	private Date lastRepaymentDate;
	
	//已还本金
	private BigInteger repaymentPrincipal;
	
	//已还利息
	private BigInteger repaymentInterest;
	
	//应还本金
	private BigInteger shouldCapital;
	
	//应还利息
	private BigInteger shouldInterest;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getCreditOrderNo() {
		return creditOrderNo;
	}

	public void setCreditOrderNo(String creditOrderNo) {
		this.creditOrderNo = creditOrderNo;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public BigInteger getAmount() {
		return amount;
	}

	public void setAmount(BigInteger amount) {
		this.amount = amount;
	}

	public BigInteger getActualAmount() {
		return actualAmount;
	}

	public void setActualAmount(BigInteger actualAmount) {
		this.actualAmount = actualAmount;
	}

	public float getRate() {
		return rate;
	}

	public void setRate(float rate) {
		this.rate = rate;
	}

	public String getRsPId() {
		return rsPId;
	}

	public void setRsPId(String rsPId) {
		this.rsPId = rsPId;
	}

	public String getRsOrgId() {
		return rsOrgId;
	}

	public void setRsOrgId(String rsOrgId) {
		this.rsOrgId = rsOrgId;
	}

	public Integer getProductType() {
		return productType;
	}

	public void setProductType(Integer productType) {
		this.productType = productType;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getRatetmp() {
		return ratetmp;
	}

	public void setRatetmp(String ratetmp) {
		this.ratetmp = ratetmp;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getErrormsg() {
		return errormsg;
	}

	public void setErrormsg(String errormsg) {
		this.errormsg = errormsg;
	}

	public Date getRelmoneytime() {
		return relmoneytime;
	}

	public void setRelmoneytime(Date relmoneytime) {
		this.relmoneytime = relmoneytime;
	}

	public String getProductspecId() {
		return productspecId;
	}

	public void setProductspecId(String productspecId) {
		this.productspecId = productspecId;
	}

	public Integer getRepaymentType() {
		return repaymentType;
	}

	public void setRepaymentType(Integer repaymentType) {
		this.repaymentType = repaymentType;
	}

	public Date getActualRepaymentDate() {
		return actualRepaymentDate;
	}

	public void setActualRepaymentDate(Date actualRepaymentDate) {
		this.actualRepaymentDate = actualRepaymentDate;
	}

	public Date getLastRepaymentDate() {
		return lastRepaymentDate;
	}

	public void setLastRepaymentDate(Date lastRepaymentDate) {
		this.lastRepaymentDate = lastRepaymentDate;
	}

	public BigInteger getRepaymentPrincipal() {
		return repaymentPrincipal;
	}

	public void setRepaymentPrincipal(BigInteger repaymentPrincipal) {
		this.repaymentPrincipal = repaymentPrincipal;
	}

	public BigInteger getRepaymentInterest() {
		return repaymentInterest;
	}

	public void setRepaymentInterest(BigInteger repaymentInterest) {
		this.repaymentInterest = repaymentInterest;
	}

	public BigInteger getShouldCapital() {
		return shouldCapital;
	}

	public void setShouldCapital(BigInteger shouldCapital) {
		this.shouldCapital = shouldCapital;
	}

	public BigInteger getShouldInterest() {
		return shouldInterest;
	}

	public void setShouldInterest(BigInteger shouldInterest) {
		this.shouldInterest = shouldInterest;
	}
	
}
