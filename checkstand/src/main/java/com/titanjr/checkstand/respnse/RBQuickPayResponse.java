/** 
 * CopyRright ©2017 深圳市天下房仓科技有限公司 All Right Reserved.
 * 
 * @fileName RBQuickPayResponse.java
 * @author Jerry
 * @date 2018年1月3日 下午2:11:15  
 */
package com.titanjr.checkstand.respnse;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;


/**
 * 融宝快捷支付响应对象
 * @author Jerry
 * @date 2018年1月3日 下午2:11:15  
 */
public class RBQuickPayResponse implements Serializable {

	/** 
	 * 
	 */
	private static final long serialVersionUID = -2646115673016255071L;
	
	/**
	 * 商户ID
	 */
	private String merchant_id;
	/**
	 * 商户订单号
	 */
	private String order_no;
	/**
	 * 返回码，0000表示成功，其他失败
	 */
	private String result_code;
	/**
	 * 绑卡ID  仅限V3.1.2以上版本
	 */
	private String bind_id;
	/**
	 * 错误信息
	 */
	private String result_msg;
	/**
	 * 银行名称
	 */
	private String bank_name;
	/**
	 * 银行编码
	 */
	private String bank_code;
	/**
	 * 认证状态 ，0 未认证（不下发短信，需调用卡密接口），1 已认证（自动发送短信）
	 */
	private String certificate;
	
	
	public String getMerchant_id() {
		return merchant_id;
	}
	public void setMerchant_id(String merchant_id) {
		this.merchant_id = merchant_id;
	}
	public String getOrder_no() {
		return order_no;
	}
	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}
	public String getResult_code() {
		return result_code;
	}
	public void setResult_code(String result_code) {
		this.result_code = result_code;
	}
	public String getBind_id() {
		return bind_id;
	}
	public void setBind_id(String bind_id) {
		this.bind_id = bind_id;
	}
	public String getResult_msg() {
		return result_msg;
	}
	public void setResult_msg(String result_msg) {
		this.result_msg = result_msg;
	}
	public String getBank_name() {
		return bank_name;
	}
	public void setBank_name(String bank_name) {
		this.bank_name = bank_name;
	}
	public String getBank_code() {
		return bank_code;
	}
	public void setBank_code(String bank_code) {
		this.bank_code = bank_code;
	}
	public String getCertificate() {
		return certificate;
	}
	public void setCertificate(String certificate) {
		this.certificate = certificate;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE); 
	}

}
