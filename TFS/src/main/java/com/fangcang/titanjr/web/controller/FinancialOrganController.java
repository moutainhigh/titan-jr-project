package com.fangcang.titanjr.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
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

import com.fangcang.titanjr.common.enums.FinancialRoleEnum;
import com.fangcang.titanjr.common.enums.OrgCheckResultEnum;
import com.fangcang.titanjr.common.enums.SMSType;
import com.fangcang.titanjr.common.enums.entity.TitanOrgEnum;
import com.fangcang.titanjr.common.enums.entity.TitanUserEnum;
import com.fangcang.titanjr.common.exception.GlobalServiceException;
import com.fangcang.titanjr.common.exception.MessageServiceException;
import com.fangcang.titanjr.common.util.CommonConstant;
import com.fangcang.titanjr.common.util.DateUtil;
import com.fangcang.titanjr.common.util.FtpUtil;
import com.fangcang.titanjr.common.util.ImageIOExtUtil;
import com.fangcang.titanjr.common.util.Tools;
import com.fangcang.titanjr.dto.bean.OrgBindInfo;
import com.fangcang.titanjr.dto.bean.OrgCheckDTO;
import com.fangcang.titanjr.dto.bean.OrgDTO;
import com.fangcang.titanjr.dto.bean.OrgImageInfo;
import com.fangcang.titanjr.dto.bean.RoleDTO;
import com.fangcang.titanjr.dto.bean.UserInfoDTO;
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
import com.fangcang.titanjr.dto.response.UserInfoResponse;
import com.fangcang.titanjr.dto.response.UserLoginNameExistResponse;
import com.fangcang.titanjr.dto.response.VerifyCheckCodeResponse;
import com.fangcang.titanjr.rs.util.RSInvokeConstant;
import com.fangcang.titanjr.service.TitanFinancialOrganService;
import com.fangcang.titanjr.service.TitanFinancialSendSMSService;
import com.fangcang.titanjr.service.TitanFinancialUserService;
import com.fangcang.titanjr.web.pojo.OrgRegPojo;
import com.fangcang.titanjr.web.pojo.RegUserLoginInfo;
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
@RequestMapping("/organ")
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

    
//    @RequestMapping(value = "/test")
//    public String test(HttpServletRequest request, Model model) {
//    	String nameString = getRequest().getParameter("name");
//    	//putSuccess("11111");
//    	int i=0;
//    	while (i<20) {
//    		try {
//				Thread.sleep(1000);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//    		System.out.println(nameString+"--------");
//    		i++;
//		}
//    	
//        return "orgManager";
//    }
//    
//    
//    @RequestMapping(value = "/test2")
//    public String test2(HttpServletRequest request, Model model) {
//    	String nameString = getRequest().getParameter("name");
//    	System.out.println("------name2--"+nameString);
//        return "orgManager";
//    }
    
    
    @RequestMapping(value = "/showOrg")
    public String queryOrgInfo(HttpServletRequest request, Model model) {
        FinancialOrganResponse dto = titanFinancialOrganService.queryFinancialOrgan(new FinancialOrganQueryRequest());
        model.addAttribute("org", dto);
        return "orgManager";
    }
    /**
     * 显示注册登录信息页面
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/showOrgUser")
    public String showOrgUser(HttpServletRequest request, Model model) {
    	
        return "org-reg/org-user";
    }
    
    /**
     * 注册时，检查注册名是否重复
     * @param userLoginName
     * @return
     */
    @ResponseBody
	@RequestMapping(value = "/checkUserLoginName")
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
	@RequestMapping(value = "/checkOrgRegNum")
    public String checkOrgRegNum(int userType,String orgId,String buslince,String certificateNumber){
    	try {
			int code = checkRegInfo(userType, orgId, buslince, certificateNumber);
			if(code==1){
				putSuccess("该证件可以注册");
			}else if(code == -1){
				 putSysError("该证件已经注册，请使用其他证件注册");
			}else if(code == -2){
				 putSysError("参数错误");
			}
			
		} catch (MessageServiceException e1) {
			putSysError(e1.getMessage());
		} catch (Exception e) {
    		putSysError("系统错误");
			log.error("机构注册校验失败，userType:"+userType+",orgId:"+orgId+",buslince:"+ buslince+",certificateNumber:"+ certificateNumber, e);
		}
    	return toJson();
    }
    /***
     * 检查注册用的证件号码是否已经注册过(包括新建和修改证件号码)
     * @return
     * @throws MessageServiceException 
     */
    private int checkRegInfo(int userType,String orgId,String buslince,String certificateNumber) throws MessageServiceException{
    	OrgRegisterValidateRequest orgRegisterValidateRequest  = new OrgRegisterValidateRequest();
    	orgRegisterValidateRequest.setUsertype(userType);
    	orgRegisterValidateRequest.setBuslince(buslince);
    	orgRegisterValidateRequest.setCertificatetype(TitanOrgEnum.CertificateType.SFZ.getKey()+"");//身份证
    	orgRegisterValidateRequest.setCertificateNumber(certificateNumber);
		OrgRegisterValidateResponse orgRegisterValidateResponse = titanFinancialOrganService.validateOrg(orgRegisterValidateRequest);
		if(orgRegisterValidateResponse.isResult()){
			//判断机构注册证件的编号和登录者是不是同一个机构
			Integer tfsUserIdStr = (Integer)getSession().getAttribute(WebConstant.SESSION_KEY_JR_TFS_USERID);//金服用户名
			if(orgRegisterValidateResponse.getOrgDTO()!=null){//如果所填写的证件编码已经存在
				if(StringUtil.isValidString(orgId)){
					//修改
					if((tfsUserIdStr!=null)&&(tfsUserIdStr>0)){
    					OrgDTO orgDTO = orgRegisterValidateResponse.getOrgDTO();
        				UserInfoQueryRequest userInfoQueryRequest = new UserInfoQueryRequest();
            			userInfoQueryRequest.setTfsUserId(tfsUserIdStr);
            			userInfoQueryRequest.setStatus(TitanUserEnum.Status.AVAILABLE.getKey());
            			UserInfoResponse userInfoResponse = titanFinancialUserService.queryFinancialUser(userInfoQueryRequest);
            			String userOrgCode = userInfoResponse.getUserInfoDTOList().get(0).getOrgCode();
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
    @RequestMapping(value = "/regOrg")
    public String regOrg(RegUserLoginInfo regUserLoginInfo,OrgRegPojo orgRegPojo,Model model){
    	try {
    		//联系人验证
    		int mobiletelCodeId = 0;
    		//登录用户验证
    		int userLoginCodeId = 0;
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
	    	
	    	//session中的信息
	    	String registerSourceStr = (String) getSession().getAttribute(WebConstant.SESSION_KEY_JR_RESOURCE);
	    	int registerSource = StringUtil.isValidString(registerSourceStr)?NumberUtils.toInt(WebConstant.SESSION_KEY_JR_RESOURCE_2_SAAS):NumberUtils.toInt(registerSourceStr);
	    	organRegisterRequest.setRegisterSource(registerSource);
	    	String fcLoginUserName = (String)getSession().getAttribute(WebConstant.SESSION_KEY_LOGIN_USER_LOGINNAME);
	    	if(StringUtil.isValidString(fcLoginUserName)){
	    		//从房仓登录过来的
	    		organRegisterRequest.setFcLoginUserName(fcLoginUserName);
	    		String merchantCode = (String) getSession().getAttribute(WebConstant.SESSION_KEY_CURRENT_MERCHANT_CODE);
	    		String merchantname = (String) getSession().getAttribute(WebConstant.SESSION_KEY_CURRENT_MERCHANT_NAME);
	    		organRegisterRequest.setMerchantCode(merchantCode);
	        	organRegisterRequest.setMerchantname(merchantname);
	        	organRegisterRequest.setOperator(getSAASLoginName());

	    	}
	    	if(orgRegPojo.getUserType()==TitanOrgEnum.UserType.ENTERPRISE.getKey()){
	    		//企业
	    		organRegisterRequest.setBuslince(orgRegPojo.getBuslince());
	    	}else{
	    		//个人
	    		organRegisterRequest.setCertificateNumber(orgRegPojo.getCertificatenumber());
	    		organRegisterRequest.setCertificateType("0");
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
				sendCheckAlarm(orgRegPojo.getOrgName());
				return "/org-reg/reg-success";
			}else{
				model.addAttribute(WebConstant.MODEL_ERROR_MSG_KEY, organRegisterResponse.getReturnMessage());
				return "error";
			}
    	} catch (MessageServiceException e) {
			model.addAttribute(WebConstant.MODEL_ERROR_MSG_KEY, e.getMessage());
			log.error("结构注册失败，错误信息："+e.getMessage()+"，输入参数|regUserLoginInfo:"+JSONSerializer.toJSON(regUserLoginInfo).toString()+",orgRegPojo:"+JSONSerializer.toJSON(orgRegPojo).toString(), e);
		} catch (GlobalServiceException e) {
			model.addAttribute(WebConstant.MODEL_ERROR_MSG_KEY, WebConstant.SERVICE_ERROR_MSG);
			log.error("结构注册失败，输入参数|regUserLoginInfo:"+JSONSerializer.toJSON(regUserLoginInfo).toString()+",orgRegPojo:"+JSONSerializer.toJSON(orgRegPojo).toString(), e);
		} catch (Exception e) {
			model.addAttribute(WebConstant.MODEL_ERROR_MSG_KEY, WebConstant.CONTROLLER_ERROR_MSG);
			log.error("结构注册失败，输入参数|regUserLoginInfo:"+JSONSerializer.toJSON(regUserLoginInfo).toString()+",orgRegPojo:"+JSONSerializer.toJSON(orgRegPojo).toString(), e);
		}
    	//错误页面
    	return "error";
    }
    /**
     * 注册后刷新session中的个人信息,如绑定状态等
     */
    private void refreshSession(String userLoginName){
    	UserInfoQueryRequest userInfoQueryRequest = new UserInfoQueryRequest();
    	userInfoQueryRequest.setUserLoginName(userLoginName);//金服登录用户名
    	UserInfoResponse userInfoResponse = titanFinancialUserService.queryFinancialUser(userInfoQueryRequest);
    	if(CollectionUtils.isNotEmpty(userInfoResponse.getUserInfoDTOList())){
    		UserInfoDTO userInfoDTO = userInfoResponse.getUserInfoDTOList().get(0);
    		//通过机构查询绑定状态
    		OrgBindInfo orgBindInfo = new OrgBindInfo();
    		orgBindInfo.setUserid(userInfoDTO.getUserId());
    		orgBindInfo.setBindStatus(1);
    		orgBindInfo = titanFinancialOrganService.queryOrgBindInfoByUserid(orgBindInfo);
    		if(orgBindInfo!=null){
    			getSession().setAttribute(WebConstant.SESSION_KEY_JR_BIND_STATUS, "1");
    		}else{
    			getSession().setAttribute(WebConstant.SESSION_KEY_JR_BIND_STATUS, "0");
    		}
    		
            getSession().setAttribute(WebConstant.SESSION_KEY_JR_ROLE_LIST, userInfoDTO.getRoleDTOList());//金服用户角色列表
            getSession().setAttribute(WebConstant.SESSION_KEY_JR_LOGIN_UESRNAME, userInfoDTO.getUserLoginName());//金服用户登录名
            getSession().setAttribute(WebConstant.SESSION_KEY_JR_USERID, userInfoDTO.getUserId());//金服机构id标示
            getSession().setAttribute(WebConstant.SESSION_KEY_JR_TFS_USERID, userInfoDTO.getTfsUserId());//金服用户名
            //如果包含系统运营员，判定当前地址
            if (containsRole(userInfoDTO.getRoleDTOList(), FinancialRoleEnum.OPERATION.roleCode)) {
                getSession().setAttribute(WebConstant.SESSION_KEY_JR_RESOURCE, WebConstant.SESSION_KEY_JR_RESOURCE_3_ADMIN);
            }
            //将金服所有角色设置进去
            for (FinancialRoleEnum roleEnum : FinancialRoleEnum.values()) {
                if (containsRole(userInfoDTO.getRoleDTOList(), roleEnum.roleCode)) {
                    getSession().setAttribute("JR_" + roleEnum.roleCode, "1");
                }
            }
    	}else{
			getSession().setAttribute(WebConstant.SESSION_KEY_JR_BIND_STATUS, "0");
		}
        
    }
    /**
     * 判定是否包含角色code。
     */
    private boolean containsRole(List<RoleDTO> roleDTOList, String roleCode) {
        for (RoleDTO roleDTO : roleDTOList) {
            if (roleCode.equals(roleDTO.getRoleCode())) {
                return true;
            }
        }
        return false;
    }
    
    /***
     * 注册时修改注册信息
     * @return
     */
    @RequestMapping(value = "/updateOrg")
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
   	@RequestMapping(value = "/sendRegCode")
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
    	String merchantCode = (String)getSession().getAttribute(WebConstant.SESSION_KEY_CURRENT_MERCHANT_CODE);
    	if(StringUtil.isValidString(merchantCode)){
    		//暂时都使用房仓商家
    		sendRegCodeRequest.setMerchantCode(CommonConstant.FANGCANG_MERCHANTCODE);
    	}else{
    		sendRegCodeRequest.setMerchantCode(RSInvokeConstant.defaultMerchant);
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
    @ResponseBody
   	@RequestMapping(value = "/sendCode")
    public String sendCode(String receiveAddress,Integer msgType){
    	SendCodeRequest sendRegCodeRequest = new SendCodeRequest();
    	if(StringUtil.isValidString(receiveAddress)){
    		sendRegCodeRequest.setReceiveAddress(receiveAddress);
    	}else {
    		return toJson(putSysError("参数错误"));
    	}
    	if(!(Tools.isEmailAddress(receiveAddress)||Tools.isPhone(receiveAddress))){
    		return toJson(putSysError("参数错误"));
    	}
    	String merchantCode = (String)getSession().getAttribute(WebConstant.SESSION_KEY_CURRENT_MERCHANT_CODE);
    	if(StringUtil.isValidString(merchantCode)){
    		//暂时都使用房仓商家
    		sendRegCodeRequest.setMerchantCode(CommonConstant.FANGCANG_MERCHANTCODE);
    	}else{
    		sendRegCodeRequest.setMerchantCode(RSInvokeConstant.defaultMerchant);
    	}
    	msgType = msgType==null?SMSType.REG_CODE.getType():msgType;
    	GetCheckCodeRequest getCheckCodeRequest = new GetCheckCodeRequest();
    	getCheckCodeRequest.setMsgType(msgType);
    	getCheckCodeRequest.setReceiveAddress(receiveAddress);
    	String regCode;
    	try {
			GetCheckCodeResponse getCheckCodeResponse = titanFinancialOrganService.getCheckCode(getCheckCodeRequest);
			if(getCheckCodeResponse.isResult()){
				regCode = getCheckCodeResponse.getCheckCode();
			}else{
				return toJson(putSysError(getCheckCodeResponse.getReturnMessage()));
			}
		} catch (GlobalServiceException e) {
			log.error("send code fail ,param|receiveAddress:"+receiveAddress+",msgType:"+msgType, e);
			return toJson(putSysError("验证码获取失败"));
		}
    	
    	if(msgType==SMSType.REG_CODE.getType()){//注册
    		sendRegCodeRequest.setContent("尊敬的用户： 您正在申请开通泰坦金融服务，验证码为："+regCode+"，验证码"+WebConstant.REG_CODE_TIME_OUT_HOUR+"小时内有效。如不是您申请，请勿将验证码发给其他人。");
        	sendRegCodeRequest.setSubject("泰坦金融注册验证码");
    	}else if(msgType==SMSType.PAY_PASSWORD_MODIFY.getType()){//修改付款密码
    		sendRegCodeRequest.setContent("尊敬的用户： 您正在修改泰坦金融的付款密码，验证码为："+regCode+"，验证码"+WebConstant.REG_CODE_TIME_OUT_HOUR+"小时内有效。如不是您申请，请勿将验证码发给其他人。");
        	sendRegCodeRequest.setSubject("泰坦金融修改付款密码");
    	}else if(msgType==SMSType.LOGIN_PASSWORD_MODIFY.getType()){//修改登录密码
    		sendRegCodeRequest.setContent("尊敬的用户： 您正在修改泰坦金融的登录密码，验证码为："+regCode+"，验证码"+WebConstant.REG_CODE_TIME_OUT_HOUR+"小时内有效。如不是您申请，请勿将验证码发给其他人。");
        	sendRegCodeRequest.setSubject("泰坦金融修改登录密码");
    	}
    	
    	SendCodeResponse sendRegCodeResponse = sendSMSService.sendCode(sendRegCodeRequest);
    	sendRegCodeResponse.putSuccess();
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
    @RequestMapping(value = "/showAgreement")
    public String showAgreement(){
    	return "org-reg/agreement";
    }
    /**
     * 显示修改的公司
     * @return
     */
    @RequestMapping(value = "/getEnterpriseInfo")
    public String getEnterpriseInfo(int orgId,Model model){
    	if(orgId <=0){
    		model.addAttribute("errormsg", "参数错误");
    		return "error";
    	}
    	int code = getInfo(orgId,model);
    	if(code==0){
    		return "error";
    	}
    	return "org-reg/org-enterprise-info";
    }
    /***
     * 显示修改的个人
     * @return
     */
    @RequestMapping(value = "/getPersernalInfo")
    public String getPersernalInfo(int orgId,Model model){
    	if(orgId <=0){
    		model.addAttribute("errormsg", "参数错误");
    		return "error";
    	}
    	int code = getInfo(orgId,model);
    	if(code==0){
    		return "error";
    	}
    	return "org-reg/org-persernal-info";
    }
    private int getInfo(int orgId,Model model){
    	FinancialOrganQueryRequest organQueryRequest = new FinancialOrganQueryRequest();
    	organQueryRequest.setOrgId(orgId);
    	FinancialOrganResponse financialOrganResponse = titanFinancialOrganService.queryFinancialOrgan(organQueryRequest);
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
    		}
    		return 1;
    	}else{
    		model.addAttribute("errormsg", "查询结果数据错误");
    		return 0;
    	}
    }
   /**
    *  显示公司信息
    * @return
    */
    @RequestMapping(value = "/showEnterpriseInfo")
    public String showEnterpriseInfo(RegUserLoginInfo pojo, Model model){
    	model.addAttribute(pojo);
    	
    	model.addAttribute("regUserLoginInfo", pojo);
    	return "org-reg/org-enterprise-info";
    }
    /**
     * 显示个人信息
     * 
     */
    @RequestMapping(value = "/showPersernalInfo")
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
    @RequestMapping(value = "/checkRegCode")
    public String checkRegCode(String userLoginName,String regCode){
    	if((!StringUtil.isValidString(userLoginName))||(!StringUtil.isValidString(regCode))){
    		putSysError("参数错误");
    		return toJson();
    		
    	}
    	VerifyCheckCodeRequest verifyCheckCodeRequest = new VerifyCheckCodeRequest();
    	verifyCheckCodeRequest.setReceiveAddress(userLoginName);
    	verifyCheckCodeRequest.setInputCode(regCode);
    	VerifyCheckCodeResponse verifyCheckCodeResponse = titanFinancialOrganService.verifyCheckCode(verifyCheckCodeRequest);
    	if(verifyCheckCodeResponse.isResult()){
    		return toJson(putSuccess("验证成功"));
    	}else{
    		return toJson(putSysError(verifyCheckCodeResponse.getReturnMessage()));
    	}
    }
    
    @RequestMapping(value = "/upload")
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
     * 显示个人信息
     * @param model
     * @return
     */
    public String showPersonalInfo(Model model){
    	
    	
    	
    	return "org-reg/org-persernal-info";
    }
    
    /**
     * 显示商家绑定页面
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/toBindSaas")
    public String toBindSaas(HttpServletRequest request, Model model) {
    	String merchantName = (String)getSession().getAttribute(WebConstant.SESSION_KEY_CURRENT_MERCHANT_CODE);
    	String merchantCode = (String)getSession().getAttribute(WebConstant.SESSION_KEY_CURRENT_MERCHANT_NAME);
    	model.addAttribute("merchantName", merchantName);
    	model.addAttribute("merchantCode", merchantCode);
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
			organBindRequest.setFcuserid(Integer.valueOf(getSession().getAttribute(WebConstant.SESSION_KEY_LOGIN_USER_ID).toString()));
			organBindRequest.setMerchantCode((String) getSession().getAttribute(WebConstant.SESSION_KEY_CURRENT_MERCHANT_CODE));
			organBindRequest.setFcloginname((String) getSession().getAttribute(WebConstant.SESSION_KEY_LOGIN_USER_LOGINNAME));
			organBindRequest.setFcusername((String) getSession().getAttribute(WebConstant.SESSION_KEY_LOGIN_USER_NAME));
			organBindRequest.setMerchantName(String.valueOf(getSession().getAttribute(WebConstant.SESSION_KEY_CURRENT_MERCHANT_NAME)));
			organBindRequest.setOperateType(1);
			organBindRequest.setPassword(request.getParameter("password").toString());
			organBindRequest.setTfsuserid(userInfoDTO.getTfsUserId());
			organBindRequest.setUserId(userInfoDTO.getUserId());
			organBindRequest.setUserloginname(userInfoDTO.getUserLoginName());
			organBindRequest.setOperator((String) getSession().getAttribute(WebConstant.SESSION_KEY_LOGIN_USER_NAME));
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
