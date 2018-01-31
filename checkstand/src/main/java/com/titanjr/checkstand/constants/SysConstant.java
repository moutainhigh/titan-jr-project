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
	public static String RS_MERCHANT_NO = "M000016";
	public static String RS_SIGN_TYPE = "1";
	public static String RS_VERSION = "v1.0";
	public static String RS_VERSION_NEW = "v1.1";
	public static String RS_SUCCESS_CODE = "0000";
	public static String RS_SUCCESS_MSG = "成功";
	
	public static String RS_CARD_AUTH_SUCCESS = "3";
	public static String RS_CARD_AUTH_FAILED = "4";
	
	
	//-----------------渠道code-----------------------------------
	public static String TL_CHANNEL_CODE = "01";
	public static String RB_CHANNEL_CODE = "02";
	
	
	//--------------------------版本号-----------------------------------
	//通联网银支付版本号
	public static String TL_NETBANK_PAY_VERSION = "v1.0";
	//通联网银支付查询版本号
	public static String TL_NETBANK_PAY_QUERY_VERSION = "v1.5";
	//通联网银退款版本号
	public static String TL_NETBANK_REFUND_VERSION = "v2.3";
	//通联网银退款查询版本号
	public static String TL_NETBANK_REFUND_QUERY_VERSION = "v2.4";
	//通联扫码/微信公众号交易默认版本号
	public static String TL_QRCODE_VERSION = "11";
	//融宝接口版本号
	public static String RB_VERSION = "3.1.3";
	
	//--------------------------signType-----------------------------------
	//通联网银交易默认MD5签名方式
	public static String TL_NETBANK_SIGNTYPE = "0";
	//融宝签名类型
	public static String RB_SIGN_TYPE = "MD5";

	
	//-------------------------------商户号、证书、用户名、密码-----------------------------------
	//通联网银支付商户号
	public static String TL_NETBANK_MERCHANT = "100020091218001";
	//通联扫码支付商户号
	public static String TL_QRCODE_CUSTID = "990440153996000";//生产
	//通联账户交易商户号
	public static String TL_AGENT_MERCHANT = "200604000000445";
	//融宝快捷支付商户号
	public static String RB_QUICKPAY_MERCHANT = "100000000000147";
	
	//通联账户交易用户名/密码
	public static String USER_NAME = "20060400000044504";//info下面的用户名一般是：商户号+04（通联demo里面是加02，后续需验证）
	public static String USER_PWD = "`12qwe";
	
	//通联账户交易商户证书
	public static String PFX_PATH= "certify/20060400000044502.p12";//私钥位置
	public static String PFX_PWD = "111111";//私钥密码
	public static String CER_PATH = "certify/allinpay-pds.cer";//公钥位置
	
	//融宝快捷支付证书
	public static String PRIVATE_KEY_PATH = "D:\\cert\\itrus001.pfx";//私钥位置（融宝测试所用，上线需要自己生成私钥）
	//public static String PRIVATE_KEY_PATH = "/opt/rb-cert/itrus001.pfx";//linux上的目录，上到213使用
	public static String PRIVATE_KEY_PWD = "123456";//私钥密码
	public static String PUBLIC_KEY_PATH = "D:\\cert\\itrus001.cer";//融宝公钥 正式环境不用更换
	//public static String PUBLIC_KEY_PATH = "/opt/rb-cert/itrus001.cer";//linux上的目录，上到213使用
	
	//签约融宝支付账号或卖家收款融宝支付帐户-test
	public static String RB_SELLER_EMAIL = "850138237@qq.com";
	
	
	//--------------------------其他-----------------------------------
	public static String RB_CURRENCY = "156";//融宝币种，人民币
	public static String RB_SUCCESS_CODE = "0000";//标示融宝返回结果成功的编码
	
	//账户交易-代付业务代码
	public static String WITHDRAW_CODE = "09400";
	
	public static String CARD_TYPE_DESPOSIT = "10";
	public static String CARD_TYPE_CREDIT = "11";
	
}
