package com.fangcang.titanjr.web.controller;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fangcang.titanjr.common.exception.GlobalServiceException;
import com.fangcang.titanjr.dto.bean.TitanUserBindInfoDTO;
import com.fangcang.titanjr.common.util.CommonConstant;
import com.fangcang.titanjr.common.util.MD5;
import com.fangcang.titanjr.dto.bean.UserInfoDTO;
import com.fangcang.titanjr.dto.request.AccountRequest;
import com.fangcang.titanjr.dto.request.AccountUpdateRequest;
import com.fangcang.titanjr.dto.request.UserInfoQueryRequest;
import com.fangcang.titanjr.dto.response.AccountResponse;
import com.fangcang.titanjr.dto.response.AccountUpdateResponse;
import com.fangcang.titanjr.dto.response.UserInfoPageResponse;
import com.fangcang.titanjr.dto.response.UserInfoResponse;
import com.fangcang.titanjr.entity.TitanUser;
import com.fangcang.titanjr.service.TitanFinancialAccountService;
import com.fangcang.titanjr.service.TitanFinancialUserService;
import com.fangcang.titanjr.web.annotation.AccessPermission;
import com.fangcang.titanjr.web.util.WebConstant;
import com.fangcang.util.StringUtil;

/**
 * 付款密码设置
 * @author luoqinglong
 * @2016年7月1日
 */
@Controller
@RequestMapping("/setting")
@AccessPermission(allowRoleCode={CommonConstant.ROLECODE_PAY_38})
public class SettingPasswordController extends BaseController{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Log LOG = LogFactory.getLog(SettingPasswordController.class);
	@Resource
	private TitanFinancialUserService userService;
	
	@Resource
	private TitanFinancialAccountService accountService;
	
	/***
	 * 付款密码设置
	 * @return
	 */
	@RequestMapping("/pay-set")
	public String paySet(Model model){
		int tfsUserId = Integer.valueOf(getTfsUserId());
		UserInfoQueryRequest userInfoQueryRequest = new UserInfoQueryRequest();
		userInfoQueryRequest.setTfsUserId(tfsUserId);
		UserInfoResponse userInfoResponse = userService.queryFinancialUser(userInfoQueryRequest);
		UserInfoDTO userInfo = userInfoResponse.getUserInfoDTOList().get(0);
		//是否设置了支付密码
		if(StringUtil.isValidString(userInfo.getPayPassword())){
			model.addAttribute("hasSetPayPass","1");//已设置
		}else{
			model.addAttribute("hasSetPayPass","0");//未设置
		}
		AccountRequest accountRequest = new AccountRequest();
		accountRequest.setUserid(getUserId());
		AccountResponse accountResponse = accountService.getAccount(accountRequest);
		
		//是否开启免密支付
		if(accountResponse.getAccountDTO()!=null&&accountResponse.getAccountDTO().getAllownopwdpay()==1){
			model.addAttribute("allownopwdpay","1");//开启
		}else{
			model.addAttribute("allownopwdpay","0");//关闭
		}
		return "setting/pay-set";
	}
	 
	/**
	 * 通过原始密码修改密码
	 * @return
	 */
	@RequestMapping("/modify-pwd")
	public String paySetPassword(){
		return "setting/modify-pwd";
	}
	
	/**
	 * 忘记原密码
	 * @return 
	 */
	@RequestMapping("/modify-pwd-forget")
	public String forgetPassword(){
		return "setting/modify-pwd-forget";
	}
	
	/**
	 * 忘记原密码
	 * @return 
	 */
	@RequestMapping("/modify-pwd-remember")
	public String rememberPassword(){
		return "setting/modify-pwd-remember";
	}
	
	 
	/**
	 * 设置小额免密支付开关（只有金服管理员才可以设置）
	 * @return
	 */
	
	@ResponseBody
	@RequestMapping("/set-swicth")
	@AccessPermission(allowRoleCode={CommonConstant.ROLECODE_ADMIN})
	public String setSwitch(Integer allownopwdpay,String payPassword){
		if(allownopwdpay==null||(allownopwdpay!=0&&allownopwdpay!=1)||(!StringUtil.isValidString(payPassword))){
			putSysError("参数异常");
			return toJson();
		}
		UserInfoQueryRequest userInfoQueryRequest = new UserInfoQueryRequest();
		userInfoQueryRequest.setTfsUserId(Integer.valueOf(getTfsUserId()));
		UserInfoPageResponse userInfoPageResponse = userService.queryUserInfoPage(userInfoQueryRequest);
		
		if(userInfoPageResponse.getTitanUserPaginationSupport().getItemList().size()>0){
			// 只有金服管理员才可以设置
			TitanUser titanUser = userInfoPageResponse.getTitanUserPaginationSupport().getItemList().get(0);
			if(titanUser.getIsadmin()==0){
				putSysError("只有金融管理员才能执行该操作");
				return toJson();
			}
			
			//未设置支付密码
			if(!StringUtil.isValidString(titanUser.getPaypassword())){
				return toJson(putSysError("请先设置支付密码"));
			}
			
			//密码是否正确
			if(!titanUser.getPaypassword().equals(MD5.MD5Encode(payPassword+titanUser.getPaySalt()))){
				return toJson(putSysError("支付密码错误"));
			}
			
			AccountUpdateRequest accountUpdateRequest = new AccountUpdateRequest();
			accountUpdateRequest.setUserId(getUserId());
			accountUpdateRequest.setAllownopwdpay(allownopwdpay);
			accountUpdateRequest.setOperator(getSAASLoginName());
			try {
				AccountUpdateResponse accountUpdateResponse = accountService.updateAccount(accountUpdateRequest);
				if(accountUpdateResponse.isResult()){
					putSuccess();
				}else{
					putSysError(accountUpdateResponse.getReturnMessage());
				}
			} catch (GlobalServiceException e) {
				LOG.error("设置小额免密支付开关失败，参数:allownopwdpay["+allownopwdpay+"],tfsuserId["+getTfsUserId()+"]", e);
				putSysError(WebConstant.CONTROLLER_ERROR_MSG);
			}
		}
		return toJson();
	}
	
	
	@ResponseBody
	@RequestMapping("/check_payPassword")
	public String checkPayPassword(String payPassword,String fcUserid) throws GlobalServiceException{
		String tfsUserid = null;
		if(StringUtil.isValidString(fcUserid)){
			TitanUserBindInfoDTO titanUserBindInfoDTO = new TitanUserBindInfoDTO();
			titanUserBindInfoDTO.setFcuserid(Long.parseLong(fcUserid));
			titanUserBindInfoDTO = userService.getUserBindInfoByFcuserid(titanUserBindInfoDTO);
			if(titanUserBindInfoDTO !=null && titanUserBindInfoDTO.getTfsuserid()!=null){
				tfsUserid = titanUserBindInfoDTO.getTfsuserid().toString();
			}
		}else{
			tfsUserid = this.getTfsUserId();
		}
		
		if(!StringUtil.isValidString(payPassword) || !StringUtil.isValidString(tfsUserid)){
			putSysError("参数错误");
			return toJson();
		}
		boolean istrue = userService.checkPayPassword(payPassword,tfsUserid);
		if(!istrue){
			putSysError("密码错误");
			return toJson();
		}
		putSuccess();
		return toJson();
	}
}
