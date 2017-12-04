/** 
 * CopyRright ©2017 深圳市天下房仓科技有限公司 All Right Reserved.
 * 
 * @fileName SysConfigDTO.java
 * @author Jerry
 * @date 2017年11月30日 下午5:16:03  
 */
package com.titanjr.checkstand.dto;

import java.io.Serializable;

/**
 * 系统配置信息
 * @author Jerry
 * @date 2017年11月30日 下午5:16:03  
 */
public class GateWayConfigDTO implements Serializable {

	/** 
	 * 
	 */
	private static final long serialVersionUID = -3414074363483467063L;
	
	private Integer configId;
	
	/**
	 * 商户号
	 */
	private String merchantId;
	
	/**
	 * 支付类型
	 * 1个人银行/信用卡/企业银行  2公众号(服务窗)/第三方扫码支付  3快捷支付
	 */
	private Integer combPayType;
	
	/**
	 * 支付渠道 01通联 02融宝
	 */
	private String payChannel;
	
	/**
	 * 请求类型, 101网关支付,支付查询,网关退款  102退款查询 <br>
	 * 201公账号/服务窗支付 202交易撤销 203交易退款 204交易查询 <br>
	 * 301卡BIN查询 302储蓄卡签约 303信用卡签约 304查询绑卡信息 305绑卡支付请求 306卡密鉴权 307确认支付 308支付结果查询 309解绑卡 
	 * 310发送(重发)短信 311更换手机号 312退款请求 313手动关闭订单 314退款查询
	 */
	private String requestType;
	
	/**
	 * 应用ID（公众号/服务窗支付）
	 */
	private String appId;

	/**
	 * 网关地址
	 */
	private String gateWayUrl;

	/**
	 * 用于加密的key
	 */
	private String secretKey;

	/**
	 * 备注
	 */
	private String remark;
	

	public Integer getConfigId() {
		return configId;
	}

	public void setConfigId(Integer configId) {
		this.configId = configId;
	}

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	public Integer getCombPayType() {
		return combPayType;
	}

	public void setCombPayType(Integer combPayType) {
		this.combPayType = combPayType;
	}

	public String getPayChannel() {
		return payChannel;
	}

	public void setPayChannel(String payChannel) {
		this.payChannel = payChannel;
	}

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getGateWayUrl() {
		return gateWayUrl;
	}

	public void setGateWayUrl(String gateWayUrl) {
		this.gateWayUrl = gateWayUrl;
	}

	public String getSecretKey() {
		return secretKey;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
