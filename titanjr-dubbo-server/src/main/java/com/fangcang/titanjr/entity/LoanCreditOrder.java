package com.fangcang.titanjr.entity;

import java.util.Date;
/**
 * 贷款授信单
 * @author wengxitao
 */
public class LoanCreditOrder {
	private String newOrderNo;//新单单号
	
	private String orderNo;// 授信单号

	private String orgCode;//机构编码

	private Integer dayLimit;// 授信期限

	private Long amount;// 申请授信总额度

	private Long actualAmount;// 剩余可用授信金额

	private Date reqTime;// 申请时间;

	private String rateTem;// 费率模板编号;

	private String rspId;// 融数产品编号

	private String rsorgId;// 融数机构编号

	private Date createTime;// 创建时间

	private String urlKey;// 授信资料key

	private Integer status;// 1 草稿 2 授信初审(待审核) 3 授信终审(初审通过) 4 审核失败(初审不通过) 5 授信成功

	private Integer assureType;// 担保 1 个人 2 企业
	
	private String firstAuditor;//审核人
	
	private Date firstAuditTime;// 初审通过时间

	private Date lastAuditTime;// 终审通过时间

	private Date auditPass;// 审核通过时间

	private String reqJson;// 授信请求json串
	
	private Date expireTime;//过期时间

	
	public String getFirstAuditor() {
		return firstAuditor;
	}

	public void setFirstAuditor(String firstAuditor) {
		this.firstAuditor = firstAuditor;
	}

	public String getNewOrderNo() {
		return newOrderNo;
	}

	public void setNewOrderNo(String newOrderNo) {
		this.newOrderNo = newOrderNo;
	}

	public Date getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}

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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
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

	public String getReqJson() {
		return reqJson;
	}

	public void setReqJson(String reqJson) {
		this.reqJson = reqJson;
	}

}
