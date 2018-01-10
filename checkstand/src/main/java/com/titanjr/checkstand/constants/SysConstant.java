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
	
	
	/**
	 * 以下信息为测试所用，以后可能不放这里
	 */
	public static String RS_MERCHANT_NO = "141223100000056";
	public static String RS_VERSION = "v1.0";
	public static String RS_VERSION_NEW = "v1.1";
	public static String RS_SUCCESS_CODE = "0000";
	public static String RS_SUCCESS_MSG = "成功";
	
	//通联网银支付前台回调地址
	public static String TL_NB_CALLBACK_PAGE_URL = "http://www.fangcang.org/checkstand/callback/tlNetBankPayConfirmPage.action";
	
	//通联网银支付后台回调地址
	public static String TL_NB_NOTICE_URL = "http://www.fangcang.org/checkstand/callback/tlNetBankPayNotice.action";
	
	//通联扫码支付结果通知地址
	public static String TL_QRCODE_NOTICE_URL = "http://www.fangcang.org/checkstand/callback/tlQrCodePayNotice.action";
	
	//通联账户交易网关地址
	public static String AGENT_TRADE_URL = "https://113.108.182.3/aipg/ProcessServlet";
	
	//通联网银支付商户号
	public static String NETBANK_MERCHANT = "100020091218001";
	
	//通联扫码支付商户号
	public static String QRCODE_CUSTID = "990440153996000";//生产
	
	//账户交易商户号
	public static String AGENT_MERCHANT = "200604000000445";
	
	//账户交易用户名/密码
	public static String USER_NAME = "20060400000044504";
	public static String USER_PWD = "`12qwe"; 
	
	//账户交易-代付业务代码
	public static String WITHDRAW_CODE = "09400";
	
	//账户交易商户证书
	public static String PFX_PATH= "certify/20060400000044502.p12";
	public static String CER_PATH = "certify/allinpay-pds.cer";
	public static String PFX_PWD = "111111";
	
	//账户交易-查询结果返回码
	public static String RET_CODE_PROCESS = "00";//处理中
	public static String RET_CODE_SUCCESS = "01";//成功
	public static String RET_CODE_FAILD = "02";//失败
	public static String RET_CODE_NOT_FIND = "03";//没有对应的交易
	
	
	//签约融宝支付账号或卖家收款融宝支付帐户-test
	public static String RB_QUICKPAY_MERCHANT = "100000000000147";
	//签约融宝支付账号或卖家收款融宝支付帐户-test
	public static String RB_SELLER_EMAIL = "850138237@qq.com";
	//融宝接口版本号
	public static String RB_VERSION = "3.1.3";
	//融宝签名类型
	public static String RB_SIGN_TYPE = "MD5";
	//私钥位置（融宝测试所用，上线需要自己生成私钥）
	public static String PRIVATE_KEY_PATH = "D:\\cert\\itrus001.pfx";
	//私钥密码
	public static String PRIVATE_KEY_PWD = "123456";
	//融宝公钥 正式环境不用更换
	public static String PUBLIC_KEY_PATH = "D:\\cert\\itrus001.cer";
	
	public static String CARD_TYPE_DESPOSIT = "10";
	public static String CARD_TYPE_CREDIT = "11";
	
}
