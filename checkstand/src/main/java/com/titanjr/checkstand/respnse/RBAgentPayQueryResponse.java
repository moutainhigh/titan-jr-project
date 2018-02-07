/** 
 * CopyRright ©2017 深圳市天下房仓科技有限公司 All Right Reserved.
 * 
 * @fileName RBAgentPayQueryResponse.java
 * @author Jerry
 * @date 2018年2月6日 下午4:39:07  
 */
package com.titanjr.checkstand.respnse;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * @author Jerry
 * @date 2018年2月6日 下午4:39:07  
 */
public class RBAgentPayQueryResponse extends RBBaseResponse {

	/** 
	 * 
	 */
	private static final long serialVersionUID = -7955164783836775666L;
	
	/**
	 * 编码  UTF-8
	 */
	private String charset;
	/**
	 * 批次号
	 */
	private String batch_no;
	/**
	 * 序号
	 */
	private String detail_no;
	/**
	 * 结果明细
	 */
	private String content;
	
	
	public String getCharset() {
		return charset;
	}
	public void setCharset(String charset) {
		this.charset = charset;
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE); 
	}

}
