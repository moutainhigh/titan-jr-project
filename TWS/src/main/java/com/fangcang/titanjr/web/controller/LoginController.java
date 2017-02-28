package com.fangcang.titanjr.web.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fangcang.titanjr.common.enums.SMSType;
import com.fangcang.titanjr.common.enums.entity.TitanUserEnum;
import com.fangcang.titanjr.common.exception.GlobalServiceException;
import com.fangcang.titanjr.common.util.CommonConstant;
import com.fangcang.titanjr.common.util.MD5;
import com.fangcang.titanjr.common.util.Tools;
import com.fangcang.titanjr.common.util.rsa.RSAUtil;
import com.fangcang.titanjr.dto.bean.CoopDTO;
import com.fangcang.titanjr.dto.bean.RoleDTO;
import com.fangcang.titanjr.dto.bean.TitanUserBindInfoDTO;
import com.fangcang.titanjr.dto.bean.UserInfoDTO;
import com.fangcang.titanjr.dto.request.CheckUserRequest;
import com.fangcang.titanjr.dto.request.CoopRequest;
import com.fangcang.titanjr.dto.request.GetCheckCodeRequest;
import com.fangcang.titanjr.dto.request.PassLoginRequest;
import com.fangcang.titanjr.dto.request.SendCodeRequest;
import com.fangcang.titanjr.dto.request.SmsLoginRequest;
import com.fangcang.titanjr.dto.request.UserInfoQueryRequest;
import com.fangcang.titanjr.dto.response.CheckUserResponse;
import com.fangcang.titanjr.dto.response.CoopResponse;
import com.fangcang.titanjr.dto.response.GetCheckCodeResponse;
import com.fangcang.titanjr.dto.response.PassLoginResponse;
import com.fangcang.titanjr.dto.response.SendCodeResponse;
import com.fangcang.titanjr.dto.response.SmsLoginResponse;
import com.fangcang.titanjr.dto.response.UserInfoPageResponse;
import com.fangcang.titanjr.dto.response.UserInfoResponse;
import com.fangcang.titanjr.entity.TitanUser;
import com.fangcang.titanjr.service.TitanFinancialBaseInfoService;
import com.fangcang.titanjr.service.TitanFinancialOrganService;
import com.fangcang.titanjr.service.TitanFinancialSendSMSService;
import com.fangcang.titanjr.service.TitanFinancialUserService;
import com.fangcang.titanjr.web.annotation.AccessPermission;
import com.fangcang.titanjr.web.pojo.LoginPojo;
import com.fangcang.titanjr.web.pojo.ProxyLoginPojo;
import com.fangcang.titanjr.web.util.LoginUtil;
import com.fangcang.titanjr.web.util.WebConstant;
import com.fangcang.util.StringUtil;

/**
 * 登录,不需要登录就可以访问的controller
 * @author luoqinglong
 * @date   2016年12月23日
 */
@Controller
@RequestMapping("/ex")
public class LoginController extends BaseController{
	private static final Log LOGGER = LogFactory.getLog(LoginController.class);
	
	@Resource
	private TitanFinancialUserService userService;
	
	@Resource
    private TitanFinancialOrganService organService;
	
    @Resource
    private TitanFinancialSendSMSService sendSMSService;
    
    @Resource
    private TitanFinancialBaseInfoService baseInfoService;
    
	/**
	 * 登录页
	 * @return
	 */
	@RequestMapping(value = "/login")
	@AccessPermission(allowRoleCode={CommonConstant.ROLECODE_NO_LIMIT})
	public String login(String returnUrl,Model model){
		model.addAttribute("returnUrl", returnUrl);
		return "login";
	}
	
