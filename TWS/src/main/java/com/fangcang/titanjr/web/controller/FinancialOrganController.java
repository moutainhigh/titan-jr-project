package com.fangcang.titanjr.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONSerializer;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fangcang.titanjr.common.enums.OrgCheckResultEnum;
import com.fangcang.titanjr.common.enums.SMSType;
import com.fangcang.titanjr.common.enums.UserSourceEnum;
import com.fangcang.titanjr.common.enums.entity.TitanOrgEnum;
import com.fangcang.titanjr.common.enums.entity.TitanUserEnum;
import com.fangcang.titanjr.common.exception.GlobalServiceException;
import com.fangcang.titanjr.common.exception.MessageServiceException;
import com.fangcang.titanjr.common.util.CommonConstant;
import com.fangcang.titanjr.common.util.DateUtil;
import com.fangcang.titanjr.common.util.FtpUtil;
import com.fangcang.titanjr.common.util.HttpUtils;
import com.fangcang.titanjr.common.util.ImageIOExtUtil;
import com.fangcang.titanjr.common.util.MD5;
import com.fangcang.titanjr.common.util.Tools;
import com.fangcang.titanjr.common.util.rsa.RSAUtil;
import com.fangcang.titanjr.dto.bean.CoopDTO;
import com.fangcang.titanjr.dto.bean.OrgBindInfo;
import com.fangcang.titanjr.dto.bean.OrgCheckDTO;
import com.fangcang.titanjr.dto.bean.OrgDTO;
import com.fangcang.titanjr.dto.bean.OrgImageInfo;
import com.fangcang.titanjr.dto.bean.UserInfoDTO;
import com.fangcang.titanjr.dto.request.CheckUserRequest;
import com.fangcang.titanjr.dto.request.CoopRequest;
import com.fangcang.titanjr.dto.request.FinancialOrganQueryRequest;
import com.fangcang.titanjr.dto.request.GetCheckCodeRequest;
import com.fangcang.titanjr.dto.request.OrgRegisterValidateRequest;
import com.fangcang.titanjr.dto.request.OrganBindRequest;
import com.fangcang.titanjr.dto.request.OrganImageRequest;
import com.fangcang.titanjr.dto.request.OrganImageUploadRequest;
import com.fangcang.titanjr.dto.request.OrganRegisterRequest;
import com.fangcang.titanjr.dto.request.OrganRegisterUpdateRequest;
import com.fangcang.titanjr.dto.request.SendCodeRequest;
import com.fangcang.titanjr.dto.request.UpdateCheckCodeRequest;
import com.fangcang.titanjr.dto.request.UserInfoQueryRequest;
import com.fangcang.titanjr.dto.request.UserLoginNameExistRequest;
import com.fangcang.titanjr.dto.request.VerifyCheckCodeRequest;
import com.fangcang.titanjr.dto.response.CheckUserResponse;
import com.fangcang.titanjr.dto.response.CoopResponse;
import com.fangcang.titanjr.dto.response.FinancialOrganResponse;
import com.fangcang.titanjr.dto.response.GetCheckCodeResponse;
import com.fangcang.titanjr.dto.response.OrgRegisterValidateResponse;
import com.fangcang.titanjr.dto.response.OrganBindResponse;
import com.fangcang.titanjr.dto.response.OrganImageResponse;
import com.fangcang.titanjr.dto.response.OrganImageUploadResponse;
import com.fangcang.titanjr.dto.response.OrganQueryCheckResponse;
import com.fangcang.titanjr.dto.response.OrganRegisterResponse;
import com.fangcang.titanjr.dto.response.OrganRegisterUpdateResponse;
import com.fangcang.titanjr.dto.response.SendCodeResponse;
import com.fangcang.titanjr.dto.response.UserInfoPageResponse;
import com.fangcang.titanjr.dto.response.UserInfoResponse;
import com.fangcang.titanjr.dto.response.UserLoginNameExistResponse;
import com.fangcang.titanjr.dto.response.VerifyCheckCodeResponse;
import com.fangcang.titanjr.entity.TitanUser;
import com.fangcang.titanjr.service.TitanFinancialBaseInfoService;
import com.fangcang.titanjr.service.TitanFinancialOrganService;
import com.fangcang.titanjr.service.TitanFinancialSendSMSService;
import com.fangcang.titanjr.service.TitanFinancialUserService;
import com.fangcang.titanjr.web.annotation.AccessPermission;
import com.fangcang.titanjr.web.pojo.CoopRegInfo;
import com.fangcang.titanjr.web.pojo.OrgRegPojo;
import com.fangcang.titanjr.web.pojo.RegUserLoginInfo;
import com.fangcang.titanjr.web.util.LoginUtil;
import com.fangcang.titanjr.web.util.WebConstant;
import com.fangcang.util.PasswordUtil;
import com.fangcang.util.StringUtil;

/**
 * 泰坦金融机构controller，目的用于后续机构申请提交，查询基础设置等
 * 修改访问路径，每个controller加前置的固定路径
 * 简化controller命名和前缀
 * Created by zhaoshan on 2016/3/30.
 */
@Controller
public class FinancialOrganController extends BaseController {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Log log = LogFactory.getLog(FinancialOrganController.class);

    @Autowired
    private TitanFinancialOrganService titanFinancialOrganService;
    
    @Autowired
    private TitanFinancialUserService titanFinancialUserService;
    
    @Resource
    private TitanFinancialSendSMSService sendSMSService;
    
