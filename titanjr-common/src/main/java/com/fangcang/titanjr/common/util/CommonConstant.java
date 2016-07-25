package com.fangcang.titanjr.common.util;

public class CommonConstant {
    //‘全国’的编码
    public static final String COUNTRY_CODE = "00";
    //一次批量插入的条数
    public static final Integer INSERT_BATCH_NUM = 100;

    //执行成功
    public static final String OPERATE_SUCCESS = "true";
    
    //待处理
    public static final String WAIT_MOMENT = "wait";


    public static final String RS_FANGCANG_CONST_ID = "M000016";//机构号
    public static final String RS_FANGCANG_PRODUCT_ID = "P000070";//产品id
    public static final String ORG_CODE_PREFIX = "TJM";//机构编码前缀
    public static final String ACCOUNT_CODE_PREFIX = "TJA";//accountCode编码前缀
    public static final String RS_FANGCANG_USER_ID = "";//房仓平台userid

    //基础业务类型
    public static final String ORDEROTYPE = "B1";

	  
	//返回成功
	public static final String RETURN_SUCCESS="success";
	/***
	 * 免密码支付额度(单位分)
	 */
	public static final Double NO_PWD_PAY_LIMIT = 100000d;
	//融数充值单默认的失效天数
	public static final Integer ORDER_EXPIRE_TIME = 5;
	/******* 审核人*******/
	public static final String CHECK_ADMIN_JR = "titanjr-admin";//泰坦金融系统管理员
	public static final String CHECK_ADMIN_RS = "rs-admin";//融数系统管理员
	/*******冻结资金账户*******/
	//冻结授权码
	public static final String FUNCCODE = "40171";
	//冻结账户调用的ip
	public static final String USER_IPADDRESS = "218.17.13.199";
	//冻结状态
	public static final Integer FREEZE_STATUS = 1;
	
	public static final int PASSWORD_SALT_LENGTH = 12; //密码salt长度
	
	//线上支付使用路径
	//public static final String REQUSET_URL ="http://www.fangcang.com:8080/TFS/trade/payment.action";
	//测试使用路径
	public static final String REQUSET_URL = "/trade/showCashierDesk.action";
	
	//商户
	public static final String ENTERPRISE = "1";
	
	//个人用户
	public static final String PERSONAL = "2";
	
	//所有绑定卡
	public static final String ALLCARD = "2";
	
	//结算卡
	public static final String DEBIT_CARD = "1";
	
	//提现卡
	public static final String WITHDRAW_CARD="3";
	
	//绑卡正常
	public static final String BIND_SUCCESS = "1";
	
	//审核失败
	public static final String BIND_FAIL = "4";
	
	
	//短信发送选用服务号
	public static final String DEFAULT_SMS_PROVIDER_KEY = "3369";
	
	/************************************** begin 权限   ****************************/
	//是否忘记支付密码
    public static final String IS_FORGET_PAYPASSWORD="1";
	// 付款权限
    public static final String ROLECODE_PAY_38 = "PAY";
    //查看权限
    public static final String ROLECODE_VIEW_39 = "VIEW";
    // 充值和提现权限
    public static final String ROLECODE_RECHARGE_40 = "RECHARGE";
    //理财权限
    public static final String ROLECODE_FINANCING_41 = "FINANCING";
    //贷款权限
    public static final String ROLECODE_LOAN_42 = "LOAN";
    //消息提醒权限
    public static final String ROLECODE_MESSAGE_43 = "MESSAGE";
    //系统运营员权限
    public static final String ROLECODE_OPERATION_44 = "OPERATION";
    
    //系统管理员
    public static final String ROLECODE_ADMIN = "ADMIN";
    
    //不需要权限
    public static final String ROLECODE_NO_LIMIT = "NO_LIMIT";
    
    /************************************** end 权限   ****************************/
    
    //验证码有效时长，单位：小时
    public static final Integer CODE_TIME_OUT_HOUR = 1 ;
	
	
}
