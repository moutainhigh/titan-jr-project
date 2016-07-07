package com.fangcang.titanjr.web.util;

/**
 * Created by zhaoshan on 2016/4/18.
 */
public class CommonConstant {
	/******* Session  key **************/
    public static final String SESSION_KEY_LOGIN_USER_NAME = "LOGIN_USER_NAME";//用户名
    public static final String SESSION_KEY_LOGIN_USER_ID  = "LOGIN_USER_ID";//用户Id
    public static final String SESSION_KEY_LOGIN_USER_LOGINNAME  = "LOGIN_USER_LOGINNAME";//用户登录名
    public static final String SESSION_KEY_LOGIN_USER  = "LOGIN_USER";//?????未知
    public static final String SESSION_KEY_LOGIN_USER_ROLE  = "LOGIN_USER_ROLE";//???未知
    public static final String SESSION_KEY_LOGIN_IS_ADMIN = "LOGIN_IS_ADMIN";//是否管理员
    
    
    public static final String SESSION_KEY_CURRENT_MERCHANT_ID = "CURRENT_MERCHANT_ID";//当前商家id
    public static final String SESSION_KEY_CURRENT_MERCHANT_CODE = "CURRENT_MERCHANT_CODE";//当前商家编码
    public static final String SESSION_KEY_CURRENT_LogoUrl  = "CURRENT_LogoUrl";//商家logo地址
    public static final String SESSION_KEY_CURRENT_MERCHANT_NAME  = "CURRENT_MERCHANT_NAME";//当前商家名称
    public static final String SESSION_KEY_CURRENT_THEME = "CURRENT_THEME";//当前主题
    public static final String SESSION_KEY_JR_BIND_STATUS  = "JR_BIND_STATUS";//绑定状态
    public static final String SESSION_KEY_JR_ROLE_LIST  = "JR_ROLE_LIST";//金服用户角色列表
    public static final String SESSION_KEY_JR_LOGIN_UESRNAME  = "JR_LOGIN_UESRNAME";//金服用户登录名
    public static final String SESSION_KEY_JR_USERID  = "JR_USERID";//金服机构id标示
    public static final String SESSION_KEY_JR_TFS_USERID  = "JR_TFS_USERID";//金服用户名id
    public static final String SESSION_KEY_JR_RESOURCE  = "JR_RESOURCE";//此时标示是从金服官网登录
    
    
    
    public static final String SESSION_KEY_JR_RESOURCE_1_TFS  = "1";//金服官网登录
    public static final String SESSION_KEY_JR_RESOURCE_2_SAAS  = "2";//SAAS网站登录
    public static final String SESSION_KEY_JR_RESOURCE_3_ADMIN  = "3";//金服后台管理登录
    
    public static final String SESSION_KEY_REG_CODE  = "REG_CODE";//注册时的验证码
   
    
    
    
    
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
    public static final String ACCOUNT_PUBLIC_FAIL="5";
    
    //对公的账户,绑定成功
   	public static final String ACCOUNT_PUBLIC_SUCCESS ="4";
   	
   	//对公账户未绑定
   	public static final String ACCOUNT_PUBLIC_NO_BIND="3";
   	
   	//对公账户审核中
   	public static final String ACCOUNT_PUBLIC_BINDING="6";
   	
       
	//提现卡
	public static final String ACCOUNT_PURPOSE_WITHDRAW ="3";
	
	//绑定成功
	public static final Integer BANKCARD_SUCCESS = 1;
	
	public static final Integer BANKCARD_BINDING = 2;
	
	public static final Integer BANKCARD_FAIL = 0;
	
	//绑定提现卡
	public static final String BIND_BANK_CARD="0";
	
	//绑定失败后修改提现卡
	public static final String MODIFY_BANK_CARD="1";
	
    
}
