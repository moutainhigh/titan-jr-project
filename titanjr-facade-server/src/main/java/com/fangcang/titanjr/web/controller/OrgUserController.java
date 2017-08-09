package com.fangcang.titanjr.web.controller;

import com.fangcang.titanjr.rest.request.OrgRegisterRequest;
import com.fangcang.titanjr.rest.response.OrgRegisterResponse;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@RestController
@Api(value = "UserOrgAPI")
public class OrgUserController {

	private static final Log log = LogFactory	.getLog(OrgUserController.class);


	@RequestMapping(value = "/orgUser/orgRegister", method = RequestMethod.POST)
	@ApiOperation(value = "机构注册接口", produces = "application/json", httpMethod = "POST",
			response = OrgRegisterResponse.class, notes = "机构注册")
	public OrgRegisterResponse orgRegister(@ApiParam(required = true, name = "userDTO", value = "机构信息json数据")
									  @RequestBody OrgRegisterRequest registerRequest, HttpServletRequest request) {
		log.info("测试添加机构操作");
		request.getSession();
		OrgRegisterResponse registerResponse = new OrgRegisterResponse();
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