    @Resource
    private TitanFinancialBaseInfoService baseInfoService;
    
    /**
     * 显示注册登录信息页面
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/ex/organ/showOrgUser")
    @AccessPermission(allowRoleCode={CommonConstant.ROLECODE_NO_LIMIT})
    public String showOrgUser(HttpServletRequest request, Model model) {
    	//校验sign签名
    	model.addAttribute("channel", request.getParameter("channel"));
    	model.addAttribute("sign", request.getParameter("sign"));
    	model.addAttribute("info", request.getParameter("info"));
    	model.addAttribute("encrypt_type", request.getParameter("encrypt_type"));
    	RegUserLoginInfo regUserLoginInfo = new RegUserLoginInfo();
    	regUserLoginInfo.setChannel(request.getParameter("channel"));
    	regUserLoginInfo.setEncrypt_type(request.getParameter("encrypt_type"));
    	regUserLoginInfo.setSign(request.getParameter("sign"));
    	regUserLoginInfo.setInfo(request.getParameter("info"));
        return "org-reg/org-user";
    }
    
    /**
     * 校验第三方注册接口签名
     */
    private String verifyRegSign(String channel,String sign,String info,String encryptType,String key){
    	if(StringUtil.isValidString(channel)&&StringUtil.isValidString(sign)&&StringUtil.isValidString(info)){
    		String keyValue = "channel="+channel+"&encrypt_type="+encryptType+"&info="+info+"&key="+key;
    		String paramSign = MD5.MD5Encode(keyValue).toUpperCase();
    		if(paramSign.equals(sign)){
    			//签名正确，第三方注册
    			return "success";
    		}else{
    			//签名错误
    			log.info("第三方注册参数非法，原始信息："+keyValue+",paramSign:"+paramSign+",md5sign:"+sign);
    			return "fail";
    		}
    	}
    	//参数不完整不需要校验，默认注册
    	return "ok";
    }
    
