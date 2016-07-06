package com.fangcang.titanjr.web.controller;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fangcang.titanjr.common.exception.GlobalServiceException;
import com.fangcang.titanjr.dto.bean.UserInfoDTO;
import com.fangcang.titanjr.dto.request.AccountRequest;
import com.fangcang.titanjr.dto.request.AccountUpdateRequest;
import com.fangcang.titanjr.dto.request.PayPasswordRequest;
import com.fangcang.titanjr.dto.request.UserInfoQueryRequest;
import com.fangcang.titanjr.dto.response.AccountResponse;
import com.fangcang.titanjr.dto.response.AccountUpdateResponse;
import com.fangcang.titanjr.dto.response.UserInfoPageResponse;
import com.fangcang.titanjr.dto.response.UserInfoResponse;
import com.fangcang.titanjr.entity.TitanUser;
import com.fangcang.titanjr.service.TitanFinancialAccountService;
import com.fangcang.titanjr.service.TitanFinancialUserService;
import com.fangcang.titanjr.web.controller.admin.OrgController;
import com.fangcang.titanjr.web.util.CommonConstant;
import com.fangcang.util.StringUtil;

/**
 * 付款密码设置
 * @author luoqinglong
 * @2016年7月1日
 */
@Controller
@RequestMapping("/setting")
public class SettingPasswordController extends BaseController{
	
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
	 * 第一次设置支付密码
	 * @return
	 */
	@RequestMapping("/pay-set-first")
	public String paySetFirst(String password){
		//支付密码必须为空，才不需要验证身份
//		UserInfoQueryRequest userInfoQueryRequest = new UserInfoQueryRequest();
//		userInfoQueryRequest.setTfsUserId(Integer.valueOf(getTfsUserId()));
//		try {
//			 
//					PayPasswordRequest payPasswordRequest = new PayPasswordRequest();
//					payPasswordRequest.setTfsuserid(getTfsUserId());
//					payPasswordRequest.setPayPassword(password);
//					userService.saveOrUpdatePayPassword(payPasswordRequest);
//			
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
		
		
		return "";
	}
	/**
	 * 通过原始密码修改密码
	 * @return
	 */
	@RequestMapping("/pay-set-password")
	public String paySetPassword(){
		return "";
	}
	/**
	 * 检查验证码是否正确
	 * @return 签名字符串
	 */
	@RequestMapping("/pay-set-check-code")
	public String checkCode(){
		
		//返回sign = md5(userloginname+time+key)
		return "";
	}
	
	/**
	 * 通过用户名修改支付密码
	 * @return
	 */
	@RequestMapping("/pay-set-userloginname")
	public String paySetUserLoginName(){
		//校验验证码是否通过 userloginname,time ,sign,
		// 时效性
		
		
		return "";
	}
	/**
	 * 设置小额免密支付开关（只有金服管理员才可以设置）
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/set-swicth")
	public String setSwitch(Integer allownopwdpay){
		if(allownopwdpay==null||(allownopwdpay!=0&&allownopwdpay!=1)){
			putSysError("参数异常");
			return toJson();
		}
		//TODO 只有金服管理员才可以设置
		UserInfoQueryRequest userInfoQueryRequest = new UserInfoQueryRequest();
		userInfoQueryRequest.setTfsUserId(Integer.valueOf(getTfsUserId()));
		UserInfoPageResponse userInfoPageResponse = userService.queryUserInfoPage(userInfoQueryRequest);
		
		if(userInfoPageResponse.getTitanUserPaginationSupport().getItemList().size()>0){
			TitanUser titanUser = userInfoPageResponse.getTitanUserPaginationSupport().getItemList().get(0);
			if(titanUser.getIsadmin()==0){
				putSysError("只有金融管理员才能执行该操作");
				return toJson();
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
				LOG.error("设置小额免密支付开关失败，参数:allownopwdpay["+allownopwdpay+"]", e);
				putSysError(CommonConstant.CONTROLLER_ERROR_MSG);
			}
		}
		
		
		return toJson();
	}
}
