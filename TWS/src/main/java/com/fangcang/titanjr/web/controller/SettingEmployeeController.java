package com.fangcang.titanjr.web.controller;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.fangcang.titanjr.common.enums.BusTypeEnum;
import com.fangcang.titanjr.common.enums.CashierItemTypeEnum;
import com.fangcang.titanjr.dto.bean.*;
import com.fangcang.titanjr.dto.request.*;
import com.fangcang.titanjr.dto.response.*;
import com.fangcang.titanjr.service.*;

import com.fangcang.titanjr.web.pojo.CashierSwitchPO;
import net.sf.json.JSONSerializer;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.fangcang.corenut.dao.PaginationSupport;
import com.fangcang.titanjr.common.enums.RegSourceEnum;
import com.fangcang.titanjr.common.enums.entity.TitanUserEnum;
import com.fangcang.titanjr.common.exception.GlobalServiceException;
import com.fangcang.titanjr.common.exception.MessageServiceException;
import com.fangcang.titanjr.common.util.AESUtil;
import com.fangcang.titanjr.common.util.CommonConstant;
import com.fangcang.titanjr.common.util.GenericValidate;
import com.fangcang.titanjr.common.util.SMSTemplate;
import com.fangcang.titanjr.common.util.Tools;
import com.fangcang.titanjr.web.annotation.AccessPermission;
import com.fangcang.titanjr.web.pojo.EmployeePojo;
import com.fangcang.titanjr.web.pojo.FcEmployeeTablePojo;
import com.fangcang.titanjr.web.util.WebConstant;
import com.fangcang.util.DateUtil;
import com.fangcang.util.StringUtil;

/***
 * 泰坦金融员工权限设置
 * @author luoqinglong
 * @2016年6月14日
 */
@Controller
public class SettingEmployeeController extends BaseController{
	/** 
	 * 
	 */
	private static final long serialVersionUID = 8044945171301793319L;

	private static final Log log = LogFactory.getLog(SettingEmployeeController.class);
    
    @Autowired
    private TitanFinancialUserService titanFinancialUserService;
    
    @Autowired
    private TitanFinancialOrganService organService;

	@Autowired
	private TitanFinancialRateService titanFinancialRateService;
	
	@Resource
	private TitanFinancialSendSMSService smsService;

	@Autowired
	private TitanCashierDeskService titanCashierDeskService;
	/**
	 * 左侧菜单（本地调试使用）
	 * @return
	 */
    @AccessPermission(allowRoleCode={CommonConstant.ROLECODE_NO_LIMIT})
	@RequestMapping("/setting/slidemenu")
	public String slidemenu(){
		return "slidemenu/jr-setting-menu";
	}
	/***
	 * 员工权限设置(查询员工)
	 * @return
	 */
	@RequestMapping("/setting/employee")
	@AccessPermission(allowRoleCode={CommonConstant.ROLECODE_ADMIN})
	public String employee(){
		
		return "setting/employee-list";
	}
	/**
	 * 员工权限设置-表格数据
	 * @param pageNo
	 * @param pageSize
	 * @param model
	 * @return
	 */
	@RequestMapping("/setting/employee-table")
	@AccessPermission(allowRoleCode={CommonConstant.ROLECODE_ADMIN})
	public String employeeTable(String tfsUserLoginName,String userName, Integer pageNo,Integer pageSize,Model model){
		if(pageNo==null){
			pageNo = 1;
		}
		if(pageSize==null||pageSize==0){
			pageSize = 15;
		}
		String tfsUserId = (String)getSession().getAttribute(WebConstant.SESSION_KEY_JR_TFS_USERID);
		UserInfoQueryRequest userInfoQueryRequest = new UserInfoQueryRequest();
		userInfoQueryRequest.setTfsUserId(Integer.valueOf(tfsUserId));
		userInfoQueryRequest.setPageSize(pageSize);
		userInfoQueryRequest.setCurrentPage(pageNo);
		userInfoQueryRequest.setStatus(TitanUserEnum.Status.AVAILABLE.getKey());
		UserInfoResponse userInfoResponse = titanFinancialUserService.queryFinancialUser(userInfoQueryRequest);
		
		userInfoQueryRequest.setTfsUserId(null);
		userInfoQueryRequest.setStatus(null);
		userInfoQueryRequest.setExcludeStatus(TitanUserEnum.Status.NOT_AVAILABLE.getKey());
		userInfoQueryRequest.setUserLoginName("".equals(tfsUserLoginName)?null:tfsUserLoginName);
		userInfoQueryRequest.setUserName("".equals(userName)?null:userName);
		userInfoQueryRequest.setOrgCode(userInfoResponse.getUserInfoDTOList().get(0).getOrgCode());
		RoleUserInfoPageResponse roleUserInfoPageResponse =  new RoleUserInfoPageResponse();
		if(StringUtil.isValidString(userInfoQueryRequest.getOrgCode())){
			roleUserInfoPageResponse = titanFinancialUserService.queryRoleUserInfoPage(userInfoQueryRequest);
		}
		
		if(roleUserInfoPageResponse.isResult()){
			model.addAttribute("userInfoDTOPage", roleUserInfoPageResponse.getPaginationSupport());
		}
		return "setting/employee-list-table";
	}
	
