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
import com.fangcang.titanjr.common.enums.SMSType;
import com.fangcang.titanjr.common.enums.entity.TitanOrgEnum;
import com.fangcang.titanjr.common.enums.entity.TitanUserEnum;
import com.fangcang.titanjr.common.exception.GlobalServiceException;
import com.fangcang.titanjr.common.exception.MessageServiceException;
import com.fangcang.titanjr.common.util.DateUtil;
import com.fangcang.titanjr.common.util.FtpUtil;
import com.fangcang.titanjr.common.util.ImageIOExtUtil;
import com.fangcang.titanjr.common.util.Tools;
import com.fangcang.titanjr.dto.bean.OrgBindInfo;
import com.fangcang.titanjr.dto.bean.OrgDTO;
import com.fangcang.titanjr.dto.bean.OrgImageInfo;
import com.fangcang.titanjr.dto.bean.RoleDTO;
import com.fangcang.titanjr.dto.bean.UserInfoDTO;
import com.fangcang.titanjr.dto.request.FinancialOrganQueryRequest;
import com.fangcang.titanjr.dto.request.OrgRegisterValidateRequest;
import com.fangcang.titanjr.dto.request.OrganBindRequest;
import com.fangcang.titanjr.dto.request.OrganImageRequest;
import com.fangcang.titanjr.dto.request.OrganImageUploadRequest;
import com.fangcang.titanjr.dto.request.OrganRegisterRequest;
import com.fangcang.titanjr.dto.request.OrganRegisterUpdateRequest;
import com.fangcang.titanjr.dto.request.SendRegCodeRequest;
import com.fangcang.titanjr.dto.request.UserInfoQueryRequest;
import com.fangcang.titanjr.dto.request.UserLoginNameExistRequest;
import com.fangcang.titanjr.dto.response.FinancialOrganResponse;
import com.fangcang.titanjr.dto.response.OrgRegisterValidateResponse;
import com.fangcang.titanjr.dto.response.OrganBindResponse;
import com.fangcang.titanjr.dto.response.OrganImageResponse;
import com.fangcang.titanjr.dto.response.OrganImageUploadResponse;
import com.fangcang.titanjr.dto.response.OrganRegisterResponse;
import com.fangcang.titanjr.dto.response.OrganRegisterUpdateResponse;
import com.fangcang.titanjr.dto.response.SendRegCodeResponse;
import com.fangcang.titanjr.dto.response.UserInfoResponse;
import com.fangcang.titanjr.dto.response.UserLoginNameExistResponse;
import com.fangcang.titanjr.rs.util.RSInvokeConstant;
import com.fangcang.titanjr.service.TitanFinancialOrganService;
import com.fangcang.titanjr.service.TitanFinancialSendSMSService;
import com.fangcang.titanjr.service.TitanFinancialUserService;
import com.fangcang.titanjr.web.pojo.OrgRegPojo;
import com.fangcang.titanjr.web.pojo.RegUserLoginInfo;
import com.fangcang.titanjr.web.util.WebConstant;
import com.fangcang.titanjr.web.util.TFSTools;
import com.fangcang.util.PasswordUtil;
import com.fangcang.util.StringUtil;

