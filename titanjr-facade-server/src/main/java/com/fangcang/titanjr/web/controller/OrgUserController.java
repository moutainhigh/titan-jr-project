package com.fangcang.titanjr.web.controller;

import com.fangcang.titanjr.common.exception.MessageServiceException;
import com.fangcang.titanjr.common.util.GenericValidate;
import com.fangcang.titanjr.common.util.Tools;
import com.fangcang.titanjr.dto.request.*;
import com.fangcang.titanjr.dto.response.OrganRegisterResponse;
import com.fangcang.titanjr.dto.response.UserInfoResponse;
import com.fangcang.titanjr.facade.TitanFinancialPermissionFacade;
import com.fangcang.titanjr.rest.request.OrgRegisterRequest;
import com.fangcang.titanjr.rest.response.OrgRegisterResponse;
import com.fangcang.titanjr.service.TitanCashierDeskService;
import com.fangcang.titanjr.service.TitanFinancialOrganService;
import com.fangcang.titanjr.service.TitanFinancialUserService;
import com.fangcang.titanjr.web.RegexValidator;
import com.fangcang.util.StringUtil;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.DecimalFormat;

@RestController
@Api(value = "UserOrgAPI")
public class OrgUserController {

	private static final Log log = LogFactory	.getLog(OrgUserController.class);

	@Autowired
	TitanFinancialOrganService titanFinancialOrganService;

	@Autowired
	TitanFinancialUserService titanFinancialUserService;

	@Autowired
	TitanCashierDeskService titanCashierDeskService;

	@Autowired
	TitanFinancialPermissionFacade titanFinancialPermissionFacade;

	@RequestMapping(value = "/orgUser/orgRegister", method = RequestMethod.POST)
	@ApiOperation(value = "机构注册接口", produces = "application/json", httpMethod = "POST",
			response = OrgRegisterResponse.class, notes = "机构注册")
	public OrgRegisterResponse orgRegister(@ApiParam(required = true, name = "registerRequest", value = "机构信息json数据")
									  @RequestBody OrgRegisterRequest registerRequest, HttpServletRequest request) {
		log.info("添加机构操作,参数registerRequest："+Tools.gsonToString(registerRequest));
		OrgRegisterResponse registerResponse = new OrgRegisterResponse();
		if (!GenericValidate.validate(registerRequest)){
			registerResponse.putParamError();
			log.error("参数校验失败，参数registerRequest："+Tools.gsonToString(registerRequest));
			return registerResponse;
		}
		if (!RegexValidator.isPhone(registerRequest.getConnectPhone()) &&
				!RegexValidator.isEmail(registerRequest.getEmail())){
			registerResponse.putParamError();
			log.error("必须有一个合法的邮箱或手机号，参数registerRequest："+Tools.gsonToString(registerRequest));
			return registerResponse;
		}

		//查询证件号是否存在

		OrganRegisterRequest organRegisterRequest = new OrganRegisterRequest();
		organRegisterRequest.setRegisterSource(registerRequest.getRegisterSourceEnum().key);
		organRegisterRequest.setOrgImageInfoList(null);
		organRegisterRequest.setEmail(registerRequest.getEmail());

		if (RegexValidator.isPhone(registerRequest.getConnectPhone())) {
			String usedPhone = validLoginName(registerRequest.getConnectPhone());
			organRegisterRequest.setUserloginname(usedPhone);
		} else{
			if (RegexValidator.isEmail(registerRequest.getEmail())){
				String usedEmail = validLoginName(registerRequest.getEmail());
				organRegisterRequest.setUserloginname(usedEmail);
			}
		}
		organRegisterRequest.setPassword(null);
		organRegisterRequest.setImageid(null);
		organRegisterRequest.setConnect(registerRequest.getConnect());
		organRegisterRequest.setMobileTel(registerRequest.getConnectPhone());
		organRegisterRequest.setCoopUserId(registerRequest.getCoopUserId());
		organRegisterRequest.setFcLoginUserName(registerRequest.getUserLoginName());
		organRegisterRequest.setMerchantCode(registerRequest.getCoopOrgCode());
		organRegisterRequest.setMerchantname(registerRequest.getCoopOrgName());
		organRegisterRequest.setCheckResKey(null);

		organRegisterRequest.setOrgName(registerRequest.getCoopOrgName());
		organRegisterRequest.setUserType(1);

		try {
			OrganRegisterResponse organRegisterResponse = titanFinancialOrganService.registerFinancialOrgan(organRegisterRequest);
			if (organRegisterResponse.isResult()){
				log.info("注册成功，参数registerRequest："+Tools.gsonToString(registerRequest));
				registerResponse.setCoopOrgCode(registerRequest.getCoopOrgCode());
				registerResponse.setCoopUserId(registerRequest.getCoopUserId());
				registerResponse.setJrOrgCode(organRegisterResponse.getOrgCode());
				registerResponse.setJrUserId(String.valueOf(organRegisterResponse.getTfsUserId()));
				registerResponse.putSuccess();
			} else {
				log.error("注册失败,返回消息:" + organRegisterResponse.getReturnMessage()+"，参数registerRequest："+Tools.gsonToString(registerRequest));
				registerResponse.putErrorResult(organRegisterResponse.getReturnCode(),
						organRegisterResponse.getReturnMessage());
				if(StringUtil.isValidString(organRegisterResponse.getOrgCode())){
					registerResponse.setJrOrgCode(organRegisterResponse.getOrgCode());
				}
			}
		} catch (MessageServiceException e) {
			registerResponse.putErrorResult(e.getCode(), e.getMessage());
			log.error("注册失败，返回消息:"+e.getMessage()+",参数registerRequest："+Tools.gsonToString(registerRequest), e);
		} catch (Exception e) {
			registerResponse.putSysError();
			log.error("" , e);
		}

		return registerResponse;
	}