    /**
     * 注册时，检查注册名是否重复
     * @param userLoginName
     * @return
     */
    @ResponseBody
	@RequestMapping(value = "/ex/organ/checkUserLoginName")
    @AccessPermission(allowRoleCode={CommonConstant.ROLECODE_NO_LIMIT})
	public String checkUserLoginName(String userLoginName,int isOperator)
	{
    	UserLoginNameExistRequest request = new UserLoginNameExistRequest();
    	UserLoginNameExistResponse response;
		try {
			request.setUserLoginName(userLoginName);
			request.setIsOperator(isOperator);
			response = titanFinancialUserService.userLoginNameExist(request);
			if("1".equals(response.getReturnCode())){
				putSuccess();
			}else if("-100".equals(response.getReturnCode())){
				putSysError("帐号已存在");
			}else{
				putSysError(response.getReturnMessage());
			}
		} catch (GlobalServiceException e) {
			log.error("检查注册名是否重复失败,userLoginName:"+userLoginName, e); 
			putSysError();
		}
		return toJson();
	}
    /**
     * 注册时校验的营业证号和身份证号是否已经注册
     * @param usertype
     * @param buslince
     * @param certificateNumber
     * @return
     */
    @ResponseBody
	@RequestMapping(value = "/ex/organ/checkOrgRegNum")
    @AccessPermission(allowRoleCode={CommonConstant.ROLECODE_NO_LIMIT})
    public String checkOrgRegNum(int userType,String buslince,String certificateNumber){
    	try {
			int code = checkRegInfo(userType, getUserId(), buslince, certificateNumber);
			if(code==1){
				putSuccess("证件可以注册");
			}else if(code == -1){
				 putSysError("证件已注册");
			}else if(code == -2){
				 putSysError("参数错误");
			}
			
		} catch (MessageServiceException e1) {
			putSysError(e1.getMessage());
		} catch (Exception e) {
    		putSysError("系统错误");
			log.error("机构注册校验失败，userType:"+userType+",orgCode:"+getUserId()+",buslince:"+ buslince+",certificateNumber:"+ certificateNumber, e);
		}
    	return toJson();
    }
    /***
     * 检查注册用的证件号码是否已经注册过(包括新建和修改证件号码)
     * @return
     * @throws MessageServiceException 
     */
    private int checkRegInfo(int userType,String orgCode,String buslince,String certificateNumber) throws MessageServiceException{
    	OrgRegisterValidateRequest orgRegisterValidateRequest  = new OrgRegisterValidateRequest();
    	orgRegisterValidateRequest.setUsertype(userType);
    	orgRegisterValidateRequest.setBuslince(buslince);
    	orgRegisterValidateRequest.setCertificatetype(TitanOrgEnum.CertificateType.SFZ.getKey()+"");//身份证
    	orgRegisterValidateRequest.setCertificateNumber(certificateNumber);
		OrgRegisterValidateResponse orgRegisterValidateResponse = titanFinancialOrganService.validateOrg(orgRegisterValidateRequest);
		if(orgRegisterValidateResponse.isResult()){
			//判断机构注册证件的编号和登录者是不是同一个机构
			if(orgRegisterValidateResponse.getOrgDTO()!=null){//如果所填写的证件编码已经存在
				if(StringUtil.isValidString(orgCode)){
					String tfsUserIdStr = (String)getSession().getAttribute(WebConstant.SESSION_KEY_JR_TFS_USERID);//金服用户名
					//修改
					if(StringUtil.isValidString(tfsUserIdStr)){
    					OrgDTO orgDTO = orgRegisterValidateResponse.getOrgDTO();
        				UserInfoQueryRequest userInfoQueryRequest = new UserInfoQueryRequest();
            			userInfoQueryRequest.setTfsUserId(Integer.valueOf(tfsUserIdStr));
            			userInfoQueryRequest.setStatus(TitanUserEnum.Status.AVAILABLE.getKey());
            			
            			UserInfoPageResponse userInfoResponse = titanFinancialUserService.queryUserInfoPage(userInfoQueryRequest);
            			List<TitanUser> list = userInfoResponse.getTitanUserPaginationSupport().getItemList();
            			if(CollectionUtils.isEmpty(list)){
            				return 1;
            			}
            			String userOrgCode = list.get(0).getOrgcode();
            			if(userOrgCode.equals(orgDTO.getOrgcode())){
            				//是登录者所属本机构,该证件可以注册
            				return 1;
            			}else{
            				//不属于,该证件已经注册
            				return -1;
            			}
    				}else{
    					return -2;//参数错误
    				}
				}else{//新增
					return -1;
				}
			}else{
				return 1;
			}
		}else{
			throw new MessageServiceException(orgRegisterValidateResponse.getReturnMessage());
		}
    	
    }
    /**
     * 注册
     * @return
     */
    @RequestMapping(value = "/ex/organ/regOrg")
    @AccessPermission(allowRoleCode={CommonConstant.ROLECODE_NO_LIMIT})
    public String regOrg(RegUserLoginInfo regUserLoginInfo,OrgRegPojo orgRegPojo,Model model){
    	try {
    		//联系人验证
    		int mobiletelCodeId = 0;
    		//登录用户验证
    		int userLoginCodeId = 0;
    		log.info("机构注册，输入参数|regUserLoginInfo:"+Tools.gsonToString(regUserLoginInfo)+",orgRegPojo:"+Tools.gsonToString(orgRegPojo));
    		
    		//校验登录名和验证码
    		if((!StringUtil.isValidString(regUserLoginInfo.getUserLoginName()))||(!StringUtil.isValidString(regUserLoginInfo.getRegCode()))){
				model.addAttribute("errormsg", "参数错误，请重试");
	    		return "error";
			}
    		VerifyCheckCodeRequest userLoginVerifyCheckCodeRequest = new VerifyCheckCodeRequest();
    		userLoginVerifyCheckCodeRequest.setReceiveAddress(regUserLoginInfo.getUserLoginName());
    		userLoginVerifyCheckCodeRequest.setInputCode(regUserLoginInfo.getRegCode());
	    	VerifyCheckCodeResponse userLoginVerifyCheckCodeResponse = titanFinancialOrganService.verifyCheckCode(userLoginVerifyCheckCodeRequest);
	    	if(userLoginVerifyCheckCodeResponse.isResult()){
	    		userLoginCodeId = userLoginVerifyCheckCodeResponse.getCodeId();
	    	}else{
	    		model.addAttribute("errormsg", userLoginVerifyCheckCodeResponse.getReturnMessage());
	    		return "error";
	    	}
    		
	    	//企业用户,校验联系人和验证码
    		if(orgRegPojo.getUserType()==TitanOrgEnum.UserType.ENTERPRISE.getKey()){
    			if((!StringUtil.isValidString(orgRegPojo.getMobiletel()))||(!StringUtil.isValidString(orgRegPojo.getSmsRegCode()))){
    				model.addAttribute("errormsg", "参数错误，请重试");
    	    		return "error";
    			}
    			VerifyCheckCodeRequest verifyCheckCodeRequest = new VerifyCheckCodeRequest();
    	    	verifyCheckCodeRequest.setReceiveAddress(orgRegPojo.getMobiletel());
    	    	verifyCheckCodeRequest.setInputCode(orgRegPojo.getSmsRegCode());
    	    	VerifyCheckCodeResponse verifyCheckCodeResponse = titanFinancialOrganService.verifyCheckCode(verifyCheckCodeRequest);
    	    	if(verifyCheckCodeResponse.isResult()){
    	    		mobiletelCodeId = verifyCheckCodeResponse.getCodeId();
    	    	}else{
    	    		model.addAttribute("errormsg", verifyCheckCodeResponse.getReturnMessage());
    	    		return "error";
    	    	}
    		}
	    	
	    	//注册
	    	OrganRegisterRequest organRegisterRequest = new OrganRegisterRequest();
	    	//登录信息
	    	organRegisterRequest.setUserloginname(regUserLoginInfo.getUserLoginName());
	    	organRegisterRequest.setPassword(regUserLoginInfo.getPassword());
	    	//机构信息
	    	organRegisterRequest.setOrgName(orgRegPojo.getOrgName());
	    	organRegisterRequest.setBuslince(orgRegPojo.getBuslince());
	    	organRegisterRequest.setImageid(orgRegPojo.getImgIds());
	    	organRegisterRequest.setUserType(orgRegPojo.getUserType());
	    	organRegisterRequest.setConnect(orgRegPojo.getConnect());
	    	organRegisterRequest.setMobileTel(orgRegPojo.getMobiletel());
	    	//渠道号要用密文
	    	int registerSource = UserSourceEnum.TWS.getKey();
	    	CoopRequest coopRequest = new CoopRequest();
	    	coopRequest.setMixcode(regUserLoginInfo.getChannel());
    		CoopResponse coopResponse = baseInfoService.getOneCoop(coopRequest);
    		boolean isNeedNofiy = false;
    		if(coopResponse.isResult()&&coopResponse.getCoopDTO()!=null){
    	    	registerSource = coopResponse.getCoopDTO().getCoopType();
    	    	isNeedNofiy = true;
    		}
	    	
	    	organRegisterRequest.setRegisterSource(registerSource);
	    	//合作方信息
	    	CoopRegInfo coopRegInfo = decryptRegInfo(regUserLoginInfo);
	    	if(coopRegInfo!=null){
	    		organRegisterRequest.setFcLoginUserName(coopRegInfo.getCoopLoginName());
	    		organRegisterRequest.setCoopUserId(coopRegInfo.getCoopUserId());
	    		organRegisterRequest.setMerchantCode(coopRegInfo.getCoopOrgCode());
	    		organRegisterRequest.setMerchantname(coopRegInfo.getCoopOrgName());
	    	}
	    	
	    	if(orgRegPojo.getUserType()==TitanOrgEnum.UserType.ENTERPRISE.getKey()){
	    		//企业
	    		organRegisterRequest.setBuslince(orgRegPojo.getBuslince());
	    	}else{
	    		//个人
	    		organRegisterRequest.setCertificateNumber(orgRegPojo.getCertificatenumber());
	    		organRegisterRequest.setCertificateType(TitanOrgEnum.CertificateType.SFZ.getKey()+"");//身份证
	    	}
			OrganRegisterResponse organRegisterResponse = titanFinancialOrganService.registerFinancialOrgan(organRegisterRequest);
			if(organRegisterResponse.isResult()){
				// 成功刷新InitSessionInterceptor中的session
				refreshSession(regUserLoginInfo.getUserLoginName());
				// 删除验证码
				if(mobiletelCodeId>0){
					UpdateCheckCodeRequest updateCheckCodeRequest = new UpdateCheckCodeRequest();
					updateCheckCodeRequest.setCodeId(mobiletelCodeId);
					updateCheckCodeRequest.setIsactive(0);
					titanFinancialOrganService.useCheckCode(updateCheckCodeRequest);
				}
				if(userLoginCodeId>0){
					UpdateCheckCodeRequest updateCheckCodeRequest = new UpdateCheckCodeRequest();
					updateCheckCodeRequest.setCodeId(userLoginCodeId);
					updateCheckCodeRequest.setIsactive(0);
					titanFinancialOrganService.useCheckCode(updateCheckCodeRequest);
				}
				//通知第三方平台，机构信息
				if(isNeedNofiy){
					nofifyCoop(coopRegInfo.getCoopOrgCode(),coopRegInfo.getCoopUserId(),coopRegInfo.getNotifyurl());
				}
				sendCheckAlarm(orgRegPojo.getOrgName());
				return "/org-reg/reg-success";
			}else{
				model.addAttribute(WebConstant.MODEL_ERROR_MSG_KEY, organRegisterResponse.getReturnMessage());
				return "error";
			}
    	} catch (MessageServiceException e) {
			model.addAttribute(WebConstant.MODEL_ERROR_MSG_KEY, e.getMessage());
			log.error("机构注册失败，错误信息："+e.getMessage()+"，输入参数|regUserLoginInfo:"+JSONSerializer.toJSON(regUserLoginInfo).toString()+",orgRegPojo:"+JSONSerializer.toJSON(orgRegPojo).toString(), e);
		} catch (GlobalServiceException e) {
			model.addAttribute(WebConstant.MODEL_ERROR_MSG_KEY, WebConstant.SERVICE_ERROR_MSG);
			log.error("机构注册失败，输入参数|regUserLoginInfo:"+JSONSerializer.toJSON(regUserLoginInfo).toString()+",orgRegPojo:"+JSONSerializer.toJSON(orgRegPojo).toString(), e);
		} catch (Exception e) {
			model.addAttribute(WebConstant.MODEL_ERROR_MSG_KEY, WebConstant.CONTROLLER_ERROR_MSG);
			log.error("机构注册失败，输入参数|regUserLoginInfo:"+JSONSerializer.toJSON(regUserLoginInfo).toString()+",orgRegPojo:"+JSONSerializer.toJSON(orgRegPojo).toString(), e);
		}
    	//错误页面
    	return "error";
    }
    
