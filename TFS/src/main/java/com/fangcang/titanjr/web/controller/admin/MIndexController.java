package com.fangcang.titanjr.web.controller.admin;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fangcang.titanjr.common.enums.OrgCheckResultEnum;
import com.fangcang.titanjr.common.enums.entity.TitanOrgEnum;
import com.fangcang.titanjr.dto.request.FinancialOrganQueryRequest;
import com.fangcang.titanjr.service.TitanFinancialOrganService;
import com.fangcang.titanjr.service.TitanHelpService;
import com.fangcang.titanjr.web.controller.BaseController;

/**
 * 首页后台管理控制器
 * @author luoqinglong
 * @2016年11月15日
 */
@Controller
@RequestMapping("/admin")
public class MIndexController extends BaseController{
	private static final Log logger = LogFactory.getLog(MIndexController.class);
	
	@Resource
	private TitanHelpService titanHelpService;
	
	@Resource
	private TitanFinancialOrganService organService;
	
	/**
	 * 首台首页
	 * @return
	 */
	@RequestMapping("/index")
	public String adminIndex(){
		
		return "admin/admin-index";
	}
	/**
	 * 头部公共数据
	 */
	@ResponseBody
	@RequestMapping("/data")
	public String data(){
		Map<String, Object> result = new HashMap<String, Object>();
		//企业开通申请待审核数量
		FinancialOrganQueryRequest enterpriseRequest = new FinancialOrganQueryRequest();
		enterpriseRequest.setResultKey(OrgCheckResultEnum.FT.getResultkey());
		enterpriseRequest.setUserType(TitanOrgEnum.UserType.ENTERPRISE.getKey());
		int enCount = organService.countOrg(enterpriseRequest);
		result.put("enCount", enCount);
		//个人开通申请待审核数量
		FinancialOrganQueryRequest personalRequest = new FinancialOrganQueryRequest();
		personalRequest.setResultKey(OrgCheckResultEnum.FT.getResultkey());
		personalRequest.setUserType(TitanOrgEnum.UserType.PERSONAL.getKey());
		int perCount = organService.countOrg(personalRequest);
		result.put("perCount", perCount);
		
		return toJson(result);
	}
	
}
