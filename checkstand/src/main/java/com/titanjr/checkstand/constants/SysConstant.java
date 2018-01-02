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
	public static String QRCODE_CUSTID = "990440153996000";
	
	//账户交易商户号
	public static String AGENT_MERCHANT = "200604000000445";
	
	//账户交易用户名/密码
	public static String USER_NAME = "20060400000044504";
	public static String USER_PWD = "`12qwe"; 
	
	//账户交易-代付业务代码
	public static String WITHDRAW_CODE = "09400";
	
	//账户交易商户证书
	public static String PFX_PATH= "certify/20060400000044502.p12";
	public static String CRE_PATH = "certify/allinpay-pds.cer";
	public static String PFX_PWD = "111111";
	
	//账户交易-查询结果返回码
	public static String RET_CODE_PROCESS = "00";//处理中
	public static String RET_CODE_SUCCESS = "01";//成功
	public static String RET_CODE_FAILD = "02";//失败
	public static String RET_CODE_NOT_FIND = "03";//没有对应的交易
	
}
