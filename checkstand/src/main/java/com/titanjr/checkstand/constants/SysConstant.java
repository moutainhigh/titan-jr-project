/** 
 * CopyRright ©2017 深圳市天下房仓科技有限公司 All Right Reserved.
 * 
 * @fileName SysConstant.java
 * @author Jerry
 * @date 2017年11月29日 下午6:25:41  
 */
package com.titanjr.checkstand.constants;

import java.util.Map;

import com.titanjr.checkstand.dto.GateWayConfigDTO;

/**
 * @author Jerry
 * @date 2017年11月29日 下午6:25:41  
 */
public class SysConstant {
	
	/**
	 * key: 商户号_支付方式_渠道_请求类型
	 * value：配置信息
	 */
	public static Map<String, GateWayConfigDTO> gateWayConfigMap = null;
	
	
	//----------------仿照融数相关常量定义-----------------------------------
	public static final String RS_MERCHANT_NO = "M000016";
	public static final String RS_SIGN_TYPE = "1";
	public static final String RS_VERSION = "v1.0";
	public static final String RS_VERSION_NEW = "v1.1";
	public static final String RS_SUCCESS_CODE = "0000";
	public static final String RS_SUCCESS_MSG = "成功";
	
	public static final String RS_CARD_TYPE_DESPOSIT = "10";
	public static final String RS_CARD_TYPE_CREDIT = "11";
	
	public static final String RS_CARD_AUTH_SUCCESS = "3";
	public static final String RS_CARD_AUTH_FAILED = "4";
	
	
	//-----------------渠道code-----------------------------------
	public static final String TL_CHANNEL_CODE = "01";
	public static final String RB_CHANNEL_CODE = "02";
	
	
	//--------------------------版本号-----------------------------------
	//通联网银支付版本号
	public static final String TL_NETBANK_PAY_VERSION = "v1.0";
	//通联网银支付查询版本号
	public static final String TL_NETBANK_PAY_QUERY_VERSION = "v1.5";
	//通联网银退款版本号
	public static final String TL_NETBANK_REFUND_VERSION = "v2.3";
	//通联网银退款查询版本号
	public static final String TL_NETBANK_REFUND_QUERY_VERSION = "v2.4";
	//通联扫码/微信公众号交易默认版本号
	public static final String TL_QRCODE_VERSION = "11";
	//融宝快捷支付版本号
	public static final String RB_QUICKPAY_VERSION = "3.1.3";
	//融宝代付版本号
	public static final String RB_AGENT_VERSION = "1.0";
	
	//--------------------------signType-----------------------------------
	//通联网银交易默认MD5签名方式
	public static final String TL_NETBANK_SIGNTYPE = "0";
	//融宝签名类型
	public static final String RB_SIGN_TYPE = "MD5";

	
	//-------------------------------商户号、证书、用户名、密码-----------------------------------
	//通联网银支付商户号
	public static String TL_NETBANK_MERCHANT = "109164111611001";//测试
	//通联扫码支付商户号   
	//public static final String TL_QRCODE_CUSTID = "990440153996000";//测试
	public static final String TL_QRCODE_CUSTID = "179222079914865";//生产
	//通联账户交易商户号
	//public static final String TL_AGENT_MERCHANT = "200604000000445";//测试
	public static final String TL_AGENT_MERCHANT = "200222000009787";//生产
	//融宝商户号
	//public static final String RB_MERCHANT = "100000000000147";//测试通用
	public static final String RB_MERCHANT = "100000001301858";//生产通用
	
	//通联账户交易商户证书（都是通联帮我们生成的）
	//public static final String PFX_PATH= "certify/tl/20060400000044502.p12";//私钥-测试
	public static final String PFX_PATH= "certify/tl/20022200000978704.p12";//私钥-生产
	public static final String PFX_PWD = "111111";//私钥密码
	public static final String CER_PATH = "certify/tl/allinpay-pds.cer";//公钥
	public static final String MD5_CER_PATH = "certify/tl/TLCert.cer";//MD5加密证书
	//通联账户交易用-户名/密码
	public static final String USER_NAME = "20022200000978704";//用户名：商户号+04
	//public static final String USER_PWD = "`12qwe";//密码-测试
	public static final String USER_PWD = "111111";//密码-生产
	
	//融宝快捷支付证书（在磁盘下）
	public static final String PRIVATE_KEY_PATH = "certify/rb/itrus001.pfx";//房仓生成私钥
	public static final String PUBLIC_KEY_PATH = "certify/rb/itrus001.cer";//融宝提供公钥
	public static final String PRIVATE_KEY_PWD = "123456";//私钥密码
	
	//签约融宝支付账号或卖家收款融宝支付帐户-test
	public static final String RB_SELLER_EMAIL = "850138237@qq.com";
	
	//通联对账文件
	public static String TL_GATEWAY_DIR = "/gateway";
	public static String TL_QECODE_DIR = "/qrcode";
	
	//融宝对账文件-FTP
	public static String RB_FTP_HOST  = "entrust.reapal.com";
	public static String RB_ACCOUNT_DIR  = "/dforderfiles/";
	public static String RB_RECHARGE_DIR  = "/inchannel/";
	public static String RB_REFUND_DIR  = "/refund/";
	public static String RB_FTP_USER = "100000001301858";//生产-对应商户号
	public static String RB_FTP_PWD = "&r1rhAD3s";
	
	
	//--------------------------其他-----------------------------------
	public static final String RB_CURRENCY = "156";//融宝币种，人民币
	public static final String RB_QUICKPAY_SUCCESS = "0000";//标示融宝返回成功的编码
	public static final String RB_AGENT_SUCCESS = "0001";//标示融宝代付查询成功的编码
	public static final String RB_CHARSET_CODE = "UTF-8";//融宝代付相关接口要求编码格式
	
	public static final String TL_WITHDRAW_CODE = "09900";//代付业务代码
	public static final String TL_AGENT_DATA_TYPE = "2";//账户交易的数据格式-固定2：xml格式
	public static final String TL_AGENT_VERSION = "03";//账户交易版本号-固定03
	public static final String TL_AGENT_LEVEL = "3";//账户交易的处理级别，统一暂定为3
	
}