    /**
     * 通知合作方注册的机构信息
     * @param coopOrgCode
     * @param notifyurl
     */
    private void nofifyCoop(String coopOrgCode,String coopUserId,String notifyurl){
    	try {
	    	String orgcode = (String) getSession().getAttribute(WebConstant.SESSION_KEY_JR_USERID).toString();
	    	String tfsUserid = (String) getSession().getAttribute(WebConstant.SESSION_KEY_JR_TFS_USERID).toString();//金服用户名
	    	
	    	Map<String, String> parameters = new HashMap<String, String>();
			parameters.put("cooporgcode", coopOrgCode);
			parameters.put("orgcode", orgcode);
			parameters.put("coopuserid", coopUserId);
			parameters.put("tfsuserid", tfsUserid);
			HttpUtils.postRequest(new URL(URLDecoder.decode(notifyurl, "UTF-8")), parameters);
		} catch (MalformedURLException e) {
			e.printStackTrace();
			log.error("通知第三方注册信息时失败。", e);
		} catch (IOException e) {
			e.printStackTrace();
			log.error("通知第三方注册信息时失败。", e);
		}catch (Exception e) {
			e.printStackTrace();
			log.error("通知第三方注册信息时失败。", e);
		}
    }
    /**
     * 解密第三方过来的参数
     * @param CoopRegInfo
     * @return
     */
    private CoopRegInfo decryptRegInfo(RegUserLoginInfo regUserLoginInfo){
    	CoopRequest coopRequest = new CoopRequest();
		coopRequest.setMixcode(regUserLoginInfo.getChannel());
		CoopResponse coopResponse = baseInfoService.getOneCoop(coopRequest);
		if(coopResponse.isResult()&&coopResponse.getCoopDTO()!=null){
			CoopDTO coopDTO = coopResponse.getCoopDTO();
	    	String msg = verifyRegSign(regUserLoginInfo.getChannel(),regUserLoginInfo.getSign(),regUserLoginInfo.getInfo(),regUserLoginInfo.getEncrypt_type(),coopDTO.getMd5Key());
	    	if(msg.equals("success")){
	    		//开始解密
    			try {
    				String urlKeyValues = RSAUtil.decryptByPrivateKeyGet(regUserLoginInfo.getInfo(), coopResponse.getCoopDTO().getPrivateKey());
    				Map<String, String> result =  Tools.unserializable2Map(urlKeyValues);
    				CoopRegInfo coopRegInfo = new CoopRegInfo();
    				coopRegInfo.setCoopOrgCode(result.get("cooporgcode"));
    				coopRegInfo.setCoopOrgName(result.get("cooporgname"));
    				coopRegInfo.setCoopLoginName(result.get("cooploginname"));
    				coopRegInfo.setCoopUserId(result.get("coopuserid"));
    				coopRegInfo.setNotifyurl(result.get("notifyurl"));
    				return coopRegInfo;
    			} catch (Exception e) {
    				log.error("第三方注册时,注册信息RSA解密失败，加密信息info为:"+regUserLoginInfo.getInfo()+",第三方为:"+coopResponse.getCoopDTO(), e); 
    			}
	    	}
    	}else{
			log.info("第三方注册时，没有找到对应的合作渠道号,渠道号【"+regUserLoginInfo.getChannel()+"】不存在");
		}
    	return null;
    	
    }
   
    
    /**
     * 注册后刷新session中的个人信息等
     */
    private void refreshSession(String userLoginName){
    	UserInfoQueryRequest userInfoQueryRequest = new UserInfoQueryRequest();
    	userInfoQueryRequest.setUserLoginName(userLoginName);//金服登录用户名
    	UserInfoResponse userInfoResponse = titanFinancialUserService.queryFinancialUser(userInfoQueryRequest);
    	if(CollectionUtils.isNotEmpty(userInfoResponse.getUserInfoDTOList())){
    		UserInfoDTO userInfoDTO = userInfoResponse.getUserInfoDTOList().get(0);
    		LoginUtil.putLoginInfo(getSession(), titanFinancialUserService,userInfoDTO.getUserId().toString(),userInfoDTO.getUserLoginName(),userInfoDTO.getTfsUserId().toString());
    	}else{
    		log.error("用户信息不存在!刷新登录用户session失败.");
		}
        
    }
    