	/**
	 * 新增员工权限
	 * @return
	 */
	@RequestMapping("/setting/fc-employee")
	@AccessPermission(allowRoleCode={CommonConstant.ROLECODE_ADMIN})
	public String fcEmployee(){
		return "setting/fc-employee-list";
	}
	
	/**
	 * 新增员工权限-表格数据(房仓员工查询)
	 * @return
	 */
	@RequestMapping("/setting/fc-employee-table")
	@AccessPermission(allowRoleCode={CommonConstant.ROLECODE_ADMIN})
	public String fcEmployeeTable(HttpServletRequest request,FcEmployeeTablePojo  fcEmployeeTablePojo,Model model){
		SaaSUserRoleRequest saaSUserRoleRequest = new SaaSUserRoleRequest();

		saaSUserRoleRequest.setSaasUserName(fcEmployeeTablePojo.getSaasUserName());
		saaSUserRoleRequest.setPageSize(PaginationSupport.NO_SPLIT_PAGE_SIZE);
		try {
			SaaSUserRoleResponse saasUserRoleResponse = titanFinancialUserService.querySaaSUserRolePage(saaSUserRoleRequest);
			if(saasUserRoleResponse.isResult()){
				model.addAttribute("saasUserRoleList", saasUserRoleResponse.getPaginationSupport().getItemList());
			}else{
				model.addAttribute("errormsg", saasUserRoleResponse.getReturnMessage());
				return "error";
			}
		} catch (GlobalServiceException e) {
			log.error("房仓用户数据查询失败,请求参数:"+ToStringBuilder.reflectionToString(fcEmployeeTablePojo), e);
			model.addAttribute("errormsg", WebConstant.CONTROLLER_ERROR_MSG);
			return "error";
		}
		return "setting/fc-employee-list-table";
	}
	/***
	 * 新增权限员工 添加 / 修改 金融员工信息（查询员工信息）
	 * @return
	 */
	@RequestMapping("/setting/employee-add")
	@AccessPermission(allowRoleCode={CommonConstant.ROLECODE_ADMIN})
	public String employeeAdd(String tfsUserId,Integer fcUserId,Model model){
		int tfsUserIdInt = 0;
		if(StringUtil.isValidString(tfsUserId)){
			tfsUserIdInt = NumberUtils.toInt(AESUtil.decrypt(tfsUserId));
		}
		
		model.addAttribute("tfsUserId", tfsUserIdInt);//新增该变量没有值
		model.addAttribute("fcUserId", fcUserId);
		
		if(tfsUserIdInt>0){
			UserInfoQueryRequest userInfoQueryRequest = new UserInfoQueryRequest();
			userInfoQueryRequest.setTfsUserId(tfsUserIdInt);
			UserInfoResponse userInfoResponse = titanFinancialUserService.queryFinancialUser(userInfoQueryRequest);
			
			if(userInfoResponse.getUserInfoDTOList()!=null&&userInfoResponse.getUserInfoDTOList().size()>0){
				UserInfoDTO userInfoDTO = userInfoResponse.getUserInfoDTOList().get(0);
				model.addAttribute("userInfoDTO", userInfoDTO);
				if(CollectionUtils.isNotEmpty(userInfoDTO.getRoleDTOList())){
					String roleIds = "";
					for(RoleDTO item : userInfoDTO.getRoleDTOList()){
						if(item.getIsActive()==1){
							roleIds += item.getRoleId()+",";
						}
					}
					model.addAttribute("roleIds", roleIds);
				}
				
				return "setting/employee-update";
			}
			model.addAttribute("errormsg", "数据信息不存在,请确认用户id是否合法");
			return "error";
		} 
		return "setting/employee-add";
	}
	
