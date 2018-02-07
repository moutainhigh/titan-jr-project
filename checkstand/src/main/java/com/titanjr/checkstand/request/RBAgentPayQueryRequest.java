/** 
 * CopyRright ©2017 深圳市天下房仓科技有限公司 All Right Reserved.
 * 
 * @fileName RBAgentPayQueryRequest.java
 * @author Jerry
 * @date 2018年2月6日 下午4:11:28  
 */
package com.titanjr.checkstand.request;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @author Jerry
 * @date 2018年2月6日 下午4:11:28  
 */
public class RBAgentPayQueryRequest extends RBBaseRequest {

	/** 
	 * 
	 */
	private static final long serialVersionUID = 5410594941889827647L;
	
	/**
	 * 编码   UTF-8
	 */
	@NotBlank
	private String charset;
	/**
	 * 查询日期  yyyy-MM-dd
	 */
	@NotBlank
	private String trans_time;
	/**
	 * 批次号  当日不能重复，不能大于50个字符
	 */
	@NotBlank
	private String batch_no;
	/**
	 * 序号  序号同一批次内不可重复，不能大于6个字符
	 */
	@NotBlank
	private String detail_no;
	
	
	public String getCharset() {
		return charset;
	}
	public void setCharset(String charset) {
		this.charset = charset;
	}
	public String getTrans_time() {
		return trans_time;
	}
	public void setTrans_time(String trans_time) {
		this.trans_time = trans_time;
	}
	public String getBatch_no() {
		return batch_no;
	}
	public void setBatch_no(String batch_no) {
		this.batch_no = batch_no;
	}
	public String getDetail_no() {
		return detail_no;
	}
	public void setDetail_no(String detail_no) {
		this.detail_no = detail_no;
	}

}
