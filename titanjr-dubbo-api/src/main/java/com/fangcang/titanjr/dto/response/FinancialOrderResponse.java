package com.fangcang.titanjr.dto.response;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fangcang.titanjr.dto.BaseRequestDTO;
import com.fangcang.titanjr.dto.BaseResponseDTO;
import com.fangcang.titanjr.dto.bean.AmtTypeEnum;


public class FinancialOrderResponse extends BaseResponseDTO{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 工单ID
	 */
	private Long financeId;
	
	/**
	 * 工单编码
	 */
	private String financeCode;
	
	/**
	 * 业务单号
	 */
	private String orderCode;
	
	/**
	 * 进账账户编码
	 */
	private String inAccountCode;
	
	/**
	 * 进账账户名称
	 */
	private String inAccountName;
	
	/**
	 * 出帐账户编码
	 */
	private String outAccountCode;
	
	/**
	 * 出帐账户名称
	 */
	private String outAccountName;
	
	/**
	 * 付款金额
	 */
	private BigDecimal payAmount;
	
	/**
	 * 币种
	 */
	private AmtTypeEnum currency;
	
	/**
	 * 付款方式
	 */
	private String passage;
	
	/**
	 * 状态
	 */
	private String status;
	
	/**
	 * 类型
	 */
	private String type;
	
	/**
	 * 交易内容
	 */
	private String content;
	
	/**
	 * 备注
	 */
	private String note;
	
	/**
	 * 创建人
	 */
	private String creator;
	
	/**
	 * 创建时间
	 */
	private Date createDate;
	
	/**
	 * 更新人
	 */
	private String updateBy;
	
	/**
	 * 更新时间
	 */
	private Date updateDate;

    /**
     * 审核状态
     */
    private String reviewStatus;

    /**
     * 审核人
     */
    private String reviewer;

    /**
     * 审核时间
     */
    private Date reviewDate;

    /**
     * 凭证单号
     */
    private String serialNumber;

    /**
     *  工单操作日志
     */
    private List<String> financeLogList;
    
    /**
     * 银行账号
     */
    private String bankAccount;
    
    /**
     * 银行名称
     */
    private String bankName;
    
    /**
     * 到账时间
     */
    private Date accountData;

    public Long getFinanceId() {
        return financeId;
    }

    public void setFinanceId(Long financeId) {
        this.financeId = financeId;
    }

    public String getFinanceCode() {
        return financeCode;
    }

    public void setFinanceCode(String financeCode) {
        this.financeCode = financeCode;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getInAccountCode() {
        return inAccountCode;
    }

    public void setInAccountCode(String inAccountCode) {
        this.inAccountCode = inAccountCode;
    }

    public String getInAccountName() {
        return inAccountName;
    }

    public void setInAccountName(String inAccountName) {
        this.inAccountName = inAccountName;
    }

    public String getOutAccountCode() {
        return outAccountCode;
    }

    public void setOutAccountCode(String outAccountCode) {
        this.outAccountCode = outAccountCode;
    }

    public String getOutAccountName() {
        return outAccountName;
    }

    public void setOutAccountName(String outAccountName) {
        this.outAccountName = outAccountName;
    }

    public BigDecimal getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }

    public AmtTypeEnum getCurrency() {
		return currency;
	}

	public void setCurrency(AmtTypeEnum currency) {
		this.currency = currency;
	}

	public String getPassage() {
        return passage;
    }

    public void setPassage(String passage) {
        this.passage = passage;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getReviewStatus() {
        return reviewStatus;
    }

    public void setReviewStatus(String reviewStatus) {
        this.reviewStatus = reviewStatus;
    }

    public String getReviewer() {
        return reviewer;
    }

    public void setReviewer(String reviewer) {
        this.reviewer = reviewer;
    }

    public Date getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(Date reviewDate) {
        this.reviewDate = reviewDate;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public List<String> getFinanceLogList() {
        return financeLogList;
    }

    public void setFinanceLogList(List<String> financeLogList) {
        this.financeLogList = financeLogList;
    }

	public String getBankAccount()
	{
		return bankAccount;
	}

	public void setBankAccount(String bankAccount)
	{
		this.bankAccount = bankAccount;
	}

	public String getBankName()
	{
		return bankName;
	}

	public void setBankName(String bankName)
	{
		this.bankName = bankName;
	}

	public Date getAccountData()
	{
		return accountData;
	}

	public void setAccountData(Date accountData)
	{
		this.accountData = accountData;
	}

}