	//校验如果出现重复手机号或者邮箱，则匹配生成新的
	private String validLoginName(String loginName) {
		UserInfoQueryRequest userInfoQueryRequest = new UserInfoQueryRequest();
		userInfoQueryRequest.setUserLoginName(loginName);
		UserInfoResponse userInfoResponse = titanFinancialUserService.queryFinancialUser(userInfoQueryRequest);
		//不存在表明当前或者邮箱可用
		if (!userInfoResponse.isResult() || CollectionUtils.isEmpty(userInfoResponse.getUserInfoDTOList())){
			return loginName;
		} else {
			DecimalFormat decimalFormat = new DecimalFormat("##");
			String formatNumber = decimalFormat.format(Math.random() *100);
			loginName = loginName + "-" + formatNumber;
			validLoginName(loginName);
			return loginName;
		}
	}

//	@RequestMapping(value = "/orgUser/test", method = RequestMethod.GET)
//	@ApiOperation(value = "测试接口", httpMethod = "GET", response = OrgRegisterRequest.class, notes = "测试")
//	public OrgRegisterRequest test(@ApiParam(name = "orgCode", value = "测试") @RequestParam(
//			value = "orgCode") String orgCode, HttpServletRequest request) {
//		log.info("测试单参数查询操作");
//		OrgRegisterRequest userResult = new OrgRegisterRequest();
//		//测试初始化收银台
//		CashierDeskInitRequest cashierDeskInitRequest = new CashierDeskInitRequest();
//		cashierDeskInitRequest.setConstId("M000016");
//		cashierDeskInitRequest.setUserId("TJM60020015");
//		cashierDeskInitRequest.setOperator("system");
//		try {
//			titanCashierDeskService.initCashierDesk(cashierDeskInitRequest);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		//测试更新收银台
//		CashierDeskUpdateRequest deskUpdateRequest = new CashierDeskUpdateRequest();
//		deskUpdateRequest.setUserId("TJM60020015");
//		deskUpdateRequest.setUsedList(new ArrayList<Integer>());
//		deskUpdateRequest.getUsedList().add(3);
//		deskUpdateRequest.getUsedList().add(4);
//		deskUpdateRequest.setIsOpen(0);
//		deskUpdateRequest.setDeskName("123124");
//		try {
//			titanCashierDeskService.updateCashierDesk(deskUpdateRequest);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		//测试查询收银台
//		CashierDeskQueryRequest cashierDeskQueryRequest = new CashierDeskQueryRequest();
//		cashierDeskQueryRequest.setUserId("TJM60020015");
//		CashierDeskResponse response = titanCashierDeskService.queryCashierDesk(cashierDeskQueryRequest);
//		log.info(response);
//
//		AccountInfoRequest accountInfo = new AccountInfoRequest();
//		accountInfo.setMerchantCode("TJM60020015");
//		CheckAccountResponse checkAccountResponse = null;
//		checkAccountResponse = titanFinancialPermissionFacade.isFinanceAccount(accountInfo);
//		accountInfo.setPaySourceEnum(PaySourceEnum.TRADING_PLATFORM_PC);
//		checkAccountResponse = titanFinancialPermissionFacade.isFinanceAccount(accountInfo);
//		accountInfo.setPaySourceEnum(PaySourceEnum.DISTRIBUTION_PC);
//		checkAccountResponse = titanFinancialPermissionFacade.isFinanceAccount(accountInfo);
//		System.out.println(checkAccountResponse);
//		return userResult;
//	}

}