/**
 * 泰坦金服机构controller，目的用于后续机构申请提交，查询基础设置等
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
    

    @RequestMapping(value = "/showOrg")
    public String queryOrgInfo(HttpServletRequest request, Model model) {
        FinancialOrganResponse dto = titanFinancialOrganService.queryFinancialOrgan(new FinancialOrganQueryRequest());
        System.out.println(dto);
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
    	Map<String, Object> resultMap = new HashMap<String, Object>();
    	UserLoginNameExistResponse response;
		try {
			request.setUserLoginName(userLoginName);
			request.setIsOperator(isOperator);
			response = titanFinancialUserService.userLoginNameExist(request);
			if("1".equals(response.getReturnCode())){
				resultMap = putSuccess();
			}else{
				resultMap = putSysError(response.getReturnMessage());
			}
		} catch (GlobalServiceException e) {
			log.error("检查注册名是否重复失败,userLoginName:"+userLoginName, e); 
			resultMap = putSysError();
		}
		return toJson(resultMap);
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
			Integer tfsUserIdStr = (Integer)session.getAttribute(WebConstant.SESSION_KEY_JR_TFS_USERID);//金服用户名
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
	    	//企业用户
    		if(orgRegPojo.getUserType()==TitanOrgEnum.UserType.ENTERPRISE.getKey()){
    			String infoFlag = TFSTools.validateRegCode(session,orgRegPojo.getMobiletel(), orgRegPojo.getSmsRegCode());
    	    	if(!"SUCCESS".equals(infoFlag)){
    	    		model.addAttribute("errormsg", "验证码错误");
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
	    	String registerSourceStr = (String) session.getAttribute(WebConstant.SESSION_KEY_JR_RESOURCE);
	    	int registerSource = StringUtil.isValidString(registerSourceStr)?NumberUtils.toInt(WebConstant.SESSION_KEY_JR_RESOURCE_2_SAAS):NumberUtils.toInt(registerSourceStr);
	    	organRegisterRequest.setRegisterSource(registerSource);
	    	//organRegisterRequest.setUserName(userName);
	    	String fcLoginUserName = (String)session.getAttribute(WebConstant.SESSION_KEY_LOGIN_USER_LOGINNAME);
	    	if(StringUtil.isValidString(fcLoginUserName)){
	    		//从房仓登录过来的
	    		organRegisterRequest.setFcLoginUserName(fcLoginUserName);
	    		String merchantCode = (String) session.getAttribute(WebConstant.SESSION_KEY_CURRENT_MERCHANT_CODE);
	    		String merchantname = (String) session.getAttribute(WebConstant.SESSION_KEY_CURRENT_MERCHANT_NAME);
	    		organRegisterRequest.setMerchantCode(merchantCode);
	        	organRegisterRequest.setMerchantname(merchantname);
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
    			session.setAttribute(WebConstant.SESSION_KEY_JR_BIND_STATUS, "1");
    		}else{
    			session.setAttribute(WebConstant.SESSION_KEY_JR_BIND_STATUS, "0");
    		}
    		
            session.setAttribute(WebConstant.SESSION_KEY_JR_ROLE_LIST, userInfoDTO.getRoleDTOList());//金服用户角色列表
            session.setAttribute(WebConstant.SESSION_KEY_JR_LOGIN_UESRNAME, userInfoDTO.getUserLoginName());//金服用户登录名
            session.setAttribute(WebConstant.SESSION_KEY_JR_USERID, userInfoDTO.getUserId());//金服机构id标示
            session.setAttribute(WebConstant.SESSION_KEY_JR_TFS_USERID, userInfoDTO.getTfsUserId());//金服用户名
            //如果包含系统运营员，判定当前地址
            if (containsRole(userInfoDTO.getRoleDTOList(), FinancialRoleEnum.OPERATION.roleCode)) {
                session.setAttribute(WebConstant.SESSION_KEY_JR_RESOURCE, WebConstant.SESSION_KEY_JR_RESOURCE_3_ADMIN);
            }
            //将金服所有角色设置进去
            for (FinancialRoleEnum roleEnum : FinancialRoleEnum.values()) {
                if (containsRole(userInfoDTO.getRoleDTOList(), roleEnum.roleCode)) {
                    session.setAttribute("JR_" + roleEnum.roleCode, "1");
                }
            }
    	}else{
			session.setAttribute(WebConstant.SESSION_KEY_JR_BIND_STATUS, "0");
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
    	//TODO 判断机构审核状态，已经通过，则不允许修改
    	try {
	    	//1.判断验证码
	    	//企业用户
			if(orgRegPojo.getUserType()==TitanOrgEnum.UserType.ENTERPRISE.getKey()){
				String infoFlag = TFSTools.validateRegCode(session,orgRegPojo.getMobiletel(), orgRegPojo.getSmsRegCode());
		    	if(!"SUCCESS".equals(infoFlag)){
		    		model.addAttribute("errormsg", "验证码错误");
		    		return "error";
		    	}
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
     * 发送邮件或者手机验证码
     * @param receiveAddress
     * @return
     */
    @ResponseBody
   	@RequestMapping(value = "/sendRegCode")
    public String sendRegCode(String receiveAddress){
    	SendRegCodeRequest sendRegCodeRequest = new SendRegCodeRequest();
    	if(StringUtil.isValidString(receiveAddress)){
    		sendRegCodeRequest.setReceiveAddress(receiveAddress);
    	}else {
    		return toJson(putSysError("参数错误"));
    	}
    	if(!(Tools.isEmailAddress(receiveAddress)||Tools.isPhone(receiveAddress))){
    		return toJson(putSysError("参数错误"));
    	}
    	String merchantCode = (String)session.getAttribute(WebConstant.SESSION_KEY_CURRENT_MERCHANT_NAME);
    	if(StringUtil.isValidString(merchantCode)){
    		//TODO 使用正式环境
    		sendRegCodeRequest.setMerchantCode("M10000001");
    	}else{
    		sendRegCodeRequest.setMerchantCode(RSInvokeConstant.defaultMerchant);
    	}
    	String regCode = setRegCode(receiveAddress);
		sendRegCodeRequest.setContent("尊敬的用户： 您正在申请开通泰坦金融服务，验证码为："+regCode+"，验证码"+WebConstant.REG_CODE_TIME_OUT_HOUR+"小时内有效。如不是您申请，请勿将验证码发给其他人。");
    	sendRegCodeRequest.setSubject("泰坦金融注册验证码");
    	 
    	
    	SendRegCodeResponse sendRegCodeResponse = sendSMSService.sendRegCode(sendRegCodeRequest);
    	sendRegCodeResponse.putSuccess();
    	if(sendRegCodeResponse.isResult()){
    		return toJson(putSuccess("验证码发送成功"));
    	}else{
    		return toJson(putSysError(sendRegCodeResponse.getReturnMessage()));
    	}
    	
   }
    @ResponseBody
   	@RequestMapping(value = "/sendCode")
    public String sendCode(String receiveAddress,Integer msgType){
    	SendRegCodeRequest sendRegCodeRequest = new SendRegCodeRequest();
    	if(StringUtil.isValidString(receiveAddress)){
    		sendRegCodeRequest.setReceiveAddress(receiveAddress);
    	}else {
    		return toJson(putSysError("参数错误"));
    	}
    	if(!(Tools.isEmailAddress(receiveAddress)||Tools.isPhone(receiveAddress))){
    		return toJson(putSysError("参数错误"));
    	}
    	String merchantCode = (String)session.getAttribute(WebConstant.SESSION_KEY_CURRENT_MERCHANT_NAME);
    	if(StringUtil.isValidString(merchantCode)){
    		//TODO 使用正式环境
    		sendRegCodeRequest.setMerchantCode("M10000001");
    	}else{
    		sendRegCodeRequest.setMerchantCode(RSInvokeConstant.defaultMerchant);
    	}
    	String regCode = setRegCode(receiveAddress);
    	if(msgType==null||msgType==SMSType.REG_CODE.getType()){//注册
    		sendRegCodeRequest.setContent("尊敬的用户： 您正在申请开通泰坦金融服务，验证码为："+regCode+"，验证码"+WebConstant.REG_CODE_TIME_OUT_HOUR+"小时内有效。如不是您申请，请勿将验证码发给其他人。");
        	sendRegCodeRequest.setSubject("泰坦金融注册验证码");
    	}else if(msgType==SMSType.LOGIN_PASSWORD_MODIFY.getType()){//修改支付密码
    		sendRegCodeRequest.setContent("尊敬的用户： 您正在修改泰坦金融的支付密码，验证码为："+regCode+"，验证码"+WebConstant.REG_CODE_TIME_OUT_HOUR+"小时内有效。如不是您申请，请勿将验证码发给其他人。");
        	sendRegCodeRequest.setSubject("泰坦金融修改支付密码");
    	}
    	
    	SendRegCodeResponse sendRegCodeResponse = sendSMSService.sendRegCode(sendRegCodeRequest);
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
    	String rcode = TFSTools.validateRegCode(session,userLoginName,regCode);
    	if(rcode.equals("SUCCESS")){
    		return toJson(putSuccess("验证成功"));
    	}else if(rcode.equals("EXPIRE")){
    		return toJson(putSysError("验证码已经过期，请重新获取验证码"));
    	}else if(rcode.equals("NOTEXIST")){
    		return toJson(putSysError("验证码错误，请重新获取验证码"));
    	}else if(rcode.equals("WRONG")){
    		return toJson(putSysError("验证码错误，请重新输入"));
    	}else{
    		return toJson(putSysError("验证码不存在，请重新获取验证码"));
    	}
    }
   
    
    
    
    
    /**
     * 生成新的验证码
     * @param receiveAddress
     * @return
     */
    private String setRegCode(String receiveAddress){
    	String regCode = Tools.getRegCode();
    	log.info(receiveAddress+"-----------regCode:"+regCode);
    	session.setAttribute(WebConstant.SESSION_KEY_REG_CODE+"_"+receiveAddress, DateUtil.formatDataToDatetime(new Date())+"_"+regCode);
    	return regCode;
    }
    
    @RequestMapping(value = "/upload")
    public void upload(@RequestParam(value = "img_file", required = false) MultipartFile file,int imageType) throws IOException{
    	Map<String, Object> resultMap = new HashMap<String, Object>();
 
    	response.setCharacterEncoding("utf-8");  //这里不设置编码会有乱码
        response.setContentType("text/html;charset=utf-8");
        response.setHeader("Cache-Control", "no-cache"); 
    	try {
    		if(file.getBytes().length>(WebConstant.UPLOAD_IMG_MAX_SIZE_10_M*1000*1000)){
    			putSysError("文件大小超过了"+WebConstant.UPLOAD_IMG_MAX_SIZE_10_M+"M，请压缩后再上传");
                PrintWriter out = response.getWriter(); 
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
			putSuccess("上传成功");
			result.put("data", resultMap);
		} catch (IOException e) {
			putSysError("文件上传失败");
			log.error("上传文件失败", e);
		} catch (GlobalServiceException e) {
			putSysError("文件上传失败");
			log.error("上传文件失败", e);
		}
    	PrintWriter out = response.getWriter(); 
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
    	String merchantName = (String)session.getAttribute(WebConstant.SESSION_KEY_CURRENT_MERCHANT_CODE);
    	String merchantCode = (String)session.getAttribute(WebConstant.SESSION_KEY_CURRENT_MERCHANT_NAME);
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
				return toJson(putSysError("输入用户不是金服系统管理员，请重新输入"));
			}
			if (!userInfoDTO.getPassword().equals(PasswordUtil.md5Hex(request.getParameter("password").toString()))){
				return toJson(putSysError("用户名密码不匹配，请重新输入"));
			}
			OrganBindRequest organBindRequest = new OrganBindRequest();
			organBindRequest.setFcuserid(Integer.valueOf(session.getAttribute(WebConstant.SESSION_KEY_LOGIN_USER_ID).toString()));
			organBindRequest.setMerchantCode((String) session.getAttribute(WebConstant.SESSION_KEY_CURRENT_MERCHANT_CODE));
			organBindRequest.setFcloginname((String) session.getAttribute(WebConstant.SESSION_KEY_LOGIN_USER_LOGINNAME));
			organBindRequest.setFcusername((String) session.getAttribute(WebConstant.SESSION_KEY_LOGIN_USER_NAME));
			organBindRequest.setMerchantName(String.valueOf(session.getAttribute(WebConstant.SESSION_KEY_CURRENT_MERCHANT_NAME)));
			organBindRequest.setOperateType(1);
			organBindRequest.setPassword(request.getParameter("password").toString());
			organBindRequest.setTfsuserid(userInfoDTO.getTfsUserId());
			organBindRequest.setUserId(userInfoDTO.getUserId());
			organBindRequest.setUserloginname(userInfoDTO.getUserLoginName());
			organBindRequest.setOperator((String) session.getAttribute(WebConstant.SESSION_KEY_LOGIN_USER_NAME));
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
