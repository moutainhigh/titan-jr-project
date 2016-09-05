package com.fangcang.titanjr.pay.rsp;

public class TitanRateComputeRsp extends TitanBaseRsp {
	private String amount;
	private String exRateAmount = "0";
	private String stRateAmount = "0";
	private String rsRateAmount = "0";
	private Integer rateType;
	private String rsRate;
	private String standRate;
	private String executionRate;
	
	public String getExRateAmount() {
		return exRateAmount;
	}

	public void setExRateAmount(String exRateAmount) {
		this.exRateAmount = exRateAmount;
	}

	public String getStRateAmount() {
		return stRateAmount;
	}

	public void setStRateAmount(String stRateAmount) {
		this.stRateAmount = stRateAmount;
	}

	public String getRsRateAmount() {
		return rsRateAmount;
	}

	public void setRsRateAmount(String rsRateAmount) {
		this.rsRateAmount = rsRateAmount;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public Integer getRateType() {
		return rateType;
	}

	public void setRateType(Integer rateType) {
		this.rateType = rateType;
	}

	public String getRsRate() {
		return rsRate;
	}

	public void setRsRate(String rsRate) {
		this.rsRate = rsRate;
	}

	public String getStandRate() {
		return standRate;
	}

	public void setStandRate(String standRate) {
		this.standRate = standRate;
	}

	public String getExecutionRate() {
		return executionRate;
	}

	public void setExecutionRate(String executionRate) {
		this.executionRate = executionRate;
	}

}
