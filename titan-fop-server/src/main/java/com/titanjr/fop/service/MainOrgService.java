package com.titanjr.fop.service;

import com.titanjr.fop.dto.server.request.OptOrgRequest;
import com.titanjr.fop.dto.server.response.OptOrgResponse;
import com.titanjr.fop.dto.server.response.UpdateOrgReponse;

/***
 * 机构
 * @author luoqinglong
 * @date 2018年1月26日
 */
public interface MainOrgService {
	/***
	 * 注册个人和企业机构
	 * @param request
	 * @return
	 */
	OptOrgResponse optOrg(OptOrgRequest request);
	
	/**
	 * 修改机构
	 * @param request
	 * @return
	 */
	UpdateOrgReponse updateOrg(OptOrgRequest request);
	
	
	/***
	 * 个人机构注册和修改
	 * @return
	 */
	//WheatfieldPersonAccountoprResponse oprPersonAccount(WheatfieldPersonAccountoprRequest request);
	
	/***
	 * 企业账户注册
	 * @param request
	 * @return
	 */
	//WheatfieldEnterpriseEntityaccountoptResponse optEnterpriseEntityaccount(WheatfieldEnterpriseEntityaccountoptRequest request);
	 
	
	/***
	 * 修改企业信息
	 * @param request
	 * @return
	 */
	//WheatfieldEnterpriseUpdatecompanyinfoResponse updateEnterpriseEntityaccount(WheatfieldEnterpriseUpdatecompanyinfoRequest request);
	
	//查询机构
	
	
	
	
}