	/***
	 * 新增用户时,保存员工
	 * @param employeePojo
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/setting/save-employee")
	@AccessPermission(allowRoleCode={CommonConstant.ROLECODE_ADMIN})
	public String saveEmployee(EmployeePojo employeePojo){
		
    	if (!GenericValidate.validate(employeePojo)) {
            return toJson(putSysError("参数错误,请重新输入"));
        }
    	//检验验证码
    	VerifyCheckCodeRequest verifyCheckCodeRequest = new VerifyCheckCodeRequest();
    	verifyCheckCodeRequest.setReceiveAddress(employeePojo.getReceiveAddress());
    	verifyCheckCodeRequest.setInputCode(employeePojo.getCode());
    	VerifyCheckCodeResponse verifyCheckCodeResponse = organService.verifyCheckCode(verifyCheckCodeRequest);
    	if(!verifyCheckCodeResponse.isResult()){
    		return toJson(putSysError(verifyCheckCodeResponse.getReturnMessage()));
    	}
		
		String userId = (String)getSession().getAttribute(WebConstant.SESSION_KEY_JR_USERID);
		
		UserRegisterRequest userRegisterRequest = new UserRegisterRequest();
		userRegisterRequest.setOperateTime(DateUtil.dateToString(DateUtil.getCurrentDate()));
    	userRegisterRequest.setIsAdminUser(0);
    	userRegisterRequest.setLoginUserName(employeePojo.getReceiveAddress());
    	userRegisterRequest.setUserName(employeePojo.getUserName());
    	userRegisterRequest.setOrgCode(userId);
    	userRegisterRequest.setRoleIdList(toList(employeePojo.getCheckedRoleId()));
    	userRegisterRequest.setUnselectRoleIdList(toList(employeePojo.getUncheckedRoleId()));
    	//生成一个密码
    	userRegisterRequest.setPassword(RandomStringUtils.randomNumeric(8));
    	userRegisterRequest.setRegisterSource(RegSourceEnum.TWS.getType());
    	userRegisterRequest.setUserId(userId);//金服机构
    	try {
			UserRegisterResponse respose = titanFinancialUserService.registerFinancialUser(userRegisterRequest);
			if(respose.isResult()){
				putSuccess("员工添加成功");
				
				if(verifyCheckCodeResponse.getCodeId()>0){
					UpdateCheckCodeRequest updateCheckCodeRequest = new UpdateCheckCodeRequest();
					updateCheckCodeRequest.setCodeId(verifyCheckCodeResponse.getCodeId());
					updateCheckCodeRequest.setIsactive(0);
					organService.useCheckCode(updateCheckCodeRequest);
				}
				//发送通知短信
				OrgDTO orgDTO = new OrgDTO();
				orgDTO.setOrgcode(userId);
				orgDTO = organService.queryOrg(orgDTO);
				SendMessageRequest sendCodeRequest = new SendMessageRequest();
				sendCodeRequest.setReceiveAddress(userRegisterRequest.getLoginUserName());
				sendCodeRequest.setSubject(SMSTemplate.EMPLOYEE_ADD_SUCCESS.getSubject());
				sendCodeRequest.setMerchantCode(CommonConstant.FANGCANG_MERCHANTCODE);
				sendCodeRequest.setContent(MessageFormat.format(SMSTemplate.EMPLOYEE_ADD_SUCCESS.getContent(), new Object[]{orgDTO.getOrgname(),userRegisterRequest.getLoginUserName(),userRegisterRequest.getPassword()}));
				smsService.asynSendMessage(sendCodeRequest);
				
			}else{
				putSysError(respose.getReturnMessage());
			}
		} catch (Exception e) {
			log.error("添加员工失败，参数employeePojo:" +Tools.gsonToString(employeePojo), e);
			putSysError("添加员工失败，请重试");
		}
    	
    	return toJson();
	}
	/**
	 * 转为数组
	 * @param roleIds
	 * @return
	 */
	private List<Long> toList(String roleIds){
		 List<Long> roleIdList = new ArrayList<Long>();
		 if(StringUtil.isValidString(roleIds)){
			 String[] roleIdString = roleIds.split(",");
			 for(String item : roleIdString){
				 try {
					 roleIdList.add(Long.valueOf(item));
				} catch (Exception e) {
				}
			 }
		 }
		 return roleIdList;
	}