    /***
     * 注册时修改注册信息
     * @return
     */
    @RequestMapping(value = "/reg/organ/updateOrg")
    @AccessPermission(allowRoleCode={CommonConstant.ROLECODE_NO_LIMIT})
    public String updateOrg(OrgRegPojo orgRegPojo,Model model){
    	
    	try {
	    	//1.判断验证码
	    	//企业用户
    		int codeId = 0;
			if(orgRegPojo.getUserType()==TitanOrgEnum.UserType.ENTERPRISE.getKey()){
				if((!StringUtil.isValidString(orgRegPojo.getMobiletel()))||(!StringUtil.isValidString(orgRegPojo.getSmsRegCode()))){
    				model.addAttribute("errormsg", "参数错误，请重试");
    	    		return "error";
    			}
    			VerifyCheckCodeRequest verifyCheckCodeRequest = new VerifyCheckCodeRequest();
    	    	verifyCheckCodeRequest.setReceiveAddress(orgRegPojo.getMobiletel());
    	    	verifyCheckCodeRequest.setInputCode(orgRegPojo.getSmsRegCode());
    	    	VerifyCheckCodeResponse verifyCheckCodeResponse = titanFinancialOrganService.verifyCheckCode(verifyCheckCodeRequest);
    	    	if(verifyCheckCodeResponse.isResult()){
    	    		codeId = verifyCheckCodeResponse.getCodeId();
    	    	}else{
    	    		model.addAttribute("errormsg", verifyCheckCodeResponse.getReturnMessage());
    	    		return "error";
    	    	}
			}
			//已经审核通过则不允许修改
			FinancialOrganQueryRequest organQueryRequest = new FinancialOrganQueryRequest();
			organQueryRequest.setOrgId(Integer.valueOf(orgRegPojo.getOrgId()));
			OrganQueryCheckResponse organQueryCheckResponse = titanFinancialOrganService.queryFinancialOrganForPage(organQueryRequest);
			OrgCheckDTO orgCheckDTO = organQueryCheckResponse.getPaginationSupport().getItemList().get(0);
			//判断机构审核状态，已经通过，则不允许修改
			if(OrgCheckResultEnum.PASS.getResultkey().equals(orgCheckDTO.getResultkey())){
				model.addAttribute("errormsg", "注册信息审核已经通过，不允许修改。请联系管理员");
				return "error";
			}
			
	    	//2.判断证件号是否已经注册
			int orgId = NumberUtils.toInt(orgRegPojo.getOrgId());
			int code = checkRegInfo(orgRegPojo.getUserType(), orgRegPojo.getOrgId(), orgRegPojo.getBuslince(), orgRegPojo.getCertificatenumber());
			if(code!=1&&orgId<=0){
				model.addAttribute("errormsg", "注册信息错误,请重新输入");
				return "error";
			}
			
			OrganRegisterUpdateRequest organRegisterUpdateRequest = new OrganRegisterUpdateRequest();
			//机构通用信息
			organRegisterUpdateRequest.setOrgId(orgId);
			organRegisterUpdateRequest.setOrgName(orgRegPojo.getOrgName());
			organRegisterUpdateRequest.setImageid(orgRegPojo.getImgIds());
			organRegisterUpdateRequest.setUserType(orgRegPojo.getUserType());
			//个人信息
			if(orgRegPojo.getUserType()==TitanOrgEnum.UserType.ENTERPRISE.getKey()){
				//企业信息
				organRegisterUpdateRequest.setBuslince(orgRegPojo.getBuslince());
				organRegisterUpdateRequest.setConnect(orgRegPojo.getConnect());
				organRegisterUpdateRequest.setMobileTel(orgRegPojo.getMobiletel());
			}else{
				organRegisterUpdateRequest.setCertificateType(TitanOrgEnum.CertificateType.SFZ.getKey()+"");
				organRegisterUpdateRequest.setCertificateNumber(orgRegPojo.getCertificatenumber());
			}
			
			//修改机构信息
			OrganRegisterUpdateResponse organRegisterUpdateResponse = titanFinancialOrganService.reRegisterOrgan(organRegisterUpdateRequest);
			if(organRegisterUpdateResponse.isResult()){
				// 删除验证码
				if(codeId>0){
					UpdateCheckCodeRequest updateCheckCodeRequest = new UpdateCheckCodeRequest();
					updateCheckCodeRequest.setCodeId(codeId);
					updateCheckCodeRequest.setIsactive(0);
					titanFinancialOrganService.useCheckCode(updateCheckCodeRequest);
				}
				sendCheckAlarm(orgRegPojo.getOrgName());
				return "/org-reg/reg-success";
			}else{
				model.addAttribute("errormsg", organRegisterUpdateResponse.getReturnMessage());
				return "error";
			}
		} catch (GlobalServiceException e) {
			log.error("注册时，修改机构信息失败，输入参数orgRegPojo："+JSONSerializer.toJSON(orgRegPojo).toString(), e);
			model.addAttribute("errormsg", WebConstant.CONTROLLER_ERROR_MSG);
			return "error";
		} catch (MessageServiceException e) {
			model.addAttribute("errormsg", e.getMessage());
			return "error";
		}

    }
    /**
     * 发送提醒,峰哥去审核金融开通申请（临时使用）
     */
    private void sendCheckAlarm(String orgName){
    	SendCodeRequest sendRegCodeRequest = new SendCodeRequest();
    	sendRegCodeRequest.setReceiveAddress("13543309695");//峰哥手机
    	sendRegCodeRequest.setMerchantCode(CommonConstant.FANGCANG_MERCHANTCODE);
    	sendRegCodeRequest.setContent(orgName+",正在提交金融账户开通申请，请及时审核。提交时间："+DateUtil.formatDataToDatetime(new Date()));
    	sendRegCodeRequest.setSubject("泰坦金融账号开通申请待审通知");
    	try {
    		sendSMSService.sendCode(sendRegCodeRequest);
		} catch (Exception e) {
			log.info("给运营人员发送泰坦金融账号开通申请待审通知失败", e);
		}
    	
    }
    /**
     * 发送邮件或者手机验证码(---废弃该方法)
     * @param receiveAddress
     * @return
     */
    @Deprecated
    @ResponseBody
   	@RequestMapping(value = "/ex/sendRegCode")
    public String sendRegCode(String receiveAddress){
    	SendCodeRequest sendRegCodeRequest = new SendCodeRequest();
    	if(StringUtil.isValidString(receiveAddress)){
    		sendRegCodeRequest.setReceiveAddress(receiveAddress);
    	}else {
    		return toJson(putSysError("参数错误"));
    	}
    	if(!(Tools.isEmailAddress(receiveAddress)||Tools.isPhone(receiveAddress))){
    		return toJson(putSysError("参数错误"));
    	}
    	String regCode;
    	try {
    		GetCheckCodeRequest getCheckCodeRequest = new GetCheckCodeRequest();
        	getCheckCodeRequest.setMsgType(SMSType.REG_CODE.getType());
        	getCheckCodeRequest.setReceiveAddress(receiveAddress);
			GetCheckCodeResponse getCheckCodeResponse = titanFinancialOrganService.getCheckCode(getCheckCodeRequest);
			if(getCheckCodeResponse.isResult()){
				regCode = getCheckCodeResponse.getCheckCode();
			}else{
				return toJson(putSysError(getCheckCodeResponse.getReturnMessage()));
			}
		} catch (GlobalServiceException e) {
			log.error("send reg code fail ,param|receiveAddress:"+receiveAddress, e);
			return toJson(putSysError("验证码获取失败"));
		}
		sendRegCodeRequest.setContent("尊敬的用户： 您正在申请开通泰坦金融服务，验证码为："+regCode+"，验证码"+WebConstant.REG_CODE_TIME_OUT_HOUR+"小时内有效。如不是您申请，请勿将验证码发给其他人。");
    	sendRegCodeRequest.setSubject("泰坦金融注册验证码");
    	
    	SendCodeResponse sendRegCodeResponse = sendSMSService.sendCode(sendRegCodeRequest);
    	
    	if(sendRegCodeResponse.isResult()){
    		return toJson(putSuccess("验证码发送成功"));
    	}else{
    		return toJson(putSysError(sendRegCodeResponse.getReturnMessage()));
    	}
    	
   }
    
    
    /**
     * 协议
     * @return
     */
    @RequestMapping(value = "/ex/showAgreement")
    @AccessPermission(allowRoleCode={CommonConstant.ROLECODE_NO_LIMIT})
    public String showAgreement(){
    	return "org-reg/agreement";
    }
    /**
     * 机构和用户状态检查
     * @return
     */
    @RequestMapping(value = "/reg/user-state")
    @AccessPermission(allowRoleCode={CommonConstant.ROLECODE_NO_LIMIT})
    public String userState(Model model){
    	//用户状态
		String tfsUserId = (String)getSession().getAttribute(WebConstant.SESSION_KEY_JR_TFS_USERID);
		if(!StringUtil.isValidString(tfsUserId)){
			model.addAttribute("errormsg", "会话失效，请重新登录");
			return "error";
		}
		CheckUserRequest checkUserRequest = new CheckUserRequest();
		checkUserRequest.setTfsUserId(Integer.valueOf(tfsUserId));
		try {
			CheckUserResponse checkUserResponse = titanFinancialUserService.checkUser(checkUserRequest);
			if(checkUserResponse.isResult()){
				return "redirect:/main/main.shtml";//到首页
			}else{
				model.addAttribute("code", checkUserResponse.getReturnCode());
				model.addAttribute("msg", checkUserResponse.getReturnMessage());
		    	return "user-state";
			}
			
		} catch (GlobalServiceException e) {
			e.printStackTrace();
			model.addAttribute("errormsg", "系统错误，请重试");
			return "error";
			
		}
    }
    
