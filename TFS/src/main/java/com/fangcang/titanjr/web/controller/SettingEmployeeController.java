package com.fangcang.titanjr.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.fangcang.titanjr.dto.bean.*;
import com.fangcang.titanjr.dto.request.*;
import com.fangcang.titanjr.dto.response.*;
import com.fangcang.titanjr.service.TitanCashierDeskService;
import com.fangcang.titanjr.service.TitanFinancialRateService;

import com.fangcang.titanjr.web.pojo.CashierSwitchPO;
import net.sf.json.JSONSerializer;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
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
import com.fangcang.titanjr.common.util.CommonConstant;
import com.fangcang.titanjr.common.util.GenericValidate;
import com.fangcang.titanjr.service.TitanFinancialOrganService;
import com.fangcang.titanjr.service.TitanFinancialUserService;
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
@RequestMapping("/setting")
@AccessPermission(allowRoleCode={CommonConstant.ROLECODE_ADMIN})
public class SettingEmployeeController extends BaseController{
	/** 
	 * 
	 */
	private static final long serialVersionUID = 376395101677758942L;

	private static final Log log = LogFactory.getLog(SettingEmployeeController.class);
    
    @Autowired
    private TitanFinancialUserService titanFinancialUserService;
    
    @Autowired
    private TitanFinancialOrganService organService;

	@Autowired
	private TitanFinancialRateService titanFinancialRateService;

	@Autowired
	private TitanCashierDeskService titanCashierDeskService;