	/**
	 * 开关收银台操作
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/setting/switch-cashier")
	@AccessPermission(allowRoleCode={CommonConstant.ROLECODE_ADMIN})
	public String switchCashierDesk(CashierSwitchPO cashierSwitchPO){
		if(cashierSwitchPO.getCashierType() == null || cashierSwitchPO.getOpen() == null ||
				(cashierSwitchPO.getCashierType() != 1 && cashierSwitchPO.getCashierType() != 2) ||
				(cashierSwitchPO.getOpen() != 0 && cashierSwitchPO.getOpen() != 1)){
			putSysError("参数错误");
			return toJson();
		}
		CashierDeskUpdateRequest deskUpdateRequest = new CashierDeskUpdateRequest();
		deskUpdateRequest.setIsOpen(cashierSwitchPO.getOpen());
		List<Integer> usedList = new ArrayList<Integer>();
		if (cashierSwitchPO.getCashierType() == 1){//分销工具GDP
			usedList.add(1);
			usedList.add(4);
		}
		if (cashierSwitchPO.getCashierType() == 2){//交易平台PUS
			usedList.add(3);
			usedList.add(9);
		}
		deskUpdateRequest.setUsedList(usedList);
		deskUpdateRequest.setUserId(String.valueOf(getSession().getAttribute(WebConstant.SESSION_KEY_JR_USERID)));
		try {
			boolean result = titanCashierDeskService.updateCashierDesk(deskUpdateRequest);
			if (result) {
				putSuccess();
				return toJson();
			} else {
				putSysError("更新收银台开关失败");
				return toJson();
			}
		} catch (Exception e) {
			putSysError("更新收银台开关异常");
			log.error("更新收银台开关异常" ,e );
		}
		return toJson();
	}
	
	/**
	 * 修改时保存员工信息
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/setting/save-update-user")
	@AccessPermission(allowRoleCode={CommonConstant.ROLECODE_ADMIN})
	public String saveUpdateUser(EmployeePojo employeePojo){
		if(employeePojo.getTfsUserId()==null||(!StringUtil.isValidString(employeePojo.getTfsUserId()))){
			putSysError("参数错误");
			return toJson();
		}
		int tfsUserIdInt = 0;
		tfsUserIdInt = NumberUtils.toInt(AESUtil.decrypt(employeePojo.getTfsUserId()));
		UpdateUserRequest updateUserRequest = new UpdateUserRequest();
		
		updateUserRequest.setTfsUserId(tfsUserIdInt);
		updateUserRequest.setUserName(employeePojo.getUserName());
		updateUserRequest.setRoleIdList(toList(employeePojo.getCheckedRoleId()));
		updateUserRequest.setUnselectRoleIdList(toList(employeePojo.getUncheckedRoleId()));
		try {
			UpdateUserResponse updateUserResponse = titanFinancialUserService.updateUser(updateUserRequest);
			if(updateUserResponse.isResult()){
				putSuccess("修改成功");
			}else{
				putSysError(updateUserResponse.getReturnMessage());
			}
		} catch (GlobalServiceException e) {
			log.error("修改时保存员工信息失败，参数employeePojo："+JSONSerializer.toJSON(employeePojo).toString(),e);
			putSysError(WebConstant.CONTROLLER_ERROR_MSG);
		}
		
		return toJson();
	}
	/***
	 * 解冻或冻结员工
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/setting/freeze-user")
	@AccessPermission(allowRoleCode={CommonConstant.ROLECODE_ADMIN})
	public String freezeUser(Integer status,String tfsUserId){
		if((status==null||status<0)||(!StringUtil.isValidString(tfsUserId))){
			putSysError("请求参数错误");
			return toJson();
		}
		int tfsUserIdInt = 0;
		tfsUserIdInt = NumberUtils.toInt(AESUtil.decrypt(tfsUserId));
		
		UserFreezeRequest userFreezeRequest = new UserFreezeRequest();
		userFreezeRequest.setTfsUserId(tfsUserIdInt);
		userFreezeRequest.setStatus(status);
		
		try {
			UserFreezeResponse userFreezeResponse = titanFinancialUserService.freezeUser(userFreezeRequest);
			if(userFreezeResponse.isResult()){
				putSuccess("操作成功");
				return toJson();
			}else{
				putSysError(userFreezeResponse.getReturnMessage());
				return toJson();
			}
		} catch (GlobalServiceException e) {
//			log.error(",冻结或者解冻失败,请求参数：tfsUserId:"+tfsUserId+",status:"+status+",操作发起用户username："+getSAASLoginName(), e);
			putSysError(WebConstant.CONTROLLER_ERROR_MSG);
			return toJson();
		}
		
				
	}
	
	/**
	 * 解除权限
	 * @param tfsUserId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/setting/cancel-permission")
	@AccessPermission(allowRoleCode={CommonConstant.ROLECODE_ADMIN})
	public String cancelPermission(String tfsUserId){
		if(!StringUtil.isValidString(tfsUserId)){
			putSysError("请求参数错误");
			return toJson();
		}
		int tfsUserIdInt = 0;
		tfsUserIdInt = NumberUtils.toInt(AESUtil.decrypt(tfsUserId));
		CancelPermissionRequest cancelPermissionRequest = new CancelPermissionRequest();
		cancelPermissionRequest.setTfsUserId(tfsUserIdInt);
		try {
			CancelPermissionResponse cancelPermissionResponse =	titanFinancialUserService.cancelPermission(cancelPermissionRequest);
			if(cancelPermissionResponse.isResult()){
				putSuccess("操作成功");
				return toJson();
			}else{
				putSysError(cancelPermissionResponse.getReturnMessage());
				return toJson();
			}
		} catch (GlobalServiceException e) {
			log.error("解除权限操作失败，被解除的用户id[tfsUserId]:"+tfsUserId, e);
			putSysError(WebConstant.CONTROLLER_ERROR_MSG);
			return toJson();
		} catch (MessageServiceException e) {
			log.error("解除权限操作失败， 原因："+e.getMessage()+",被解除的用户id[tfsUserId]:"+tfsUserId, e);
			putSysError(e.getMessage());
			return toJson();
		}
	}
	
	
	
	/***
	 * 收付款费率公示
	 * @return
	 */
	@RequestMapping("/setting/fee")
	@AccessPermission(allowRoleCode={CommonConstant.ROLECODE_NO_LIMIT})
	public String fee(HttpServletRequest request,Model model){
		List<TitanRateDto> rateInfoList = new ArrayList<TitanRateDto>();
		RateConfigRequest rateConfigRequest = new RateConfigRequest();
		rateConfigRequest.setUserId(String.valueOf(getSession().getAttribute(WebConstant.SESSION_KEY_JR_USERID)));
		rateConfigRequest.setPayType(CashierItemTypeEnum.B2B_ITEM);
		rateConfigRequest.setPayType(CashierItemTypeEnum.QR_ITEM);

		RateConfigResponse rateConfigResponse = titanFinancialRateService.getRateConfigInfos(rateConfigRequest);
		CashierDeskQueryRequest cashierDeskQueryRequest = new CashierDeskQueryRequest();
		cashierDeskQueryRequest.setUserId(String.valueOf(getSession().getAttribute(WebConstant.SESSION_KEY_JR_USERID)));
		CashierDeskResponse response = titanCashierDeskService.queryCashierDesk(cashierDeskQueryRequest);
		if (response.isResult() && CollectionUtils.isNotEmpty(response.getCashierDeskDTOList())){
			for (CashierDeskDTO cashierDeskDTO : response.getCashierDeskDTOList()){
				if (cashierDeskDTO.getUsedFor() == 1){
					model.addAttribute("saasOpen", cashierDeskDTO.getIsOpen());
				}
				if (cashierDeskDTO.getUsedFor() == 3){
					model.addAttribute("unionOpen", cashierDeskDTO.getIsOpen());
				}
			}
		}
		if (CollectionUtils.isNotEmpty(rateConfigResponse.getRateInfoList())) {
			TitanRateDto rateDto = rateConfigResponse.getRateInfoList().get(0);
			model.addAttribute("rateInfo", rateDto);
			rateDto.setDescription(rateDto.getDescription().replace("费率",""));
			if (!rateDto.getBustype().equals(BusTypeEnum.QR_RATE.type)){
				rateDto.setDescription(rateDto.getDescription().replace("支付",""));
			}
		}
		return "setting/fee";
	}
	