    @RequestMapping(value = "/reg/organ/getOrgInfo")
    @AccessPermission(allowRoleCode={CommonConstant.ROLECODE_NO_LIMIT})
    public String getOrgInfo(Model model){
    	FinancialOrganQueryRequest organQueryRequest = new FinancialOrganQueryRequest();
    	organQueryRequest.setOrgCode(getUserId());
    	FinancialOrganResponse financialOrganResponse = titanFinancialOrganService.queryBaseFinancialOrgan(organQueryRequest);
    	if(financialOrganResponse.isResult()){
    		model.addAttribute("org", financialOrganResponse.getFinancialOrganDTO());
    		if(financialOrganResponse.getFinancialOrganDTO().getOrgImageInfoList().size()>0){
    			for(OrgImageInfo item : financialOrganResponse.getFinancialOrganDTO().getOrgImageInfoList()){
    				if(item.getSizeType()==10){
    					model.addAttribute("small_img_10", item.getImageURL());
    				}else if(item.getSizeType()==50){
    					model.addAttribute("big_img_50", item.getImageURL());
    				}
    			}
    			if(financialOrganResponse.getFinancialOrganDTO().getUserType()==TitanOrgEnum.UserType.ENTERPRISE.getKey()){
    				return "org-reg/org-enterprise-info";
    			}else{
    				return "org-reg/org-persernal-info";
    			}
    		}
    		model.addAttribute("errormsg", "机构没有找到");
    	}else{
    		model.addAttribute("errormsg", "机构查询错误");
    		
    	}
    	return "error";
    }
    
