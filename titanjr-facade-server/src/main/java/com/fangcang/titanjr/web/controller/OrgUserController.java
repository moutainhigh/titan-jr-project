package com.fangcang.titanjr.web.controller;

import com.fangcang.titanjr.common.exception.GlobalServiceException;
import com.fangcang.titanjr.common.exception.MessageServiceException;
import com.fangcang.titanjr.common.util.GenericValidate;
import com.fangcang.titanjr.dto.request.OrganRegisterRequest;
import com.fangcang.titanjr.dto.response.OrganRegisterResponse;
import com.fangcang.titanjr.rest.request.OrgRegisterRequest;
import com.fangcang.titanjr.rest.response.OrgRegisterResponse;
import com.fangcang.titanjr.service.TitanFinancialOrganService;
import com.fangcang.titanjr.web.RegexValidator;
import com.fangcang.util.StringUtil;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@RestController
@Api(value = "UserOrgAPI")
public class OrgUserController {

	private static final Log log = LogFactory	.getLog(OrgUserController.class);

	@Autowired
	TitanFinancialOrganService titanFinancialOrganService;

	@RequestMapping(value = "/orgUser/orgRegister", method = RequestMethod.POST)
	@ApiOperation(value = "机构注册接口", produces = "application/json", httpMethod = "POST",
			response = OrgRegisterResponse.class, notes = "机构注册")
	public OrgRegisterResponse orgRegister(@ApiParam(required = true, name = "registerRequest", value = "机构信息json数据")
									  @RequestBody OrgRegisterRequest registerRequest, HttpServletRequest request) {
		log.info("添加机构操作");
		OrgRegisterResponse registerResponse = new OrgRegisterResponse();
		if (!GenericValidate.validate(registerRequest)){
			registerResponse.putParamError();
			log.error("参数校验失败");
			return registerResponse;
		}
		if (!RegexValidator.isPhone(registerRequest.getConnectPhone()) &&
				!RegexValidator.isEmail(registerRequest.getEmail())){
			registerResponse.putParamError();
			log.error("必须有一个合法的邮箱或手机号");
			return registerResponse;
		}

		//查询证件号是否存在

		OrganRegisterRequest organRegisterRequest = new OrganRegisterRequest();
		organRegisterRequest.setUserName(registerRequest.getCoopUserName());
		organRegisterRequest.setRegisterSource(registerRequest.getRegisterSourceEnum().key);
		organRegisterRequest.setOrgImageInfoList(null);
		organRegisterRequest.setEmail(registerRequest.getEmail());

		if (RegexValidator.isPhone(registerRequest.getConnectPhone())) {
			organRegisterRequest.setUserloginname(registerRequest.getConnectPhone());
		} else if (RegexValidator.isEmail(registerRequest.getEmail())){
			organRegisterRequest.setUserloginname(registerRequest.getEmail());
		}
		organRegisterRequest.setPassword(null);
		organRegisterRequest.setImageid(null);

		organRegisterRequest.setCoopUserId(registerRequest.getCoopUserId());
		organRegisterRequest.setFcLoginUserName(registerRequest.getUserLoginName());
		organRegisterRequest.setMerchantCode(registerRequest.getCoopOrgCode());
		organRegisterRequest.setMerchantname(registerRequest.getCoopOrgName());
		organRegisterRequest.setIsAutoCheck(true);
		organRegisterRequest.setCheckResKey(null);

		organRegisterRequest.setIsAddOrgSub(false);
		organRegisterRequest.setOrgName(registerRequest.getCoopOrgName());
		organRegisterRequest.setUserType(1);

		try {
			OrganRegisterResponse organRegisterResponse = titanFinancialOrganService.registerFinancialOrgan(organRegisterRequest);
			if (organRegisterResponse.isResult()){
//				registerResponse.setCoopOrgCode();
//				registerResponse.setCoopUserId();
//				registerResponse.setJrOrgCode();
//				registerResponse.setJrUserId();
				registerResponse.putSuccess();
			}
		} catch (Exception e) {
			registerResponse.putSysError();
			log.error("" , e);
		}

		return registerResponse;
	}

	@RequestMapping(value = "/orgUser/queryOrgInfo", method = RequestMethod.GET)
	@ApiOperation(value = "查询机构信息接口", httpMethod = "GET", response = OrgRegisterRequest.class, notes = "机构查询")
	public OrgRegisterRequest queryOrgInfo(@ApiParam(required = true, name = "orgCode", value = "机构编码") @RequestParam(
			value = "orgCode") String orgCode, HttpServletRequest request) {
		log.info("测试单参数查询操作");
		OrgRegisterRequest userResult = new OrgRegisterRequest();
		return userResult;
	}

}
