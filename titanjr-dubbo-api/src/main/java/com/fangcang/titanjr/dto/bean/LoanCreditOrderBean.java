package com.fangcang.titanjr.dto.bean;

import java.util.Date;

/**
 * 贷款授信单
 * 
 * @author wengxitao
 */
public class LoanCreditOrderBean implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -397916317083742463L;

	private String orderNo;

	private String orgCode;// 授信金融机构ID

	private Integer dayLimit;// 授信期限--应该在service中设置

	private Long amount;// 申请授信金额

	private Long actualAmount;// 实际授信金额

	private Date reqTime;// 申请时间;

	private String rateTem;// 费率模板编号;--应该在service中设置，固定的

	private String rspId;// 融数产品编号--应该在service中设置

	private String rsorgId;// 融数机构编号--应该在service中设置

	private String createTime;// 创建时间

	private String urlKey;// 授信资料key

	private Integer status;// 1 草稿 2 授信初审 3 授信终审 4 审核失败 5 授信成功

	private Integer assureType;// 担保 1 个人 2 企业

	private Date firstAuditTime;// 初审通过时间

	private Date lastAuditTime;// 终审通过时间

	private Date auditPass;// 审核通过时间

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public Integer getDayLimit() {
		return dayLimit;
	}

	public void setDayLimit(Integer dayLimit) {
		this.dayLimit = dayLimit;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public Long getActualAmount() {
		return actualAmount;
	}

	public void setActualAmount(Long actualAmount) {
		this.actualAmount = actualAmount;
	}

	public Date getReqTime() {
		return reqTime;
	}

	public void setReqTime(Date reqTime) {
		this.reqTime = reqTime;
	}

	public String getRateTem() {
		return rateTem;
	}

	public void setRateTem(String rateTem) {
		this.rateTem = rateTem;
	}

	public String getRspId() {
		return rspId;
	}

	public void setRspId(String rspId) {
		this.rspId = rspId;
	}

	public String getRsorgId() {
		return rsorgId;
	}

	public void setRsorgId(String rsorgId) {
		this.rsorgId = rsorgId;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUrlKey() {
		return urlKey;
	}

	public void setUrlKey(String urlKey) {
		this.urlKey = urlKey;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getAssureType() {
		return assureType;
	}

	public void setAssureType(Integer assureType) {
		this.assureType = assureType;
	}

	public Date getFirstAuditTime() {
		return firstAuditTime;
	}

	public void setFirstAuditTime(Date firstAuditTime) {
		this.firstAuditTime = firstAuditTime;
	}

	public Date getLastAuditTime() {
		return lastAuditTime;
	}

	public void setLastAuditTime(Date lastAuditTime) {
		this.lastAuditTime = lastAuditTime;
	}

	public Date getAuditPass() {
		return auditPass;
	}

	public void setAuditPass(Date auditPass) {
		this.auditPass = auditPass;
	}

}