	/**
	 * 左侧菜单（本地调试使用）
	 * @return
	 */
    @AccessPermission(allowRoleCode={CommonConstant.ROLECODE_NO_LIMIT})
	@RequestMapping("/slidemenu")
	public String slidemenu(){
		return "slidemenu/jr-setting-menu";
	}
	/***
	 * 员工权限设置(查询员工)
	 * @return
	 */
	@RequestMapping("/employee")
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
	@RequestMapping("/employee-table")
	public String employeeTable(String tfsUserLoginName,String userName, Integer pageNo,Integer pageSize,Model model){
		if(pageNo==null){
			pageNo = 1;
		}
		if(pageSize==null||pageSize==0){
			pageSize = 15;
		}
		Integer tfsUserId = Integer.valueOf((String)getSession().getAttribute(WebConstant.SESSION_KEY_JR_TFS_USERID));
		UserInfoQueryRequest userInfoQueryRequest = new UserInfoQueryRequest();
		userInfoQueryRequest.setTfsUserId(tfsUserId);
		userInfoQueryRequest.setPageSize(pageSize);
		userInfoQueryRequest.setBindIsactive(1);
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
	@RequestMapping("/fc-employee")
	public String fcEmployee(){
		return "setting/fc-employee-list";
	}
	
	/**
	 * 新增员工权限-表格数据(房仓员工查询)
	 * @return
	 */
	@RequestMapping("/fc-employee-table")
	public String fcEmployeeTable(HttpServletRequest request,FcEmployeeTablePojo  fcEmployeeTablePojo,Model model){
		SaaSUserRoleRequest saaSUserRoleRequest = new SaaSUserRoleRequest();
		String merchantCode = (String)request.getSession().getAttribute(WebConstant.SESSION_KEY_CURRENT_MERCHANT_CODE);
		
		saaSUserRoleRequest.setMerchantCode(merchantCode);
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
	@RequestMapping("/employee-add")
	public String employeeAdd(Integer tfsUserId,Integer fcUserId,Model model){
		
		model.addAttribute("tfsUserId", tfsUserId);//新增该变量没有值
		model.addAttribute("fcUserId", fcUserId);
		//SAAS员工信息
		if(fcUserId!=null&&fcUserId>0){
			String merchantCode = (String)getSession().getAttribute(WebConstant.SESSION_KEY_CURRENT_MERCHANT_CODE);
			SaaSUserRoleRequest saaSUserRoleRequest = new SaaSUserRoleRequest();
			saaSUserRoleRequest.setFcUserId(fcUserId);
			saaSUserRoleRequest.setMerchantCode(merchantCode);
			SaaSUserRoleResponse saaSUserRoleResponse = new SaaSUserRoleResponse();
			try {
				saaSUserRoleResponse = titanFinancialUserService.querySaaSUserRolePage(saaSUserRoleRequest);
				
			} catch (GlobalServiceException e) {
				log.error("查询saas用户失败，参数saaSUserRoleRequest:" +ToStringBuilder.reflectionToString(saaSUserRoleRequest), e);
				model.addAttribute("errormsg", "数据信息不存在");
				return "error";
			}
			SaaSMerchantUserDTO saaSMerchantUserDTO =saaSUserRoleResponse.getPaginationSupport().getItemList().get(0);
			model.addAttribute("fcUserName", saaSMerchantUserDTO.getUserName());
			model.addAttribute("fcMobile", saaSMerchantUserDTO.getMobileNum());
			
		}
		
		if(tfsUserId!=null&&tfsUserId>0){
			UserInfoQueryRequest userInfoQueryRequest = new UserInfoQueryRequest();
			userInfoQueryRequest.setTfsUserId(tfsUserId);
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
			model.addAttribute("errormsg", "数据信息不存在");
			return "error";
		}
		
		return "setting/employee-add";
	}
	
	/***
	 * 保存添加员工
	 * @param employeePojo
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/save-employee")
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
		//TODO 校验待新增的用户是不是属于该商家
		
		String merchantCode = (String)getSession().getAttribute(WebConstant.SESSION_KEY_CURRENT_MERCHANT_CODE);
		String userId = (String)getSession().getAttribute(WebConstant.SESSION_KEY_JR_USERID);
		
		SaaSUserRoleRequest saaSUserRoleRequest = new SaaSUserRoleRequest();
		saaSUserRoleRequest.setFcUserId(employeePojo.getFcUserId());
		saaSUserRoleRequest.setMerchantCode(merchantCode);
		SaaSUserRoleResponse saaSUserRoleResponse = new SaaSUserRoleResponse();
		try {
			saaSUserRoleResponse = titanFinancialUserService.querySaaSUserRolePage(saaSUserRoleRequest);
		} catch (GlobalServiceException e) {
			log.error("查询saas用户失败，参数employeePojo:" +ToStringBuilder.reflectionToString(employeePojo), e);
			putSysError(WebConstant.CONTROLLER_ERROR_MSG);
			return toJson();
		}
		SaaSMerchantUserDTO saaSMerchantUserDTO =saaSUserRoleResponse.getPaginationSupport().getItemList().get(0);
		//新增用户
		UserRegisterRequest userRegisterRequest = new UserRegisterRequest();
		userRegisterRequest.setOperator(getSAASLoginName());
		userRegisterRequest.setOperateTime(DateUtil.dateToString(DateUtil.getCurrentDate()));
    	userRegisterRequest.setIsAdminUser(0);
		userRegisterRequest.setFcLoginUserName(saaSMerchantUserDTO.getUserLoginName());
    	userRegisterRequest.setLoginUserName(employeePojo.getReceiveAddress());
    	userRegisterRequest.setUserName(employeePojo.getUserName());
    	userRegisterRequest.setMerchantCode(merchantCode);
    	userRegisterRequest.setOrgCode(userId);
    	userRegisterRequest.setRoleIdList(toList(employeePojo.getCheckedRoleId()));
    	userRegisterRequest.setUnselectRoleIdList(toList(employeePojo.getUncheckedRoleId()));
    	//生成一个密码
    	userRegisterRequest.setPassword(RandomStringUtils.randomAlphabetic(6));
    	userRegisterRequest.setRegisterSource(RegSourceEnum.SAAS.getType());
    	userRegisterRequest.setUserId(userId);//金服机构
    	try {
			UserRegisterResponse respose = titanFinancialUserService.registerFinancialUser(userRegisterRequest);
			if(respose.isResult()){
				putSuccess("添加成功");
				if(verifyCheckCodeResponse.getCodeId()>0){
					UpdateCheckCodeRequest updateCheckCodeRequest = new UpdateCheckCodeRequest();
					updateCheckCodeRequest.setCodeId(verifyCheckCodeResponse.getCodeId());
					updateCheckCodeRequest.setIsactive(0);//改为无效
					organService.useCheckCode(updateCheckCodeRequest);
				}
			}else{
				putSysError(respose.getReturnMessage());
			}
		} catch (Exception e) {
			log.error("添加saas用户失败，参数employeePojo:" +ToStringBuilder.reflectionToString(employeePojo), e);
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
	 * 修改时保存员工信息
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/save-update-user")
	public String saveUpdateUser(EmployeePojo employeePojo){
		if(employeePojo.getTfsUserId()==null||employeePojo.getTfsUserId()<=0){
			putSysError("参数错误");
			return toJson();
		}
		UpdateUserRequest updateUserRequest = new UpdateUserRequest();
		
		updateUserRequest.setTfsUserId(employeePojo.getTfsUserId());
		updateUserRequest.setUserName(employeePojo.getUserName());
		updateUserRequest.setOperator(getSAASLoginName());
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
	@RequestMapping("/freeze-user")
	public String freezeUser(Integer status,Integer tfsUserId){
		if((status==null||status<0)||(tfsUserId==null||tfsUserId<0)){
			putSysError("参数错误");
			return toJson();
		}
		UserFreezeRequest userFreezeRequest = new UserFreezeRequest();
		userFreezeRequest.setTfsUserId(tfsUserId);
		userFreezeRequest.setOperator(getSAASLoginName());
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
			log.error(",冻结或者解冻失败,请求参数：tfsUserId:"+tfsUserId+",status:"+status+",操作发起用户username："+getSAASLoginName(), e);
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
	@RequestMapping("/cancel-permission")
	public String cancelPermission(Integer tfsUserId){
		String merchantcode = (String)getSession().getAttribute(WebConstant.SESSION_KEY_CURRENT_MERCHANT_CODE);
		CancelPermissionRequest cancelPermissionRequest = new CancelPermissionRequest();
		cancelPermissionRequest.setTfsUserId(tfsUserId);
		cancelPermissionRequest.setOperator(getSAASLoginName());
		cancelPermissionRequest.setMerchantcode(merchantcode);
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
			log.error("解除权限操作失败，操作发起用户username："+getSAASLoginName()+",tfsUserId:"+tfsUserId, e);
			putSysError(WebConstant.CONTROLLER_ERROR_MSG);
			return toJson();
		} catch (MessageServiceException e) {
			log.error("解除权限操作失败， 原因：参数错误。操作发起用户username："+getSAASLoginName()+",tfsUserId:"+tfsUserId, e);
			putSysError(e.getMessage());
			return toJson();
		}
	}
	
	
	
	/***
	 * 收付款费率公示
	 * 费率机构更改  update by jerry
	 * @return
	 */
	@AccessPermission(allowRoleCode={CommonConstant.ROLECODE_VIEW_39})
	@RequestMapping("/fee")
	public String fee(HttpServletRequest request,Model model){
		RateConfigRequest rateConfigRequest = new RateConfigRequest();
		rateConfigRequest.setUserId(String.valueOf(getSession().getAttribute(WebConstant.SESSION_KEY_JR_USERID)));

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

		RateConfigResponse rateConfigResponse = titanFinancialRateService.getRateConfigInfos(rateConfigRequest);
		model.addAttribute("rateInfoList", rateConfigResponse.getRateInfoList());

		return "setting/fee";
	}

	/**
	 * 开关收银台操作
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/switch-cashier")
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
	 * 金融协议
	 * @return
	 */
	@AccessPermission(allowRoleCode={CommonConstant.ROLECODE_VIEW_39})
	@RequestMapping("/protocol")
	public String protocol(){
		return "setting/protocol";
	}
	
	
}
