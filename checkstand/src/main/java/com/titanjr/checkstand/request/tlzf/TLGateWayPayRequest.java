/** 
 * CopyRright ©2017 深圳市天下房仓科技有限公司 All Right Reserved.
 * 
 * @fileName AllinPayRequestDTO.java
 * @author Jerry
 * @date 2017年10月27日 上午11:27:55  
 */
package com.titanjr.checkstand.request.tlzf;

import java.io.Serializable;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 通联支付请求对象
 * @author Jerry
 * @date 2017年10月27日 上午11:27:55  
 */
public class TLGateWayPayRequest implements Serializable {
	
	/** 
	 * 
	 */
	private static final long serialVersionUID = -2983288355583447559L;

	/**
	 * 默认填1；1代表UTF-8、2代表GBK、3代表GB2312；
	 */
	@NotBlank
	private String inputCharset;
	/**
	 * 前台回调地址
	 */
	@NotBlank
	private String pickupUrl;
	/**
	 * 后台回调地址
	 */
	@NotBlank
	private String receiveUrl;
	/**
	 * 网关接收支付请求接口版本, 固定填v1.0
	 */
	@NotBlank
	private String version;
	/**
	 * 默认填1，固定选择值：1；1代表简体中文、2代表繁体中文、3代表英文
	 */
	@NotBlank
	private String language;
	/**
	 * 默认填1，固定选择值：0、1；
	 * 0表示订单上送和交易结果通知都使用MD5进行签名
	 * 1表示商户用使用MD5算法验签上送订单，通联交易结果通知使用证书签名
	 * Asp商户不使用通联dll文件签名验签的商户填0
	 */
	@NotBlank
	private String signType;
	/**
	 * 数字串，商户在通联申请开户的商户号
	 */
	@NotBlank
	private String merchantId;
	/**
	 * 付款人姓名, 跨境支付商户若采用直连模式，必须填写该值
	 */
	@NotBlank
	private String payerName;
	/**
	 * 付款人邮件联系方式
	 */
	private String payerEmail;
	/**
	 * 付款人电话联系方式
	 */
	private String payerTelephone;
	/**
	 * 付款人类型及证件号 <br>
	 * 填写规则：证件类型+证件号码再使用通联公钥加密。（加密请参考示例代码）
	 */
	private String payerIDCard;
	/**
	 * 用于商户与第三方合作伙伴拓展支付业务，Partner merchantId
	 */
	private String pid;
	/**
	 * 商户订单号<br>
	 * 只允许使用字母、数字、- 、_,并以字母或数字开头；每商户提交的订单号，必须在当天的该商户所有交易中唯一
	 */
	@NotBlank
	private String orderNo;
	/**
	 * 商户订单金额<br>
	 * 整型数字，金额与币种有关 如果是人民币，则单位是分，即10元提交时金额应为1000 如果是美元，单位是美分，即10美元提交时金额为1000
	 */
	@NotBlank
	private Long orderAmount;
	/**
	 * 订单金额币种类型<br>
	 * 默认填0, 0和156代表人民币、840代表美元、344代表港币，跨境支付商户不建议使用0
	 */
	@NotBlank
	private String orderCurrency;
	/**
	 * 商户订单提交时间<br>
	 * 日期格式：yyyyMMDDhhmmss，例如：20121116020101
	 */
	@NotBlank
	private String orderDatetime;
	/**
	 * 订单过期时间<br>
	 * 整形数字，单位为分钟。最大值为9999分钟<br>
	 * 如填写则以商户上送时间为准，如不填写或填0或填非法值，则服务端默认该订单9999分钟后过期，超期后不允许商户发起同一商户订单支付
	 */
	private String orderExpireDatetime;
	/**
	 * 商品名称, 英文或中文字符串，请勿首尾有空格字符
	 */
	private String productName;
	/**
	 * 商品价格, 整型数字
	 */
	private String productPrice;
	/**
	 * 商品数量, 整型数字，默认传1
	 */
	private String productNum;
	/**
	 * 商品代码<br>
	 * 字母、数字或- 、_ 的组合；用于使用产品数据中心的产品数据，或用于市场活动的优惠
	 */
	private String productId;
	/**
	 * 商品描述
	 */
	private String productDesc;
	/**
	 * 扩展字段1
	 */
	private String ext1;
	/**
	 * 扩展字段2
	 */
	private String ext2;
	/**
	 * 通联商户拓展业务字段，在v2.2.0版本之后才使用到的，用于开通分账等业务
	 */
	private String extTL;
	/**
	 * 支付方式<br>
	 * 固定选择值：0、1、4、11、23、28<br>
	 * 接入互联网关时，默认为间连模式，填0<br>
	 * 若需接入外卡支付，只支持直连模式，即固定上送payType=23，issuerId=visa或mastercard <br>
	 * 0代表未指定支付方式，即显示该商户开通的所有支付方式，1个人储蓄卡网银支付， 4企业网银支付， 11个人信用卡网银支付， 23外卡支付，28认证支付 <br>
	 * 非直连模式，设置为0；直连模式，值为非0的固定选择值
	 */
	@NotBlank
	private String payType;
	/**
	 * 发卡方代码<br>
	 * 银行或预付卡发卡机构代码，用于指定支付使用的付款方机构。   接入手机网关时，该值留空 <br>
	 * payType为0时， issuerId必须为空——显示该商户支持的所有支付类型和各支付类型下支持的全部发卡机构 <br>
	 * payType非0时，若issuerId为空——显示该商户所填payType支付类型下的全部发卡机构 ，若issuerId不为空——直接跳转到该商户所填payType下指定的发卡机构网银页面，支持发卡机构详见附录《机构代码》
	 */
	private String issuerId;
	/**
	 * 付款人支付卡号<br>
	 * 数字串 如果是民生银行B2B直连模式，企业客户号，必填；<br>
     * 如果是投融资行业希望支付卡号，则必填<br>
	 * 上送的卡号必须使用公钥加密(PKCS1)后进行Base64编码。
	 */
	private String pan;
	/**
	 * 贸易类型<br>
	 * 固定选择值：GOODS或SERVICES 当币种为人民币时选填  当币种为非人民币时必填，GOODS表示实物类型，SERVICES表示服务类型
	 */
	private String tradeNature;
	/**
	 * 签名串生成机制：按上述顺序所有非空参数与密钥key组合，经MD5加密后生成signMsg；
	 */
	private String signMsg;
	/**
	 * 通联网关地址
	 */
	private String serverUrl;
	
	
	public String getInputCharset() {
		return inputCharset;
	}
	public void setInputCharset(String inputCharset) {
		this.inputCharset = inputCharset;
	}
	public String getPickupUrl() {
		return pickupUrl;
	}
	public void setPickupUrl(String pickupUrl) {
		this.pickupUrl = pickupUrl;
	}
	public String getReceiveUrl() {
		return receiveUrl;
	}
	public void setReceiveUrl(String receiveUrl) {
		this.receiveUrl = receiveUrl;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getSignType() {
		return signType;
	}
	public void setSignType(String signType) {
		this.signType = signType;
	}
	public String getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}
	public String getPayerName() {
		return payerName;
	}
	public void setPayerName(String payerName) {
		this.payerName = payerName;
	}
	public String getPayerEmail() {
		return payerEmail;
	}
	public void setPayerEmail(String payerEmail) {
		this.payerEmail = payerEmail;
	}
	public String getPayerTelephone() {
		return payerTelephone;
	}
	public void setPayerTelephone(String payerTelephone) {
		this.payerTelephone = payerTelephone;
	}
	public String getPayerIDCard() {
		return payerIDCard;
	}
	public void setPayerIDCard(String payerIDCard) {
		this.payerIDCard = payerIDCard;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public Long getOrderAmount() {
		return orderAmount;
	}
	public void setOrderAmount(Long orderAmount) {
		this.orderAmount = orderAmount;
	}
	public String getOrderCurrency() {
		return orderCurrency;
	}
	public void setOrderCurrency(String orderCurrency) {
		this.orderCurrency = orderCurrency;
	}
	public String getOrderDatetime() {
		return orderDatetime;
	}
	public void setOrderDatetime(String orderDatetime) {
		this.orderDatetime = orderDatetime;
	}
	public String getOrderExpireDatetime() {
		return orderExpireDatetime;
	}
	public void setOrderExpireDatetime(String orderExpireDatetime) {
		this.orderExpireDatetime = orderExpireDatetime;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(String productPrice) {
		this.productPrice = productPrice;
	}
	public String getProductNum() {
		return productNum;
	}
	public void setProductNum(String productNum) {
		this.productNum = productNum;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getProductDesc() {
		return productDesc;
	}
	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}
	public String getExt1() {
		return ext1;
	}
	public void setExt1(String ext1) {
		this.ext1 = ext1;
	}
	public String getExt2() {
		return ext2;
	}
	public void setExt2(String ext2) {
		this.ext2 = ext2;
	}
	public String getExtTL() {
		return extTL;
	}
	public void setExtTL(String extTL) {
		this.extTL = extTL;
	}
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}
	public String getIssuerId() {
		return issuerId;
	}
	public void setIssuerId(String issuerId) {
		this.issuerId = issuerId;
	}
	public String getPan() {
		return pan;
	}
	public void setPan(String pan) {
		this.pan = pan;
	}
	public String getTradeNature() {
		return tradeNature;
	}
	public void setTradeNature(String tradeNature) {
		this.tradeNature = tradeNature;
	}
	public String getSignMsg() {
		return signMsg;
	}
	public void setSignMsg(String signMsg) {
		this.signMsg = signMsg;
	}
	public String getServerUrl() {
		return serverUrl;
	}
	public void setServerUrl(String serverUrl) {
		this.serverUrl = serverUrl;
	}

}
