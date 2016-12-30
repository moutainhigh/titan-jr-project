package com.fangcang.titanjr.common.util;

/**
 * 短信或者邮件模板
 * @author luoqinglong
 * @date   2016年12月30日
 */
public enum SMSTemplate {
	LOAN_REQ_ING("贷款申请提交成功","{1}提交的贷款订单{2}，贷款金额{3}，收款方：{4}，贷款订单已提交成功，正在等待审批，如有疑问可联系融资方详细咨询","贷款申请提交成功"),
	LOAN_AUDIT_PASS("贷款申请审核通过","{1}提交的贷款订单{2}，贷款金额{3}，收款方：{4}，贷款订单审核已通过，等待放款中","贷款申请审核通过"),
	LOAN_AUDIT_FIAL("贷款审核不通过","{1}提交的贷款订单{2}，贷款金额{3}，收款方：{4}，贷款订单审核未通过，如有疑问可联系融资方详细咨询","贷款审核不通过"),
	LOAN_HAVE_LOAN("贷款已放款","{1}提交的贷款订单{2}，贷款金额{3}，收款方：{4}，贷款订单已放款","贷款已放款"),
	LOAN_LENDING_FAIL("贷款放款失败","{1}提交的贷款订单{2}，贷款金额{3}，收款方：{4}，贷款订单放款失败，如有疑问可联系融资方详细咨询","贷款放款失败");
	
	private  SMSTemplate(String subject,String content,String des){
		this.subject = subject;
		this.content = content;
		this.des = des;
	}
	/***
	 * 标题
	 */
	private String subject;
	/**
	 * 类容（格式化内容）
	 */
	private String content;
	/***
	 * 描述
	 */
	private String des;
	
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	
}