   /**
    *  显示公司信息
    * @return
    */
    @RequestMapping(value = "/ex/showEnterpriseInfo")
    @AccessPermission(allowRoleCode={CommonConstant.ROLECODE_NO_LIMIT})
    public String showEnterpriseInfo(RegUserLoginInfo pojo, Model model){
    	model.addAttribute(pojo);
    	
    	model.addAttribute("regUserLoginInfo", pojo);
    	return "org-reg/org-enterprise-info";
    }
    /**
     * 显示个人信息
     * 
     */
    @RequestMapping(value = "/ex/showPersernalInfo")
    @AccessPermission(allowRoleCode={CommonConstant.ROLECODE_NO_LIMIT})
    public String showPersernalInfo(RegUserLoginInfo pojo, Model model){
    	model.addAttribute(pojo);
    	model.addAttribute("regUserLoginInfo", pojo);
    	return "org-reg/org-persernal-info";
    }
    /***
     * 检查验证码(短信或者邮箱)
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/ex/checkCode")
    @AccessPermission(allowRoleCode={CommonConstant.ROLECODE_NO_LIMIT})
    public String checkCode(String userLoginName,String code){
    	if((!StringUtil.isValidString(userLoginName))||(!StringUtil.isValidString(code))){
    		putSysError("参数错误");
    		return toJson();
    		
    	}
    	VerifyCheckCodeRequest verifyCheckCodeRequest = new VerifyCheckCodeRequest();
    	verifyCheckCodeRequest.setReceiveAddress(userLoginName);
    	verifyCheckCodeRequest.setInputCode(code);
    	VerifyCheckCodeResponse verifyCheckCodeResponse = titanFinancialOrganService.verifyCheckCode(verifyCheckCodeRequest);
    	if(verifyCheckCodeResponse.isResult()){
    		return toJson(putSuccess("验证成功"));
    	}else{
    		return toJson(putSysError(verifyCheckCodeResponse.getReturnMessage()));
    	}
    }
    
    @RequestMapping(value = "/ex/organ/upload")
    @AccessPermission(allowRoleCode={CommonConstant.ROLECODE_NO_LIMIT})
    public void upload(@RequestParam(value = "img_file", required = false) MultipartFile file,int imageType) throws IOException{
    	Map<String, Object> resultMap = new HashMap<String, Object>();
 
    	getResponse().setCharacterEncoding("utf-8");  //这里不设置编码会有乱码
    	getResponse().setContentType("text/html;charset=utf-8");
    	getResponse().setHeader("Cache-Control", "no-cache"); 
    	try {
    		if(file.getBytes().length>(WebConstant.UPLOAD_IMG_MAX_SIZE_10_M*1000*1000)){
    			putSysError("文件大小超过了"+WebConstant.UPLOAD_IMG_MAX_SIZE_10_M+"M，请压缩后再上传");
                PrintWriter out = getResponse().getWriter(); 
                out.print(toJson());
                out.flush();
                out.close();
    			return ;
    		}
    		
    		OrganImageUploadRequest organImageUploadRequest = new OrganImageUploadRequest();
    		
    		String newFileName = FtpUtil.createFileName()+"."+ImageIOExtUtil.getFileSuffix(file.getOriginalFilename());
        	organImageUploadRequest.setFileName(newFileName);
        	organImageUploadRequest.setImageType(imageType);
			organImageUploadRequest.setFileBytes(file.getBytes());
			OrganImageUploadResponse imgResponse = titanFinancialOrganService.uploadFinancialOrganImages(organImageUploadRequest);
			OrganImageRequest organImageRequest = new OrganImageRequest();
			organImageRequest.setImageid(NumberUtils.toInt(imgResponse.getImageId().split(",")[0]));
			organImageRequest.setIsactive(1);
			OrganImageResponse organImageResponse = titanFinancialOrganService.getOrganImage(organImageRequest);
			
			if(organImageResponse.getOrgImageInfoList()!=null&&organImageResponse.getOrgImageInfoList().size()>0){
				String imgUrl = organImageResponse.getOrgImageInfoList().get(0).getImageURL();
				resultMap.put("imgPreview_10", ImageIOExtUtil.addImgSize(imgUrl, 10));
				resultMap.put("imgPreview_50", ImageIOExtUtil.addImgSize(imgUrl, 50));
				resultMap.put("imgIds", imgResponse.getImageId());
			}
			putSuccess("上传成功", resultMap);
		} catch (IOException e) {
			putSysError("文件上传失败");
			log.error("上传文件失败", e);
		} catch (GlobalServiceException e) {
			putSysError("文件上传失败");
			log.error("上传文件失败", e);
		}
    	PrintWriter out = getResponse().getWriter(); 
        out.print(toJson());
        out.flush();
        out.close();
		return ;
    }
    
    /**
     * 显示商家绑定页面
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/toBindSaas")
    public String toBindSaas(HttpServletRequest request, Model model) {
		log.info("打开机构绑定页面");
        return "org-reg/bind-saas";
    }

	@RequestMapping(value = "/bindSaas")
	@ResponseBody
	public String bindSaasMerchant(HttpServletRequest request){
		UserInfoQueryRequest userInfoQueryRequest = new UserInfoQueryRequest();
		userInfoQueryRequest.setUserLoginName(request.getParameter("username").toString());
		UserInfoResponse userInfoResponse = null;
		try {
			userInfoResponse = titanFinancialUserService.queryFinancialUser(userInfoQueryRequest);
		} catch (Exception e){
			log.error("查询客户输入用户信息异常");
			return toJson(putSysError("查询客户输入用户信息异常"));
		}
		if (userInfoResponse != null && userInfoResponse.isResult() && CollectionUtils.isNotEmpty(userInfoResponse.getUserInfoDTOList())){
			UserInfoDTO userInfoDTO = userInfoResponse.getUserInfoDTOList().get(0);
			if (userInfoDTO.getIsAdmin() == 0){
				return toJson(putSysError("输入用户不是金融系统管理员，请重新输入"));
			}
			if (!userInfoDTO.getPassword().equals(PasswordUtil.md5Hex(request.getParameter("password").toString()))){
				return toJson(putSysError("用户名密码不匹配，请重新输入"));
			}
			OrganBindRequest organBindRequest = new OrganBindRequest();
			try {
				OrganBindResponse organBindResponse = titanFinancialOrganService.bindFinancialOrgan(organBindRequest);
				if (organBindResponse.isResult()){
					return toJson(putSuccess("success"));
				} else {
					log.error("商家绑定失败" + organBindResponse.getReturnMessage());
					return toJson(putSysError("商家绑定失败" + organBindResponse.getReturnMessage()));
				}
			} catch (Exception e){
				log.error("绑定商家发生异常", e);
				return toJson(putSysError("绑定商家发生异常"));
			}
		} else {
			log.error("查询客户输入用户信息失败");
			return toJson(putSysError("查询客户输入用户信息失败"));
		}
	}
    
    
    public void setTitanFinancialOrganService(TitanFinancialOrganService titanFinancialOrganService) {
        this.titanFinancialOrganService = titanFinancialOrganService;
    }

}
