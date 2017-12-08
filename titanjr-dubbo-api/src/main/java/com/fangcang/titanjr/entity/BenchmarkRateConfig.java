/** 
 * CopyRright ©2017 深圳市天下房仓科技有限公司 All Right Reserved.
 * 
 * @fileName BenchmarkRateConfig.java
 * @author Jerry
 * @date 2017年11月20日 下午2:29:14  
 */
package com.fangcang.titanjr.entity;

import java.io.Serializable;

/**
 * 基准费率配置
 * @author Jerry
 * @date 2017年11月20日 下午2:29:14  
 */
public class BenchmarkRateConfig implements Serializable {

	/** 
	 * 
	 */
	private static final long serialVersionUID = -8745889840599103492L;
	
	/**
	 * 主键
	 */
	private long ratebenchid;
	
	/**
	 * 支付渠道
	 */
	private String paychannel;
	
	/**
	 * 支付方式
	 */
	private Integer paytype;
	
	/**
	 * 银行卡类型 10储蓄卡  11信用卡
	 */
	private Integer cardtype;
	
	/**
	 * 费率类型1.百分比，2.每笔固定
	 */
	private Integer ratetype;
	
	/**
	 * 基准费率
	 */
	private Float bmrate;
	
	/**
	 * 最小费率
	 */
	private Float minrate;
	
	/**
	 * 最大费率
	 */
	private Float maxrate;
	
	/**
	 * 有效截止日期
	 */
	private String expiration;
	
	/**
	 * 描述
	 */
	private String description;
	
	/**
	 * 创建人
	 */
	private String creator;
	
	/**
	 * 创建时间
	 */
	private String createtime;

	public long getRatebenchid() {
		return ratebenchid;
	}

	public void setRatebenchid(long ratebenchid) {
		this.ratebenchid = ratebenchid;
	}

	public String getPaychannel() {
		return paychannel;
	}

	public void setPaychannel(String paychannel) {
		this.paychannel = paychannel;
	}

	public Integer getPaytype() {
		return paytype;
	}

	public void setPaytype(Integer paytype) {
		this.paytype = paytype;
	}

	public Integer getCardtype() {
		return cardtype;
	}

	public void setCardtype(Integer cardtype) {
		this.cardtype = cardtype;
	}

	public Integer getRatetype() {
		return ratetype;
	}

	public void setRatetype(Integer ratetype) {
		this.ratetype = ratetype;
	}

	public Float getBmrate() {
		return bmrate;
	}

	public void setBmrate(Float bmrate) {
		this.bmrate = bmrate;
	}

	public Float getMinrate() {
		return minrate;
	}

	public void setMinrate(Float minrate) {
		this.minrate = minrate;
	}

	public Float getMaxrate() {
		return maxrate;
	}

	public void setMaxrate(Float maxrate) {
		this.maxrate = maxrate;
	}

	public String getExpiration() {
		return expiration;
	}

	public void setExpiration(String expiration) {
		this.expiration = expiration;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

}