	/**
	 * 新的收付款费率公示（暂时只查第三方支付费率）
	 * @author Jerry
	 * @date 2017年8月23日 下午2:24:07
	 */
	@AccessPermission(allowRoleCode={CommonConstant.ROLECODE_VIEW_39})
	@RequestMapping("/feeNew")
	public String feeNew(HttpServletRequest request,Model model){
		
		RateConfigResponse rateConfigResponse = null;
		List<TitanRateDto> rateInfoList = new ArrayList<TitanRateDto>();
		RateConfigRequest rateConfigRequest = new RateConfigRequest();
		
		rateConfigRequest.setUserId(String.valueOf(getSession().getAttribute(WebConstant.SESSION_KEY_JR_USERID)));
		rateConfigRequest.setPayType(CashierItemTypeEnum.QR_ITEM);
		rateConfigResponse = titanFinancialRateService.getRateConfigInfos(rateConfigRequest);
		rateInfoList = rateConfigResponse.getRateInfoList();
		if(CollectionUtils.isNotEmpty(rateInfoList)){
			model.addAttribute("titanRateDto", rateInfoList.get(0));
		}
		return "setting/fee";
		
	}
	
	/**
	 * 金融协议
	 * @return
	 */
	@AccessPermission(allowRoleCode={CommonConstant.ROLECODE_NO_LIMIT})
	@RequestMapping("/ex/protocol")
	public String protocol(){
		return "setting/protocol";
	}
	/**
	 * 金融协议
	 * @return
	 */
	@AccessPermission(allowRoleCode={CommonConstant.ROLECODE_NO_LIMIT})
	@RequestMapping("/ex/print-protocol")
	public String printProtocol(){
		return "setting/print-protocol";
	}
	
}
