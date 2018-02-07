/** 
 * CopyRright ©2017 深圳市天下房仓科技有限公司 All Right Reserved.
 * 
 * @fileName RBAgentPayResponse.java
 * @author Jerry
 * @date 2018年2月5日 下午6:03:59  
 */
package com.titanjr.checkstand.respnse;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * @author Jerry
 * @date 2018年2月5日 下午6:03:59  
 */
public class RBAgentPayResponse extends RBBaseResponse {

	/** 
	 * 
	 */
	private static final long serialVersionUID = -4722566806933159781L;
	
	/**
	 * 编码  UTF-8
	 */
	private String charset;
	/**
	 * 批次号
	 */
	private String batch_no;
	
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

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE); 
	}

}
