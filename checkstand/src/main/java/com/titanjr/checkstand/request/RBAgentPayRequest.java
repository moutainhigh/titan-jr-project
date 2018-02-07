/** 
 * CopyRright ©2017 深圳市天下房仓科技有限公司 All Right Reserved.
 * 
 * @fileName RBAgentPayRequest.java
 * @author Jerry
 * @date 2018年2月5日 上午10:58:08  
 */
package com.titanjr.checkstand.request;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.validator.constraints.NotBlank;
import com.titanjr.checkstand.dto.RBAgentPayContentDTO;

/**
 * 融宝代付请求参数
 * @author Jerry
 * @date 2018年2月5日 上午10:58:08  
 */
public class RBAgentPayRequest extends RBBaseRequest {

	/** 
	 * 
	 */
	private static final long serialVersionUID = 1204093502039823469L;
	
	/**
	 * 编码  GBK，UTF-8  
	 */
	@NotBlank
	private String charset;
	/**
	 * 交易时间   yyyy-MM-dd HH:mm:ss
	 */
	@NotBlank
	private String trans_time;
	/**
	 * 商户后台系统的回调地址，选填<br>
	 * 需要找融宝开通
	 */
	private String notify_url;
	/**
	 * 批次号（唯一标示）  当日不能重复，不能大于50个字符
	 */
	@NotBlank
	private String batch_no;
	/**
	 * 批次总数量    pay_type=加急，每包笔数≤200笔；pay_type =普通，每包笔数≤20000笔
	 */
	@NotBlank
	private String batch_count;
	/**
	 * 记账方式  0-加急；1-普通   默认为普通
	 */
	@NotBlank
	private String pay_type;
	/**
	 * 付款场景：
	 * 01	供应商打款
	 * 02	分销商打款
	 * 03	代理商打款
	 * 04	保险理赔
	 * 05	电商赔付
	 * 06	代发工资
	 * 07	代发报销
	 * 08	代发奖金
	 * 09	劳务费
	 * 10	P2P理财
	 * 51	其他
	 */
	@NotBlank
	private String pay_sight;
	/**
	 * 批次总金额  单位元
	 */
	@NotBlank
	private String batch_amount;
	/**
	 * 批次明细，此参数值为加密后的数据<br>
	 * 加密前的明文说明： 序号,银行账户,开户名,开户行,分行,支行,公/私,金额,币种,省,市,手机号,证件类型,证件号,用户协议号,商户订单号,备注<br>
	 * (证件类型 ：'身份证'；'户口簿'；'护照'；'军官证'；'士兵证'；'台胞证')，序号可任意填写,不重复即可<br>
	 * {@link RBAgentPayContentDTO}
	 */
	@NotNull
	private RBAgentPayContentDTO content;
	
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
	public String getNotify_url() {
		return notify_url;
	}
	public void setNotify_url(String notify_url) {
		this.notify_url = notify_url;
	}
	public String getBatch_no() {
		return batch_no;
	}
	public void setBatch_no(String batch_no) {
		this.batch_no = batch_no;
	}
	public String getBatch_count() {
		return batch_count;
	}
	public void setBatch_count(String batch_count) {
		this.batch_count = batch_count;
	}
	public String getPay_type() {
		return pay_type;
	}
	public void setPay_type(String pay_type) {
		this.pay_type = pay_type;
	}
	public String getPay_sight() {
		return pay_sight;
	}
	public void setPay_sight(String pay_sight) {
		this.pay_sight = pay_sight;
	}
	public String getBatch_amount() {
		return batch_amount;
	}
	public void setBatch_amount(String batch_amount) {
		this.batch_amount = batch_amount;
	}
	public RBAgentPayContentDTO getContent() {
		return content;
	}
	public void setContent(RBAgentPayContentDTO content) {
		this.content = content;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE); 
	}

}
