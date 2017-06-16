package com.fangcang.titanjr.web.util;

/**
 * Created by zhaoshan on 2016/4/18.
 */
public class WebConstant {
	/******* Session  key **************/
    public static final String SESSION_KEY_JR_LOGIN_UESRNAME  = "JR_LOGIN_UESRNAME";//金服用户登录名
    public static final String SESSION_KEY_JR_USERID  = "JR_USERID";//金服机构id标示
    public static final String SESSION_KEY_JR_TFS_USERID  = "JR_TFS_USERID";//金服用户名id
    //public static final String SESSION_KEY_JR_RESOURCE  = "JR_RESOURCE";//此时标示是从金服官网登录
    public static final String SESSION_KEY_JR_ROLE_LIST  = "JR_ROLE_LIST";//金服用户角色列表
    
    
    public static final String SESSION_KEY_JR_RESOURCE_1_TFS  = "1";//金服官网登录或者注册
    public static final String SESSION_KEY_JR_RESOURCE_2_SAAS  = "2";//SAAS网站登录或者注册
    public static final String SESSION_KEY_JR_RESOURCE_3_ADMIN  = "3";//金服后台管理登录或者注册
    public static final String SESSION_KEY_JR_RESOURCE_4_TTM  = "4";//TTM登录或者注册
    
    public static final String SESSION_KEY_REG_CODE  = "REG_CODE";//注册时的验证码
   
    /********** TWS *******************/
    
    
    //'全国'的编码
    public static final String COUNTRY_CODE = "00";
    
    //注册验证码有效时长，单位：小时
    public static final Integer REG_CODE_TIME_OUT_HOUR = 1 ;
    
    public static final String MODEL_ERROR_MSG_KEY = "errormsg";
    
    public static final String SERVICE_ERROR_MSG = "系统错误，请重试";
    
    public static final String CONTROLLER_ERROR_MSG = "请求错误，请重试";
    public static final String TRANSFER_PAYAMOUNT = "0";
    //上传最大的上size，10M
    public static final int UPLOAD_IMG_MAX_SIZE_10_M = 10;
    
    //支付失败
    public static final String FAIL = "false";
    
    //调用成功
    public static final String SUCCESS="success";
    
    public static final String RESULT = "result";
    
    public static final String MSG = "msg";
    
    //融数回调时的支付成功的状态
    public static final String PAY_SUCCESS="3";
    
    //对公的账户
	public static final String ACCOUNT_PUBLIC ="1";
	
	//个人的账户
    public static final String ACCOUNT_PERSON ="2";
    
    
    //对公账户，绑定失败
    public static final String ACCOUNT_PUBLIC_FAIL_5="5";
    
    //对公的账户,绑定成功
   	public static final String ACCOUNT_PUBLIC_SUCCESS_4 ="4";
   	
   	//对公账户未绑定
   	public static final String ACCOUNT_PUBLIC_NO_BIND_3 ="3";
   	
   	//对公账户审核中
   	public static final String ACCOUNT_PUBLIC_BINDING_6 ="6";
   	
       
	//提现卡
	public static final String ACCOUNT_PURPOSE_WITHDRAW ="3";
	
	//绑定成功
	public static final Integer BANKCARD_SUCCESS = 1;
	
	public static final Integer BANKCARD_AUDIT = 3;
	
	public static final Integer BANKCARD_BINDING = 2;
	
	public static final Integer BANKCARD_FAILED = 4;
	
	public static final Integer BANKCARD_DELETE = 0;
	
	//绑定提现卡
	public static final String BIND_BANK_CARD="0";
	
	//绑定失败后修改提现卡
	public static final String MODIFY_BANK_CARD="1";
	
	public static final String FREEZE_ORDER = "0";
	//代理登录链接失效时长 ，5分钟
    public static final Long PROXY_LOGIN_LINK_EXPIRE_TIME = 5*60*1000L;
    
    //回话失效，需要重新登录
    public static final int REQUIRED_LOGIN = 603;
}
