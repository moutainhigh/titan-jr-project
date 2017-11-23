package com.fangcang.titanjr.pay.rsp;

public class TitanRateComputeRsp extends TitanBaseRsp {
	private String amount;
	private String exRateAmount = "0";
	private String stRateAmount = "0";
	private String benchmarkRateAmount = "0";
	private Integer rateType;
	private String benchmarkRate;
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

	public String getBenchmarkRateAmount() {
		return benchmarkRateAmount;
	}

	public void setBenchmarkRateAmount(String benchmarkRateAmount) {
		this.benchmarkRateAmount = benchmarkRateAmount;
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

	public String getBenchmarkRate() {
		return benchmarkRate;
	}

	public void setBenchmarkRate(String benchmarkRate) {
		this.benchmarkRate = benchmarkRate;
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
