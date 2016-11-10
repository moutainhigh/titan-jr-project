package com.fangcang.titanjr.entity;

import java.util.Date;

/**
 * 授信批注实体类
 * @author wengxitao
 *
 */
public class LoanCreditOpinion 
{
	private String orderNo;//授信单号
	
	private String creater;//创建人
	
	private Date createTime;//创建时间
	
	private String content;//企业内容
	
	private int status;//1 未修改 2 已修改
	
	private int result;//0 未知 1 通过 2 不通过

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getCreater() {
		return creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}
	
	
}
