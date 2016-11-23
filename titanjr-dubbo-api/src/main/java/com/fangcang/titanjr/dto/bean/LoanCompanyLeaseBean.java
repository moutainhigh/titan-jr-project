package com.fangcang.titanjr.dto.bean;

public class LoanCompanyLeaseBean {
	
	private String leaseType;
	// 经营信息
	private String beginLeaseDate;// 租赁期限
	private String endLeaseDate;// 租赁期限
	
	private String leaseAddress;// 租赁地址
	private String housingArea;// 建筑面积
	private String rental;// 租金（元/年）
	private String paymentMethod;// 支付方式
	private String remark;// 备注

	public String getLeaseType() {
		return leaseType;
	}

	public void setLeaseType(String leaseType) {
		this.leaseType = leaseType;
	}
	public String getBeginLeaseDate() {
		return beginLeaseDate;
	}

	public void setBeginLeaseDate(String beginLeaseDate) {
		this.beginLeaseDate = beginLeaseDate;
	}

	public String getEndLeaseDate() {
		return endLeaseDate;
	}

	public void setEndLeaseDate(String endLeaseDate) {
		this.endLeaseDate = endLeaseDate;
	}

	public String getLeaseAddress() {
		return leaseAddress;
	}

	public void setLeaseAddress(String leaseAddress) {
		this.leaseAddress = leaseAddress;
	}

	public String getHousingArea() {
		return housingArea;
	}

	public void setHousingArea(String housingArea) {
		this.housingArea = housingArea;
	}

	public String getRental() {
		return rental;
	}

	public void setRental(String rental) {
		this.rental = rental;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
