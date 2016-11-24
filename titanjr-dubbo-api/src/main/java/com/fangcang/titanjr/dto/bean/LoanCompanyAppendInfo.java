package com.fangcang.titanjr.dto.bean;

import java.util.List;

/**
 * Created by zhaoshan on 2016/11/18.
 */
public class LoanCompanyAppendInfo implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<LoanControllDataBean> controllDatas;
	private List<LoanCooperationCompanyBean> cooperationCompanyInfos;
	private List<LoanMainBusinessDataBean> mainBusinessDatas;
	private LoanCompanyLeaseBean companyLease;

	public LoanCompanyLeaseBean getCompanyLease() {
		return companyLease;
	}

	public void setCompanyLease(LoanCompanyLeaseBean companyLease) {
		this.companyLease = companyLease;
	}

	public List<LoanControllDataBean> getControllDatas() {
		return controllDatas;
	}

	public void setControllDatas(List<LoanControllDataBean> controllDatas) {
		this.controllDatas = controllDatas;
	}

	public List<LoanCooperationCompanyBean> getCooperationCompanyInfos() {
		return cooperationCompanyInfos;
	}

	public void setCooperationCompanyInfos(
			List<LoanCooperationCompanyBean> cooperationCompanyInfos) {
		this.cooperationCompanyInfos = cooperationCompanyInfos;
	}

	public List<LoanMainBusinessDataBean> getMainBusinessDatas() {
		return mainBusinessDatas;
	}

	public void setMainBusinessDatas(
			List<LoanMainBusinessDataBean> mainBusinessDatas) {
		this.mainBusinessDatas = mainBusinessDatas;
	}

}
