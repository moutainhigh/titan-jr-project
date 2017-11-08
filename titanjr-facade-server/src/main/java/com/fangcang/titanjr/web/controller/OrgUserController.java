package com.fangcang.titanjr.web.controller;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fangcang.titanjr.common.exception.GlobalServiceException;
import com.fangcang.titanjr.common.exception.MessageServiceException;
import com.fangcang.titanjr.common.util.GenericValidate;
import com.fangcang.titanjr.common.util.Tools;
import com.fangcang.titanjr.dto.bean.TitanUserBindInfoDTO;
import com.fangcang.titanjr.dto.request.OrganRegisterRequest;
import com.fangcang.titanjr.dto.request.UpdateUserRequest;
import com.fangcang.titanjr.dto.request.UserInfoQueryRequest;
import com.fangcang.titanjr.dto.request.UserRegisterRequest;
import com.fangcang.titanjr.dto.response.OrganRegisterResponse;
import com.fangcang.titanjr.dto.response.UpdateUserResponse;
import com.fangcang.titanjr.dto.response.UserInfoPageResponse;
import com.fangcang.titanjr.dto.response.UserInfoResponse;
import com.fangcang.titanjr.dto.response.UserRegisterResponse;
import com.fangcang.titanjr.entity.TitanUser;
import com.fangcang.titanjr.facade.TitanFinancialPermissionFacade;
import com.fangcang.titanjr.response.BaseResponse;
import com.fangcang.titanjr.rest.request.AddEmployeeRequest;
import com.fangcang.titanjr.rest.request.ModifyEmployeeInfoRequest;
import com.fangcang.titanjr.rest.request.OrgRegisterRequest;
import com.fangcang.titanjr.rest.response.AddEmployeeResponse;
import com.fangcang.titanjr.rest.response.OrgRegisterResponse;
import com.fangcang.titanjr.service.TitanCashierDeskService;
import com.fangcang.titanjr.service.TitanFinancialOrganService;
import com.fangcang.titanjr.service.TitanFinancialUserService;
import com.fangcang.titanjr.web.RegexValidator;
import com.fangcang.util.DateUtil;
import com.fangcang.util.StringUtil;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