	@RequestMapping(value = "/test")
	@AccessPermission(allowRoleCode={CommonConstant.ROLECODE_NO_LIMIT})
	public String test(String returnUrl,Model model){
		try {
			if(returnUrl==null||returnUrl.length()==0){
				getResponse().sendRedirect("http://baidu.com");
				return null;
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "error";
	}
	
	/**
	 * 密码登录
	 * @return
	 */
	@RequestMapping(value = "/passlogin")
	@ResponseBody
	@AccessPermission(allowRoleCode={CommonConstant.ROLECODE_NO_LIMIT})
	public String passlogin(LoginPojo login){
		if(!StringUtil.isValidString(login.getLoginUserName())){
			return toJson(putSysError("登录用户名不能为空"));
		}
		if(!StringUtil.isValidString(login.getPassword())){
			return toJson(putSysError("登录密码不能为空"));
		}
		
		//校验用户名和密码
		PassLoginRequest passLoginRequest = new PassLoginRequest();
		passLoginRequest.setLoginUsername(login.getLoginUserName());
		passLoginRequest.setPassword(login.getPassword());
		try {
			PassLoginResponse passLoginResponse = userService.passLogin(passLoginRequest);
			if(passLoginResponse.isResult()){
				//保存登录表示到session
				LoginUtil.putLoginInfo(getSession(), userService, passLoginResponse.getUserId(),passLoginResponse.getUserLoginName(),passLoginResponse.getTfsuserId().toString());
				return checkUser();
			}else{
				putSysError("用户名或者密码错误");
				return toJson();
			}
		} catch (GlobalServiceException e) {
			LOGGER.error("用户登录失败(密码登录)，登录参数："+Tools.gsonToString(login), e);
			putSysError("登录异常，请重试");
			return toJson();
		}
	}
	
	/**
	 * 检查用户状态
	 * @return
	 * @throws GlobalServiceException
	 */
	private String checkUser() throws GlobalServiceException{
		//检查用户状态
		String tfsUserId = (String)getSession().getAttribute(WebConstant.SESSION_KEY_JR_TFS_USERID);
		CheckUserRequest checkUserRequest = new CheckUserRequest();
		checkUserRequest.setTfsUserId(Integer.valueOf(tfsUserId));
		CheckUserResponse checkUserResponse = userService.checkUser(checkUserRequest);
		if(checkUserResponse.isResult()){
			putSuccess("登录成功");
			return toJson();
		}else{
			//账号异常
			String returnUrl = getRequest().getScheme()+"://"+getRequest().getServerName()+":"+getRequest().getServerPort()+getRequest().getContextPath()+"/reg/user-state.shtml";
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("returnUrl", returnUrl);
			putSysError(checkUserResponse.getReturnMessage(), data);
			return toJson();
		}
	}
	/**
	 * 动态码登录
	 * @return
	 */
	@RequestMapping(value = "/smslogin")
	@ResponseBody
	@AccessPermission(allowRoleCode={CommonConstant.ROLECODE_NO_LIMIT})
	public String smslogin(LoginPojo login){
		if(!StringUtil.isValidString(login.getLoginUserName())){
			return toJson(putSysError("登录用户名不能为空"));
		}
		if(!StringUtil.isValidString(login.getPassword())){
			return toJson(putSysError("校验码不能为空"));
		}
		SmsLoginRequest smsLoginRequest = new SmsLoginRequest();
		smsLoginRequest.setLoginUsername(login.getLoginUserName());
		smsLoginRequest.setSmsCode(login.getPassword());
		try {
			SmsLoginResponse smsLoginResponse = userService.smsLogin(smsLoginRequest);
			if(smsLoginResponse.isResult()){
				putSuccess("登录成功");
				//保存登录表示到session
				LoginUtil.putLoginInfo(getSession(), userService,smsLoginResponse.getUserId(),smsLoginResponse.getUserLoginName(),smsLoginResponse.getTfsuserId().toString());
				return checkUser();
			}else{
				putSysError("用户名或者验证码错误");
				return toJson();
			}
		} catch (GlobalServiceException e) {
			LOGGER.error("用户登录失败(动态码登录)，登录参数："+Tools.gsonToString(login), e);
			putSysError("登录异常，请重试");
			return toJson();
		}
	}
	/***
	 * 用户登录状态和登录信息
	 * @return
	 */
	@RequestMapping(value = "/loginService", produces = "text/html;charset=UTF-8")
	@ResponseBody
	@AccessPermission(allowRoleCode={CommonConstant.ROLECODE_NO_LIMIT})
	public String loginService(){
		Map<String, Object> dataMap = new HashMap<String, Object>();
		String tfsUserId = (String)getSession().getAttribute(WebConstant.SESSION_KEY_JR_TFS_USERID);
		String loginusername = (String)getSession().getAttribute(WebConstant.SESSION_KEY_JR_LOGIN_UESRNAME);
		if(StringUtil.isValidString(tfsUserId)){
			dataMap.put("tfsuserId", tfsUserId);
			dataMap.put("loginusername", loginusername);
			dataMap.put("islogin", "1");
			putSuccess("已经登录", dataMap);
		}else{
			
			dataMap.put("islogin", "0");
			putSysError("未登录",dataMap);
		}
		
		return toJson();
	}
	/**
	 * 退出
	 * @return
	 */
	@RequestMapping(value = "/loginout", produces = "text/html;charset=UTF-8")
	@AccessPermission(allowRoleCode={CommonConstant.ROLECODE_NO_LIMIT})
	public String loginout(){
		//清理资源
		getSession().removeAttribute(WebConstant.SESSION_KEY_JR_TFS_USERID);
		getSession().removeAttribute(WebConstant.SESSION_KEY_JR_LOGIN_UESRNAME);
		
		return "redirect:/ex/login.shtml"; 
	}
	/***
	 * 发送验证码
	 * @param receiveAddress
	 * @param msgType
	 * @return
	 */
	@ResponseBody
   	@RequestMapping(value = "/sendCode")
    @AccessPermission(allowRoleCode={CommonConstant.ROLECODE_NO_LIMIT})
    public String sendCode(String receiveAddress,Integer msgType){
    	SendCodeRequest sendRegCodeRequest = new SendCodeRequest();
    	if(StringUtil.isValidString(receiveAddress)){
    		sendRegCodeRequest.setReceiveAddress(receiveAddress);
    	}else {
    		return toJson(putSysError("参数错误"));
    	}
    	if(!(Tools.isEmailAddress(receiveAddress)||Tools.isPhone(receiveAddress))){
    		return toJson(putSysError("手机号码或者邮箱地址格式不正确"));
    	}
    	sendRegCodeRequest.setMerchantCode(CommonConstant.FANGCANG_MERCHANTCODE);
    	msgType = msgType==null?SMSType.REG_CODE.getType():msgType;
    	GetCheckCodeRequest getCheckCodeRequest = new GetCheckCodeRequest();
    	getCheckCodeRequest.setMsgType(msgType);
    	getCheckCodeRequest.setReceiveAddress(receiveAddress);
    	String regCode;
    	try {
			GetCheckCodeResponse getCheckCodeResponse = organService.getCheckCode(getCheckCodeRequest);
			if(getCheckCodeResponse.isResult()){
				regCode = getCheckCodeResponse.getCheckCode();
			}else{
				return toJson(putSysError(getCheckCodeResponse.getReturnMessage()));
			}
		} catch (GlobalServiceException e) {
			LOGGER.error("send code fail ,param|receiveAddress:"+receiveAddress+",msgType:"+msgType, e);
			return toJson(putSysError("验证码获取失败"));
		}
    	
    	if(msgType==SMSType.REG_CODE.getType()){//注册
    		sendRegCodeRequest.setContent("尊敬的用户： 您正在申请开通泰坦金融服务，验证码为："+regCode+"，验证码"+WebConstant.REG_CODE_TIME_OUT_HOUR+"小时内有效。如不是您申请，请勿将验证码发给其他人。");
        	sendRegCodeRequest.setSubject("泰坦金融注册验证码");
    	}else if(msgType==SMSType.PAY_PASSWORD_MODIFY.getType()){//修改付款密码
    		sendRegCodeRequest.setContent("尊敬的用户： 您正在修改泰坦金融的付款密码，验证码为："+regCode+"，验证码"+WebConstant.REG_CODE_TIME_OUT_HOUR+"小时内有效。请勿将验证码发给其他人。");
        	sendRegCodeRequest.setSubject("泰坦金融修改付款密码");
    	}else if(msgType==SMSType.LOGIN_PASSWORD_MODIFY.getType()){//修改登录密码
    		sendRegCodeRequest.setContent("尊敬的用户： 您正在修改泰坦金融的登录密码，验证码为："+regCode+"，验证码"+WebConstant.REG_CODE_TIME_OUT_HOUR+"小时内有效。请勿将验证码发给其他人。");
        	sendRegCodeRequest.setSubject("泰坦金融修改登录密码");
    	}else if(msgType==SMSType.LOGIN_BY_CODE.getType()){//动态码登录
    		sendRegCodeRequest.setContent("尊敬的用户： 您正在使用动态验证码登录泰坦金融，验证码为："+regCode+"，验证码"+WebConstant.REG_CODE_TIME_OUT_HOUR+"小时内有效。请勿将验证码发给其他人。");
        	sendRegCodeRequest.setSubject("动态验证码登录");
    	}
    	
    	SendCodeResponse sendRegCodeResponse = sendSMSService.sendCode(sendRegCodeRequest);
    	if(sendRegCodeResponse.isResult()){
    		return toJson(putSuccess("验证码发送成功"));
    	}else{
    		return toJson(putSysError(sendRegCodeResponse.getReturnMessage()));
    	}
    	
	}
	/***
	 * 忘记登录密码页面
	 * @return
	 */
   	@RequestMapping(value = "/u-login-pwd-forget")
    @AccessPermission(allowRoleCode={CommonConstant.ROLECODE_NO_LIMIT})
	public String uLoginPwdForget(){
   		
   		
		return  "user/u-login-pwd-forget";
	}
   	/***
   	 * 代理登录
   	 * @return
   	 */
   	@RequestMapping(value = "/proxyLogin")
    @AccessPermission(allowRoleCode={CommonConstant.ROLECODE_NO_LIMIT})
   	public String proxyLogin(ProxyLoginPojo proxyLogin,Model model){
   		//校验参数
   		if(isBlank(proxyLogin.getChannel())||isBlank(proxyLogin.getInfo())||isBlank(proxyLogin.getReqtime())||isBlank(proxyLogin.getSign())){
   			//跳到登录界面
			return "redirect:/ex/login.shtml";
   		}
   		Long reqTime = NumberUtils.toLong(proxyLogin.getReqtime());
   		Date now = new Date();
   		long dateDiff = Math.abs(now.getTime()-reqTime);
   		
   		if(reqTime==0||dateDiff>WebConstant.PROXY_LOGIN_LINK_EXPIRE_TIME){
   			model.addAttribute("errormsg", "代理登录链接已经失效");
   			return "error";
   		}
   		
   		CoopRequest coopRequest = new CoopRequest();
    	coopRequest.setMixcode(proxyLogin.getChannel());
		CoopResponse coopResponse = baseInfoService.getOneCoop(coopRequest);
		if(coopResponse.isResult()&&coopResponse.getCoopDTO()!=null){
			//验证签名
			CoopDTO coopDTO = coopResponse.getCoopDTO();
	    	String msg = verifyLoginSign(proxyLogin.getChannel(),proxyLogin.getEncrypt_type(),proxyLogin.getInfo(),proxyLogin.getJumpurl(),reqTime,proxyLogin.getSign(),coopDTO.getMd5Key());
	    	if(msg.equals("success")){
    			try {
    				//开始解密
    				String urlKeyValues = RSAUtil.decryptByPrivateKeyGet(proxyLogin.getInfo(), coopDTO.getPrivateKey());
    				Map<String, String> result =  Tools.unserializable2Map(urlKeyValues);
    				String tfsuserid = result.get("tfsuserid");
    				String orgcode = result.get("orgcode");
    				if(!StringUtil.isValidString(tfsuserid)){
    					model.addAttribute("errormsg", "无效用户无法登陆");
    		   			return "error";
    				}
    				
    				TitanUserBindInfoDTO paramBindInfo = new TitanUserBindInfoDTO();
    				paramBindInfo.setTfsuserid(NumberUtils.toInt(tfsuserid));
    				paramBindInfo.setCooptype(coopDTO.getCoopType());
    				TitanUserBindInfoDTO userBindInfoDTO = userService.getUserBindInfoByFcuserid(paramBindInfo);
    				
    				if(userBindInfoDTO==null){
    					model.addAttribute("errormsg", "无效用户无法登陆，请联系管理员");
    					LOGGER.error("问题：代理登录时，无效用户无法登陆，原因：用户id的绑定关系不存在,登录参数：tfsuserid|"+tfsuserid+",orgcode|"+orgcode+",cooptype|"+coopDTO.getCoopType());
    		   			return "error";
    				}
    				UserInfoQueryRequest userInfoQueryRequest = new UserInfoQueryRequest();
    				userInfoQueryRequest.setTfsUserId(NumberUtils.toInt(tfsuserid));
    				userInfoQueryRequest.setOrgCode(orgcode);
    				UserInfoPageResponse userInfoPageResponse = userService.queryUserInfoPage(userInfoQueryRequest);
    				TitanUser titanUser = userInfoPageResponse.getTitanUserPaginationSupport().getItemList().get(0);
    				if(titanUser==null){
    					model.addAttribute("errormsg", "无效用户无法登陆，请联系管理员");
    					LOGGER.error("问题：代理登录时，无效用户无法登陆，原因：用户不存在,登录参数：tfsuserid|"+tfsuserid+",orgcode|"+orgcode+",cooptype|"+coopDTO.getCoopType());
    		   			return "error";
    				}
    				//校验用户id是否存在,且为管理员
    				if(titanUser.getStatus()==TitanUserEnum.Status.AVAILABLE.getKey()){
    					if(titanUser.getIsadmin()==1){
    						//机构id和用户id关联关系正确，可以登录
    						LoginUtil.putLoginInfo(getSession(), userService,titanUser.getOrgcode(), titanUser.getUserloginname(), titanUser.getTfsuserid().toString());
    						
    						String jumpUrl = "";
    						if(!StringUtil.isValidString(proxyLogin.getJumpurl())){
    							jumpUrl = getRequest().getScheme()+"://"+getRequest().getServerName()+":"+getRequest().getServerPort()+getRequest().getContextPath();
    						}else{
    							jumpUrl = URLDecoder.decode(proxyLogin.getJumpurl(),"UTF-8");
    						}
							getResponse().sendRedirect(jumpUrl);
							return null;
    					}else{
    						//跳到登录界面
    						return "redirect:/ex/login.shtml"; 
    					}
    				}else{
    					model.addAttribute("errormsg", "账号状态异常无法登陆，请联系管理员");
    					LOGGER.error("问题：代理登录时，账号状态异常无法登陆,登录参数：tfsuserid|"+tfsuserid+",orgcode|"+orgcode+",cooptype|"+coopDTO.getCoopType()+",titanUser status :"+titanUser.getStatus());
    		   			return "error";
    				}
    				
    			} catch (Exception e) {
    				LOGGER.error("代理登录失败,注册信息RSA解密失败，加密信息info为:"+proxyLogin.getInfo()+",第三方为:"+coopResponse.getCoopDTO(), e); 
    			}
	    	}else{
	    		model.addAttribute("errormsg", "必填参数不能为空");
	   			return "error";
	    	}
		}else{
			LOGGER.error("代理登录失败，没有找到对应的合作渠道号,渠道号【"+proxyLogin.getChannel()+"】不存在");
			model.addAttribute("errormsg", "渠道号错误");
			return "redirect:/ex/login.shtml"; 
		}
   		
		return "redirect:/ex/login.shtml"; 
   	}
   	/**
   	 * 验证登录签名
   	 * @param channel
   	 * @param sign
   	 * @param info
   	 * @param encryptType
   	 * @param key
   	 * @return
   	 */
    private String verifyLoginSign(String channel,String encryptType,String info,String jumpUrl,Long reqTime,String sign,String key){
    	if(StringUtil.isValidString(channel)&&StringUtil.isValidString(sign)&&StringUtil.isValidString(info)){
//    		StringBuilder keyValue = new StringBuilder();
//    		keyValue.append("channel=").append(channel);
//    		keyValue.append("&encrypt_type=").append(encryptType);
//    		keyValue.append("&info=").append(info);
//    		if(!isBlank(jumpUrl)){
//    			try {
//					keyValue.append("&jumpurl=").append(URLEncoder.encode(jumpUrl, "UTF-8"));
//				} catch (UnsupportedEncodingException e) {
//					e.printStackTrace();
//				}
//    		}
//    		keyValue.append("&reqtime=").append(reqTime);
//    		keyValue.append("&key=").append(key);
//    		,,,
    		Map<String, String> signParam = new HashMap<String, String>();
    		signParam.put("channel", channel);
    		signParam.put("encrypt_type", encryptType);
    		signParam.put("info", info);
    		if(!isBlank(jumpUrl)){
    			try {
    				signParam.put("jumpurl", URLEncoder.encode(jumpUrl, "UTF-8"));
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
    		}
    		signParam.put("reqtime", reqTime.toString());
    		String keyValue = MD5.generatorSignParam(signParam, key);
    		
    		String paramSign = MD5.MD5Encode(keyValue).toUpperCase();
    		if(paramSign.equals(sign)){
    			//签名正确，第三方注册
    			return "success";
    		}else{
    			//签名错误
    			LOGGER.info("代理登录参数非法，原始信息："+keyValue.toString()+",paramSign:"+paramSign+",md5sign:"+sign);
    			return "fail";
    		}
    	}
    	//参数不完整不需要校验，默认注册
    	return "ok";
    }
   	private boolean isBlank(String origin){
   		if(origin==null||origin.length()==0){
   			return true;
   		}
   		return false;
   	}
   	
}