import net.sf.json.JSONSerializer;

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
	
	private static Map<String, Long> roleCodeMap = new HashMap<String, Long>(){
		{put("1", 38L);put("2", 39L);put("4", 40L);}
	};
	

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

		if (!StringUtil.isValidString(registerRequest.getConnectPhone()) &&
				!StringUtil.isValidString(registerRequest.getEmail())){
			registerResponse.putParamError();
			log.error("必须有一个合法的邮箱或手机号，参数registerRequest："+Tools.gsonToString(registerRequest));
			return registerResponse;
		}

		//查询证件号是否存在

		OrganRegisterRequest organRegisterRequest = new OrganRegisterRequest();
		organRegisterRequest.setRegisterSource(registerRequest.getRegisterSourceEnum().key);
		organRegisterRequest.setOrgImageInfoList(null);
		organRegisterRequest.setEmail(registerRequest.getEmail());

		if (StringUtil.isValidString(registerRequest.getConnectPhone()) &&
				RegexValidator.isPhone(registerRequest.getConnectPhone())) {
			String usedPhone = validLoginName(registerRequest.getConnectPhone());
			organRegisterRequest.setUserloginname(usedPhone);
		} else if (StringUtil.isValidString(registerRequest.getEmail()) &&
				RegexValidator.isEmail(registerRequest.getEmail())) {
			String usedEmail = validLoginName(registerRequest.getEmail());
			organRegisterRequest.setUserloginname(usedEmail);
		} else {
			String usedPhone = validLoginName(registerRequest.getConnectPhone() + registerRequest.getEmail());
			organRegisterRequest.setUserloginname(usedPhone);
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
			log.info("开始调用服务注册，参数为：" + Tools.gsonToString(organRegisterRequest));
			OrganRegisterResponse organRegisterResponse = titanFinancialOrganService.registerFinancialOrgan(organRegisterRequest);
			if (organRegisterResponse.isResult()){
				log.info("注册成功，参数registerRequest："+Tools.gsonToString(registerRequest));
				registerResponse.setCoopOrgCode(registerRequest.getCoopOrgCode());
				registerResponse.setCoopUserId(registerRequest.getCoopUserId());
				registerResponse.setJrOrgCode(organRegisterResponse.getOrgCode());
				registerResponse.setJrUserId(String.valueOf(organRegisterResponse.getTfsUserId()));
				registerResponse.putSuccess();
			} else {
				log.error("注册失败,返回消息:" + Tools.gsonToString(organRegisterResponse)+"，参数registerRequest："+Tools.gsonToString(registerRequest));
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

	@RequestMapping(value = "/user/addEmployee", method = RequestMethod.POST)
	@ApiOperation(value = "添加金融员工接口", produces = "application/json", httpMethod = "POST",
			response = AddEmployeeResponse.class, notes = "添加金融员工")
	public AddEmployeeResponse addEmployee(@ApiParam(required = true, name = "addEmployeeRequest", value = "新用户信息json数据")
									  @RequestBody AddEmployeeRequest registerRequest, HttpServletRequest request) {
		AddEmployeeResponse addEmployeeResponse = new AddEmployeeResponse();
		log.info("添加金融员工接口，参数："+Tools.gsonToString(registerRequest));
		if (!GenericValidate.validate(registerRequest)) {
            addEmployeeResponse.putErrorResult("必填参数不能为空");
            return addEmployeeResponse;
        }
		//检查来源是否合法
		TitanUserBindInfoDTO userBindInfoExistDTOParam = new TitanUserBindInfoDTO();
		userBindInfoExistDTOParam.setMerchantcode(registerRequest.getCoopOrgCode());
		userBindInfoExistDTOParam.setFcuserid(Long.valueOf(registerRequest.getCoopUserIdExist()));
		TitanUserBindInfoDTO coopUserInfoExistDTO = titanFinancialUserService.getUserBindInfoByFcuserid(userBindInfoExistDTOParam);
		if(coopUserInfoExistDTO==null){
			addEmployeeResponse.putErrorResult("该合作方尚未开通泰坦金融");
			log.info("添加金融员工接口，该合作方尚未开通泰坦金融，参数："+Tools.gsonToString(registerRequest));
            return addEmployeeResponse;
		}
		
		//检查是否已经注册
		TitanUserBindInfoDTO userBindInfoDTOParam = new TitanUserBindInfoDTO();
		userBindInfoDTOParam.setMerchantcode(registerRequest.getCoopOrgCode());
		userBindInfoDTOParam.setFcuserid(Long.valueOf(registerRequest.getCoopUserId()));
		TitanUserBindInfoDTO userBindInfoDTO = titanFinancialUserService.getUserBindInfoByFcuserid(userBindInfoDTOParam);
		if(userBindInfoDTO!=null){
			log.error("该员工已经添加，请勿重复添加.添加请求参数："+Tools.gsonToString(registerRequest)+",已存在员工："+Tools.gsonToString(userBindInfoDTO));
			addEmployeeResponse.putErrorResult("该员工已经添加，请勿重复添加");
			addEmployeeResponse.setTfsUserId(userBindInfoDTO.getTfsuserid().toString());
            return addEmployeeResponse;
		}
		UserInfoQueryRequest userInfoQueryRequest = new UserInfoQueryRequest();
		userInfoQueryRequest.setTfsUserId(coopUserInfoExistDTO.getTfsuserid());
		UserInfoPageResponse userInfoResponse = titanFinancialUserService.queryUserInfoPage(userInfoQueryRequest);
		TitanUser titanUser = userInfoResponse.getTitanUserPaginationSupport().getItemList().get(0);
		//新增用户
		UserRegisterRequest userRegisterRequest = new UserRegisterRequest();
		userRegisterRequest.setOperateTime(DateUtil.dateToString(DateUtil.getCurrentDate()));
    	userRegisterRequest.setIsAdminUser(0);
		userRegisterRequest.setFcLoginUserName(registerRequest.getCoopLoginUserName());
    	userRegisterRequest.setUserName(registerRequest.getCoopUserName());
    	userRegisterRequest.setMerchantCode(registerRequest.getCoopOrgCode());
    	userRegisterRequest.setCoopUserId(registerRequest.getCoopUserId());
    	userRegisterRequest.setOrgCode(titanUser.getUserid());
    	userRegisterRequest.setUserId(titanUser.getUserid());//金服机构
    	userRegisterRequest.setLoginUserName(registerRequest.getLoginUserName());
    	userRegisterRequest.setRoleIdList(roleCodeToRoleId(registerRequest.getRoleCode()));
    	userRegisterRequest.setUnselectRoleIdList(null);
    	userRegisterRequest.setPassword(RandomStringUtils.randomAlphabetic(6));//生成一个密码
    	userRegisterRequest.setRegisterSource(coopUserInfoExistDTO.getCooptype());
		    	
    	try {
			UserRegisterResponse respose = titanFinancialUserService.registerFinancialUser(userRegisterRequest);
			if(respose.isResult()){
				addEmployeeResponse.putSuccess("添加成功");
				addEmployeeResponse.setTfsUserId(respose.getTfsUserId()+"");
			}else{
				addEmployeeResponse.putErrorResult(respose.getReturnMessage());
				return addEmployeeResponse;
			}
		} catch (Exception e) {
			log.error("添加金融员工失败，参数："+Tools.gsonToString(registerRequest),e);
			addEmployeeResponse.putErrorResult("添加金融员工失败");
			return addEmployeeResponse;
		}
		return addEmployeeResponse;
	}
	
	private List<Long> roleCodeToRoleId(String roleCode){
		String[] roleCodes = roleCode.split("\\|");
		List<Long> selectRoleList = new ArrayList<Long>();
		for(String role : roleCodes){
			if(roleCodeMap.get(role)!=null){
				selectRoleList.add(roleCodeMap.get(role));
			}
		}
		return selectRoleList;
	}
	
	
	@RequestMapping(value = "/user/modifyEmployeeInfo", method = RequestMethod.POST)
	@ApiOperation(value = "修改金融员工信息接口", produces = "application/json", httpMethod = "POST",
			response = AddEmployeeResponse.class, notes = "修改金融员工信息接口")
	public BaseResponse modifyEmployeeInfo(@ApiParam(required = true, name = "modifyEmployeeInfoRequest", value = "用户基本信息json数据")
	  @RequestBody ModifyEmployeeInfoRequest modifyEmployeeInfoRequest, HttpServletRequest request){
		BaseResponse baseResponse = new BaseResponse();
		if (!GenericValidate.validate(modifyEmployeeInfoRequest)) {
			baseResponse.putErrorResult("必填参数不能为空");
            return baseResponse;
        }
		//校验员工是否存在
		TitanUserBindInfoDTO userBindInfoExistDTOParam = new TitanUserBindInfoDTO();
		userBindInfoExistDTOParam.setMerchantcode(modifyEmployeeInfoRequest.getCoopOrgCode());
		userBindInfoExistDTOParam.setFcuserid(Long.valueOf(modifyEmployeeInfoRequest.getCoopUserId()));
		TitanUserBindInfoDTO coopUserInfoExistDTO = titanFinancialUserService.getUserBindInfoByFcuserid(userBindInfoExistDTOParam);
		if(coopUserInfoExistDTO==null){
			baseResponse.putErrorResult("未找到该员工信息");
			baseResponse.setReturnCode("EMPLOYEE_NOT_EXIST");
			log.info("修改金融员工接口，未找到该员工信息，参数："+Tools.gsonToString(modifyEmployeeInfoRequest));
            return baseResponse;
		}
		
		//修改
		UpdateUserRequest updateUserRequest = new UpdateUserRequest();
		updateUserRequest.setTfsUserId(coopUserInfoExistDTO.getTfsuserid());
		updateUserRequest.setUserName(modifyEmployeeInfoRequest.getCoopUserName());
		updateUserRequest.setRoleIdList(roleCodeToRoleId(modifyEmployeeInfoRequest.getRoleCode()));
		updateUserRequest.setUnselectRoleIdList(null);
		try {
			UpdateUserResponse updateUserResponse = titanFinancialUserService.updateUser(updateUserRequest);
			if(updateUserResponse.isResult()){
				baseResponse.putSuccess("修改成功");
			}else{
				baseResponse.putErrorResult(updateUserResponse.getReturnMessage());
			}
		} catch (GlobalServiceException e) {
			log.error("修改时保存员工信息失败，参数updateUserRequest："+JSONSerializer.toJSON(updateUserRequest).toString(),e);
			baseResponse.putErrorResult("修改失败");
		}
		return baseResponse;
	}
	
}
